package ie.gmit.ngrams;

/**
 * Calcualte out of place metric distance of 2 languages
 * 
 * @author Jose I. Retamal
 *
 */
public class OutOfPlaceMetricDistance implements DistanceCalculator<Countator<Long, Entry<Long>> >  {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getDistance(Countator<Long, Entry<Long>> query, Countator<Long, Entry<Long>> subject) {
		int distance = 0;
		for(Long l : query.getKeys() ) {
			Entry<Long> temp = subject.get(l);
			distance += temp==null ? subject.getSize()+1: temp.getRank() - query.get(l).getRank();
		}
		return Math.abs(distance);
	}

}
