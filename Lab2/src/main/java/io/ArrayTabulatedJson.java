package io;

import functions.ArrayTabulatedFunction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;

public class ArrayTabulatedJson {
    public static void main(String[] args) throws IOException {
        try (FileWriter out1 = new FileWriter("Lab2/output/array function.json");
             BufferedWriter bos1 = new BufferedWriter(out1)) {
            ArrayTabulatedFunction test1Array = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 4, 9, 16});
            FunctionsIO.serializeJson(bos1, test1Array);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileReader out2 = new FileReader("Lab2/output/array function.json");
             BufferedReader bos2 = new BufferedReader(out2)) {
            ArrayTabulatedFunction test2Array = FunctionsIO.deserializeJson(bos2);
            System.out.println(test2Array);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
