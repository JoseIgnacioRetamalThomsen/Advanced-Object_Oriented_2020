package ie.gmit.worker;

/**
 * In queue 
 * 
 * @author Jose I. Retamal
 *
 * @param <T> type of queue elements
 */
public interface InQueueInterface<T> {
	
	/**
	 * Add a job to the queue
	 * 
	 * @param t the job to add to queue
	 * @return true if the job was added
	 */
	boolean put(T t);
	
	/**
	 * Take a jobs from queue
	 * 
	 * @return the job
	 */
	T take();
	
	/**
	 * Amount of jobs remaining
	 * 
	 * @return the amount of jobs reaming
	 */
	int jobRemain();
	
}
