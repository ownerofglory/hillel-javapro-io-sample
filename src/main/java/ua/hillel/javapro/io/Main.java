package ua.hillel.javapro.io;

import ua.hillel.javapro.io.serilization.client.OpenWeatherMapClient;
import ua.hillel.javapro.io.serilization.client.WeatherClient;
import ua.hillel.javapro.io.serilization.service.WeatherService;
import ua.hillel.javapro.io.serilization.service.WeatherServiceDefault;
import ua.hillel.javapro.io.serilization.ui.WeatherUi;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.http.HttpClient;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        WeatherClient client = new OpenWeatherMapClient(HttpClient.newHttpClient());
        WeatherService service = new WeatherServiceDefault(client);
        new WeatherUi(service);
        Path path = Paths.get("/Users/ownerofglory/projects/hillel/io/files/file.txt");

        Path path1 = path.toAbsolutePath();

        try (
               Selector selector = Selector.open();
               ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
               ) {

           serverSocketChannel.bind(new InetSocketAddress(8080));
           serverSocketChannel.configureBlocking(false);
           serverSocketChannel.register(selector, serverSocketChannel.validOps());

           while (true) {
               int count = selector.select();
               Set<SelectionKey> selectionKeys = selector.selectedKeys();
               Iterator<SelectionKey> iterator = selectionKeys.iterator();

               while (iterator.hasNext()) {
                   SelectionKey signal = iterator.next();

                   if (signal.isAcceptable()) {
                       SocketChannel conn = serverSocketChannel.accept();
                       if (conn != null) {
                           conn.configureBlocking(false);
                           conn.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                       }

                   } else if (signal.isReadable()) {
                       SocketChannel channel = (SocketChannel) signal.channel();
                       ByteBuffer buffer = ByteBuffer.allocate(256);
                       channel.read(buffer);
                       buffer.flip();

                       System.out.println(new String(buffer.array()));

                   } else if (signal.isWritable()) {
                       SocketChannel channel = (SocketChannel) signal.channel();
                       ByteBuffer buffer = ByteBuffer.allocate(256);
                       String hello = new StringBuilder()
                               .append("HTTP/1.1 200 OK").append("\r\n")
                               .append("Content-Type: text/html").append("\r\n").append("\r\n")
                               .append("<!DOCTYPE html>")
                               .append("<html>")
                               .append("<body>")
                               .append("<h1>")
                               .append("Hello")
                               .append(LocalDateTime.now())
                               .append("</h1>")
                               .append("</body>")
                               .append("</html>").toString();
                       buffer.put(hello.getBytes());
                       buffer.flip();
                       channel.write(buffer);
                       channel.close();
                   }
               }
               iterator.remove();

           }

       } catch (IOException e) {
           throw new RuntimeException(e);
       }
    }
}
