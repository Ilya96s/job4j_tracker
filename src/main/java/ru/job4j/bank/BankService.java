package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *Класс реализует систему управления пользователями банка и их счетами
 * @author ILYA KALTYGIN
 * @version 1.0
 */
public class BankService {
    /**
     * Хранение всех пользователей и информации о их счета осуществляется
     * в коллекции users типа HashMap
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод принмает на вход пользователя и при условии, что этого пользователя нет в коллекции
     * @param user пользователь которого необходимо добавить
     */
    public void addUser(User user) {
            users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод позволяет удалить пользователя из коллекции
     * @param user пользователь которого необходимо удалить
     */
    public void deleteUser(User user) {
        users.remove(user);
    }

    /**
     * Метод добавляет пользователю данные о паспорте и счете, которые переданы в параметрах, при условии что
     * этого счета у пользователя еще нет
     * @param passport данные паспорта пользователя
     * @param account счет пользователя
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null && !users.get(user).contains(account)) {
            users.get(user).add(account);
        }
    }

    /**
     * Метод позволяет найти пользователя по данным паспорта
     * @param passport данные паспорта
     * @return возвращает пользователя если он найден или null если пользователя с таким паспортом нет
     */
    public User findByPassport(String passport) {
        for (User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Метод позволяет найти счет пользователя по его реквизитам
     * @param passport данные паспорта
     * @param requisite реквизиты счета
     * @return
     */
    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            for (Account acc : users.get(user)) {
                if (acc.getRequisite().equals(requisite)) {
                    return acc;
                }
            }
        }
        return null;
    }

    /**
     * Метод позволяет перевести денежные средства с одного счета на другой
     * @param srcPassport номер паспорта пользователя со счета которого будет происходить перевод
     * @param srcRequisite счет пользователя с которого будет проихсодить перевод
     * @param destPassport номер паспорта пользователя на счет которого будет зачислены средства
     * @param destRequisite счет пользователя на который будет происходить перевод
     * @param amount сумма перевода
     * @return возвращает true если перевод совершен и false в обратном случае
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        Account destAccount = findByRequisite(destPassport, destRequisite);
        boolean rsl = false;
        if ((srcAccount != null) && (destAccount != null) && (srcAccount.getBalance() >= amount)) {
            srcAccount.setBalance(srcAccount.getBalance() - amount);
            destAccount.setBalance(destAccount.getBalance() + amount);
            rsl = true;
        }
        return rsl;
    }
}

