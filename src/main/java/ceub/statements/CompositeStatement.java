package ceub.statements;

import java.util.ArrayList;
import java.util.List;

public class CompositeStatement implements Statement {
    List<Statement> statements;

    public CompositeStatement() {
        this.statements = new ArrayList<>();
    }

    public void addStatement(Statement statement) {
        statements.add(statement);
    }

    @Override
    public void generateCCode() {
        for (Statement stmt : statements) {
            stmt.generateCCode();
        }
    }
}
