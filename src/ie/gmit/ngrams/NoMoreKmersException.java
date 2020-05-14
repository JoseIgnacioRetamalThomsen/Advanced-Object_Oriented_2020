package ie.gmit.ngrams;

/***
 *  {@code RuntimeException} throwed when there are no more kmers.
 * 
 * @author Jose Retamal
 */
public class NoMoreKmersException extends RuntimeException {

	/**
	 * Serial version 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "No more kmers" + super.getMessage();
	}
	
	

}
