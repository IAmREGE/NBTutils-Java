package rege.rege.nbtutils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author REGE
 * @since 0.0.1a1
 */
public class NBTPath implements Iterable<NBTPathNode> {
    private final NBTPathNode[] nodes;

    public NBTPath(NBTPathNode... nodes) throws NullPointerException {
        this.nodes = new NBTPathNode[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == null) {
                throw new NullPointerException(
                    "Node " + Integer.toString(i) + " is null"
                );
            }
            this.nodes[i] = nodes[i];
        }
    }

    public NBTPath(Iterable<NBTPathNode> nodes) throws NullPointerException {
        final List<NBTPathNode> R = new ArrayList<NBTPathNode>();
        int index = 0;
        for (NBTPathNode i : nodes) {
            if (i == null) {
                throw new NullPointerException(
                    "Node " + Integer.toString(index) + " is null"
                );
            }
            R.add(i);
        }
        this.nodes = new NBTPathNode[R.size()];
        R.toArray(this.nodes);
    }

    public NBTPathNode getNode(int index) throws IndexOutOfBoundsException {
        return this.nodes[index];
    }

    public NBTPath subPath(int start, int stop) throws IndexOutOfBoundsException {
        if (start > stop) {
            throw new IndexOutOfBoundsException();
        }
        final NBTPathNode[] R = new NBTPathNode[stop - start];
        for (int i = start; i < stop; i++) {
            R[i - start] = this.nodes[i];
        }
        return new NBTPath(R);
    }

    public NBTPath subPath(int start) throws IndexOutOfBoundsException {
        return this.subPath(start, this.nodes.length);
    }

    public boolean isRoot() {
        return this.nodes.length == 0;
    }

    public NBTPathNode lastNode() {
        return this.isRoot() ? null : (this.nodes[this.nodes.length - 1]);
    }

    @Override
    public String toString() {
        if (this.isRoot()) {
            return "{}";
        }
        final StringBuilder SB = new StringBuilder();
        SB.append(this.nodes[0].toString());
        for (int i = 1; i < this.nodes.length; i++) {
            if (this.nodes[i].isAsName()) {
                SB.append('.');
            }
            SB.append(this.nodes[i].toString());
        }
        return SB.toString();
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof NBTPath) && Arrays.equals(this.nodes, ((NBTPath)o).nodes);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.nodes);
    }

    //@Override
    public Iterator<NBTPathNode> iterator() {
        return Arrays.asList(this.nodes).iterator();
    }

    public NBTPath connect(NBTPathNode node) throws NullPointerException {
        if (node == null) {
            throw new NullPointerException("Node is null");
        }
        final NBTPathNode[] R = new NBTPathNode[this.nodes.length + 1];
        for (int i = 0; i < this.nodes.length; i++) {
            R[i] = this.nodes[i];
        }
        R[this.nodes.length] = node;
        return new NBTPath(R);
    }
}