package ie.gmit.ngrams.test;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.Before;
import org.junit.Test;

import ie.gmit.ngrams.LongKmer4Generator;
import ie.gmit.ngrams.Countator;
import ie.gmit.ngrams.Entry;
import ie.gmit.ngrams.Language;
import ie.gmit.ngrams.LanguageEntry;
import ie.gmit.ngrams.Mapper;
import ie.gmit.ngrams.StringPoison;

public class MapperTest {

	ExecutorService executor = Executors.newFixedThreadPool(10);
	
	private static final BlockingQueue<CharSequence> inQueue = new LinkedBlockingQueue<>();
	private static final Map<Language, Countator<Long, Entry<Long>>> outQueue = new ConcurrentHashMap<>();
	private static String[] lines= {
			"ab ca ab cab c@Spanish",
			"Klement Gottwaldi surnukeha palsameeriti ning paigutati mausoleumi. Surnukeha oli aga liiga hilja ja oskamatult palsameeritud ning hakkas ilmutama lagunemise tundemärke. 1962. aastal viidi ta surnukeha mausoleumist ära ja kremeeriti. Zlíni linn kandis aastatel 1949–1989 nime Gottwaldov. Ukrainas Harkivi oblastis kandis Zmiivi linn aastatel 1976–1990 nime Gotvald.@Estonian",
		"و�?ي ذكرى عيد زواجهم �?ي سنة 1945، كان الاستوديو قد تم بناؤه. وكان أول �?يلم تم تصويره �?ي الاستوديو \" أم السعد \" للمخرج أحمد جلال وبطولة \" ماري كويني و\"محمد سليمان \" . وتوالت الأ�?لام اللي صورتها شركة \" جلال \" �?ي الاستوديو ومن بينها : \" بين نارين \" ، \" عودة الغائب \" اللي كان آخر �?يلم أخرجه \" أحمد جلال \" ( واللي اتعرض بعد و�?اته ) . و بعد و�?اة أحمد جلال صممت أرملته ال�?نانة \" ماري كويني \" علي مواصلة مشواره �?قررت أن تقوم بإدارة الاستوديو وتطوير أنشطته وتحديث آلاته. و�?ي سنة 1958، ا�?تتحت معمل لتحميض وطبع الأ�?لام الملونة قبل أن تنتقل ملكية الاستوديو إلى شركة مصر للاستوديوهات سنة . ولتكريم اسم زوجها الراحل ، �?قد أصرت �?ي عقد البيع أن يظل الاستوديو يحمل اسمه.@EgyptianArabic",
		"Indtil 1545 havde flådecheferne kunnet hyre et betydeligt antal frie mænd til galejerne, selv om de kun sjældent var venetianere. De kom fra Dalmatien, Kreta og Grækenland. Herefter gik man i stigende grad over til tvangsudskrivning af fanger og skyldnere, ligesom det længe havde været normalt i resten af Europa. På langt sigt havde det den konsekvens for arbejdsmarkedet, at stadig færre lønmodtagere tjente deres penge på havet.@Danish",
		"ab ca ab cab c@Spanish"};
	
	@Before
	public void setUp() throws Exception {
		for(String s : lines) {
			inQueue.put(s);
		}
		inQueue.put(new StringPoison());
	}
	
	
	@Test
	public void test() {
		Mapper<Long> mapper = new Mapper<>(inQueue,outQueue,new LongKmer4Generator());
		try {
			boolean result = executor.submit(mapper).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(6,outQueue.get(Language.Spanish).get(getKmer("ab c")).getFrequency());
		
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
