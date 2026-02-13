package sieve;

import java.util.*;

class Sieve {
    final int limit;

    Sieve(int maxPrime) {
        this.limit = maxPrime;
    }

    List<Integer> getPrimes() {
        if (limit < 2) {
            return List.of();
        }

        boolean[] primes = new boolean[limit + 1];

        for (int i = 2; i <= limit; i++) {
            primes[i] = true;
        }

        for (int i = 2; i <= Math.sqrt(limit); i++) {
            if (primes[i]) {
                for (int y = i * i; y <= limit; y += i) {
                    primes[y] = false;
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 2; i <= limit; i++) {
            if (primes[i]) {
                result.add(i);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Sieve sieve = new Sieve(2);
        System.out.println(sieve.getPrimes());
    }
}
