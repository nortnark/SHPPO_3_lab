package task;

import menu.MenuBuilder;

public class TaskManager implements Runnable {
    static MenuBuilder taskMenu;
    static MenuBuilder noTaskMenu;
    private TaskList taskList;
    public boolean DupRemoved = false;

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

    public void run() {
        int removed = 0;
        String s1, s2;

        while (true) {
            if (Thread.currentThread().isInterrupted()) break;

            for (int i = 0; i < taskList.getSize(); i++) {
                s1 = taskList.get(i).getTask();
                for (int j = i + 1; j < taskList.getSize(); j++) {
                    s2 = taskList.get(j).getTask();
                    if (s1.equals(s2)) {
                        taskList.deleteTask(j);
                        ++removed;
                        --j;
                    }
                }
            }
            if (removed > 0) {
                System.out.println("Удалено дубликатов: " + removed);
            }
            removed = 0;
            DupRemoved = true;

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
