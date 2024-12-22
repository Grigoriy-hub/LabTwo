package io;

import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.LinkedListTabulatedFunctionFactory;

import java.io.*;
import java.text.ParseException;

public class TabulatedFunctionFileReader {
    public static void main(String[] args) {
        ArrayTabulatedFunction test1 = null;
        LinkedListTabulatedFunction test2 = null;
        try (FileReader out1 = new FileReader("C:/Labbs/Labs-OOP-3d-sem-Chernov-Kalinin/Lab2/input/function.txt");
             BufferedReader bos1 = new BufferedReader(out1)) {
            ArrayTabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
            test1 = (ArrayTabulatedFunction) FunctionsIO.readTabulatedFunction(bos1, factory);
        } catch (IOException | ParseException e) {

            e.printStackTrace();
        }
        System.out.print(test1.toString());
        try (FileReader out2 = new FileReader("C:/Labbs/Labs-OOP-3d-sem-Chernov-Kalinin/Lab2/input/function.txt");
             BufferedReader bos2 = new BufferedReader(out2)) {
            LinkedListTabulatedFunctionFactory factory2 = new LinkedListTabulatedFunctionFactory();
            test2 = (LinkedListTabulatedFunction) FunctionsIO.readTabulatedFunction(bos2, factory2);
        } catch (IOException | ParseException e) {

            e.printStackTrace();
        }
        System.out.print(test2.toString());
    }
    

}
