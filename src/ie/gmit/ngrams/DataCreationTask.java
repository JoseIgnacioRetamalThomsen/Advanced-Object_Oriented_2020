package ie.gmit.ngrams;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

import ie.gmit.ngrams.builders.KmerGeneratorFactory;

/**
 * Create database (subject for queries) using multiple threads. 
 * 
 * @author Jose I. Retamal
 *
 */
public class DataCreationTask implements Callable<Map<Language, Countator<Long, Entry<Long>>>> {

	private static final int EXECUTORS = 100;
	private static final int FILE_READERS_THREADS = 10;
	private static final int REDUCERS_NUMBER = 5;

	private final BlockingQueue<CharSequence> readFileQueue = new LinkedBlockingQueue<>(1000);
	private final Map<Language, Countator<Long, Entry<Long>>> countingQueue = new ConcurrentHashMap<>();
	private final List<Mapper<Long>> countersList = new ArrayList<>();
	private final List<Future<Boolean>> countersFutures = new ArrayList<>();
	private List<Reducer<Long>> reducersList = new ArrayList<>();
	private List<Future<Boolean>> reducersFutures = new ArrayList<>();
	private BlockingQueue<Language> languages;
	private ExecutorService executor = Executors.newFixedThreadPool(EXECUTORS);
	private static FileParser fileParser;
	private KmerGenerator kmerGenerator;
	private KmersSize kmerSize;
	
	/**
	 * Create {@code Callable} that returns the map
	 * 
	 * @param file
	 * @param kmerSize
	 */
	public DataCreationTask(File file,KmersSize kmerSize) {
		this.kmerGenerator = kmerGenerator;
		fileParser = new FileParser(file, readFileQueue, FILE_READERS_THREADS);
		this.kmerSize = kmerSize;

	}

	/**
	 * Create the subject map on two steps.
	 * 
	 */
	@Override
	public Map<Language, Countator<Long, Entry<Long>>> call() throws Exception {

		// start reading file
		executor.submit(fileParser);

		// create threads for map the data
		for (int i = 0; i < FILE_READERS_THREADS; i++) {
			KmerGenerator<Long> temp = (KmerGenerator<Long>) KmerGeneratorFactory.getInstance().getNew(kmerSize, Long.class);
			countersList.add(new Mapper<Long>(readFileQueue, countingQueue, temp));
		}

		// submit all thread on executor
		for (Mapper m : countersList) {
			countersFutures.add(executor.submit(m));
		}

		// wait for all them to finish
		for (Future<Boolean> f : countersFutures) {
			f.get();
		}

		// create a queue with all languages
		languages = new LinkedBlockingQueue<>(countingQueue.keySet());

		// add one poison for each reducer thread
		for (int i = 0; i < REDUCERS_NUMBER; i++) {
			languages.add(Language.Poison);
		}

		// create reducers threads
		for (int i = 0; i < REDUCERS_NUMBER; i++) {
			reducersList.add(new Reducer(countingQueue, languages));
		}
		
		//submit reducers and get futures
		for (Reducer r : reducersList) {
			reducersFutures.add(executor.submit(r));
		}

		// wait for all to finish
		for (Future<Boolean> f : reducersFutures) {
			f.get();
		}

		executor.shutdown();
		return countingQueue;
	}

}
