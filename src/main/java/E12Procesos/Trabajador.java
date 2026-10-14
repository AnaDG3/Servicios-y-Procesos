package E12Procesos;

public class Trabajador {

    public static void main(String[] args) {

        // Comprobar los argumentos recibidos
        if (args.length != 3) {
            System.err.println("Error: argumentos incorrectos.");
            System.exit(1);
        }

        try {

            int inicio = Integer.parseInt(args[0]);
            int fin = Integer.parseInt(args[1]);
            int numeroTrabajador = Integer.parseInt(args[2]);

            long pares = 0;
            long impares = 0;
            long suma = 0;

            // Procesar el intervalo
            for (int i = inicio; i <= fin; i++) {

                if (i % 2 == 0) {
                    pares++;
                } else {
                    impares++;
                }

                suma += i;
            }

            // Enviar el resultado al proceso padre mediante stdout
            System.out.println("Trabajador " + numeroTrabajador);
            System.out.println("Pares: " + pares);
            System.out.println("Impares: " + impares);
            System.out.println("Suma: " + suma);

        } catch (NumberFormatException e) {

            System.err.println(
                    "Error: los argumentos deben ser números."
            );

            System.exit(1);
        }
    }
}