package com.tao.java.algorithm.greedy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author AIERXUAN
 * @create 2022/3/20 - 8:54
 * @description
 */
public class Greedy {

    HashMap<String, HashSet<String>> broadCast;
    HashSet<String> allArea;
    ArrayList<String> selected;

    public Greedy(HashMap<String, HashSet<String>> broadCast) {
        this.broadCast = broadCast;
        allArea = new HashSet<>();
        for (HashSet<String> set : broadCast.values()) {
            allArea.addAll(set);
        }
    }

    public ArrayList<String> getSelect() {
        if(selected == null){
            String maxKey;
            HashSet<String> tempSet = new HashSet<>();
            selected = new ArrayList<>(broadCast.size());
            while (allArea.size() > 0) {
                maxKey = null;
                for (String key : broadCast.keySet()) {
                    if (!selected.contains(key)) {
                        tempSet.clear();
                        tempSet.addAll(broadCast.get(key));
                        tempSet.retainAll(allArea);
                        if (tempSet.size() > 0) {
                            if (maxKey != null) {
                                HashSet<String> maxValue = broadCast.get(maxKey);
                                maxValue.retainAll(allArea);
                                if (tempSet.size() <= maxValue.size())
                                    continue;
                            }
                            maxKey = key;
                        }
                    }
                }
                selected.add(maxKey);
                allArea.removeAll(broadCast.get(maxKey));
            }
        }
        return selected;
    }

    public static void main(String[] args) {

        HashMap<String, HashSet<String>> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        HashSet<String> set1 = new HashSet<>();
        HashSet<String> set2 = new HashSet<>();
        HashSet<String> set3 = new HashSet<>();
        HashSet<String> set4 = new HashSet<>();
        set.add("??????");
        set.add("??????");
        set.add("??????");
        map.put("K1", set);

        set1.add("??????");
        set1.add("??????");
        set1.add("??????");
        map.put("K2", set1);

        set2.add("??????");
        set2.add("??????");
        set2.add("??????");
        map.put("K3", set2);

        set3.add("??????");
        set3.add("??????");
        map.put("K4", set3);

        set4.add("??????");
        set4.add("??????");
        map.put("K5", set4);

        Greedy g = new Greedy(map);
        g.getSelect().forEach(System.out::println);

    }
}
