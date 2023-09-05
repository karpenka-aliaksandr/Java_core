package karpenko;

//import java.util.Random;
import java.util.Scanner;

import java.lang.Math;

public class App {
    //region Stat
    private static int winCount; // Выигрышная комбинация
    private static final char DOT_HUMAN = 'X'; // Фишка игрока - человек
    private static final char DOT_AI = '0'; // Фишка игрока - компьютер
    private static final char DOT_EMPTY = '*'; // Признак пустого поля
    private static final int fieldSizeXMax = 25;
    private static final int fieldSizeYMax = 25;
    private static final Scanner scanner = new Scanner(System.in);
    //private static final Random random = new Random();

    private static char[][] field; // Двумерный массив хранит текущее состояние игрового поля

    private static int fieldSizeX; // Размерность игрового поля
    private static int fieldSizeY; // Размерность игрового поля
    private static long maxRating;

    //endregion

    public static void main(String[] args) {
        while (true) {
            initialize();
            printField();
            while (true) {
                humanTurn();
                printField();
                if (checkGameState(DOT_HUMAN, "Вы победили!"))
                    break;
                aiTurn();
                printField();
                if (checkGameState(DOT_AI, "Победил компьютер!"))
                    break;
            }
            System.out.print("Желаете сыграть еще раз? (Y - да): ");
            if (!scanner.next().equalsIgnoreCase("Y"))
                break;
        }
    }

    /**
     * Инициализация объектов игры
     */
    private static void initialize(){
        while (true){
            System.out.printf("Введите размер X поля по вертикали (от 2 до %d): ",fieldSizeXMax);
            if (scanner.hasNextInt()){
                fieldSizeX = scanner.nextInt();
                scanner.nextLine();
                if (fieldSizeX<fieldSizeXMax && fieldSizeX>1)
                break;
            }
            else {
                System.out.println("Некорректное число, повторите попытку ввода.");
                scanner.nextLine();
            }
        }
        while (true){
            System.out.printf("Введите размер Y поля по горизонтали (от 2 до %d): ",fieldSizeYMax);
            if (scanner.hasNextInt()){
                fieldSizeY = scanner.nextInt();
                scanner.nextLine();
                if (fieldSizeY<fieldSizeYMax && fieldSizeY>1)
                break;
            }
            else {
                System.out.println("Некорректное число, повторите попытку ввода.");
                scanner.nextLine();
            }
        }
        int maxWin_Count = Math.min(fieldSizeX,fieldSizeY);
        while (true){
            System.out.printf("Введите Win_Count (количество подряд идущих фишек для пробеды (от 2 до %d): ",maxWin_Count);
            if (scanner.hasNextInt()){
                winCount = scanner.nextInt();
                scanner.nextLine();
                if (winCount<=maxWin_Count && winCount>1)
                break;
            }
            else {
                System.out.println("Некорректное число, повторите попытку ввода.");
                scanner.nextLine();
            }
        }

        field = new char[fieldSizeX][fieldSizeY];
        for (int x = 0; x < fieldSizeX; x++){
            for (int y = 0; y < fieldSizeY; y++){
                field[x][y] = DOT_EMPTY;
            }
        }

    }
    /**
     * Отрисовка игрового поля
     *
     * +-1-2-3-
     * 1|*|X|0|
     * 2|*|*|0|
     * 3|*|*|0|
     * --------
     */
    private static void printField() {
        System.out.print("++");
        for (int y = 0; y < fieldSizeY * 2 + 1; y++) {
            System.out.print((y % 2 == 0) ? (y/2+1>10? "":"-") : y / 2 + 1);
        }
        System.out.println();

        for (int x = 0; x < fieldSizeX; x++) {
            System.out.print((x + 1 > 9 ? x + 1: x + 1 + " ") + "|");
            for (int y = 0; y < fieldSizeY; y++) {
                System.out.print(field[x][y] + "|");
            }
            System.out.println();
        }

        for (int y = 0; y < fieldSizeY * 2 + 2; y++) {
            System.out.print("-");
        }
        System.out.println();
        //System.out.println(maxRating);

    }

    /**
     * Обработка хода игрока (человек)
     */
    private static void humanTurn() {
        int x, y;

        do {

            while (true) {
                System.out.printf("Введите координату хода X (от 1 до %d): ",fieldSizeX);
                if (scanner.hasNextInt()) {
                    x = scanner.nextInt() - 1;
                    scanner.nextLine();
                    break;
                } else {
                    System.out.println("Некорректное число, повторите попытку ввода.");
                    scanner.nextLine();
                }
            }

            while (true) {
                System.out.printf("Введите координату хода Y (от 1 до %d): ",fieldSizeY);
                if (scanner.hasNextInt()) {
                    y = scanner.nextInt() - 1;
                    scanner.nextLine();
                    break;
                } else {
                    System.out.println("Некорректное число, повторите попытку ввода.");
                    scanner.nextLine();
                }
            }
        } while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[x][y] = DOT_HUMAN;
    }

    /**
     * Проверка, ячейка является пустой (DOT_EMPTY)
     * 
     * @param x
     * @param y
     * @return
     */
    private static boolean isCellEmpty(int x, int y) {
        return field[x][y] == DOT_EMPTY;
    }

