package com.tao.java.datastruture.stack;

/**
 * @author AIERXUAN
 * @create 2022/3/14 - 21:49
 * @description
 */
public class Calculator {
    public static void main(String[] args) {
        // 算式
        String expression = "7*2+8*7-9";

        // 数栈和符号栈
        CalculatorArrayStack numStack = new CalculatorArrayStack(10);
        CalculatorArrayStack operStack = new CalculatorArrayStack(10);

        //定义变量
        int num1 = 0, num2 = 0, oper = 0, res = 0;
        char c = ' ';
        for (int index = 0; index < expression.length(); index++) {
            c = expression.charAt(index); // 获取字符
            if (operStack.isOper(c)) {  //判断为运算符
                if (!operStack.isEmpty()) { // 符号栈不为空
                    if (operStack.priority(c) <= operStack.peek()) { // 当前运算符优先级小于等于栈内运算符优先级
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        numStack.push(res);
                    }
                }
                operStack.push(c);
            } else {
                numStack.push(Integer.parseInt(c + ""));
            }
        }
        while (true){
            if(operStack.isEmpty())
                break;
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        res = numStack.pop();
        System.out.printf("表达式%s = %d", expression, res);
    }
}

class CalculatorArrayStack {
    private int maxSize; // 栈的大小
    private int[] stack; // 存储数组
    private int top = -1; // 栈顶

    public CalculatorArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        stack[++top] = value;
    }

    public int pop() {
        if (isEmpty())
            throw new RuntimeException("栈空");
        return stack[top--];
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }

    public int peek() {
        return priority(stack[top]);
    }

    // 返回+-*/运算优先级
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    // 判断是否为+-*/运算
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    // 计算
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '/':
                res = num2 / num1;
                break;
        }
        return res;
    }

}