package ee.bcs.valiit.tasks;

import java.util.Arrays;

public class Lesson2b {
    public static void main(String[] args) {
        exercise1(5);
        System.out.println(Arrays.toString(sampleArray()));
        System.out.println(Arrays.toString(generateArray(7)));
        System.out.println(Arrays.toString(decreasingArray(-9)));
        System.out.println(Arrays.toString(yl3(11)));

    }

    // TODO trüki välja n arvu
    // näiteks
    // sisend: 5
    // trüki välja: 1 2 3 4 5
    public static int exercise1(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.println(i);
        }
        return n;
    }

    // TODO tagasta massiiv milles oleks numbrid 1,2,3,4,5
    public static int[] sampleArray() {
        int[] mass = {1, 2, 3, 4, 5};
        return mass;
    }


    // TODO loo massiiv mis saab sisendiks n ja tagastab massiivi suurusega n
    // Kus esimene element on 1 ja iga järnev arv on 1 võrra suurem
    // näiteks:
    // sisend: 5
    // vastus: {1, 2, 3, 4, 5}
    public static int[] generateArray(int n) {
        int[] numbers = new int[n];
        for (int i = 1; i < n; i++) {
            numbers[i] = i + 1;
        }
        return numbers;
    }

    // TODO
    // Tagastada massiiv kus oleks numbrid n-ist 0 ini
    // Näiteks: sisend: 5
    // Väljund: 5, 4, 3, 2, 1, 0
    // Näide2: siend: -3
    // Väljund: -3, -2, -1, 0
    public static int[] decreasingArray(int n) {
            if (n >= 0) {
                int[] resultArray = new int[n + 1];
                int index = 0;
                for (int i = n; i >= 0; i--) {
                    resultArray[index] = i;
                    index++;
                }
                return resultArray;
            } else {
                int maxIndex = -n;
                int[] resultArray = new int[maxIndex + 1];
                for (int i = 0; i >= maxIndex; i++) {
                    resultArray[i] = n + i;
                }
                return resultArray;
            }
        }

        // TODO
        // tagasta massiiv pikkusega n, mille kõigi elementide väärtused on 3
        public static int[] yl3 ( int n) {
            int[] resultArray = new int[n];

            for (int i = 0; i < n; i++) {
                resultArray[i] = 3;
            }
            return resultArray;
        }
    }



