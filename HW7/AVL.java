import java.util.Collection;
import java.util.List;

/**
 * Your implementation of an AVL Tree.
 *
 * @author DANIEL TADESSE
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> implements AVLInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private AVLNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty AVL tree.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public AVL() {

    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("A null collection not allowed");
        }
        for (T x : data) {
            if (x == null) {
                throw new IllegalArgumentException("Null data in collection "
                        + "not allowed");
            }
            add(x);
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Null data value");
        }
        root = addHelper(root, data);
    }

    /**
     * Performs recursive comparison the value of the new node with the
     * existing nodes in the tree. If the value of the new node is less, then
     * the comparison goes to the left child. if the value of the new node is
     * more, then the comparison goes to the right child. It is added when null
     * leaf node is reached.
     * @param data the data to be added to the tree
     * @param addNode existing node in the tree to compare with data
     * value is added or not. If it is added height, balance factor need to be
     * called and a rotation requirment is also checked
     * @return the updated AVL tree root node
     */
    private AVLNode<T> addHelper(AVLNode<T> addNode, T data) {
        if (addNode == null) {
            size++;
            return new AVLNode<>(data);
        }
        int comp = data.compareTo(addNode.getData());
        if (comp < 0) {
            addNode.setLeft(addHelper(addNode.getLeft(), data));
        } else if (comp > 0) {
            addNode.setRight(addHelper(addNode.getRight(), data));
        }
        addNode.setHeight(updateHeight(addNode));
        addNode.setBalanceFactor(updateBalanceFactor(addNode));
        addNode = checkRotation(addNode);
        return addNode;
    }

    /**
     * Takes a given node and checks if it needs rotation. If it does need
     * rotation it calls the required rotation method
     * @param addNode the node whose balance factor is going to be checked for
     * rotation
     * @return the root node of the rotated subtree, or the node itself if it
     * does not need rotation
     */
    private AVLNode<T> checkRotation(AVLNode<T> addNode) {
        if (addNode.getBalanceFactor() == 2) {
             if (addNode.getLeft().getBalanceFactor() == -1) {
                addNode.setLeft(leftRotate(addNode.getLeft()));
                addNode = rightRotate(addNode);
            } else {
                addNode = rightRotate(addNode);
            }
        } else if (addNode.getBalanceFactor() == -2) {
           if (addNode.getRight().getBalanceFactor() == 1) {
                addNode.setRight(rightRotate(addNode.getRight()));
                addNode = leftRotate(addNode);
            }  else {
                addNode = leftRotate(addNode);
            }
        }
        return addNode;
    }

    /**
     * Performs a single left rotation about a given node. It also calls methods
     * to update height and balance factor after rotation.
     * @param addNode node to be rotated to the left
     * @return the rotated subtree's root node
     */
    private AVLNode<T> leftRotate(AVLNode<T> addNode) {
        AVLNode<T> rotX = addNode.getRight();
        AVLNode<T> rotY = rotX.getLeft();
        rotX.setLeft(addNode);
        addNode.setRight(rotY);
        addNode.setHeight(updateHeight(addNode));
        rotX.setHeight(updateHeight(rotX));
        addNode.setBalanceFactor(updateBalanceFactor(addNode));
        rotX.setBalanceFactor(updateBalanceFactor(rotX));
        return rotX;
    }


    /**
     * Performs a single right rotation about given node. t also calls methods
     * to update height and balance factor after rotation.
     * @param addNode node to be rotated to the right
     * @return the rotated subtree's root node
     */
    private AVLNode<T> rightRotate(AVLNode<T> addNode) {
        AVLNode<T> rotX = addNode.getLeft();
        AVLNode<T> rotY = rotX.getRight();
        rotX.setRight(addNode);
        addNode.setLeft(rotY);
        addNode.setHeight(updateHeight(addNode));
        rotX.setHeight(updateHeight(rotX));
        addNode.setBalanceFactor(updateBalanceFactor(addNode));
        rotX.setBalanceFactor(updateBalanceFactor(rotX));
        return rotX;
    }

    /**
     * Evaluates height of a given node after adding/removing/rotating
     * @param addNode the node whose height is going to be updated
     * @return the evaluated height of the node
     */
    private int updateHeight(AVLNode<T> addNode) {
        if (addNode.getRight() != null || addNode.getLeft() != null) {
            if (addNode.getRight() == null) {
                return (1 + addNode.getLeft().getHeight());
            } else if (addNode.getLeft() == null) {
                return (1 + addNode.getRight().getHeight());
            } else {
                return 1 + Math.max(addNode.getLeft().getHeight(),
                        addNode.getRight().getHeight());
            }
        }
        return 0;
    }

    /**
     * Evaluates the balance of a node after adding/removing/rotating
     * @param addNode the node whose height is going to be updated
     * @return the evaluated balance factor of the node
     */
    private int updateBalanceFactor(AVLNode<T> addNode) {
        if (addNode.getRight() != null || addNode.getLeft() != null) {
            if (addNode.getRight() == null) {
                return addNode.getLeft().getHeight() + 1;
            } else if (addNode.getLeft() == null) {
                return (-1) - addNode.getRight().getHeight();
            } else {
                return addNode.getLeft().getHeight()
                        - addNode.getRight().getHeight();
            }
        }
        return 0;
    }


    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Null data value is used");
        }
        AVLNode<T> removedHolder = new AVLNode<>(null);
        root = getRemovedNode(data, root, removedHolder);
        if (removedHolder.getData() == null) {
            throw new java.util.NoSuchElementException("Data value not found");
        }
        return removedHolder.getData();
    }

    /**
     * A method that returns the updated(height/height/balance factor) root node
     * after removing
     * @param data the data value to be removed
     * @param updatedRoot the parent node of the node to be removed
     * @param removedNode dummy node that holds the removed node value
     * @return The updated AVL tree root node
     */
    private AVLNode<T> getRemovedNode(T data, AVLNode<T> updatedRoot,
                                      AVLNode<T> removedNode) {
        if (updatedRoot == null) {
            return null;
        }
        int comp = data.compareTo(updatedRoot.getData());
        if (comp < 0) {
            updatedRoot.setLeft(getRemovedNode(data, updatedRoot.getLeft(),
                    removedNode));
        } else if (comp > 0) {
            updatedRoot.setRight(getRemovedNode(data, updatedRoot.getRight(),
                    removedNode));
        } else {
            removedNode.setData(updatedRoot.getData());
            if (updatedRoot.getRight() == null || updatedRoot.getLeft()
                    == null) {
                if (!(updatedRoot.getLeft() == null)) {
                    updatedRoot = updatedRoot.getLeft();
                } else if (!(updatedRoot.getRight() == null)) {
                    updatedRoot = updatedRoot.getRight();
                } else {
                    updatedRoot = null;
                }
            } else {
                AVLNode<T> successorParent = updatedRoot;
                AVLNode<T> successor = updatedRoot.getRight();
                while (successor.getLeft() != null) {
                    successorParent = successor;
                    successor = successor.getLeft();
                }
                updatedRoot.setData(successor.getData());
                if (successorParent.equals(updatedRoot)) {
                    updatedRoot.setRight(successor.getRight());
                } else {
                    successorParent.setLeft(successor.getRight());
                }
                updatedRoot.setRight(getRemovedNode(successor.getData(),
                        updatedRoot.getRight(), new AVLNode<>(null)));
            }
            size--;
        }
        if (updatedRoot == null) {
            return null;
        }
        updatedRoot.setHeight(updateHeight(updatedRoot));
        updatedRoot.setBalanceFactor(updateBalanceFactor(updatedRoot));
        updatedRoot = checkRotation(updatedRoot);
        return updatedRoot;
    }

    /**
     * Searches a given node in the AVL tree and returns the node itself, or
     * returns nulL value if data is not found
     * @param data The data value to be checked for existence in the tree.
     * @param nodeLocator The node location where the value is located
     * @return The location of the node that is found or null if not found
     */
    private AVLNode<T> findData(T data, AVLNode<T> nodeLocator) {
        if (nodeLocator != null) {
            int comp = data.compareTo(nodeLocator.getData());
            if (comp == 0) {
                return nodeLocator;
            } else if (comp > 0) {
                return findData(data, nodeLocator.getRight());
            } else {
                return findData(data, nodeLocator.getLeft());
            }
        }
        return null;
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Null data value");
        }
        AVLNode<T> nodeLocator = findData(data, root);
        if (nodeLocator == null) {
            throw new java.util.NoSuchElementException("Data is not found");
        }
        return nodeLocator.getData();
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Null data value");
        }
        AVLNode<T>  startAt = findData(data, root);
        return startAt != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> preorder() {
        List<T> lst = new java.util.ArrayList<>();
        lst = preOrderHelper(root, lst);
        return lst;
    }
    
    /**
     * This is a private helper method for preorder() method. It returns a list
     * that contains the pre-order traversal of the AVL tree.
     * @param node The next node to be traversed on the binary tree
     * @param lst The list containing the traversal results
     * @return The pre-order in list data structure
     */
    private List<T> preOrderHelper(AVLNode<T> node, List<T> lst) {
        if (node != null) {
            lst.add(node.getData());
            preOrderHelper(node.getLeft(), lst);
            preOrderHelper(node.getRight(), lst);
        }
        return lst;
    }

    @Override
    public List<T> postorder() {
        List<T> lst = new java.util.ArrayList<>();
        lst = postOrderHelper(root, lst);
        return lst;
    }

    /**
     * This is a private helper method for postorder() method. It returns a list
     * that contains the post-order traversal of the AVL tree.
     * @param node The next node to be traversed on the binary tree
     * @param lst The list containing the traversal results
     * @return The post-order in List Data Sturacture
     */
    private List<T> postOrderHelper(AVLNode<T> node, List<T> lst) {
        if (node != null) {
            postOrderHelper(node.getLeft(), lst);
            postOrderHelper(node.getRight(), lst);
            lst.add(node.getData());
        }
        return lst;
    }

    @Override
    public List<T> inorder() {
        List<T> lst = new java.util.ArrayList<>();
        lst = inorderHelper(root, lst);
        return lst;
    }

    /**
     * This is a private helper method for inorder() method. It returns a list
     * that contains the inorder traversal of the AVL tree.
     * @param node The next node to be traversed on the binary tree
     * @param lst The list containing the traversal results
     * @return the inorder traversal inside List data structure
     */
    private List<T> inorderHelper(AVLNode<T> node, List<T> lst) {
        if (node != null) {
            inorderHelper(node.getLeft(), lst);
            lst.add(node.getData());
            inorderHelper(node.getRight(), lst);
        }
        return lst;
    }

    @Override
    public List<T> levelorder() {
        List<T> lst = new java.util.ArrayList<>();
        if (size == 0) {
            return lst;
        }
        java.util.Queue<AVLNode<T>> qq = new java.util.LinkedList<>();
        qq.add(root);
        lst = levelOrderHelper(qq, lst);
        return lst;
    }

    /**
     * This is a private helper method for levelorder() method. It returns a
     * list that contains the levelorder traversal of the AVL tree.
     * @param qq The next node to be traversed on the binary tree
     * @param lst The list containing the traversal results
     * @return The level order traversal inside List data structure
     */
    private List<T> levelOrderHelper(java.util.Queue<AVLNode<T>> qq,
                                     List<T> lst) {
        AVLNode<T> level;
        while (!qq.isEmpty()) {
            level = qq.poll();
            lst.add(level.getData());
            if (level.getLeft() != null) {
                qq.add(level.getLeft());
            }
            if (level.getRight() != null) {
                qq.add(level.getRight());
            }
        }
        return lst;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        if (root == null) {
            return -1;
        }
        return root.getHeight();
    }

    @Override
    public AVLNode<T> getRoot() {
        // DO NOT EDIT THIS METHOD!
        return root;
    }
}
