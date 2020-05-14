package ie.gmit.ngrams.builders;

import ie.gmit.ngrams.KmerGenerator;
import ie.gmit.ngrams.KmersSize;

/**
 * Define a factory for create 
 * 
 * @author Jose I. Retamal
 *
 */
public interface KmerGeneratorFactoryInterface {

	/**
	 * Gets the {@code KkersGenerator} with specific characteristics
	 * 
	 * @param kmerSize size of kmers
	 * @param cls the class of the kmers
	 * @return the {@code KkersGenerator} generator
	 */
	KmerGenerator<?> getNew(KmersSize kmerSize, Class<?> cls);
}
