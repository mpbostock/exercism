import java.util.Map;
import java.util.function.BinaryOperator;

class CalculatorConundrum {
    private static final Map<String, BinaryOperator<Integer>> operations = Map.of(
            "+", Integer::sum,
            "/", (a, b) -> a / b,
            "*", (a, b) -> a * b
    );

    public String calculate(int operand1, int operand2, String operation) {
        validate(operation);

        int result = performOperation(operand1, operand2, operation);
        return String.format("%d %s %d = %d", operand1, operation, operand2, result);
    }

    private static void validate(String operation) {
        if (null == operation) {
            throw new IllegalArgumentException("Operation cannot be null");
        }
        if (operation.isEmpty()) {
            throw new IllegalArgumentException("Operation cannot be empty");
        }
        if (!operations.containsKey(operation)) {
            throw new IllegalOperationException(String.format("Operation '%s' does not exist", operation));
        }
    }

    private static int performOperation(int operand1, int operand2, String operation) {
        int result;
        try {
            result = operations.get(operation).apply(operand1, operand2);
        } catch (ArithmeticException e) {
            throw new IllegalOperationException("Division by zero is not allowed", e);
        }
        return result;
    }
}
