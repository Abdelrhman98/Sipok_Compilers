package compiler;

import java.util.ArrayList;

public class Scanner2 {

    ArrayList<Token> tokens;
    
    Logger2 logger;
    char[] Code;
    String CuttentToken;
    int index;
    int line;
    boolean EndOfFile = false;
    boolean sign = false;


    public Scanner2(String code) {
        this.Code = new char[9999];
        this.Code = code.toCharArray();
        this.tokens = new ArrayList<>();
        this.line = 1;
        this.logger = new Logger2();
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

    
    private void pushNewToken(String Token, String type) {

        Token nuToken = new Token(type, Token, this.line);
        if(type==""){
            this.logger.add("Error In Line#: (" +this.line +")"+" TokenText : "+Token + " Token Type{ " +type + " } ");
        }
        else {
            this.logger.add(" Line#: (" +this.line +")"+" TokenText : "+Token + " Token Type{ " +type + " } ");
        }
        
        //System.out.println();
        this.tokens.add(nuToken);
    }

    
    public ArrayList<Token> generateAllTokens() {
        this.logger.clear();
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
        return this.tokens;
    }

    public void print() {
        System.out.print(this.Code);
    }

}
