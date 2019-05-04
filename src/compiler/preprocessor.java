package compiler;

public class preprocessor {

    private String code;

    public preprocessor(String sourceCode) {
        this.code = sourceCode;
    }
    
    public void removeComments() {
        String codeAfterRemovingComments = "";
        for (int i = 0; i < (int) this.code.length() - 1; i++) {
            if (this.code.charAt(i) == '/' && this.code.charAt(i + 1) == '$') {
                i += 2;
                while (this.code.charAt(i) != '$' && this.code.charAt(i) != '/' && i < (int) this.code.length() - 1) {
                    i++;
                }
                i += 2;
            }
            if (this.code.charAt(i) == '*' && this.code.charAt(i + 1) == '*' && this.code.charAt(i + 2) == '*') {
                while (i < (int) this.code.length() - 1 && this.code.charAt(i) != '\n') {
                    i++;
                }
                
            } else {
                codeAfterRemovingComments += this.code.charAt(i);
            }
        }
        codeAfterRemovingComments+=this.code.charAt(this.code.length()-1);
        this.code = codeAfterRemovingComments;
    }

    
    
    public void remove_spaces() {
        StringBuilder sb = new StringBuilder(String.valueOf(this.code));
        StringBuilder sb1 = new StringBuilder();

        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == ' ' && i != sb.length() - 1) {
                if (sb.charAt(i + 1) == ' ') {
                    continue;
                }
            }
            sb1.append(sb.charAt(i));
        }
        if (sb1.charAt(sb1.length() - 1) == ' ') {
            sb1.deleteCharAt(sb1.length() - 1);
        }
        this.code = String.valueOf(sb1);
    }
    
      public void remove_newLines(){
        String afterRemovingSpaces = "";
        int i = 0;
        if(this.code.charAt(0)=='\n')i=1;
        for ( ; i < this.code.length()-1; i++) {
            if(this.code.charAt(i) == '\n' && this.code.charAt(i+1) == '\n'){
                continue;
            }
            afterRemovingSpaces+= this.code.charAt(i);
        }
        afterRemovingSpaces+=this.code.charAt(this.code.length()-1);
        this.code = afterRemovingSpaces;
    }
    
    
    public void remove_Tabs(){
        String afterRemovingTabs = "";
        int i = 0;
        for ( ; i < this.code.length(); i++) 
            if(this.code.charAt(i) != '\t')    
                afterRemovingTabs+= this.code.charAt(i);
        this.code = afterRemovingTabs;
    }
    
    public void spacifyCode(){
        String codeAfterUpdate = "";
        for (int i = 0; i < (int) this.code.length(); i++) {
            char ch = this.code.charAt(i);
            /*if(ch == ''){
                
            }*/
        }
    }
    
    private void endFile(){
        this.code+="***";
    }
    public void cleanCode(){
        this.removeComments();
        this.remove_newLines();
        this.remove_spaces();
        this.remove_Tabs();
        this.endFile();
    }
    public String serveCode() {
        this.cleanCode();
        System.out.print(this.code);
        return this.code;
    }
}