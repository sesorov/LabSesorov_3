package labsesorov_3;
public interface ArrangedList<Type>
{
    Type peek(int index);
    Type poll();
    int length();
    void push(Type item);
    int firstIndexOf(Type item);
    int lastIndexOf(Type item);
    void remove(int index);
    void clear();
}
