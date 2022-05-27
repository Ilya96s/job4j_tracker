package ru.job4j.tracker;

public class ReplaceAction implements UserAction {
    private final Output out;

    public ReplaceAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Edit name";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Edit name ===");
        int id = input.askInt("Enter id: ");
        String name = input.askStr("Enter name: ");
        Item item = new Item(name);
        if (tracker.replace(id, item)) {
            out.println("Заявка изменена успешно");
        } else {
            out.println("Ошибка замены заявки");
        }
        return true;
    }
}
