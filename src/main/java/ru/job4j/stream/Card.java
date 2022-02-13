package ru.job4j.stream;


import ru.job4j.tracker.StubOutput;

import javax.sound.midi.SysexMessage;
import java.util.stream.Stream;

public class Card {
    private Suit suit;
    private Value value;

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    public enum Suit {
        Diamonds, Hearts, Spades, Clubs
    }

    public enum Value {
        V_6, V_7, V_8
    }

    @Override
    public String toString() {
        return "Card{"
                + "suit=" + suit
                + ", value=" + value
                + '}';
    }

    public static void main(String[] args) {
            Stream.of(Suit.values())
                    .flatMap(s -> Stream.of(Value.values())
                            .map(v -> new Card(s, v)))
                            .forEach(System.out::println);
        }
    }

