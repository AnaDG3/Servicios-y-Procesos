package E8Procesos;

public class Calculador {

    public static void main(String[] args) {

        int numero = Integer.parseInt(args[0]);

        int cuadrado = numero * numero;

        System.out.println(
                "Calculador " + numero +
                        " -> " + numero + "² = " + cuadrado
        );
    }
}