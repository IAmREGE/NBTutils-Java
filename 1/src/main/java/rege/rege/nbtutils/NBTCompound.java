package rege.rege.nbtutils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import rege.rege.utf8chr.UTF8Char;
import rege.rege.utf8chr.UTF8Sequence;

/**
 * @author REGE
 * @since 0.0.1a1
 */
public class NBTCompound {
    private final Map<UTF8Sequence, NBTTag> map;

    public NBTCompound() {
        this.map = new HashMap<UTF8Sequence, NBTTag>();
    }

    public NBTCompound(NBTCompound toCopy) {
        this.map = new HashMap<UTF8Sequence, NBTTag>(toCopy.map);
    }

    public NBTCompound(Map<UTF8Sequence, NBTTag> map)
    throws NullPointerException {
        for (Entry<UTF8Sequence, NBTTag> i : map.entrySet()) {
            if (i.getKey() == null) {
                throw new NullPointerException("Name is null");
            }
            if (i.getValue() == null) {
                throw new NullPointerException("Tag is null");
            }
            final int LENGTH = i.getKey().length();
            if (LENGTH > 65535) {
                throw new IllegalArgumentException(
                    "one of the names is with a length of " +
                    Integer.toString(LENGTH) + " which is greater than 65535");
            }
        }
        this.map = new HashMap<UTF8Sequence, NBTTag>(map);
    }

    public NBTTag set(UTF8Sequence name, NBTTag tag)
    throws NullPointerException {
        if (name == null) {
            throw new NullPointerException("Name is null");
        }
        final int LENGTH = name.length();
        if (LENGTH > 65535) {
            throw new IllegalArgumentException("Name is with a length of " +
                                               Integer.toString(LENGTH) +
                                               " which is greater than 65535");
        }
        if (tag == null) {
            return this.get(name);
        }
        return this.map.put(name, tag);
    }

    public NBTTag get(UTF8Sequence name)
    throws NullPointerException {
        return this.map.get(name);
    }

    public NBTTag remove(UTF8Sequence name)
    throws NullPointerException {
        return this.map.remove(name);
    }

    public NBTCompound merge(NBTCompound compound) {
        for (Entry<UTF8Sequence, NBTTag> i : compound.map.entrySet()) {
            this.set(i.getKey(), i.getValue());
        }
        return this;
    }

    public NBTCompound merge(NBTCompound[] compounds) {
        for (int i = 0; i < compounds.length; i++) {
            this.merge(compounds[i]);
        }
        return this;
    }

    public NBTCompound merge(Iterable<NBTCompound> compounds) {
        for (NBTCompound i : compounds) {
            this.merge(i);
        }
        return this;
    }

    public Set<UTF8Sequence> names() {
        return this.map.keySet();
    }

    public boolean containsName(UTF8Sequence name) {
        return this.map.containsKey(name);
    }

    public boolean containsName(UTF8Sequence name, NBTTagType type) {
        return this.containsName(name) &&
               (type == null || this.get(name).type == type);
    }

    @Override
    public String toString() {
        final int SIZE = this.size();
        if (SIZE == 0) {
            return "[]";
        }
        final StringBuilder SB = new StringBuilder();
        SB.append('{');
        for (Entry<UTF8Sequence, NBTTag> i : this.map.entrySet()) {
            if (i.getKey().strip(new UTF8Sequence(
                "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_-.+"
            )).isEmpty() && !(i.getKey().isEmpty())) {
                SB.append(i.getKey().toString());
            } else {
                SB.append('"');
                final UTF8Char QUOTE = new UTF8Char('"');
                final UTF8Char BACKSLASH = new UTF8Char('\\');
                SB.append(i.getKey().replace(BACKSLASH, new UTF8Sequence(
                    new UTF8Char[]{BACKSLASH, BACKSLASH}
                )).replace(QUOTE, new UTF8Sequence(new UTF8Char[]{
                    BACKSLASH, QUOTE
                })).toString());
                SB.append('"');
            }
            SB.append(i.getValue().toString());
            SB.append(',');
        }
        SB.setCharAt(SB.length() - 1, '}');
        return SB.toString();
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof NBTCompound) &&
               this.map.equals(((NBTCompound)o).map);
    }

    @Override
    public int hashCode() {
        return this.map.hashCode();
    }

    public int size() {
        return this.map.size();
    }
}