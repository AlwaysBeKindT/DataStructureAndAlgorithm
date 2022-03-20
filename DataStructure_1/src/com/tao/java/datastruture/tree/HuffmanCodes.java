package com.tao.java.datastruture.tree;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author AIERXUAN
 * @create 2022/3/17 - 17:49
 * @description
 */
public class HuffmanCodes {

    protected byte[] messageBytes;
    protected HuffmanTree huffmanTree;
    protected Map<Byte, String> huffmanCodes;
    protected byte[] huffmanCodesBytes;

    public HuffmanCodes() {
    }

    /**
     * 根据指定的字符串message生成赫夫曼树
     *
     * @param message 指定的字符串
     */
    public HuffmanCodes(String message) {
        this(message.getBytes());
    }

    /**
     * 根据指定的byte数组生成赫夫曼树
     * @param messageBytes
     */
    public HuffmanCodes(byte[] messageBytes) {
        this.messageBytes = messageBytes;
        huffmanTree = new HuffmanTree(HuffmanNode.getHuffmanNodeList(messageBytes));
    }

    public void setMessage(String message) {
        this.messageBytes = message.getBytes(StandardCharsets.UTF_8);
        huffmanTree = new HuffmanTree(HuffmanNode.getHuffmanNodeList(messageBytes));
    }

    public void setMessageBytes(byte[] messageBytes) {
        this.messageBytes = messageBytes;
    }

    public HuffmanTree getHuffmanTree() {
        return huffmanTree;
    }

    /**
     * 根据赫夫曼树获取赫夫曼编码
     *
     * @return 如果返回null，请调用setMessage(String message)后在调用此方法
     */
    public Map<Byte, String> getHuffmanCode() {
        if (huffmanTree == null)
            return null;
        if (huffmanCodes == null) {
            huffmanCodes = new HashMap<>();
            getHuffmanCode(huffmanTree.getRoot(), "", new StringBuilder(), huffmanCodes);
        }
        return huffmanCodes;
    }

    /**
     * 递归获取赫夫曼叶子节点编码
     *
     * @param node        节点
     * @param code        相对路径
     * @param builder     绝对路径
     * @param huffmanCode 生成的赫夫曼编码
     */
    private void getHuffmanCode(HuffmanNode node, String code, StringBuilder builder, Map<Byte, String> huffmanCode) {

        // 生成本级赫夫曼路径
        StringBuilder newBuilder = new StringBuilder(builder);
        newBuilder.append(code);

        // 判断是否为叶子节点
        if (node.getData() == null) {
            // 向左遍历
            getHuffmanCode((HuffmanNode) node.getLeft(), "0", newBuilder, huffmanCode);
            // 向右遍历
            getHuffmanCode((HuffmanNode) node.getRight(), "1", newBuilder, huffmanCode);
        } else
            // 添加节点编码路径
            huffmanCode.put(node.getData(), newBuilder.toString());
    }

    /**
     * 压缩赫夫曼编码
     *
     * @return 如果返回null，请调用setMessage(String message)后在调用此方法
     */
    public byte[] zip() {
        if (huffmanCodesBytes == null) {
            // 判断赫夫曼编码是否为空
            if (getHuffmanCode() == null)
                return null;
            // 根据字符串byte数组和赫夫曼编码生成字符串对应的赫夫曼编码
            StringBuilder stringBuilder = new StringBuilder(messageBytes.length * 5);
            for (byte b : messageBytes) {
                String s = huffmanCodes.get(b);
                stringBuilder.append(s);
            }

            // 初始化所需的赫夫曼压缩编码byte数组
            int length = stringBuilder.length() / 8;
            length = stringBuilder.length() % 8 == 0 ? length : length + 1;
            // 为防止最后一位byte出现数据丢失，压缩时多加一位byte
            huffmanCodesBytes = new byte[length + 1];

            // 循环压缩数据
//            System.out.println();
//            System.out.println("zip");
            for (int i = 0, index = 0; i < stringBuilder.length(); i += 8) {
                // 获取8位二进制编码
                String s;
                if (i + 8 > stringBuilder.length()) {
                    s = stringBuilder.substring(i);
                } else
                    s = stringBuilder.substring(i, i + 8);
                // 压缩编码
                byte b = (byte) Integer.parseInt(s, 2);
                // 加入压缩后数组
                huffmanCodesBytes[index++] = b;
//                System.out.print(s + " ");
            }
//            System.out.println();
            // 为防止传输后最后一位byte出现数据丢失，压缩时加上最后一位byte的位数
            int lastSize = stringBuilder.length() % 8 == 0 ? 8 : stringBuilder.length() % 8;
            huffmanCodesBytes[length] = (byte) Integer.parseInt(Integer.toBinaryString(lastSize), 2);
        }
        return huffmanCodesBytes;
    }

    /**
     * 解码赫夫曼编码
     *
     * @param bytes        需要解压的赫夫曼字节数组
     * @param huffmanCode 对应的赫夫曼编码
     * @return 解压后的字符串
     */
    public static byte[] decode(byte[] bytes, Map<Byte, String> huffmanCode) {

        StringBuilder stringBuilder = new StringBuilder(bytes.length * 5);

        // 将压缩后的byte数组还原为字符串对应的赫夫曼编码
//        System.out.println();
//        System.out.println("unzip");
        for (int i = 0; i < bytes.length - 1; i++) {

            byte aByte = bytes[i];
            String s = Integer.toBinaryString(aByte);

            if(i < bytes.length - 2) {
                if (aByte >= 0)
                    s = "00000000".substring(s.length()) + s; // b大于0，补齐8位
                else
                    s =  s.substring(s.length() - 8); // b小于0返回后八位
            } else {
                // 按指定位数取出最后一位byte
                int lastSize = bytes[bytes.length - 1];
                s = "00000000".substring(8 + s.length() - lastSize) + s;
            }

            stringBuilder.append(s);
//            System.out.print(s + " ");
        }
//        System.out.println();

        // 生成解压所需的表
        Map<String, Byte> deCodes = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCode.entrySet()) {
            deCodes.put(entry.getValue(), entry.getKey());
        }

        // 根据字符串对应的赫夫曼编码和解压表生成字符串byte数列
        List<Byte> list = new ArrayList<>();
        String s = null;
        Byte b = null;
        for (int i = 0, count = 0; i < stringBuilder.length(); ) {
            b = null;
            s = null;
            while (b == null && count < stringBuilder.length()) {
                s = stringBuilder.substring(i, ++count);
                b = deCodes.get(s);
            }
            i = count;
            list.add(b);
        }

        // 根据字符串byte数列生成字符串
        byte[] result = new byte[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }

        return result;
    }

//    public static void main(String[] args) {
//        // 明文
//        String message = "Hello! this is TaoYong, how about you?";
//
//        // 生成赫夫曼树
//        HuffmanCodes codes = new HuffmanCodes(message);
//
//        // 生成并获取赫夫曼编码
//        Map<Byte, String> huffmanCodes = codes.getHuffmanCode();
////        System.out.println();
////        huffmanCodes.forEach((aByte, s) -> System.out.print((char) aByte.byteValue() + ":" + s + " "));
////        System.out.println();
//
//        // 压缩赫夫曼编码
//        byte[] zip = codes.zip();
//
//        // 解压赫夫曼编码
//        System.out.println(HuffmanCodes.decode(zip, huffmanCodes));
//
//        message = "Hello, this is QinYu.";
//        HuffmanCodes codes1 = new HuffmanCodes();
//        codes1.setMessage(message);
//        System.out.println(HuffmanCodes.decode(codes1.zip(), codes1.getHuffmanCode()));
//
//    }

}
