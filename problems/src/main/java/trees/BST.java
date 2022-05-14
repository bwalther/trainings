package trees;

public class BST {

    public class Node {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    int getHeight(Node node) {
        if (node == null) return -1;
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }
}
