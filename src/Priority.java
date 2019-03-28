/*
 * @author David Velez and Harry Staley
 */

import java.util.Comparator;
import java.util.List;

public class Priority implements Algorithm {

    private final int HEAD = 0;
    private List<Task> tasks;

    public Priority (List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void schedule() throws InterruptedException {

        // Slice
        int slice = 0;

        // Current task
        Task current = null;

        // Create CPU runner
        CPU cpurunner = new CPU();

        // Sort by task priority
        tasks.sort(Comparator.comparing(t -> t.getPriority()));

        // Priority logic
        for (int i = 0; i < tasks.size(); ) {
            current = pickNextTask();
            cpurunner.run(current, slice += current.getBurst());
            Thread.sleep(current.getBurst());
            System.out.println("Task " + current.getName() + " finished.\n");
            tasks.remove(HEAD);
        }
    }

    @Override
    public Task pickNextTask() {
        return tasks.get(HEAD);
    }
}
