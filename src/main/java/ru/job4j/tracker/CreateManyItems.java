package ru.job4j.tracker;

public class CreateManyItems implements UserAction {
    private final Output out;

    public CreateManyItems(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Add new Items";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        out.println("=== Create a new Items ===");
        String name = input.askStr("Enter name: ");
        for (int i = 0; i < 100000; i++) {
            Item item = new Item(name);
            memTracker.add(item);
            out.println("Добавлена заявка: " + item);
        }
        return true;
    }
}
