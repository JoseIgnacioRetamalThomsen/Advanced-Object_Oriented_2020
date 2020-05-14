package ie.gmit.worker;

import java.util.Set;

import ie.gmit.ngrams.KmersSize;
import ie.gmit.ngrams.Language;
import ie.gmit.ngrams.OutOfPlaceMetric;

/**
 * Define a job for add to input queue and then perform the work.
 * 
 * @author Jose I. Retamal
 *
 */
public class Job {
	private long jobNumber;// Job number from webapp
	private CharSequence input; // line for query
	private Language language; // result language
	private Set<OutOfPlaceMetric> results;// list with metrics to all all languges
	private KmersSize kmerSize; // type of kmers

	public Job() {
	}// for testing and poison

	/**
	 * Creatre the job
	 * 
	 * @param jobNumber the job number from web app
	 * @param input line for determine the language
	 * @param kmersSize the kmer type
	 */
	public Job(long jobNumber, CharSequence input, int kmersSize) {
		super();
		this.jobNumber = jobNumber;
		this.input = input;
		this.language = null;
		switch (kmersSize) {
		case 3:
			kmerSize = KmersSize.Three;
			break;
		case 4:
			kmerSize = KmersSize.Four;
		}
	}

	/**
	 * The job number
	 * @return the job number for respond to the right request
	 */
	public long getJobNumber() {
		return jobNumber;
	}

	/**
	 * Set the job number
	 * @param jobNumber the job number
	 */
	public void setJobNumber(int jobNumber) {
		this.jobNumber = jobNumber;
	}

	/**
	 * The String line for identify on which language is written
	 * 
	 * @return the query string
	 */
	public CharSequence getInput() {
		return input;
	}

	/**
	 * Sets the query String
	 *  
	 * @param input the query String
	 */
	public void setInput(CharSequence input) {
		this.input = input;
	}

	/**
	 * The language identified from the query line, null before the job is completed
	 * 
	 * @return the  identified language
	 */
	public Language getLanguage() {
		return language;
	}

	/**
	 * Set language, used before the job is completed
	 * 
	 * @param language  the  identified language
	 */
	public void setLanguage(Language language) {
		this.language = language;
	}

	/**
	 * Check if the job is done
	 * 
	 * @return true is the job is completed
	 */
	public boolean isDone() {
		return !(language == null);
	}

	/**
	 * Returns the distance for all the languages
	 * 
	 * @return list with distance to all languages
	 */
	public Set<OutOfPlaceMetric> getResults() {
		return results;
	}

	/**
	 * Set the distance for all the languages
	 * 
	 * @param results  list with distance to all languages
	 */
	public void setResults(Set<OutOfPlaceMetric> results) {
		this.results = results;
	}

	/**
	 * Returns the kmer type
	 * 
	 * @return the kemer type
	 */
	public KmersSize getKmersSize() {
		return this.kmerSize;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(jobNumber);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Job other = (Job) obj;
		if (Float.floatToIntBits(jobNumber) != Float.floatToIntBits(other.jobNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Job [jobNumber=" + jobNumber + ", input=" + input + ", language=" + language + "]";
	}

}
