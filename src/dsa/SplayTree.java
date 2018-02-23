package dsa;

import dsa.interfaces.*;

/**
 *   @author lungern xiii.at.cn@gmail.com
 *   @date 2018/2/23
 *   伸展树
 */

public class SplayTree extends BSTree implements Dictionary {

    public SplayTree() {
        super();
    }

    public SplayTree(BinTreePosition position) {
        super(position);
    }

    public SplayTree(BinTreePosition position, Comparator comparator) {
        super(position, comparator);
    }

    @Override
    public Entry find(Object key) {
        if (isEmpty()) {
            return null;
        }
        BSTreeNode u = binSearch((BSTreeNode) root, key, C);
        root = moveToRoot(u);
        return 0 == C.compare(key, u.getKey()) ? (Entry) u.getElement() : null;
    }

    @Override
    public Entry insert(Object key, Object value) {
        Entry e = super.insert(key, value);
        root = moveToRoot(last);
        return e;
    }

    @Override
    public Entry remove(Object key) {
        Entry e = super.remove(key);
        if (null != e && null != last) {
            root = moveToRoot(last);
        }
        return e;
    }

    protected static BinTreePosition moveToRoot(BinTreePosition position) {
        while (position.hasParent()) {
            if (!position.getParent().hasParent()) {
                if (position.isLChild()) {
                    position = zig(position);
                } else {
                    position = zag(position);
                }
            } else {
                if (position.isLChild()) {
                    if (position.getParent().isLChild()) {
                        position = zigzig(position);
                    } else {
                        position = zigzag(position);
                    }
                } else {
                    if (position.getParent().isLChild()) {
                        position = zagzig(position);
                        position = zagzag(position);
                    }
                }
            }
        }
        return position;
    }

    //顺时针旋转
    protected static BinTreePosition zig(BinTreePosition position) {
        if (null != position && position.isLChild()) {
            BinTreePosition p = position.getParent();
            BinTreePosition g = p.getParent();
            boolean asLChild = p.isLChild();
            position.secede();
            BinTreePosition c = position.getRChild();
            if (null != c) {
                p.attachL(c.secede());
            }
            p.secede();
            position.attachR(p);
            if (null != g) {
                if (asLChild) {
                    g.attachL(position);
                } else {
                    g.attachR(position);
                }
            }
        }
        return position;
    }

    //逆时针旋转
    protected static BinTreePosition zag(BinTreePosition position) {
        if (null != position && position.isRChild()) {
            BinTreePosition p = position.getParent();
            BinTreePosition g = p.getParent();
            boolean asLChild = p.isLChild();
            position.secede();
            BinTreePosition c = position.getLChild();
            if (null != c) {
                p.attachR(c.secede());
            }
            p.secede();
            position.attachL(p);
            if (null != g) {
                if (asLChild) {
                    g.attachL(position);
                } else {
                    g.attachR(position);
                }
            }
        }
        return position;
    }

    //position为左孩子, 父亲为左孩子, 顺时针旋转v, 使之上升两层
    protected static BinTreePosition zigzig(BinTreePosition position) {
        if (null != position && position.isLChild() && position.hasParent() && position.getParent().isLChild()) {
            BinTreePosition p = position.getParent();
            BinTreePosition g = p.getParent();
            BinTreePosition s = g.getParent();

            boolean asLChild = g.isLChild();
            g.secede();
            p.secede();
            position.secede();
            BinTreePosition c;
            c =p.getRChild();
            if (null != c) {
                g.attachL(c.secede());
            }
            c = position.getRChild();
            if (null != c) {
                p.attachL(c.secede());
            }
            p.attachR(g);
            position.attachR(p);
            if (null != s) {
                if (asLChild) {
                    s.attachL(position);
                } else {
                    s.attachR(position);
                }
            }
        }
        return position;
    }

    protected static BinTreePosition zigzag(BinTreePosition position) {
        if (null != position && position.isLChild() && position.hasParent() && position.getParent().isRChild()) {
            BinTreePosition p = position.getParent();
            BinTreePosition g = p.getParent();
            BinTreePosition s = g.getParent();
            boolean asLChild = g.isLChild();
            g.secede();
            p.secede();
            position.secede();
            BinTreePosition c;
            c = position.getLChild();
            if (null != c) {
                g.attachR(c.secede());
            }
            c = position.getRChild();
            if (null != c) {
                p.attachL(c.secede());
            }
            position.attachL(g);
            position.attachR(p);
            if (null != s) {
                if (asLChild) {
                    s.attachL(position);
                } else {
                    s.attachR(position);
                }
            }
        }
        return position;
    }

    protected static BinTreePosition zagzig(BinTreePosition position) {

        if (null != position && position.isRChild() && position.hasParent() && position.getParent().isLChild()) {
            BinTreePosition p = position.getParent();
            BinTreePosition g = p.getParent();
            BinTreePosition s = g.getParent();
            boolean asLChild = g.isLChild();

            g.secede();
            p.secede();
            position.secede();
            BinTreePosition c;
            c = position.getRChild();
            if (null != c) {
                g.attachL(c.secede());
            }
            c = position.getLChild();
            if (null != c) {
                p.attachR(c.secede());
            }
            position.attachR(g);
            position.attachL(p);
            if (null != s) {
                if (asLChild) {
                    s.attachL(position);
                } else {
                    s.attachR(position);
                }
            }
        }
        return position;
    }

    protected static BinTreePosition zagzag(BinTreePosition position) {
        if (null != position && position.isRChild() && position.hasParent() && position.getParent().isRChild()) {
            BinTreePosition p = position.getParent();
            BinTreePosition g = p.getParent();
            BinTreePosition s = g.getParent();
            boolean asLChild = g.isLChild();
            g.secede();
            p.secede();
            position.secede();

            BinTreePosition c;

            c = p.getLChild();
            if (null != c) {
                g.attachR(c.secede());
            }
            c = position.getLChild();
            if (null != c) {
                p.attachR(c.secede());
            }
            p.attachL(g);
            position.attachL(p);
            if (null != s) {
                if (asLChild) {
                    s.attachL(position);
                } else {
                    s.attachR(position);
                }
            }
        }
        return position;
    }
}
