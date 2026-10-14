package MiniProyecto;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Principal {

    public static void main(String[] args) {

        // Comprobar argumentos
        if (args.length != 1) {
            System.out.println("Uso: java Principal <carpeta>");
            return;
        }

        Path carpeta = Paths.get(args[0]);

        // Comprobar que la carpeta existe
        if (!Files.exists(carpeta)) {
            System.out.println("Error: la carpeta no existe.");
            return;
        }

        if (!Files.isDirectory(carpeta)) {
            System.out.println("Error: la ruta indicada no es una carpeta.");
            return;
        }

        long tiempoInicio = System.currentTimeMillis();

        try {

            // Buscar los archivos de la carpeta
            List<Path> archivos = new ArrayList<>();

            try (DirectoryStream<Path> stream =
                         Files.newDirectoryStream(carpeta)) {

                for (Path archivo : stream) {

                    if (Files.isRegularFile(archivo)) {
                        archivos.add(archivo);
                    }
                }
            }

            if (archivos.isEmpty()) {
                System.out.println("No hay archivos para procesar.");
                return;
            }

            System.out.println("Archivos encontrados: " + archivos.size());
            System.out.println();

            // Crear los tres grupos de archivos
            List<List<Path>> grupos = new ArrayList<>();

            grupos.add(new ArrayList<>());
            grupos.add(new ArrayList<>());
            grupos.add(new ArrayList<>());

            for (int i = 0; i < archivos.size(); i++) {
                grupos.get(i % 3).add(archivos.get(i));
            }

            String java = System.getProperty("java.home")
                    + "/bin/java";

            String classpath = System.getProperty("java.class.path");

            Process[] procesos = new Process[3];
            BufferedReader[] lectores = new BufferedReader[3];

            long archivosTotales = 0;
            long lineasTotales = 0;
            long palabrasTotales = 0;
            long caracteresTotales = 0;

            // Crear los tres procesos
            for (int i = 0; i < 3; i++) {

                StringBuilder listaArchivos = new StringBuilder();

                for (Path archivo : grupos.get(i)) {

                    if (listaArchivos.length() > 0) {
                        listaArchivos.append("|");
                    }

                    listaArchivos.append(archivo.toAbsolutePath());
                }

                ProcessBuilder pb = new ProcessBuilder(
                        java,
                        "-cp",
                        classpath,
                        "RetoFinal.Trabajador",
                        listaArchivos.toString()
                );

                // stdout y stderr se mezclan
                pb.redirectErrorStream(true);

                procesos[i] = pb.start();

                lectores[i] = new BufferedReader(
                        new InputStreamReader(
                                procesos[i].getInputStream()
                        )
                );
            }

            // Recoger los resultados
            for (int i = 0; i < 3; i++) {

                String linea;

                long archivosProceso = 0;
                long lineasProceso = 0;
                long palabrasProceso = 0;
                long caracteresProceso = 0;

                while ((linea = lectores[i].readLine()) != null) {

                    if (linea.startsWith("ARCHIVOS:")) {

                        archivosProceso = Long.parseLong(
                                linea.substring(9).trim()
                        );

                    } else if (linea.startsWith("LINEAS:")) {

                        lineasProceso = Long.parseLong(
                                linea.substring(7).trim()
                        );

                    } else if (linea.startsWith("PALABRAS:")) {

                        palabrasProceso = Long.parseLong(
                                linea.substring(9).trim()
                        );

                    } else if (linea.startsWith("CARACTERES:")) {

                        caracteresProceso = Long.parseLong(
                                linea.substring(11).trim()
                        );

                    } else {

                        System.out.println(
                                "Proceso " + (i + 1) + ": " + linea
                        );
                    }
                }

                // Esperar al proceso
                int codigoSalida = procesos[i].waitFor();

                if (codigoSalida != 0) {

                    System.out.println(
                            "El proceso " + (i + 1)
                                    + " terminó con error. Código: "
                                    + codigoSalida
                    );

                } else {

                    System.out.println(
                            "Proceso " + (i + 1) + " finalizado correctamente."
                    );
                }

                archivosTotales += archivosProceso;
                lineasTotales += lineasProceso;
                palabrasTotales += palabrasProceso;
                caracteresTotales += caracteresProceso;
            }

            long tiempoFin = System.currentTimeMillis();

            // Mostrar resumen
            System.out.println();
            System.out.println("========== RESUMEN ==========");
            System.out.println();
            System.out.printf(
                    "Archivos procesados: %d%n",
                    archivosTotales
            );

            System.out.printf(
                    "Líneas:              %d%n",
                    lineasTotales
            );

            System.out.printf(
                    "Palabras:            %d%n",
                    palabrasTotales
            );

            System.out.printf(
                    "Caracteres:          %d%n",
                    caracteresTotales
            );

            System.out.println();
            System.out.println("Procesos utilizados: 3");

            System.out.println(
                    "Tiempo total:        "
                            + (tiempoFin - tiempoInicio)
                            + " ms"
            );

            System.out.println("==============================");

        } catch (IOException e) {

            System.out.println(
                    "Error de lectura o ejecución: "
                            + e.getMessage()
            );

        } catch (InterruptedException e) {

            System.out.println(
                    "El proceso principal ha sido interrumpido."
            );

            Thread.currentThread().interrupt();
        }
    }
}