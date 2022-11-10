package ru.job4j.tracker;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StartUITest {
    @Test
    public void whenReplaceItemTestOutputIsSuccessfully() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item one = tracker.add(new Item("test1"));
        String replaceName = "New Test Name";
        Input in = new StubInput(
                new String[]{"0", String.valueOf(one.getId()), replaceName, "1"}
        );
        List<UserAction> actions = List.of(
                new ReplaceAction(out),
                new ExitAction(out)
        );
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Menu:" + ln
                        + "0. Edit name" + ln
                        + "1. Exit" + ln
                        + "=== Edit name ===" + ln
                        + "Заявка изменена успешно" + ln
                        + "Menu:" + ln
                        + "0. Edit name" + ln
                        + "1. Exit" + ln
        );
    }

    @Test
    public void whenFindAllItemTestOutputIsSuccessfully() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item one = tracker.add(new Item("test1"));
        Input in = new StubInput(
                new String[]{"0", "1"}
        );
        List<UserAction> actions = List.of(
                new ShowAction(out),
                new ExitAction(out)
        );
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Menu:" + ln
                        + "0. Show all items" + ln
                        + "1. Exit" + ln
                        + "=== Show all items ===" + ln
                        + one + ln
                        + "Menu:" + ln
                        + "0. Show all items" + ln
                        + "1. Exit" + ln

        );
    }

    @Test
    public void whenFindByNameItemTestOutputIsSuccessfully() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item one = tracker.add(new Item("test1"));
        Input in = new StubInput(
                new String[]{"0", "test1", "1"}
        );
        List<UserAction> actions = List.of(
                new FindByNameAction(out),
                new ExitAction(out)
        );
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Menu:" + ln
                        + "0. Find item by name" + ln
                        + "1. Exit" + ln
                        + "=== Find items by name ===" + ln
                        + one + ln
                        + "Menu:" + ln
                        + "0. Find item by name" + ln
                        + "1. Exit" + ln
        );
    }

    @Test
    public void whenFindByIdItemTestOutputIsSuccessfully() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item one = tracker.add(new Item("test1"));
        Input in = new StubInput(
                new String[]{"0", String.valueOf(one.getId()), "1"}
        );
        List<UserAction> actions = List.of(
                new FindByIdAction(out),
                new ExitAction(out)
        );
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Menu:" + ln
                        + "0. Find items by id" + ln
                        + "1. Exit" + ln
                        + "=== Find item by id ===" + ln
                        + one + ln
                        + "Menu:" + ln
                        + "0. Find items by id" + ln
                        + "1. Exit" + ln
        );
    }

    @Test
    public void whenInvalidExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"7", "0"}
        );
        MemTracker tracker = new MemTracker();
        List<UserAction> actions = List.of(new ExitAction(out));
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                        "Menu:" + ln
                                + "0. Exit" + ln
                                + "Wrong input, you can select: 0 .. 0" + ln
                                + "Menu:" + ln
                                + "0. Exit" + ln
                );
    }

    @Test
    public void whenFindItemByNameSuccessfully() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item1 = new Item("Item 1");
        Item item2 = new Item("Item 2");
        tracker.add(item1);
        tracker.add(item2);
        FindByNameAction findByNameAction = new FindByNameAction(out);
        Input input = mock(Input.class);
        when(input.askStr(any())).thenReturn("Item 1");
        findByNameAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo("=== Find items by name ===" + ln + item1 + ln);
        assertThat(tracker.findAll().get(0)).isEqualTo(item1);
    }

    @Test
    public void whenFindItemByNameFail() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item1 = new Item("Item 1");
        Item item2 = new Item("Item 2");
        tracker.add(item1);
        tracker.add(item2);
        FindByNameAction findByNameAction = new FindByNameAction(out);
        Input input = mock(Input.class);
        findByNameAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo("=== Find items by name ===" + ln + "Заявки с именем: " + null + " не найдены" + ln);
        assertThat(tracker.findAll().get(0)).isEqualTo(item1);
    }

    @Test
    public void whenFindItemByIdSuccessfully() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item1 = new Item("Item 1");
        Item item2 = new Item("Item 2");
        tracker.add(item1);
        tracker.add(item2);
        FindByIdAction findByIdAction = new FindByIdAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any())).thenReturn(1);
        findByIdAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo("=== Find item by id ===" + ln + item1 + ln);
        assertThat(tracker.findAll().get(0)).isEqualTo(item1);
        assertThat(tracker.findAll()).isEqualTo(List.of(item1, item2));
    }

    @Test
    public void whenFindItemByIdFail() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item1 = new Item("Item 1");
        Item item2 = new Item("Item 2");
        tracker.add(item1);
        tracker.add(item2);
        FindByIdAction findByIdAction = new FindByIdAction(out);
        Input input = mock(Input.class);
        findByIdAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "=== Find item by id ==="
                        + ln + "Заявка с введенным id: "
                        + input.askInt(any())
                        + " не найдена" + ln);
        assertThat(tracker.findAll().get(0)).isEqualTo(item1);
        assertThat(tracker.findAll()).isEqualTo(List.of(item1, item2));
    }

    @Test
    public void whenDeleteItemSuccessfully() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item1 = new Item("item 1");
        Item item2 = new Item("item 2");
        tracker.add(item1);
        tracker.add(item2);
        DeleteAction del = new DeleteAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        del.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo("=== Delete item ===" + ln + "Заявка удалена успешно" + ln);
        assertThat(tracker.findAll()).isEqualTo(List.of(item2));
    }

    @Test
    public void whenDeleteItemFail() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item1 = new Item("Item 1");
        Item item2 = new Item("Item 2");
        tracker.add(item1);
        tracker.add(item2);
        DeleteAction del = new DeleteAction(out);
        Input input = mock(Input.class);
        del.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo("=== Delete item ===" + ln + "Ошибка удаления заявки" + ln);
        assertThat(tracker.findAll()).isEqualTo(List.of(item1, item2));
    }

    @Test
    public void whenExecuteSuccessfully() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        ReplaceAction rep = new ReplaceAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askStr(any(String.class))).thenReturn(replacedName);
        rep.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo("=== Edit name ===" + ln + "Заявка изменена успешно" + ln);
        assertThat(tracker.findAll().get(0).getName()).isEqualTo(replacedName);
    }

    @Test
    public void whenExecuteFail() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Replaced item"));
        ReplaceAction rep = new ReplaceAction(out);
        Input input = mock(Input.class);
        rep.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo("=== Edit name ===" + ln + "Ошибка замены заявки" + ln);
        assertThat(tracker.findAll().get(0).getName()).isEqualTo("Replaced item");
    }
}