package ie.gmit.ngrams.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ie.gmit.ngrams.ConcurrentLanguageEntryCountator;
import ie.gmit.ngrams.Countator;
import ie.gmit.ngrams.DistanceCalculator;
import ie.gmit.ngrams.Entry;
import ie.gmit.ngrams.LanguageEntry;
import ie.gmit.ngrams.OutOfPlaceMetricDistance;

public class OutOfPlaceMetricDistanceTest {
	private static String[] kmrs = {"aaaa","bbbb","cccc","dddd"};

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
		Countator<Long, Entry<Long>> query = new ConcurrentLanguageEntryCountator<>();
		Countator<Long, Entry<Long>> subject = new ConcurrentLanguageEntryCountator<>();
		
		for(int i =0;i<5;i++) {
			query.count(getKmer(kmrs[0]));
			subject.count(getKmer(kmrs[0]));
		}
		
		for(int i =0;i<4;i++) {
			query.count(getKmer(kmrs[2]));
			subject.count(getKmer(kmrs[1]));
		}
		
		for(int i =0;i<3;i++) {
			query.count(getKmer(kmrs[3]));
			subject.count(getKmer(kmrs[2]));
		}
		
		for(int i =0;i<2;i++) {
			query.count(getKmer(kmrs[2]));
			subject.count(getKmer(kmrs[3]));
		}
		
		DistanceCalculator<Countator<Long, Entry<Long>>> dc = new OutOfPlaceMetricDistance();
		assertEquals(0,dc.getDistance(query, subject));
		
	}
	
	private long getKmer(String s) {
		long kmer = 0x00000000000000000000000000000000L;
		for (int i=0; i <4; i++) {
			kmer <<= 16;
			kmer |= s.charAt(i);

		}
		return kmer;
	}

}
