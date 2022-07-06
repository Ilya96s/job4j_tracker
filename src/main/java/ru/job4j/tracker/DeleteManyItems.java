package ru.job4j.tracker;

import java.util.List;

public class DeleteManyItems implements UserAction {
    private final Output out;

    public DeleteManyItems(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete all Items";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        out.println("=== Delete all items ===");
        String name = input.askStr("Enter name: ");
        List<Item> items = memTracker.findByName(name);
        if (items.size() > 0) {
            for (Item item : items) {
                memTracker.delete(item.getId());
            }
            out.println("Заявки удалены успешно.");
        } else {
            out.println("Ошибка удаления заявок, заявки не найдены.");
        }
        return true;
    }
}
