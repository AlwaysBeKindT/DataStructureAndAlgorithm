package com.tao.java.project;

import java.io.*;
import java.util.Map;
import com.tao.java.datastruture.tree.HuffmanCodes;

/**
 * @author AIERXUAN
 * @create 2022/3/18 - 13:08
 * @description
 */
public class HuffmanZip {

    public static void zipFile(String scrFile, String dstFile) {
        try (FileInputStream fis = new FileInputStream(scrFile);
             FileOutputStream fos = new FileOutputStream(dstFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos);)
        {
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            HuffmanCodes codes = new HuffmanCodes(bytes);
            System.out.println((bytes.length - codes.zip().length + 0.0) / bytes.length);
            oos.writeObject(new ZipFile(codes.zip(), codes.getHuffmanCode()));
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void decodeFile(String scrFile, String dstFile) {
        try (FileInputStream fis = new FileInputStream(scrFile);
             FileOutputStream fos = new FileOutputStream(dstFile);
             ObjectInputStream ois = new ObjectInputStream(fis);)
        {
            ZipFile zipFile = (ZipFile) ois.readObject();
            HuffmanCodes codes = new HuffmanCodes();
            fos.write(HuffmanCodes.decode(zipFile.getBytes(), zipFile.getHuffmanCode()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static class ZipFile implements Serializable {
        private byte[] bytes;
        private Map<Byte, String> huffmanCode;

        public ZipFile(byte[] bytes, Map<Byte, String> huffmanCode) {
            this.bytes = bytes;
            this.huffmanCode = huffmanCode;
        }

        public byte[] getBytes() {
            return bytes;
        }

        public Map<Byte, String> getHuffmanCode() {
            return huffmanCode;
        }
    }

}
