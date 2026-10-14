package E11Procesos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ejercicio11 {

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("=== SIN redirectErrorStream ===");

        ProcessBuilder pb1 = new ProcessBuilder(
                "cmd", "/c", "comando_que_no_existe"
        );

        Process proceso1 = pb1.start();

        BufferedReader salida = new BufferedReader(
                new InputStreamReader(proceso1.getInputStream())
        );

        BufferedReader errores = new BufferedReader(
                new InputStreamReader(proceso1.getErrorStream())
        );

        System.out.println("SALIDA ESTÁNDAR:");

        String linea;

        while ((linea = salida.readLine()) != null) {
            System.out.println(linea);
        }

        System.out.println("ERRORES:");

        while ((linea = errores.readLine()) != null) {
            System.out.println(linea);
        }

        proceso1.waitFor();


        System.out.println("\n=== CON redirectErrorStream ===");

        ProcessBuilder pb2 = new ProcessBuilder(
                "cmd", "/c", "comando_que_no_existe"
        );

        pb2.redirectErrorStream(true);

        Process proceso2 = pb2.start();

        BufferedReader salidaCompleta = new BufferedReader(
                new InputStreamReader(proceso2.getInputStream())
        );

        System.out.println("SALIDA Y ERRORES:");

        while ((linea = salidaCompleta.readLine()) != null) {
            System.out.println(linea);
        }

        proceso2.waitFor();
    }
}