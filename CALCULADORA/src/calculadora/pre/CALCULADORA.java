package calculadora.pre;
import java.util.Scanner;
import java.util.Stack;


public class CALCULADORA {

    /**
     * @param args the command line arguments
     */
    public String Postfijo(String infix)
    {
        Stack stack = new Stack();
        String postfix = new String();
        for(int i=0; infix!=null && i<infix.length(); i++){
            char c=infix.charAt(i);
            if(c!=' '){
                if(c=='('){
                    stack.push(c);
                }
                else if(c==')'){
                    char top = (char) stack.pop ();
                    while(top!='('){
                        postfix=postfix.concat(String.valueOf(top));
                        top=(char)stack.pop();
                    }
                }
                else if(isOperator(c)){
                    if (! stack.isEmpty ()) {
                        char top = (char) stack.pop ();
                        if (getPriority (c)> getPriority (top))
                            stack.push(top);
                        else{
                            while (getPriority(c) <= getPriority(top)) {
                                postfix = postfix.concat(String.valueOf(top));
                                if (stack.isEmpty())
                                    break;
                                top = (char) stack.pop();
                            }
                        }
                    }
                    stack.push(c);}
                        else{
                            postfix=postfix.concat(String.valueOf(c));
                        }
            }
        }
        while(!stack.isEmpty()){
            postfix=postfix.concat(String.valueOf(stack.pop()));
        }
        return postfix;
    }
    
    public static void  Prefijo(String input){ 
                    char c, tempChar; 
                    Stack <Character> s1 = new Stack <Character> (); 
                    Stack <Integer> s2 = new Stack <Integer> ();
                    Stack <Object> expression = new Stack <Object> ();
            for (int i=input.length()-1; i>=0; --i){ 
                c = input.charAt(i);
                if (Character.isDigit(c)) {
                    String s = String. valueOf(c);
                    int j = Integer.parseInt(s);
                    s2.push(j); 
                    expression.push(j); 
                }else if (isOperator2(c)) {
                    while (!s1.isEmpty() 
                    && s1.peek() != ')' 
                    && priorityCompare(c, s1.peek()) < 0) { 
                        expression.push(s1.peek()); 
                        s2.push( calc(s2.pop(), s2.pop(), s1.pop())); 
                    } 
                    s1.push(c); 
                }else if (c == ')' ) {
                    s1.push(c); 
                } else if (c == '(' ) { 
                    while ((tempChar=s1.pop()) != ')' ) { 
                        expression.push(tempChar); 
                        s2.push( calc(s2.pop(), s2.pop(), tempChar)); 
                        if (s1.isEmpty()) { 
                            throw new IllegalArgumentException( 
                            "bracket dosen't match, missing right bracket ')'."); 
                        } 
                    } 
                } else if (c == ' ' ) {
                } else { 
                    throw new IllegalArgumentException( 
                               "wrong character '" + c + "'" ); 
                } 
            }
            while (!s1.isEmpty()) { 
                tempChar = s1.pop(); 
                expression.push(tempChar); 
                s2.push( calc(s2.pop(), s2.pop(), tempChar)); 
            } 
            while (!expression.isEmpty()) { 
                System. out .print(expression.pop() + " " ); 
            } 
            int result = s2.pop(); 
            if (!s2.isEmpty()) 
                throw new IllegalArgumentException( "input is a wrong expression.");  
            System. out .println(); 
          
        }
        /**
         * @param num1
        * @param num2
        * @param op
                * @return 
        */
      private static Integer calc( int num1, int num2, char op) {
       
            switch (op) { 
            case '+' : 
                  return num1 + num2; 
            case '-' : 
                  return num1 - num2; 
            case '*' : 
                  return num1 * num2; 
            case '/' : 
                  if (num2 == 0) throw new IllegalArgumentException("divisor can't be 0." ); 
                  return num1 / num2; 
            default : 
                  return 0;
            } 
       
     }
      /**
      * @param c
      * @return
      */
        private static boolean isOperator2( char c) {
                    return (c=='+' || c=='-' || c=='*' || c=='/' ); 
        }
        /**
        * @param op1
        * @param op2
                * @return 
        */
        private static int priorityCompare( char op1, char op2) { 
            switch (op1) { 
            case '+' : case '-': 
                return (op2 == '*' || op2 == '/' ? -1 : 0); 
            case '*' : case '/': 
                return (op2 == '+' || op2 == '-' ? 1 : 0); 
            } 
            return 1; 
    }
        
        public boolean isOperator(char c)
        {
            if(c=='+' || c=='-' || c=='*' || c=='/' || c=='%' || c=='^')
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        public int getPriority(char c)
        {
            if(c=='+' || c=='-')
                return 1;
            else if(c=='*' || c=='/' || c=='%')
                return 2;
            else if(c=='^')
                return 3;
            else
                return 0;
        }
        public double numberCalculate(String result)
        {
            Stack stack=new Stack();
            for(int i=0; result!=null && i<result.length(); i++)
            {
                char c=(char)result.charAt(i);
                if(isOperator(c))
                {
                    double d2=Double.valueOf(stack.pop().toString());
                    double d1=Double.valueOf(stack.pop().toString());
                    double d3=0;
    
                    if(c=='+')
                    {
                        d3=d1+d2;
                    }
                    else if(c=='-')
                    {
                        d3=d1-d2;
                    }
                    else if(c=='*')
                    {
                        d3=d1*d2;
                    }
                    else if(c=='/')
                    {
                        d3=d1/d2;
                    }
                    else if(c=='%')
                    {
                        d3=d1%d2;
                    }
                    else if(c=='^')
                    {
                        d3=Math.pow(d1,d2);
                    }
                    stack.push(d3);
                }
                else
                {
                    stack.push(c);
                }
            }
            return (Double)stack.pop();
        }
        
        public static void main(String[] args) {
            int sa;
            do{
                CALCULADORA calculate=new CALCULADORA();
            Scanner scanner = new Scanner(System.in);
            Scanner enteros = new Scanner(System.in);
            
            System.out.println("-------CALCULADORA-------");
            System.out.println("INGRESE OPERACION EN INFIJO");
            String infijo = scanner.nextLine();
            String postfijo = calculate.Postfijo(infijo);
            //String prefijo = calculate.Prefijo(infijo);
            double resultado=calculate.numberCalculate(postfijo);
            System.out.println ("RESULTADO AL SUFIJO:" + postfijo);
            System.out.print ("RESULTADO AL PREFIJO:");
            Prefijo(infijo);
            System.out.println ("RESULTADO DE LA OPERACION:" + resultado);
            System.out.println("¿CONTINUAR? SI=1 NO=0");
            sa=enteros.nextInt();
            }while(sa!=0);
            

        }
        
    }
