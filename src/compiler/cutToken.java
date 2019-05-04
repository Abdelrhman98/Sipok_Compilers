package compiler;

import java.util.ArrayList;


public class cutToken {
    boolean sign;
    char []sepCharss = {'[',']','{','}','+','-','*','/','<','=','>','(',')','@','|','&','~','$','#','!'};
    private boolean cutSign(char ch) {
        for(char symbole:this.sepCharss)
            if(symbole == ch)
                return true;
        return false;
    }

    public ArrayList<String> cut(String s, int size){
        ArrayList<String> allCuts = new ArrayList<>();
        char[]ch = s.toCharArray();
        String str="";
        for(int i = 0;i<size;i++){
            if(sign ==false && this.cutSign(ch[i])){
                allCuts.add(str);
                sign = true;
                i--;
                str = "";
                continue;
            }if(sign== true && !this.cutSign(ch[i])){
                allCuts.add(str);
                i--;
                sign = false;
                str = "";
                continue;
            }
            str+=ch[i];
            
        }
        if(str!="")
            allCuts.add(str);
        
        return allCuts;
        
    }
}
