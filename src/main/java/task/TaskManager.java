package task;

import application.App;
import menu.MenuBuilder;

public class TaskManager implements Runnable {
    static MenuBuilder taskMenu;
    static MenuBuilder noTaskMenu;
    private TaskList taskList;

    public void setTaskMenu(MenuBuilder builder) {
        this.taskMenu = builder;
    }

    public void setNoTaskMenu(MenuBuilder builder) {
        this.noTaskMenu = builder;
    }

    public TaskManager(TaskList taskList) {
        this.taskList = taskList;
    }

    public void showInterface() {

        System.out.println("\n====My tasks====");
        taskList.printList();
        System.out.println();
        System.out.println("=====Menu====");

        if (taskList.getSize() == 0) {
            noTaskMenu.buildMenu();
        } else {
            taskMenu.buildMenu();
        }

        System.out.println("Введите номер пункта меню: ");
    }

    public synchronized void run() {
        int taskIndex = App.index;

        while(true)
        {
            if (Thread.currentThread().isInterrupted()) break;

            for (int i = 0; i < taskIndex; i++) {
                if (i != taskIndex) {
                    if(taskList.get(i).getTask().equals(taskList.get(taskIndex).getTask())) {
                        taskList.deleteTask(taskIndex);
                    }
                }
            }

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                System.out.println("Поток остановлен!");
                break;
            }
        }
    }
}
