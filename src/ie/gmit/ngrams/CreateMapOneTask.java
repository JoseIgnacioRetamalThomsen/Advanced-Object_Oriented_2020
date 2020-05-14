package ie.gmit.ngrams;

import java.util.concurrent.Callable;

/**
 * Create one map for a language, generates the kmers from a string input. Depends on the {@ KmerGenerator}
 * that must be provided, 
 * 
 * @author Jose I. Retamal
 *
 */
public class CreateMapOneTask implements Callable<Countator<Long, Entry<Long>>> {

	private CharSequence line;
	private  KmerGenerator<Long> kmerGen;
	private int max;
	
	/**
	 * Create the object for generate the map of kmers.
	 * 
	 * @param line the {@code String} for generate kmers
	 * @param kmerGen object that generate the kmers from the line
	 * @param max the maximum amount of kmers on the final map
	 */
	public CreateMapOneTask(CharSequence line, KmerGenerator<Long> kmerGen, int max) {
		super();
		this.line = line;
		this.kmerGen = kmerGen;
		this.kmerGen.setLine(line);
		this.max = max;
	}

	/***
	 * Must be run on executor, return the {@code Future} of the map that contains the counted and ranked kmers..
	 */
	@Override
	public Countator<Long, Entry<Long>> call() throws Exception {
		
		Countator<Long, Entry<Long>> map = new ConcurrentLanguageEntryCountator<>();
		
		while (kmerGen.hasNext()) {
			map.count(kmerGen.next());
		}
		map.setRanks(max);
		
		return map;
	}

}