    /**
     * Проверка корректности ввода
     * (координаты хода не должны превышать размерность игрового поля)
     * 
     * @param x
     * @param y
     * @return
     */
    private static boolean isCellValid(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    /**
     * Обработка хода компьютера
     */
    private static void aiTurn() {
        maxRating = 0;
        int turnX = -1;
        int turnY = -1;
        long rating;
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                if (isCellEmpty(x, y)) {
                    rating = GetRating(x, y);
                    if (rating >= maxRating){
                        maxRating = rating;
                        turnX = x;
                        turnY = y;
                    }
                }
            }
        } 
        field[turnX][turnY] = DOT_AI;
    }

    public static long pow(int value, int powValue) {
        return (long) Math.pow(value, powValue);
    }

    private static long GetRating(int x, int y){
        long rating = 0;
        rating = pow(4, getMainDiag(DOT_AI, x, y)*2+1) 
        + pow(4, getAddDiag(DOT_AI, x, y)*2+1)
        + pow(4, getVert(DOT_AI, x, y)*2+1)
        + pow(4, getHoriz(DOT_AI, x, y)*2+1)
        + pow(4, getMainDiag(DOT_HUMAN, x, y)*2)
        + pow(4, getAddDiag(DOT_HUMAN, x, y)*2)
        + pow(4, getVert(DOT_HUMAN, x, y)*2)
        + pow(4, getHoriz(DOT_HUMAN, x, y)*2);
        return rating;
    }

    private static int getMainDiag(char c, int x, int y){
        int result = 0;
        int xCur = x-1;
        int yCur = y-1;
        while (xCur >= 0 && xCur > x-winCount && yCur>=0){
            if (field[xCur][yCur] == c) {
                xCur--;
                yCur--;
                result++;
            } else 
                break;
        }
        xCur = x+1;
        yCur = y+1;
        while (xCur < fieldSizeX && xCur < x+winCount && yCur < fieldSizeY){
            if (field[xCur][yCur] == c) {
                xCur++;
                yCur++;
                result++;
            } else 
                break;
        }
        return result > winCount ? winCount : result;
    }

    private static int getAddDiag(char c, int x, int y){
        int result = 0;
        int xCur = x-1;
        int yCur = y+1;
        while (xCur >= 0 && xCur > x-winCount && yCur < fieldSizeY){
            if (field[xCur][yCur] == c) {
                xCur--;
                yCur++;
                result++;
            } else 
                break;
        }
        xCur = x+1;
        yCur = y-1;
        while (xCur < fieldSizeX && xCur < x+winCount && yCur >= 0){
            if (field[xCur][yCur] == c) {
                xCur++;
                yCur--;
                result++;
            } else 
                break;
        }
        return result > winCount ? winCount : result;
    }
    private static int getVert(char c, int x, int y){
        int result = 0;
        int xCur = x-1;
        int yCur = y;
        while (xCur >= 0 && xCur > x-winCount){
            if (field[xCur][yCur] == c) {
                xCur--;
                result++;
            } else 
                break;
        }
        xCur = x+1;
        yCur = y;
        while (xCur < fieldSizeX && xCur < x+winCount){
            if (field[xCur][yCur] == c) {
                xCur++;
                result++;
            } else 
                break;
        }
        return result > winCount ? winCount : result;
    }

    private static int getHoriz(char c, int x, int y){
        int result = 0;
        int xCur = x;
        int yCur = y-1;
        while (yCur > y-winCount && yCur>=0){
            if (field[xCur][yCur] == c) {
                yCur--;
                result++;
            } else 
                break;
        }
        xCur = x;
        yCur = y+1;
        while (yCur < y+winCount && yCur < fieldSizeY){
            if (field[xCur][yCur] == c) {
                yCur++;
                result++;
            } else 
                break;
        }
        return result > winCount ? winCount : result;
    }

    /**
     * Проверка состояния игры
     * 
     * @param c фишка игрока
     * @param s победный слоган
     * @return
     */
    private static boolean checkGameState(char c, String s) {
        if (checkWin(c)) {
            System.out.println(s);
            return true;
        }
        if (checkDraw()) {
            System.out.println("Ничья!");
            return true;
        }

        return false; // Игра продолжается
    }

    /**
     * Проверка победы
     * @param c
     * @return
     */
    private static boolean checkWin(char c){
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                if (checkWinDown(c, x, y) || checkWinRight(c, x, y) || checkWinRightDown(c, x, y) || checkWinRightUp(c, x, y)) return true;
            }   
        } 
        return false;
    }
    private static boolean checkWinDown(char c, int x, int y) {
        if (x + winCount > fieldSizeX)
            return false;
        else { 
            for (int i = 0; i < winCount; i++) {
               if (field[x+i][y] != c) return false;
            } 
        }
        return true;
    }

    private static boolean checkWinRight(char c, int x, int y) {
        if (y + winCount > fieldSizeY)
            return false;
        else { 
            for (int i = 0; i < winCount; i++) {
               if (field[x][y+i] != c) return false;
            } 
        }
        return true;
    }

    private static boolean checkWinRightDown(char c, int x, int y) {
        if (y + winCount > fieldSizeY || x+winCount > fieldSizeX)
            return false;
        else { 
            for (int i = 0; i < winCount; i++) {
               if (field[x+i][y+i] != c) return false;
            } 
        }
        return true;
    }

    private static boolean checkWinRightUp(char c, int x, int y) {
        if (y + winCount > fieldSizeY || x+winCount > fieldSizeX)
            return false;
        else { 
            for (int i = 0; i < winCount; i++) {
               if (field[x+winCount-i-1][y+i] != c) return false;
            } 
        }
        return true;
    }


    /**
     * Проверка на ничью
     * 
     * @return
     */
    private static boolean checkDraw() {
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                if (isCellEmpty(x, y))
                    return false;
            }
        }
        return true;
    }

}
