package E10Procesos;

public class PrimosProceso {

    public static void main(String[] args) {

        int inicio = Integer.parseInt(args[0]);
        int fin = Integer.parseInt(args[1]);

        for (int numero = inicio; numero <= fin; numero++) {

            if (esPrimo(numero)) {
                // Se devuelve el primo mediante stdout
                System.out.println(numero);
            }
        }
    }

    public static boolean esPrimo(int numero) {

        if (numero < 2) {
            return false;
        }

        if (numero == 2) {
            return true;
        }

        if (numero % 2 == 0) {
            return false;
        }

        for (int i = 3; i * i <= numero; i += 2) {

            if (numero % i == 0) {
                return false;
            }
        }

        return true;
    }
}