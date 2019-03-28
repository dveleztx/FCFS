/*
 * @author David Velez and Harry Staley
 */

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PriorityRR implements Algorithm {

    private final int QUANTUM = 10;
    private int index = 0;
    private int samePriority = 0;
    private List<Task> tasks;

    public PriorityRR(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void schedule() throws InterruptedException {

        // Slice
        int slice = 0;

        // Current Task
        Task current = null;

        // Create CPU runner
        CPU cpurunner = new CPU();

        // Sort by task priority - NOTE: I think the commented one is actually right, not the next line
        tasks.sort(Comparator.comparing(t -> t.getPriority(), Collections.reverseOrder()));
        //tasks.sort(Comparator.comparing(t -> t.getPriority()));

        // Print Algo being used
        System.out.println("\nPriority with RR Scheduling\n");

        // Scheduling
        for (index = 0; index < tasks.size(); index++) {
            current = pickNextTask();

            // If the burst is zero, skip to next task
            if (current.getBurst() <= 0) {
                continue;
            }

            // If not the last task, check to see if there are matching priorities and run tasks by quantum
            if (index < tasks.size() - 1) {
                if (current.getPriority() == tasks.get(index + 1).getPriority()) {
                    cpurunner.run(current, QUANTUM);
                    current.setBurst(current.getBurst() - QUANTUM);
                    if (current.getBurst() <= 0) {
                        System.out.println("Task " + current.getName() + " finished.\n");
                    }
                    samePriority += 1;
                    continue;
                } else if (samePriority > 0) {
                    cpurunner.run(current, QUANTUM);
                    current.setBurst(current.getBurst() - QUANTUM);
                    if (current.getBurst() <= 0) {
                        System.out.println("Task " + current.getName() + " finished.\n");
                    }
                    index -= (samePriority + 1);
                    samePriority = 0;
                    continue;
                }
            }

            // Run tasks
            cpurunner.run(current, slice += current.getBurst());
            System.out.println("Task " + current.getName() + " finished.\n");
        }
    }

    @Override
    public Task pickNextTask() {
        return tasks.get(index);
    }
}
