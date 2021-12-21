package ru.job4j.tracker.oop;

public class Computer {

    private boolean multiMonitor;

    private int ssd;

    private String cpu;

    public Computer(boolean multiMonitor, int ssd, String cpu) {
        this.multiMonitor = multiMonitor;
        this.ssd = ssd;
        this.cpu = cpu;
    }

    public Computer() {
    }

    public Computer(int ssd, String cpu) {
        this.ssd = ssd;
        this.cpu = cpu;
    }

    public Computer(boolean multiMonitor, double ssd, String cpu) {
        this.multiMonitor = multiMonitor;
        this.ssd = (int) ssd;
        this.cpu = cpu;
    }

    public void printInfo() {
        System.out.println("Много мониторов : " + multiMonitor);
        System.out.println("SSD : " + ssd + " GGB");
        System.out.println("Модель CPU : " + cpu);
    }

    public static void main(String[] args) {
        Computer first = new Computer(true, 500, "Intel Core I7-10700K");
        first.printInfo();
        Computer second = new Computer(true, 256, "AMD Ryzen 5 3600");
        second.printInfo();
        Computer third = new Computer(256, "AMD Ryze 5 3600");
        third.printInfo();
        Computer fourth = new Computer(true, 512.0, "AMD Ryzen 7 3700X");
        fourth.printInfo();
    }
}
