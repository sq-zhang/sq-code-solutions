package datastructure.tree;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sqzhang
 * @year 2020
 */
public class RBTree<T extends Comparable<T>> {

    public static class RBTreeNode<T extends Comparable<T>> {
        private T value;
        private RBTreeNode<T> left;
        private RBTreeNode<T> right;
        private RBTreeNode<T> parent;
        private boolean red;

        public RBTreeNode(){}
        public RBTreeNode(T value){this.value=value;}
        public RBTreeNode(T value,boolean isRed){this.value=value;this.red = isRed;}

        public T getValue() {
            return value;
        }
        void setValue(T value) {
            this.value = value;
        }
        RBTreeNode<T> getLeft() {
            return left;
        }
        void setLeft(RBTreeNode<T> left) {
            this.left = left;
        }
        RBTreeNode<T> getRight() {
            return right;
        }
        void setRight(RBTreeNode<T> right) {
            this.right = right;
        }
        RBTreeNode<T> getParent() {
            return parent;
        }
        void setParent(RBTreeNode<T> parent) {
            this.parent = parent;
        }
        boolean isRed() {
            return red;
        }
        boolean isBlack(){
            return !red;
        }

        void setRed(boolean red) {
            this.red = red;
        }

        void makeRed(){
            red = true;
        }
        void makeBlack(){
            red = false;
        }
        @Override
        public String toString(){
            return value.toString();
        }
    }

    private final RBTreeNode<T> root;

    private AtomicInteger size = new AtomicInteger(0);

    private volatile boolean overrideMode = true;

    public RBTree(){
        this.root = new RBTreeNode<>();
    }

    public RBTree(boolean overrideMode){
        this();
        this.overrideMode=overrideMode;
    }

    /**
     * number of tree number
     */
    public int getSize() {
        return size.get();
    }

    /**
     * get the root node
     */
    private RBTreeNode<T> getRoot(){
        return root.getLeft();
    }

    /**
     * add value to a new node,if this value exist in this tree,
     * if value exist,it will return the exist value.otherwise return null
     * if override mode is true,if value exist in the tree,
     * it will override the old value in the tree
     */
    public T addNode(T value){
        RBTreeNode<T> t = new RBTreeNode<>(value);
        return addNode(t);
    }

    private T addNode(RBTreeNode<T> node){
        node.setLeft(null);
        node.setRight(null);
        node.setRed(true);
        setParent(node,null);
        if(getRoot() == null){
            root.setLeft(node);
            node.setRed(false); //root node is black
        } else {
            RBTreeNode<T> x = findParentNode(node);
            int cmp = x.getValue().compareTo(node.getValue());
            if(this.overrideMode && cmp == 0){
                T v = x.getValue();
                x.setValue(node.getValue());
                return v;
            } else if(cmp == 0){
                return x.getValue();
            }
            setParent(node, x);
            if(cmp > 0){
                x.setLeft(node);
            } else {
                x.setRight(node);
            }
            fixInsert(node);
        }

        size.incrementAndGet();
        return null;
    }

    /**
     * find the parent node to hold node x,if parent value equals x.value return parent.
     */
    private RBTreeNode<T> findParentNode(RBTreeNode<T> x){
        RBTreeNode<T> parent = getRoot();
        RBTreeNode<T> node = parent;

        while(node != null){
            int cmp = node.getValue().compareTo(x.getValue());
            if(cmp == 0){
                return node;
            }
            parent = node;
            if(cmp > 0){
                node = node.getLeft();
            }else {
                node = node.getRight();
            }
        }
        return parent;
    }

    /**
     * red black tree insert fix
     */
    private void fixInsert(RBTreeNode<T> x){
        RBTreeNode<T> parent = x.getParent();

        while(parent != null && parent.isRed()){
            RBTreeNode<T> uncle = getUncle(x);
            if(uncle == null){
                RBTreeNode<T> ancestor = parent.getParent();
                if(parent == ancestor.getLeft()){
                    boolean isRight = x == parent.getRight();
                    if(isRight){
                        rotateLeft(parent);
                    }
                    rotateRight(ancestor);

                    if(isRight){
                        x.setRed(false);
                        parent = null;
                    }else{
                        parent.setRed(false);
                    }
                    ancestor.setRed(true);
                } else {
                    boolean isLeft = x == parent.getLeft();
                    if(isLeft){
                        rotateRight(parent);
                    }
                    rotateLeft(ancestor);

                    if(isLeft){
                        x.setRed(false);
                        parent = null;
                    }else{
                        parent.setRed(false);
                    }
                    ancestor.setRed(true);
                }
            } else {//uncle is red
                parent.setRed(false);
                uncle.setRed(false);
                parent.getParent().setRed(true);
                x=parent.getParent();
                parent = x.getParent();
            }
        }
        getRoot().makeBlack();
        getRoot().setParent(null);
    }

