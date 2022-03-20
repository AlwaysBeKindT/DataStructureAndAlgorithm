package com.tao.java.datastruture.tree;

/**
 * @author AIERXUAN
 * @create 2022/3/17 - 16:24
 * @description
 */
public class Node {

    protected int value;
    protected Node left;
    protected Node right;

    public Node(int value) {
        this.value = value;
    }

    public Node(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    public Node(int value, Node left, Node right) {
        this(left, right);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    /**
     * 删除节点
     *
     * @param value 要删除节点的id
     */
    public void delNode(int value) {

        if (left != null && left.value == value) {
            left = null;
            return;
        }

        if (right != null && right.value == value) {
            right = null;
            return;
        }

        if (left != null)
            left.delNode(value);
        if (right != null)
            right.delNode(value);
    }

    /**
     * 前序查找
     *
     * @param value 通过id查询
     * @return 找到节点就返回，否则返回null
     */
    public Node preSearch(int value) {
        if (this.value == value)
            return this;
        if (left != null)
            return left.preSearch(value);
        if (right != null)
            return right.preSearch(value);
        return null;
    }

    /**
     * 中序查找
     *
     * @param value 通过id查询
     * @return 找到节点就返回，否则返回null
     */
    public Node infixSearch(int value) {
        if (left != null)
            return left.infixSearch(value);
        if (this.value == value)
            return this;
        if (right != null)
            return right.infixSearch(value);
        return null;
    }

    /**
     * 后序查找
     *
     * @param value 通过id查询
     * @return 找到节点就返回，否则返回null
     */
    public Node postSearch(int value) {
        if (left != null)
            return left.postSearch(value);
        if (right != null)
            return right.postSearch(value);
        if (this.value == value)
            return this;
        return null;
    }

    /**
     * 前序遍历
     */
    public void preSelect() {
        System.out.println(this);
        if (left != null)
            left.preSelect();
        if (right != null)
            right.preSelect();
    }

    /**
     * 中序遍历
     */
    public void infixSelect() {
        if (left != null)
            left.infixSelect();
        System.out.println(this);
        if (right != null)
            right.infixSelect();
    }

    /**
     * 后序遍历
     */
    public void postSelect() {
        if (left != null)
            left.postSelect();
        if (right != null)
            right.postSelect();
        System.out.println(this);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "value=" + value +
                '}';
    }

}
