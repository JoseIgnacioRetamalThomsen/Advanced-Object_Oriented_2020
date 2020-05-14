package ie.gmit.ngrams;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

public class Reducer<T extends Number> implements Callable<Boolean> {

	private Map<Language, Countator<T, LanguageEntry<T>>> inQueue;

	private BlockingQueue<Language> languages;

	public Reducer(Map<Language, Countator<T, LanguageEntry<T>>> inQueue, BlockingQueue<Language> languages) {
		super();
		this.inQueue = inQueue;
		this.languages = languages;
	}

	@Override
	public Boolean call() throws Exception {
		while (true) {
			Language g = languages.take();
			if (g.equals(Language.Poison))
				break;

			inQueue.get(g).setRanks(300);
		}

		return true;
	}

}
