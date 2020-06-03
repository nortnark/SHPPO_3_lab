//package threads;
//
//import task.TaskList;
//
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//
//public class Writer implements Runnable {
//    Lock lock = new ReentrantLock();
//    private TaskList taskList;
//
//    public Writer(TaskList taskList) {
//        this.taskList = taskList;
//    }
//
//    @Override
//    public void run() {
//        lock.lock();
//        System.out.println("\n====My tasks====");
//        taskList.printList();
//        System.out.println();
//        lock.unlock();
//    }
//}
