package com.tao.java.tree;

/**
 * @author AIERXUAN
 * @create 2022/3/17 - 9:41
 * @description
 */
public class ArrayBinaryTree {
    private int[] arrays;
    public static final int PRE = 0;
    public static final int INFIX = 1;
    public static final int POST = 2;

    public ArrayBinaryTree(int[] arrays) {
        this.arrays = arrays;
    }

    /**
     * 前序遍历
     */
    public void preSelect(int index) {
        if (!checkEmpty()) {
            if (checkInBounds(index)) {
                System.out.println(arrays[index]);
                preSelect(index * 2 + 1);
                preSelect(index * 2 + 2);
            }
        }
    }

    /**
     * 中序遍历
     */
    public void infixSelect(int index) {
        if (!checkEmpty()) {
            if (checkInBounds(index)) {
                infixSelect(index * 2 + 1);
                System.out.println(arrays[index]);
                infixSelect(index * 2 + 2);
            }
        }
    }

    /**
     * 后序遍历
     */
    public void postSelect(int index) {
        if (!checkEmpty()) {
            if (checkInBounds(index)) {
                if (index * 2 + 1 < arrays.length)
                    postSelect(index * 2 + 1);
                if (index * 2 + 2 < arrays.length)
                    postSelect(index * 2 + 2);
                System.out.println(arrays[index]);
            }
        }
    }

    /**
     * 通过value查找节点 找到对应值返回index 其他-1 有重复值返回找到的第一个
     * @param value
     * @param type 查找方式:前序查找PRE 中序查找INFIX 后序查找POST 错误的类型数字会导致异常
     * @return 找到对应值返回index 其他-1
     */
    public int searchMethod(int value, int type) {
        switch (type) {
            case PRE:
                return preSearch(value, 0);
            case INFIX:
                return infixSearch(value, 0);
            case POST:
                return postSearch(value, 0);
        }
        throw new RuntimeException("错误的类型:" + type);
    }

    /**
     * 前序查找
     */
    private int preSearch(int value, int index) {
        if (!checkEmpty()) {
            if (checkInBounds(index)) {
                if (arrays[index] == value)
                    return index;
                int i = preSearch(value, index * 2 + 1);
                return i != -1 ? i : preSearch(value, index * 2 + 2);
            }
        }
        return -1;
    }

    /**
     * 中序查找
     */
    private int infixSearch(int value, int index) {
        if (!checkEmpty()) {
            if (checkInBounds(index)) {
                if (arrays[index] == value)
                    return index;
                int i = infixSearch(value, index * 2 + 1);
                return i != -1 ? i : infixSearch(value, index * 2 + 2);
            }
        }
        return -1;
    }

    /**
     * 后序查找
     */
    private int postSearch(int value, int index) {
        if (!checkEmpty()) {
            if (checkInBounds(index)) {
                if (arrays[index] == value)
                    return index;
                int i = postSearch(value, index * 2 + 1);
                return i != -1 ? i : postSearch(value, index * 2 + 2);
            }
        }
        return -1;
    }

    private boolean checkEmpty() {
        if (arrays == null || arrays.length == 0) {
            System.out.println("数组为空");
            return true;
        }
        return false;
    }

    private boolean checkInBounds(int index) {
        if (0 < index && index < arrays.length)
            return false;//throw new IndexOutOfBoundsException("index(" + index + ")不在数组范围(0," + arrays.length + ")内");
        return true;
    }

}
