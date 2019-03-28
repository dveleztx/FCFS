/*
 * @author David Velez and Harry Staley
 */

import java.util.*;

public class FCFS implements Algorithm {

    private List<Task> tasks;
    private Queue<Task> queuedTasks;

    public FCFS(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void schedule() throws InterruptedException {

        // Slice
        int slice = 0;

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

        // Print Algo being used
        System.out.println("\nFCFS Scheduling\n");

        // Pick Next Task and run them
        for (int i = 0; i < queuedTasks.size(); ) {
            current = pickNextTask();
            cpurunner.run(current, slice += current.getBurst());
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