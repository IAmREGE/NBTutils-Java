package rege.rege.nbtutils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author REGE
 * @since 0.0.1a1
 */
public class NBTList implements Iterable<NBTTag> {
    private final List<NBTTag> list;

    public NBTList() {
        this.list = new ArrayList<NBTTag>();
    }

    public NBTList(NBTList toCopy) {
        this.list = new ArrayList<NBTTag>(toCopy.list);
    }

    public NBTList(List<NBTTag> list)
    throws NullPointerException, IllegalArgumentException {
        if (list == null) {
            throw new NullPointerException("List is null");
        }
        int index = 0;
        final NBTTagType CHECKTYPE = (list.isEmpty()) ? NBTTagType.TAG_End :
                                     (list.get(0).type);
        for (NBTTag i : list) {
            if (i == null) {
                throw new NullPointerException(
                    "List element " + Integer.toString(index) + " is null"
                );
            }
            if (i.type != CHECKTYPE) {
                throw new IllegalArgumentException(
                    "Tag types in the list are not all the same"
                );
            }
            index++;
        }
        this.list = new ArrayList<NBTTag>(list);
    }

    //@Override
    public Iterator<NBTTag> iterator() {
        return this.list.iterator();
    }

    public int size() {
        return this.list.size();
    }

    public NBTTag get(int index) throws IndexOutOfBoundsException {
        return this.list.get((index < 0) ? index + this.list.size() : index);
    }

    public NBTList append(NBTTag value) {
        if (this.size() == 0 || this.list.get(0).type == value.type) {
            this.list.add(value);
        }
        return this;
    }

    public NBTList set(int index, NBTTag value) {
        if (index < 0) {
            index += this.list.size();
        }
        if (index >= 0 && index < this.list.size() &&
            this.list.get(0).type == value.type) {
            this.list.set(index, value);
        }
        return this;
    }

    @Override
    public String toString() {
        final int SIZE = this.size();
        if (SIZE == 0) {
            return "[]";
        }
        final StringBuilder SB = new StringBuilder();
        SB.append('[');
        for (NBTTag i : this.list) {
            SB.append(i.toString());
            SB.append(',');
        }
        SB.setCharAt(SB.length() - 1, ']');
        return SB.toString();
    }
}