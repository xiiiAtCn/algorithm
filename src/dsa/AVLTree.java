package dsa;


import dsa.interfaces.*;

/**
 * @author lungern xiii.at.cn@gmail.com
 * @date 2018/2/23
 * <p>
 * 定义:
 * 1. 中序遍历序列相同的任意两棵二叉树, 称作互相"等价"的.
 * 2. 在二叉查找树中, 任一节点的平衡因子都定义为"其左, 右子树的高度差".
 * 3. 在二分查找树T中, 若所有节点的平衡因子的绝对值均不超过1, 则称T为一棵AVL树.
 * <p>
 * 引理:
 * 1. 由n个节点组成的任何一棵二分查找树T, 都与某一棵高度不超过|log2n|的二分查找树S等价
 * 2. zig和zag旋转操作都可以在常数时间内完成
 * 3. 高度为h的AVL树, 至少包含Fib(h+3) - 1个节点
 * 定理:
 * 1. 由n个节点构成的AVL树的高度为O(logn)
 */

public class AVLTree extends BSTree implements Dictionary {
    public AVLTree() {
        super();
    }

    public AVLTree(BinTreePosition binTreePosition) {
        super(binTreePosition);
    }

    public AVLTree(BinTreePosition position, Comparator comparator) {
        super(position, comparator);
    }

    @Override
    public Entry insert(Object key, Object value) {
        Entry e = super.insert(key, value);
        root = rebalance(last.getParent(), root);
        return e;
    }

    @Override
    public Entry remove(Object key) {
        Entry e = super.remove(key);
        if (null != e) {
            root = rebalance(last, root);
        }
        return e;
    }

    protected static BinTreePosition rebalance(BinTreePosition z, BinTreePosition r) {
        if (null == z) {
            return r;
        }
        while (true) {
            if (!isBalanced(z)) {
                rotate(z);
            }
            if (!z.hasParent()) {
                return z;
            }
            z = z.getParent();
        }
    }

    protected static boolean isBalanced(BinTreePosition v) {
        if (null == v) {
            return true;
        }
        int lH = v.hasLChild() ? v.getLChild().getHeight() : -1;
        int rH = v.hasRChild() ? v.getRChild().getHeight() : -1;
        int deltaH = lH - rH;
        return -1<= deltaH && deltaH <= 1;
    }

    protected static BinTreePosition rotate(BinTreePosition tree) {
        BinTreePosition y = tallerChild(tree);
        BinTreePosition x = tallerChild(y);
        boolean cType = tree.isLChild();
        BinTreePosition parent = tree.getParent();
        BinTreePosition a, b, c;
        BinTreePosition t0, t1, t2, t3;

        if (y.isLChild()) {
            c = tree;
            t3 = tree.getRChild();
            if (x.isLChild()) {
                b = y;
                t2 = y.getRChild();
                a = x;
                t1 = x.getRChild();
                t0 = x.getLChild();
            } else {
                a = y;
                t0 = y.getLChild();
                b = x;
                t1 = x.getLChild();
                t2 = x.getRChild();
            }
        } else {
            a = tree;
            t0 = tree.getLChild();
            if (x.isLChild()) {
                b = x;
                t1 = x.getLChild();
                t2 = x.getRChild();
                c = y;
                t3 = y.getRChild();
            } else {
                b = y;
                t1 = y.getLChild();
                c = x;
                t2 = x.getLChild();
                t3 = x.getRChild();
            }
        }
        tree.secede();
        y.secede();
        x.secede();

        if (null != t0) {
            t0.secede();
        }
        if (null != t1) {
            t1.secede();
        }
        if (null != t2) {
            t2.secede();
        }
        if (null != t3) {
            t3.secede();
        }

        a.attachL(t0);
        a.attachR(t1);
        b.attachL(a);
        c.attachL(t2);
        c.attachR(t3);
        b.attachR(c);

        if (null != parent) {
            if (cType) {
                parent.attachL(b);
            } else {
                parent.attachR(b);
            }
        }
        return b;
    }

    //返回节点v的孩子中较高者
    protected static BinTreePosition tallerChild(BinTreePosition v) {
        int lH = v.hasLChild() ? v.getLChild().getHeight() : -1;
        int rH = v.hasRChild() ? v.getRChild().getHeight() : -1;
        if (lH > rH) {
            return v.getLChild();
        }
        if (lH < rH) {
            return v.getRChild();
        }
        if (v.isLChild()) {
            return v.getLChild();
        } else {
            return v.getRChild();
        }
    }

    protected static BinTreePosition shorterChild(BinTreePosition v) {
        int lH = v.hasLChild() ? v.getLChild().getHeight() : -1;
        int rH = v.hasRChild() ? v.getRChild().getHeight() : -1;
        if (lH > rH) {
            return v.getRChild();
        }
        if (lH < rH) {
            return v.getLChild();
        }

        if (v.isLChild()) {
            return v.getRChild();
        } else {
            return v.getLChild();

        }
    }
}
