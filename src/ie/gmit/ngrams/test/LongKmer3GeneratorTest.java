package ie.gmit.ngrams.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ie.gmit.ngrams.KmerGenerator;
import ie.gmit.ngrams.LongKmer3Generator;

public class LongKmer3GeneratorTest {
	
	private static int kmerLength = 3;
	private static String line = "родонь";
	private static int kmersNumber = 4;
	private static int[] characters = {0b10001000000,0b10000111110,0b10000110100,0b10000111110,0b10000111101,0b10001001100};
	private static String kmers[] = {"10001000000000001000011111000000100001101000000000000000000" ,
										"10000111110000001000011010000000100001111100000000000000000",
										"10000110100000001000011111000000100001111010000000000000000",
	 									"10000111110000001000011110100000100010011000000000000000000"};

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
	public void testNext() {
		KmerGenerator<Long> gen = new LongKmer3Generator();
		gen.setLine(line);
		Long next = gen.next();
		System.out.println(Long.toBinaryString(next));
		assertEquals(kmers[0],Long.toBinaryString(next));
		
		next = gen.next();
		assertEquals(kmers[1],Long.toBinaryString(next));
		
		next = gen.next();
		assertEquals(kmers[2],Long.toBinaryString(next));
		
		assertEquals(true,gen.hasNext());
		next = gen.next();
		assertEquals(kmers[3],Long.toBinaryString(next));
		
		assertEquals(false,gen.hasNext());
	}

	@Test
	public void testList() {
		KmerGenerator<Long> gen = new LongKmer3Generator();
		gen.setLine(line);
		List<Long> list = gen.getAll();
		int i =0;
		for(Long l : list) {
			assertEquals(kmers[i++],Long.toBinaryString(l));
		}
	}
}
