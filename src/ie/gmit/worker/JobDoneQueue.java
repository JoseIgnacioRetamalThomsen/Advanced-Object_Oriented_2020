package ie.gmit.worker;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Out put queue implementatuion for {@code job}
 * 
 * {@inheritDoc}
 */
public class JobDoneQueue implements DoneQueueInterface<Job> {

	private Map<Long, Future<Job>> map = new ConcurrentHashMap<>();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isDone(long number) throws NoSuchJobException {

		Future<Job> temp = map.get(number);
		if (temp == null)
			throw new NoSuchJobException();
		return temp.isDone();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Job get(long number) {
		Job temp = null;
		try {
			temp = map.remove(number).get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void put(long number, Future<Job> t) {
		map.put(number, t);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int remains() {

		return map.keySet().size();
	}

}
