package ceub.statements;


import ceub.Comparison;

public class WhileStatement implements Statement {
    Comparison comparison;
    Statement command;

    public WhileStatement(Comparison comparison, Statement command) {
        this.comparison = comparison;
        this.command = command;
    }

    @Override
    public void generateCCode() {
        System.out.print("while (");
        comparison.generateCCode();
        System.out.println(") {");
        command.generateCCode();
        System.out.println("}");
    }
}
