import java.io.IOException;
import java.util.Scanner;

public class GameSelector
{
    Scanner sc = new Scanner(System.in);

    public GameSelector() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
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
        System.out.println("[1] 2048\n[2] Minesweeper\n[3] Hangman");
        switch(sc.nextInt()) {
            case 1:
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                TwentyFourtyEight twentyFourtyEight = new TwentyFourtyEight();
                break;

            case 2:
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                Minesweeper minesweeper = new Minesweeper();
                break;

            case 3:
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                Hangman hangman = new Hangman();
                break;

            default:
                System.out.println("Please select a valid option.");
                initMenu();
                break;
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        GameSelector g = new GameSelector();
    }
}
