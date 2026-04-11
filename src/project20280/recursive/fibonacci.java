package project20280.recursive;
import java.util.Timer;
import java.util.TimerTask;

// Why didnt I use timerTask?
// I found out that timerTask is more for delaying an action rather than timing things

public class fibonacci {

    // For checking how many times the function has been called
    static long callCount = 0;

    public static void main(String[] args) {
        System.out.println("Starting 1-min Fibonacci test...\n");

        int n = 0;
        long startTime = System.currentTimeMillis();
        long oneMin = 60000;

        // While the timer is still under the 1 minute limit
        while(true) {
            callCount = 0;
            long result = fibonacci(n);

            // makes sure that it is under the time limit
            long endTask = System.currentTimeMillis();
            long duration = endTask - startTime;
            System.out.println("F: (" + n + ") = " + result);

            if (duration > oneMin) {
                System.out.println("\n--- LIMIT REACHED ---");
                System.out.println("The largest Fibonacci number computed is: F(" + n + ")");
                System.out.println("Total recursive calls for this number: " + callCount);
                break;
            }
            n++;
          }
        int triNum = 9;
        System.out.println("Tri: (" + triNum + "): " + tribonacci(triNum));

        int nestedNum = 87;
        System.out.println("Nested: (" + nestedNum + "): " + Q4(nestedNum));
        }

        public static long fibonacci(int n) {
        callCount++;
        if(n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
        }


        public static long tribonacci(int n) {
        if (n == 1) return 0;
        if (n == 2) return 0;
        if (n == 3) return 1;
        return (tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3));
        }

        // This is a nested recursion!
        public static int Q4(int n) {
          if(n > 100) return n -10;
          else {
              return Q4(Q4(n + 11));
          }
        }
}
