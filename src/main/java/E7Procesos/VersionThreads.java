package E7Procesos;

public class VersionThreads {

    static class MiTarea extends Thread {
        private int inicio;
        private int fin;

        public MiTarea(int inicio, int fin) {
            this.inicio = inicio;
            this.fin = fin;
        }

        @Override
        public void run() {
            int suma = 0;

            for (int i = inicio; i <= fin; i++) {
                suma += i;
            }

            System.out.println(
                    Thread.currentThread().getName()
                            + " -> suma de " + inicio + " a " + fin
                            + " = " + suma
            );
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new MiTarea(1, 33);
        Thread t2 = new MiTarea(34, 66);
        Thread t3 = new MiTarea(67, 100);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Los tres hilos han terminado.");
    }
}