
import java.util.Scanner;

import operations.MathOperations;
import operations.ResultPrinter;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите первое число: ");
        double num1 = scanner.nextDouble();

        System.out.print("Введите второе число: ");
        double num2 = scanner.nextDouble();

        double addResult = MathOperations.add(num1, num2);
        double subtractResult = MathOperations.subtract(num1, num2);
        double multiplyResult = MathOperations.multiply(num1, num2);
        double divideResult = 0;

        try {
            divideResult = MathOperations.divide(num1, num2);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        ResultPrinter.printResult("addition", num1, num2, addResult);
        ResultPrinter.printResult("subtraction", num1, num2, subtractResult);
        ResultPrinter.printResult("multiplication", num1, num2, multiplyResult);
        ResultPrinter.printResult("division", num1, num2, divideResult);
    }
}
