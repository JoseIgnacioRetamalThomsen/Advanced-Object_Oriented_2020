package ie.gmit.ngrams;

import java.util.List;

/***
 * Generate kmers from a line of characters.
 *  
 * @author Jose Retamal
 *
 * @param <T> The Kmer type.
 * 
 */
public interface KmerGenerator<T> {

	/***
	 * Give information about kmers left, should be use always before {@code next()}.
	 * 
	 * @return {@code True} if there is at least one kmer left
	 */
	boolean hasNext() ;
	
	/***
	 * Return the next kmers if there is one.
	 * 
	 * @return The next kmer
	 * @throws NoMoreKmersException If there are no more kmers available
	 */
	T next() throws NoMoreKmersException;
	
	/***
	 * Return all kmers left.
	 *  
	 * @return List containing all kmers left
	 * @throws NoMoreKmersException If there are no more kmers left
	 */
	List<T> getAll() throws NoMoreKmersException;
	
	/***
	 * Set kmer line and reset object, means kmers will be produced from the new line.
	 * @param line The new line for extract kmers.
	 */
	void setLine(CharSequence line);
	

	
}
