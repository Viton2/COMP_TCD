package ceub;

public class Lexer {
    private final String input;
    private int pos = 0;

    Lexer(String input) {
        this.input = input;
    }

    Token nextToken() {
        while (pos < input.length() && Character.isWhitespace(input.charAt(pos))) {
            pos++;
        }
        if (pos >= input.length()) return new Token(TokenType.EOF, "");

        char current = input.charAt(pos);
        pos++;
        if (Character.isLowerCase(current)) {
            return new Token(TokenType.VARIABLE, Character.toString(current));
        } else if (Character.isDigit(current)) {
            return new Token(TokenType.NUMBER, Character.toString(current));
        } else if ("=<#".indexOf(current) != -1) {
            return new Token(TokenType.OPERATOR, Character.toString(current));
        } else if ("G+-*/%PIW{}".indexOf(current) != -1) {
            return new Token(TokenType.COMMAND, Character.toString(current));
        }
        throw new RuntimeException("Unexpected character: " + current);
    }

    }

