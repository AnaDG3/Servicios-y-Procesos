package E3Procesos;

public class Padre {

    public static void main(String[] args) {

        try {
            ProcessBuilder pb = new ProcessBuilder(
                    "java",
                    "-cp",
                    "target/classes",
                    "E3Procesos.Hijo",
                    "10"
            );

            Process proceso = pb.start();

            int resultado = proceso.waitFor();

            System.out.println("Resultado recibido del hijo: " + resultado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}