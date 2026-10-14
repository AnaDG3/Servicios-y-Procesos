package MiniProyecto;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Trabajador {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println(
                    "Error: no se han recibido los archivos."
            );

            System.exit(1);
        }

        String[] archivos = args[0].split("\\|");

        long numeroArchivos = 0;
        long numeroLineas = 0;
        long numeroPalabras = 0;
        long numeroCaracteres = 0;

        try {

            for (String nombreArchivo : archivos) {

                if (nombreArchivo.isEmpty()) {
                    continue;
                }

                Path archivo = Paths.get(nombreArchivo);

                // Comprobar que existe
                if (!Files.exists(archivo)) {

                    System.err.println(
                            "Archivo inexistente: " + archivo
                    );

                    continue;
                }

                try (BufferedReader lector =
                             Files.newBufferedReader(archivo)) {

                    String linea;

                    numeroArchivos++;

                    while ((linea = lector.readLine()) != null) {

                        numeroLineas++;

                        // Contar caracteres
                        numeroCaracteres += linea.length();

                        // Contar palabras
                        String texto = linea.trim();

                        if (!texto.isEmpty()) {

                            String[] palabras =
                                    texto.split("\\s+");

                            numeroPalabras += palabras.length;
                        }
                    }

                } catch (IOException e) {

                    System.err.println(
                            "Error leyendo archivo "
                                    + archivo
                                    + ": "
                                    + e.getMessage()
                    );
                }
            }

            // Enviar resultados al proceso principal
            System.out.println("ARCHIVOS: " + numeroArchivos);
            System.out.println("LINEAS: " + numeroLineas);
            System.out.println("PALABRAS: " + numeroPalabras);
            System.out.println("CARACTERES: " + numeroCaracteres);

        } catch (Exception e) {

            System.err.println(
                    "Error en el trabajador: "
                            + e.getMessage()
            );

            System.exit(1);
        }
    }
}