    /**
     * remove the node by give value,if this value not exists in tree return null
     */
    public T remove(T value){
        RBTreeNode<T> root = getRoot();
        RBTreeNode<T> parent = this.root;

        while(root != null){
            int cmp = root.getValue().compareTo(value);
            if(cmp < 0){
                parent = root;
                root = root.getRight();
            } else if(cmp > 0){
                parent = root;
                root = root.getLeft();
            } else {
                if(root.getRight() != null){
                    RBTreeNode<T> min = removeMin(root.getRight());
                    //x used for fix color balance
                    RBTreeNode<T> x = min.getRight()==null ? min.getParent() : min.getRight();
                    boolean isParent = min.getRight()==null;

                    min.setLeft(root.getLeft());
                    setParent(root.getLeft(),min);
                    if(parent.getLeft()==root){
                        parent.setLeft(min);
                    } else {
                        parent.setRight(min);
                    }
                    setParent(min,parent);

                    boolean curMinIsBlack = min.isBlack();
                    min.setRed(root.isRed());

                    if(min!=root.getRight()){
                        min.setRight(root.getRight());
                        setParent(root.getRight(),min);
                    }
                    if(curMinIsBlack){
                        if(min != root.getRight()){
                            fixRemove(x,isParent);
                        } else if(min.getRight()!=null){
                            fixRemove(min.getRight(),false);
                        } else {
                            fixRemove(min,true);
                        }
                    }
                }else{
                    setParent(root.getLeft(),parent);
                    if(parent.getLeft()==root){
                        parent.setLeft(root.getLeft());
                    } else {
                        parent.setRight(root.getLeft());
                    }
                    //current node is black and tree is not empty
                    if(root.isBlack() && !(this.root.getLeft()==null)){
                        RBTreeNode<T> x = root.getLeft()==null
                            ? parent :root.getLeft();
                        boolean isParent = root.getLeft()==null;
                        fixRemove(x,isParent);
                    }
                }
                setParent(root,null);
                root.setLeft(null);
                root.setRight(null);
                if(getRoot() != null){
                    getRoot().setRed(false);
                    getRoot().setParent(null);
                }
                size.decrementAndGet();
                return root.getValue();
            }
        }
        return null;
    }

    /**
     * fix remove action
     */
    private void fixRemove(RBTreeNode<T> node,boolean isParent){
        RBTreeNode<T> cur = isParent ? null : node;
        boolean isRed = !isParent && node.isRed();
        RBTreeNode<T> parent = isParent ? node : node.getParent();

        while(!isRed && !isRoot(cur)){
            RBTreeNode<T> sibling = getSibling(cur,parent);
            //if cur is a left node
            boolean isLeft = parent.getRight() == sibling;
            if(sibling.isRed() && !isLeft){//case 1
                //cur in right
                parent.makeRed();
                sibling.makeBlack();
                rotateRight(parent);
            } else if(sibling.isRed() && isLeft){
                //cur in left
                parent.makeRed();
                sibling.makeBlack();
                rotateLeft(parent);
            } else if(isBlack(sibling.getLeft()) && isBlack(sibling.getRight())){//case 2
                sibling.makeRed();
                cur = parent;
                isRed = cur.isRed();
                parent=parent.getParent();
            } else if(isLeft && !isBlack(sibling.getLeft())
                && isBlack(sibling.getRight())){//case 3
                sibling.makeRed();
                sibling.getLeft().makeBlack();
                rotateRight(sibling);
            } else if(!isLeft && !isBlack(sibling.getRight())
                && isBlack(sibling.getLeft()) ){
                sibling.makeRed();
                sibling.getRight().makeBlack();
                rotateLeft(sibling);
            } else if(isLeft && !isBlack(sibling.getRight())){//case 4
                sibling.setRed(parent.isRed());
                parent.makeBlack();
                sibling.getRight().makeBlack();
                rotateLeft(parent);
                cur = getRoot();
            } else if(!isLeft && !isBlack(sibling.getLeft())){
                sibling.setRed(parent.isRed());
                parent.makeBlack();
                sibling.getLeft().makeBlack();
                rotateRight(parent);
                cur = getRoot();
            }
        }
        if(isRed){
            cur.makeBlack();
        }
        if(getRoot() != null){
            getRoot().setRed(false);
            getRoot().setParent(null);
        }

    }

