package org.example;

public class AvlTree {
    private static final int ALLOWED_IMBALANCE = 1;
    private AvlNode _root;
    private int _colNumber;

    public AvlTree(int colNumber) {
        _root = null;
        _colNumber = colNumber;
    }

    public boolean isEmpty() {
        return _root == null;
    }

    public String[] search(String x) {
        return search(x, _root);
    }

    public void insert(String[] x) {
        _root = insert(x, _root);
    }

    public void remove(String[] x) {
        _root = remove(x, _root);
    }

    private int height(AvlNode node) {
        return node == null ? -1 : node.height;
    }

    private AvlNode insert(String[] x, AvlNode node) {
        if (node == null) {
            return new AvlNode(x, null, null);
        }
        int compareResult = x[_colNumber].compareTo(node.element[_colNumber]);

        if (compareResult < 0) {
            node.leftNode = insert(x, node.leftNode);
        } else if (compareResult > 0) {
            node.rightNode = insert(x, node.rightNode);
        }
        return balance(node);
    }

    private AvlNode balance(AvlNode node) {
        if (node == null) {
            return node;
        }
        if (height(node.leftNode) - height(node.rightNode) > ALLOWED_IMBALANCE) {
            if (height(node.leftNode.leftNode) >= height(node.leftNode.rightNode)) {
                node = rotateWithLeftChild(node);
            } else {
                node = doubleWithLeftChild(node);
            }
        } else {
            if (height(node.rightNode) - height(node.rightNode) > ALLOWED_IMBALANCE) {
                if (height(node.rightNode.rightNode) >= height(node.rightNode.leftNode)) {
                    node = rotateWithRightChild(node);
                } else {
                    node = doubleWithRightChild(node);
                }
            }
        }
        node.height = Math.max(height(node.leftNode), height(node.rightNode));
        return node;
    }

    private AvlNode remove(String[] x, AvlNode node) {
        if (node == null) {
            return node;
        }
        int compareResult = x[_colNumber].compareTo(node.element[_colNumber]);
        if (compareResult < 0) {
            node.leftNode = remove(x, node.leftNode);
        } else if (compareResult > 0) {
            node.rightNode = remove(x, node.rightNode);
        } else if (node.leftNode != null && node.rightNode != null) {
            node.element = findMin(node.rightNode).element;
            node.rightNode = remove(node.element, node.rightNode);
        } else {
            node = (node.leftNode != null) ? node.leftNode : node.rightNode;
        }
        return balance(node);
    }

    private AvlNode rotateWithLeftChild(AvlNode k2) {
        AvlNode k1 = k2.leftNode;
        k2.leftNode = k1.rightNode;
        k1.rightNode = k2;
        k2.height = Math.max(height(k2.leftNode), height(k2.rightNode)) + 1;
        k1.height = Math.max(height(k1.leftNode), k2.height) + 1;
        return k1;
    }

    private AvlNode rotateWithRightChild(AvlNode k2) {
        AvlNode k1 = k2.rightNode;
        k2.rightNode = k1.leftNode;
        k1.leftNode = k2;
        k2.height = Math.max(height(k2.rightNode), height(k2.leftNode)) + 1;
        k1.height = Math.max(height(k1.rightNode), k2.height) + 1;
        return k1;
    }

    private AvlNode doubleWithLeftChild(AvlNode k3) {
        k3.leftNode = rotateWithLeftChild(k3.leftNode);
        return rotateWithLeftChild(k3);
    }

    private AvlNode doubleWithRightChild(AvlNode k3) {
        k3.rightNode = rotateWithLeftChild(k3.rightNode);
        return rotateWithLeftChild(k3);
    }

    private AvlNode findMin(AvlNode node) {
        if (node == null) {
            return null;
        } else if (node.leftNode == null) {
            return node;
        }
        return findMin(node.leftNode);
    }

    private String[] search(String x, AvlNode node) {
        if (node == null) {
            return null;
        }
        int endIndex = Math.min(x.length(), node.element[_colNumber].length());
        int compareResult = x.compareTo(node.element[_colNumber].substring(0, endIndex));
        if (compareResult < 0) {
            return search(x, node.leftNode);
        } else if (compareResult > 0) {
            return search(x, node.rightNode);
        } else {
            return node.element;
        }
    }

    private class AvlNode {
        private String[] element;
        private AvlNode leftNode;
        private AvlNode rightNode;
        private int height;

        public AvlNode(String[] element, AvlNode lNode, AvlNode rNode) {
            this.element = element;
            leftNode = lNode;
            rightNode = rNode;
            height = 0;
        }
    }
}
