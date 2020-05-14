package ie.gmit.worker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import ie.gmit.ngrams.DatabaseInterface;
import ie.gmit.ngrams.KmersSize;

/**
 * Manage all worker and queue's, take jobs from in queue and assign the work to a worker that
 *  works on a independent thread.
 *  
 * @author Jose I. Retamal
 *
 */
public class WorkersManager implements Runnable {

	private static WorkersManager instance = new WorkersManager();// singleton
	private InQueueInterface<Job> inQueue; // input queue
	private DoneQueueInterface<Job> outQueue; // output queue
	private Map<KmersSize,DatabaseInterface> databases = new HashMap<>(); //databases, specific for the type of kmers.
	
	private ExecutorService executor = Executors.newFixedThreadPool(100);//for run threads
	private transient boolean isRunnig = true;// for stot the thread.

	// private consturctor for singletons
	private WorkersManager() {
	}

	/**
	 * Initialize the object with the in and out queue, will start to run the unique instance on a thread.
	 * 
	 * @param inQueue input queue
	 * @param outQueue output queue
	 * @return unique instance for method call chain
	 */
	public static WorkersManager init(InQueueInterface<Job> inQueue, DoneQueueInterface<Job> outQueue
			) {
		instance.inQueue = inQueue;
		instance.outQueue = outQueue;
		
		new Thread(instance).start();
		return instance;
	}

/*	public WorkersManager(InQueueInterface<Job> inQueue, DoneQueueInterface<Job> outQueue, DatabaseInterface database) {
		super();
		this.inQueue = inQueue;
		this.outQueue = outQueue;
		
	}*/

	/**
	 * Add a database, databases type are defined by {@code KmersSize} Enum, jobs submitted 
	 * must indicate the type of database.
	 * 
	 * @param kmersSize type od database
	 * @param db the database
	 * @return unique instance of this object for chain methods call
	 */
	public static WorkersManager addDatabase(KmersSize kmersSize,DatabaseInterface db) {
		instance.databases.put(kmersSize,db);
		return instance;
	}

	/**
	 * take jobs from queue, assign a worker for the job, then submit the new worker on the executor.
	 */
	@Override
	public void run() {
		while (isRunnig) {

			// take job
			Job job = instance.inQueue.take();

			if (job instanceof JobPoison)
				break;

			// create a worker
			Worker worker = new Worker(instance.databases.get(job.getKmersSize()), job);
			//submit and add to ouput queue
			instance.outQueue.put( job.getJobNumber(),instance.executor.submit(worker));

		}

	}

	/**
	 * Add a job to input queue
	 * 
	 * @param jobNumber the jos mumber
	 * @param line
	 * @param kmersSize
	 */
	public static void addJob(long jobNumber, String line,int kmersSize) {
		instance.inQueue.put(new Job(jobNumber, line,kmersSize));
	}

	public static void stop() {
		instance.isRunnig = false;
	}

	protected void finalize() throws Throwable {
		WorkersManager.stop();
	}
}
