package E9Procesos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CalculadoraPrincipal {

    public static void main(String[] args) throws IOException, InterruptedException {

        String java = System.getProperty("java.home") + "/bin/java";
        String classpath = System.getProperty("java.class.path");

        int[][] numeros = {
                {5, 3},
                {10, 2},
                {20, 4},
                {7, 8}
        };

        Process[] procesos = new Process[4];
        BufferedReader[] lectores = new BufferedReader[4];

        // Crear los cuatro procesos
        for (int i = 0; i < numeros.length; i++) {

            ProcessBuilder pb = new ProcessBuilder(
                    java,
                    "-cp",
                    classpath,
                    "E9Procesos.Calculadora",
                    String.valueOf(numeros[i][0]),
                    String.valueOf(numeros[i][1])
            );

            procesos[i] = pb.start();

            lectores[i] = new BufferedReader(
                    new InputStreamReader(procesos[i].getInputStream())
            );
        }

        // Esperar y recoger los resultados
        for (int i = 0; i < procesos.length; i++) {

            procesos[i].waitFor();

            String resultado = lectores[i].readLine();

            System.out.println("Resultado " + (i + 1) + ": " + resultado);
        }
    }
}