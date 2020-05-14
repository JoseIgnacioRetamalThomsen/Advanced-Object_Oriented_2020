package ie.gmit.ngrams.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ie.gmit.ngrams.ConcurrentLanguageEntryCountator;
import ie.gmit.ngrams.Countator;
import ie.gmit.ngrams.Entry;
import ie.gmit.ngrams.LanguageEntry;

public class ConcurrentCountatorTest {
	private static Long kmer1 = 0b10001000000000001000011111000000100001101000000010000111110l;
	private static Long kmer2 = 0b10000111110000001000011010000000100001111100000010000111101l;
	private static Long kmer3 = 0b10000110100000001000011111000000100001111010000010001001100l;

	@Test
	public void countTest() {

		Countator<Long, Entry<Long>> c = new ConcurrentLanguageEntryCountator<>();
		Entry<Long> temp = c.count(kmer1);
		assertEquals(1, temp.getFrequency());
		int i = 2;
		for (; i < 100; i++) {
			temp = c.count(kmer1);
			assertEquals(i, temp.getFrequency());
		}
		Entry<Long> temp1 = c.count(kmer2);
		assertEquals(1, temp1.getFrequency());
		int j = 2;
		for (; j < 100; j++) {
			temp1 = c.count(kmer2);
			assertEquals(j, temp1.getFrequency());
		}
		temp = c.count(kmer1);
		temp1 = c.count(kmer2);
		assertEquals(i, temp.getFrequency());
		assertEquals(j, temp1.getFrequency());

	}

	public void rankTest() {
		Countator<Long, Entry<Long>> c = new ConcurrentLanguageEntryCountator<>();
		int c1 = 0;
		for (; c1 < 10; c1++)
			c.count(kmer1);
		int c2 = 0;
		for (; c2 < 20; c2++)
			c.count(kmer2);
		int c3 = 0;
		for (; c3 < 20; c3++)
			c.count(kmer3);

		c.setRanks(2);

		assertEquals(1, c.get(kmer3).getRank());
		assertEquals(2, c.get(kmer2).getRank());
		assertEquals(2, c.getSize());

	}

	
}
