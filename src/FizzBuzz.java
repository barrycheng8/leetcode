// LeetCode 412. Fizz Buzz
// Write a program that outputs string representations from 1 to n.
// Multiples of 3: print Fizz
// Multiples of 5: print Buzz
// Multiples of 3 and 5: print Fizz Buzz
//
// Intuition: Iterate and add the appropriate value based on the current iteration
//
// Growth functions:
// O(n) runtime: need to iterate up to n to get results up to n
// O(n) space: we need to store n results

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {

    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) res.add("FizzBuzz");
            else if (i % 5 == 0) res.add("Buzz");
            else if (i % 3 == 0) res.add("Fizz");
            else res.add(Integer.toString(i));
        }

        return res;
    }
}
