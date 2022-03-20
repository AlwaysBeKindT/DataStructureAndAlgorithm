package com.tao.java.datastruture.tree;

import org.junit.Test;

/**
 * @author AIERXUAN
 * @create 2022/3/16 - 19:58
 * @description
 */
public class BinaryTree {
    protected Node root;

    public void setRoot(Node root) {
        this.root = root;
    }

    /**
     * 删除节点
     * @param id 要删除节点的id
     */
    public void delNode(int id) {
        if (root != null) {
            if (root.getValue() == id)
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
        Node root = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        tree.setRoot(root);
        root.setLeft(n2);
        root.setRight(n3);
        n2.setLeft(n4);
        n3.setRight(n5);
        n4.setRight(n6);

        tree.preSelect();
        System.out.println();
        tree.infixSelect();
        System.out.println();
        tree.postSelect();
        System.out.println();

        Node n = null;
        if ((n = root.preSearch(6)) != null)
            System.out.println(n);
        else
            System.out.println("未找到节点");

        n = null;
        if ((n = root.infixSearch(6)) != null)
            System.out.println(n);
        else
            System.out.println("未找到节点");

        n = null;
        if ((n = root.postSearch(6)) != null)
            System.out.println(n);
        else
            System.out.println("未找到节点");

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

