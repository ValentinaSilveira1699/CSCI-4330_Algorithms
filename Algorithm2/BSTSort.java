class Node {
    int value;
    Node left, right, parent;
 
    public Node(int item) {
        value = item;
        left = right = parent = null;
    }
}
 
public class BSTSort {
    Node root;
    static int comparisons = 0;
 
    public BSTSort() {
        root = null;
    }
 
    void insert(int value) {
        root = insertRoot(root, value);
    }
 
    Node insertRoot(Node root, int value) {
        if (root == null) {
            root = new Node(value);
            return root;
        }
    
        if (value < root.value) {
            root.left = insertRoot(root.left, value);
            root.left.parent = root;
        } else if (value > root.value) {
            root.right = insertRoot(root.right, value);
            root.right.parent = root;
        }
    
        comparisons++; 
    
        return root;
    }    
 
    void inorderTraversal(Node root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.print(root.value + " ");
            inorderTraversal(root.right);
        }
    }
 
    public static void main(String[] args) {
        // Worst-case input array for n = 15
        int[] worstCaseArray = {15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        BSTSort worstCaseBST = createAndInsert(worstCaseArray);
        printBST("Worst-case BST:", worstCaseBST);
    
        // Best-case input array for n = 15
        int[] bestCaseArray = {8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};
        BSTSort bestCaseBST = createAndInsert(bestCaseArray);
        printBST("Best-case BST:", bestCaseBST);
    }
    
    public static BSTSort createAndInsert(int[] array) {
        BSTSort bst = new BSTSort();
        for (int value : array) {
            bst.insert(value);
        }
        return bst;
    }
    
    public static void printBST(String message, BSTSort bst) {
        System.out.println(message);
        bst.inorderTraversal(bst.root);
        System.out.println("\nNumber of key comparisons: " + comparisons + "\n");
    }    
}
