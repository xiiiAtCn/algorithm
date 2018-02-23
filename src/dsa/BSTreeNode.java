package dsa;

/**
 *   @author lungern xiii.at.cn@gmail.com
 *   @date 2018/2/11
 *   基于链表实现的BST节点类
 */

import dsa.interfaces.BinTreePosition;
import dsa.interfaces.Entry;

public class BSTreeNode extends BinTreeNode implements BinTreePosition, Entry {

    public BSTreeNode() {
        super();
    }

    public BSTreeNode(Object e, BinTreePosition p, boolean asLChild, BinTreePosition l, BinTreePosition r) {
        super(e, p, asLChild, l, r);
    }

    @Override
    public Object getKey() {
        return ((Entry)getElement()).getKey();
    }

    @Override
    public Object setKey(Object k) {
        return ((Entry)getElement()).setKey(k);
    }

    @Override
    public Object getValue() {
        return ((Entry)getElement()).getValue();
    }

    @Override
    public Object setValue(Object v) {
        return ((Entry)getElement()).setValue(v);
    }
}
