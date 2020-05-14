package ie.gmit.ngrams;

/**
 * Define a distance between two objects of the same type.
 * 
 * @author Jose I. Retamal
 *
 * @param <T> the type of the objects
 */
public interface DistanceCalculator<T> {

	/**
	 * Calculate distance between two objects of the same type.
	 * 
	 * @param t1 object one
	 * @param t2 object two
	 * @return the distance between object one and object two
	 */
	int getDistance(T t1, T t2);
}
