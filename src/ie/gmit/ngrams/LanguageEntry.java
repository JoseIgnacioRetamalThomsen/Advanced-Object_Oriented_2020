package ie.gmit.ngrams;

import java.util.ArrayList;
import java.util.List;

/**
 * Entry implementation for a specific language.
 * 
 * @author Jose I. Retamal
 *
 * @param <T> The type of object of the entry
 */
public class LanguageEntry<T> implements Entry<T> {
	
	private T kmer;
	private int frequency;
	private int rank;
	
	/**
	 * Constructor for poison children.
	 */
	public LanguageEntry() {}

	/**
	 * Create object, start frequency at 1.
	 * 
	 * @param kmer the kmer that define the entry
	 */
	public LanguageEntry(T kmer) {
		super();
		this.kmer = kmer;
		frequency =1;
		
	}

	/**
	 * Create object with decidere frequency, used for testing/
	 * 
	 * @param kmer the kmer
	 * @param frequency stating frequency
	 */
	public LanguageEntry(T kmer, int frequency) {
		super();
		this.kmer = kmer;
		this.frequency = frequency;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public T getEntry() {
		return kmer;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setKmer(T kmer) {
		this.kmer = kmer;
	}


	@Override
	public int getFrequency() {
		return frequency;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getRank() {
		return rank;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Entry<T> plusOne() {
		frequency++;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {

		return kmer.hashCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LanguageEntry other = (LanguageEntry) obj;
		if (kmer == null) {
			if (other.kmer != null)
				return false;
		} else if (!kmer.equals(other.kmer))
			return false;
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "LanguageEntry [kmer=" + kmer + ", frequency=" + frequency + ", rank=" + rank + "]";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(Entry entry) {
		
		return -Integer.compare(frequency, entry.getFrequency());
	}

}
