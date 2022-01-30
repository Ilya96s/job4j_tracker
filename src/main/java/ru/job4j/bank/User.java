package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс описывает модель пользователя банка
 * @author ILYA KALTYGIN
 * @version 1.0
 */
public class User {
    /**
     * Данные паспорта пользователя
     */
    private String passport;
    /**
     * Имя пользователя
     */
    private String username;

    /**
     * Конструктор класса User
     * @param passport данные паспорта
     * @param username имя пользователя
     */
    public User(String passport, String username) {
        this.passport = passport;
        this.username = username;
    }

    /**
     * Метод предоставляет доступ к данным паспорта пользователя
     * @return возвращает переменную passport
     */
    public String getPassport() {
        return passport;
    }

    /**
     * Метод позволяет установить данные паспорта пользователя
     * @param passport
     */
    public void setPassport(String passport) {
        this.passport = passport;
    }

    /**
     * Метод предоставляет доступ к имени пользователя
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * Метод позволяет установить имя пользователя
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Метод сравнивает объекты типа User по значению поля passport
     * @param o сравниваемый объект
     * @return возвращает true если объекты равны и false при неравенстве
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(passport, user.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passport);
    }
}
