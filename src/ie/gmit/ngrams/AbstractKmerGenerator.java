package ie.gmit.ngrams;

/**
 * {@inheritDoc}
 * 
 * @author Jose Retamal
 *
 * @param <T> Kmer Type
 */
public abstract class AbstractKmerGenerator<T> implements KmerGenerator<T> {

	
	protected CharSequence line;


	/**
	 * Create a new generator, line must be set before use.
	 * 
	 * @param kmerLength Length of the kmers.
	 */
	public AbstractKmerGenerator() {
		super();
		
	}

	/**
	 * {@inheritDoc}
	 */
	public void setLine(CharSequence line) {
		this.line = line;
		start();
		
	}



	/**
	 * Return the {@code CharSequence} line
	 * 
	 * @return the line
	 */
	public CharSequence getLine() {
		return line;
	}

	/**
	 * Initialize the generator.
	 */
	protected abstract void start();

}
