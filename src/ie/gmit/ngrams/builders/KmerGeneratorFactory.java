package ie.gmit.ngrams.builders;

import ie.gmit.ngrams.KmerGenerator;
import ie.gmit.ngrams.KmersSize;
import ie.gmit.ngrams.LongKmer3Generator;
import ie.gmit.ngrams.LongKmer4Generator;

/**
 * Factory for {@code KmerGenerator}. Implemented as a singleton.
 * 
 * @author Jose I. Retamal
 *
 */
public class KmerGeneratorFactory implements KmerGeneratorFactoryInterface {

	private static KmerGeneratorFactory instance = new KmerGeneratorFactory();

	private KmerGeneratorFactory() {
	}

	/**
	 * Returns the unique instance of this builder
	 * 
	 * @return the unique instance
	 */
	public static KmerGeneratorFactory getInstance() {
		return instance;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public KmerGenerator<?> getNew(KmersSize kmerSize, Class<?> cls) {
		if (cls == Long.class) {
			switch (kmerSize) {

			case Four:
				return new LongKmer4Generator();
			case Three:
				return new LongKmer3Generator();
			case ThreeAndFour:
				throw new RuntimeException("Not implemented");
			default:
				throw new RuntimeException("Wrong parameters");

			}
		}
		return null;
	}

}
