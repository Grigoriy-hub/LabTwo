package io;

import functions.LinkedListTabulatedFunction;
import functions.factory.LinkedListTabulatedFunctionFactory;
import operations.TabulatedDifferentialOperator;

import java.io.*;

public class LinkedListTabulatedFunctionSerialization {

    public static void main(String[] args){
            try(BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(new FileOutputStream("Lab2/output/serialized linked list function.bin"))){
                LinkedListTabulatedFunction func = new LinkedListTabulatedFunction(new double[]{0, 1, 4, 5}, new double[]{8, 4, 9, 3});
                TabulatedDifferentialOperator diffOp=new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
                var nu_func = (LinkedListTabulatedFunction)diffOp.derive(func);
                var nuer_func=(LinkedListTabulatedFunction)diffOp.derive(nu_func);
                FunctionsIO.serialize(bufferedOutputStream,func);
                FunctionsIO.serialize(bufferedOutputStream,nu_func);
                FunctionsIO.serialize(bufferedOutputStream,nuer_func);
                bufferedOutputStream.flush();
            }catch(IOException e){
                e.printStackTrace();
            }
            try(BufferedInputStream bufferedInputStream=new BufferedInputStream(new FileInputStream("Lab2/output/serialized linked list function.bin"))){
                System.out.println(FunctionsIO.deserialize(bufferedInputStream).toString());
                System.out.println(FunctionsIO.deserialize(bufferedInputStream).toString());
                System.out.println(FunctionsIO.deserialize(bufferedInputStream).toString());
            }
            catch(IOException|ClassNotFoundException e){
                e.printStackTrace();
            }
    }
}
