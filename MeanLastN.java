// import CircularBuffer;

public class MeanLastN
{
    // Java generics cannot be parameterized by primitive type, hence we use Double instead of double
    private CircularBuffer<Double> buf;

    public MeanLastN(Double[] storage)
    {
        buf = new CircularBuffer<Double>(storage);
    }

    public void add(double x)
    {
        buf.add((Double)x);
    }

    public double getMean()
    {
        double sum = 0;
        int n = 0;
        for(Double x: buf)
        {
            sum += x;
            n++;
        }
        return sum / n;
    }

    public static void main(String[] args)
    {
        Double[] primes = { 2., 3., 5., 7. };
        MeanLastN m = new MeanLastN(primes);
        System.out.println("Initially: " + m.getMean());
        m.add(11.);
        System.out.println(" After 11: " + m.getMean());
        m.add(13.);
        System.out.println(" After 13: " + m.getMean());
        m.add(17);
        System.out.println(" After 17: " + m.getMean());
        m.add(19);
        System.out.println(" After 19: " + m.getMean());
    }
}
