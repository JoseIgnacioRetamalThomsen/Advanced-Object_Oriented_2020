package ie.gmit.ngrams.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import ie.gmit.ngrams.Entry;
import ie.gmit.ngrams.LanguageEntry;


public class LanguageEntryTest {

	private static String kmers[] = { "10001000000000001000011111000000100001101000000010000111110",
			"10000111110000001000011010000000100001111100000010000111101",
			"10000110100000001000011111000000100001111010000010001001100" };
	private static Long kmer1 = 0b10001000000000001000011111000000100001101000000010000111110l;

	@Test
	public void constructorTest() {
		Entry<Long> le = new LanguageEntry<Long>(kmer1);

		assertEquals(kmer1, le.getEntry());
		assertEquals(1, le.getFrequency());
	}

	@Test
	public void increaseTest() {
		Entry<Long> le = new LanguageEntry<Long>(kmer1);
		int x = 10;
		for (int i = 0; i < x; i++) {
			le.plusOne();
		}
		assertEquals(x + 1, le.getFrequency());
	}

	@Test
	public void setRankTest() {
		Entry<Long> le = new LanguageEntry<Long>(kmer1);
		int rank = 11;
		le.setRank(rank);
		assertEquals(rank, le.getRank());
	}

	@Test
	public void equalsTest() {
		Entry<Long> le = new LanguageEntry<Long>(kmer1);
		Entry<Long> le1 = new LanguageEntry<Long>(kmer1);
		assertEquals(le, le1);
	}

	@Test
	public void compareToTest() {
		LanguageEntry<Long> le = new LanguageEntry<Long>(kmer1);
		LanguageEntry<Long> le1 = new LanguageEntry<Long>(kmer1);
		le.setFrequency(7);
		le1.setFrequency(6);
		List<LanguageEntry> temp = new ArrayList<>();
		temp.add(le);
		temp.add(le1);
		assertTrue(0 > le.compareTo(le1));

	}
}
