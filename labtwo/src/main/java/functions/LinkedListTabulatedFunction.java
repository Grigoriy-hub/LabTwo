package functions;

import exceptions.ArrayIsNotSortedException;
import exceptions.DifferentLengthOfArraysException;
import exceptions.InterpolationException;

import java.io.Serial;
import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements TabulatedFunction,Removable, Insertable, Serializable {


    @Serial
    private static final long serialVersionUID = -2011337478607682793L;

    protected static class Node implements Serializable {

        @Serial
        private static final long serialVersionUID = -592560142370147150L;
        public Node next=null;
        public Node prev=null;
        public double x;
        public double y;
        public Node(double x,double y){this.x=x;this.y=y;}
    }
    protected Node head=null;
    protected void addNode(double x,double y){
        Node nu_node=new Node(x,y);
        if (this.head!=null){
            Node last = head.prev;
            nu_node.prev=last;
            nu_node.next=head;
            last.next=nu_node;
            head.prev=nu_node;
        }
        else {
            this.head=nu_node;
            head.prev=head.next=head;
        }
        this.count++;
    }
    public LinkedListTabulatedFunction(double[] xValues,double[] yValues) throws DifferentLengthOfArraysException, ArrayIsNotSortedException {
        if(xValues.length<2)throw new IllegalArgumentException("Construction of a Tabulated function requires at least 2 points");
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);
        for(int i=0;i<xValues.length;i++){addNode(xValues[i],yValues[i]);}
    }
    public LinkedListTabulatedFunction(MathFunction source,double xFrom,double xTo,int count){
        if(count<2)throw new IllegalArgumentException("Construction of a Tabulated function requires at least 2 points");
        if(Math.abs(xFrom-xTo)>EPSILON){
            if(xFrom>xTo){
                double tmp= xFrom;
                xFrom=xTo;
                xTo=tmp;
            }
            double step=(xTo-xFrom)/(count-1);
            for(int i=0;i<count;i++){
                double x0 = xFrom + step * i;
                addNode(x0,source.apply(x0));
            }
        }else{
            double y=source.apply(xFrom);
            for(int i=0;i<count;i++){addNode(xFrom,y);}
        }
    }
    @Override
    public int getCount(){return count;}
    @Override
    public double leftBound(){return this.head.x;}
    @Override
    public double rightBound(){return this.head.prev.x;}
    protected Node getNode(int index){
        if(index<0)throw new IllegalArgumentException("Index can't be less than zero");
        if(index>count-1)throw new IllegalArgumentException("Index out of bounds");
        if (index<=((this.count-1)/2)){
            Node nu_node=this.head;
            for(int i=0;i<index;i++){nu_node=nu_node.next;}
            return nu_node;
        }
        else{
            Node nu_node=this.head.prev;
            for(int i=count-1;i>index;i--){nu_node=nu_node.prev;}
            return nu_node;
        }
    }
    @Override
    public double getX(int index){
        if(index<0)throw new IllegalArgumentException("Index can't be less than zero");
        if(index>count-1)throw new IllegalArgumentException("Index out of bounds");
        return getNode(index).x;
    }
    @Override
    public double getY(int index){
        if(index<0)throw new IllegalArgumentException("Index can't be less than zero");
        if(index>count-1)throw new IllegalArgumentException("Index out of bounds");
        return getNode(index).y;
    }
    @Override
    public void setY(int index,double value){
        if(index<0)throw new IllegalArgumentException("Index can't be less than zero");
        if(index-1>count)throw new IllegalArgumentException("Index out of bounds");
        getNode(index).y=value;
    }
    @Override
    public int indexOfX(double x){
        int index=0;
        Node nu_node=this.head;
        while((Math.abs(nu_node.x-x)>EPSILON)&&(index<this.count)){nu_node=nu_node.next;index++;}
        if(index<this.count){return index;}
        else{return -1;}
    }
    @Override
    public int indexOfY(double y){
        int index=0;
        Node nu_node=this.head;
        while((Math.abs(nu_node.y-y)>EPSILON)&&(index<this.count)){nu_node=nu_node.next;index++;}
        if(index<this.count){return index;}
        else{return -1;}
    }
    @Override
    protected  int floorIndexOfX(double x){
        if (x < leftBound()) {throw new IllegalArgumentException("Argument can't be less than the leftBound");}
        if (x > rightBound()) return count;
        Node nu_node=this.head;
        int index;
        int tmpInd=0;
        do{
            index=tmpInd;
            tmpInd++;
            nu_node=nu_node.next;
        }while(nu_node!=this.head && nu_node.x<=x);
        return index;
    }
    @Override
    protected double extrapolateRight(double x){
        double leftX=this.head.prev.prev.x;
        double rightX=this.head.prev.x;
        double leftY=this.head.prev.prev.y;
        double rightY=this.head.prev.y;
        return interpolate(x,leftX,rightX,leftY,rightY);
    }
    @Override
    protected double extrapolateLeft(double x){
        double leftX=this.head.x;
        double rightX=this.head.next.x;
        double leftY=this.head.y;
        double rightY=this.head.next.y;
        return interpolate(x,leftX,rightX,leftY,rightY);
    }
    protected  double interpolate(double x,Node floorNode){
        return interpolate(x,floorNode.x,floorNode.next.x,floorNode.y,floorNode.next.y);
    }
    @Override
    protected  double interpolate(double x,int floorIndex) throws InterpolationException {
        if(floorIndex >= (count-1)){
            throw new InterpolationException();
        }
        for(int i = 0; i <= (count - 1); i++){
            if(x == getNode(i).x){
                throw new InterpolationException();
            }
        }
        if(!((x > getNode(floorIndex).x)&&(x < getNode((floorIndex+1)).x))){
            throw new InterpolationException();
        }
        Node nu_node=getNode(floorIndex);
        return interpolate(x,nu_node);
    }
    protected  Node floorNodeOfX(double x){
        if (x < leftBound()) throw new IllegalArgumentException("Argument can't be less than the leftBound");
        if (x > rightBound()) return this.head.prev;
        Node nu_node=this.head;
        Node floorNode;
        do{
            floorNode=nu_node;
            nu_node=nu_node.next;
        }while(nu_node!=this.head && nu_node.x<=x);
        return floorNode;
    }
    @Override
    public double apply(double x) {
        if (x < leftBound()) {return extrapolateLeft(x);}
        else if (x > rightBound()) return extrapolateRight(x);
        else if(indexOfX(x)==-1){
            return interpolate(x,floorNodeOfX(x));
        }
        return getY(floorIndexOfX((x)));
    }
    @Override
    public void remove(int index){
        if(count==2)throw new UnsupportedOperationException("Cannot remove from a function with only 2 elements");
        if(index<0)throw new IllegalArgumentException("Index can't be less than zero");
        if(index>count-1)throw new IllegalArgumentException("Index out of bounds");
        if(index==0){
            this.head.next.prev=this.head.prev;
            this.head=this.head.next;
        }

        else if(index==count-1){
            this.head.prev.prev.next=this.head;
            this.head.prev=this.head.prev.prev;
        }
        else{
            Node nu_node = getNode(index);
            nu_node.prev.next=nu_node.next;
            nu_node.next.prev=nu_node.prev;
        }
        count--;
    }
    public void insert(double x, double y) {
        if (this.head == null) {
            addNode(x, y);
        } else {
            Node currentNode = this.head;
            do {
                if (currentNode.x == x) {
                    currentNode.y = y;
                    return;
                }
                currentNode = currentNode.next;
            } while (currentNode != this.head);
            do {
                if ((currentNode.x < x) && (currentNode.next.x > x)) {
                    Node nu_node = new Node(x, y);
                    Node last = currentNode;
                    nu_node.prev = last;
                    nu_node.next = currentNode.next;
                    last.next = nu_node;
                    currentNode.next.prev = nu_node;
                    this.count++;
                    return;
                }
                currentNode = currentNode.next;
            } while (currentNode != this.head);
            if (leftBound() > x){
                Node nu_node = new Node(x, y);
                Node last = currentNode.prev;
                nu_node.prev = last;
                nu_node.next = currentNode;
                last.next = nu_node;
                currentNode.prev = nu_node;
                this.head = nu_node;
                this.count++;
            }
            else{
                Node nu_node = new Node(x, y);
                Node last = currentNode.prev;
                nu_node.prev = last;
                nu_node.next = currentNode;
                last.next = nu_node;
                currentNode.prev = nu_node;
                this.count++;
            }
        }
    }
    @Override
    public Iterator<Point> iterator(){
        return new Iterator<Point>(){
            private Node nu_node=head;
            @Override
            public boolean hasNext(){
                return nu_node!=null;
            }
            @Override
            public Point next(){
                if (this.hasNext()){
                    Point p= new Point(nu_node.x,nu_node.y);
                    if(nu_node.next==head)nu_node=null;
                    else nu_node=nu_node.next;
                    return p;
                }
                throw new NoSuchElementException();
            }
        };
    }


}