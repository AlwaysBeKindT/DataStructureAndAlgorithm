package com.tao.java.datastruture.tree;

/**
 * @author AIERXUAN
 * @create 2022/3/19 - 9:58
 * @description
 */
public class AVLTree extends BinarySortTree {

    public AVLTree() {
    }

    public AVLTree(AVLNode root) {
        super(root);
    }

    public void add(AVLNode node) {
        if (getRoot() == null)
            root = node;
        else
            getRoot().add(node);
    }

    @Override
    public AVLNode getRoot() {
        return (AVLNode) super.getRoot();
    }

    @Override
    public AVLNode delNode(int value) {
        return (AVLNode) super.delNode(value);
    }

    @Override
    public AVLNode selectByValue(int value) {
        return (AVLNode) super.selectByValue(value);
    }

    public AVLNode selectParent(AVLNode node) {
        return (AVLNode) super.selectParent(node);
    }

    static class AVLNode extends SortNode {

        public AVLNode(int value) {
            super(value);
        }

        public AVLNode(int value, AVLNode left, AVLNode right) {
            super(value, left, right);
        }

        public int leftHeight() {
            if (left == null)
                return 0;
            else
                return ((AVLNode) left).height();
        }

        public int rightHeight() {
            if (right == null)
                return 0;
            else
                return ((AVLNode) right).height();
        }

        public int height() {
            return Math.max(left == null ? 0 : ((AVLNode) left).height(), right == null ? 0 : ((AVLNode) right).height()) + 1;
        }

        private void leftRotate() {
            AVLNode newNode = new AVLNode(value);
            newNode.left = left;
            newNode.right = right.left;
            value = right.value;
            right = right.right;
            left = newNode;
        }

        private void rightRotate() {
            AVLNode newNode = new AVLNode(value);
            newNode.right = right;
            newNode.left = left.right;
            value = left.value;
            left = left.left;
            right = newNode;
        }

        public void add(AVLNode node) {
            super.add(node);
            if (rightHeight() - leftHeight() > 1) {
                if (((AVLNode) right).leftHeight() > ((AVLNode) right).rightHeight())
                    ((AVLNode) right).rightRotate();
                leftRotate();
            } else if (leftHeight() - rightHeight() > 1) {
                if (((AVLNode) left).rightHeight() > ((AVLNode) left).leftHeight())
                    ((AVLNode) left).leftRotate();
                rightRotate();
            }
        }

        public void setLeft(AVLNode left) {
            super.setLeft(left);
        }

        public void setRight(AVLNode right) {
            super.setRight(right);
        }

        @Override
        public AVLNode getLeft() {
            return (AVLNode) super.getLeft();
        }

        @Override
        public AVLNode getRight() {
            return (AVLNode) super.getRight();
        }

        @Override
        public AVLNode preSearch(int value) {
            return (AVLNode) super.preSearch(value);
        }

        @Override
        public AVLNode infixSearch(int value) {
            return (AVLNode) super.infixSearch(value);
        }

        @Override
        public AVLNode postSearch(int value) {
            return (AVLNode) super.postSearch(value);
        }

        @Override
        public AVLNode selectByValue(int value) {
            return (AVLNode) super.selectByValue(value);
        }

        public AVLNode selectParent(AVLNode node) {
            return (AVLNode) super.selectParent(node);
        }

        @Override
        public AVLNode selectMaxFromLeft() {
            return (AVLNode) super.selectMaxFromLeft();
        }

        @Override
        public AVLNode selectMinFromRight() {
            return (AVLNode) super.selectMinFromRight();
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        int[] arr = {10, 7, 11, 8, 5, 9,};
        for (int i = 0; i < arr.length; i++) {
            tree.add(new AVLNode(arr[i]));
        }

        tree.infixSelect();

        System.out.println(tree.getRoot().height());
        System.out.println(tree.getRoot().leftHeight());
        System.out.println(tree.getRoot().rightHeight());
        System.out.println(tree.root);

    }

}
