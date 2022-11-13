package Lab04.Zad2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

public class Statistics {
    private Map<Integer, Double> naiveProducer = new HashMap<Integer, Double>();
    private Map<Integer, Double> naiveCustomer = new HashMap<Integer, Double>();
    private Map<Integer, Double> fairProducer = new HashMap<Integer, Double>();
    private Map<Integer, Double> fairCustomer = new HashMap<Integer, Double>();

    public long getNanoStartTimestamp() {
        return System.nanoTime();
    }

    public long getNanoDuration(long startTimestamp) {
        return System.nanoTime() - startTimestamp;
    }

    private void putMap(HashMap<Integer, Double> map, Double value, Integer key) {

    }

    private double avg(ArrayList<Integer> l) {
        // https://stackoverflow.com/questions/10791568/calculating-average-of-an-array-list
        final OptionalDouble average = l
                .stream()
                .mapToDouble(a -> a)
                .average();

        return average.isPresent() ? average.getAsDouble() : 0;
    }

    public void putNaiveProducer(Integer key, ArrayList<Integer> l) {

    }
}
