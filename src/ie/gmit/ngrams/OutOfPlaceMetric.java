package ie.gmit.ngrams;


/**
 * Store a  out of place metric distance for a language
 * 
 * @author Jose I. Retmal
 *
 */
public class OutOfPlaceMetric  implements Comparable<OutOfPlaceMetric>{

	private Language lang;
	private int distance;
			
	/**
	 * Create the object
	 * 
	 * @param lang the language
	 * @param distance the distance
	 */
	public OutOfPlaceMetric(Language lang, int distance) {
		super();
		this.lang = lang;
		this.distance = distance;
	}
		
	public Language getLang() {
		return lang;
	}


	public void setLang(Language lang) {
		this.lang = lang;
	}


	public int getDistance() {
		return distance;
	}


	public void setDistance(int distance) {
		this.distance = distance;
	}


	@Override
	public int compareTo(OutOfPlaceMetric o) {
		return Integer.compare(distance, o.getDistance());
		
	}

}
