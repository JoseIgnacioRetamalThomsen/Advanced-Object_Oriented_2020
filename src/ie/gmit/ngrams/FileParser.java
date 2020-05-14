package ie.gmit.ngrams;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.BlockingQueue;

/**
 * Parse a file, put each line of the file into a output queue as
 * {@code CharSequence}, a poison will indicate the end of the file.
 * 
 * @author Jose I. Retamal
 *
 */
public class FileParser implements Runnable {

	private int parsersNum;
	private BufferedReader br;
	private BlockingQueue<CharSequence> outQueue;

	/**
	 * Create the {@Code FileParse}
	 * 
	 * @param file       input file
	 * @param outQueue   output queue
	 * @param parsersNum number of thread that will read from outputn queue
	 */
	public FileParser(File file, BlockingQueue<CharSequence> outQueue, int parsersNum) {
		this.parsersNum = parsersNum;
		this.outQueue = outQueue;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Start reading file and put lines on queue, will put the indicated number of
	 * poison at the end.
	 */
	@Override
	public void run() {
		CharSequence line = null;

		try {
			while ((line = br.readLine()) != null) {
				outQueue.put(line);
			}

			for (int i = 0; i < parsersNum; i++) {
				outQueue.put(new StringPoison());
			}

		} catch (InterruptedException e) {
			// Nothing to do
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}