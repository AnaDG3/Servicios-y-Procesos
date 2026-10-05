import java.io.IOException;

public class E1Procesos {
    public static void main(String[] args) throws IOException {
        // código para ejecutar el block de notas
        try {
            // Crear el ProcessBuilder
            ProcessBuilder pb = new ProcessBuilder("notepad.exe");

            // Lanzar el proceso
            Process proceso = pb.start();

            // Mostrar el PID
            System.out.println("PID del proceso: " + proceso.pid());

            // Esperar a que termine
            int codigoSalida = proceso.waitFor();

            // Mostrar el código de salida
            System.out.println("Código de salida: " + codigoSalida);

        } catch (IOException e) {
            System.out.println("Error al lanzar el proceso: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("El proceso fue interrumpido.");
            Thread.currentThread().interrupt();
        }
    }
}