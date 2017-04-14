import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

public class CircularBuffer<Type> implements Iterable<Type>
{
    private Type[] values;
    private int nextInsertionPosition;

    public CircularBuffer(Type[] values)
    {
        this.values = values;
        nextInsertionPosition = 0;
    }
    public void add(Type value)
    {
        values[nextInsertionPosition] = value;
        nextInsertionPosition = (nextInsertionPosition + 1) % values.length ;
    }
    class CircularBufferIterator implements java.util.Iterator<Type>
    {
        private int current;
        public CircularBufferIterator()
        {
            current = 0;
        }
        @Override
        public boolean hasNext()
        {
            return current < CircularBuffer.this.values.length;
        }
        @Override
        public Type next()
        {
            Type retval = CircularBuffer.this.values[(current + CircularBuffer.this.nextInsertionPosition) % CircularBuffer.this.values.length];
            current = current + 1;
            return retval;
        }
        @Override
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }
    @Override
    public Iterator<Type> iterator()
    {
        return new CircularBufferIterator();
    }

    public static void main(String args[])
    {
        String[] init = { "A", "B", "C", "D" } ;
        CircularBuffer<String> cb = new CircularBuffer<String>(init);
        CircularBuffer<Integer> cb2 = new CircularBuffer<Integer>(new Integer[3]);
        cb.add("E");
	    cb2.add(101);
        cb.add("F");
	    cb2.add(102);
        for(String s : cb) // uses hasNext() and next() methods of CircularBuffer's iterator
        {
            System.out.println("    " + s);
        }
	    for(Integer i : cb2)
	    {
		    System.out.println(" " + i);
	    }
    }
}
