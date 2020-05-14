package ie.gmit.ngrams.builders;

import java.io.File;

import ie.gmit.ngrams.DatabaseInterface;

/**
 * Define methods to build the database. Database is build on 2 steps : build the man
 *  and build the distance calculator.
 * 
 * @author Jose I. Retamal 
 *
 */
public interface DatabaseBuilder {

	/**
	 * Build the map, map is build from a file containing entries for each language.
	 * 
	 * @param file the file containing languages entries.
	 */
	void buildMap(File file);
	
	/**
	 * Builde the distance calculator, will define the metric used for compare entries.
	 */
	void buildDistanceCalculator();
	
	/**
	 * Returns the builded database
	 * 
	 * @return the database
	 */
	DatabaseInterface getDatabase();
}
