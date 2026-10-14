package E7Procesos;

import java.io.IOException;

public class VersionProcesos {

    public static void main(String[] args) throws IOException, InterruptedException {

        String java = System.getProperty("java.home")
                + "/bin/java";

        String classpath = System.getProperty("java.class.path");

        ProcessBuilder p1 = new ProcessBuilder(
                java, "-cp", classpath,
                "E7Procesos.TareaProceso", "1", "33"
        );

        ProcessBuilder p2 = new ProcessBuilder(
                java, "-cp", classpath,
                "E7Procesos.TareaProceso", "34", "66"
        );

        ProcessBuilder p3 = new ProcessBuilder(
                java, "-cp", classpath,
                "E7Procesos.TareaProceso", "67", "100"
        );

        Process proceso1 = p1.inheritIO().start();
        Process proceso2 = p2.inheritIO().start();
        Process proceso3 = p3.inheritIO().start();

        proceso1.waitFor();
        proceso2.waitFor();
        proceso3.waitFor();

        System.out.println("Los tres procesos han terminado.");
    }
}