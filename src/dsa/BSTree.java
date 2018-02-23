import dsa.*;
import dsa.interfaces.*;

/**
 *   @author lungern xiii.at.cn@gmail.com
 *   @date 2018/2/23
 *
 *  定义: 二分查找树T, 要么是一棵空树, 要么是以r=(key, value)为根节点的二叉树, 而且其左, 右子树都是二叉查找树, 同时
 *      1. 在r的左子树中, 所有节点(如果存在的话)的关键码均不大于key
 *      2. 在r的右子树中, 所有节点(如果存在的话)的关键码均不小于key
 *  引理或结论:
 *      1. 二叉树T为二叉查找树, 当且仅当其中序遍历序列是单调非降的
 *      2. 在查找算法中, 每次深入左(右)子树时, 被忽略的右(左)子树中必然不含目标节点
 *      3. 在任一二分查找树T中, 若至少存在一个关键码为key的节点, 则这些节点中深度最小者必然唯一
 *      4. 在二分查找树中查找一个节点需要O(h)时间, 其中h为目标节点的深度(查找成功时), 或者是二分查找树的高度(查找失败时).
 *      5. 在二分查找树中插入一个节点需要O(h)时间, 其中h为被插入节点的深度
 *      6. 在二分查找树中删除一个节点需要O(h)时间, 其中h为被删除节点的深度
 *  定理:
 *      1. 由n个互异条目随机生成的BST, 平均查找长度为O(logn)
 *      2. 由n个互异节点组成的二分查找树, 总共有(2n)!/n!/(n+1)!棵
 */

public class BSTree extends BinTree_LinkedList implements Dictionary {
    protected Comparator C;
    protected BinTreePosition last;

    public BSTree() {
        this(null, new ComparatorDefault());
    }

    public BSTree(BinTreePosition r) {
        this(r, new ComparatorDefault());
    }

    public BSTree(BinTreePosition r, Comparator c) {
        root = r;
        C = c;
    }


    @Override
    public Entry find(Object key) {
        if (isEmpty()) {
            return null;
        }
        BSTreeNode u = binSearch((BSTreeNode) root, key, C);
        return 0 == C.compare(key, u.getKey()) ? (Entry) u.getElement() : null;
    }

    @Override
    public Iterator findAll(Object key) {
        List s = new List_DLNode();
        findAllNodes((BSTreeNode) root, key, s, C);
        return s.elements();
    }

    @Override
    public Entry insert(Object key, Object value) {
        Entry e = new EntryDefault(key, value);
        if (isEmpty()) {
            last = root = new BinTreeNode(e, null, true, null, null);
        } else {
            BSTreeNode p = (BSTreeNode) root;
            boolean asLeftChild;
            while (true) {
                p = binSearch(p, key, C);
                if (C.compare(key, p.getKey()) < 0) {
                    asLeftChild = true;
                    break;
                } else if (C.compare(key, p.getKey()) > 0) {
                    asLeftChild = false;
                    break;
                } else if (!p.hasLChild()) {
                    asLeftChild = true;
                    break;
                } else if (!p.hasRChild()) {
                    asLeftChild = false;
                    break;
                } else {
                    p = (BSTreeNode) p.getLChild();
                }
            }
            last = new BSTreeNode(e, p, asLeftChild, null, null);
        }
        return e;
    }

    @Override
    public Entry remove(Object key) {
        if (isEmpty()) {
            return null;
        }
        BinTreePosition v = binSearch((BSTreeNode) root, key, C);
        if (0 != C.compare(key, ((BSTreeNode) v).getKey())) {
            return null;
        }
        if (v.hasLChild()) {
            BinTreePosition w = v.getPrev();
            w.setElement(v.getElement());
            v = w;
            last = v.getParent();
            BinTreePosition u = v.hasLChild() ? v.getLChild() : v.getRChild();
            if (null == last) {
                if (null != u) {
                    u.secede();
                    root = u;
                } else {
                    if (v.isLChild()) {
                        v.secede();
                        last.attachL(u);
                    } else {
                        v.secede();
                        last.attachR(u);
                    }
                }
            }
        }
        return (Entry)v.getElement();
    }

    @Override
    public Iterator entries() {
        List list = new List_DLNode();
        concatenate(list, (BSTreeNode)root);
        return list.elements();
    }

    protected static BSTreeNode binSearch(BSTreeNode v, Object key, Comparator c) {
        BSTreeNode u = v;
        while (true) {
            int comp = c.compare(key, u.getKey());
            if (comp < 0) {
                if (u.hasLChild()) {
                    u = (BSTreeNode) u.getLChild();
                } else {
                    return u;
                }
            } else if (comp > 0) {
                if (u.hasRChild()) {
                    u = (BSTreeNode) u.getRChild();
                } else {
                    return u;
                }
            } else {
                return u;
            }
        }
    }

    protected static void findAllNodes(BSTreeNode v, Object k, List s, Comparator c) {
        if (null == v) {
            return;
        }
        int comp = c.compare(k, v.getKey());
        if (comp >= 0) {
            findAllNodes((BSTreeNode) v.getLChild(), k, s, c);
        }
        if (0 == comp) {
            s.insertLast(v);
        }
        if (0 <= comp) {
            findAllNodes((BSTreeNode) v.getRChild(), k, s, c);
        }
    }

    protected static void concatenate(List list, BSTreeNode v) {
        if (null == v) {
            return;
        }
        concatenate(list, (BSTreeNode) v.getLChild());
        list.insertLast(v.getElement());
        concatenate(list, (BSTreeNode) v.getRChild());
    }
}
