/**
 * Interface representing a generic scheduling algorithm.
 *
 * @author Greg Gagne - March 2016
 */

public interface Algorithm
{
    /**
     * Invokes the scheduler
     */
    public abstract void schedule() throws InterruptedException;

    /**
     * Selects the next task using the appropriate scheduling algorithm
     */
    public abstract Task pickNextTask();
}
