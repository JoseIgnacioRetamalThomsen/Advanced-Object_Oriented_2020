package ie.gmit.ngrams;

import java.util.ArrayList;
import java.util.List;

/**
 * Generate kmers of size 3
 * 
 * @author Jose I. Retamal
 *
 */
public class LongKmer3Generator extends AbstractKmerGenerator<Long> {

	protected int position = 0; 
	private long kmer;
	private int kmerLength = 3;

	@Override
	public boolean hasNext() {
		if (line.length() == 0)
			return false;
		return line.length() > (position);
	}

	@Override
	public Long next() throws NoMoreKmersException {
		try {
			
			kmer |= line.charAt(position++);
			kmer <<= 16;
			
		} catch (Exception e) {
			throw new NoMoreKmersException();
		}
		return kmer;
	}

	@Override
	public List<Long> getAll() throws NoMoreKmersException {
		List<Long> temp = new ArrayList<>();
		while (hasNext()) {
			temp.add(next());
		}
		return temp;
	}

	@Override
	protected void start() {
		position = 0;
		kmer = 0x00000000000000000000000000000000L;
		for (; position < (kmerLength-1); position++) {
			kmer <<= 16;
			kmer |= line.charAt(position);

		}
		kmer <<= 16;
	}
}
