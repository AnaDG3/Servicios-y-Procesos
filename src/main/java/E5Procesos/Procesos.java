package E5Procesos;

import java.io.IOException;

public class Procesos {

    public static void main(String[] args) {

        try {
            // Crear los tres procesos
            Process procesoA = new ProcessBuilder(
                    "java", "Proceso", "A", "5"
            ).start();

            Process procesoB = new ProcessBuilder(
                    "java", "Proceso", "B", "3"
            ).start();

            Process procesoC = new ProcessBuilder(
                    "java", "Proceso", "C", "2"
            ).start();

            // Esperar a que terminen los tres procesos
            procesoA.waitFor();
            procesoB.waitFor();
            procesoC.waitFor();

            System.out.println("Los tres procesos han terminado.");

        } catch (IOException e) {
            System.out.println("Error al crear los procesos: " + e.getMessage());

        } catch (InterruptedException e) {
            System.out.println("El proceso principal fue interrumpido.");
            Thread.currentThread().interrupt();
        }
    }
}