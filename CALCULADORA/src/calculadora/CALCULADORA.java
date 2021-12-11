/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calculadora;

import java.util.Stack;
import javax.swing.JLabel;

/**
 *
 * @author braya
 */
public class CALCULADORA {

    /**
     * @param args the command line arguments
     */

    static void convertir(String infija,JLabel mostrarPre, JLabel mostrarInfi){
       
       mostrarPre.setText("Notación prefija: " + infijaAPrefija(infija));
       mostrarInfi.setText("Notación postfija: " + infijaAPostfija(infija));
     
    }
        static int precedence(char c){
        switch (c){
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }
    static StringBuilder infijaAPrefija(String expression){

       StringBuilder result = new StringBuilder();
       StringBuilder input = new StringBuilder(expression);
       input.reverse();
       Stack<Character> stack = new Stack<>();

       char [] charsExp = new String(input).toCharArray();
       for (int i = 0; i < charsExp.length; i++) {

            if (charsExp[i] == '(') {
                charsExp[i] = ')';
                i++;
            }
            else if (charsExp[i] == ')') {
                charsExp[i] = '(';
                i++;
            }
        }

        for (int i = 0; i <charsExp.length ; i++) {
            char c = charsExp[i];
            //verificamos si C es operando o operador
            if(precedence(c)>0){
               //cuando es mayor que 0 significa que es un operando
                while(stack.isEmpty()==false && precedence(stack.peek())>precedence(c)){
                    //mientras la pila tenga datos, y 
                    //la precendencia de de la pila sea mayor 
                    //a la precendencia del caracter
                    //se colocan los datos de la pila en el resultado
                    Character dato = stack.pop();
                    result.append(dato);
                                   }
                //Si la pila esta vacia o el operando de la pila es de menor presedencia que 
                // el operando de del caracter se agrega el operando a la pila
                stack.push(c);
            }else if(c==')'){
                char x = stack.pop();
                    while(x!='('){
                    result.append(x);
                    x = stack.pop();
                    }
                }else if(c=='('){
                stack.push(c);
                    }else{
                    //el caracater no es operando ni "("
                    result.append(c);
                    }
        }

        for (int i = 0; i <=stack.size()+1 ; i++) {
           Character dato = stack.pop();
            result.append(dato);
        }
        return result.reverse();
    }

    static String infijaAPostfija(String expression){

        String result = "";
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i <expression.length() ; i++) {
            char c = expression.charAt(i);

            //verificamos si el carcater es un operador
            if(precedence(c)>0){
                while(stack.isEmpty()==false && precedence(stack.peek())>=precedence(c)){
                    result += stack.pop();
                    //mientras la pila tenga datos, y 
                    //la precendencia de de la pila sea mayor 
                    //o igual a la precendencia del caracter
                    //se colocan los datos de la pila en el resultado
                }
                //Si la pila esta vacia o el operadoR de la pila es de menor presedencia que 
                // el operadoR de del caracter se agrega el operadoR a la pila
                stack.push(c);
            }else if(c==')'){
                char x = stack.pop();
                while(x!='('){
                    result += x;
                    x = stack.pop();
                }
            }else if(c=='('){
                stack.push(c);
            }else{
                //el caracter no es operado ni (
                result += c;
            }
        }
        for (int i = 0; i <=stack.size() ; i++) {
            result += stack.pop();
        }
        return result;
    }

}
