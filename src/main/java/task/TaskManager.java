package task;

import menu.MenuBuilder;

public class TaskManager implements Runnable {
    static MenuBuilder taskMenu;
    static MenuBuilder noTaskMenu;
    private TaskList taskList;
    public boolean DupRemoved = false;
    public boolean checkingSwitch = false;

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
        // выводим задачи, только если списко не пуст
        if (taskList.getSize() > 0) {
            System.out.println("\n====My tasks====");
            taskList.printList();
            System.out.println();
        }

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

            synchronized(taskList) { // синхронизация так же по taskList
                while(!checkingSwitch) { // ждём, пока фильтра не активен
                    try {
                        taskList.wait();
                    } catch (InterruptedException e) {
                        return; // выходим из метода, если получили прерывание потока
                    }
                }

                for (int i = 0; i < taskList.getSize() - 1; i++) {
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
                taskList.notify();  // будим поток интерфейса

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }
}
