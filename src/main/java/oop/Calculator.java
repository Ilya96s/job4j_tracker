package oop;

public class Calculator {

    private static int x = 5;

    public static int sum(int y) {
        return x + y;
    }

    private int multiply(int a) {
        return x * a;
    }

    public static int minus(int c) {
        return c - x;
    }

    public int devide(int b) {
        return b / x;
    }

    public int sumAllOperation(int d) {
        return sum(d) + multiply(d) + minus(d) + devide(d);
    }

    public static void main(String[] args) {
        int rsl1 = sum(10);
        System.out.println("x + y = " + rsl1);
        int rsl2 = minus(10);
        System.out.println("c - x = " + rsl2);
        Calculator calculator = new Calculator();
        int rsl3 = calculator.multiply(15);
        System.out.println("x * a = " + rsl3);
        int rsl4 = calculator.devide(25);
        System.out.println("b / x = " + rsl4);
        int rsl5 = calculator.sumAllOperation(10);
        System.out.println("The sum of all operations is : " + rsl5);
    }
}