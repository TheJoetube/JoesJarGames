import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameSelector
{
    public static String os = System.getProperty("os.name");
    Scanner sc = new Scanner(System.in);

    public GameSelector() throws IOException, InterruptedException {
        clearScreen();
        initMenu();
    }

    public static void clearScreen() throws IOException, InterruptedException {
        if(os.contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
        else {
            new ProcessBuilder("/bin/bash", "-c", "clear").inheritIO().start().waitFor();
        }
    }

    public void initMenu() throws IOException, InterruptedException {
        System.out.println("\n" +
                "      ,--.-,   _,.---._        ,----.    ,-,--.              ,--.-,  ,---.                               _,---.   ,---.             ___      ,----.    ,-,--.  \n" +
                "     |==' -| ,-.' , -  `.   ,-.--` , \\ ,-.'-  _\\            |==' -|.--.'  \\      .-.,.---.           _.='.'-,  \\.--.'  \\     .-._ .'=.'\\  ,-.--` , \\ ,-.'-  _\\ \n" +
                "     |==|- |/==/_,  ,  - \\ |==|-  _.-`/==/_ ,_.'            |==|- |\\==\\-/\\ \\    /==/  `   \\         /==.'-     /\\==\\-/\\ \\   /==/ \\|==|  ||==|-  _.-`/==/_ ,_.' \n" +
                "   __|==|, |==|   .=.     ||==|   `.-.\\==\\  \\             __|==|, |/==/-|_\\ |  |==|-, .=., |       /==/ -   .-' /==/-|_\\ |  |==|,|  / - ||==|   `.-.\\==\\  \\    \n" +
                ",--.-'\\=|- |==|_ : ;=:  - /==/_ ,    / \\==\\ -\\         ,--.-'\\=|- |\\==\\,   - \\ |==|   '='  /       |==|_   /_,-.\\==\\,   - \\ |==|  \\/  , /==/_ ,    / \\==\\ -\\   \n" +
                "|==|- |=/ ,|==| , '='     |==|    .-'  _\\==\\ ,\\        |==|- |=/ ,|/==/ -   ,| |==|- ,   .'        |==|  , \\_.' )==/ -   ,| |==|- ,   _ |==|    .-'  _\\==\\ ,\\  \n" +
                "|==|. /=| -|\\==\\ -    ,_ /|==|_  ,`-._/==/\\/ _ |       |==|. /=| -/==/-  /\\ - \\|==|_  . ,'.        \\==\\-  ,    (==/-  /\\ - \\|==| _ /\\   |==|_  ,`-._/==/\\/ _ | \n" +
                "\\==\\, `-' /  '.='. -   .' /==/ ,     /\\==\\ - , /       \\==\\, `-' /\\==\\ _.\\=\\.-'/==/  /\\ ,  )        /==/ _  ,  |==\\ _.\\=\\.-'/==/  / / , /==/ ,     /\\==\\ - , / \n" +
                " `--`----'     `--`--''   `--`-----``  `--`---'         `--`----'  `--`        `--`-`--`--'         `--`------' `--`        `--`./  `--``--`-----``  `--`---'  \n");
        System.out.println("Choose a game:\n");
        System.out.println("[1] 2048\n[2] Minesweeper\n[3] Hangman\n[4] Tic-Tac-Toe");
        try {
            switch(sc.nextInt()) {
                case 1:
                    clearScreen();
                    TwentyFortyEight twentyFortyEight = new TwentyFortyEight();
                    break;

                case 2:
                    clearScreen();
                    Minesweeper minesweeper = new Minesweeper();
                    break;

                case 3:
                    clearScreen();
                    Hangman hangman = new Hangman();
                    break;

                case 4:
                    clearScreen();
                    TicTacToe ticTacToe = new TicTacToe();
                    break;

                default:
                    System.out.println("Please select a valid option.");
                    initMenu();
                    break;
            }
        } catch(InputMismatchException e) {
            initMenu();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        GameSelector g = new GameSelector();
    }
}
