import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class E2Procesos {
     public static void main(String[] args) {
         try {
             ProcessBuilder pb;

             // Detectar el sistema operativo
             String sistema = System.getProperty("os.name").toLowerCase();

             if (sistema.contains("win")) {
                 // Windows
                 pb = new ProcessBuilder("ipconfig");
             } else {
                 // Linux
                 pb = new ProcessBuilder("ip", "addr");
             }

             // Lanzar el proceso
             Process proceso = pb.start();

             // Leer la salida estándar del proceso hijo
             BufferedReader salida = new BufferedReader(
                     new InputStreamReader(proceso.getInputStream())
             );

             String linea;

             System.out.println("Salida del proceso");

             while ((linea = salida.readLine()) != null) {
                 System.out.println(linea);
             }

             // Leer los errores del proceso hijo
             BufferedReader errores = new BufferedReader(
                     new InputStreamReader(proceso.getErrorStream())
             );

             System.out.println("Errores del proceso");

             while ((linea = errores.readLine()) != null) {
                 System.out.println(linea);
             }

             // Esperar a que termine el proceso
             int codigoSalida = proceso.waitFor();

             System.out.println("Código de salida: " + codigoSalida);

             salida.close();
             errores.close();

         } catch (IOException e) {
             System.out.println("Error al ejecutar el comando: " + e.getMessage());

         } catch (InterruptedException e) {
             System.out.println("El proceso fue interrumpido.");
             Thread.currentThread().interrupt();
         }
     }
}
