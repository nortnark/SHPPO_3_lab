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

        while(true)
        {
            if (Thread.currentThread().isInterrupted()) break;
            String s1, s2;
            
            for (int i = 0; i < taskList.getSize(); i++) {
            	s1 = taskList.taskListArr.get(i).getTask();
            	for (int j = i + 1; j < taskList.getSize(); j++) {
            		
                	s2 = taskList.taskListArr.get(j).getTask();
                    if(s1.equals(s2)) {
                        taskList.deleteTask(j);
                    }
            	}
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println("Поток остановлен!");
                break;
            }
        }
    }
}
