package rege.rege.nbtutils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import rege.rege.utf8chr.UTF8Sequence;

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
        }
        this.map = new HashMap<UTF8Sequence, NBTTag>(map);
    }

    public NBTTag set(UTF8Sequence name, NBTTag tag)
    throws NullPointerException {
        if (name == null) {
            throw new NullPointerException("Name is null");
        }
        if (tag == null) {
            return this.remove(name);
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
}