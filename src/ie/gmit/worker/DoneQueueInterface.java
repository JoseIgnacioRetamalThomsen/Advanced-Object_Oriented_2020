package ie.gmit.worker;

import java.util.concurrent.Future;

/**
 * Out put queue
 * 
 * @author Jose I. Retamal
 *
 * @param <T> type of the queue
 */
public interface DoneQueueInterface<T> {
	
	/**
	 * check if a job in the queue is done
	 * @param number the job number
	 * @return true if the job is finished
	 * @throws NoSuchJobException
	 */
	boolean isDone(long number) throws NoSuchJobException;
	
	/**
	 * Returns the job
	 * 
	 * @param numnber the job number
	 * @return the job
	 */
	T get(long numnber);
	
	/**
	 *  Add a job to the queue
	 * @param number the job number
	 * @param t the job
	 */
	void put( long number,Future<T> t);
	
	/**
	 * Return the amount of jobs remaining in the queue
	 * 
	 * @return amount of jobs remaining in the queue
	 */
	int remains();

}
