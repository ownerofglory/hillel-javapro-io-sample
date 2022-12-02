package ua.hillel.javapro.io.decorator;

public class ClientRetryDecorator implements ExternalClient {
    private static final int RETRY_AMOUNT = 5;
    private ExternalClient client;

    public ClientRetryDecorator(ExternalClient client) {
        this.client = client;
    }

    @Override
    public Object requestData(int userId) throws Exception {
        int retries = RETRY_AMOUNT;
        while (retries > 0) {
            try {
                return client.requestData(userId);
            } catch (Exception e) {
                retries--;
            }
        }
        throw new Exception();
    }
}
