package ee.bcs.valiit.tasks;

import java.util.Scanner;

// TODO kasuta if/else. Ära kasuta Math librarit
public class Lesson1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("vali üks kuuest numbrist 1, 2, 3, 4 ,5, 6");
        int num = scanner.nextInt();
        if (num == 1) {
            System.out.println("sisesta kaks täisarvu");
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            System.out.println(min(a, b));
        } else if (num == 2) {
            System.out.println("Sisesta 2 täisarvu");
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            System.out.println(max(a, b));
        } else if (num == 3) {
            System.out.println("Sisesta absoluutarv");
            int a = scanner.nextInt();
            System.out.println(abs(a));
        } else if (num == 4) {
            System.out.println("Sisesta absoluutarv");
            int a = scanner.nextInt();
            System.out.println(abs(a));
        } else if (num == 5) {
            System.out.println("Sisesta kolmest arvust kõige väiksem");
            int a = scanner.nextInt();
            int b = scanner.nextInt();

        }

        System.out.println(min(1, 3)); // trükib miinimumi 1 ja 3
        System.out.println();
    }

    // TODO tagasta a ja b väikseim väärtus
    public static int min(int a, int b) {
        if (a < b) {
            return a;
        } else {
            return b;
        }
    }


    // TODO tagasta a ja b suurim väärtus
    public static int max(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    // TODO tagasta a absoluut arv
    public static int abs(int a) {
        if (a < 0) {
            return -a;
        } else {
            return a;
        }
    }

    // TODO tagasta true, kui a on paaris arv
    // tagasta false kui a on paaritu arv
    public static boolean isEven(int a) {
        if (a % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    // TODO tagasta kolmest arvust kõige väiksem
    public static int min3(int a, int b, int c) {
        if (a <= b && b <= c) {
            return a;
        } else if (b <= a && b <= c) {
            return b;
        } else {
            return c;
        }
    }

    // TODO tagasta kolmest arvust kõige suurem
    public static int max3(int a, int b, int c) {
        if (a >= b && b >= c) {
            return a;
        } else if (b >= a && b >= c) {
            return b;
        } else {
            return c;
        }
    }
}

