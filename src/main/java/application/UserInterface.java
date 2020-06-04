package application;

import task.Task;
import task.TaskList;
import task.TaskManager;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Scanner;

public class UserInterface implements Runnable {
    private TaskList taskList;
    private TaskManager manager;
    private Thread thread;
    private boolean checkingSwitch = false;

    public void open() {
        (new Thread(this)).start();
    }
    public synchronized void run() {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext"
        );
        manager = context.getBean("taskManager", TaskManager.class);
        taskList = context.getBean("taskList", TaskList.class);

        Scanner scanner = new Scanner(System.in);

        int choose = 1, index;

        while (choose != 0) {

            manager.showInterface();

            choose = scanner.nextInt();

            switch (choose) {
                case 0:
                    break;
                case 1:
                    taskList.addTask(new Task());
                    break;
                case 2:
                    System.out.println("Введите номер задачи, которую хотите изменить: ");
                    index = scanner.nextInt();
                    taskList.editTask(index);
                    break;
                case 3:
                    System.out.println("Введите номер задачи, которую хотите удалить: ");
                    index = scanner.nextInt();
                    taskList.deleteTask(index);
                    break;
                case 4:
                    if (checkingSwitch) {
                        thread.interrupt();
                    } else {
                        thread = new Thread(manager);
                        thread.start();
                    }
                    checkingSwitch = !checkingSwitch;
                    System.out.println("Режим проверки дубликатов "
                            + (checkingSwitch ? "включен" : "выключен"));
            }
            waitForFilter();
        }
        System.out.println("Goodbye.");
        thread.interrupt();
        context.close();
    }

    private void waitForFilter() {
        if (checkingSwitch) {
            manager.DupRemoved = false;
            while (!manager.DupRemoved) {
                try {
                    wait(1);
                } catch (InterruptedException e) {  }
            }
        }
    }
}
