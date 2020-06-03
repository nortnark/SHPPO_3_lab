package task;

import java.util.Scanner;

public class Task {

    // Объявление используемых переменных
    private String task;
    Scanner scanner = new Scanner(System.in);

    // Геттер для вывода значения приватной переменной
    public String getTask() {
        return task;
    }

    //Метод класса task.Task для создания новой задачи
    public String makeNewTask() {
        System.out.println("Новая задача: ");
        task = scanner.nextLine();
        return task;
    }

    //Метод класса task.Task для изменения существующей задачи
    public  String editTheTask() {
        String check;
        System.out.println("Изменить задачу '" + task + "': ");
        check = scanner.nextLine();
        if (!check.equals("")) {
            task = check;
        }
        return task;
    }
}
