package labsesorov_3;
public class Operations 
{
    public static int getRandomValue(int range) { return new java.util.Random().nextInt(range * 2 + 1) - range; }
    public static int getRandomValue(int range, boolean isPositive) { return new java.util.Random().nextInt(range); }
    
    public static void setRandomArray(int[] array, int range)
    {
        for (int i = 0; i < array.length; i++) 
            array[i] = getRandomValue(range);
    }
    
    public static void shuffle(int[] a)
    {
        for (int i = 0; i < a.length; i++)
        {
            int temp = a[i], rnd = getRandomValue(a.length, true);
            a[i] = a[rnd];
            a[rnd] = temp;
        }
    }
    @SuppressWarnings("ManualArrayToCollectionCopy")
    public static void cycleShuffle(int[] a, int cycles)
    {
        if (cycles > a.length)
        {
            System.out.println("Too many cycles. Array cannot be shuffled.");
            return;
        }
        int temp[] = cycle(a, null, cycles);
        printArray(a);
    }
    private static int[] cycle(int[] source, int[] temp, int cycles)
    {
        if (cycles == source.length) 
        {
            temp = new int[1];
            temp[0] = source[0];
            return temp;
        }
        else
        {
            if (cycles % 2 == 0) //cycles = 4 // source = 1 2 3 4 5
            {
                temp = cycle(source, temp, cycles + 1); //{1}
                source[0] = source[source.length - cycles]; //2 2 3 4 5
                for (int i = 0; i < temp.length; i++)
                {
                    source[i + 1] = temp[i]; // 2 1 3 4 5
                }
                temp = new int[source.length - cycles + 1];
                for (int i = 0; i < temp.length; i++)
                    temp[i] = source[i];
                return temp;
            }
            else //cycles = 3
            {
                temp = cycle(source, temp, cycles + 1); // 2 1
                for (int i = 0; i < temp.length; i++)
                {
                    source[i] = temp[i]; // 2 1 3 4 5
                }
                swap(source, 0, source.length - cycles); // 3 1 2 4 5
                temp = new int[source.length - cycles + 1];
                for (int i = 0; i < temp.length; i++)
                    temp[i] = source[i];
                return temp;
            }
        }
    }
    
    public static void swap(int[] array, int first, int second)
    {
        int temp;
        temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }
    
    public static int firstIndexOf(int[] input, int num) // index of num in input
    {
        for (int i = 0; i < input.length; i++)
        {
            if (input[i] == num) return i;
        }
        return -1;
    }
    
    public static void duplicateArray(int[] from, int[] to)
    {
        for (int i = 0; i < from.length; i++)
            to[i] = from[i];
    }
    
    public static void printArray(int[] array)
    {
        if (array.length < 21)
            for (Object a : array)
                System.out.print(a + " ");
        else
        {
            for (byte i = 0; i < 10; i++)
                System.out.print(array[i] + " ");
            System.out.print("... ");
            for (int i = array.length - 10; i < array.length; i++)
                System.out.print(array[i] + " ");
        }
        System.out.println();
    }
    
    public static void printArray(Object[] array)
    {
        if (array.length < 21)
            for (Object a : array)
                System.out.print(a + " ");
        else
        {
            for (byte i = 0; i < 10; i++)
                System.out.print(array[i] + " ");
            System.out.print("... ");
            for (int i = array.length - 10; i < array.length; i++)
                System.out.print(array[i] + " ");
        }
        System.out.println();
    }
    
    public static void quickSort(int[] input, int leftMarker, int rightMarker)
    {
        int leftBorder = leftMarker, rightBorder = rightMarker;
        int pointer = input[(leftBorder + rightBorder) / 2];
        do
        {
            while (input[leftBorder] < pointer)
                leftBorder++;
            while (input[rightBorder] > pointer)
                rightBorder--;
        
            if (leftBorder <= rightBorder)
            {
                if (leftBorder < rightBorder)
                {
                    int temp = input[leftBorder];
                    input[leftBorder] = input[rightBorder];
                    input[rightBorder] = temp;
                }
                leftBorder++;
                rightBorder--;
            } 
        } while (leftBorder <= rightBorder);
        
        if (leftBorder < rightMarker) quickSort(input, leftBorder, rightMarker);
        if (rightBorder > leftMarker) quickSort(input, leftMarker, rightBorder);
    }
    
    public static void quickSort(int[] input, int leftMarker, int rightMarker, boolean isAscending)
    {
        int leftBorder = leftMarker, rightBorder = rightMarker;
        int pointer = input[(leftBorder + rightBorder) / 2];
        do
        {
            while (input[leftBorder] < pointer)
                leftBorder++;
            while (input[rightBorder] > pointer)
                rightBorder--;
        
            if (leftBorder >= rightBorder)
            {
                if (leftBorder > rightBorder)
                {
                    int temp = input[leftBorder];
                    input[leftBorder] = input[rightBorder];
                    input[rightBorder] = temp;
                }
                leftBorder++;
                rightBorder--;
            } 
        } while (leftBorder <= rightBorder);
        
        if (leftBorder < rightMarker) quickSort(input, leftBorder, rightMarker);
        if (rightBorder > leftMarker) quickSort(input, leftMarker, rightBorder);
    }
}
