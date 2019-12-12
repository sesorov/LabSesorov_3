package labsesorov_3;
public class Stack<Type> implements ArrangedList<Type>
{
    private Object[] array;
    private int pointer = -1;
    
    Stack()
    {
        this.array = (Type[]) new Object[1];
    }
    public Type peek(int index)
    {
        try
        {
            return (Type)array[index];
        }
        catch(Exception wrongIndex)
        {
            System.out.printf("Check index: %d. RETURNED LAST. Msg: %s%n", index, wrongIndex.getMessage());
        }
        return (Type)array[pointer];
    }
    public Type poll() //LIFO
    { 
        try
        {
            Type temp = (Type) array[pointer];
            remove(pointer);
            return temp;
        }
        catch (Exception emptyStack) { System.out.println("You're trying to poll an element from an empty stack. Msg: " + emptyStack.getMessage()); }
        return null; 
    } 
    public int length()
    {
        return this.pointer + 1;
    }
    public void push(Type item)
    {
        try
        {
            reset(true);
            array[++pointer] = item;
        }
        catch(Exception stackOverflow) { System.out.println("Stack overflow exception: " + stackOverflow.getMessage()); this.pointer--;}
    }
    public int firstIndexOf(Type value)
    {
        for (int i = 0; i < pointer + 1; i++)
            if (array[i].equals(value) || array[i] == value) return i;
        return -1;
    }
    public int lastIndexOf(Type value)
    {
        int output = -1;
        for (int i = 0; i < pointer + 1; i++)
            if (array[i].equals(value)) output = i;
        return output;
    }
    public boolean contains(Type value)
    {
        for (Object a : array) 
            if (a.equals(value)) return true;
        return false;
    }
    public void remove(int index)
    {
        try
        {
            for (int i = index; i < pointer; i++) //array.length - 1
            {
                array[i] = array[i + 1];
            }
            reset(false);
            this.pointer--;
        }
        catch (Exception outOfBounds) { System.out.println(this.length() < 1 ? "You're trying to remove an element from an empty stack." : outOfBounds.getMessage());}
    }
    public void replace(int i, int j)
    {
        try
        {
            Object temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        catch(Exception IndexOutOfBounds) { System.out.printf("Indexes %d, %d are out of bounds for current stack.%n", i, j); }
    }
    public void print()
    {
        for (int i = 0; i < pointer + 1; i++) System.out.print(array[i] + " ");
        System.out.println();
    }
    public void clear()
    {
        this.array = (Type[]) new Object[1];
        pointer = -1;
    }
    @SuppressWarnings("ManualArrayToCollectionCopy")
    private void copy(Object[] from, Object[] to)
    {
        for(int i = 0; i < (from.length < to.length ? from.length : to.length); i++)
            to[i] = from[i];
    }
    private void reset(boolean bigger)
    {
        Object[] newArray = bigger ? new Object[pointer + 2] : new Object[pointer];
        copy(array, newArray);
        array = newArray;
    }
}
