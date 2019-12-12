package labsesorov_3;

public class LabSesorov_3
{
    public static final int STACK_COUNT = 3;
    public static final int PUSH_COUNT = 10;
    public static final int POLL_COUNT = 5;
    public static final int RANDOM_RANGE = 100;
    
    public static void main(String[] args) 
    {
        int iSize = 5;//Integer.parseInt(args[0]);
        int jSize = 5;//Integer.parseInt(args[1]);
        int module = 10;//Integer.parseInt(args[2]);
        int[][] test = new int[iSize][jSize];
        setRandomArray(test, module);
        printArray(test);
        
        Stack<int[]> max = maxIndexes(test);
        System.out.println("Максимум = " + test[max.peek(0)[0]][max.peek(0)[1]]);
        System.out.printf("Индекс" + ((max.length() > 1) ? "ы:" : ":"));
        for (int i = 0; i < max.length(); i++)
        {
            System.out.printf(" [%d][%d]", max.peek(i)[0], max.peek(i)[1]);
        }
        System.out.println();
        
        Stack<int[]> min = minIndexes(test);
        System.out.println("Минимум = " + test[min.peek(0)[0]][min.peek(0)[1]]);
        System.out.printf("Индекс" + ((min.length() > 1) ? "ы:" : ":"));
        for (int i = 0; i < min.length(); i++)
        {
            System.out.printf(" [%d][%d]", min.peek(i)[0], min.peek(i)[1]);
        }
        
        //printTimeSort(iSize, jSize, module, true);
        //randomStackOperations();
        //replacements(5);
        //oneCycleReplacement(5, 3);
    } 
    public static Stack<int[]> maxIndexes(int[][] input)
    {
        int max = input[0][0];
        Stack<int[]> indexes = new Stack();
        for (int i = 0; i < input.length; i++) // перебор строк
            for (int j = 0; j < input[i].length; j++) // перебор столбцов
            {
                if (input[i][j] > max)
                {
                    max = input[i][j];
                    indexes.clear();
                    int[] pair = {i, j};
                    indexes.push(pair);
                }
                else if (input[i][j] == max)
                {
                    int[] pair = {i, j};
                    indexes.push(pair);
                }
            }
        return indexes;
    }
    public static Stack<int[]> minIndexes(int[][] input)
    {
        int min = input[0][0];
        Stack<int[]> indexes = new Stack();
        for (int i = 0; i < input.length; i++) // перебор строк
            for (int j = 0; j < input[i].length; j++) // перебор столбцов
            {
                if (input[i][j] < min)
                {
                    min = input[i][j];
                    indexes.clear();
                    int[] pair = {i, j};
                    indexes.push(pair);
                }
                else if (input[i][j] == min)
                {
                    int[] pair = {i, j};
                    indexes.push(pair);
                }
            }
        return indexes;
    }
    
    public static void printTimeSort(int iSize, int jSize, int module, boolean isAscending)
    {
        int[][] array = new int[iSize][jSize];
        setRandomArray(array, module);
        if (isAscending) // возрастающая
        {
            System.out.printf("%nИсходный массив:");
            printArray(array);
            Thread.currentThread().setPriority(1);
            long startTime = System.nanoTime();
            sort(array, true);
            System.out.printf("Время сортировки по возрастанию: %d%nОтсортированный массив:", (System.nanoTime() - startTime) / 1000);
            printArray(array);
        }
        else // убывающая
        {
            System.out.printf("%nИсходный массив:");
            printArray(array);
            Thread.currentThread().setPriority(1);
            long startTime = System.nanoTime();
            sort(array, false);
            System.out.printf("Время сортировки по убыванию: %d%nОтсортированный массив:", (System.nanoTime() - startTime) / 1000);
            printArray(array);
        }
    }
    
