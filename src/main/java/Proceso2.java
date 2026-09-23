import java.io.IOException;
import java.io.IOException;

public class Proceso2 {
    public static void main(String[] args) throws IOException {
        // código para ejecutar cmd (Power Shell) y cerrarlo.

        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "dir");
        // /c sirve para cerrar un proceso al terminar. dir es lo que debe ejecutar en power shell

        Process p = pb.start();

        //ProcessBuilder pb = new ProcessBuilder("ls","-la");
        //Process p = pb.start();
    }
}
