package Town;

import java.util.Comparator;

class NodeArc implements Comparator<NodeArc> {
    public int node;
    public int cost;

    public NodeArc() {
        this.node = 0;
        this.cost = 0;
    }

    public NodeArc(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compare(NodeArc node1, NodeArc node2) {
        if (node1.cost < node2.cost)
            return -1;
        if (node1.cost > node2.cost)
            return 1;
        return 0;
    }

    @Override
    public String toString() {
        return "NodeArc{" +
                "node=" + node +
                ", cost=" + cost +
                '}';
    }
}
