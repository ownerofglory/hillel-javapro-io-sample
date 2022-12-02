package ua.hillel.javapro.io.decorator;

public interface ExternalClient {
    Object requestData(int userId) throws Exception;
}
