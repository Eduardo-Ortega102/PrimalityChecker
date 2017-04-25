import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.StrictMath.ceil;
import static java.math.BigInteger.*;


public class PrimalityChecker {

    private static final Random random = new Random();
    private static final BigInteger TWO = valueOf(2);
    private static final BigInteger THREE = valueOf(3);


    public static boolean isPrime(BigInteger number) {
        return number.compareTo(ONE) < 1 ? false :
                number.compareTo(THREE) < 1 ? true :
                        number.compareTo(TEN) < 1 ? isPrime(number, 3) : isPrime(number, 10);
    }

    private static boolean isPrime(BigInteger number, int m) {
        if (valueOf(m).compareTo(number) != -1) throw new IllegalArgumentException("Variable 'm' is too big");
        if (m < ceil(log_2(1.0 / Math.pow(2, m)))) throw new IllegalArgumentException("Variable 'm' is too little");
        ArrayList<BigInteger> k_List = null;
        for (BigInteger b : randomNumbers(m, number)) {
            if (!b.modPow(number.subtract(ONE), number).equals(ONE)) return false;
            if (k_List == null) k_List = getKList(number);
            for (BigInteger k : k_List) {
                BigInteger greatestCommonDivisor = b.modPow(k, number).subtract(ONE).gcd(number);
                if (greatestCommonDivisor.compareTo(ONE) == 1 && greatestCommonDivisor.compareTo(number) == -1)
                    return false;
            }
        }
        return true;
    }

    private static double log_2(double n) {
        return Math.log(n) / Math.log(2); // Mathematical identity: log_b(n) = log_e(n) / log_e(b)
    }

    private static BigInteger[] randomNumbers(int size, BigInteger n) {
        BigInteger[] vector = new BigInteger[size];
        BigInteger upperLimit = n.subtract(THREE);
        for (int i = 0; i < size; i++) {
            vector[i] = randomBigInteger(upperLimit).add(TWO); //BigInteger in range [2 .. n - 1]
        }
        return vector;
    }

    private static BigInteger randomBigInteger(BigInteger upperLimit) {
        BigInteger result;
        do {
            result = new BigInteger(upperLimit.bitLength(), random); //random BigInteger in range [0 .. upperLimit]
        } while (result.compareTo(upperLimit) != -1);
        return result;
    }


    private static ArrayList<BigInteger> getKList(BigInteger n) {
        ArrayList<BigInteger> k_List = new ArrayList<>();
        BigInteger numerator = n.subtract(ONE);
        BigInteger[] k;
        for (BigInteger j = valueOf(numerator.bitLength() - 1); j.compareTo(ONE) > 0; j = j.subtract(ONE)) {
            k = numerator.divideAndRemainder(TWO.modPow(j, n));
            if (k[1].equals(ZERO))
                k_List.add(k[0]);
        }
        return k_List;
    }

}
