package oop;

public class Error {

    private boolean active;

    private int status;

    private String message;

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public Error() {
    }

    public void info() {
        System.out.println("Наличие ошибки : " + active);
        System.out.println("Статус : " + status);
        System.out.println("Сообщение : " + message);
    }

    public static void main(String[] args) {
        Error error1 = new Error(true, 400, "Неверный запрос");
        Error error2 = new Error(true, 1, "Доступ к ресурсу запрещен");
        Error error3 = new Error(true, 404, "Ресурс не найден");
        Error error4 = new Error();
        error1.info();
        error2.info();
        error3.info();
        error4.info();
    }
}
