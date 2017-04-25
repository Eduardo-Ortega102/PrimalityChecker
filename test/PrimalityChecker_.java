
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PrimalityChecker_ {

    @Test
    public void should_return_false_if_a_number_is_not_prime() throws Exception {
        File file = new File("nonPrimes.txt");
        if (!file.exists()) NonPrimesGenerator.generate();
        assertFile(file);
    }

    @Test
    public void should_return_true_if_a_number_is_prime() throws Exception {
        assertFile(new File("primes.txt"));
    }

    private void assertFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        long total = 0;
        long lines = 0;
        while (true) {
            String number = reader.readLine();
            if (number == null) break;
            boolean isPrime;
            long start = System.nanoTime();
            isPrime = PrimalityChecker.isPrime(new BigInteger(number));
            total += System.nanoTime() - start;
            lines++;
            if (file.getName().equals("primes.txt")) {
                assertTrue(isPrime);
            } else {
                assertFalse(isPrime);
            }
        }
        System.out.println("Tiempo (seg): " + total/1e9 + " Numeros: " + lines);
        reader.close();
    }

}
