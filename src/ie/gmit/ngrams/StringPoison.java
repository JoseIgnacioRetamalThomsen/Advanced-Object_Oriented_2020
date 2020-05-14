package ie.gmit.ngrams;

/**
 * Poison for CharSequence queue, this is the queue for reading the file.
 * Poisons are used for mark the end of the concurrent queue.
 * 
 * @author Jose I. Retamal
 *
 */
public class StringPoison implements CharSequence {

	@Override
	public char charAt(int index) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int length() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

}
