package io;

import functions.TabulatedFunction;
import functions.factory.LinkedListTabulatedFunctionFactory;
import operations.TabulatedDifferentialOperator;

import java.io.*;
import java.text.ParseException;

public class TabulatedFunctionFileInputStream {
    public static void main(String[] args) throws ParseException{
        try(BufferedInputStream bufferedInputStream=new BufferedInputStream(new FileInputStream("Lab2/output/linked list.bin"))){
            var factory= new LinkedListTabulatedFunctionFactory();
            System.out.println(FunctionsIO.readTabulatedFunction(bufferedInputStream,factory).toString());
       }
        catch(IOException e){
            e.printStackTrace();
        }
        try {
           BufferedReader buffInpStream = new BufferedReader(new InputStreamReader(System.in));
           System.out.println("Введите размер и значения функции:");
           TabulatedFunction func=FunctionsIO.readTabulatedFunction(buffInpStream,new LinkedListTabulatedFunctionFactory());
           TabulatedDifferentialOperator diffOp=new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
           TabulatedFunction nu_func=diffOp.derive(func);
           System.out.println(nu_func.toString());
       }
        catch (IOException e){
           e.printStackTrace();
        }
    }
}
