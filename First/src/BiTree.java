import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class BiTree<R extends Comparable<R>> {

    private static class BiTreeNode<R extends Comparable<R>> implements Comparable<R>{

        BiTreeNode(R value) {
            this.value = value;
        }

        R value;
        BiTreeNode<R> left;
        BiTreeNode<R> right;

        @Override
        public int compareTo(R o) {
            return value.compareTo(o);
        }
    }

    private BiTreeNode<R> head;

    public void addRandom(R i) {

        if (head == null)
            head = new BiTreeNode<>(i);
        else
            addRecursive(head, i);

    }

    public BiTree addBalanced(R i) {

        throw new NotImplementedException();

    }

    private void addRecursive(BiTreeNode<R> node, R i) {

        if (i.compareTo(node.value) > 0)
            if (node.right != null)
                addRecursive(node.right, i);
            else
                node.right = new BiTreeNode<>(i);
        else
            if (node.left != null)
                addRecursive(node.left, i);
            else
                node.left = new BiTreeNode<>(i);
    }

    private List<R> levelTraverse() {

        List<R> list = new LinkedList<>();
        list = listByLevel(head, list);
        return list;
    }

    private List<R> listByLevel(BiTreeNode<R> node, List<R> list) {
        if (node == null) return list;
        LinkedList<BiTreeNode<R>> queue = new LinkedList<>();
        queue.add(node);

        for (int i = 0; i < depth(node); i++) {
            for (int j = 0; j < Math.pow(2, i); j++) {
                BiTreeNode<R> curr = queue.pollFirst();
                if (curr == null) {
                    list.add(null);
                    queue.addLast(null);
                    queue.addLast(null);
                } else {
                    list.add(curr.value);
                    queue.addLast(curr.left);
                    queue.addLast(curr.right);
                }
            }
        }

        return list;
    }

    public void prettyPrint() {

        System.out.println(printByLevel(this.head, 1, depth(this.head)));

    }

    private StringBuilder printByLevel(BiTreeNode<R> node, int currentLevel, int depth) {

        throw new NotImplementedException();

    }

    private int getMaxElementLength(BiTreeNode<R> node) {

        if (node == null) return 0;
        return max(node).toString().length();

    }

    private BiTreeNode<R> max(BiTreeNode<R> node) {

        if (node.right == null) return node;
        return max(node.right);

    }

    //Or depth parameter could be added to BiTreeNode class, depends on conditions
    private int depth(BiTreeNode node) {

        if (node == null) return 0;

        int ldepth = depth(node.left);
        int rdepth = depth(node.right);

        return Math.max(ldepth, rdepth) + 1;

    }

    public static void main(String[] args) {

        BiTree<Integer> tree = new BiTree<>();
        tree.addRandom(100);
        tree.addRandom(15);
        tree.addRandom(190);
        tree.addRandom(171);
        tree.addRandom(170);
        tree.addRandom(3);
        tree.addRandom(91);
        tree.addRandom(205);
        tree.addRandom(2);
        tree.addRandom(1);
//        tree.addRandom(92);
//        tree.addRandom(93);
//        tree.addRandom(94);
//        tree.addRandom(95);

        tree.prettyPrint();

//        List<Integer> list = tree.levelTraverse();
//        list.forEach(System.out::println);

    }

}