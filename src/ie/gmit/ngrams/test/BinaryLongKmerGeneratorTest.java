package ie.gmit.ngrams.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import ie.gmit.ngrams.LongKmer4Generator;
import ie.gmit.ngrams.KmerGenerator;

public class BinaryLongKmerGeneratorTest {

	private static int kmerLength = 4;
	private static String line = "родонь";
	private static int kmersNumber = 3;
	private static int[] characters = {0b10001000000,0b10000111110,0b10000110100,0b10000111110,0b10000111101,0b10001001100};
	private static String kmers[] = { "10001000000000001000011111000000100001101000000010000111110",
										"10000111110000001000011010000000100001111100000010000111101",
	 									"10000110100000001000011111000000100001111010000010001001100"};
	
	
	
	@Test
	public void setLineTest() {
		LongKmer4Generator kg = new LongKmer4Generator();
		kg.setLine(line);
		assertEquals("Line was properly set",line,kg.getLine());
	}
	
	@Test
	public void hasNextTest() {
		KmerGenerator<Long> kg = new LongKmer4Generator();
		kg.setLine(line);
	
		for(int i =0 ; i <3 ; i++) {
			assertTrue(kg.hasNext());
			assertEquals(kmers[i],Long.toBinaryString(kg.next()));
		}
		assertFalse(kg.hasNext());
	
	}
	
	@Test
	public void getAllTest() {
		KmerGenerator kg = new LongKmer4Generator();
		kg.setLine(line);
		
		List<Long> list = kg.getAll();
		int count =0;
		for(Long l : list) {
			assertEquals(kmers[count++],Long.toBinaryString(l));
		}
		
	}
	
	

}
