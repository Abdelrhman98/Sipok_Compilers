
package compiler;

public class Token {
    String TokenType;
    String TokenText;
    //String lexeme; // this describe the Current Token
    int line;
    public Token(String text){
        this.TokenText = text;
    }
    public Token(String type,String text, int line){
        this.TokenText = text;
        this.TokenType = type;
        this.line = line;
    }
    
    
}
