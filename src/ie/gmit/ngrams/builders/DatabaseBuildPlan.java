package ie.gmit.ngrams.builders;

import java.util.Map;

import ie.gmit.ngrams.DistanceCalculator;

/**
 * Set the plan for build the database, contains the main parts: the map and the distance calculator.
 * 
 * @author Jose I. Retamal
 *
 */
public interface DatabaseBuildPlan {

	/**
	 * Set the map after is build.
	 * 
	 * @param database the map that contains all the language entries.
	 */
	void setMap(Map database);
	
	/**
	 * Set the distance calculator
	 * 
	 * @param distanceCalculator the {@code DistanceCalculator}
	 */
	void setDistanceCalculator(DistanceCalculator distanceCalculator);
	
}
