package E10Procesos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PrimosPrincipal {

    public static void main(String[] args) throws IOException, InterruptedException {

        long inicioTiempo = System.currentTimeMillis();

        int[][] intervalos = {
                {1, 250000},
                {250001, 500000},
                {500001, 750000},
                {750001, 1000000}
        };

        String java = System.getProperty("java.home") + "/bin/java";
        String classpath = System.getProperty("java.class.path");

        Process[] procesos = new Process[4];
        BufferedReader[] lectores = new BufferedReader[4];

        // Crear los cuatro procesos
        for (int i = 0; i < 4; i++) {

            ProcessBuilder pb = new ProcessBuilder(
                    java,
                    "-cp",
                    classpath,
                    "E10Procesos.PrimosProceso",
                    String.valueOf(intervalos[i][0]),
                    String.valueOf(intervalos[i][1])
            );

            procesos[i] = pb.start();

            lectores[i] = new BufferedReader(
                    new InputStreamReader(procesos[i].getInputStream())
            );
        }

        // Recoger los resultados
        List<Integer> primos = new ArrayList<>();

        for (int i = 0; i < 4; i++) {

            String linea;

            while ((linea = lectores[i].readLine()) != null) {
                if (!linea.isEmpty()) {
                    primos.add(Integer.parseInt(linea));
                }
            }

            procesos[i].waitFor();
        }

        long finTiempo = System.currentTimeMillis();

        System.out.println("Número de primos encontrados: " + primos.size());
        System.out.println("Tiempo empleado: "
                + (finTiempo - inicioTiempo) + " ms");
    }
}