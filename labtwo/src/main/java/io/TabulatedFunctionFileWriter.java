package io;

import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TabulatedFunctionFileWriter {
    public static void main(String[] args) throws IOException {
        try (FileWriter out1 = new FileWriter("Lab2/output/array function.txt");
             BufferedWriter bos1 = new BufferedWriter(out1)) {
            ArrayTabulatedFunction test1Array = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 4, 9, 16});
            io.FunctionsIO.writeTabulatedFunction(bos1, test1Array);
        } catch (IOException e) {

            e.printStackTrace();
        }
        try (FileWriter out2 = new FileWriter("Lab2/output/linked list function.txt");
             BufferedWriter bos2 = new BufferedWriter(out2)) {
            LinkedListTabulatedFunction test2Array = new LinkedListTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 4, 9, 16});
            FunctionsIO.writeTabulatedFunction(bos2, test2Array);
        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}
