package task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    volatile List<Task> taskList = new ArrayList<>();

    // Получим размер массива (нужен для выбора меню)
    public int getSize() {
        return taskList.size();
    }

    // Создадим новую задачу и добавим в список
    public void addTask(Task task){
        task.makeNewTask();
        taskList.add(task);
    }
    public Task get(int index) {
        return taskList.get(index);
    }

    // Изменим задачу из списка
    public void editTask(int index) {
        taskList.get(index).editTheTask();
    }
    // Удалим задачу из списка
    public void deleteTask(int index){
        taskList.remove(index);
    }
    //Напечатаем список
    public void printList() {
        if (taskList.size() != 0) {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(i + ". " + taskList.get(i).getTask());
            }
        }
    }
}