package compiler;

import java.awt.*;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;



public class testing extends JFrame {
    
 private static final long serialVersionUID = 1L;
 private static String[] arr = { "Type", "Sipok", "Sipokf", "Ipok", "Ipokf",
   "Infer", "Craf", "Sequence", "If", "Else", "ValueLess", "Rational", "Endthis","However","When","Respondwith","Srap","Scan",
   "Conditionof", "&&", "||","~","{}","[]","Require"};
 private JPanel contentPane;
 private JTextField txtName;
 private JComboBox<String> comboBox;
 private JTextPane jTextPane2;
  private JTextPane jTextPane1;
  private JLabel log;
  private JButton b1;
  private JButton b2;
  private JButton b3;
  private JButton b4;
 
  
//private JScrollPane jsp;
  private static JTextArea jta;
  private static JTextArea lines;
 
 
 private int findLastNonWordChar (String text, int index) {
        while (--index >= 0) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
        }
        return index;
    }
 
 
    private int findFirstNonWordChar (String text, int index) {
        while (index < text.length()) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
            index++;
        }
        return index;
    }

 
 
 public static void main(String[] args) {
  EventQueue.invokeLater(new Runnable() {
   public void run() {
    try {
     testing frame = new testing();
     frame.setVisible(true);
     
    } catch (Exception e) {
     e.printStackTrace();
    }
   }
  });
 }
