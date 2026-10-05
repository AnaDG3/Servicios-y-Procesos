package E5Procesos;

public class Proceso {

    public static void main(String[] args) {

        String nombre = args[0];
        int segundos = Integer.parseInt(args[1]);

        System.out.println("Proceso " + nombre + " iniciado");

        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Proceso " + nombre + " finalizado");
    }
}