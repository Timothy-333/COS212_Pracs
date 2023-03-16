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
        System.out.println(leaf.toString());
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
            return 1+numLeavesInTree(leaf.right);
        else if(leaf.right == null)
            return 1+numLeavesInTree(leaf.left);
        else
            return 1+numLeavesInTree(leaf.left) + numLeavesInTree(leaf.right);
    }
    @Override
    public int height() 
    {
        if(root != null)
            return height(root)-1;
        else
            return 0;
    }
    private int height(Leaf<T> leaf)
    {
        if(leaf == null)
            return 0;
        else
            return 1 + Math.max(height(leaf.left), height(leaf.right));
    }
    @Override
    public Leaf<T> findParent(T data) 
    {
        if(root != null)
            return findParent(root, data);
        else
            return null;
    }
    private Leaf<T> findParent(Leaf<T> leaf, T data)
    {
        if(leaf == null)
            return null;
        System.out.println(leaf.toString());
        if((leaf.left != null && leaf.left.data.compareTo(data) == 0) || (leaf.right != null && leaf.right.data.compareTo(data) == 0))
            return leaf;
        else if(leaf.data.compareTo(data) > 0)
            return findParent(leaf.left, data);
        else if(leaf.data.compareTo(data) < 0)
            return findParent(leaf.right, data);
        else
            return null;
    }

    @Override
    public void insert(T data) 
    {
        super.insert(data, true);
    }

    @Override
    public Leaf<T> find(T data) 
    {
        return find(root, data);
    }
    private Leaf<T> find(Leaf<T> leaf, T data)
    {
        if(leaf == null)
            return null;
        System.out.println(leaf.toString());
        if(leaf.data.compareTo(data) == 0)
            return leaf;
        else if(leaf.data.compareTo(data) > 0)
            return find(leaf.left, data);
        else if(leaf.data.compareTo(data) < 0)
            return find(leaf.right, data);
        else
            return null;
    }
    @Override
    public boolean perfectlyBalanced() 
    {
        if(root != null)
            return perfectlyBalanced(root);
        else
            return true;
    }
    private boolean perfectlyBalanced(Leaf<T> leaf)
    {
        if(leaf.left == null && leaf.right == null)
            return true;
        else if(leaf.left == null || leaf.right == null)
            return false;
        else
            return perfectlyBalanced(leaf.left) && perfectlyBalanced(leaf.right);

    }
    @Override
    public boolean contains(T data) 
    {
        return contains(root, data);
    }
    private boolean contains(Leaf<T> leaf, T data)
    {
        if(leaf == null)
            return false;
        System.out.println(leaf.toString());
        if(leaf.data.compareTo(data) == 0)
            return true;
        else if(leaf.data.compareTo(data) > 0)
            return contains(leaf.left, data);
        else if(leaf.data.compareTo(data) < 0)
            return contains(leaf.right, data);
        else
            return false;
    }
    @Override
    public BinaryTree<T> convertTree() 
    {
        BinaryTree<T> tree = new MirroredBinaryTree<T>();
        if(root != null)
            return convertTree(root, tree);
        else
            return tree;
    }
    private BinaryTree<T> convertTree(Leaf<T> leaf, BinaryTree<T> tree)
    {
        tree.insert(leaf.data);
        if(leaf.left != null)
            convertTree(leaf.left, tree);
        if(leaf.right != null)
            convertTree(leaf.right, tree);
        return tree;
    }
}
