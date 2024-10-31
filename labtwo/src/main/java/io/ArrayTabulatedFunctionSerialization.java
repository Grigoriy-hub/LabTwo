package io;

import functions.ArrayTabulatedFunction;
import operations.TabulatedDifferentialOperator;
import java.io.*;

public class ArrayTabulatedFunctionSerialization {
    public static void main(String[] args) {
        try (FileOutputStream out1 = new FileOutputStream("Lab2/output/serialised array function.bin")) {
            BufferedOutputStream bos1 = new BufferedOutputStream(out1);
            ArrayTabulatedFunction test1 = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 4, 9, 16});
            TabulatedDifferentialOperator help1 = new TabulatedDifferentialOperator();
            var test1DO1 =  help1.derive(test1);
            var test1DO2 =  help1.derive(test1DO1);
            FunctionsIO.serialize(bos1, test1);
            FunctionsIO.serialize(bos1, test1DO1);
            FunctionsIO.serialize(bos1, test1DO2);
            bos1.flush();
        } catch (IOException e) {

            e.printStackTrace();
        }
        try (FileInputStream out2 = new FileInputStream("Lab2/output/serialised array function.bin")) {
            BufferedInputStream bos2 = new BufferedInputStream(out2);
            ArrayTabulatedFunction test1 = (ArrayTabulatedFunction) FunctionsIO.deserialize(bos2);
            ArrayTabulatedFunction test1DO1 = (ArrayTabulatedFunction) FunctionsIO.deserialize(bos2);
            ArrayTabulatedFunction test1DO2 = (ArrayTabulatedFunction) FunctionsIO.deserialize(bos2);
            System.out.println(test1.toString());
            System.out.println(test1DO1.toString());
            System.out.println(test1DO2.toString());
        } catch (IOException | ClassNotFoundException e) {

            e.printStackTrace();
        }
    }
}