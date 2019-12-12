package labsesorov_3;

public class LabSesorov_3
{
    public static final int I_SIZE = 8;
    public static final int J_SIZE = 6;
    public static final int MODULE = 10;
    public static final int STACK_COUNT = 3;
    public static final int PUSH_COUNT = 10;
    public static final int POLL_COUNT = 5;
    public static final int RANDOM_RANGE = 100;
    public static final int SORT_COUNT = 2;
    public static final int REPLACEMENT_SIZE = 5;
    public static final int CYCLES_COUNT = 3;
    public static final String SORT_TYPE = "BOTH"; 
    
    public static void main(String[] args) 
    {
        //ВВОД С КОМАНДНОЙ СТРОКИ: i-РАЗМЕР 2D МАССИВА, j-РАЗМЕР 2D МАССИВА, МОДУЛЬ ЕГО ЭЛЕМЕНТОВ, ТИП СОРТИРОВКИ ASCENDING/DESCENDING/BOTH, ПАРАМЕТРЫ ГЕНЕРАЦИИ СТЕКОВ (КОЛИЧЕСТВО СТЕКОВ, КОЛИЧЕСТВО ВСТАВОК, КОЛИЧЕСТВО ИЗВЛЕЧЕНИЙ), РАЗМЕР ПЕРЕСТАНОВКИ, КОЛИЧЕСТВО ЦИКЛОВ
        int iSize = I_SIZE, jSize = J_SIZE, module = MODULE, stackCount = STACK_COUNT, pushCount = PUSH_COUNT, pollCount = POLL_COUNT, repSize = REPLACEMENT_SIZE, cycles = CYCLES_COUNT;
        String type = SORT_TYPE;
        try
        {
            iSize = Integer.parseInt(args[0]);
            jSize = Integer.parseInt(args[1]);
            module = Integer.parseInt(args[2]);
            type = args[3];
            stackCount = Integer.parseInt(args[4]);
            pushCount = Integer.parseInt(args[5]);
            pollCount = Integer.parseInt(args[6]);
            repSize = Integer.parseInt(args[7]);
            cycles = Integer.parseInt(args[8]);
        }
        catch(Exception wrongInput)
        {
            System.out.println("Нвеверный ввод. Будут использованы значения по умолчанию: ");
            System.out.printf("i-РАЗМЕР 2D МАССИВА = %d%n", iSize);
            System.out.printf("j-РАЗМЕР 2D МАССИВА = %d%n", jSize);
            System.out.printf("МОДУЛЬ ЕГО ЭЛЕМЕНТОВ = %d%n", module);
            System.out.println("ТИП СОРТИРОВКИ = " + type);
            System.out.printf("КОЛИЧЕСТВО СТЕКОВ = %d%n", stackCount);
            System.out.printf("КОЛИЧЕСТВО ВСТАВОК = %d%n", pushCount);
            System.out.printf("КОЛИЧЕСТВО ИЗВЛЕЧЕНИЙ = %d%n", pollCount);
            System.out.printf("РАЗМЕР ПЕРЕСТАНОВКИ = %d%n", repSize);
            System.out.printf("КОЛИЧЕСТВО ЦИКЛОВ = %d%n", cycles);
        }
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
        System.out.println();
        System.out.println("-------СКОРОСТЬ СОРТИРОВОК-------");
        printTimeSort(iSize, jSize, module, type);
        System.out.println("-------ОПЕРАЦИИ СО СТЕКАМИ-------");
        randomStackOperations(stackCount, pushCount, pollCount);
        System.out.println("-------ПЕРЕСТАНОВКА С ПОДСЧЁТОМ ЦИКЛОВ-------");
        replacements(repSize);
        System.out.println("-------ПЕРЕСТАНОВКА С ПОЛЬЗОВАТЕЛЬСКИМ КОЛИЧЕСТВОМ ЦИКЛОВ-------");
        customCycleReplacement(repSize, cycles);
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
    
    public static void printTimeSort(int iSize, int jSize, int module, String type)
    {
        int[][] array = new int[iSize][jSize];
        setRandomArray(array, module);
        if (type.equals("ASCENDING")) // возрастающая
        {
            System.out.printf("%nИсходный массив:%n");
            printArray(array);
            Thread.currentThread().setPriority(1);
            long startTime = System.nanoTime();
            sort(array, true);
            System.out.printf("Время сортировки по возрастанию: %d%nОтсортированный массив:%n", (System.nanoTime() - startTime) / 1000);
            printArray(array);
        }
        else if (type.equals("DESCENDING"))// убывающая
        {
            System.out.printf("%nИсходный массив:%n");
            printArray(array);
            Thread.currentThread().setPriority(1);
            long startTime = System.nanoTime();
            sort(array, false);
            System.out.printf("Время сортировки по убыванию: %d%nОтсортированный массив:%n", (System.nanoTime() - startTime) / 1000);
            printArray(array);
        }
        else if (type.equals("BOTH"))
        {
            System.out.printf("%nИсходный массив:%n");
            printArray(array);
            int[][] copy = new int[iSize][jSize];
            Operations.duplicateArray(array, copy);
            long[] time = new long[SORT_COUNT];
            Thread.currentThread().setPriority(1);
            boolean sType = true;
            for(int i = 0; i < SORT_COUNT; i++)
            {
                time[i] = System.nanoTime();
                sort(array, sType);
                time[i] = (System.nanoTime() - time[i]) / 1000;
                System.out.println("Отсортирован по" + (sType ? " возрастанию:" : " убыванию:"));
                printArray(array);
                Operations.duplicateArray(copy, array);
                sType = false;
            }
            System.out.printf("Сортировка по возрастанию заняла %d мкс.%n", time[0]);
            System.out.printf("Сортировка по убыванию заняла %d мкс.%n", time[1]);
            System.out.println("Самая быстрая для данного массива: по " + (time[0] <= time[1] ? "возрастанию." : "убыванию."));
        }
        else System.out.printf("%nWrong TYPE parameter. Expected: ASCENDING/DESCENDING, got: " + type);
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
            for (int operation : sequence)
            {
                if (operation == 0) System.out.print("ИЗВЛЕЧЕНИЕ ");
                else System.out.print("ВСТАВКА ");
            }
            System.out.println();
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
        System.out.printf("Параметры генерации не заданы или заданы неверно (количество стеков, количество вставок, количество извлечений). Использованы значения по умолчанию: %d, %d, %d%n", STACK_COUNT, PUSH_COUNT, POLL_COUNT);
        randomStackOperations(STACK_COUNT, PUSH_COUNT, POLL_COUNT);
    }
    
    public static void customCycleReplacement(int size, int cycleNum)
    {
        if (cycleNum > size)
        {
            System.out.println("Too many cycles. Array cannot be shuffled.");
            return;
        }
        if (cycleNum < 1)
        {
            System.out.println("Please, use 1+ cycles. You required 0 or less.");
            return;
        }
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
            for (int i = 0; i < array.length - 1; i++) //перебор столбцов
                for (int j = 0; j < array[0].length; j++) //перебор строк
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
            for (int i = 0; i < array.length - 1; i++) //перебор столбцов
                for (int j = 0; j < array[0].length; j++) //перебор строк
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
