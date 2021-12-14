package example;

import example.route.MyRouteBuilder;
import org.apache.camel.main.Main;

public final class MyApplication {

    private MyApplication() {
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.configure().addRoutesBuilder(MyRouteBuilder.class);
        // now keep the application running until the JVM is terminated (ctrl + c or sigterm)
        main.run(args);
    }
}