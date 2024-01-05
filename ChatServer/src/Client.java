import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static final String DEFAULT_SERVER_IP = "127.0.0.1";
    private static final int DEFAULT_PORT = 5000;

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private JTextArea chatArea;
    private JScrollPane scrollPane;
    private StringBuilder allMessages = new StringBuilder(); // Хранение всех сообщений
    private String login;
    private JLabel statusLabel; // Добавлено поле для отображения статуса

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Клиент");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 450);

        JPanel loginPanel = new JPanel();
        JTextField loginField = new JTextField(10);
        JTextField serverIPField = new JTextField(DEFAULT_SERVER_IP, 10);
        JTextField portField = new JTextField(String.valueOf(DEFAULT_PORT), 5);
        JButton connectButton = new JButton("Подключиться");

        loginPanel.add(new JLabel("Логин:"));
        loginPanel.add(loginField);
        loginPanel.add(new JLabel("IP сервера:"));
        loginPanel.add(serverIPField);
        loginPanel.add(new JLabel("Порт:"));
        loginPanel.add(portField);
        loginPanel.add(connectButton);

        JPanel statusPanel = new JPanel();
        statusLabel = new JLabel("Статус: Не подключено");
        statusLabel.setForeground(Color.RED);
        statusPanel.add(statusLabel);

        chatArea = new JTextArea(10, 30);
        chatArea.setEditable(false);
        scrollPane = new JScrollPane(chatArea);

        JPanel messagePanel = new JPanel();
        JTextField messageField = new JTextField(20);
        JButton sendButton = new JButton("Отправить");

        messagePanel.add(messageField);
        messagePanel.add(sendButton);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(statusPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(messagePanel, BorderLayout.SOUTH);

        frame.add(loginPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);

        connectButton.addActionListener(e -> {
            String serverIP = serverIPField.getText();
            int port = Integer.parseInt(portField.getText());
            login = loginField.getText(); // Получаем логин из поля ввода

            connectToServer(serverIP, port);
        });

        sendButton.addActionListener(e -> {
            String message = messageField.getText();
            sendMessage(message);
            messageField.setText("");
        });

        messageField.addActionListener(e -> {
            String message = messageField.getText();
            sendMessage(message);
            messageField.setText("");
        });
    }

    private void connectToServer(String serverIP, int port) {
        try {
            socket = new Socket(serverIP, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(login); // Отправляем логин на сервер

            statusLabel.setText("Статус: Подключено к серверу");
            statusLabel.setForeground(Color.GREEN); // Зеленый цвет при подключении

            startListening();
        } catch (IOException e) {
            statusLabel.setText("Статус: Ошибка подключения");
            statusLabel.setForeground(Color.RED); // Красный цвет при ошибке подключения

            JOptionPane.showMessageDialog(null, "Не удалось подключиться к серверу: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void startListening() {
        new Thread(() -> {
            try {
                String receivedMessage;
                while ((receivedMessage = in.readLine()) != null) {
                    allMessages.append(receivedMessage).append("\n");
                    updateChatArea();
                }
            } catch (IOException e) {
                statusLabel.setText("Статус: Сервер отключен");
                statusLabel.setForeground(Color.RED); // Красный цвет при отключении сервера

                JOptionPane.showMessageDialog(null, "Сервер отключен", "Информация", JOptionPane.INFORMATION_MESSAGE);
            }
        }).start();
    }

    private void updateChatArea() {
        SwingUtilities.invokeLater(() -> {
            chatArea.setText(allMessages.toString());
            JScrollBar vertical = scrollPane.getVerticalScrollBar();
            vertical.setValue(vertical.getMaximum());
        });
    }

    private void sendMessage(String message) {
        if (out != null) {
            out.println(message);
            allMessages.append("ВЫ: ").append(message).append("\n");
            updateChatArea();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Client client = new Client();
            client.createAndShowGUI();
        });
    }
}
