package menu;

public class NoTaskMenu extends MenuBuilder{

    public NoTaskMenu(Menu menu) {
        this.menu = menu;
    }
    @Override
    public Menu buildMenu() {
        menu.addExit();
        menu.addCreator();
        return menu;
    }
}
