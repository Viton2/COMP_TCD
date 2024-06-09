package ceub;

public class Comparison {
    String variable;
    String operator;
    String value;

    public Comparison(String variable, String operator, String value) {
        this.variable = variable;
        this.operator = operator;
        this.value = value;
    }

    public void generateCCode() {
        System.out.printf("%s %s %s", this.variable, translateOperator(this.operator), this.value);
    }

    private String translateOperator(String op) {
        switch (op) {
            case "=":
                return "==";
            case "#":
                return "!=";
            default:
                return op;
        }
    }
}
