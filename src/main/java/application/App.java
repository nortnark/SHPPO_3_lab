package application;
import task.Task;
import task.TaskList;
import task.TaskManager;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Scanner;

public class App {

    public static int index;
    public static void main(String[] args) throws InterruptedException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext"
        );
        TaskManager manager = context.getBean("taskManager", TaskManager.class);
        TaskList taskList = context.getBean("taskList", TaskList.class);

        Scanner scanner = new Scanner(System.in);

        int choose = 1;
        boolean checkingSwitch = false;

        Thread thread = null;

        while (choose != 0) {

            Thread.sleep(200);

            manager.showInterface();

            choose = scanner.nextInt();

            switch (choose) {
                case 0:
                    System.out.println("Goodbye.");
                    break;
                case 1:
                    taskList.addTask(new Task());
                    index = taskList.getSize() - 1;
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
                        //System.out.println("Поток прерван");
                    } else {
                        thread = new Thread(manager);
                        thread.start();
                        //System.out.println("Поток запущен");
                    }
                    checkingSwitch = !checkingSwitch;
                    System.out.println("Режим проверки дубликатов "
                            + (checkingSwitch ? "включен" : "выключен"));
            }
        }
        thread.interrupt();
        context.close();
    }

}