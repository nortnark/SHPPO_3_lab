package menu;

public class TaskMenu extends MenuBuilder {

    public TaskMenu(Menu menu){
        this.menu = menu;
    }

    @Override
    public Menu buildMenu() {
        menu.addExit();
        menu.addCreator();
        menu.addEditor();
        menu.addCleaner();
        return menu;
    }
}

