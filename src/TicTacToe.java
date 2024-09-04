import java.io.IOException;
import java.util.*;

public class TicTacToe
{
    String[] marks;
    boolean yourTurn;
    String characterCode;

    Scanner sc = new Scanner(System.in);
    Random rand = new Random();

    public TicTacToe() throws IOException, InterruptedException {
        initGame();
    }

    public void initGame() throws IOException, InterruptedException {
        marks = new String[]{" ", " ", " ", " ", " ", " ", " ", " ", " "};
        yourTurn = false;
        Helper.clearScreen();
        System.out.println("\n" +
                " _____  _  ____     _____  ____  ____     _____  ____  _____\n" +
                "/__ __\\/ \\/   _\\   /__ __\\/  _ \\/   _\\   /__ __\\/  _ \\/  __/\n" +
                "  / \\  | ||  / _____ / \\  | / \\||  / _____ / \\  | / \\||  \\  \n" +
                "  | |  | ||  \\_\\____\\| |  | |-|||  \\_\\____\\| |  | \\_/||  /_ \n" +
                "  \\_/  \\_/\\____/     \\_/  \\_/ \\|\\____/     \\_/  \\____/\\____\\\n" +
                "                                                            \n");
        printBoard();
    }

    public void printBoard() throws IOException, InterruptedException {
        yourTurn = !yourTurn;
        System.out.println();
        for(int i = 0; i < marks.length; i++) {
            if(marks[i].equals(" ")) {
                System.out.print(Helper.colorText("black", "blue") + (i + 1));
            }
            else
            {
                System.out.print(Helper.colorText("black", "white") + marks[i]);
            }
            if ((i + 1) % 3 == 0) {
                System.out.println();
            }
            else {
                System.out.print(Helper.colorText("black", "white") + "|");
            }
        }
        if(yourTurn) {
            System.out.println(Helper.colorText("black", "white") + "\nIt's your turn.");
        }
        else {
            System.out.println(Helper.colorText("black", "white") + "\nIt's the Bot's turn.");
        }
        gameLoop();
    }

    public void botPlace() throws IOException, InterruptedException {
        int x = rand.nextInt(0, 9);
        if(!marks[x].equals(" ")) {
            botPlace();
        }
        marks[x] = "O";
        Helper.clearScreen();
        printBoard();
    }

    public static String checkWinner(String[] board) {
        // All possible winning combinations
        int[][] winningCombinations = {
                {0, 1, 2}, // Row 1
                {3, 4, 5}, // Row 2
                {6, 7, 8}, // Row 3
                {0, 3, 6}, // Column 1
                {1, 4, 7}, // Column 2
                {2, 5, 8}, // Column 3
                {0, 4, 8}, // Diagonal 1
                {2, 4, 6}  // Diagonal 2
        };

        // Check each winning combination
        for (int[] combination : winningCombinations) {
            String first = board[combination[0]];
            String second = board[combination[1]];
            String third = board[combination[2]];

            // Check if all three positions are the same and not a free space
            if (!first.equals(" ") && first.equals(second) && first.equals(third)) {
                return first; // Return "X" or "O" as the winner
            }
        }

        // Check for a tie (no free spaces left)
        boolean tie = true;
        for (String cell : board) {
            if (cell.equals(" ")) {
                tie = false;
                break;
            }
        }

        if (tie) {
            return "Tie";
        }

        // No winner yet
        return "None";
    }

    public boolean contains(int[] arr, int key) {
        for(Integer i: arr) {
            if(i == key) {
                return true;
            }
        }
        return false;
    }

    public void gameLoop() throws IOException, InterruptedException {
        switch (checkWinner(marks)) {
            case "None":
                break;

            case "Tie":
                System.out.println("It's a tie!");
                System.out.println("\nDo you want to play again?");
                System.out.println("[1]: Yes");
                System.out.println("[2]: No");
                try {
                    switch (sc.next())
                    {
                        case "1":
                            initGame();
                            break;

                        case "2":
                            GameSelector g = new GameSelector();
                            break;

                        default:
                            System.out.println("Invalid option");
                            gameLoop();
                            break;
                    }
                } catch(InputMismatchException e) {
                    gameLoop();
                }
                break;

            case "X":
                System.out.println("You Won!");
                System.out.println("\nDo you want to play again?");
                System.out.println("[1]: Yes");
                System.out.println("[2]: No");
                try {
                    switch (sc.next())
                    {
                        case "1":
                            initGame();
                            break;

                        case "2":
                            GameSelector g = new GameSelector();
                            break;

                        default:
                            System.out.println("Invalid option");
                            gameLoop();
                            break;
                    }
                } catch(InputMismatchException e) {
                    gameLoop();
                }
                break;

            case "O":
                System.out.println("You Lost!");
                System.out.println("\nDo you want to play again?");
                System.out.println("[1]: Yes");
                System.out.println("[2]: No");
                try {
                    switch (sc.next())
                    {
                        case "1":
                            initGame();
                            break;

                        case "2":
                            GameSelector g = new GameSelector();
                            break;

                        default:
                            System.out.println("Invalid option");
                            gameLoop();
                            break;
                    }
                } catch(InputMismatchException e) {
                    gameLoop();
                }
                break;
        }
        if(!yourTurn) {
            botPlace();
        }
        else {
            int choice = sc.nextInt();
            if(contains(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, choice)) {
                if(marks[choice - 1].equals(" ")) {
                    marks[choice - 1] = "X";
                    Helper.clearScreen();
                    printBoard();
                }
            }
            else {
                System.out.println("Invalid position.");
                gameLoop();
            }
        }
    }
}