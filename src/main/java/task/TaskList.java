package task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    protected List<Task> taskListArr = new ArrayList<Task>();

    // Получим размер массива (нужен для выбора меню)
    public int getSize() {
        return taskListArr.size();
    }
    

    // Создадим новую задачу и добавим в список
    public void addTask(Task task){
        task.makeNewTask();
        taskListArr.add(task);
    }

    // Изменим задачу из списка
    public void editTask(int index) {
    	taskListArr.get(index).editTheTask();
    }
    // Удалим задачу из списка
    public void deleteTask(int index){
    	taskListArr.remove(index);
    }
    //Напечатаем список
    public void printList() {
        if (taskListArr.size() != 0) {
            for (int i = 0; i < taskListArr.size(); i++) {
                System.out.println(i + ". " + taskListArr.get(i).getTask());
            }
        }
    }
}