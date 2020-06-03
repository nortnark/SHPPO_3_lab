package application;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import task.Task;
import task.TaskList;
import task.TaskManager;
//import threads.Writer;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext"
        );
        TaskManager taskManager = context.getBean("taskManager", TaskManager.class);
        TaskList taskList = context.getBean("taskList", TaskList.class);

        Scanner scanner = new Scanner(System.in);

        int choose = 1;
        int index;
        TaskManager manager = new TaskManager(taskList);
        Thread tr = new Thread(manager);
        tr.start();
        
        while (choose != 0) {

            
            
        	manager.show();
        	

            choose = scanner.nextInt();

            switch (choose) {
                case 0:
                    System.out.println("Goodbye.");
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
            }
        }
        tr.interrupt();
        context.close();
    }
}