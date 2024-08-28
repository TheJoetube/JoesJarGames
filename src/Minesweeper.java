import java.awt.event.KeyEvent;
import java.util.Scanner;
import java.io.IOException;
import java.util.Random;

public class Minesweeper
{
    private class Field
    {
        int bombCount = 0;
        boolean isBomb = false;
        boolean uncovered = false;
        boolean flagged = false;

        public Field()
        {

        }

        public void incBombCount()
        {
            bombCount++;
        }
    }

    int size = 0;
    int bombCount = 0;
    int[] cursorPos = new int[]{0,0};
    boolean gameWon = false;
    boolean gameLost = false;
    boolean hardMode = false;
    String characterCode;
    Field[][] minefield;
    Scanner sc = new Scanner(System.in);
    Random rand = new Random();
    public Minesweeper() throws IOException, InterruptedException {
        gameModeInit();
    }

    public void gameModeInit() throws IOException, InterruptedException {
        size = 0;
        bombCount = 0;
        cursorPos = new int[]{0,0};
        gameWon = false;
        gameLost = false;
        hardMode = false;
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        System.out.println("\n" +
                "                                                                                                                           \n" +
                "          ____                                                                                                             \n" +
                "        ,'  , `.                                                                                                           \n" +
                "     ,-+-,.' _ |  ,--,                                                                       ,-.----.                      \n" +
                "  ,-+-. ;   , ||,--.'|         ,---,                                .---.                    \\    /  \\             __  ,-. \n" +
                " ,--.'|'   |  ;||  |,      ,-+-. /  |            .--.--.           /. ./|                    |   :    |          ,' ,'/ /| \n" +
                "|   |  ,', |  ':`--'_     ,--.'|'   |   ,---.   /  /    '       .-'-. ' |   ,---.     ,---.  |   | .\\ :   ,---.  '  | |' | \n" +
                "|   | /  | |  ||,' ,'|   |   |  ,\"' |  /     \\ |  :  /`./      /___/ \\: |  /     \\   /     \\ .   : |: |  /     \\ |  |   ,' \n" +
                "'   | :  | :  |,'  | |   |   | /  | | /    /  ||  :  ;_     .-'.. '   ' . /    /  | /    /  ||   |  \\ : /    /  |'  :  /   \n" +
                ";   . |  ; |--' |  | :   |   | |  | |.    ' / | \\  \\    `. /___/ \\:     '.    ' / |.    ' / ||   : .  |.    ' / ||  | '    \n" +
                "|   : |  | ,    '  : |__ |   | |  |/ '   ;   /|  `----.   \\.   \\  ' .\\   '   ;   /|'   ;   /|:     |`-''   ;   /|;  : |    \n" +
                "|   : '  |/     |  | '.'||   | |--'  '   |  / | /  /`--'  / \\   \\   ' \\ |'   |  / |'   |  / |:   : :   '   |  / ||  , ;    \n" +
                ";   | |`-'      ;  :    ;|   |/      |   :    |'--'.     /   \\   \\  |--\" |   :    ||   :    ||   | :   |   :    | ---'     \n" +
                "|   ;/          |  ,   / '---'        \\   \\  /   `--'---'     \\   \\ |     \\   \\  /  \\   \\  / `---'.|    \\   \\  /           \n" +
                "'---'            ---`-'                `----'                  '---\"       `----'    `----'    `---`     `----'            \n" +
                "                                                                                                                           \n");
        System.out.println("Pick a board size:");
        System.out.println("[1]: 10x10 Board");
        System.out.println("[2]: 20x20 Board");
        System.out.println("[3]: 30x30 Board");
        switch (sc.next())
        {
            case "1":
                size = 10;
                break;

            case "2":
                size = 20;
                break;

            case "3":
                size = 30;
                break;

            default:
                System.out.println("Invalid option");
                gameModeInit();
                break;
        }
        System.out.println("\nPick a difficulty:");
        System.out.println("[1]: Normal");
        System.out.println("[2]: Hard");
        switch (sc.next())
        {
            case "1":
                bombSelector();
                break;

            case "2":
                hardMode = true;
                bombSelector();
                break;


            default:
                System.out.println("Invalid option");
                gameModeInit();
                break;
        }
    }

