import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Account account = null;

        boolean exit = false;
        while (!exit) {
            System.out.println("Меню:");
            System.out.println("1. Создать счет с первым взносом");
            System.out.println("2. Баланс");
            System.out.println("3. Снять");
            System.out.println("4. Пополнить");
            System.out.println("5. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Введите начальный баланс для создания счета: ");
                    double initialDeposit = scanner.nextDouble();
                    try {
                        account = new Account(initialDeposit);
                        System.out.println("Счет успешно создан. Начальный баланс: " + initialDeposit);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка при создании счета: " + e.getMessage());
                    }
                    break;
                case 2:
                    if (account != null) {
                        System.out.println("Текущий баланс: " + account.getBalance());
                    } else {
                        System.out.println("Сначала создайте счет и укажите начальный баланс.");
                    }
                    break;
                case 3:
                    if (account != null) {
                        System.out.print("Введите сумму для снятия: ");
                        double withdrawAmount = scanner.nextDouble();
                        try {
                            account.withdraw(withdrawAmount);
                        } catch (InsufficientFundsException e) {
                            System.out.println("Ошибка: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Сначала создайте счет и укажите начальный баланс.");
                    }
                    break;
                case 4:
                    if (account != null) {
                        System.out.print("Введите сумму для пополнения: ");
                        double depositAmount = scanner.nextDouble();
                        try {
                            account.deposit(depositAmount);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Ошибка: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Сначала создайте счет и укажите начальный баланс.");
                    }
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Выберите корректный пункт меню.");
                    break;
            }
        }
        scanner.close();
    }
}
