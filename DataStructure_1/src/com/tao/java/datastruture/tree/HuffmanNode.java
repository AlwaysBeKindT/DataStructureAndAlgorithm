package com.tao.java.datastruture.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanNode extends Node implements Comparable<HuffmanNode> {

    protected Byte data;

    public HuffmanNode(int value, Byte data) {
        super(value);
        this.data = data;
    }

    public HuffmanNode(int value, Node left, Node right) {
        super(value, left, right);
    }

    /**
     * 获取节点对应值
     *
     * @return
     */
    public Byte getData() {
        return data;
    }

    /**
     * 将字符串转化为赫夫曼节点数列
     *
     * @param message
     * @return
     */
    public static List<HuffmanNode> getHuffmanNodeList(String message) {
        return getHuffmanNodeList(message.getBytes());
    }

    /**
     * 将二进制数组转化为赫夫曼节点数列
     *
     * @param bytes
     * @return
     */
    public static List<HuffmanNode> getHuffmanNodeList(byte[] bytes) {

        // 统计字符串出现的次数，并以此为权重
        Map<java.lang.Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            counts.merge(b, 1, Integer::sum);
        }

        // 转换为HuffmanNode对象，并生成合集
        return getHuffmanNodeList(counts);
    }

    /**
     * 将权重函数转化为赫夫曼节点数列
     *
     * @param map
     * @return
     */
    public static List<HuffmanNode> getHuffmanNodeList(Map<Byte, Integer> map) {

        List<HuffmanNode> list = new ArrayList<>();
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            list.add(new HuffmanNode(entry.getValue(), entry.getKey()));
        }
        return list;
    }

    @Override
    public String toString() {
        String s = super.toString();
        return "CodeNode{" +
                "data=" + data +
                ", " +
                s.substring(s.indexOf("{") + 1);
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return value - o.value;
    }

}
