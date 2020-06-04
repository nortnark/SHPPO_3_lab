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
    private Scanner scanner;

    public void open() {
        (new Thread(this, "Menu")).start();
    }
    public synchronized void run() {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext"
        );
        manager = context.getBean("taskManager", TaskManager.class);
        taskList = context.getBean("taskList", TaskList.class);
        scanner = new Scanner(System.in);

        int choose = 1, index = 0;

        // запускаем поток сразу, так как теперь он простаивает, пока не активен ключ
        thread = new Thread(manager, "DupFilter");
        thread.start();

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
                    if (taskList.getSize() < 1) { // этот пункт не должен работать при пустом списке
                        System.out.println("Неверный ввод, повторите!");
                        continue;
                    }
                    System.out.println("Введите номер задачи, которую хотите изменить: ");
                    index = scanner.nextInt();
                    taskList.editTask(index);
                    break;
                case 3:
                    if (taskList.getSize() < 1) { // этот пункт не должен работать при пустом списке
                        System.out.println("Неверный ввод, повторите!");
                        continue;
                    }
                    System.out.println("Введите номер задачи, которую хотите удалить: ");
                    index = scanner.nextInt();
                    taskList.deleteTask(index);
                    break;
                case 4:
                    manager.checkingSwitch = !manager.checkingSwitch;
                    System.out.println("Режим проверки дубликатов "
                            + (manager.checkingSwitch ? "включен" : "выключен"));
                    break;
                default:
                    System.out.println("Неверный ввод, повторите!");
            }
            waitForFilter(); // ждём фильтр
        }
        System.out.println("Goodbye.");
        thread.interrupt();
        context.close();
    }

    private void waitForFilter() {
        if (manager.checkingSwitch) {
            manager.DupRemoved = false;
            synchronized (taskList) { // синхронизация по taskList
                taskList.notify(); // будим поток фильтра
                while (!manager.DupRemoved) {
                    try {
                        taskList.wait(); // ждём, пока фильтр отработает и разбудит поток интерфейса
                    } catch (InterruptedException e) { }
                }
            }
        }
    }
}
