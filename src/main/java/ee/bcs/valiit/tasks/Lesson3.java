package ee.bcs.valiit.tasks;

import java.util.HashMap;
import java.util.Map;

public class Lesson3 {

    public static void main(String[] args) {
        System.out.println(factorial(5));
        System.out.println(reverseString("Veiko"));
        System.out.println(isPrime(7));

    }

    // TODO tagasta x faktoriaal.
    // Näiteks
    // x = 5
    // return 5*4*3*2*1 = 120
    public static int factorial(int x) {
        int factorial = x;
        for (int i = 1; i < x; i++) {
            factorial = factorial * i;
        }
        return factorial;
    }

    // TODO tagasta string tagurpidi
    public static String reverseString(String a) {
        String text = "";
        for (int i = a.length() - 1; i >= 0; i--) {
            System.out.println(a.charAt(i));
            text = text + a.charAt(i);
        }
        return text;
    }

    // TODO tagasta kas sisestatud arv on primaar arv (jagub ainult 1 ja iseendaga)
    public static boolean isPrime(int x) {
        if (x == 1 || x == 0) {
            return false;
        } else {
            for (int i = 2; i < x; i++) {
                if (x % i == 0) ;
            }
            return false;
        }
    }

    // TODO sorteeri massiiv suuruse järgi.
    // TODO kasuta tsükleid, ära kasuta ühtegi olemasolevat sort funktsiooni
    public static int[] sort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = 1; j < a.length; j++) {
                if (a[i - 1] > a[i]) {
                    int tmp = a[i];
                    a[i] = a[i - 1];
                    a[i - 1] = tmp;

                }
            }
        }
        return a;
    }

    // TODO liida kokku kõik paaris fibonacci arvud kuni numbrini x
    public static int evenFibonacci(int x) {
        int a = 0;
        int b = 1;
        int sum = 0;
        while (b <= x) {
            int c = a;
            a = b;
            b = c + b;
            if (a % 2 == 0) {
                sum += a;

            }
        }
        return sum;
    }


    // TODO kirjuta programm, mis tagastab sisestatud teksti morse koodis (https://en.wikipedia.org/wiki/Morse_code)
    // Kasuta sümboleid . ja - ning eralda kõik tähed tühikuga
    public static String morseCode(String text) {
        Map<Character, String> morseMap = new HashMap<>();
        morseMap.put('h', "....");
        morseMap.put('e', ".");
        morseMap.put('l', ".-..");
        morseMap.put('o', "---");
        morseMap.put('s', "...");
        //for(int i = 0; i < text.length(); i++){
        //char c = text.charAt(i);
        //}
        String resultString = "";
        for (char c : text.toCharArray()) {
            if (!resultString.isEmpty()) {
                resultString += " ";
            }
            resultString += morseMap.get(c);
        }
        return resultString;
    }
}




