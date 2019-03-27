import java.util.Comparator;
import java.util.List;

public class SJF implements Algorithm {

    final int HEAD = 0;
    List<Task> tasks;

    public SJF (List<Task> tasks) {
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

        // Sort by task burst
        tasks.sort(Comparator.comparing(t -> t.getBurst()));

        // Run tasks
        for (int i = 0; i < tasks.size(); ) {
            current = pickNextTask();
            cpurunner.run(current, slice += current.getBurst());
            Thread.sleep(current.getBurst());
            System.out.println("Task " + current.getName() + " finished.\n");
            tasks.remove(HEAD);
        }

        System.out.println("SLICE: " + slice);
    }

    @Override
    public Task pickNextTask() {
        return tasks.get(HEAD);
    }
}
