package com.tao.java.algorithm.kruskal;

import com.tao.java.datastruture.graph.Edge;
import com.tao.java.datastruture.graph.Graph;

import java.util.*;

/**
 * @author AIERXUAN
 * @create 2022/3/20 - 12:53
 * @description
 */
public class Kruskal {

    // 获取最断路径（最低权重）链路
    public static List<Edge> kruskal(Graph graph) {
        List<Edge> edges = new ArrayList<>(graph.getNumOfEdges());
        List<Edge> selectEdges = new ArrayList<>(graph.getNumOfEdges());
        int[][] graphEdges = graph.getEdges();
        int[] lastEnd = new int[graph.getVertexCount()];
        int visited = 0;
        for (int i = 0; i < graphEdges.length; i++) {
            for (int j = i + 1; j < graphEdges[i].length; j++) {
                if (graphEdges[i][j] > 0)
                    edges.add(new Edge(i, j, graphEdges[i][j]));
            }
        }
        Collections.sort(edges);
        Iterator<Edge> iterator = edges.iterator();
        while (visited < graph.getVertexCount() - 1 && iterator.hasNext()) {
            Edge next = iterator.next();
            int p1 = next.getStart();
            int p2 = next.getEnd();
            int e1 = genEnd(lastEnd, p1);
            int e2 = genEnd(lastEnd, p2);
            if (e1 != e2) {
                lastEnd[e1] = e2;
                selectEdges.add(next);
                visited++;
            }
        }
        return selectEdges;
    }

    // 返回通路中最大的节点，为0表示不连通
    public static int genEnd(int[] ends, int i) {
        while (ends[i] != 0)
            i = ends[i];
        return i;
    }

    public static void main(String[] args) {
        String[] s = {"A", "B", "C", "D", "E", "F", "G"};
        int[][] weights = new int[][]{
                {0, 4, -1, -1, -1, 16, 14},
                {4, 0, 10, -1, -1, 7, -1},
                {-1, 10, 0, 3, 12, 6, -1},
                {-1, -1, 3, 0, 9, -1, -1},
                {-1, -1, 12, 9, 0, 2, 8},
                {16, 7, 6, -1, 2, 0, 5},
                {14, -1, -1, -1, 8, 5, 0}
        };

        Graph graph = new Graph(s.length);
        graph.addVertexAndEdge(s, weights);
        graph.list();
        System.out.println();
        List<Edge> selectEdges = kruskal(graph);
        for (Edge e : selectEdges) {
            System.out.println(graph.getVertexByIndex(e.getStart()) + "->"
                    + graph.getVertexByIndex(e.getEnd()) + " " + e.getWeight());
        }

    }

}

