import java.util.*;

public class FCFS implements Algorithm {

    List<Task> tasks;
    Queue<Task> queuedTasks;

    public FCFS(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void schedule() throws InterruptedException {

        // Current task
        Task current = null;

        // Create CPU Runner
        CPU cpurunner = new CPU();

        // Use queue to setup priority
        queuedTasks = new LinkedList<>();

        // Queue up tasks in order
        for (Task task : tasks) {
            queuedTasks.add(task);
        }

        // Pick Next Task and run them
        for (int i = 0; i < queuedTasks.size(); ) {
            current = pickNextTask();
            cpurunner.run(current, current.getTid());
            Thread.sleep(current.getBurst());
            System.out.println("Task " + current.getName() + " finished.\n");
            queuedTasks.remove();
        }
    }

    @Override
    public Task pickNextTask() {
        return queuedTasks.peek();
    }
}