    public void bombSelector() throws IOException, InterruptedException {
        System.out.println("\nPlease pick a bomb count (1," + size * 2 + ")");
        int c = sc.nextInt();
        if(c < 1 || c > (size * 2))
        {
            System.out.println("Invalid Bomb Count");
            bombSelector();
        }
        bombCount = c;
        initGame();
    }
    public void initGame() throws IOException, InterruptedException {
        minefield = new Field[size][size];
        fillBoard();
        for(int i = 0; i < bombCount; i++) {
            placeMine();
        }
        calculateTiles();
        printBoard();
        gameLoop();
    }

    public void fillBoard()
    {
        for(int i = 0; i < minefield.length; i++)
        {
            for(int j = 0; j < minefield[i].length; j++)
            {
                minefield[i][j] = new Field();
            }
        }
    }

    public void placeMine()
    {
        int x = rand.nextInt(minefield.length);
        int y = rand.nextInt(minefield[0].length);
        if(!minefield[x][y].isBomb) {
               minefield[x][y].isBomb = true;
        }
        else {
            placeMine();
        }
    }

    public void calculateTiles()
    {
        for (int i = 0; i < minefield.length; i++) {
            for (int j = 0; j < minefield[i].length; j++) {
                if (minefield[i][j].isBomb) {
                    calculateMineCount(i, j);
                }
            }
        }
    }

    public void calculateMineCount(int x, int y) {
        for (int i = Math.max(0, x - 1); i <= Math.min(minefield.length - 1, x + 1); i++) {
            for (int j = Math.max(0, y - 1); j <= Math.min(minefield[0].length - 1, y + 1); j++) {
                minefield[i][j].incBombCount();
            }
        }
    }

