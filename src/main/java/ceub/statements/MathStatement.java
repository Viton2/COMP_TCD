package ceub.statements;

public class MathStatement implements Statement {
    String operation;
    String result;
    String operand1;
    String operand2;

    public MathStatement(String operation, String result, String operand1, String operand2) {
        this.operation = operation;
        this.result = result;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public void generateCCode() {
        System.out.printf("%s = %s %s %s;\n", this.result, this.operand1, this.operation, this.operand2);
    }
}
