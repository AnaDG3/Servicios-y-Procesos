package E7Procesos;

public class TareaProceso {

    public static void main(String[] args) {

        int inicio = Integer.parseInt(args[0]);
        int fin = Integer.parseInt(args[1]);

        int suma = 0;

        for (int i = inicio; i <= fin; i++) {
            suma += i;
        }

        System.out.println(
                "Proceso " + ProcessHandle.current().pid()
                        + " -> suma de " + inicio
                        + " a " + fin
                        + " = " + suma
        );
    }
}