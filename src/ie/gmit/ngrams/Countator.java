package ie.gmit.ngrams;

import java.util.Set;

/**
 * Used for counts objects of type {@code T} , the count is store in {@code K}. Should be use for counting 
 * fast and then be able to access the object in constant time, they also have a rank for keep them in a order.
 * 
 * @author Jose I. Retamal
 *
 * @param <T> The objects to be counted
 * @param <K> Keep counts of the objects, each one can be assigned a rank
 */
public interface Countator<T,K extends Entry<T>> {

	/**
	 * Count the object {@code T}
	 * 
	 * @param entry the object to be counted.
	 * @return the updated objects that keep the count
	 */
	K count(T entry);
	
	/**
	 * Return all keys, those are the different counted objects
	 *
	 * @return the key set of counted objects
	 */
	Set<T> getKeys();
	
	/**
	 * Set the rank of the counted object, limited to a maximum rank.
	 * 
	 * @param max the maximum rank
	 */
	void setRanks(int max);
	
	/**
	 * Return the object that keep the count
	 * 
	 * @param entry the counted object
	 * @return the object that keep count and rank
	 */
	K get(T entry);
	
	/**
	 * Return the amount of counted object, amount of keys.
	 * 
	 * @return the number of counted objects
	 */
	int getSize();
}
