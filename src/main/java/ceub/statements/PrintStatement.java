package ceub.statements;

public class PrintStatement implements Statement {
    String value;

    public PrintStatement(String value) {
        this.value = value;
    }

    @Override
    public void generateCCode() {
        System.out.printf("printf(\"%%d\\n\", %s);\n", this.value);
    }
}
