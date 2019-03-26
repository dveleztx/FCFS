import java.util.List;

public class FCFS implements Algorithm {

    List<Task> tasks;

    public FCFS(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void schedule() {

        CPU cpurunner = new CPU();

        for (Task task : tasks) {
            cpurunner.run(task, task.getTid());
        }
    }

    @Override
    public Task pickNextTask() {
        return null;
    }
}