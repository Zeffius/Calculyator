
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

public class Main {

    public static void main(String[] args) throws ExceptionForTask {
        System.out.println("Введите выражение формата a + b, a - b, a * b, a / b");
        Scanner scan = new Scanner(System.in);
        String Input = scan.nextLine();
        try {
            int resultOfOperation = calculator(Input);
            System.out.println(Input + " = " + resultOfOperation);
        }
       catch (ExceptionForTask ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public static int calculator (String inputUser) throws ExceptionForTask {

        char operation = ' ';
        int number1, number2;
        int result = 0;

        Pattern pattern1 = Pattern.compile("[+-/*]");
        Matcher matcher1 = pattern1.matcher(inputUser);
        Pattern pattern2 = Pattern.compile("(\\d+\\s)[+-/*](\\s\\d+\\s\\d+)");
        Matcher matcher2 = pattern2.matcher(inputUser);

        if (!matcher1.find()) {
            throw new ExceptionForTask("throws Exception //т.к. строка не является математической операцией");
        } else if (matcher2.find()) {
            throw new ExceptionForTask("throws Exception"); //в примере было без описания, если вводится похожее "1 + 2 1"
        } else if (!inputUser.matches(("(\\d+\\s)[+-/*](\\s\\d+)"))) {
            throw new ExceptionForTask("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        } //по сути можно ограничиться только последним условием, прочие сделаны для вывода по заданию

        for (int i = 0; i < inputUser.length(); i++) {
            if (inputUser.charAt(i) == '+') {
                operation = '+';
            }
            if (inputUser.charAt(i) == '-') {
                operation = '-';
            }
            if (inputUser.charAt(i) == '*') {
                operation = '*';
            }
            if (inputUser.charAt(i) == '/') {
                operation = '/';
            }
        }

        String[] separate = inputUser.split("[+-/*]");
        String stable0 = separate[0];
        String stable1 = separate[1];
        number1 = Integer.parseInt(stable0.trim());
        number2 = Integer.parseInt(stable1.trim());

        if (number1 < 1 | number1 > 10)
        {
            throw new ExceptionForTask("throws Exception //т.к. первое число не соответствует диапазону от 1 до 10");
        }
        if (number2 < 1 | number2 > 10)
        {
            throw new ExceptionForTask("throws Exception //т.к. второе число не соответствует диапазону от 1 до 10");
        }

        result = switch (operation) {
            case '+' -> number1 + number2;
            case '-' -> number1 - number2;
            case '*' -> number1 * number2;
            case '/' -> number1 / number2;
            default -> result;
        };
        return result;
    }
}