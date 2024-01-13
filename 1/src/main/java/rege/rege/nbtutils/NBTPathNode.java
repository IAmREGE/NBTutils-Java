package rege.rege.nbtutils;

import rege.rege.utf8chr.UTF8Char;
import rege.rege.utf8chr.UTF8Sequence;

/**
 * @author REGE
 * @since 0.0.1a1
 */
public class NBTPathNode {
    public final int asIndex;
    public final UTF8Sequence asName;

    public NBTPathNode(int index) {
        this.asIndex = index;
        this.asName = null;
    }

    public NBTPathNode(UTF8Sequence name)
    throws NullPointerException, IllegalArgumentException {
        if (name == null) {
            throw new NullPointerException("string is null");
        }
        for (UTF8Char i : name) {
            final long ORD = i.ord();
            if (ORD == -1L || ORD > 0x7fffffffL) {
                throw new IllegalArgumentException("string is not valid");
            }
        }
        this.asIndex = 0;
        this.asName = name;
    }

    public NBTPathNode(String name) throws NullPointerException {
        this(new UTF8Sequence(name));
    }

    public boolean isAsIndex() {
        return this.asName == null;
    }

    public boolean isAsName() {
        return this.asName != null;
    }

    @Override
    public String toString() {
        if (this.isAsIndex()) {
            return "[" + Integer.toString(this.asIndex) + "]";
        }
        if (this.asName.isEmpty()) {
            return "\"\"";
        }
        final UTF8Char QUOTE = new UTF8Char('"');
        final UTF8Char BACKSLASH = new UTF8Char('\\');
        if (this.asName.indexOf(QUOTE) == -1 &&
            this.asName.indexOf(BACKSLASH) == -1 &&
            this.asName.indexOf(new UTF8Char(' ')) == -1 &&
            this.asName.indexOf(new UTF8Char('.')) == -1 &&
            this.asName.indexOf(new UTF8Char('[')) == -1 &&
            this.asName.indexOf(new UTF8Char(']')) == -1 &&
            this.asName.indexOf(new UTF8Char('{')) == -1 &&
            this.asName.indexOf(new UTF8Char('}')) == -1) {
            return this.asName.toString();
        }
        return "\"" + this.asName.replace(BACKSLASH, new UTF8Sequence(
            new UTF8Char[]{BACKSLASH, BACKSLASH}
        )).replace(QUOTE, new UTF8Sequence(new UTF8Char[]{
            BACKSLASH, QUOTE
        })).toString() + "\"";
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof NBTPathNode) {
            final NBTPathNode CVT = (NBTPathNode)o;
            return this.isAsIndex() ?
                   CVT.isAsIndex() && this.asIndex == CVT.asIndex :
                   this.asName.equals(CVT.asName);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.isAsIndex() ? Integer.hashCode(this.asIndex) :
               this.asName.hashCode();
    }
}