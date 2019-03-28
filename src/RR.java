/*
 * @author David Velez and Harry Staley
 */

import java.util.List;

public class RR implements Algorithm {

    private final int QUANTUM = 5;
    private int index = 0;
    private List<Task> tasks;

    public RR (List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void schedule() throws InterruptedException {

        // Current Task
        Task current = null;

        // Create CPU runner
        CPU cpurunner = new CPU();

        // Print Algo being used
        System.out.println("\nRound Robin Scheduling\n");

        for (index = 0; index < tasks.size(); index++) {

            current = pickNextTask();

            // If burst is zero, go to next task
            if (current.getBurst() <= 0) {
                continue;
            }

            // Run task
            cpurunner.run(current, QUANTUM);

            // Go through tasks and decrement burst by the fixed quantum
            if (current.setBurst(current.getBurst() - QUANTUM) <= 0) {
                System.out.println("Task " + current.getName() + " finished.\n");
            }

            // Go back index
            if (index == (tasks.size() - 1)) {
                index = -1;
            }
        }
    }

    @Override
    public Task pickNextTask() {
        return tasks.get(index);
    }
}
