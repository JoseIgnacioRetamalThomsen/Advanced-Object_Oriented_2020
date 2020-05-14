package ie.gmit.ngrams;

/**
 * A entry of a {@code T} elements that keep count and rank of it.
 * 
 * @author Jose I. Retamal
 *
 * @param <T> The object to keep count and rank
 */
public interface Entry<T> extends Comparable<Entry<T>>{

	/**
	 * Return the counted object, for example the kemr.
	 * 
	 * @return the object of which we are keeping record
	 */
	T getEntry();

	/**
	 * Set the counted object
	 * 
	 * @param kmer the object of which we are keeping record
	 */
	void setKmer(T kmer);

	/**
	 * Get the actual frequency, which is the count of the object.
	 *  
	 * @return the count
	 */
	int getFrequency();

	/**
	 * Set frequency, must be use only for testing.
	 * 
	 * @param frequency hte new frequency.
	 */
	void setFrequency(int frequency);

	/**
	 * Return the rank of the object in a list of entries.
	 * 
	 * @return the rank
	 */
	int getRank();

	/**
	 * Set the rank of the object in a List.
	 * 
	 * @param rank the new rank
	 */
	void setRank(int rank);

	/**
	 * Add one to the count.
	 * 
	 * @return the updated entry
	 */
	Entry<T> plusOne();

}