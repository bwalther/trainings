package trees;

import java.util.*;

public class TreeNode<T> {
    private T value;
    private List<TreeNode> children;

    public TreeNode(T value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public TreeNode<T> addChild(T value) {
        TreeNode child = new TreeNode(value);
        this.children.add(child);
        return child;
    }

    public static <T> Optional<TreeNode<T>> search(TreeNode root, T value) {
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode n = q.remove();
            System.out.println("Visited node with value:" + n.value);
            if (n.value.equals(value)) {
                return Optional.of(n);
            } else {
                q.addAll(n.getChildren());
            }
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode(10);
        TreeNode<Integer> rootFirstChild = root.addChild(2);
        TreeNode<Integer> depthMostChild = rootFirstChild.addChild(3);
        TreeNode<Integer> rootSecondChild = root.addChild(4);
        System.out.println(TreeNode.search(root, 3));
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
