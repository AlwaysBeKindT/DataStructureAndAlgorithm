package com.tao.java.algorithm.prim;

import com.tao.java.datastruture.graph.Edge;
import com.tao.java.datastruture.graph.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AIERXUAN
 * @create 2022/3/20 - 10:17
 * @description
 */
public class Prim {

    public static void main(String[] args) {
        String[] s = {"A", "B", "C", "D", "E", "F", "G"};
        int[][] weights = new int[][]{
                {-1, 5, 7, -1, -1, -1, 2},
                {5, -1, -1, 9, -1, -1, 3},
                {7, -1, -1, -1, 8, -1, -1},
                {-1, 9, -1, -1, -1, 4, -1},
                {-1, -1, 8, -1, -1, 5, 4},
                {-1, -1, -1, 4, 5, -1, 6},
                {2, 3, -1, -1, 4, 6, -1}
        };

        Graph graph = new Graph(s.length);
        graph.addVertexAndEdge(s, weights);
        graph.list();
        System.out.println();
        List<Edge> result = prim(graph, 0);
        for (Edge e : result) {
            int i = e.getStart();
            int j = e.getEnd();
            System.out.println("边<" + graph.getVertexByIndex(i) + "，"
                    + graph.getVertexByIndex(j) + "> 权值：" + e.getWeight());
        }

    }

    public static List<Edge> prim(Graph graph, int v) {

        // 初始化参数
        List<Edge> selectPath = new ArrayList<>(graph.getVertexCount() - 1);
        ArrayList<Integer> visited = new ArrayList<>(graph.getVertexCount());
        ArrayList<Integer> nonVisited = new ArrayList<>(graph.getVertexCount());
        for (int i = 0, j = 0; i < graph.getVertexCount(); i++) {
            if (i == v)
                continue;
            nonVisited.add(i);
        }
        visited.add(v);
        int minWeight;
        int h1 = -1;
        int h2 = -1;

        // 需要获取graph.getVertexCount() - 1 次最短路径
        for (int i = 1; i < graph.getVertexCount(); i++) {
            minWeight = Integer.MAX_VALUE;
            // 遍历已拜访路径
            for (int j = 0; j < visited.size(); j++) {
                // 遍历未拜访路径
                for (int k = 0; k < nonVisited.size(); k++) {
                    // 从所有可达路径中选出最短路径
                    int weight = graph.getWeight(visited.get(j), nonVisited.get(k));
                    if (-1 < weight && weight < minWeight) {
                        minWeight = weight;
                        h1 = visited.get(j);
                        h2 = nonVisited.get(k);
                    }
                }
            }
            // 添加最短路径
            selectPath.add(new Edge(h1, h2, minWeight));
            // 设置已拜访
            visited.add(h2);
            // 移除未拜访
            nonVisited.remove(new Integer(h2));
        }
        return selectPath;
    }

}
