package ie.gmit.worker.test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ie.gmit.ngrams.Countator;
import ie.gmit.ngrams.DataCreationTask;
import ie.gmit.ngrams.Database;
import ie.gmit.ngrams.DatabaseInterface;
import ie.gmit.ngrams.DistanceCalculator;
import ie.gmit.ngrams.Entry;
import ie.gmit.ngrams.KmersSize;
import ie.gmit.ngrams.Language;
import ie.gmit.ngrams.LanguageEntry;
import ie.gmit.ngrams.OutOfPlaceMetricDistance;
import ie.gmit.worker.Job;
import ie.gmit.worker.Worker;

public class WorkerTest {
	private ExecutorService executor = Executors.newFixedThreadPool(10);
	private DatabaseInterface database;
	private Job job;
	private String line = "Klement Gottwaldi surnukeha palsameeriti ning paigutati mausoleumi. Surnukeha oli aga liiga hilja ja oskamatult palsameeritud ning hakkas ilmutama lagunemise tundemärke. 1962. aastal viidi ta surnukeha mausoleumist ära ja kremeeriti. Zlíni linn kandis aastatel 1949–1989 nime Gottwaldov. Ukrainas Harkivi oblastis kandis Zmiivi linn aastatel 1976–1990 nime Gotvald.";
	private Language language = Language.Estonian;
	@Before
	public void setUp() throws Exception {
		
		DataCreationTask cdt = new DataCreationTask(new File("wili-2018-Small-11750-Edited.txt"),KmersSize.Four);
		
		Map<Language, Countator<Long, Entry<Long>>> map = executor.submit(cdt).get();
						
		System.out.println("done" + map.size());
	
		DistanceCalculator dc = new OutOfPlaceMetricDistance();
		
		database =  new Database(map,dc);
		
		
		
		job = new Job(1,line,4);
		
	}
	@After
	public void tearDown() throws Exception {
		executor.shutdown();
	}
	
	@Test
	public void test() throws InterruptedException, ExecutionException {
		Worker worker = new Worker(database,job);
		Job result = executor.submit(worker).get();
		
		assertEquals(language,job.getLanguage());
		
	}

}