    public static void randomStackOperations(int stackCount, int pushCount, int pollCount)
    {
        for(int i = 0; i < stackCount; i++)
        {
            int[] sequence = new int[pushCount + pollCount];
            for (int j = 0; j < pushCount; j++)
                sequence[j] = 1;
            for (int j = pushCount; j < pollCount; j++)
                sequence[j] = 0;
            Operations.shuffle(sequence);
            Stack<Integer> modifiedStack = new Stack();
            Stack<Integer> fullStack = new Stack();
            Stack<Integer> removed = new Stack();
            
            for (int type : sequence)
            {
                if (type == 0)
                {
                    if (modifiedStack.length() > 0)
                        removed.push(modifiedStack.poll());
                    else
                    {
                        System.out.println("Сгенерированная последовательность подразумевает извлечение элемента из пустого стека. ВМЕСТО ЭЛЕМЕНТА БУДЕТ null ВО ИЗБЕЖАНИЕ АВАРИЙНОГО ЗАВЕРШЕНИЯ");
                        removed.push(modifiedStack.poll());
                    }
                }
                else
                {
                    modifiedStack.push(Operations.getRandomValue(RANDOM_RANGE));
                    fullStack.push(modifiedStack.peek(modifiedStack.length() - 1));
                }
            }
            System.out.printf("%nЭлементы, заносившиеся в стек: ");
            for (int j = 0; j < fullStack.length(); j++) System.out.printf(fullStack.peek(j) + " ");
            System.out.printf("%nИзвлечённые элементы: ");
            for (int j = 0; j < removed.length(); j++) System.out.printf(removed.peek(j) + " ");
            System.out.printf("%nКонечный стек: ");
            for (int j = 0; j < modifiedStack.length(); j++) System.out.printf(modifiedStack.peek(j) + " ");
            System.out.printf("%n--------------------------------------%n");
        }
    }
    public static void randomStackOperations()
    {
        randomStackOperations(STACK_COUNT, PUSH_COUNT, POLL_COUNT);
    }
    
    public static void oneCycleReplacement(int size, int cycleNum)
    {
        int[] aRaw = new int[size]; //transposition
        for (int i = 0; i < size;) aRaw[i] = ++i;
        int[] bRaw = new int[size]; //copy
        Operations.duplicateArray(aRaw, bRaw);
        Operations.cycleShuffle(bRaw, cycleNum);
        /*int[] aRaw = {1, 2, 3, 4, 5, 6};
        int[] bRaw = {6, 1, 3, 2, 5, 4};*/
        Operations.printArray(aRaw);
        Operations.printArray(bRaw);
        
        Stack<Integer> a = new Stack();
        Stack<Integer> b = new Stack();
        for (int num : aRaw) a.push(num);
        for (int num : bRaw) b.push(num);
        Stack<int[]> cycles = new Stack();
        
        for (int i = 0; i < a.length(); i++)
        {
            if (a.peek(i) == b.peek(i))
            {
                int[] single = {a.peek(i), a.peek(i)};
                a.remove(i); // 1 2 3 4 5 6
                b.remove(i); // 6 1 3 2 5 4
                cycles.push(single); //1-6-4-2-1 3-3 5-5
                i = -1;
            }
        }
        for (int i = 0; i < a.length(); i++)
        {
            if (a.peek(i) != b.peek(i))
            {
                Stack<Integer> cycle = new Stack();
                int index = i;
                int temp = a.peek(index);
                int elementA = a.peek(index), elementB = b.peek(index);
                cycle.push(elementA);
                while (temp != elementB)
                {
                    elementA = a.peek(index);
                    elementB = b.peek(index);
                    cycle.push(elementB);
                    index = a.firstIndexOf(elementB);
                    a.replace(a.firstIndexOf(elementA), a.firstIndexOf(elementB));
                    i = index;
                }
                int[] res = new int[cycle.length()];
                for (int j = 0; j < res.length; j++)
                    res[j] = cycle.peek(j);
                cycles.push(res);
                i = 0;
            }
        }
        System.out.println("Количество циклов: " + cycles.length());
        for(int i = 0; i < cycles.length(); i++)
        {
            System.out.printf("Цикл %d: ", i + 1);
            Operations.printArray(cycles.peek(i));
        }
    }
    
