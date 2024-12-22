package io;

import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;

import java.io.*;

public class TabulatedFunctionFileOutputStream {
    public static void main(String[] args){
        try(BufferedOutputStream arrStream=new BufferedOutputStream(new FileOutputStream("Lab2/output/array function.bin"));
            BufferedOutputStream listStream=new BufferedOutputStream(new FileOutputStream("Lab2/output/linked list.bin"))) {
            ArrayTabulatedFunction arrFunc = new ArrayTabulatedFunction(new double[]{0, 1, 3, 4}, new double[]{2, 4, 5, 3});
            LinkedListTabulatedFunction listFunc = new LinkedListTabulatedFunction(new double[]{0, 1, 4, 5}, new double[]{8, 4, 9, 3});
            FunctionsIO.writeTabulatedFunction(arrStream, arrFunc);
            FunctionsIO.writeTabulatedFunction(listStream, listFunc);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
