package dsa;
/**
 * @author lungern lungern.site@outlook.com
 * @date 2018/3/2 11:44
 * @description
 * m阶B-树, 是满足以下条件的m路平衡查找树: 其中的每一内部节点, 都存在n个关键码{K1 < K2 < ... Kn}和n+1个引用{A0, A1, A2, ..., An}, n+1 <= m.
 * 对于每一非内部节点, 都有 n+1 >= [m/2]; 对于根节点, 除非它同时是叶子节点, 否则必有n+1 >= 2
 *
 */

public class BTree<K, V> {
    private BTreeLeafNode<K, V> root;
    private int rank;
    public BTree(int rank) throws Exception{
        if (rank < 2) {
            throw new Exception("rank is too small");
        } else if (rank == 2) {
            System.out.println("warn: B-tree will degenerate to BSTree");
        }
        this.rank = rank;
    }

    public BTree() {

    }

    public <K, V> BTreeLeafNode<K, V> insertKey(BTree<K, V> tree, BTreeLeafNode<K, V> node) {
        K key = node.getKey();

        return node;
    }
}
