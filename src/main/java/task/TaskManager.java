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
    
    public void show() {
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
    	int task_index = 0;
    	
    	while(true)
    	{
	    	if (Thread.currentThread().isInterrupted()) break;
	    	if (task_index < 10)
	    		taskList.addTask(new Task("Task from another thread #" + task_index++), true);
	    	else if (!taskList.taskList.isEmpty()) {
	    		taskList.deleteTask(0);
	    	}
	    	else
	    		task_index = 0;
	    		
	    	
	    	try {
	    		Thread.sleep(2000);
	    	} catch (InterruptedException e) { 
	    		// System.out.println("Поток остановлен!");
	    		break;
	    	}
    	}
    }
}
