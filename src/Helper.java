import java.io.IOException;

public class Helper
{
    public static String os = System.getProperty("os.name");

    public Helper()
    {

    }

    public static void clearScreen() throws IOException, InterruptedException {
        if(os.contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
        else {
            //new ProcessBuilder("/bin/bash", "-c", "clear").inheritIO().start().waitFor();
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

    public static String colorText(String background, String foreground) {
        String bg = "";
        String fg = "";

        bg = switch (background.toLowerCase()) {
            case "black" -> "40";
            case "red" -> "41";
            case "green" -> "42";
            case "yellow" -> "43";
            case "blue" -> "44";
            case "purple" -> "45";
            case "cyan" -> "46";
            case "white" -> "47";
            default -> bg;
        };

        fg = switch (foreground.toLowerCase()) {
            case "black" -> "30";
            case "red" -> "31";
            case "green" -> "32";
            case "yellow" -> "33";
            case "blue" -> "34";
            case "purple" -> "35";
            case "cyan" -> "36";
            case "white" -> "37";
            default -> fg;
        };

        return "\033[" + bg + ";" + fg + ";1m";
    }
}
