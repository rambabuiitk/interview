package DesignAlgoJava;

import java.util.ArrayList;
import java.util.List;

/* Using two points of Rectangular (Top,Left) & (Bottom , Right)*/
public class QuadTree {

    public static void main(String args[]) {
        // Insert nodes into the tree
        QuadTree tree = new QuadTree(1,
                new Boundry(0, 0, 1000, 1000));
        tree.insert(100, 100, 1);
        tree.insert(500, 500, 1);
        tree.insert(600, 600, 1);
        tree.insert(700, 600, 1);
        tree.insert(800, 600, 1);
        tree.insert(900, 600, 1);
        tree.insert(510, 610, 1);
        tree.insert(520, 620, 1);
        tree.insert(530, 630, 1);
        tree.insert(540, 640, 1);
        tree.insert(550, 650, 1);
        tree.insert(555, 655, 1);
        tree.insert(560, 660, 1);
        //Traveling the graph
        QuadTree.construct(tree);
    }

    final int MAX_CAPACITY = 4;
    int level = 0;
    List<Node> nodes;
    QuadTree topLeft = null;
    QuadTree topRight = null;
    QuadTree bottomLeft = null;
    QuadTree bottomRight = null;
    Boundry boundry;

    QuadTree(int level, Boundry boundry) {
        this.level = level;
        nodes = new ArrayList<Node>();
        this.boundry = boundry;
    }

    /* Traveling the Graph using Depth First Search*/
    static void construct(QuadTree tree) {
        if (tree == null)
            return;

        System.out.printf("\nLevel = %d [X1=%d Y1=%d] \t[X2=%d Y2=%d] ",
                tree.level, tree.boundry.xMin, tree.boundry.yMin,
                tree.boundry.xMax, tree.boundry.yMax);

        for (Node node : tree.nodes) {
            System.out.printf(" \n\t  x=%d y=%d", node.x, node.y);
        }
        if (tree.nodes.size() == 0) {
            System.out.printf(" \n\t  Leaf Node.");
        }
        construct(tree.topLeft);
        construct(tree.topRight);
        construct(tree.bottomLeft);
        construct(tree.bottomRight);

    }

    void split() {
        int xOffset = this.boundry.xMin
                + (this.boundry.xMax - this.boundry.xMin) / 2;
        int yOffset = this.boundry.yMin
                + (this.boundry.yMax - this.boundry.yMin) / 2;

        topLeft = new QuadTree(this.level + 1, new Boundry(
                this.boundry.xMin, this.boundry.yMin, xOffset,
                yOffset));
        topRight = new QuadTree(this.level + 1, new Boundry(xOffset,
                this.boundry.yMin, xOffset, yOffset));
        bottomLeft = new QuadTree(this.level + 1, new Boundry(
                this.boundry.xMin, xOffset, xOffset,
                this.boundry.yMax));
        bottomRight = new QuadTree(this.level + 1, new Boundry(xOffset, yOffset,
                this.boundry.xMax, this.boundry.yMax));

    }

    void insert(int x, int y, int value) {
        if (!this.boundry.inRange(x, y)) {
            return;
        }

        Node node = new Node(x, y, value);
        if (nodes.size() < MAX_CAPACITY) {
            nodes.add(node);
            return;
        }
        // Exceeded the capacity so split it in FOUR
        if (topLeft == null) {
            split();
        }

        // Check coordinates belongs to which partition
        if (this.topLeft.boundry.inRange(x, y))
            this.topLeft.insert(x, y, value);
        else if (this.topRight.boundry.inRange(x, y))
            this.topRight.insert(x, y, value);
        else if (this.bottomLeft.boundry.inRange(x, y))
            this.bottomLeft.insert(x, y, value);
        else if (this.bottomRight.boundry.inRange(x, y))
            this.bottomRight.insert(x, y, value);
        else
            System.out.printf("ERROR : Unhandled partition %d %d", x, y);
    }


    private static class Boundry {
        int xMin, yMin, xMax, yMax;

        public Boundry(int xMin, int yMin, int xMax, int yMax) {
            super();
            /*
             *  Storing two diagonal points
             */
            this.xMin = xMin;
            this.yMin = yMin;
            this.xMax = xMax;
            this.yMax = yMax;
        }

        public boolean inRange(int x, int y) {
            return (x >= this.xMin && x <= this.xMax
                    && y >= this.yMin && y <= this.yMax);
        }

    }

    private class Node {
        int x, y, value;

        Node(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value; /* some data*/
        }
    }

}