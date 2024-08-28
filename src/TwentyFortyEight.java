import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.io.IOException;

class TwentyFourtyEight
{
    Integer[][] gameBoard = new Integer[4][4];
    int emptySpaces = 16;
    int score = 0;
    boolean gameWon = false;
    boolean endless = false;
    Random rand = new Random();
    Scanner sc = new Scanner(System.in);

    public TwentyFourtyEight() throws IOException, InterruptedException {
        initGame();
    }

    private void initGame() throws IOException, InterruptedException {
        System.out.println("\n" +
                " .----------------.  .----------------.  .----------------.  .----------------. \n" +
                "| .--------------. || .--------------. || .--------------. || .--------------. |\n" +
                "| |    _____     | || |     ____     | || |   _    _     | || |     ____     | |\n" +
                "| |   / ___ `.   | || |   .'    '.   | || |  | |  | |    | || |   .' __ '.   | |\n" +
                "| |  |_/___) |   | || |  |  .--.  |  | || |  | |__| |_   | || |   | (__) |   | |\n" +
                "| |   .'____.'   | || |  | |    | |  | || |  |____   _|  | || |   .`____'.   | |\n" +
                "| |  / /____     | || |  |  `--'  |  | || |      _| |_   | || |  | (____) |  | |\n" +
                "| |  |_______|   | || |   '.____.'   | || |     |_____|  | || |  `.______.'  | |\n" +
                "| |              | || |              | || |              | || |              | |\n" +
                "| '--------------' || '--------------' || '--------------' || '--------------' |\n" +
                " '----------------'  '----------------'  '----------------'  '----------------' \n");
        System.out.println("Do you want to play normal(n) or endless(e) mode?");
        switch(sc.next())
        {
            case "n":
                endless = false;
                break;

            case "e":
                endless = true;
                break;

            default:
                System.out.println("Answer the damn question!");
                initGame();
                break;
        }
        generateNewNumber();
        printBoard();
        gameLoop();
    }

    private void generateNewNumber() {
        int row = rand.nextInt(4);
        int col = rand.nextInt(4);

        if(gameBoard[row][col] == null && emptySpaces > 0)
        {
            emptySpaces--;
            int rando = rand.nextInt(5);
            if(rando == 1 || rando == 2) {
                gameBoard[row][col] = 2;
            }
            else {
                gameBoard[row][col] = 4;
            }
        }
        else if(emptySpaces > 0){
            generateNewNumber();
        }
        else {
            return;
        }
    }

    private void printBoard() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

