package E6Procesos;

public class Tarea {

    public static void main(String[] args) {

        int numeroTarea = Integer.parseInt(args[0]);
        int segundos = Integer.parseInt(args[1]);

        System.out.println("Tarea " + numeroTarea + " iniciada.");

        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Tarea " + numeroTarea + " finalizada.");
    }
}