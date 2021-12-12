package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerApplication {


    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(3636)) {
            System.out.println("Сервер запущен");
            while (true) {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Новое соединение установлено\n" +
                            "IP:" + clientSocket.getInetAddress() + ":" + clientSocket.getPort());

                       new Thread(new RequestHandler(clientSocket)).start();
                        break;// обработчик клиентских запросов
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}