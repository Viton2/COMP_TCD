package ceub;
import ceub.statements.CompositeStatement;

public class CodeGenerator {
    public static void main(String[] args) {
        String input = "G n\n" +
                "= i 2\n" +
                "% a n i\n" +
                "W i < n {\n" +
                " I a = 0 = i n\n" +
                " + i i 1\n" +
                " % a n i\n" +
                "}\n" +
                "I a = 0 P 0\n" +
                "I a # 0 P 1\n";
        Parser parser = new Parser(input);
        CompositeStatement program = parser.parse();
        System.out.println("#include <stdio.h>\nint main() {\nint a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z;\nchar str[512];");
        program.generateCCode();
        System.out.println("return 0;\n}");
    }
}