import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static final int PORT = 5000;
    private static Map<String, PrintWriter> clients = new ConcurrentHashMap<>();
    private static JTextArea textArea;
    private static ServerSocket serverSocket;
    private static volatile boolean serverRunning = false;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Server::createAndShowGUI);
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Управление сервером");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel topPanel = new JPanel();
        JLabel statusLabel = new JLabel("Статус сервера: Остановлен");
        topPanel.add(statusLabel);
        frame.add(topPanel, BorderLayout.NORTH);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton startButton = new JButton("Запустить сервер");
        JButton stopButton = new JButton("Остановить сервер");

        startButton.addActionListener(e -> {
            if (!serverRunning) {
                startServer();
                statusLabel.setText("Статус сервера: Запущен");
                statusLabel.setForeground(Color.GREEN);
            }
        });

        stopButton.addActionListener(e -> {
            if (serverRunning) {
                stopServer();
                statusLabel.setText("Статус сервера: Остановлен");
                statusLabel.setForeground(Color.RED);
            }
        });

        bottomPanel.add(startButton);
        bottomPanel.add(stopButton);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private static void startServer() {
        Runnable serverTask = () -> {
            try {
                serverSocket = new ServerSocket(PORT);
                serverRunning = true;
                appendToTextArea("Сервер запущен. Ожидание подключения...");

                while (serverRunning) {
                    Socket clientSocket = serverSocket.accept();
                    appendToTextArea("Клиент подключен.");

                    PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                    ClientHandler clientHandler = new ClientHandler(clientSocket, writer);
                    new Thread(clientHandler).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        new Thread(serverTask).start();
    }

    static class ClientHandler implements Runnable {
        private Socket socket;
        private BufferedReader reader;
        private PrintWriter writer;
        private String clientLogin;

        ClientHandler(Socket clientSocket, PrintWriter writer) {
            try {
                socket = clientSocket;
                this.writer = writer;
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            try {
                writer.println("");
                String login = reader.readLine();

                clientLogin = login;
                clients.put(clientLogin, writer);
                broadcast(clientLogin, clientLogin + " присоединился к чату.");

                new Thread(() -> {
                    try {
                        String message;
                        while (serverRunning && (message = reader.readLine()) != null) {
                            appendToTextArea("Получено сообщение от " + clientLogin + ": " + message);
                            broadcast(clientLogin, clientLogin + ": " + message);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            socket.close();
                            clients.remove(clientLogin);
                            if (clientLogin != null) {
                                broadcast(clientLogin, clientLogin + " покинул чат.");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void stopServer() {
        serverRunning = false;
        try {
            serverSocket.close();
            appendToTextArea("Сервер остановлен.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void appendToTextArea(String message) {
        SwingUtilities.invokeLater(() -> textArea.append(message + "\n"));
    }

    private static void broadcast(String sender, String message) {
        for (PrintWriter clientWriter : clients.values()) {
            try {
                if (!clientWriter.equals(clients.get(sender))) {
                    clientWriter.println(message);
                    clientWriter.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
