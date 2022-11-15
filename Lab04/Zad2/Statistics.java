package Lab04.Zad2;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

public class Statistics {

    private int M;
    private int portionTimes;
    private double[] naiveProducerArray;
    private double[] naiveCustomerArray;
    private double[] fairProducerArray;
    private double[] fairCustomerArray;

    public Statistics(int portionTimes, int M) {
        this.M = M;
        this.portionTimes = portionTimes;
        naiveProducerArray = new double[M];
        Arrays.fill(naiveProducerArray, 0);
        naiveCustomerArray = new double[M];
        Arrays.fill(naiveCustomerArray, 0);
        fairProducerArray = new double[M];
        Arrays.fill(fairProducerArray, 0);
        fairCustomerArray = new double[M];
        Arrays.fill(fairCustomerArray, 0);
    }

    // time measurement
    public long getNanoStartTimestamp() {
        return System.nanoTime();
    }

    public long getNanoDuration(long startTimestamp) {
        return System.nanoTime() - startTimestamp;
    }

    // temporary sum arrays
    public void putNaiveCustomer(int idx, long value) {
        naiveCustomerArray[idx] += (double) value;
    }

    public void putNaiveProducer(int idx, long value) {
        naiveProducerArray[idx] += (double) value;
    }

    public void putFairCustomer(int idx, long value) {
        fairCustomerArray[idx] += (double) value;
    }

    public void putFairProducer(int idx, long value) {
        fairProducerArray[idx] += (double) value;
    }

    public void avgNaiveCustomer() {
        for (int i = 0; i < this.M; i++) {
            naiveCustomerArray[i] = naiveCustomerArray[i] / this.portionTimes / 1000000000; // avg nanoseconds
        }
    }

    public void avgNaiveProducer() {
        for (int i = 0; i < this.M; i++) {
            naiveProducerArray[i] = naiveProducerArray[i] / this.portionTimes / 1000000000; // avg nanoseconds
        }
    }

    public void avgFairCustomer() {
        for (int i = 0; i < this.M; i++) {
            fairCustomerArray[i] = fairCustomerArray[i] / this.portionTimes / 1000000000; // avg nanoseconds
        }
    }

    public void avgFairProducer() {
        for (int i = 0; i < this.M; i++) {
            fairProducerArray[i] = fairProducerArray[i] / this.portionTimes / 1000000000; // avg nanoseconds
        }
    }

    // save results

    public void saveResults() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("Lab04/Results/results.dat", "UTF-8");
        writer.println("portion\tnaivProd\tnaivCons\tfairProd\tfairCons");
        for (int i = 0; i < this.M; i++) {
            writer.println(i + "\t" + naiveProducerArray[i] + "\t" + naiveCustomerArray[i] + "\t" + fairProducerArray[i] + "\t" + fairCustomerArray[i]);
        }
        writer.close();
    }
}
