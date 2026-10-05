package E4Procesos;

import java.io.*;

public class Padre {

    public static void main(String[] args) {

        try {
            // Crear el proceso hijo
            ProcessBuilder pb = new ProcessBuilder(
                    "java",
                    "-cp",
                    "target/classes",
                    "E3Procesos.Hijo"
            );

            Process proceso = pb.start();

            // Enviar el número 10 al hijo
            OutputStream os = proceso.getOutputStream();
            PrintWriter escritor = new PrintWriter(os);

            escritor.println(10);
            escritor.flush();

            // Leer el resultado que devuelve el hijo
            InputStream is = proceso.getInputStream();
            BufferedReader lector = new BufferedReader(
                    new InputStreamReader(is)
            );

            String resultado = lector.readLine();

            System.out.println("Resultado recibido del hijo: " + resultado);

            // Esperar a que termine el proceso
            proceso.waitFor();

            escritor.close();
            lector.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());

        } catch (InterruptedException e) {
            System.out.println("El proceso fue interrumpido.");
            Thread.currentThread().interrupt();
        }
    }
}