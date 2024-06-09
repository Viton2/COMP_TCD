package ceub.statements;

public class AssignStatement implements Statement {
    String variable;
    String value;

    public AssignStatement(String variable, String value) {
        this.variable = variable;
        this.value = value;
    }

    @Override
    public void generateCCode() {
        System.out.printf("%s = %s;\n", this.variable, this.value);
    }
}
