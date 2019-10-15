import java.util.LinkedList;
import java.util.List;

@SuppressWarnings({"WeakerAccess", "unused"})
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
        throw new UnsupportedOperationException();
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

    public List<R> levelTraverse() {
        return listByLevel(this.head);
    }

    private LinkedList<R> listByLevel(BiTreeNode<R> node) {
        return listByLevel(node, new LinkedList<>());
    }

    //Missing elements added as null, need it for pretty print
    private LinkedList<R> listByLevel(BiTreeNode<R> node, LinkedList<R> list) {
        if (node == null) return list;
        LinkedList<BiTreeNode<R>> queue = new LinkedList<>();
        queue.add(node);

        for (int i = 0; i < getDepth(node); i++) {
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
        System.out.println(printByLevel(listByLevel(this.head), getDepth(this.head), getMaxElementLength(this.head)));
    }

    private StringBuilder printByLevel(LinkedList<R> levelTraverseList, int depth, int width) {

        StringBuilder builder = new StringBuilder();

        for (int currentLevel = 1; currentLevel <= depth; currentLevel++) {

            //The center of the element is calculated relative to the deepest level.
            int indent = (int) Math.floor(Math.pow(2, depth - currentLevel - 1) * (width + 1) - 0.5 - width * 1.0 / 2);
            int interval = (int) Math.floor(Math.pow(2, depth - currentLevel) * (width + 1) - width);

            int elementsOnLevel = (int) Math.pow(2, currentLevel - 1);
            builder.append(" ".repeat(indent));

            //bad code, thinking on better solution
            for (int j = 0; j < elementsOnLevel; j++) {
                builder.append(getWrappedStringElement(levelTraverseList.pollFirst(), width))
                        //trailing spaces in each row
                        .append(" ".repeat(interval));
            }
            builder.append("\n");

        }

        return builder;

    }

    private String getWrappedStringElement(R element, int width) {
        if (element == null) {
            return " ".repeat(width);
        }

        String elStr = element.toString();
        if (elStr.length() > width) {
            System.out.println("WARN: element length > than width (" + width + "): " + elStr);
            return elStr;
        }

        StringBuilder sb = new StringBuilder(width);
        sb.append(" ".repeat((width - elStr.length()) / 2));
        sb.append(elStr);
        while (sb.length() < width) {
            sb.append(" ");
        }
        return sb.toString();
    }

    private int getMaxElementLength(BiTreeNode<R> node) {
        if (node == null) return 0;
        return getMaxNode(node).value.toString().length();
    }

    private BiTreeNode<R> getMaxNode(BiTreeNode<R> node) {
        if (node.right == null) return node;
        return getMaxNode(node.right);
    }

    //Or depth parameter could be added to BiTreeNode class, depends on conditions
    private int getDepth(BiTreeNode node) {

        if (node == null) return 0;

        int ldepth = getDepth(node.left);
        int rdepth = getDepth(node.right);

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
        tree.addRandom(92);
        tree.addRandom(93);
        tree.addRandom(94);
        tree.addRandom(90);
        tree.addRandom(89);

        tree.prettyPrint();

    }

}