    private RBTreeNode<T> getSibling(RBTreeNode<T> node,RBTreeNode<T> parent){
        parent = node == null ? parent : node.getParent();
        if(node == null){
            return parent.getLeft() == null ? parent.getRight() : parent.getLeft();
        }
        if(node == parent.getLeft()){
            return parent.getRight();
        }else{
            return parent.getLeft();
        }
    }

    private boolean isBlack(RBTreeNode<T> node){
        return node==null || node.isBlack();
    }
    private boolean isRoot(RBTreeNode<T> node){
        return root.getLeft() == node && node.getParent()==null;
    }
    /**
     * find the successor node
     * @param node current node's right node
     */
    private RBTreeNode<T> removeMin(RBTreeNode<T> node){
        RBTreeNode<T> parent = node;
        while(node != null && node.getLeft() != null){
            parent = node;
            node = node.getLeft();
        }
        if(parent == node){
            return node;
        }
        if (node != null) {
            parent.setLeft(node.getRight());
            setParent(node.getRight(),parent);
        }
        return node;
    }

    /**
     * get uncle node
     */
    private RBTreeNode<T> getUncle(RBTreeNode<T> node){
        RBTreeNode<T> parent = node.getParent();
        RBTreeNode<T> ancestor = parent.getParent();
        if(ancestor==null){
            return null;
        }
        if(parent == ancestor.getLeft()){
            return ancestor.getRight();
        }else{
            return ancestor.getLeft();
        }
    }

    private void rotateLeft(RBTreeNode<T> node){
        RBTreeNode<T> right = node.getRight();
        if(right == null){
            throw new java.lang.IllegalStateException("right node is null");
        }
        RBTreeNode<T> parent = node.getParent();
        node.setRight(right.getLeft());
        setParent(right.getLeft(),node);

        right.setLeft(node);
        setParent(node,right);

        if(parent==null){//node pointer to root
            //right  raise to root node
            root.setLeft(right);
            setParent(right,null);
        }else{
            if(parent.getLeft()==node){
                parent.setLeft(right);
            }else{
                parent.setRight(right);
            }
            //right.setParent(parent);
            setParent(right,parent);
        }
    }

    private void rotateRight(RBTreeNode<T> node){
        RBTreeNode<T> left = node.getLeft();
        if(left == null){
            throw new java.lang.IllegalStateException("left node is null");
        }
        RBTreeNode<T> parent = node.getParent();
        node.setLeft(left.getRight());
        setParent(left.getRight(),node);

        left.setRight(node);
        setParent(node,left);

        if(parent==null){
            root.setLeft(left);
            setParent(left,null);
        } else {
            if(parent.getLeft() == node){
                parent.setLeft(left);
            }else{
                parent.setRight(left);
            }
            setParent(left,parent);
        }
    }


    private void setParent(RBTreeNode<T> node, RBTreeNode<T> parent){
        if(node != null){
            if(parent == root){
                node.setParent(null);
            } else {
                node.setParent(parent);
            }
        }
    }
    /**
     * debug method,it used print the given node and its children nodes,
     * every layer output in one line
     */
    public void printTree(RBTreeNode<T> root){
        LinkedList<RBTreeNode<T>> queue = new LinkedList<>();
        LinkedList<RBTreeNode<T>> queue2 = new LinkedList<>();
        if(root == null){
            return ;
        }
        queue.add(root);
        boolean firstQueue = true;

        while(!queue.isEmpty() || !queue2.isEmpty()){
            LinkedList<RBTreeNode<T>> q = firstQueue ? queue : queue2;
            RBTreeNode<T> n = q.poll();

            if(n != null){
                String pos = n.getParent()==null ? "" : ( n == n.getParent().getLeft()
                    ? " LE" : " RI");
                String pstr = n.getParent()==null ? "" : n.getParent().toString();
                String cstr = n.isRed()?"R":"B";
                cstr = n.getParent() == null ? cstr : cstr+" ";
                System.out.print(n+"("+(cstr)+pstr+(pos)+")"+"\t");
                if(n.getLeft()!=null){
                    (firstQueue ? queue2 : queue).add(n.getLeft());
                }
                if(n.getRight()!=null){
                    (firstQueue ? queue2 : queue).add(n.getRight());
                }
            } else {
                System.out.println();
                firstQueue = !firstQueue;
            }
        }
    }


    public static void main(String[] args) {
        RBTree<String> rbTree = new RBTree<>();
        rbTree.addNode("d");
        rbTree.addNode("d");
        rbTree.addNode("c");
        rbTree.addNode("c");
        rbTree.addNode("b");
        rbTree.addNode("f");
        rbTree.addNode("a");
        rbTree.addNode("e");
        rbTree.addNode("g");
        rbTree.addNode("h");
        rbTree.remove("c");
        rbTree.printTree(rbTree.getRoot());
    }
}
