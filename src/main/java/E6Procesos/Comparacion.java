package E6Procesos;

public class Comparacion {

    public static void main(String[] args) {

        try {
            // Ejecución secuencial

            long inicio = System.currentTimeMillis();

            Tarea1();
            Tarea2();
            Tarea3();

            long fin = System.currentTimeMillis();

            long tiempoSecuencial = fin - inicio;

            System.out.println("Tiempo secuencial: "
                    + tiempoSecuencial + " ms");


            // Ejecución paralela

            inicio = System.currentTimeMillis();

            Process proceso1 = new ProcessBuilder(
                    "java", "Tarea", "1", "4"
            ).start();

            Process proceso2 = new ProcessBuilder(
                    "java", "Tarea", "2", "4"
            ).start();

            Process proceso3 = new ProcessBuilder(
                    "java", "Tarea", "3", "4"
            ).start();

            // Esperar a que terminen los tres
            proceso1.waitFor();
            proceso2.waitFor();
            proceso3.waitFor();

            fin = System.currentTimeMillis();

            long tiempoParalelo = fin - inicio;

            System.out.println("Tiempo paralelo: "
                    + tiempoParalelo + " ms");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Tarea 1
    public static void Tarea1() throws InterruptedException {
        System.out.println("Tarea 1 iniciada.");
        Thread.sleep(4000);
        System.out.println("Tarea 1 finalizada.");
    }

    // Tarea 2
    public static void Tarea2() throws InterruptedException {
        System.out.println("Tarea 2 iniciada.");
        Thread.sleep(4000);
        System.out.println("Tarea 2 finalizada.");
    }

    // Tarea 3
    public static void Tarea3() throws InterruptedException {
        System.out.println("Tarea 3 iniciada.");
        Thread.sleep(4000);
        System.out.println("Tarea 3 finalizada.");
    }
}