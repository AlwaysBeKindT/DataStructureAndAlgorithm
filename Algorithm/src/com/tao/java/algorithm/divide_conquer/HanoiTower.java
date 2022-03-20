package com.tao.java.algorithm.divide_conquer;

import java.util.Stack;

/**
 * @author AIERXUAN
 * @create 2022/3/19 - 13:59
 * @description
 */
public class HanoiTower {

    private final Tower towerA;
    private final Tower towerB;
    private final Tower towerC;
    private long count = 1;

    public HanoiTower(int num) {
        towerA = new Tower("a");
        towerB = new Tower("b");
        towerC = new Tower("c");
        for (int weight = num; weight > 0; weight--) {
            towerA.getTower().add(new Flower(weight));
        }
    }

    public void move() {
//        System.out.println(s.toString() + "把" + towera.size() + "层从" + towera.getName() + "移动到" + towerc.getName() + "{");
//        System.out.println(s.toString() + "}");
        move(towerA.getTower().get(0), towerA, towerB, towerC, towerA.size());
    }

    private void move(Flower f, Tower towerPresent, Tower towerUsage, Tower towerTarget, int num) {

        if (num == 1) {

            // 输出步骤
            System.out.println(count + "\t: 第" + f.getWeight() + "个盘从" + towerPresent.getName() + "移动到 " + towerTarget.getName());
            count++;

            //一阶塔直接移动 git config --global http.sslCAInfo "D:\Git\mingw64\ssl\cert.pem"
            towerTarget.add(f);
            towerPresent.pop();

        } else {
//            System.out.println(s.toString() + "把" + (towerPresent.size() - f.getPresentFlower()) + "层从" + towerPresent.getName() + "移动到" + towerUsage.getName() + "{");
//            System.out.println(s.toString() + "}");
//            System.out.println(s.toString() + "把" + towerUsage.size() + "层从" + towerUsage.getName() + "移动到" + towerTarget.getName() + "{");
//            System.out.println(s.toString() + "}");

            // 将需要移动的层上面的层移动到另一个塔
            move(towerPresent.getTower().get(towerPresent.size() - num + 1), towerPresent, towerTarget, towerUsage, num - 1);

            // 输出步骤
            System.out.println(count + "\t: 第" + f.getWeight() + "个盘从" + towerPresent.getName() + "移动到 " + towerTarget.getName());
            count++;

            // 将需要移动的层移动到target塔
            towerTarget.add(f);
            towerPresent.pop();

            // 将上一层的移动到target塔
            move(towerUsage.getTower().get(towerUsage.size() - num + 1), towerUsage, towerPresent, towerTarget, num - 1);

        }
    }

    public long getCount() {
        return count;
    }

    static class Tower {
        private final Stack<Flower> tower;
        private final String name;

        public Tower(String name) {
            tower = new Stack<>();
            this.name = name;
        }

        public void add(Flower flower) {
            tower.add(flower);
        }

        public void pop() {
            tower.pop();
        }

        public int size() {
            return tower.size();
        }

        public Stack<Flower> getTower() {
            return tower;
        }

        public String getName() {
            return name;
        }
    }

    static class Flower {
        private final int weight;

        public Flower(int weight) {
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            return weight == ((Flower) o).weight;
        }
    }

    public static void main(String[] args) {
        HanoiTower t = new HanoiTower(10);
        t.move();
        System.out.println(t.count);
    }

}
