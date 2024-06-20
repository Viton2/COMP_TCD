package ceub;
import ceub.statements.CompositeStatement;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CodeGenerator {
    public static void main(String[] args) {
        boolean i = true;
        List<String> codes = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o codigo: (Pressione 0 para sair)");
        while (i){
            String input = sc.nextLine();
            if (input.equals("0")){
                i = false;
            }else{
                codes.add(input);
            }
        }
        StringBuilder input = new StringBuilder();
        for (String line : codes){
            input.append(line).append("\n");
        }
        Parser parser = new Parser(input.toString());
        CompositeStatement program = parser.parse();
        System.out.println("#include <stdio.h>\nint main() {\nint a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z;\nchar str[512];");
        program.generateCCode();
        System.out.println("return 0;\n}");
    }
}