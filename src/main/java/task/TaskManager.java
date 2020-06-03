package task;

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

    public synchronized void run() {
//        while (true) {
//            // Проверяем, был ли получен сигнал на прерывание потока, если да, то выходим
//            // из цикла и завершаем работу потока
//            if (Thread.currentThread().isInterrupted()) {
//                break;
//            }

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

//            try {
//                wait();
//            } catch (InterruptedException e) {
//                break;
//            }
//        }

    }
}
