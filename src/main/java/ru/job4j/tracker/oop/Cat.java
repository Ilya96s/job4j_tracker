package ru.job4j.tracker.oop;

public class Cat {

    private String food;
    private String name;

    public void show() {
        System.out.println(this.name);
        System.out.println(this.food);
    }

    public void eat(String meat) {
        this.food = meat;
    }

    public void giveNick(String nick) {
        this.name = nick;
    }

    public static void main(String[] args) {
        System.out.println("There are gav's food");
        Cat gav = new Cat();
        gav.name = ("gav");
        gav.eat("Kotletka");
        gav.show();
        System.out.println("There are black's food");
        Cat black = new Cat();
        black.name = ("black");
        black.eat("fish");
        black.show();
    }
}