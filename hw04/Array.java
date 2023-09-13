import java.util.Random;

// 1 Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4. При
// подаче массива другого размера необходимо бросить исключение MyArraySizeException.
// 2 Далее метод должен пройтись по всем элементам массива, преобразовать в int и
// просуммировать. Если в каком-то элементе массива преобразование не удалось (например, в
// ячейке лежит символ или текст вместо числа), должно быть брошено исключение
// MyArrayDataException с детализацией, в какой именно ячейке лежат неверные данные.
// 3 В методе main() вызвать полученный метод, обработать возможные исключения
// MyArraySizeException и MyArrayDataException и вывести результат расчета.
/**
 * Exeption
 */
public final class Array {
    static Random rnd = new Random();
    public static void main(String[] args) {
        
        for (int i = 0; i < 10; i++) {
            String[][] array = GenerateArrayOfStrings();
            PrintArrayOfStrings(array);
            try {
                Integer sum = SummArrayOfSrings(array);
                System.out.printf("Сумма элементов массива: %d\n\n",sum);
            } catch (MyArraySizeException | MyArrayDataException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    

    public static String[][] GenerateArrayOfStrings(){
        int x = rnd.nextInt(10) == 0 ? 3:4;
        int y = rnd.nextInt(10) == 0 ? 3:4;
        String[][] array = new String[x][y];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                Integer num = rnd.nextInt(50);
                if (num == 0)
                    array[i][j] = "a";
                else
                    array[i][j] = Integer.toString(num);
            }
        }
        return array;
    }
    
    public static void PrintArrayOfStrings(String[][] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.printf("%5s",array[i][j]);
            }
            System.out.println();
        }
    }  
    public static int SummArrayOfSrings(String[][] array) throws MyArraySizeException, MyArrayDataException{
        if (array.length != 4 || array[0].length !=4) 
            throw new MyArraySizeException(String.format("ОШИБКА! Массив размером %d x %d, а должен быть 4 х 4.\n\n",array.length,array[0].length));
        int sum = 0;
        int i = 0;
        int j = 0;
        try {
            for (i = 0; i < array.length; i++) {
                for (j = 0; j < array[i].length; j++) {
                    sum = sum + parseInt(array[i][j]);
                }    
            }
            return sum;
        } catch (MyArrayDataException e) {
            throw new MyArrayDataException(String.format("ОШИБКА! Строка %d, Столбец %d: %s\n\n", i+1, j+1, e.getMessage()));
        }
    }
    private static int parseInt(String value) throws MyArrayDataException {
        try
        {
            return Integer.parseInt(value);
        }
        catch (NumberFormatException e){
            throw new MyArrayDataException(String.format("Строка '%s' не может быть преобразована в число.", value));
        }
    } 
}

class MyArraySizeException extends Exception{
    public MyArraySizeException(String message) {
        super(message);
    }
}
class MyArrayDataException extends Exception{
    public MyArrayDataException(String message) {
        super(message);
    }
}
