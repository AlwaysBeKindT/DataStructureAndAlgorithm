package com.tao.java.algorithm.dijkstra;

import com.tao.java.datastruture.graph.Graph;
import java.util.Arrays;


/**
 * @author AIERXUAN
 * @create 2022/3/20 - 17:25
 * @description
 */
public class Dijkstra {

    public static void main(String[] args) {

        String[] vertex = {"A", "B", "C", "D", "E", "F", "G"};
        final int N = -1;
        int[][] matrix = new int[vertex.length][vertex.length];
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};
        int[][] weights = new int[][]{
                {0, 4, -1, -1, -1, 16, 14},
                {4, 0, 10, -1, -1, 7, -1},
                {-1, 10, 0, 3, 12, 6, -1},
                {-1, -1, 3, 0, 9, -1, -1},
                {-1, -1, 12, 9, 0, 2, 8},
                {16, 7, 6, -1, 2, 0, 5},
                {14, -1, -1, -1, 8, 5, 0}
        };

        Dijkstra dijkstra = new Dijkstra();
        DijkstraGraph graph = new DijkstraGraph(vertex.length);
        graph.addVertexAndEdge(vertex, matrix);
        graph.list();
        System.out.println();
        StringBuilder[] builders = dijkstra.dijkstra(graph, 2);
        System.out.println(Arrays.toString(builders));
        System.out.println();

        graph = new DijkstraGraph(vertex.length);
        graph.addVertexAndEdge(vertex, weights);
        graph.list();
        System.out.println();
        builders = dijkstra.dijkstra(graph, 0);
        System.out.println(Arrays.toString(builders));
        System.out.println();

    }

    /**
     * 对给定的图采用Dijkstra算法求出最短路径
     * @param graph 对给定的图
     * @param index 根节点
     * @return 根节点到各节点的路径，包括中间路径，和与根节点的距离
     */
    public StringBuilder[] dijkstra(DijkstraGraph graph, int index) {

        // 对应下标已经访问的顶点
        boolean[] visited = new boolean[graph.getVertexCount()];
        boolean[] canVisit = new boolean[graph.getVertexCount()];
        // 对应下标到根节点的距离
        int[] distance = new int[graph.getVertexCount()];
        StringBuilder[] paths = new StringBuilder[graph.getVertexCount()];
        // 对应下标的前一个节点
        int[] preVertex = new int[graph.getVertexCount()];

        for (int i = 0; i < graph.getVertexCount(); i++) {
            canVisit[i] = false;
            visited[i] = false;
            preVertex[i] = -1;
            distance[i] = 0;
            paths[i] = new StringBuilder();
        }

        // 设置根节点下标为自己
        paths[index].append(graph.getVertexByIndex(index));
        preVertex[index] = index;

        // 设置当前节点能且已访问
        visited[index] = true;
        canVisit[index] = true;

        // 判断是否已经全部访问
        for (int k = 0; k < graph.getVertexCount() - 1; k++) {

            // 获取已访问节点可访问节点
            graph.getPresentCanVisit(visited, canVisit);

            // 更新距离
            for (int j = 0; j < canVisit.length; j++) {
                if ((visited[j] ^ canVisit[j]) && graph.getWeight(index, j) > 0) {
                    if (distance[j] == 0) {
                        distance[j] = graph.getWeight(index, j) + distance[index];
                        preVertex[j] = index;
                    } else if (distance[j] > graph.getWeight(index, j) + distance[index]) {
                        distance[j] = graph.getWeight(index, j) + distance[index];
                        preVertex[j] = index;
                    }
                }
            }

            // 获取最小路径点
            int minDistance = Integer.MAX_VALUE;
            for (int j = 0; j < visited.length; j++) {
                if (!visited[j] && canVisit[j] && distance[j] < minDistance) {
                    minDistance = distance[j];
                    index = j;
                }
            }
            visited[index] = true;
            paths[index].append(paths[preVertex[index]])
                    .append("->").append(graph.getVertexByIndex(index));
        }
        for (int i = 0; i < paths.length; i++) {
            paths[i].append(":").append(distance[i]);
        }
        return paths;
    }

}

class DijkstraGraph extends Graph {

    public DijkstraGraph(int n) {
        super(n);
    }

    public void getPresentCanVisit(boolean[] visited, boolean[] canVisit) {
        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                int[] edgesByIndex = getEdgesByIndex(i);
                for (int index = 0; index < edgesByIndex.length; index++)
                    if (edgesByIndex[index] > 0)
                        canVisit[index] = true;
            }
        }
    }

}
