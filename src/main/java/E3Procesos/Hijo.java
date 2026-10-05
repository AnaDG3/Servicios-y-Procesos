package E3Procesos;

public class Hijo {

    public static void main(String[] args) {

        int numero = Integer.parseInt(args[0]);

        int resultado = numero * numero;

        System.out.println(numero + "² = " + resultado);

        System.exit(resultado);
    }
}