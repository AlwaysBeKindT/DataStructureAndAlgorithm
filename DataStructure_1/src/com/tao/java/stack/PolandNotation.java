package com.tao.java.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author AIERXUAN
 * @create 2022/3/15 - 8:47
 * @description
 */
public class PolandNotation {

    public static void main(String[] args) {
        String suffixExpression = "30 14 + 5 * 6 -";
        List<String> rpnList = Arrays.asList(suffixExpression.split(" "));
        Stack<String> stack = new Stack<>();
        for (String str : rpnList) {
            if(str.matches("\\d+"))
                stack.push(str);
            else {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;
                switch (str){
                    case "+" :
                        res = num2 + num1;
                        break;
                    case "-" :
                        res = num2 - num1;
                        break;
                    case "*" :
                        res = num2 * num1;
                        break;
                    case "/" :
                        res = num2 / num1;
                        break;
                    default:
                        throw new RuntimeException("无法处理的运算符：" + str);
                }
                stack.push(res + "");
            }
        }
        System.out.println(suffixExpression + " = " + stack.pop());
    }

}
