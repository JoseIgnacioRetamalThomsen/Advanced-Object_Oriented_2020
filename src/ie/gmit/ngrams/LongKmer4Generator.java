package ie.gmit.ngrams;

import java.util.ArrayList;
import java.util.List;

/***
 * {@inheritDoc}
 * 
 * @author Jose Retamal
 *
 */
public class LongKmer4Generator extends AbstractKmerGenerator<Long> {

	protected int position = 0;
	private long kmer;
	private int kmerLength = 4;
	
	/**
	 * Create generator of specific kmer length
	 * @param kmerLength the length of the kmers
	 */
	public LongKmer4Generator() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public boolean hasNext() {
		if (line.length() == 0)
			return false;
		return line.length() > (position);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public Long next() {
		try {
			kmer <<= 16;
			kmer |= line.charAt(position++);
		} catch (Exception e) {
			throw new NoMoreKmersException();
		}
		return kmer;
	}

	@Override
	public List<Long> getAll() {
		List<Long> temp = new ArrayList<>();
		while (hasNext()) {
			temp.add(next());
		}
		return temp;
	}

	/**
	 * generate the first part of the kmer
	 */
	protected void start() {
		position = 0;
		kmer = 0x00000000000000000000000000000000L;
		for (; position < (kmerLength - 1); position++) {
			kmer <<= 16;
			kmer |= line.charAt(position);

		}
	}
}
