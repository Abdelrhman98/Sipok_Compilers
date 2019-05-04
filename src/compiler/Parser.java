package compiler;

import java.util.ArrayList;

public class Parser {
    public Scanner2 scanner;
    private ArrayList<Token> tokens;
    private int index = 0;
    tokensStack s1 = new tokensStack(2000);
    /*String [][] table =
    {  //@,^,$,#,',',(,),{,},T,=,if,else,STR,TXT,/$ STR $/,***STR,require,infer,;,return,respondwith,however,when,print,Scanvalur,endthis,none,ID,number,[,],&&|||,ops,mul/div
        {"StartSymbols ClassDeclaration EndSymbols","StartSymbols ClassDeclaration EndSymbols","StartSymbols ClassDeclaration EndSymbols","StartSymbols ClassDeclaration EndSymbols",null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {"@","^",null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,"$","#",null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
    };*/
    String [][] table =
    {              //Ipok
      /*Program*/  {"A"},
      /*A      */  {"Ipok"}
    };
    String [] terminals={"Ipok"};
    String [] nonTerminals={"Program", "A"};


    public Parser(String input) {
        preprocessor x = new preprocessor(input);
        this.scanner = new Scanner2(x.serveCode());
        this.tokens = this.scanner.generateAllTokens();
        this.tokens.add(new Token("$"));
    }

    public void parse() {
        String top;
        Token token = readToken();
        push("Program");  //Start Symbol
        while(true){
            top=this.pop();
            if(top.equals("$")){
                break;
            }
            if(isTerminal(top)){
                if(top.equals(token.TokenText)){
                    this.scanner.logger.add("Line #" + token.line + " Matched [" + token.TokenText + "].");
                }
                else{
                    this.scanner.logger.add("Line #" + token.line + " Unmatched.");
                }
                token = readToken();
            }
            else if(isNonTerminal(top)){
                String rule = this.getRule(top, token.TokenText);
                this.pushRule(rule);
            }
            else{
                this.scanner.logger.add("Unspecified symbol : ["+top+"]");
            }
        }
        if(token.TokenText.equals("$")){
            this.scanner.logger.add("[+]Parsed Successfuly!");	
        }
        else{
            this.scanner.logger.add("[-]Input is not accepted by parser!");	
        }
    }
    
    private boolean isTerminal(String s) {
        for(int i=0;i<this.terminals.length;i++){
            if(s.equals(this.terminals[i])){
                return true;
            }
        }
        return false;
    }

    private boolean isNonTerminal(String s) {
        for(int i=0;i<this.nonTerminals.length;i++){
            if(s.equals(this.nonTerminals[i])){
                return true;
            }
        }
        return false;
    }

    private Token readToken() {
        Token tok = this.tokens.get(index);
        System.out.println(tok.TokenText);
        index += 1;
        return tok;
    }
    
    private  void pushRule(String r) {
        String[] rules = r.split(" ");
        for(int i=rules.length-1;i>=0;i--){
            String str=rules[i];
            push(str);
        }
    }

    private void push(String s) {
        this.s1.push(s);	
    }

    private String pop() {
        return this.s1.pop()+"";	
    }

    public String getRule(String nonTerm, String Term) {
        int row = getNonTermIndex(nonTerm);
        int column = getTermIndex(Term);
        String rule = this.table[row][column];
        if(rule==null){
            this.scanner.logger.add("There is no rule for [" + nonTerm + "=>" + Term + "]");
        }
        return rule;
    }

    private int getNonTermIndex(String non) {
        for(int i=0;i<this.nonTerminals.length;i++){
            if(non.equals(this.nonTerminals[i])){
                return i;
            }
        }
        this.scanner.logger.add(non + " is not NonTerminal");
        return -1;
    }

    private int getTermIndex(String term) {
        for(int i=0;i<this.terminals.length;i++){
            if(term.equals(this.terminals[i])){
                return i;
            }
        }
        this.scanner.logger.add(term + " is not Terminal");
        return -1;
    }

    public static void main(String[] args) { //int x = 5;
        Parser parser=new Parser("Ipok Ipok ***");//i*i+(i+i)$
        parser.parse();
    }
}
