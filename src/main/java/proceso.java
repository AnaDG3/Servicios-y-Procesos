import java.io.IOException;
public class proceso {
    public static void main(String[] args) throws IOException {
        // código para ejecutar el block de notas

        ProcessBuilder pb = new ProcessBuilder("notepad");
        Process p = pb.start();

        //ProcessBuilder pb = new ProcessBuilder("ls","-la");
        //Process p = pb.start();
    }
}