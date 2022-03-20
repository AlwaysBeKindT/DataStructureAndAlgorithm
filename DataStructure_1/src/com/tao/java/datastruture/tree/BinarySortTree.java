package com.tao.java.datastruture.tree;

/**
 * @author AIERXUAN
 * @create 2022/3/18 - 19:50
 * @description
 */
public class BinarySortTree {

    protected SortNode root;

    public BinarySortTree() {
    }

    public BinarySortTree(SortNode root) {
        this.root = root;
    }

    public void add(SortNode node) {
        if (root == null)
            root = node;
        else
            root.add(node);
    }

    public void infixSelect() {
        if (root == null)
            return;
        else
            root.infixSelect();
    }

    public SortNode getRoot() {
        return root;
    }

    public SortNode delNode(int value) {
        if (root != null) {
            SortNode del = root.selectByValue(value);
            if (del == null)
                return null;
            else {
                SortNode parent = root.selectParent(del);
                SortNode replace = del.selectMaxFromLeft();
                if (replace == null)
                    replace = del.selectMinFromRight();
                if (replace != null) {
                    replace.left = del.left;
                    replace.right = del.right;
                    del.left = null;
                    del.right = null;
                }
                if (parent != null){
                    if (parent.left == del)
                        parent.left = replace;
                    else
                        parent.right = replace;
                } else {
                    root = replace;
                }
                return del;
            }
        }
        return null;
    }

    public SortNode selectByValue(int value) {
        return root == null ? null : root.selectByValue(value);
    }

    public SortNode selectParent(SortNode node) {
        return root == null ? null : root.selectParent(node);
    }

    static class SortNode extends Node {

        public SortNode(int value) {
            super(value);
        }

        public SortNode(int value, SortNode left, SortNode right) {
            super(value, left, right);
        }

        /**
         * 添加节点，按value值排序加入
         *
         * @param node
         */
        public void add(SortNode node) {
            if (node != null) {
                if (value < node.value) {
                    if (right == null)
                        right = node;
                    else
                        ((SortNode) right).add(node);
                } else {
                    if (left == null)
                        left = node;
                    else
                        ((SortNode) left).add(node);
                }
            }
        }

        public void setLeft(SortNode left) {
            super.setLeft(left);
        }

        public void setRight(SortNode right) {
            super.setRight(right);
        }

        @Override
        public SortNode getLeft() {
            return (SortNode) super.getLeft();
        }

        @Override
        public SortNode getRight() {
            return (SortNode) super.getRight();
        }

        @Override
        public SortNode preSearch(int value) {
            return (SortNode) super.preSearch(value);
        }

        @Override
        public SortNode infixSearch(int value) {
            return (SortNode) super.infixSearch(value);
        }

        @Override
        public SortNode postSearch(int value) {
            return (SortNode) super.postSearch(value);
        }

        public SortNode selectByValue(int value) {
            if (value == this.value)
                return this;
            if (value > this.value) {
                if (right != null)
                    return ((SortNode) right).selectByValue(value);
            } else {
                if (left != null)
                    return ((SortNode) left).selectByValue(value);
            }
            return null;
        }

        public SortNode selectParent(SortNode node) {
            if ((left != null && left == node) || (right != null && right == node))
                return this;
            if (value > node.value && left != null)
                return ((SortNode) left).selectParent(node);
            if (value < node.value && right != null)
                return ((SortNode) right).selectParent(node);
            return null;
        }

        public SortNode selectMaxFromLeft() {
            if (left == null)
                return null;
            else {
                SortNode temp = (SortNode) left;
                while (temp != null) {
                    if (temp.right == null) {
                        SortNode parent = selectParent(temp);
                        parent.delNode(temp.value);
                        break;
                    }
                    temp = (SortNode) temp.right;
                }
                return temp;
            }
        }

        public SortNode selectMinFromRight() {
            if (right == null)
                return null;
            else {
                SortNode temp = (SortNode) right;
                while (temp != null) {
                    if (temp.left == null) {
                        selectParent(temp).delNode(temp.value);
                        break;
                    }
                    temp = (SortNode) temp.left;
                }
                return temp;
            }
        }
    }

    public static void main(String[] args) {
        BinarySortTree tree = new BinarySortTree();
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        for (int i = 0; i < arr.length; i++) {
            tree.add(new SortNode(arr[i]));
        }

        tree.infixSelect();
        System.out.println();

        SortNode n = null;
        if ((n = (SortNode) tree.root.selectByValue(12)) != null)
            System.out.println("找到:" + n);
        else
            System.out.println("未找到节点");

        System.out.println();
        System.out.println("删除:" + tree.delNode(3));
        System.out.println();
        System.out.println("删除:" + tree.delNode(7));
        System.out.println();
        tree.infixSelect();

    }
}
