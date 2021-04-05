import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Сортировки\nQuicksort;\nВыбором;\nВставкой;\nОбменом;\nШелла;\nТурнирная;\nПирамидальная;");
        System.out.println("\n Исходная матрица:");

        //Генерация случайной матрицы
        int[][] a = new int[40][40];
        for (int i=0; i<40; i++) {
            for (int j=0; j<40; j++) {
                a[i][j] = (int) (Math.random()*256);
                System.out.print(a[i][j] + "\t");
            }
            System.out.println();
        }

        //Преобразование матрицы в одномерный массив
        int[] arr = getArrLine(a);

        //Quicksort
        int[] arrQuiclSort = copy(arr);
        long startTime = System.currentTimeMillis();
        quickSort(arrQuiclSort, 0, arrQuiclSort.length - 1);
        long endTime = System.currentTimeMillis();
        System.out.println("\n\nВремя выполнения методом quicksort: " + (endTime-startTime) + "ms\nРезультат");
        outputMatr(arrQuiclSort);

        //Выбором
        int[] arrSelectionSort = copy(arr);
        startTime = System.currentTimeMillis();
        selectionSort(arrSelectionSort);
        endTime = System.currentTimeMillis();
        System.out.println("\n\nВремя выполнения методом выбора: " + (endTime-startTime) + "ms\nРезультат");
        outputMatr(arrSelectionSort);

        //Вставкой
        int[] arrInsertSort = copy(arr);
        startTime = System.currentTimeMillis();
        insertSort(arrInsertSort);
        endTime = System.currentTimeMillis();
        System.out.println("\n\nВремя выполнения методом вставки: " + (endTime-startTime) + "ms\nРезультат");
        outputMatr(arrInsertSort);

        //Обменом
        int[] arrSwapSort = copy(arr);
        startTime = System.currentTimeMillis();
        bubbleSort(arrSwapSort);
        endTime = System.currentTimeMillis();
        System.out.println("\n\nВремя выполнения методом обмена: " + (endTime-startTime) + "ms\nРезультат");
        outputMatr(arrSwapSort);

        //Шелл
        int[] arrShellSort = copy(arr);
        startTime = System.currentTimeMillis();
        shellSort(arrShellSort);
        endTime = System.currentTimeMillis();
        System.out.println("\n\nВремя выполнения методом Шелла: " + (endTime-startTime) + "ms\nРезультат");
        outputMatr(arrShellSort);

        //Турнирная
        int[] arrTournamentSort = copy(arr);
        startTime = System.currentTimeMillis();
        tournamentSort(arrTournamentSort);
        endTime = System.currentTimeMillis();
        System.out.println("\n\nВремя выполнения турнирным методом: " + (endTime-startTime) + "ms\nРезультат");
        outputMatr(arrTournamentSort);

        //Пирамидная
        int[] arrPyramidSort = copy(arr);
        startTime = System.currentTimeMillis();
        pyramidSort(arrPyramidSort, arrPyramidSort.length);
        endTime = System.currentTimeMillis();
        System.out.println("\n\nВремя выполнения пирамидным методом: " + (endTime-startTime) + "ms\nРезультат");
        outputMatr(arrPyramidSort);

        scanner.close();
    }

    private static int[] getArrLine(int[][] arr) {
        int[] result = new int[1600];
        for (int i=0, counter = 0; i<40; i++) {
            for (int j=0; j<40; j++, counter++) {
                result[counter] = arr[i][j];
            }
        }
        return result;
    }

    private static int[] copy(int[] arr) {
        int[] result = new int[1600];
        for (int i=0; i<1600; i++) {
                result[i] = arr[i];
        }
        return result;
    }

    private static void outputMatr(int[] arr) {
        for (int i=0, counter = 0; i<40; i++) {
            for (int j=0; j<40; j++, counter++) {
                System.out.print(arr[counter]+"\t");
            }
            System.out.println();
        }
    }

    private static void quickSort(int[] array, int low, int high) {
        if (array.length == 0)
            return;//завершить выполнение если длина массива равна 0

        if (low >= high)
            return;//завершить выполнение если уже нечего делить

        // выбрать опорный элемент
        int middle = low + (high - low) / 2;
        int opora = array[middle];

        // разделить на подмассивы, который больше и меньше опорного элемента
        int i = low, j = high;
        while (i <= j) {
            while (array[i] < opora) {
                i++;
            }

            while (array[j] > opora) {
                j--;
            }

            if (i <= j) {//меняем местами
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        // вызов рекурсии для сортировки левой и правой части
        if (low < j)
            quickSort(array, low, j);

        if (high > i)
            quickSort(array, i, high);
    }

    private static void selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {    // i - номер текущего шага
            int pos = i;
            int min = array[i];
            // цикл выбора наименьшего элемента
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    pos = j;    // pos - индекс наименьшего элемента
                    min = array[j];
                }
            }
            array[pos] = array[i];
            array[i] = min;    // меняем местами наименьший с array[i]
        }
    }

    private  static void insertSort (int[] array) {
        for (int left = 0; left < array.length; left++) {
            // Вытаскиваем значение элемента
            int value = array[left];
            // Перемещаемся по элементам, которые перед вытащенным элементом
            int i = left - 1;
            for (; i >= 0; i--) {
                // Если вытащили значение меньшее — передвигаем больший элемент дальше
                if (value < array[i]) {
                    array[i + 1] = array[i];
                } else {
                    // Если вытащенный элемент больше — останавливаемся
                    break;
                }
            }
            // В освободившееся место вставляем вытащенное значение
            array[i + 1] = value;
        }
    }

    private static void swap(int[] array, int ind1, int ind2) {
        int tmp = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = tmp;
    }

    private  static void bubbleSort (int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                swap(array, i, i-1);
            }
        }
    }

    private  static void shellSort (int[] array) {
        int gap = array.length / 2;
        // Пока разница между элементами есть
        while (gap >= 1) {
            for (int right = 0; right < array.length; right++) {
                // Смещаем правый указатель, пока не сможем найти такой, что
                // между ним и элементом до него не будет нужного промежутка
                for (int c = right - gap; c >= 0; c -= gap) {
                    if (array[c] > array[c + gap]) {
                        swap(array, c, c + gap);
                    }
                }
            }
            // Пересчитываем разрыв
            gap = gap / 2;
        }
    }

    private  static void tournamentSort (int[] array) {
        Integer[] newArray = new Integer[array.length];
        List<Integer> intList = new ArrayList<Integer>(array.length);
        for (int i : array)
        {
            intList.add(i);
        }


        List<Integer> secondRunList = new ArrayList<Integer>();
        List<Integer> secondRun = new ArrayList<Integer>();
        List<Integer> sortedNumbers = new ArrayList<Integer>();

        TournamentSortAlgorithm tournamentSortAlgorithm = new TournamentSortAlgorithm();

        tournamentSortAlgorithm.sort(intList, secondRun, secondRunList, sortedNumbers);

        int numbersSize = intList.size();


        do {

            tournamentSortAlgorithm.sort(intList, secondRun, secondRunList, sortedNumbers);

            intList =  new ArrayList<Integer>(sortedNumbers);

            secondRunList = new ArrayList<Integer>(secondRun);

            secondRun = new ArrayList<Integer>();

            sortedNumbers = new ArrayList<Integer>();


        } while (secondRunList.size() != 0 );

        int i=0;
        for (Integer temp : intList)
            array[i++] = temp;
    }

    private  static void pyramidSort (int[] array, int heapSize) {
        buildHeap(array, heapSize);
        while (heapSize > 1) {
            swap(array, 0, heapSize - 1);
            heapSize--;
            heapify(array, 0, heapSize);
        }
    }

    private static void buildHeap(int[] a, int heapSize) {
        heapSize = a.length;
        for (int i = a.length / 2; i >= 0; i--) {
            heapify(a, i, heapSize);
        }
    }private static void heapify(int[] a, int i, int heapSize) {
        int l = left(i);
        int r = right(i);
        int largest = i;
        if (l < heapSize && a[i] < a[l]) {
            largest = l;
        }
        if (r < heapSize && a[largest] < a[r]) {
            largest = r;
        }
        if (i != largest) {
            swap(a, i, largest);
            heapify(a, largest, heapSize);
        }
    }

    private static int right(int i) {
        return 2 * i + 2;
    }

    private static int left(int i) {
        return 2 * i + 1;
    }


}
