package ie.gmit.ngrams.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BinaryLongKmerGeneratorTest.class, ConcurrentCountatorTest.class, CreateMapOneTaskTest.class,
		DatabaseBuilderManagerTest.class, DataCreationTaskTest.class, KmerGeneratorFactoryTest.class,
		LanguageEntryTest.class, LongKmer3GeneratorTest.class, MapperTest.class, OutOfPlaceMetricDistanceTest.class })
public class AllTests {

}
