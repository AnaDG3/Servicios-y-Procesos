package E8Procesos;

import java.io.IOException;

public class Ejercicio8 {

    public static void main(String[] args) throws IOException, InterruptedException {

        String java = System.getProperty("java.home") + "/bin/java";
        String classpath = System.getProperty("java.class.path");

        int[] numeros = {10, 20, 30, 40, 50};

        Process[] procesos = new Process[5];

        for (int i = 0; i < numeros.length; i++) {

            ProcessBuilder pb = new ProcessBuilder(
                    java,
                    "-cp",
                    classpath,
                    "E8Procesos.Calculador",
                    String.valueOf(numeros[i])
            );

            procesos[i] = pb.inheritIO().start();
        }

        // Esperamos a que terminen los cinco procesos
        for (Process proceso : procesos) {
            proceso.waitFor();
        }

        System.out.println("Todos los procesos han terminado.");
    }
}