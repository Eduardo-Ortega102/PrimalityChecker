
import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

public class NonPrimesGenerator {

    public static void generate() throws Exception {
        createNonPrimesFile(readPrimesFile());
    }

    private static List<BigInteger> readPrimesFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("primes.txt")));
        List<BigInteger> list = new ArrayList<>();
        while (true) {
            String number = reader.readLine();
            if (number == null) break;
            list.add(new BigInteger(number));
        }
        reader.close();
        System.out.println("Primes' reading completed!");
        return list;
    }

    private static void createNonPrimesFile(List<BigInteger> list) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("nonPrimes.txt")));
        for (int i = 0; i < sqrt(list.size()); i++)
            for (int j = list.size() - 1; j > list.size() - sqrt(list.size()); j--)
                writer.write(list.get(i).multiply(list.get(j)) + "\n");
        writer.close();
        System.out.println("NonPrimes' writing completed!");
    }

}
