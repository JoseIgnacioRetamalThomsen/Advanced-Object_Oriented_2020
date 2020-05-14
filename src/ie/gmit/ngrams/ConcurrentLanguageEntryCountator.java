package ie.gmit.ngrams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * {@inheritDoc}
 *  
 *
 * @author Jose I. Retamal
 *
 * @param <T>
 */
public class ConcurrentLanguageEntryCountator<T> extends LanguageEntryAbstractCountator<T> {

	private Map<T, Entry<T>> map = new ConcurrentHashMap<>();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Entry<T> count(T kmer) {
		return (Entry<T>) map.compute(kmer,
				(key, value) -> (value == null) ? new LanguageEntry<T>(kmer) : value.plusOne());

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<T> getKeys() {
		return map.keySet();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setRanks(int max) {
		ArrayList<Entry<T>> temp = new ArrayList<>(map.values());
		Map<T, Entry<T>> newMap = new ConcurrentHashMap<>();
		Collections.sort(temp);
		int rank = 1;
		for (Entry<T> e : temp) {
			e.setRank(rank);
			newMap.put(e.getEntry(), e);
			if (rank++ == max)
				break;
		}
		map = newMap;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Entry<T> get(T kmer) {

		return map.get(kmer);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getSize() {

		return map.size();
	}

}
