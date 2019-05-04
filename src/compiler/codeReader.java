/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author BooOooDeY
 */
public class codeReader {
    String Code;
    public codeReader(String str) throws IOException{
        this.loadFileAsString(str);
    }
    public void loadFileAsString(String file) throws FileNotFoundException, IOException {
        StringBuilder fileBuilder = new StringBuilder();
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    fileBuilder.append(line + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.Code = fileBuilder.toString();
        //return fileBuilder.toString();
    }
    
    public String Serve(){
        preprocessor pre = new preprocessor(this.Code);
        this.Code = pre.serveCode();
        return this.Code;
    }
    
}
