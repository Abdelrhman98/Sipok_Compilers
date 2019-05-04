package compiler;

public class tokensStack {
    
    private String[] tokens;
    private int size;
    private int stack_length;
    
    public tokensStack(int length)
    {
        this.stack_length = length;
        tokens = new String[length];
        this.size = 0;
    }
    
    public void push(String a)
    {
        if(this.size!=this.stack_length)
        {
            tokens[this.size]= a;
            this.size++;
        }
    }
    
    public String pop()
    {
        if(this.size!=0)
        {
            String result = tokens[this.size-1];
            tokens[this.size-1] = "";
            this.size--;
            return result;
        }
        return "$";
    }
       
    public int stack_size()
    {
        return this.size;
    }
}
