package ie.gmit.ngrams.test;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ie.gmit.ngrams.LongKmer4Generator;
import ie.gmit.ngrams.Countator;
import ie.gmit.ngrams.CreateMapOneTask;
import ie.gmit.ngrams.Entry;
import ie.gmit.ngrams.KmerGenerator;
import ie.gmit.ngrams.LanguageEntry;

public class CreateMapOneTaskTest {

	private ExecutorService executor = Executors.newFixedThreadPool(10);
	private String line = "ab ca ab cab c";

	@Test
	public void test() throws InterruptedException, ExecutionException {
		try {
			KmerGenerator<Long> temp = new LongKmer4Generator();
			CreateMapOneTask cmot = new CreateMapOneTask(line, temp,300);
			Countator<Long, Entry<Long>> map = executor.submit(cmot).get();
			assertEquals(3, map.get(getKmer("ab c")).getFrequency());
			assertEquals(1, map.get(getKmer("ab c")).getRank());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private long getKmer(String s) {
		long kmer = 0x00000000000000000000000000000000L;
		for (int i = 0; i < 4; i++) {
			kmer <<= 16;
			kmer |= s.charAt(i);

		}
		return kmer;
	}
}
