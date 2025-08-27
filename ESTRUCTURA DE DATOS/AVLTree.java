import java.util.*;

public class AVLTree<E extends Comparable<? super E>> {

    private static class Node<E> {
        E key;
        Node<E> left, right;
        int height;

        Node(E key) {
            this.key = key;
            this.height = 1; // hojas con altura 1
        }
    }

    private Node<E> root;
    private int size = 0;

    // ===== API PÚBLICA =====

    public int size() { return size; }
    public boolean isEmpty() { return root == null; }

    public boolean contains(E key) {
        Node<E> cur = root;
        while (cur != null) {
            int cmp = key.compareTo(cur.key);
            if (cmp == 0) return true;
            cur = (cmp < 0) ? cur.left : cur.right;
        }
        return false;
    }

    public void insert(E key) {
        root = insert(root, key);
    }

    public void remove(E key) {
        root = remove(root, key);
    }

    // Recorridos
    public List<E> inOrder() {
        List<E> out = new ArrayList<>();
        inOrder(root, out);
        return out;
    }

    public List<E> preOrder() {
        List<E> out = new ArrayList<>();
        preOrder(root, out);
        return out;
    }

    public List<E> postOrder() {
        List<E> out = new ArrayList<>();
        postOrder(root, out);
        return out;
    }

    // ===== IMPLEMENTACIÓN INTERNA =====

    private Node<E> insert(Node<E> node, E key) {
        if (node == null) {
            size++;
            return new Node<>(key);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = insert(node.left, key);
        else if (cmp > 0) node.right = insert(node.right, key);
        else return node; // duplicado: ignorar

        updateHeight(node);
        return rebalance(node);
    }

    private Node<E> remove(Node<E> node, E key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = remove(node.left, key);
        else if (cmp > 0) node.right = remove(node.right, key);
        else {
            // Encontrado
            size--;
            if (node.left == null || node.right == null) {
                Node<E> temp = (node.left != null) ? node.left : node.right;
                if (temp == null) {
                    // sin hijos
                    node = null;
                } else {
                    // un hijo
                    node = temp;
                }
            } else {
                // dos hijos: sucesor (mínimo del subárbol derecho)
                Node<E> succ = minNode(node.right);
                node.key = succ.key;
                // eliminar sucesor en subárbol derecho
                node.right = remove(node.right, succ.key);
                // OJO: remove decrementó size, restablecemos
                size++;
            }
        }

        if (node == null) return null;

        updateHeight(node);
        return rebalance(node);
    }

    private Node<E> minNode(Node<E> node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private void inOrder(Node<E> n, List<E> out) {
        if (n == null) return;
        inOrder(n.left, out);
        out.add(n.key);
        inOrder(n.right, out);
        }
    private void preOrder(Node<E> n, List<E> out) {
        if (n == null) return;
        out.add(n.key);
        preOrder(n.left, out);
        preOrder(n.right, out);
    }
    private void postOrder(Node<E> n, List<E> out) {
        if (n == null) return;
        postOrder(n.left, out);
        postOrder(n.right, out);
        out.add(n.key);
    }

    // ===== BALANCEO =====

    private int height(Node<E> n) { return (n == null) ? 0 : n.height; }

    private void updateHeight(Node<E> n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    private int balanceFactor(Node<E> n) {
        return (n == null) ? 0 : height(n.left) - height(n.right);
    }

    private Node<E> rebalance(Node<E> n) {
        int bf = balanceFactor(n);

        // Caso Izq-Izq
        if (bf > 1 && balanceFactor(n.left) >= 0) {
            return rotateRight(n);
        }
        // Caso Izq-Der
        if (bf > 1 && balanceFactor(n.left) < 0) {
            n.left = rotateLeft(n.left);
            return rotateRight(n);
        }
        // Caso Der-Der
        if (bf < -1 && balanceFactor(n.right) <= 0) {
            return rotateLeft(n);
        }
        // Caso Der-Izq
        if (bf < -1 && balanceFactor(n.right) > 0) {
            n.right = rotateRight(n.right);
            return rotateLeft(n);
        }
        return n; // ya balanceado
    }

    private Node<E> rotateRight(Node<E> y) {
        Node<E> x = y.left;
        Node<E> T2 = x.right;

        // rotación
        x.right = y;
        y.left = T2;

        // actualizar alturas
        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private Node<E> rotateLeft(Node<E> x) {
        Node<E> y = x.right;
        Node<E> T2 = y.left;

        // rotación
        y.left = x;
        x.right = T2;

        // actualizar alturas
        updateHeight(x);
        updateHeight(y);

        return y;
    }

    // ===== DEMO =====
    public static void main(String[] args) {
        AVLTree<Integer> avl = new AVLTree<>();
        int[] vals = { 10, 20, 30, 40, 50, 25 };

        for (int v : vals) avl.insert(v);

        System.out.println("InOrder (ordenado): " + avl.inOrder());
        System.out.println("PreOrder: " + avl.preOrder());
        System.out.println("PostOrder: " + avl.postOrder());
        System.out.println("Size: " + avl.size());

        // Borrado que provoca re-balanceos
        avl.remove(40);
        System.out.println("\nTras eliminar 40:");
        System.out.println("InOrder: " + avl.inOrder());
        System.out.println("PreOrder: " + avl.preOrder());
        System.out.println("PostOrder: " + avl.postOrder());
        System.out.println("Size: " + avl.size());
    }
}
