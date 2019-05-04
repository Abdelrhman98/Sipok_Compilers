package compiler;

import java.io.File;
import java.io.IOException;

public class Compiler {
    public static void main(String[] args) throws IOException {
        
        
        codeReader y = new codeReader("code.txt");
        scanner tests = new scanner(y.Serve());
        System.out.println(tests.getNextToken());
        System.out.println(tests.getNextToken());
        System.out.println(tests.getNextToken());
        System.out.println(tests.getNextToken());
        //tests.cleanCode();
        //tests.print();
        ///tests.getNextToken()
        
        
        
        
    }
}
