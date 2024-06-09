package ceub;


import ceub.statements.AssignStatement;
import ceub.statements.CompositeStatement;
import ceub.statements.GetStatement;
import ceub.statements.IfStatement;
import ceub.statements.MathStatement;
import ceub.statements.PrintStatement;
import ceub.statements.Statement;
import ceub.statements.WhileStatement;

public class Parser {
    private Lexer lexer;
    private Token currentToken;

    public Parser(String input) {
        this.lexer = new Lexer(input);
        this.currentToken = lexer.nextToken();
    }

    public CompositeStatement parse() {
        CompositeStatement program = new CompositeStatement();
        while (currentToken.type != TokenType.EOF) {
            program.addStatement(parseCommand());
        }
        return program;
    }

    private Statement parseCommand() {
        switch (currentToken.value) {
            case "G":
                return parseGetCommand();
            case "=":
                return parseAssignCommand();
            case "+":
            case "-":
            case "*":
            case "/":
            case "%":
                return parseMathCommand(currentToken.value);
            case "P":
                return parsePrintCommand();
            case "I":
                return parseIfCommand();
            case "W":
                return parseWhileCommand();
            case "{":
                return parseCompositeCommand();
            default:
                throw new RuntimeException("Unexpected command: " + currentToken.value);
        }
    }

    private Statement parseGetCommand() {
        consume("G");
        String variable = consume(TokenType.VARIABLE).value;
        return new GetStatement(variable);
    }

    private Statement parseAssignCommand() {
        consume("=");
        String variable = consume(TokenType.VARIABLE).value;
        String value = consumeEither(TokenType.NUMBER, TokenType.VARIABLE).value;
        return new AssignStatement(variable, value);
    }

    private Statement parseMathCommand(String operator) {
        consume(operator);
        String result = consume(TokenType.VARIABLE).value;
        String operand1 = consumeEither(TokenType.NUMBER, TokenType.VARIABLE).value;
        String operand2 = consumeEither(TokenType.NUMBER, TokenType.VARIABLE).value;
        return new MathStatement(operator, result, operand1, operand2);
    }


    private Statement parsePrintCommand() {
        consume("P");
        String value = consumeEither(TokenType.NUMBER, TokenType.VARIABLE).value;
        return new PrintStatement(value);
    }

    private Statement parseIfCommand() {
        consume("I");
        Comparison comparison = parseComparison();
        Statement command = parseCommand();
        return new IfStatement(comparison, command);
    }

    private Statement parseWhileCommand() {
        consume("W");
        Comparison comparison = parseComparison();
        Statement command = parseCommand();
        return new WhileStatement(comparison, command);
    }

    private CompositeStatement parseCompositeCommand() {
        consume("{");
        CompositeStatement composite = new CompositeStatement();
        while (!currentToken.value.equals("}")) {
            composite.addStatement(parseCommand());
        }
        consume("}");
        return composite;
    }

    private Comparison parseComparison() {
        String variable = consume(TokenType.VARIABLE).value;
        String operator = consume(TokenType.OPERATOR).value;
        String value = consumeEither(TokenType.NUMBER, TokenType.VARIABLE).value;
        return new Comparison(variable, operator, value);
    }

    private void consume(String expected) {
        if (!currentToken.value.equals(expected)) {
            throw new RuntimeException("Expected " + expected + " but found " + currentToken.value);
        }
        currentToken = lexer.nextToken();
    }

    private Token consumeEither(TokenType type1, TokenType type2) {
        if (currentToken.type != type1 && currentToken.type != type2) {
            throw new RuntimeException("Expected " + type1 + " or " + type2 + " but found " + currentToken.type);
        }
        Token token = currentToken;
        currentToken = lexer.nextToken();
        return token;
    }

    private Token consume(TokenType expected) {
        if (currentToken.type != expected) {
            throw new RuntimeException("Expected " + expected + " but found " + currentToken.type);
        }
        Token token = currentToken;
        currentToken = lexer.nextToken();
        return token;
    }
}
