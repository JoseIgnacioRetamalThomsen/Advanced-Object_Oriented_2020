package ie.gmit.ngrams.test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ie.gmit.ngrams.Countator;
import ie.gmit.ngrams.CreateMapOneTask;
import ie.gmit.ngrams.DatabaseInterface;
import ie.gmit.ngrams.Entry;
import ie.gmit.ngrams.KmerGenerator;
import ie.gmit.ngrams.LongKmer3Generator;
import ie.gmit.ngrams.LongKmer4Generator;
import ie.gmit.ngrams.builders.DatabaseBuildManager;
import ie.gmit.ngrams.builders.LongKmer3EncodeDatabaseBuilder;
import ie.gmit.ngrams.builders.LongKmer4EncodeDatabase;

public class DatabaseBuilderManagerTest {
	private ExecutorService executor = Executors.newFixedThreadPool(10);
	private String line = "Klement Gottwaldi surnukeha palsameeriti ning paigutati mausoleumi. Surnukeha oli aga liiga hilja ja oskamatult palsameeritud ning hakkas ilmutama lagunemise tundemärke. 1962. aastal viidi ta surnukeha mausoleumist ära ja kremeeriti. Zlíni linn kandis aastatel 1949–1989 nime Gottwaldov. Ukrainas Harkivi oblastis kandis Zmiivi linn aastatel 1976–1990 nime Gotvald.";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testKmer3() throws InterruptedException, ExecutionException {
		try {
		File file = new File("wili-2018-Small-11750-Edited.txt");
		DatabaseInterface db = DatabaseBuildManager.getInstance().setBuilder(new LongKmer3EncodeDatabaseBuilder()).build(file)
				.getDatabase(); 
		KmerGenerator<Long> temp = new LongKmer3Generator();
		CreateMapOneTask cmot = new CreateMapOneTask(line, temp,300);
		Countator<Long, Entry<Long>> map = executor.submit(cmot).get();
		System.out.println(db==null);
		System.out.println(db.getLanguage(map));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
