package ceub.statements;


import ceub.Comparison;

public class IfStatement implements Statement {
    Comparison comparison;
    Statement command;

    public IfStatement(Comparison comparison, Statement command) {
        this.comparison = comparison;
        this.command = command;
    }

    @Override
    public void generateCCode() {
        System.out.print("if (");
        comparison.generateCCode();
        System.out.println(") {");
        command.generateCCode();
        System.out.println("}");
    }
}
