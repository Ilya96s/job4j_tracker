package ru.job4j.bank;

import java.util.*;

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
        Optional<User> user = findByPassport(passport);
        if (user.isPresent() && !users.get(user.get()).contains(account)) {
            users.get(user.get()).add(account);
        }
    }

    /**
     * Метод позволяет найти пользователя по данным паспорта
     * @param passport данные паспорта
     * @return возвращает пользователя если он найден или null если пользователя с таким паспортом нет
     */
    public Optional<User> findByPassport(String passport) {
        return users.keySet()
                .stream()
                .filter(s -> s.getPassport().equals(passport))
                .findFirst();
    }

    /**
     * Метод позволяет найти счет пользователя по его реквизитам
     * @param passport данные паспорта
     * @param requisite реквизиты счета
     * @return
     */
    public Optional<Account> findByRequisite(String passport, String requisite) {
        Optional<User> user = findByPassport(passport);
        if (user.isPresent()) {
            return users.get(user.get())
                    .stream()
                    .filter(s -> s.getRequisite().equals(requisite))
                    .findFirst();
        }
        return Optional.empty();
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
        Optional<Account> srcAccount = findByRequisite(srcPassport, srcRequisite);
        Optional<Account> destAccount = findByRequisite(destPassport, destRequisite);
        boolean rsl = false;
        if (srcAccount.isPresent() && destAccount.isPresent() && srcAccount.get().getBalance() >= amount) {
            srcAccount.get().setBalance(srcAccount.get().getBalance() - amount);
            destAccount.get().setBalance(destAccount.get().getBalance() + amount);
            rsl = true;
        }
        return rsl;
    }
}

