package ru.job4j.tracker.oop;

public class Max {
        public static int max(int left, int right) {
            int max = left > right ? left : right;
            return max;
        }

        public static int max(int first, int second, int third) {
            return max(max(first, second), third);
        }

        public static int max(int first, int second, int third, int fourth) {
            return max(max(max(first, second), third), fourth);
        }
}
