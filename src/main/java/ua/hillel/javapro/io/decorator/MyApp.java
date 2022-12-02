package ua.hillel.javapro.io.decorator;

public class MyApp {
    private ExternalClient client;

    public MyApp(ExternalClient client) {
        this.client = client;
    }

    public void doSomething() {
        try {
            Object o = client.requestData(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
