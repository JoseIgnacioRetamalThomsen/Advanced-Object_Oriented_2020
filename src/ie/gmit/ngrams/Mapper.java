package ie.gmit.ngrams;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

/**
 * Generates kmers from input queue of {@code CharSequence} then they are counted in a map queue.
 * 
 * @author Jose I. Retamal
 *
 * @param <T> the type of kmers to be counted
 */
public class Mapper<T> implements Callable<Boolean> {

	private BlockingQueue<CharSequence> inQueue;
	private Map<Language, Countator<T, Entry<T>>> outQueue;
	private KmerGenerator<T> kmerGen;

	/**
	 * Creates the {@code mapper} with 
	 * 
	 * @param inQueue in queue with {@code CharSequence} of language entries for generate kmers
	 * @param outQueue out queue with a {@code Countator} for each language
	 * @param kmerGen the {@code KmerGenerator} 
	 */
	public Mapper(BlockingQueue<CharSequence> inQueue, Map<Language, Countator<T , Entry<T>>> outQueue,
			KmerGenerator<T> kmerGen) {
		super();
		this.inQueue = inQueue;
		this.outQueue = outQueue;
		this.kmerGen = kmerGen;
	}

	/**
	 * Will return true when the works is finish.
	 */
	@Override
	public Boolean call() throws Exception {
		while (true) {
			CharSequence line = inQueue.take();
			if (line instanceof StringPoison)
				break;

			CharSequence lines[] = ((String) line).split("@");
			Language lan;
			try {
				lan = Language.valueOf(((String) lines[lines.length - 1]).trim());
			} catch (Exception e) {
				continue;
			}

			if (lines[0].length() > 4) {

				kmerGen.setLine(lines[0]);

				while (kmerGen.hasNext()) {

					if (outQueue.containsKey(lan)) {
						outQueue.get(lan).count(kmerGen.next());
					} else {

						Countator<T, Entry<T>> temp = new ConcurrentLanguageEntryCountator<T>();
						temp.count(kmerGen.next());
						outQueue.put(lan, temp);
					}

				}

			}
		}
		return true;
	}

}
