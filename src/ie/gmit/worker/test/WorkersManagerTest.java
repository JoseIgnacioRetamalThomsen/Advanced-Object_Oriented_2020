package ie.gmit.worker.test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ie.gmit.ngrams.Countator;
import ie.gmit.ngrams.DataCreationTask;
import ie.gmit.ngrams.DatabaseInterface;
import ie.gmit.ngrams.DistanceCalculator;
import ie.gmit.ngrams.KmersSize;
import ie.gmit.ngrams.Language;
import ie.gmit.ngrams.LanguageEntry;
import ie.gmit.ngrams.OutOfPlaceMetricDistance;
import ie.gmit.ngrams.builders.DatabaseBuildManager;
import ie.gmit.ngrams.builders.LongKmer4EncodeDatabase;
import ie.gmit.worker.DoneQueueInterface;
import ie.gmit.worker.InJobQueue;
import ie.gmit.worker.InQueueInterface;
import ie.gmit.worker.Job;
import ie.gmit.worker.JobDoneQueue;
import ie.gmit.worker.WorkersManager;

public class WorkersManagerTest {

	private ExecutorService executor = Executors.newFixedThreadPool(10);
	private DatabaseInterface database;

	// static BlockingQueue<Job> inQueue = new LinkedBlockingQueue<>();
	static InQueueInterface<Job> inQueue = new InJobQueue();
	// static Map<Integer, Future<Job>> outQueue = new ConcurrentHashMap<>();
	static DoneQueueInterface<Job> outQueue = new JobDoneQueue();

	WorkersManager wm;

	private static String[] lines = {
			"Klement Gottwaldi surnukeha palsameeriti ning paigutati mausoleumi. Surnukeha oli aga liiga hilja ja oskamatult palsameeritud ning hakkas ilmutama lagunemise tundemärke. 1962. aastal viidi ta surnukeha mausoleumist ära ja kremeeriti. Zlíni linn kandis aastatel 1949–1989 nime Gottwaldov. Ukrainas Harkivi oblastis kandis Zmiivi linn aastatel 1976–1990 nime Gotvald.",
			"Tuigeann Bhiolbó gurb é Gandalf atá taobh thiar den tóraíocht seo agus gur tugadh le fios chuig na habhaic go ndéanfadh Biolbó gadaí den scoth. Go drogallach agus mar thoradh comhairle Gandalf, téann Biolbó leo. Teann an grúpa amach sa domhan mór, ag taisteal trasna Críocha na Hobad i dtreo an tSléibhe Uaignigh. Bíonn Biolbó go síoraí ag machnamh ar an gcinneadh a rinne sé faoi dheifir, i ndiaidh léargas a fháil ar fhiántas an domhain. Sna Críocha Uaigneacha tagann said ar throill, a ghabhann na habhaic agus a chuireann i málaí iad, agus é i gceist acu iad a róstáil agus a ithe. Éiríonn le Biolbó agus Gandalf iad a shábháil agus faigheann siad claimhte i bpoll na dtroll",
			"エノが行きがかりでバスに乗ってしまい、気分が悪くなった際に助けるが、今すぐバスを降りたいと運転手に頼む際、本当のことを言ってしまうと彼女が恥ずかしい思いをすると察して「僕ウンコしたいんです!!」と言ってバスを降りた。エノは内心「私もしたいみたいじゃないの」と思うも、別れ際にエノの髪を「ふわふわのお菓子みたい」と言い、この台詞に憧れていたエノに強い衝撃を与えた。この話を聞いたリコは、以後彼のことを『ウンコ王子』または『" };
	private static Language[] languages = { Language.Estonian, Language.Irish, Language.Japanese };

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

		WorkersManager.init(inQueue, outQueue).addDatabase(KmersSize.Four,
				DatabaseBuildManager.getInstance().setBuilder(new LongKmer4EncodeDatabase())
						.build(new File("wili-2018-Small-11750-Edited.txt")).getDatabase());

		for (int i = 0; i < languages.length; i++) {
			WorkersManager.addJob(i, lines[i], 4);
		}

	}

	@After
	public void tearDown() throws Exception {
		executor.shutdown();
		WorkersManager.stop();
	}

	@Test
	public void test() throws InterruptedException, ExecutionException {
		for (int i = 0; i < languages.length; i++) {

			if (outQueue.remains() <= 0) {
				i--;
				continue;
			}
			Job temp = outQueue.get(i);

			assertEquals(languages[i], temp.getLanguage());
		}
	}

}
