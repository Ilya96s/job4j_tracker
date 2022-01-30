package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс Account представляет собой модель банковского счета
 * @author ILYA KALTYGIN
 * @version 1.0
 */
public class Account {
    /**
     * Реквизиты счета
     */
    private String requisite;
    /**
     * Баланс счета
     */
    private double balance;

    /**
     * Конструктор класса Account
     * @param requisite реквизиты счета
     * @param balance баланс счета
     */
    public Account(String requisite, double balance) {
        this.requisite = requisite;
        this.balance = balance;
    }

    /**
     * Метод предоставляет доступ к реквизитам счета
     * @return возвращает переменную requisite
     */
    public String getRequisite() {
        return requisite;
    }

    /**
     * Метод позволяет установить значение рекизитов счета
     * @param requisite рекизиты счета
     */
    public void setRequisite(String requisite) {
        this.requisite = requisite;
    }

    /**
     * Метод предоставляет доступ к балансу счета
     * @return возвращает переменную balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Метод позволяет установить значение баланса счета
     * @param balance баланс счета
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Метод сравнивает объекты типа Account по значению поля requisite
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
        Account account = (Account) o;
        return Objects.equals(requisite, account.requisite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisite);
    }
}
