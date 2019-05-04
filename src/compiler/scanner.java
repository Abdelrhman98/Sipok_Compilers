package compiler;

import java.util.ArrayList;

public class scanner {

    ArrayList<Token> tokens;
    
    public String Log="";
    char[] Code;
    String CuttentToken;
    int index;
    int line;
    boolean EndOfFile = false;
    boolean sign = false;


    public scanner(String codex) {
        this.Code = new char[999999999];
        this.Code = codex.toCharArray();
        //System.out.print(this.Code);
        this.tokens = new ArrayList<>();
        this.line = 1;
    }

    private char getChar() {
        if (this.Code[this.index] == '\n') {
            this.line++;
        }

        if (this.Code[this.index] == '*') {
            if (this.Code[this.index + 1] == '*') {
                if (this.Code[this.index + 2] == '*') {
                    this.EndOfFile = true;
                }
            }
        } else {
            return this.Code[this.index++];
        }
        return ' ';
    }

    private boolean isDelimiters(char ch) {
        if (ch == ' ' || ch == ';' || ch == '\n') {
            return true;
        }
        return false;
    }

    public String getNextToken() {
        this.CuttentToken = "";

        while (this.EndOfFile != true) {
            char ch = getChar();
            if (this.EndOfFile == true) {
                break;
            }
            if (!isDelimiters(ch)) {
                this.CuttentToken += ch;
            } else {
                break;
            }
        }

        return this.CuttentToken;
    }

    private boolean cutSign(char ch) {
        char []sepCharss = {'[',']','{','}','+','-','*','/','<','=','>','(',')','@','|','&','~','$','#','!'};
         for(char symbole:sepCharss)
            if(symbole == ch)
                return true;
        return false;
    }

    public int isCuttable(String token) {
        int cnt = 0;
        int letterSize = 0;
        char[] passedToken = token.toCharArray();
        boolean hasLetter = false;
        for (char ch : passedToken) {
            letterSize++;
            if (ch >= 'a' && ch <= 'z' || ch <= 'A' && ch <= 'Z') {
                hasLetter = true;
            }
            if (this.cutSign(ch)) {
                cnt++;
            }
        }

        if (hasLetter == true && cnt > 0) {
            return letterSize;
        }

        return 0;
    }

    private boolean isGar(String str){
        char[] code = str.toCharArray();
        
        for(char letter : code){
            if(letter == ' ' || letter=='\n' ||letter=='\t'){
                return true;
            }
        }
        return false;
        
    }
    private void pushNewToken(String Token, String type) {

        Token nuToken = new Token(type, Token, this.line);
        
        if(!isGar(Token) ){
            //this.Log+=  " Line#: (" +this.line +")"+" TokenText : "+Token + " Token Type{ " +type + " } " +"\n";
            System.out.println(Token);
            this.tokens.add(nuToken);
        }
        
    }

    
    public void generateAllTokens() {
        this.Log = "";
        keywordsCollections cheker = new keywordsCollections();
        String tok = this.getNextToken();
        while (this.EndOfFile != true) {
            int size = this.isCuttable(tok);
            if (size > 0) {
                cutToken cutter = new cutToken();
                ArrayList<String> cuts = cutter.cut(tok,size);
                for(String cut:cuts){
                    this.pushNewToken(cut, cheker.checkToken(cut));
                }
            }else{
                this.pushNewToken(tok, cheker.checkToken(tok));
            }
            
            tok = this.getNextToken();
        }
    }

    public void print() {
        System.out.print(this.Code);
    }

}
