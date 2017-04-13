import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

public class CircularBuffer<Type> implements Iterable<Type>
{
    Type[] values;
    int nextInsertionPosition;

    public CircularBuffer(Type[] values)
    {
        this.values = values;
        nextInsertionPosition = 0;
    }
    void add(Type value)
    {
        values[nextInsertionPosition] = value;
        nextInsertionPosition = (nextInsertionPosition + 1) % values.length ;
    }
    class CircularBufferIterator implements java.util.Iterator<Type>
    {
        int current = 0;
        public boolean hasNext()
        {
            return current < CircularBuffer.this.values.length;
        }
        public Type next()
        {
            Type retval = CircularBuffer.this.values[(current + CircularBuffer.this.nextInsertionPosition) % CircularBuffer.this.values.length];
            current = current + 1;
            return retval;
        }
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }
    public Iterator<Type> iterator()
    {
        return new CircularBufferIterator();
    }

    public static void main(String args[])
    {
        String[] init = { "A", "B", "C", "D" } ;
        CircularBuffer<String> cb = new CircularBuffer<String>(init);
        cb.add("E");
        cb.add("F");
        for(String s : cb) // uses hasNext() and next() methods of CircularBuffer's iterator
        {
            System.out.println("    " + s);
        }
    }
}
