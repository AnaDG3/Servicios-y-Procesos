package E9Procesos;

public class Calculadora {

    public static void main(String[] args) {

        int numero1 = Integer.parseInt(args[0]);
        int numero2 = Integer.parseInt(args[1]);

        int suma = numero1 + numero2;

        System.out.println(suma);
    }
}