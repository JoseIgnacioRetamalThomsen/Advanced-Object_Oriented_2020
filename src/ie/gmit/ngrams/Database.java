package ie.gmit.ngrams;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import ie.gmit.ngrams.builders.DatabaseBuildPlan;

/**
 * A databse base with one entry for each language, contains methods for compare other entry's against the 
 * stored entries.
 * 
 * @author Jose I. Retamal
 *
 */
public class Database implements DatabaseBuildPlan, DatabaseInterface {

	private Map<Language, Countator<Long, Entry<Long>>> database;
	private DistanceCalculator<Countator<Long, Entry<Long>>> distanceCalculator;
    
	/**
	 * No parameter constructor, used for builder.
	 */
	public Database() {
	}

	/**
	 * Constructor with database map and {@code DistanceCalculator}
	 * 
	 * @param database with entries of all languages
	 * @param distanceCalculator distance calculator
	 */
	public Database(Map<Language, Countator<Long, Entry<Long>>> database,
			DistanceCalculator<Countator<Long, Entry<Long>>> distanceCalculator) {
		super();
		this.database = database;
		this.distanceCalculator = distanceCalculator;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Language getLanguage(Countator<Long, Entry<Long>> query) {
		TreeSet<OutOfPlaceMetric> entries = new TreeSet<>();

		for (Language l : database.keySet()) {
			OutOfPlaceMetric temp = new OutOfPlaceMetric(l, distanceCalculator.getDistance(query, database.get(l)));
			entries.add(temp);
		}
		return entries.first().getLang();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<OutOfPlaceMetric> getResults(Countator<Long, Entry<Long>> query) {
		TreeSet<OutOfPlaceMetric> entries = new TreeSet<>();

		for (Language l : database.keySet()) {
			OutOfPlaceMetric temp = new OutOfPlaceMetric(l, distanceCalculator.getDistance(query, database.get(l)));
			entries.add(temp);
		}
		return entries;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMap(Map database) {
		this.database = database;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDistanceCalculator(DistanceCalculator distanceCalculator) {
		this.distanceCalculator = distanceCalculator;

	}
}