    public void printBoard() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        gameWon = true;
        for (Field[] fields : minefield) {
            for (Field field : fields) {
                if(field.uncovered)
                {
                    if (field.bombCount == 0 && !field.isBomb) {
                        characterCode = "\033[40;37;1m";
                        System.out.print(characterCode +"|");
                        if(field == minefield[cursorPos[1]][cursorPos[0]]) {
                            characterCode = "\033[47;30;1m";
                        }
                        System.out.print(characterCode + " ");
                    } else if (field.isBomb) {
                        characterCode = "\033[40;37;1m";
                        System.out.print(characterCode + "|");
                        characterCode = "\033[41;30;1m";
                        System.out.print(characterCode + "*");
                    } else {
                        characterCode = "\033[40;37;1m";
                        System.out.print(characterCode + "|");
                        switch(field.bombCount)
                        {
                            case 1:
                                characterCode = "\033[40;34;1m";
                                break;

                            case 2:
                                characterCode = "\033[40;32;1m";
                                break;

                            case 3 | 4 | 5 | 6 | 7 | 8:
                                characterCode = "\033[40;31;1m";
                                break;
                        }
                        if(field == minefield[cursorPos[1]][cursorPos[0]]) {
                            characterCode = "\033[47;30;1m";
                        }
                        System.out.print(characterCode + field.bombCount);
                    }
                }
                else if(field.flagged) {
                    characterCode = "\033[40;37;1m";
                    System.out.print(characterCode + "|");
                    if(field == minefield[cursorPos[1]][cursorPos[0]]) {
                        characterCode = "\033[47;30;1m";
                    }
                    System.out.print(characterCode + "⚑");
                }
                else
                {
                    characterCode = "\033[40;37;1m";
                    System.out.print(characterCode + "|");
                    if(field == minefield[cursorPos[1]][cursorPos[0]]) {
                        characterCode = "\033[47;30;1m";
                    }
                    System.out.print(characterCode + "■");
                }
                if(field.isBomb && !field.flagged || !field.isBomb && field.flagged)
                {
                    gameWon = false;
                }
            }
            characterCode = "\033[40;37;1m";
            System.out.print(characterCode + "|");
            System.out.println();
        }
        //System.out.println(cursorPos[0] + "|" + cursorPos[1]);
        //getBombInfo();
    }

    public void getBombInfo()
    {
        System.out.println("Bombs:");
        for (int i = 0; i < minefield.length; i++) {
            for (int j = 0; j < minefield[i].length; j++) {
                if (minefield[i][j].isBomb) {
                    System.out.println(j + "|" + i);
                }
            }
        }
    }

    public void uncover() throws IOException, InterruptedException {
        if(minefield[cursorPos[1]][cursorPos[0]].isBomb)
        {
            gameLost = true;
        }
        if(minefield[cursorPos[1]][cursorPos[0]].bombCount == 0 && !hardMode) {
            revealZeros(cursorPos[0],cursorPos[1]);
        }
        else
        {
            minefield[cursorPos[1]][cursorPos[0]].uncovered = true;
        }
        printBoard();
        gameLoop();
    }

    public void revealZeros(int x, int y) {
        if (x < 0 || x > size - 1 || y < 0 || y > size - 1) return; // check for bounds

        if (minefield[y][x].bombCount == 0 && !minefield[y][x].uncovered && !minefield[y][x].isBomb) {
            minefield[y][x].uncovered = true;
            revealZeros( x+1, y );
            revealZeros( x-1, y );
            revealZeros( x, y-1 );
            revealZeros( x, y+1 );
        } else {
            return;
        }
    }

    public void flag() throws IOException, InterruptedException {
        minefield[cursorPos[1]][cursorPos[0]].flagged = !minefield[cursorPos[1]][cursorPos[0]].flagged;
        printBoard();
        gameLoop();
    }

    public void gameLoop() throws IOException, InterruptedException {
        if(gameLost)
        {
            for (Field[] fields : minefield) {
                for (Field field : fields) {
                    field.uncovered = true;
                }
            }
            printBoard();
            System.out.println("You Lost!");
            System.out.println("\nDo you want to play again?");
            System.out.println("[1]: Yes");
            System.out.println("[2]: No");
            switch (sc.next())
            {
                case "1":
                    gameModeInit();
                    break;

                case "2":
                    break;

                default:
                    System.out.println("Invalid option");
                    gameLoop();
                    break;
            }
        }
        else if(gameWon)
        {
            System.out.println("You're winner!");
            System.out.println("\nDo you want to play again?");
            System.out.println("[1]: Yes");
            System.out.println("[2]: No");
            switch (sc.next())
            {
                case "1":
                    gameModeInit();
                    break;

                case "2":
                    break;

                default:
                    System.out.println("Invalid option");
                    gameLoop();
                    break;
            }
        }
        while(!gameWon && !gameLost)
        {
            switch (sc.next())
            {
                case "w":
                    if(cursorPos[1] > 0) {
                        cursorPos[1] -= 1;
                    }
                    printBoard();
                    break;

                case "a":
                    if(cursorPos[0] > 0) {
                        cursorPos[0] -= 1;
                    }
                    printBoard();
                    break;

                case "s":
                    if(cursorPos[1] < size - 1) {
                        cursorPos[1] += 1;
                    }
                    printBoard();
                    break;

                case "d":
                    if(cursorPos[0] < size - 1) {
                        cursorPos[0] += 1;
                    }
                    printBoard();
                    break;

                case "e":
                    uncover();
                    break;

                case "f":
                    flag();
                    break;

                default:
                    printBoard();
                    gameLoop();
            }
        }
    }

    /*public static void main(String[] args) throws IOException, InterruptedException {
        Minesweeper m = new Minesweeper();
    }*/
}
