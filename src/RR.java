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

        for (index = 0; index < tasks.size(); index++) {

            current = pickNextTask();

            if (current.getBurst() <= 0) {
                continue;
            }

            cpurunner.run(current, QUANTUM);

            if (current.setBurst(current.getBurst() - QUANTUM) <= 0) {
                System.out.println("Task " + current.getName() + " finished.\n");
            }

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