public static String replaceLast(String string, String toReplace, String replacement) {
    int pos = string.lastIndexOf(toReplace);
    if (pos > -1) {
        return string.substring(0, pos)
             + replacement
             + string.substring(pos + toReplace.length(), string.length());
    } else {
        return string;
    }
}
     
 

 public testing() {
     
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setBounds(200, 100, 1100,650);
  this.contentPane = new JPanel();
  
  
        final StyleContext cont = StyleContext.getDefaultStyleContext();
        final AttributeSet attr = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.blue);
        final AttributeSet attrBlack = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.white);
        
        
        DefaultStyledDocument doc = new DefaultStyledDocument() {
            public void insertString (int offset, String str, AttributeSet a) throws BadLocationException {
                super.insertString(offset, str, a);

                String text = getText(0, getLength());
                int before = findLastNonWordChar(text, offset);
                if (before < 0) before = 0;
                int after = findFirstNonWordChar(text, offset + str.length());
                int wordL = before;
                int wordR = before;

                while (wordR <= after) {
                    if (wordR == after || String.valueOf(text.charAt(wordR)).matches("\\W")) {
                        if (text.substring(wordL, wordR).matches("(\\W)*(Type|Infer|If|Else|Ipok|Sipok|Craf|Sequece|Ipokf|Sipokf|Valueless|Rational|Endthis|However|When|Respondwith|Srap|Scan|Conditionof|Require)"))
                            setCharacterAttributes(wordL, wordR - wordL, attr, false);
                        else
                            setCharacterAttributes(wordL, wordR - wordL, attrBlack, false);
                        wordL = wordR;
                    }
                    wordR++;
                }
            }

            public void remove (int offs, int len) throws BadLocationException {
                super.remove(offs, len);

                String text = getText(0, getLength());
                int before = findLastNonWordChar(text, offs);
                if (before < 0) before = 0;
                int after = findFirstNonWordChar(text, offs);

                if (text.substring(before, after).matches("(\\W)*(Type|Infer|If|Else|Ipok|Sipok|Craf|Sequece|Ipokf|Sipokf|Valueless|Rational|Endthis|However|When|Respondwith|Srap|Scan|Conditionof|Require)")) {
                    setCharacterAttributes(before, after - before, attr, false);
                } else {
                    setCharacterAttributes(before, after - before, attrBlack, false);
                }
            }
        };
        
        this.jTextPane2 = new JTextPane(doc);
        
        this.jTextPane2.setBackground(Color.black);
        
        Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
        this.jTextPane2.setBorder(border);
       
        
        this.jTextPane2.setFont(new Font(jTextPane2.getName(), Font.PLAIN, 20));
      
       this.jTextPane2.setCaretColor(Color.white);
       
        
      
        
        this.jTextPane2.setBounds(15,90, 900, 330);
        
        this.add(this.jTextPane2);
        
  //--------------------------------------------------------------  
        
  
        this.jTextPane1 = new JTextPane();
        this.jTextPane1.setEditable(false);
        
        this.jTextPane1.setBounds(15,450, 900,150);
        this.contentPane.add(jTextPane1);
        
        Border border2 = BorderFactory.createLineBorder(Color.black, 1);
        this.jTextPane1.setBorder(border2);
        
        
        log = new JLabel("LOG");
        this.log.setBounds(15,430, 100,10);
       this.contentPane.add(this.log);
       
       this.b1 = new JButton("Scan");
       this.b2 = new JButton("Parse");
       this.b3 = new JButton("Browse");
       this.b4 = new JButton("Compile");
       
       this.b1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                preprocessor x = new preprocessor(jTextPane2.getText());
                Scanner2 scn = new Scanner2(x.serveCode());
                scn.generateAllTokens();
                String str = x.serveCode();
                jTextPane2.setText(str.substring(0, str.length()-6));
                System.out.println(scn.logger.getText()); 
                jTextPane1.setText(scn.logger.getText());
            }
        });
       
       this.b2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Parser parser = new Parser(jTextPane2.getText());
                parser.parse();
                System.out.println(parser.scanner.logger.getText()); 
                jTextPane1.setText(parser.scanner.logger.getText());
                
            }
        });
       
       this.b3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                File file = chooser.getSelectedFile();
                System.out.println(file);
                codeReader codeRead;
                try {
                    codeRead = new codeReader(file.getAbsolutePath());
                    Scanner2 scn = new Scanner2(codeRead.Serve());
                    scn.generateAllTokens();
                    System.out.println(scn.logger.getText()); 
                    jTextPane1.setText(scn.logger.getText());

                } catch (IOException ex) {
                    Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
       
       this.b4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Parser parser = new Parser(jTextPane2.getText());
                parser.parse();
                System.out.println(parser.scanner.logger.getText()); 
                jTextPane1.setText(parser.scanner.logger.getText());
            }
        });
       
       this.b1.setBounds(15,15, 150,70);
       this.b2.setBounds(170,15, 150,70);
       this.b3.setBounds(325,15, 170,70);
       this.b4.setBounds(500,15, 170,70);
       
       this.b1.setContentAreaFilled(false);
       this.b2.setContentAreaFilled(false);
       this.b3.setContentAreaFilled(false);
       this.b4.setContentAreaFilled(false);
       
       this.b1.setForeground(Color.red);
       this.b2.setForeground(Color.blue);
       this.b3.setForeground(Color.darkGray);
       this.b4.setForeground(Color.blue);
              
       
       try{
           Image img1 = ImageIO.read(getClass().getResource("Images/magnifier-2.png"));
           this.b1.setIcon(new ImageIcon(img1));
           
           Image img2 = ImageIO.read(getClass().getResource("Images/play1.png"));
           this.b2.setIcon(new ImageIcon(img2));
           
           Image img3 = ImageIO.read(getClass().getResource("Images/load_39552.png"));
           this.b3.setIcon(new ImageIcon(img3));
           
           Image img4 = ImageIO.read(getClass().getResource("Images/13694-200.png"));
           this.b4.setIcon(new ImageIcon(img4));
           
       }catch(Exception ex)
       {
           System.out.println(ex);
       }
       
       this.contentPane.add(this.b1);
       this.contentPane.add(this.b2);
       this.contentPane.add(this.b3);
       this.contentPane.add(this.b4);
       
  
  this.contentPane.setAutoscrolls(true);
  this.contentPane.setBackground(new Color(240, 240, 240));
  setContentPane(this.contentPane);
  this.contentPane.setLayout(null);

  
        
  
  
  this.contentPane.add(jTextPane2);
  
  this.txtName = new JTextField();
  
  jTextPane2.addKeyListener(new KeyAdapter() {
   @Override
   public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == 38) {
     int x = comboBox.getSelectedIndex();
     if (x > 0) {
      comboBox.setSelectedIndex(x - 1);
     }
     getContentPane().add(comboBox);
     comboBox.showPopup();
    } else if (e.getKeyCode() == 40) {
     int x = comboBox.getSelectedIndex();
     int y = comboBox.getItemCount();
     if (x + 1 < y)
      comboBox.setSelectedIndex(x + 1);
     getContentPane().add(comboBox);
     comboBox.showPopup();
    }
  
        
   }
  });
  
  
  jTextPane2.addKeyListener(new KeyAdapter() {
   @Override
   public void keyPressed(KeyEvent e) {
         
        if (e.getKeyCode()==KeyEvent.VK_TAB && comboBox.isShowing()){
            
                e.consume();
     try {
     String str_all = jTextPane2.getText();
     //String str_last = str_all.substring(str_all.lastIndexOf(" ")+1);
     //txtName.setText(str_last);
     if(comboBox.getSelectedItem().toString().equals("}"))
     {
         str_all = replaceLast(str_all, str_all.substring(str_all.lastIndexOf(" ")+1),"{ \n}");
         jTextPane2.setText(str_all);
     }
     else if(comboBox.getSelectedItem().toString().equals("]"))
     {
         str_all = replaceLast(str_all, str_all.substring(str_all.lastIndexOf(" ")+1),"[]");
         jTextPane2.setText(str_all);
     }
     else{
    str_all = replaceLast(str_all, str_all.substring(str_all.lastIndexOf(" ")+1),comboBox.getSelectedItem().toString());
     //jTextPane2.setText(str_last);
     //txtName.setText(str_all);
     jTextPane2.setText(str_all);
     }
     comboBox.removeAllItems();
     comboBox.hidePopup();
     contentPane.remove(comboBox);
     
     int currentCaretPosition = jTextPane2.getCaretPosition();
         System.out.println(currentCaretPosition);
    } catch (Exception ex) {
    }
   }
        }
   
  });
 
  this.jTextPane2.addCaretListener(new TextFieldCaretListener());
  this.txtName.setBounds(800, 19, 150, 37);
  this.txtName.setBackground(new Color(240, 240, 240));
  this.contentPane.add(this.txtName);
  txtName.setBorder(null);
  txtName.setEditable(false);
  this.txtName.setColumns(10);

  this.comboBox = new JComboBox<String>();
  this.comboBox.setFocusCycleRoot(true);
  this.comboBox.setFocusTraversalPolicyProvider(true);
  this.comboBox.setAutoscrolls(true);
  this.comboBox.setBorder(null);
  this.comboBox.setOpaque(false);
  this.comboBox.setBounds(920, 90, 150, 37);
  
  
   //this.contentPane.add(comboBox);
  
  
 
  
 }

 private class TextFieldCaretListener implements CaretListener {
  public void caretUpdate(CaretEvent e) {
      
   try {
    comboBox.removeAllItems();
    comboBox.hidePopup();
    contentPane.remove(comboBox);
    if (e.getMark() > 0) {
     for (String string : arr) {
         String str = jTextPane2.getText().toLowerCase();
      if (string.toLowerCase().startsWith(str.substring(str.lastIndexOf(" ")+1))) {
          
         if(str.substring(str.lastIndexOf(" ")+1).equals("{"))
         {
            contentPane.add(comboBox);
            comboBox.addItem("}");
            comboBox.showPopup();
         }else if(str.substring(str.lastIndexOf(" ")+1).equals("["))
                    {      
                    contentPane.add(comboBox);
                    comboBox.addItem("]");
                    comboBox.showPopup();
         }else{
       contentPane.add(comboBox);
       comboBox.addItem(string);
       comboBox.showPopup();
         }
      }
     }
    }
   } catch (Exception e1) {
   }
   if (e.getMark() < 2) {
    contentPane.remove(comboBox);
   }
  }
 }
}