    public static void replacements(int size)
    {
        int[] aRaw = new int[size]; //transposition
        for (int i = 0; i < size;) aRaw[i] = ++i;
        int[] bRaw = new int[size]; //copy
        Operations.duplicateArray(aRaw, bRaw);
        Operations.shuffle(bRaw);
        /*int[] aRaw = {1, 2, 3, 4, 5, 6};
        int[] bRaw = {6, 1, 3, 2, 5, 4};*/
        Operations.printArray(aRaw);
        Operations.printArray(bRaw);
        
        Stack<Integer> a = new Stack();
        Stack<Integer> b = new Stack();
        for (int num : aRaw) a.push(num);
        for (int num : bRaw) b.push(num);
        Stack<int[]> cycles = new Stack();
        
        for (int i = 0; i < a.length(); i++)
        {
            if (a.peek(i) == b.peek(i))
            {
                int[] single = {a.peek(i), a.peek(i)};
                a.remove(i); // 1 2 3 4 5 6
                b.remove(i); // 6 1 3 2 5 4
                cycles.push(single); //1-6-4-2-1 3-3 5-5
                i = -1;
            }
        }
        for (int i = 0; i < a.length(); i++)
        {
            if (a.peek(i) != b.peek(i))
            {
                Stack<Integer> cycle = new Stack();
                int index = i;
                int temp = a.peek(index);
                int elementA = a.peek(index), elementB = b.peek(index);
                cycle.push(elementA);
                while (temp != elementB)
                {
                    elementA = a.peek(index);
                    elementB = b.peek(index);
                    cycle.push(elementB);
                    index = a.firstIndexOf(elementB);
                    a.replace(a.firstIndexOf(elementA), a.firstIndexOf(elementB));
                    i = index;
                }
                int[] res = new int[cycle.length()];
                for (int j = 0; j < res.length; j++)
                    res[j] = cycle.peek(j);
                cycles.push(res);
                i = 0;
            }
        }
        System.out.println("Количество циклов: " + cycles.length());
        for(int i = 0; i < cycles.length(); i++)
        {
            System.out.printf("Цикл %d: ", i + 1);
            Operations.printArray(cycles.peek(i));
        }
    }
    
    public static void setRandomArray(int[][] input, int module)
    {
        for (int i = 0; i < input.length; i++) // перебор строк
            for (int j = 0; j < input[i].length; j++) // перебор столбцов
                input[i][j] = Operations.getRandomValue(module);
    }
    public static void printArray(int[][] array)
    {
        for (int i = 0; i < array[0].length; i++) System.out.print("----");
        for (int i = 0; i < array.length; i++)
        {
            System.out.println();
            for (int j = 0; j < array[i].length; j++) 
                System.out.print((Integer.toString(array[i][j]).length() > 2 ? "|" : "| ")+  array[i][j] + (Integer.toString(array[i][j]).length() > 1 ? "" : " "));
        }
        System.out.println();
        for (int i = 0; i < array[0].length; i++) System.out.print("----");
        System.out.println();
    }
    public static void sort(int[][] array, boolean isAscending)
    {
        if (isAscending) //по возрастанию
        {
            for (int i = 0; i < array[i].length - 1; i++) //перебор столбцов
                for (int j = 0; j < array.length; j++) //перебор строк
                if (array[i][j] > array[i + 1][j])
                        {
                            int temp = array[i][j];
                            array[i][j] = array[i + 1][j];
                            array[i + 1][j] = temp;
                            i = 0;
                            j = -1;
                        }
        }
        else // по убыванию
        {
            for (int i = 0; i < array[i].length - 1; i++) //перебор столбцов
                for (int j = 0; j < array.length; j++) //перебор строк
                if (array[i][j] < array[i + 1][j])
                    {
                        int temp = array[i][j];
                        array[i][j] = array[i + 1][j];
                        array[i + 1][j] = temp;
                        i = 0;
                        j = -1;
                    }
        }
    }
}
