package oop;

public class DummyDic {

    public String enToRus(){
        String word = "Hello";
        return word;
    }

    public static void main(String[] args) {
        DummyDic word = new DummyDic();
        String eng = word.enToRus();
        System.out.println("Неизвестное слово: " + eng);
    }
}
