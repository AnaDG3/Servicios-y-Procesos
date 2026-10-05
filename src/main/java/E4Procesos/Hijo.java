package E4Procesos;

import java.io.*;

public class Hijo {

    public static void main(String[] args) {

        try {
            // Leer el número enviado por el padre
            BufferedReader lector = new BufferedReader(
                    new InputStreamReader(System.in)
            );

            String linea = lector.readLine();

            int numero = Integer.parseInt(linea);

            // Calcular el cuadrado
            int resultado = numero * numero;

            // Devolver el resultado al padre
            System.out.println(resultado);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}