package io;

import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Locale;

import functions.ArrayTabulatedFunction;
import functions.Point;
import functions.TabulatedFunction;
import functions.factory.TabulatedFunctionFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.thoughtworks.xstream.XStream;

public final class FunctionsIO {
    public static void serialize(BufferedOutputStream stream, TabulatedFunction function) throws IOException {
        ObjectOutputStream serialiseStream = new ObjectOutputStream(stream);
        serialiseStream.writeObject(function);
        serialiseStream.flush();
    }

    private FunctionsIO() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    public static TabulatedFunction readTabulatedFunction(BufferedReader reader, TabulatedFunctionFactory factory) throws IOException, ParseException {
        int count = Integer.parseInt(reader.readLine());
        double[] xValues = new double[count];
        double[] yValues = new double[count];
        NumberFormat numberFormatter = NumberFormat.getInstance(Locale.forLanguageTag("ru"));

         for(int i=0;i<count;i++){
             String line=reader.readLine();
             String[] twoString = line.split(" ");
             try{
                Number numberXValues = numberFormatter.parse(twoString[0]);
                Number numberYValues = numberFormatter.parse(twoString[1]);
                xValues[i] = numberXValues.doubleValue();
                yValues[i] = numberYValues.doubleValue();
            }catch(ParseException e){
                 throw new IOException(e);
             }
        }
        return factory.create(xValues, yValues);

    }

    public static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function){
        PrintWriter wrapper = new PrintWriter(writer);
        wrapper.println(function.getCount());
        Iterator<Point> iterator = function.iterator();
        Point[] array = new Point[function.getCount()];
        for (Point point : array ) {
            point = iterator.next();
            wrapper.printf("%f %f\n", point.x, point.y);
        }
        wrapper.flush();
    }
    static void writeTabulatedFunction(BufferedOutputStream outputStream,TabulatedFunction function) throws IOException{
        DataOutputStream nu_stream=new DataOutputStream(outputStream);
        nu_stream.writeInt(function.getCount());
        for(Point p:function){
            nu_stream.writeDouble(p.x);
            nu_stream.writeDouble(p.y);
        }
        nu_stream.flush();
    }
    static TabulatedFunction readTabulatedFunction(BufferedInputStream inputStream, TabulatedFunctionFactory factory)throws IOException{
        DataInputStream nu_inputStream=new DataInputStream(inputStream);
        int count=nu_inputStream.readInt();
        double[] xValues=new double[count];
        double[] yValues=new double[count];
        for(int i=0;i<count;i++){
            xValues[i]=nu_inputStream.readDouble();
            yValues[i]=nu_inputStream.readDouble();
        }
        return factory.create(xValues,yValues);
    }
    public static TabulatedFunction deserialize(BufferedInputStream inStream)throws IOException,ClassNotFoundException{
        ObjectInputStream nu_inStream=new ObjectInputStream(inStream);
        Object function=nu_inStream.readObject();
        return (TabulatedFunction) function;
    }
    public static void serializeJson(BufferedWriter writer, ArrayTabulatedFunction function)throws IOException{
        ObjectMapper objectMapper=new ObjectMapper();
        writer.write(objectMapper.writeValueAsString(function));
        writer.flush();
    }
    public static ArrayTabulatedFunction deserializeJson(BufferedReader reader)throws IOException{
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper.readerFor(ArrayTabulatedFunction.class).readValue(reader);
    }



    public static void serializeXml(BufferedWriter writer, TabulatedFunction function) throws IOException {
        XStream xStream = new XStream();
        String xml = xStream.toXML(function);
        writer.write(xml);
        writer.flush();
    }

    public static ArrayTabulatedFunction deserializeXml(BufferedReader reader) throws IOException {
        XStream xStream = new XStream();
        xStream.allowTypes(new Class[]{ArrayTabulatedFunction.class});
        return (ArrayTabulatedFunction) xStream.fromXML(reader);
    }




}
