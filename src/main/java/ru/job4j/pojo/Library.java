package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book book1 = new Book("Clean code", 250);
        Book book2 = new Book("Harry Potter", 400);
        Book book3 = new Book("The Help", 126);
        Book book4 = new Book("Playing God", 301);
        Book[] books = {book1, book2, book3, book4};
        for (int i = 0; i < books.length; i++) {
            Book b = books[i];
            System.out.println("В книге: " + b.getName() + " - " + b.getPages() + " страниц.");
        }
        Book tmp = books[0];
        books[0] = books[3];
        books[3] = tmp;
        for (int i = 0; i < books.length; i++) {
            Book b = books[i];
            System.out.println("В книге: " + b.getName() + " - " + b.getPages() + " страниц.");
        }
        for (int i = 0; i < books.length; i++) {
            Book b = books[i];
            if (b.equals(book1)) {
                System.out.println(b.getName() + " - " + b.getPages());
            }
        }
    }
}
