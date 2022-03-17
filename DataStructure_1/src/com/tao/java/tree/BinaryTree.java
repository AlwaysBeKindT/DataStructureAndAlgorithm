package com.tao.java.tree;

import org.junit.Test;

/**
 * @author AIERXUAN
 * @create 2022/3/16 - 19:58
 * @description
 */
public class BinaryTree {
    private Node root;

    public void setRoot(Node root) {
        this.root = root;
    }

    /**
     * 删除节点
     * @param id 要删除节点的id
     */
    public void delNode(int id) {
        if (root != null) {
            if (root.getId() == id)
                root = null;
            else
                root.delNode(id);
        } else
            System.out.println("root为空");
    }

    /**
     * 前序查找
     *
     * @param id 通过id查询
     * @return 找到节点就返回，否则返回null
     */
    public Node preSearch(int id) {
        if (root != null)
            return root.preSearch(id);
        else
            System.out.println("root为空");
        return null;
    }

    /**
     * 中序查找
     *
     * @param id 通过id查询
     * @return 找到节点就返回，否则返回null
     */
    public Node infixSearch(int id) {
        if (root != null)
            return root.infixSearch(id);
        else
            System.out.println("root为空");
        return null;
    }

    /**
     * 后序查找
     *
     * @param id 通过id查询
     * @return 找到节点就返回，否则返回null
     */
    public Node postSearch(int id) {
        if (root != null)
            return root.postSearch(id);
        else
            System.out.println("root为空");
        return null;
    }

    /**
     * 前序遍历
     */
    public void preSelect() {
        if (root != null)
            root.preSelect();
        else
            System.out.println("root为空");
    }

    /**
     * 中序遍历
     */
    public void infixSelect() {
        if (root != null)
            root.infixSelect();
        else
            System.out.println("root为空");
    }

    /**
     * 后序遍历
     */
    public void postSelect() {
        if (root != null)
            root.postSelect();
        else
            System.out.println("root为空");
    }

    @Test
    public void test() {
        BinaryTree tree = new BinaryTree();
        Node root = new Node(1, "Tom");
        Node n2 = new Node(2, "Jerry");
        Node n3 = new Node(3, "Sam");
        Node n4 = new Node(4, "Frank");
        Node n5 = new Node(5, "Ben");
        Node n6 = new Node(6, "Smith");
        tree.setRoot(root);
        root.setLeft(n2);
        root.setRight(n3);
        n2.setLeft(n4);
        n3.setRight(n5);
        n4.setRight(n6);

//        tree.preSelect();
//        System.out.println();
//        tree.infixSelect();
//        System.out.println();
//        tree.postSelect();
//        System.out.println();
//
//        Node n = null;
//        if ((n = root.preSearch(6)) != null)
//            System.out.println(n);
//        else
//            System.out.println("未找到节点");
//
//        n = null;
//        if ((n = root.infixSearch(6)) != null)
//            System.out.println(n);
//        else
//            System.out.println("未找到节点");
//
//        n = null;
//        if ((n = root.postSearch(6)) != null)
//            System.out.println(n);
//        else
//            System.out.println("未找到节点");

        tree.postSelect();
        System.out.println();
        tree.delNode(3);
        tree.postSelect();
        System.out.println();
        tree.delNode(1);
        tree.postSelect();
        System.out.println();

    }
}

class Node {

    private int id;
    private String name;
    private Node left;
    private Node right;

    public Node(int id, String name, Node left, Node right) {
        this(left, right);
        this.id = id;
        this.name = name;
    }

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Node(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
     * @param id 要删除节点的id
     */
    public void delNode(int id) {

        if (left != null && left.id == id) {
            left = null;
            return;
        }

        if (right != null && right.id == id) {
            right = null;
            return;
        }

        if (left != null)
            left.delNode(id);
        if(right != null)
            right.delNode(id);

        return;
    }

    /**
     * 前序查找
     *
     * @param id 通过id查询
     * @return 找到节点就返回，否则返回null
     */
    public Node preSearch(int id) {
        if (this.id == id)
            return this;
        if (left != null)
            return left.preSearch(id);
        if (right != null)
            return right.preSearch(id);
        return null;
    }

    /**
     * 中序遍历
     *
     * @param id 通过id查询
     * @return 找到节点就返回，否则返回null
     */
    public Node infixSearch(int id) {
        if (left != null)
            return left.infixSearch(id);
        if (this.id == id)
            return this;
        if (right != null)
            return right.infixSearch(id);
        return null;
    }

    /**
     * 后序遍历
     *
     * @param id 通过id查询
     * @return 找到节点就返回，否则返回null
     */
    public Node postSearch(int id) {
        if (left != null)
            return left.postSearch(id);
        if (right != null)
            return right.postSearch(id);
        if (this.id == id)
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
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
