package ru.job4j.tracker;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class ValidateInputTest {

    @Test
    public void whenValidInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"one", "1"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(1));
    }

    @Test
    public void whenMultiValidInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"1", "2", "3"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected1 = input.askInt("Enter menu:");
        assertThat(selected1, is(1));
        int selected2 = input.askInt("Enter menu:");
        assertThat(selected2, is(2));
        int selected3 = input.askInt("Enter menu:");
        assertThat(selected3, is(3));
    }

    @Test
    public void whenTrueInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"1"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(1));
    }

    @Test
    public void whenFalseInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"-3"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(-3));
    }
}