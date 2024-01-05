import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Создание и запуск сервера
        Server server = new Server();
        SwingUtilities.invokeLater(() -> {
            server.createAndShowGUI();

            // Задержка перед запуском клиентов для обеспечения старта сервера
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Создание и запуск первого клиента
            Client client1 = new Client();
            client1.createAndShowGUI();

            // Создание и запуск второго клиента
            Client client2 = new Client();
            client2.createAndShowGUI();
        });
    }
}
