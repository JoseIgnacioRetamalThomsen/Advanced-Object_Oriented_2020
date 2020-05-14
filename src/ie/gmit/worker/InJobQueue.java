package ie.gmit.worker;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * In queue implemantation for {@code Job}
 * 
 * {@inheritDoc}
 */
public class InJobQueue implements InQueueInterface<Job> {

	private BlockingQueue<Job> queue; 

	/**
	 * {@inheritDoc}
	 */
	public InJobQueue() {
		queue = new LinkedBlockingQueue<>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public InJobQueue(int maxSize) {
		queue = new LinkedBlockingQueue<>(maxSize);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean put(Job t) {
		try {
			queue.put(t);
		} catch (InterruptedException e) {
			return false;
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Job take() {
		Job job = null;
		try {
			 job = queue.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return job;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int jobRemain() {
		
		return queue.size();
	}

	

	

}
