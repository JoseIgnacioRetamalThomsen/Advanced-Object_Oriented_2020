package ie.gmit.worker;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ie.gmit.ngrams.LongKmer4Generator;
import ie.gmit.ngrams.builders.KmerGeneratorFactory;
import ie.gmit.ngrams.Countator;
import ie.gmit.ngrams.CreateMapOneTask;
import ie.gmit.ngrams.DatabaseInterface;
import ie.gmit.ngrams.Entry;
import ie.gmit.ngrams.KmerGenerator;
import ie.gmit.ngrams.KmersSize;
import ie.gmit.ngrams.LanguageEntry;

/**
 * Use the line {@code String} from a job for compare build a {@code Countator} for them compare 
 *  it again a database of languages.
 * 
 * @author Jose I. Retamal
 *
 */
public class Worker implements Callable<Job> {
	private DatabaseInterface database; // the database conatining entrys for all languages
	private Job job; // the job 

	/**
	 * Create the worker with the database and the job
	 * @param database the database conatining all languages
	 * @param job the job
	 */
	public Worker(DatabaseInterface database, Job job) {
		super();
		this.database = database;
		this.job = job;
	}

	/**
	 * Execute the query of the job against the database, return the job with the results.
	 */
	@Override
	public Job call() throws Exception {
		//get kmers generator from factory using job parameters
		KmerGenerator<Long> temp = (KmerGenerator<Long>) KmerGeneratorFactory.getInstance().getNew(job.getKmersSize(),
				Long.class);
		// create the query  countator
		CreateMapOneTask cmot = new CreateMapOneTask(job.getInput(), temp, 300);
		ExecutorService executor = Executors.newFixedThreadPool(1);
		Countator<Long, Entry<Long>> map = executor.submit(cmot).get();
		executor.shutdown();
		// calculate resuts and set them in the job
		job.setLanguage(database.getLanguage(map));
		job.setResults(database.getResults(map));
		return job;
	}

}
