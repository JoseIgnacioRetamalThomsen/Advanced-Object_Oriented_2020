package ie.gmit.ngrams;

import java.util.Map;
import java.util.Set;

/**
 * Define interface methods for access database 
 * 
 * @author Jose I. Retamal
 *
 */
public interface DatabaseInterface {

	/**
	 * Return the {@code Language} with smallest distance to query
	 * 
	 * @param query count of kmers of query
	 * @return the language with smallest distance to query
	 */
	Language getLanguage(Countator<Long, Entry<Long>> query);

	/**
	 * Return list with distance to all available languages.
	 * 
	 * @param query map of kmers for query on database
	 * @return list with distance to the other languages
	 */
	Set<OutOfPlaceMetric> getResults(Countator<Long, Entry<Long>> query);

	/**
	 * Set the map to query on
	 * 
	 * @param database the map for use as subject for query's
	 */
	void setMap(Map database);

	/**
	 * Set the type of distance to be use
	 * 
	 * @param distanceCalculator the distance calculator
	 */
	void setDistanceCalculator(DistanceCalculator distanceCalculator);

}