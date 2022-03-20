package com.tao.java.datastruture.graph;

import java.util.ArrayList;

/**
 * @author AIERXUAN
 * @create 2022/3/19 - 11:26
 * @description
 */
public class Graph {

    protected ArrayList<String> vertexList; // 顶点列表
    protected int[][] edges; //邻结矩阵
    protected boolean[] isVisited;
    protected int numOfEdges;

    public Graph(int n) {
        vertexList = new ArrayList<>(n);
        isVisited = new boolean[n];
        edges = new int[n][n];
        numOfEdges = 0;
    }

    /**
     * 显示图对应的矩阵
     */
    public void list() {
        for (String s : vertexList) {
            System.out.print("\t" + s);
        }
        System.out.println();
        for (int j = 0; j < edges.length; j++) {
            int[] edge = edges[j];
            System.out.print(vertexList.get(j) + "\t");
            for (int i : edge) {
                if (i >= 0)
                    System.out.print(i + "\t");
                else
                    System.out.print("*\t");
//                if (i < 999)
//                    System.out.print("\t");
            }
            System.out.println();
        }
    }

    /**
     * 插入顶点
     * @param vertex
     */
    public void addVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     * @param v1     边对应顶点1
     * @param v2     边对应顶点2
     * @param weight 权重
     */
    public void addEdge(int v1, int v2, int weight) {
        if ((0 <= v1 && v1 < edges.length) || (0 <= v2 && v2 < edges[0].length)){
            edges[v1][v2] = weight;
            edges[v2][v1] = weight;
            if (weight >= 0)
                numOfEdges++;
        } else
            System.out.printf("错误的下标v1=%d, v2=%d, VertexCount=%d\n", v1, v2, vertexList.size());
    }

    /**
     * 添加顶点和边
     * @param vertexList 顶点列表
     * @param edges 边数组
     */
    public void addVertexAndEdge(ArrayList<String> vertexList, int[][] edges) {
        if (vertexList != null && edges != null) {
            if (vertexList.size() != edges.length || vertexList.size() != edges[0].length)
                throw new RuntimeException("不匹配的顶点和边");
            this.vertexList = vertexList;
            this.edges = edges;
        } else
            System.out.println("strings=" + vertexList + "\tedges=" + edges);

    }

    /**
     * 添加顶点和边
     * @param vertexStrings 顶点数组
     * @param edges 边数组
     */
    public void addVertexAndEdge(String[] vertexStrings, int[][] edges) {
        if (vertexStrings != null && edges != null) {
            if (vertexStrings.length != edges.length || vertexStrings.length != edges[0].length)
                throw new RuntimeException("不匹配的顶点和边");
            for (String str : vertexStrings) {
                addVertex(str);
            }
            this.edges = edges;
        } else
            System.out.println("vertexStrings=" + vertexStrings + "\tedges=" + edges);

    }

    /**
     * 深度优先遍历
     */
    public void deepFirstSelect() {
        for (int i = 0; i < getVertexCount(); i++) {
            if (!isVisited[i])
                deepFirstSelect(isVisited, i);
        }
        System.out.println();
        isVisited = new boolean[isVisited.length];
    }

    /**
     * 深度优先迭代方法
     *
     * @param isVisited 已访问列表
     * @param i         当前访问节点
     */
    private void deepFirstSelect(boolean[] isVisited, int i) {

        System.out.print(getVertexByIndex(i) + "->");

        isVisited[i] = true;

        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w])
                deepFirstSelect(isVisited, w);
            w = getNextNeighbor(i, w);
        }

    }

    /**
     * 广度优先遍历
     */
    public void broadFirstSelect() {
        for (int i = 0; i < getVertexCount(); i++) {
            if (!isVisited[i])
                broadFirstSelect(isVisited, i);
        }
        System.out.println();
        isVisited = new boolean[isVisited.length];
    }

    /**
     * 广度优先迭代方法
     *
     * @param isVisited 已访问列表
     * @param i         当前访问节点
     */
    private void broadFirstSelect(boolean[] isVisited, int i) {
        System.out.print(getVertexByIndex(i) + "->");
        isVisited[i] = true;
        int neighbor = getFirstNeighbor(i);
        while (neighbor != -1) {
            if (!isVisited[neighbor]) {
                System.out.print(getVertexByIndex(neighbor) + "->");
                isVisited[neighbor] = true;
            }
            neighbor = getNextNeighbor(i, neighbor);
        }
    }

    /**
     * 获取顶点列表
     * @return
     */
    public ArrayList<String> getVertexList() {
        return vertexList;
    }

    public int[] getEdgesByIndex(int index) {
        return edges[index];
    }

    /**
     * 获取边数组
     * @return
     */
    public int[][] getEdges() {
        return edges;
    }

    /**
     * 获取节点i的第一个邻接结点
     *
     * @param i 获取该节点的邻接节点
     * @return -1或节点下标
     */
    public int getFirstNeighbor(int i) {
        for (int n : edges[i]) {
            if (n > 0)
                return n;
        }
        return -1;
    }

    /**
     * 根据前一个邻接结点的下标来获取下一个邻接结点
     *
     * @param i 获取该节点的邻接节点
     * @param j 前一个邻接节点下标
     * @return -1或节点下标
     */
    public int getNextNeighbor(int i, int j) {
        for (int k = j + 1; k < edges[i].length; k++) {
            if (edges[i][k] > 0)
                return k;
        }
        return -1;
    }

    /**
     * 获取节点数量
     *
     * @return
     */
    public int getVertexCount() {
        return vertexList.size();
    }

    /**
     * 获取边数量
     *
     * @return
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     * 获取index位置的节点 可能抛出下标越界异常
     *
     * @param index
     * @return
     */
    public String getVertexByIndex(int index) {
        return vertexList.get(index);
    }

    /**
     * 获取节点v1、v2的权重
     *
     * @param v1
     * @param v2
     * @return
     */
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    public static void main(String[] args) {

        String[] s = {"A", "B", "C", "D", "E"};
        Graph g = new Graph(s.length);
        for (String str : s) {
            g.addVertex(str);
        }

        g.addEdge(0, 1, 1);
        g.addEdge(0, 2, 1);
        g.addEdge(0, 4, 1);
        g.addEdge(1, 2, 1);
        g.addEdge(1, 3, 1);
        g.addEdge(3, 4, 1);

        g.list();
        g.deepFirstSelect();
        g.broadFirstSelect();
    }
}

