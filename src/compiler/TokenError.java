package compiler;


public class TokenError {
    String TokenText;
    int line;
    public TokenError(String text, int line){
        this.TokenText = text;
        this.line = line;
    }
}
