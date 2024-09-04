import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameSelector
{
    Scanner sc = new Scanner(System.in);

    public GameSelector() throws IOException, InterruptedException {
        Helper.clearScreen();
        initMenu();
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
                    Helper.clearScreen();
                    TwentyFortyEight twentyFortyEight = new TwentyFortyEight();
                    break;

                case 2:
                    Helper.clearScreen();
                    Minesweeper minesweeper = new Minesweeper();
                    break;

                case 3:
                    Helper.clearScreen();
                    Hangman hangman = new Hangman();
                    break;

                case 4:
                    Helper.clearScreen();
                    TicTacToe ticTacToe = new TicTacToe();
                    break;

                default:
                    System.out.println("Please select a valid option.");
                    initMenu();
                    break;
            }
        } catch(InputMismatchException e) {
            //initMenu();
            return;
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        GameSelector g = new GameSelector();
    }
}
