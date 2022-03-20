package com.tao.java.datastruture.tree;

import java.util.*;

/**
 * @author AIERXUAN
 * @create 2022/3/17 - 15:57
 * @description
 */
public class HuffmanTree {

    protected HuffmanNode root;

    /**
     * 将包含权重和值的二叉树数列转化为赫夫曼树
     *
     * @param list 为包含权值和值的二叉树数列，可通过静态方法getHuffmanNodeList()获取对应数列
     */
    public HuffmanTree(List<HuffmanNode> list) {

        while (list.size() > 1) {
            Collections.sort(list);

            HuffmanNode left = list.get(0);
            HuffmanNode right = list.get(1);

            HuffmanNode root = new HuffmanNode(left.getValue() + right.getValue(), left, right);
            list.remove(left);
            list.remove(right);
            list.add(root);
        }
        root = list.get(0);
    }

    /**
     * 获取根节点
     *
     * @return
     */
    public HuffmanNode getRoot() {
        return root;
    }


//    public static void main(String[] args) {
//        List<HuffmanNode> list = new ArrayList();
//        list.add(new HuffmanNode(13));
//        list.add(new HuffmanNode(7));
//        list.add(new HuffmanNode(8));
//        list.add(new HuffmanNode(29));
//        list.add(new HuffmanNode(6));
//        list.add(new HuffmanNode(1));
//        HuffmanTree tree = new HuffmanTree(list);
//        tree.getRoot().preSelect();
//    }

}

