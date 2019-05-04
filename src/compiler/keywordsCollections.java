package compiler;

import java.util.*;

public class keywordsCollections {

    private char tran[];
    private int[][] transition;
    private int rowSize = 150, colSize = 100, accSize = 20, minKeySize = 2, maxKeySize = 12, maxLength = 30;
    private int acc[];
    private int prevstate;
    private int currentState;
    private char[] CurrentToken;
    private String returnTokenType;
    private boolean isSigned;

    public keywordsCollections() {
        this.tran = new char[this.colSize];
        this.transition = new int[this.rowSize][this.colSize];
        this.acc = new int[this.accSize];
        this.CurrentToken = new char[this.maxLength];
        this.fillDec();
        this.fillAcc();
        this.fill();

    }

    // tested
    public boolean isNumber() {
        for (char number : this.CurrentToken) {
            if (number >= '0' && number <= '9') {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean isIdentifier() {

        for (char letter : this.CurrentToken) {
            if (letter >= 'a' && letter <= 'z' || letter >= 'A' && letter <= 'Z' || letter == '_') {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean isAcc() {

        for (int index : this.acc) {
            if (this.currentState == index && this.currentState != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isStartOfKeyWord(String str) {
        this.CurrentToken = str.toCharArray();
        this.currentState = 1;
        for (char letter : this.CurrentToken) {
            if(letter == 'S')
                this.isSigned = true;
            int currentLetter = getI(letter);
            this.prevstate = this.currentState;
            this.currentState = this.transition[this.currentState][currentLetter];
            if (this.currentState == 0) {
                return false;
            }
        }
        if (this.isAcc()) {
            return true;
        }
        return false;
    }

    public void Get_type() {
        if(prevstate==1){
            prevstate = this.currentState;
        }
        switch (prevstate) {
            case 74:
                this.returnTokenType = "Logic_operators";
                break;
            case 73:
                this.returnTokenType = "Logic_operators";
                break;
            case 64:
                this.returnTokenType = "Character";
                break;
            case 61:
                this.returnTokenType = "Loop";
                break;
            case 55:
                this.returnTokenType = "String";
                break;
            case 77:
                this.returnTokenType = "Switch";
                break;
            case 75:
                this.returnTokenType = "Switch";
                break;
            case 5:
                if(this.isSigned){
                    this.returnTokenType = "SFloat";
                    isSigned = false;
                }else
                    this.returnTokenType = "Float";
                
                break;
            case 8:
                this.returnTokenType = "Inheritance";
                break;
            case 4:
                if(this.isSigned){
                    this.returnTokenType = "SInteger";
                    isSigned = false;
                }else
                this.returnTokenType = "Integer";
                break;
            case 2:
                this.returnTokenType = "Condition";
                break;
            case 20:
                this.returnTokenType = "Void";
                break;
            case 10:
                this.returnTokenType = "Assignment_operators";
                break;
            case 11:
                this.returnTokenType = "relational_operators";
                break;
            case 112:
                this.returnTokenType = "relational_operators";
                break;
            case 13:
                this.returnTokenType = "relational_operators";
                break;
            case 25:
                this.returnTokenType = "None";
                break;
            case 28:
                this.returnTokenType = "None";
                break;
            case 35:
                this.returnTokenType = "Boolean";
                break;
            case 48:
                this.returnTokenType = "Inclusion";
                break;
            case 44:
                this.returnTokenType = "Return";
                break;
            case 87:
                this.returnTokenType = "Struct";
                break;
            case 72:
                this.returnTokenType = "None";
                break;
            case 90:
                this.returnTokenType = "Class";
                break;
            case 93:
                this.returnTokenType = "Loop";
                break;

            case 67:
                this.returnTokenType = "Condition";
            break;
            case 97:
                this.returnTokenType = "Break";
            break;
            case 86:
                this.returnTokenType = "Condition";
            break;
            case 98:
                this.returnTokenType = "Stat Symbol";
            break;
            case 99:
                this.returnTokenType = "End Symbol";
            break;
            case 100:
                this.returnTokenType = "Arithmetic Operation";
            break;
            case 101:
                this.returnTokenType = "Logic operators";
            break;
            case 102:
                this.returnTokenType = "Quotation Mark";
            break;
            case 103:
                this.returnTokenType = "Access Operator";
            break;
        }
        //System.out.println(this.returnTokenType);

    }

    public String checkToken(String token) {
        this.CurrentToken = token.toCharArray();
        this.returnTokenType = "";
        if (this.isStartOfKeyWord(token)) {
            this.Get_type();
        } else if (this.isIdentifier()) {
            this.returnTokenType = "Identifer";

        } else if (this.isNumber()) {
            this.returnTokenType = "Constant";
        }

        return this.returnTokenType;
    }

    private void fillAcc() {
        this.acc[0] = 75;
        this.acc[1] = 5;
        this.acc[2] = 10;
        this.acc[3] = 12;
        this.acc[4] = 13;
        this.acc[5] = 98;
        this.acc[6] = 99;
        this.acc[7] = 100;
        this.acc[9] = 101;
        this.acc[10] = 102;
        
        
    }

    private void fill() {
        // start of Sequence
        this.setState(1, 'S', 49);
        this.setState(49, 'e', 50);
        this.setState(50, 'q', 51);
        this.setState(51, 'u', 52);
        this.setState(52, 'e', 53);
        this.setState(53, 'n', 54);
        this.setState(54, 'c', 55);
        this.setState(55, 'e', 75);
        //start of Infer
        this.setState(2, 'n', 6);
        this.setState(6, 'f', 7);
        this.setState(7, 'e', 8);
        this.setState(8, 'r', 75);

        //start of Srap
        this.setState(49, 'r', 86);
        this.setState(86, 'a', 87);
        this.setState(87, 'p', 75);

        //start of Type
        this.setState(1, 'T', 88);
        this.setState(88, 'y', 89);
        this.setState(89, 'p', 90);
        this.setState(90, 'e', 75);

        //start of When
        this.setState(1, 'W', 91);
        this.setState(91, 'h', 92);
        this.setState(92, 'e', 93);
        this.setState(93, 'n', 75);

        // start of Ipok 
        this.setState(1, 'I', 2);
        this.setState(2, 'p', 3);
        this.setState(3, 'o', 4);
        this.setState(4, 'k', 5);//acc

        // start of Ipokf
        this.setState(5, 'f', 75);//acc

        // start of if
        this.setState(2, 'f', 75);//acc

        //start of Valueless
        this.setState(1, 'V', 9);
        this.setState(9, 'a', 14);
        this.setState(14, 'l', 15);
        this.setState(15, 'u', 16);
        this.setState(16, 'e', 17);
        this.setState(17, 'l', 18);
        this.setState(18, 'e', 19);
        this.setState(19, 's', 20);
        this.setState(20, 's', 75);

        //start of return 
        this.setState(1, 'r', 21);
        this.setState(21, 'e', 22);
        this.setState(22, 't', 23);
        this.setState(23, 'u', 24);
        this.setState(24, 'r', 25);
        this.setState(25, 'n', 75);

        //start of None
        this.setState(1, 'N', 26);
        this.setState(26, 'o', 27);
        this.setState(27, 'n', 28);
        this.setState(28, 'e', 75);

        // start of Rational
        this.setState(1, 'R', 29);
        this.setState(29, 'a', 30);
        this.setState(30, 't', 31);
        this.setState(31, 'i', 32);
        this.setState(32, 'o', 33);
        this.setState(33, 'n', 34);
        this.setState(34, 'a', 35);
        this.setState(35, 'l', 75);

        //start of Respondwith
        this.setState(29, 'e', 36);
        this.setState(36, 's', 37);
        this.setState(37, 'p', 38);
        this.setState(38, 'o', 39);
        this.setState(39, 'n', 40);
        this.setState(40, 'd', 41);
        this.setState(41, 'w', 42);
        this.setState(42, 'i', 43);
        this.setState(43, 't', 44);
        this.setState(44, 'h', 75);

        // start of Require
        this.setState(36, 'q', 45);
        this.setState(45, 'u', 46);
        this.setState(46, 'i', 47);
        this.setState(47, 'r', 48);
        this.setState(48, 'e', 75);

        //start of signs {;,#,$,{,},[,],(,),+,-,/,*, , , . , ^ , @}
        this.setState(1, ';', 75);
        this.setState(1, '#', 99);
        this.setState(1, '$', 99);
        this.setState(1, '{', 75);
        this.setState(1, '}', 75);
        this.setState(1, '[', 75);
        this.setState(1, ']', 75);
        this.setState(1, '(', 75);
        this.setState(1, ')', 75);
        
        this.setState(1, '+', 100);
        this.setState(1, '-', 100);
        this.setState(1, '/', 100);
        this.setState(1, '*', 100);
        
        this.setState(1, ',', 102);
        
        this.setState(1, '.', 75);
        this.setState(1, '^', 98);
        this.setState(1, '@', 98);

        this.setState(1, '-', 103);
        this.setState(103, '>', 75);//acc
        
        
        // start of arthi [<, <= , > , >=, ==,!=]
        this.setState(1, '=', 10);//acc
        this.setState(1, '!', 11);
        this.setState(1, '>', 12);//acc
        this.setState(1, '<', 13);//acc
        this.setState(10, '=', 75);
        this.setState(11, '=', 75);
        this.setState(12, '=', 75);
        this.setState(13, '=', 75);

        //start of Sipok
        this.setState(49, 'i', 2);

        //start of Conditionof ****
        this.setState(62, 'o', 78);
        this.setState(78, 'n', 79);
        this.setState(79, 'd', 80);
        this.setState(80, 'i', 81);
        this.setState(81, 't', 82);
        this.setState(82, 'i', 83);
        this.setState(83, 'o', 84);
        this.setState(84, 'n', 85);
        this.setState(85, 'o', 86);
        this.setState(86, 'f', 75);

        // start of Scan
        this.setState(49, 'c', 76);
        this.setState(76, 'a', 77);
        this.setState(77, 'n', 75);

        //_________________________________//
        //start of ||
        this.setState(1, '|', 74);
        this.setState(74, '|', 101);

        //start of &&
        this.setState(1, '&', 73);
        this.setState(1, '~', 101);
        this.setState(73, '&', 101);

        //start of print
        this.setState(1, 'P', 69);
        this.setState(69, 'r', 70);
        this.setState(70, 'i', 71);
        this.setState(71, 'n', 72);
        this.setState(72, 't', 75);

        //start of Endthis
        this.setState(1, 'E', 65);
        this.setState(65, 'n', 68);
        this.setState(68, 'd', 94);
        this.setState(94, 't', 95);
        this.setState(95, 'h', 96);
        this.setState(96, 'i', 97);
        this.setState(97, 's', 75);

        
        //start of Else
        this.setState(1, 'E', 65);
        this.setState(65, 'l', 66);
        this.setState(66, 's', 67);
        this.setState(67, 'e', 75);

        //strat of craf
        this.setState(1, 'C', 62);
        this.setState(62, 'r', 63);
        this.setState(63, 'a', 64);
        this.setState(64, 'f', 75);

        //start of however
        this.setState(1, 'H', 56);
        this.setState(56, 'o', 57);
        this.setState(57, 'w', 58);
        this.setState(58, 'e', 59);
        this.setState(59, 'v', 60);
        this.setState(60, 'e', 61);
        this.setState(61, 'r', 75);

    }

    private void setState(int row, char col, int val) {
        int intCol = this.getI(col);
        if (row <= this.rowSize && intCol <= this.colSize) {
            this.transition[row][intCol] = val;
        }
    }

    public int getI(char ch) {
        for (int i = 0; i < this.colSize; i++) {
            if (ch == this.tran[i]) {
                return i;
            }
        }
        return 0;
    }

    public void print() {
        for (int i = 0; i < this.rowSize; i++) {
            for (int j = 0; j < this.colSize; j++) {
                System.out.print(this.transition[i][j] + " ");
            }
            System.out.println();
        }

    }

    private void fillDec() {
        tran[0] = 'A';
        tran[1] = 'B';
        tran[2] = 'C';
        tran[3] = 'D';
        tran[4] = 'E';
        tran[5] = 'F';
        tran[6] = 'G';
        tran[7] = 'H';
        tran[8] = 'I';
        tran[9] = 'J';
        tran[10] = 'K';
        tran[11] = 'L';
        tran[12] = 'M';
        tran[13] = 'N';
        tran[14] = 'O';
        tran[15] = 'P';
        tran[16] = 'Q';
        tran[17] = 'R';
        tran[18] = 'S';
        tran[29] = 'T';
        tran[20] = 'U';
        tran[21] = 'V';
        tran[22] = 'W';
        tran[23] = 'X';
        tran[24] = 'Y';
        tran[25] = 'Z';
        tran[26] = 'a';
        tran[27] = 'b';
        tran[28] = 'c';
        tran[29] = 'd';
        tran[30] = 'e';
        tran[31] = 'f';
        tran[32] = 'g';
        tran[33] = 'h';
        tran[34] = 'i';
        tran[35] = 'j';
        tran[36] = 'k';
        tran[37] = 'l';
        tran[38] = 'm';
        tran[39] = 'n';
        tran[40] = 'o';
        tran[41] = 'p';
        tran[42] = 'q';
        tran[43] = 'r';
        tran[45] = 's';
        tran[46] = 't';
        tran[47] = 'u';
        tran[48] = 'v';
        tran[49] = 'w';
        tran[50] = 'x';
        tran[51] = 'y';
        tran[52] = 'z';
        tran[53] = ';';
        tran[54] = '.';
        tran[55] = ',';
        tran[56] = '<';
        tran[57] = '>';
        tran[58] = '=';
        tran[59] = '^';
        tran[60] = '|';
        tran[61] = '[';
        tran[62] = ']';
        tran[63] = '(';
        tran[64] = ')';
        tran[65] = '&';
        tran[66] = '@';
        tran[67] = '^';
        tran[68] = '#';
        tran[69] = '$';
        tran[70] = '+';
        tran[71] = '-';
        tran[72] = '/';
        tran[73] = '*';
        tran[74] = '!';
        tran[75] = '{';
        tran[76] = '}';
        tran[77] = '~';
        
    }

}
/*
 -create an array have all letter and special chars (Done)
 -create an array 
 - add {-}
 */
