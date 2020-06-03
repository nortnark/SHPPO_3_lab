package task;

import menu.MenuBuilder;

public class TaskManager {
    static MenuBuilder taskMenu;
    static MenuBuilder noTaskMenu;

    public void setTaskMenu(MenuBuilder builder) {
        this.taskMenu = builder;
    }

    public void setNoTaskMenu(MenuBuilder builder) {
        this.noTaskMenu = builder;
    }

    public void showInterface(TaskList taskList) {
        System.out.println("\n====My tasks====");
        taskList.printList();
        System.out.println();
        System.out.println("=====menu.Menu====");
        if (taskList.getSize() == 0) {
            noTaskMenu.buildMenu();
        }
        if (taskList.getSize() != 0){
            taskMenu.buildMenu();
        }
        System.out.println("Введите номер пункта меню: ");
    }
}
