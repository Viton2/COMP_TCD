package ceub.statements;

public class GetStatement implements Statement {
    String variable;

    public GetStatement(String variable) {
        this.variable = variable;
    }

    @Override
    public void generateCCode() {
        System.out.printf("{ gets(str);\n\tsscanf(str, \"%%d\", &%s);\n}\n", this.variable);
    }
}
