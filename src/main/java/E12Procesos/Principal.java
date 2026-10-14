package E12Procesos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Principal {

    public static void main(String[] args) {

        // Comprobar que se ha recibido el número como argumento
        if (args.length != 1) {
            System.out.println("Uso: java Principal <numero>");
            return;
        }

        int limite;

        try {
            limite = Integer.parseInt(args[0]);

            if (limite < 4) {
                System.out.println("El número debe ser mayor o igual que 4.");
                return;
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: el argumento debe ser un número entero.");
            return;
        }

        long tiempoInicio = System.currentTimeMillis();

        System.out.println("=== PROCESAMIENTO PARALELO ===");
        System.out.println();

        String java = System.getProperty("java.home")
                + "/bin/java";

        String classpath = System.getProperty("java.class.path");

        Process[] procesos = new Process[4];
        BufferedReader[] lectores = new BufferedReader[4];

        long paresTotales = 0;
        long imparesTotales = 0;
        long sumaTotal = 0;

        try {

            // Calcular los intervalos
            int tamaño = limite / 4;

            int[][] intervalos = {
                    {1, tamaño},
                    {tamaño + 1, tamaño * 2},
                    {tamaño * 2 + 1, tamaño * 3},
                    {tamaño * 3 + 1, limite}
            };

            // Crear los cuatro procesos
            for (int i = 0; i < 4; i++) {

                ProcessBuilder pb = new ProcessBuilder(
                        java,
                        "-cp",
                        classpath,
                        "E12Procesos.Trabajador",
                        String.valueOf(intervalos[i][0]),
                        String.valueOf(intervalos[i][1]),
                        String.valueOf(i + 1)
                );

                // Redirigimos los errores para poder controlarlos
                pb.redirectErrorStream(true);

                procesos[i] = pb.start();

                lectores[i] = new BufferedReader(
                        new InputStreamReader(
                                procesos[i].getInputStream()
                        )
                );
            }

            // Recoger los resultados
            for (int i = 0; i < 4; i++) {

                String linea;
                long pares = 0;
                long impares = 0;
                long suma = 0;

                while ((linea = lectores[i].readLine()) != null) {

                    if (linea.startsWith("Pares:")) {
                        pares = Long.parseLong(
                                linea.substring(6).trim()
                        );

                    } else if (linea.startsWith("Impares:")) {
                        impares = Long.parseLong(
                                linea.substring(8).trim()
                        );

                    } else if (linea.startsWith("Suma:")) {
                        suma = Long.parseLong(
                                linea.substring(5).trim()
                        );

                    } else {
                        System.out.println(linea);
                    }
                }

                // Esperar a que termine el proceso
                int codigo = procesos[i].waitFor();

                if (codigo != 0) {
                    System.out.println(
                            "Error: el Trabajador " + (i + 1)
                                    + " terminó con código " + codigo
                    );
                } else {
                    System.out.println(
                            "Trabajador " + (i + 1) + " finalizado"
                    );
                }

                paresTotales += pares;
                imparesTotales += impares;
                sumaTotal += suma;
            }

        } catch (IOException e) {

            System.out.println(
                    "Error al crear o ejecutar los procesos: "
                            + e.getMessage()
            );

        } catch (InterruptedException e) {

            System.out.println(
                    "El proceso principal fue interrumpido."
            );

            Thread.currentThread().interrupt();
        }

        long tiempoFin = System.currentTimeMillis();

        System.out.println();
        System.out.println("RESULTADO GLOBAL");
        System.out.println("----------------");
        System.out.println("Pares: " + paresTotales);
        System.out.println("Impares: " + imparesTotales);
        System.out.println("Suma total: " + sumaTotal);
        System.out.println();
        System.out.println("Tiempo: "
                + (tiempoFin - tiempoInicio) + " ms");
    }
}