        for(int i = 0; i < gameBoard.length; i++)
        {
            if(i > 0){
                System.out.println("---------------------");
            }
            for(int j = 0; j < gameBoard[i].length; j++)
            {
                if(gameBoard[i][j] == null)
                {
                    System.out.print("|    ");
                }
                else
                {
                    switch(gameBoard[i][j].toString().length())
                    {
                        case 1:
                            System.out.print("|   " + gameBoard[i][j]);
                            break;

                        case 2:
                            System.out.print("|  " + gameBoard[i][j]);
                            break;

                        case 3:
                            System.out.print("| " + gameBoard[i][j]);
                            break;

                        case 4:
                            System.out.print("|" + gameBoard[i][j]);
                            break;
                    }
                }
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("Score:" + score);
    }

    private void checkWin() throws IOException, InterruptedException {
        for (Integer[] integers : gameBoard) {
            for (Integer integer : integers) {
                if (integer != null && !endless && integer == 2048) {
                    gameWon = true;
                    System.out.println("You won!");
                    System.out.println("Do you want to play again?");
                    System.out.println("[1] Yes");
                    System.out.println("[2] No");
                    try {
                        switch (sc.nextInt()) {
                            case 1:
                                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                                initGame();
                                break;

                            case 2:
                                GameSelector g = new GameSelector();
                                break;

                            default:
                                System.out.println("Invalid option.");
                                gameLoop();
                        }
                    } catch(InputMismatchException e) {
                        checkWin();
                    }
                }
            }
        }
    }

    private void checkLose() throws IOException, InterruptedException {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if(gameBoard[i][j] == null) {
                    return;
                }
                if(i != 0 && Objects.equals(gameBoard[i][j], gameBoard[i - 1][j])) {
                    return;
                }
                if(i != 3 && Objects.equals(gameBoard[i][j], gameBoard[i + 1][j])) {
                    return;
                }
                if(j != 0 && Objects.equals(gameBoard[i][j], gameBoard[i][j - 1])) {
                    return;
                }
                if(j != 3 && Objects.equals(gameBoard[i][j], gameBoard[i][j + 1])) {
                    return;
                }
            }
        }
        gameWon = true; //fuck it, im using the same var, it only matters what's on screen anyway
        System.out.println("You lost!");
        System.out.println("Do you want to play again?");
        System.out.println("[1] Yes");
        System.out.println("[2] No");
        try {
            switch (sc.nextInt()) {
                case 1:
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    initGame();
                    break;

                case 2:
                    GameSelector g = new GameSelector();
                    break;

                default:
                    System.out.println("Invalid option.");
                    gameLoop();
            }
        } catch(InputMismatchException e) {
            checkLose();
        }
    }

    private void compress(String dir)
    {
        int edge = 0;
        int iPlus = 0;
        int jPlus = 0;

        switch (dir) {
            case "w" -> {
                edge = 0;
                iPlus = -1;
                jPlus = 0;
            }
            case "s" -> {
                edge = 3;
                iPlus = 1;
                jPlus = 0;
            }
            case "a" -> {
                edge = 0;
                iPlus = 0;
                jPlus = -1;
            }
            case "d" -> {
                edge = 3;
                iPlus = 0;
                jPlus = 1;
            }
        }

        if(dir.equals("w") || dir.equals("s"))
        {
            for (int i = 0; i < gameBoard.length; i++) {
                for (int j = 0; j < gameBoard[i].length; j++) {
                    if (gameBoard[i][j] != null && i != edge) {
                        int tempI = i;
                        if(dir.equals("w")) {
                            while (tempI > edge && gameBoard[tempI + iPlus][j + jPlus] == null) {
                                gameBoard[tempI + iPlus][j + jPlus] = gameBoard[tempI][j];
                                gameBoard[tempI][j] = null;
                                tempI--;
                            }
                        }
                        else
                        {
                            while (tempI < edge && gameBoard[tempI + iPlus][j + jPlus] == null) {
                                gameBoard[tempI + iPlus][j + jPlus] = gameBoard[tempI][j];
                                gameBoard[tempI][j] = null;
                                tempI++;
                            }
                        }
                    }
                }
            }
            return;
        }

        if(dir.equals("a") || dir.equals("d"))
        {
            for (int i = 0; i < gameBoard.length; i++) {
                for (int j = 0; j < gameBoard[i].length; j++) {
                    if (gameBoard[i][j] != null && j != edge) {
                        int tempJ = j;
                        if(dir.equals("a"))
                        {
                            while (tempJ > edge && gameBoard[i + iPlus][tempJ + jPlus] == null) {
                                gameBoard[i + iPlus][tempJ + jPlus] = gameBoard[i][tempJ];
                                gameBoard[i][tempJ] = null;
                                tempJ--;
                            }
                        }
                        else
                        {
                            while (tempJ < edge && gameBoard[i + iPlus][tempJ + jPlus] == null) {
                                gameBoard[i + iPlus][tempJ + jPlus] = gameBoard[i][tempJ];
                                gameBoard[i][tempJ] = null;
                                tempJ++;
                            }
                        }
                    }
                }
            }
        }
    }
    private void merge(String dir)
    {
        int edge = 0;
        int iPlus = 0;
        int jPlus = 0;

        switch(dir)
        {
            case "w":
                edge = 0;
                iPlus = -1;
                jPlus = 0;
            case "s":
                edge = 3;
                iPlus = 1;
                jPlus = 0;
                for (int i = 0; i < gameBoard.length; i++) {
                    for (int j = 0; j < gameBoard[i].length; j++) {
                        if(gameBoard[i][j] != null && i != edge && gameBoard[i + iPlus][j + jPlus] != null && gameBoard[i + iPlus][j + jPlus].equals(gameBoard[i][j])) {
                            gameBoard[i + iPlus][j + jPlus] = gameBoard[i][j] * 2;
                            score += gameBoard[i][j] * 2;
                            gameBoard[i][j] = null;
                            emptySpaces++;
                        }
                    }
                }
                break;

            case "a":
                edge = 0;
                iPlus = 0;
                jPlus = -1;
            case "d":
                edge = 3;
                iPlus = 0;
                jPlus = 1;
                for (int i = 0; i < gameBoard.length; i++) {
                    for (int j = 0; j < gameBoard[i].length; j++) {
                        if(gameBoard[i][j] != null && j != edge && gameBoard[i + iPlus][j + jPlus] != null && gameBoard[i + iPlus][j + jPlus].equals(gameBoard[i][j])) {
                            gameBoard[i + iPlus][j + jPlus] = gameBoard[i][j] * 2;
                            score += gameBoard[i][j] * 2;
                            gameBoard[i][j] = null;
                            emptySpaces++;
                        }
                    }
                }
                break;
        }
    }

    private void gameLoop() throws IOException, InterruptedException {
        while(!gameWon)
        {
            checkWin();
            checkLose();
            switch(sc.next().charAt(0))
            {
                case 'w':
                    compress("w");
                    merge("w");
                    compress("w");
                    generateNewNumber();
                    printBoard();
                    break;

                case 'a':
                    compress(("a"));
                    merge("a");
                    compress(("a"));
                    generateNewNumber();
                    printBoard();
                    break;

                case 's':
                    compress("s");
                    merge("s");
                    compress("s");
                    generateNewNumber();
                    printBoard();
                    break;

                case 'd':
                    compress("d");
                    merge("d");
                    compress("d");
                    generateNewNumber();
                    printBoard();
                    break;

                default:
                    gameLoop();
                    break;
            }
        }
    }

    /*public static void main(String[] args) throws IOException, InterruptedException {
        TwentyFourtyEight game = new TwentyFourtyEight();
    }*/
}
