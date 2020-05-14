package ie.gmit.ngrams.builders;

import java.io.File;

import ie.gmit.ngrams.DatabaseInterface;

/**
 * Build different types of databases based on the {@code DatabaseBuilder},
 * implemented as a Eagle singleton.
 * 
 * 
 * @author Jose I. Retamal
 *
 */
public class DatabaseBuildManager {

	private static DatabaseBuildManager instance = new DatabaseBuildManager();
	private DatabaseBuilder builder;

	// private constructor for singleton
	private DatabaseBuildManager() {
	}

	/**
	 * Get the unique instance
	 * 
	 * @return the unique instance
	 */
	public static DatabaseBuildManager getInstance() {
		return instance;
	}

	/**
	 * Set the builder
	 * 
	 * @param builder the database builder
	 * @return unique instance of this object
	 */
	public DatabaseBuildManager setBuilder(DatabaseBuilder builder) {
		instance.builder = builder;
		return instance;
	}

	/**
	 * Build the database and the distance calculator
	 * 
	 * @param file the file for build the database
	 * @return the unique instance of this object
	 */
	public DatabaseBuildManager build(File file) {
		if (builder == null)
			throw new RuntimeException("Builder need to be set");
		instance.builder.buildMap(file);
		instance.builder.buildDistanceCalculator();
		return instance;
	}

	/**
	 * Returns the builded database.
	 * 
	 * @return the builded database
	 */
	public DatabaseInterface getDatabase() {
		return instance.builder.getDatabase();
	}
}
