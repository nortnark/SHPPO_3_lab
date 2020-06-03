package task;

import application.App;
import menu.MenuBuilder;

public class TaskManager extends TaskList implements Runnable  {
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
        int LastInd;

        while(true)
        {
        	LastInd = taskList.getLastChanged();
        	//System.out.println(LastInd);
            if (Thread.currentThread().isInterrupted()) break;
            String s1, s2;
            for (int i = 0; i < taskList.getSize(); i++) {
            	s1 = taskList.taskListArr.get(i).getTask();
            	s2 = taskList.taskListArr.get(LastInd).getTask();
            	if (i != LastInd)
                if(s1.equals(s2)) {
                    taskList.deleteTask(LastInd);
                    break;
                }
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Поток остановлен!");
                break;
            }
        }
    }
}
