package ie.gmit.ngrams.builders;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ie.gmit.ngrams.Countator;
import ie.gmit.ngrams.DataCreationTask;
import ie.gmit.ngrams.Database;
import ie.gmit.ngrams.DatabaseInterface;
import ie.gmit.ngrams.DistanceCalculator;
import ie.gmit.ngrams.Entry;
import ie.gmit.ngrams.KmersSize;
import ie.gmit.ngrams.Language;
import ie.gmit.ngrams.OutOfPlaceMetricDistance;

/**
 * Build a database with kmers of size 3 using long for store kmers in binary .
 * 
 * @author Jose I. Retamal
 *
 */
public class LongKmer3EncodeDatabaseBuilder implements DatabaseBuilder {

	private Database database;

	/**
	 * Create the object and initialize database
	 */
	public LongKmer3EncodeDatabaseBuilder() {
		super();
		this.database = new Database();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void buildMap(File file) {
		ExecutorService executor = Executors.newFixedThreadPool(1);
		DataCreationTask cdt = new DataCreationTask(file, KmersSize.Three);
		Map<Language, Countator<Long, Entry<Long>>> map = null;
		try {
			map = executor.submit(cdt).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executor.shutdown();
		this.database.setMap(map);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void buildDistanceCalculator() {
		DistanceCalculator dc = new OutOfPlaceMetricDistance();
		this.database.setDistanceCalculator(dc);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DatabaseInterface getDatabase() {
		// TODO Auto-generated method stub
		return database;
	}

}
