
package a3;

public class BSTImpl implements BST {

    private Node root;
    private int size;

    public BSTImpl() {
        root = null;
        size = 0;
    }

    public BSTImpl(int val) {
        this.root = new NodeImpl(val);
        size = 1;
    }

    // The implementations given to you are intended to help you 
    // see how the linked cells work, and how to program with them.
    //
    // These methods are patterns to illustrate for you how to set up
    // the method implementations as recursion.
    //
    // You should follow this pattern for implementing the other recursive methods
    // by adding your own private recursive helper methods.

    @Override
    // interface method ==================================================
    public int height() {
        // It is not recursive itself, but it calls a recursive
        // private "helper" method and passes it access to the tree cells.
        return height_recursive(this.root);
    }

    // private recursive helper
    private int height_recursive(Node c) {
        // private inner "helper" method with different signature
        // this helper method uses recursion to traverse
        // and process the recursive structure of the tree of cells
        if (c == null) return -1;
        int lht = height_recursive(c.getLeft());
        int rht = height_recursive(c.getRight());
        return Math.max(lht, rht) + 1;
    }

    @Override
    // interface method ==================================================
    public boolean contains(int val) {
        if (this.root == null) return false;
        return contains_r(val, root);
    }

    // private recursive helper
    private boolean contains_r(int val, Node c) {
        if (c == null) return false;
        if (c.getValue() == val) return true;
        return contains_r(val, c.getLeft()) || contains_r(val, c.getRight());
    }

    @Override
    // interface method, used by autograder, do not change
    public Node getRoot() {
        return this.root;
    }

    @Override
    // interface method ==================================================
    public int size() {
        return this.size;
    }


    @Override
    // interface method ===================================================
    public void remove(int value) {
        remove_r(value, this.root);
    }

    // private recursive helper
    private Node remove_r(int k, Node c) {
        if (c == null) return null; // item not found, nothing to do
        // now we know we have a real node to examine
        //int cflag = k.compareTo(c.getValue());
        int vc = c.getValue();
        if (k < vc) { // k is smaller than node
            c.setLeft(remove_r(k, c.getLeft()));
            return c;
        } else if (k > vc) { // k is larger than node
            c.setRight(remove_r(k, c.getRight()));
            return c;
        } else { // k==vc   // found it... now figure out how to rearrange
            // cases
            if (c.getLeft() == null && c.getRight() == null) {
                c = null;
                this.size--;
            } // leaf
            else if (c.getLeft() == null && c.getRight() != null) {
                c = c.getRight();
                this.size--;
            } // RC only
            else if (c.getLeft() != null && c.getRight() == null) {
                c = c.getLeft();
                this.size--;
            } // LC only
            else { // 2 children
                Node mc = findMaxCell(c.getLeft());
                c.setValue(mc.getValue());
                c.setLeft(remove_r(c.getValue(), c.getLeft()));   // recurse
            }
            return c;
        }
    }

    // private recursive helper
    private Node findMaxCell(Node c) {
        if (c.getRight() == null) return c;
        return findMaxCell(c.getRight());
    }


    //====================================================================================
    //
    // The methods below here are part of the public BST interface we defined,
    // but you will write the implementation code.
    //
    // At the moment, they have return statements that return dummy values; this
    // is so they will compile, but those return values are just dummy behavior
    // you will replace the dummy returns with your own code and proper return values.
    //
    //====================================================================================


    @Override
    // interface method ==================================================
    //Method 1
    public int insert(int val) {
        Node current = getRoot();
        /* Hint: Don't forget to update size */
        /* Hint: You can find examples of how to create a new Node object elsewhere in the code */
        // if elt exists in tree, return elt
        if (contains(val)) {
            return val;
        }
        //if tree is empty
        else if (this.root == null) {
            Node new_root = new NodeImpl(val);
            root = new_root;
            size++;
            return val;
        } else {
            return insert_r(val, current);
        }
    }

    private int insert_r(int val, Node elt) {
        // if val > elt (recurse on right subtree)

        if (val > elt.getValue()) {
            if (elt.getRight() == null) {
                Node r_child = new NodeImpl(val);
                size++;
                elt.setRight(r_child);
                return val;
            } else {
                return insert_r(val, elt.getRight());
            }
        }
        // if val < elt recurse on left subtree
        else if (val < elt.getValue()) {
            if (elt.getLeft() == null) {
                Node l_child = new NodeImpl(val);
                size++;
                elt.setLeft(l_child);
                return val;
            } else {
                return insert_r(val, elt.getLeft());
            }
        }
        return val;
    }

    @Override
    // Method 2
    // interface method ==================================================
    public int findMin() {
        /*See BST.java for method specification */
        int minimum = root.getValue();
        Node current = root;
        while (current != null) {
            minimum = current.getValue();
            current = current.getLeft();
        }
        return minimum;
    }

    @Override
    // Method 3
    // interface method ==================================================
    public int findMax() {
        int maximum = root.getValue();
        Node current = root;
        while (current != null) {
            maximum = current.getValue();
            current = current.getRight();
        }
        return maximum;
    }

    @Override
    // interface method ==================================================
    // Method 4
    public Node get(int val) {

        /*See BST.java for method specification */
        /* Hint: Make sure you return a Node, not an int */
        Node current = root;
        while (current.getValue() != val) {
            if (val > current.getValue()) {
                current = current.getRight();
            } else {
                current = current.getLeft();
            }
        }
        return current;

    }

    @Override
    // Method 5
    // interface method ==================================================
    public boolean isFullBT() {
        /*See BST.java for method specification */
        /* Hint: How can you "break-up" the problem into smaller pieces? */
        if (root == null) {
            return true;
        } else {
            return isFullBT_r(root);
        }
    }

    private boolean isFullBT_r(Node child) {
        if (child.getLeft() == null && child.getRight() == null) {
            return true;
        }

        if (child.getLeft() == null || child.getRight() == null) {
            return false;
        }
        else {
            return isFullBT_r(child.getLeft()) && isFullBT_r(child.getRight());
        }
    }

    @Override
    // Method 6
    // interface method ==================================================
    public int merge(BST nbt) {
        //Mar 4
        /*See BST.java for method specification */
        // Hint: traverse bst using pre-order
        // as each node is visited, take the value there
        // and do this.insert(value)
        // have to somehow count when an add is successful
        // so we can return the number of nodes added
        /* Your code here */
        //First put parameter bst into an array pre-order
        //Then insert each element in order into the tree.
        return preorder(nbt.getRoot());
    }
    int count = 0;
    public int preorder(Node root) {

        // return if the current node is empty
        if (root == null) {
            return count;
        }
        if (!this.contains(root.getValue())) {
            this.insert(root.getValue());
            count++;
        }
        preorder(root.getLeft());
        preorder(root.getRight());
        return count;
    }


    public int getMaxLeafHeightDiff() {
        /*See BST.java for method specification */
        /* Hint: Which of the methods you're given are most similar to this? */
        return height() - min_height_recursive(root);
    }

    private int min_height_recursive(Node c) {
        if (c == null) return -1;
        int lht = height_recursive(c.getLeft());
        int rht = height_recursive(c.getRight());
        return Math.min(lht, rht) + 1;
    }
}