package ie.gmit.ngrams.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ie.gmit.ngrams.KmerGenerator;
import ie.gmit.ngrams.KmersSize;
import ie.gmit.ngrams.builders.KmerGeneratorFactory;

public class KmerGeneratorFactoryTest {

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
		KmerGenerator temp =  KmerGeneratorFactory.getInstance().getNew(KmersSize.Four, Long.class);
	}

}
