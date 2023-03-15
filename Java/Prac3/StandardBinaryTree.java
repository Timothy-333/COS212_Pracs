public class StandardBinaryTree<T extends Comparable<T>> extends BinaryTree<T> {
    @Override
    public void depthFirstTraversal() 
    {
        if(root != null)
            depthFirstTraversal(root);
    }
    private void depthFirstTraversal(Leaf<T> leaf)
    {
        if(leaf.left != null)
            depthFirstTraversal(leaf.left);
        else
            leaf.toString();
        if(leaf.right != null)
            depthFirstTraversal(leaf.right);
    }
    @Override
    public int numLeavesInTree() 
    {
        if(root != null)
            return numLeavesInTree(root);
        else
            return 0;
    }
    private int numLeavesInTree(Leaf<T> leaf)
    {
        if(leaf.left == null && leaf.right == null)
            return 1;
        else if(leaf.left == null)
            return numLeavesInTree(leaf.right);
        else if(leaf.right == null)
            return numLeavesInTree(leaf.left);
        else
            return numLeavesInTree(leaf.left) + numLeavesInTree(leaf.right);
    }
    @Override
    public int height() 
    {
        return height(root);
    }
    private int height(Leaf<T> leaf)
    {
        if(leaf.left == null && leaf.right == null)
            return 1;
        else if(leaf.left == null)
            return height(leaf.right) + 1;
        else if(leaf.right == null)
            return height(leaf.left) + 1;
        else
            return Math.max(height(leaf.left), height(leaf.right)) + 1;
    }
    @Override
    public Leaf<T> findParent(T data) {
        //TODO: Implement this function
    }

    @Override
    public void insert(T data) {
        super.insert(data, true);
        
    }

    @Override
    public Leaf<T> find(T data) {
        //TODO: Implement this function
    }

    @Override
    public boolean perfectlyBalanced() {
        //TODO: Implement this function
    }

    @Override
    public boolean contains(T data) {
        //TODO: Implement this function
    }

    @Override
    public BinaryTree<T> convertTree() {
        //TODO: Implement this function
    }
    

}
