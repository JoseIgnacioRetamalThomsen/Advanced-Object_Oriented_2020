package ie.gmit.ngrams.test;

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
import ie.gmit.ngrams.Entry;
import ie.gmit.ngrams.KmersSize;
import ie.gmit.ngrams.Language;
import ie.gmit.ngrams.LanguageEntry;

public class DataCreationTaskTest {

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
	public void test() {
		 ExecutorService executor = Executors.newFixedThreadPool(10);
		
		DataCreationTask cdt = new DataCreationTask(new File("wili-2018-Large-117500-Edited.txt"),KmersSize.Four);

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

		assertEquals(235,map.size());
		for(Language key : map.keySet()) {
			assertEquals(300,map.get(key).getSize());
		
		}
		
	}

}
