package menu;

import menu.items.CreateItem;
import menu.items.DeleteItem;
import menu.items.EditItem;
import menu.items.ExitItem;

public class Menu {

    public void addExit() {
        System.out.println(new ExitItem().addItem());
    }

    public void addCreator() {
        System.out.println(new CreateItem().addItem());
    }

    public void addEditor() {
        System.out.println(new EditItem().addItem());
    }

    public void addCleaner() {
        System.out.println(new DeleteItem().addItem());
    }

}
