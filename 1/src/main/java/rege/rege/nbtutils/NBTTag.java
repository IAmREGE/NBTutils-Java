package rege.rege.nbtutils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rege.rege.utf8chr.UTF8Char;
import rege.rege.utf8chr.UTF8Sequence;

/**
 * A class for creating NBT tags, with a specific type.
 * @author REGE
 * @since 0.0.1a1
 * @see NBTTagType
 */
public class NBTTag {
    /**
     * The type of this tag.
     * @see NBTTagType
     */
    public final NBTTagType type;
    /**
     * The value stored that if the tag type is TAG_Byte.
     */
    private final byte asByte;
    /**
     * The value stored that if the tag type is TAG_Short.
     */
    private final short asShort;
    /**
     * The value stored that if the tag type is TAG_Int.
     */
    private final int asInt;
    /**
     * The value stored that if the tag type is TAG_Long.
     */
    private final long asLong;
    /**
     * The value stored that if the tag type is TAG_Float.
     */
    private final float asFloat;
    /**
     * The value stored that if the tag type is TAG_Double.
     */
    private final double asDouble;
    /**
     * The value stored that if the tag type is TAG_Byte_Array.
     */
    private final byte[] asByteArray;
    /**
     * The value stored that if the tag type is TAG_String.
     * @see rege.rege.utf8chr.UTF8Sequence
     */
    private final UTF8Sequence asString;
    /**
     * The value stored that if the tag type is TAG_List.
     * @see NBTList
     */
    private final NBTList asList;
    /**
     * The value stored that if the tag type is TAG_Compound.
     * @see NBTCompound
     */
    private final NBTCompound asCompound;
    /**
     * The value stored that if the tag type is TAG_Int_Array.
     */
    private final int[] asIntArray;
    /**
     * The value stored that if the tag type is TAG_Long_Array.
     */
    private final long[] asLongArray;

    public NBTTag(NBTTagType type) {
        this.type = (type == null) ? NBTTagType.TAG_End : type;
        this.asByte = 0;
        this.asShort = 0;
        this.asInt = 0;
        this.asLong = 0L;
        this.asFloat = 0f;
        this.asDouble = 0.;
        this.asByteArray =
        (type == NBTTagType.TAG_Byte_Array) ? new byte[0] : null;
        this.asString =
        (type == NBTTagType.TAG_String) ? new UTF8Sequence() : null;
        this.asList =
        (type == NBTTagType.TAG_List) ? new NBTList() : null;
        this.asCompound =
        (type == NBTTagType.TAG_Compound) ? new NBTCompound() : null;
        this.asIntArray =
        (type == NBTTagType.TAG_Int_Array) ? new int[0] : null;
        this.asLongArray =
        (type == NBTTagType.TAG_Long_Array) ? new long[0] : null;
    }

    public NBTTag(boolean value) {
        this.type = NBTTagType.TAG_Byte;
        this.asByte = (byte)(value ? 1 : 0);
        this.asShort = 0;
        this.asInt = 0;
        this.asLong = 0L;
        this.asFloat = 0f;
        this.asDouble = 0.;
        this.asByteArray = null;
        this.asString = null;
        this.asList = null;
        this.asCompound = null;
        this.asIntArray = null;
        this.asLongArray = null;
    }

    public NBTTag(byte value) {
        this.type = NBTTagType.TAG_Byte;
        this.asByte = value;
        this.asShort = 0;
        this.asInt = 0;
        this.asLong = 0L;
        this.asFloat = 0f;
        this.asDouble = 0.;
        this.asByteArray = null;
        this.asString = null;
        this.asList = null;
        this.asCompound = null;
        this.asIntArray = null;
        this.asLongArray = null;
    }

    public NBTTag(short value) {
        this.type = NBTTagType.TAG_Short;
        this.asByte = 0;
        this.asShort = value;
        this.asInt = 0;
        this.asLong = 0L;
        this.asFloat = 0f;
        this.asDouble = 0.;
        this.asByteArray = null;
        this.asString = null;
        this.asList = null;
        this.asCompound = null;
        this.asIntArray = null;
        this.asLongArray = null;
    }

    public NBTTag(int value) {
        this.type = NBTTagType.TAG_Int;
        this.asByte = 0;
        this.asShort = 0;
        this.asInt = value;
        this.asLong = 0L;
        this.asFloat = 0f;
        this.asDouble = 0.;
        this.asByteArray = null;
        this.asString = null;
        this.asList = null;
        this.asCompound = null;
        this.asIntArray = null;
        this.asLongArray = null;
    }

    public NBTTag(long value) {
        this.type = NBTTagType.TAG_Long;
        this.asByte = 0;
        this.asShort = 0;
        this.asInt = 0;
        this.asLong = value;
        this.asFloat = 0f;
        this.asDouble = 0.;
        this.asByteArray = null;
        this.asString = null;
        this.asList = null;
        this.asCompound = null;
        this.asIntArray = null;
        this.asLongArray = null;
    }

    public NBTTag(float value) {
        this.type = NBTTagType.TAG_Float;
        this.asByte = 0;
        this.asShort = 0;
        this.asInt = 0;
        this.asLong = 0L;
        this.asFloat = value;
        this.asDouble = 0.;
        this.asByteArray = null;
        this.asString = null;
        this.asList = null;
        this.asCompound = null;
        this.asIntArray = null;
        this.asLongArray = null;
    }

    public NBTTag(double value) {
        this.type = NBTTagType.TAG_Double;
        this.asByte = 0;
        this.asShort = 0;
        this.asInt = 0;
        this.asLong = 0L;
        this.asFloat = 0f;
        this.asDouble = value;
        this.asByteArray = null;
        this.asString = null;
        this.asList = null;
        this.asCompound = null;
        this.asIntArray = null;
        this.asLongArray = null;
    }

    public NBTTag(byte[] value) throws NullPointerException {
        if (value == null) {
            throw new NullPointerException("byte array is null");
        }
        this.type = NBTTagType.TAG_Byte_Array;
        this.asByte = 0;
        this.asShort = 0;
        this.asInt = 0;
        this.asLong = 0L;
        this.asFloat = 0f;
        this.asDouble = 0.;
        this.asByteArray = new byte[value.length];
        this.asString = null;
        this.asList = null;
        this.asCompound = null;
        this.asIntArray = null;
        this.asLongArray = null;
        for (int i = 0; i < value.length; i++) {
            this.asByteArray[i] = value[i];
        }
    }

    public NBTTag(UTF8Sequence value)
    throws NullPointerException, IllegalArgumentException {
        if (value == null) {
            throw new NullPointerException("string is null");
        }
        final int LENGTH = value.length();
        if (LENGTH > 65535) {
            throw new IllegalArgumentException("string is with a length of " +
                                               Integer.toString(LENGTH) +
                                               " which is greater than 65535");
        }
        int index = 0;
        for (UTF8Char i : value) {
            final long ORD = i.ord();
            if (ORD == -1L || ORD > 0x7fffffffL) {
                throw new IllegalArgumentException(
                    "string is not valid at index " + Integer.toString(index)
                );
            }
            index++;
        }
        this.type = NBTTagType.TAG_String;
        this.asByte = 0;
        this.asShort = 0;
        this.asInt = 0;
        this.asLong = 0L;
        this.asFloat = 0f;
        this.asDouble = 0.;
        this.asByteArray = null;
        this.asString = value.regularAll();
        this.asList = null;
        this.asCompound = null;
        this.asIntArray = null;
        this.asLongArray = null;
    }

    public NBTTag(String value) throws NullPointerException {
        this(new UTF8Sequence(value));
    }

    public NBTTag(NBTList value) throws NullPointerException {
        if (value == null) {
            throw new NullPointerException("NBTList is null");
        }
        this.type = NBTTagType.TAG_List;
        this.asByte = 0;
        this.asShort = 0;
        this.asInt = 0;
        this.asLong = 0L;
        this.asFloat = 0f;
        this.asDouble = 0.;
        this.asByteArray = null;
        this.asString = null;
        this.asList = new NBTList(value);
        this.asCompound = null;
        this.asIntArray = null;
        this.asLongArray = null;
    }

    public NBTTag(NBTCompound value) throws NullPointerException {
        if (value == null) {
            throw new NullPointerException("NBTCompound is null");
        }
        this.type = NBTTagType.TAG_Compound;
        this.asByte = 0;
        this.asShort = 0;
        this.asInt = 0;
        this.asLong = 0L;
        this.asFloat = 0f;
        this.asDouble = 0.;
        this.asByteArray = null;
        this.asString = null;
        this.asList = null;
        this.asCompound = new NBTCompound(value);
        this.asIntArray = null;
        this.asLongArray = null;
    }

    public NBTTag(int[] value) throws NullPointerException {
        if (value == null) {
            throw new NullPointerException("int array is null");
        }
        this.type = NBTTagType.TAG_Int_Array;
        this.asByte = 0;
        this.asShort = 0;
        this.asInt = 0;
        this.asLong = 0L;
        this.asFloat = 0f;
        this.asDouble = 0.;
        this.asByteArray = null;
        this.asString = null;
        this.asList = null;
        this.asCompound = null;
        this.asIntArray = new int[value.length];
        this.asLongArray = null;
        for (int i = 0; i < value.length; i++) {
            this.asIntArray[i] = value[i];
        }
    }

    public NBTTag(long[] value) throws NullPointerException {
        if (value == null) {
            throw new NullPointerException("int array is null");
        }
        this.type = NBTTagType.TAG_Int_Array;
        this.asByte = 0;
        this.asShort = 0;
        this.asInt = 0;
        this.asLong = 0L;
        this.asFloat = 0f;
        this.asDouble = 0.;
        this.asByteArray = null;
        this.asString = null;
        this.asList = null;
        this.asCompound = null;
        this.asIntArray = null;
        this.asLongArray = new long[value.length];
        for (int i = 0; i < value.length; i++) {
            this.asLongArray[i] = value[i];
        }
    }

    @Override
    public String toString() {
        switch (this.type) {
            case TAG_Byte: return Byte.toString(this.asByte) + "b";
            case TAG_Short: return Short.toString(this.asShort) + "s";
            case TAG_Int: return Integer.toString(this.asInt);
            case TAG_Long: return Long.toString(this.asLong) + "L";
            case TAG_Float: return Float.toString(this.asFloat) + "f";
            case TAG_Double: return Double.toString(this.asDouble) + "d";
            case TAG_Byte_Array: {
                if (this.asByteArray.length == 0) {
                    return "[B;]";
                }
                final StringBuilder SB = new StringBuilder();
                SB.append("[B;");
                SB.append((int)(this.asByteArray[0]));
                SB.append('B');
                for (int i = 1; i < this.asByteArray.length; i++) {
                    SB.append(',');
                    SB.append((int)(this.asByteArray[i]));
                    SB.append('B');
                }
                SB.append(']');
                return SB.toString();
            }
            case TAG_String: {
                final UTF8Char BACKSLASH = new UTF8Char('\\');
                final UTF8Char QUOTE = new UTF8Char('"');
                return this.asString.replace(BACKSLASH, new UTF8Sequence(
                    new UTF8Char[]{BACKSLASH, BACKSLASH}
                )).replace(QUOTE, new UTF8Sequence(new UTF8Char[]{
                    BACKSLASH, QUOTE
                })).toString();
            }
            case TAG_List: {
                if (this.asList.size() == 0) {
                    return "[]";
                }
                final StringBuilder SB = new StringBuilder();
                SB.append("[");
                for (NBTTag i : this.asList) {
                    SB.append(i.toString());
                    SB.append(',');
                }
                SB.setCharAt(SB.length() - 1, ']');
                return SB.toString();
            }
            case TAG_Compound: return this.asCompound.toString();
            case TAG_Int_Array: {
                if (this.asIntArray.length == 0) {
                    return "[I;]";
                }
                final StringBuilder SB = new StringBuilder();
                SB.append("[I;");
                SB.append(this.asIntArray[0]);
                for (int i = 1; i < this.asIntArray.length; i++) {
                    SB.append(',');
                    SB.append(this.asIntArray[i]);
                }
                SB.append(']');
                return SB.toString();
            }
            case TAG_Long_Array: {
                if (this.asLongArray.length == 0) {
                    return "[L;]";
                }
                final StringBuilder SB = new StringBuilder();
                SB.append("[L;");
                SB.append(this.asLongArray[0]);
                SB.append('L');
                for (int i = 1; i < this.asLongArray.length; i++) {
                    SB.append(',');
                    SB.append(this.asLongArray[i]);
                    SB.append('L');
                }
                SB.append(']');
                return SB.toString();
            }
            default: assert false;
        }
        return "";
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof NBTTag) {
            final NBTTag CVT = (NBTTag)o;
            if (this.type != CVT.type) {
                return false;
            }
            switch (this.type) {
                case TAG_Byte: return this.asByte == CVT.asByte;
                case TAG_Short: return this.asShort == CVT.asShort;
                case TAG_Int: return this.asInt == CVT.asInt;
                case TAG_Long: return this.asLong == CVT.asLong;
                case TAG_Float: return this.asFloat == CVT.asFloat;
                case TAG_Double: return this.asDouble == CVT.asDouble;
                case TAG_Byte_Array: return Arrays.equals(this.asByteArray,
                                                          CVT.asByteArray);
                case TAG_String: return this.asString.equals(CVT.asString);
                case TAG_List: return this.asList.equals(CVT.asList);
                case TAG_Compound: return this.asCompound
                                          .equals(CVT.asCompound);
                case TAG_Int_Array: return Arrays.equals(this.asIntArray,
                                                         CVT.asIntArray);
                case TAG_Long_Array: return Arrays.equals(this.asLongArray,
                                                          CVT.asLongArray);
                default: assert false;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        switch (this.type) {
            case TAG_Byte: return Byte.hashCode(this.asByte);
            case TAG_Short: return Short.hashCode(this.asShort);
            case TAG_Int: return Integer.hashCode(this.asInt);
            case TAG_Long: return Long.hashCode(this.asLong);
            case TAG_Float: return Float.hashCode(this.asFloat);
            case TAG_Double: return Double.hashCode(this.asDouble);
            case TAG_Byte_Array: return Arrays.hashCode(this.asByteArray);
            case TAG_String: return this.asString.hashCode();
            case TAG_List: return this.asList.hashCode();
            case TAG_Compound: return this.asCompound.hashCode();
            case TAG_Int_Array: return Arrays.hashCode(this.asIntArray);
            case TAG_Long_Array: return Arrays.hashCode(this.asLongArray);
            default: assert false;
        }
        return 0;
    }

    public long longValue() {
        switch (this.type) {
            case TAG_Byte: return this.asByte;
            case TAG_Short: return this.asShort;
            case TAG_Int: return this.asInt;
            case TAG_Long: return this.asLong;
            case TAG_Float: return (long)(this.asFloat);
            case TAG_Double: return (long)(this.asDouble);
            case TAG_Byte_Array: return this.asByteArray.length;
            case TAG_String: return this.asString.length();
            case TAG_List: return this.asList.size();
            case TAG_Compound: return this.asCompound.size();
            case TAG_Int_Array: return this.asIntArray.length;
            case TAG_Long_Array: return this.asLongArray.length;
            default: assert false;
        }
        return 0L;
    }

    public int intValue() {
        final long LONG = this.longValue();
        if (LONG > (long)(Integer.MAX_VALUE)) {
            return Integer.MAX_VALUE;
        }
        if (LONG < (long)(Integer.MIN_VALUE)) {
            return Integer.MIN_VALUE;
        }
        return (int)LONG;
    }

    public short shortValue() {
        final long LONG = this.longValue();
        if (LONG > (long)(Short.MAX_VALUE)) {
            return Short.MAX_VALUE;
        }
        if (LONG < (long)(Short.MIN_VALUE)) {
            return Short.MIN_VALUE;
        }
        return (short)LONG;
    }

    public byte byteValue() {
        final long LONG = this.longValue();
        if (LONG > (long)(Byte.MAX_VALUE)) {
            return Byte.MAX_VALUE;
        }
        if (LONG < (long)(Byte.MIN_VALUE)) {
            return Byte.MIN_VALUE;
        }
        return (byte)LONG;
    }

    public byte getAsByte() throws IllegalArgumentException {
        if (this.type != NBTTagType.TAG_Byte) {
            throw new IllegalArgumentException("Tag is not TAG_Byte");
        }
        return this.asByte;
    }

    public short getAsShort() throws IllegalArgumentException {
        if (this.type != NBTTagType.TAG_Short) {
            throw new IllegalArgumentException("Tag is not TAG_Short");
        }
        return this.asShort;
    }

    public int getAsInt() throws IllegalArgumentException {
        if (this.type != NBTTagType.TAG_Int) {
            throw new IllegalArgumentException("Tag is not TAG_Int");
        }
        return this.asInt;
    }

    public long getAsLong() throws IllegalArgumentException {
        if (this.type != NBTTagType.TAG_Long) {
            throw new IllegalArgumentException("Tag is not TAG_Long");
        }
        return this.asLong;
    }

    public float getAsFloat() throws IllegalArgumentException {
        if (this.type != NBTTagType.TAG_Float) {
            throw new IllegalArgumentException("Tag is not TAG_Float");
        }
        return this.asFloat;
    }

    public double getAsDouble() throws IllegalArgumentException {
        if (this.type != NBTTagType.TAG_Double) {
            throw new IllegalArgumentException("Tag is not TAG_Double");
        }
        return this.asDouble;
    }

    public byte[] getAsByteArray() throws IllegalArgumentException {
        if (this.type != NBTTagType.TAG_Byte_Array) {
            throw new IllegalArgumentException("Tag is not TAG_Byte_Array");
        }
        final byte[] RES = new byte[this.asByteArray.length];
        for (int i = 0; i < RES.length; i++) {
            RES[i] = this.asByteArray[i];
        }
        return RES;
    }

    public UTF8Sequence getAsString() throws IllegalArgumentException {
        if (this.type != NBTTagType.TAG_String) {
            throw new IllegalArgumentException("Tag is not TAG_String");
        }
        return this.asString;
    }

    public NBTList getAsList() throws IllegalArgumentException {
        if (this.type != NBTTagType.TAG_List) {
            throw new IllegalArgumentException("Tag is not TAG_List");
        }
        return new NBTList(this.asList);
    }

    public NBTCompound getAsCompound() throws IllegalArgumentException {
        if (this.type != NBTTagType.TAG_Compound) {
            throw new IllegalArgumentException("Tag is not TAG_Compound");
        }
        return new NBTCompound(this.asCompound);
    }

    public int[] getAsIntArray() throws IllegalArgumentException {
        if (this.type != NBTTagType.TAG_Int_Array) {
            throw new IllegalArgumentException("Tag is not TAG_Int_Array");
        }
        final int[] RES = new int[this.asIntArray.length];
        for (int i = 0; i < RES.length; i++) {
            RES[i] = this.asIntArray[i];
        }
        return RES;
    }

    public long[] getAsLongArray() throws IllegalArgumentException {
        if (this.type != NBTTagType.TAG_Long_Array) {
            throw new IllegalArgumentException("Tag is not TAG_Long_Array");
        }
        final long[] RES = new long[this.asLongArray.length];
        for (int i = 0; i < RES.length; i++) {
            RES[i] = this.asLongArray[i];
        }
        return RES;
    }

    public boolean exists(NBTPath path) {
        if (path.isRoot()) {
            return true;
        }
        if (path.getNode(0).isAsName()) {
            if (this.type != NBTTagType.TAG_Compound ||
                !(this.asCompound.containsName(path.getNode(0).asName))) {
                return false;
            }
            return this.asCompound.get(path.getNode(0).asName)
                   .exists(path.subPath(1));
        }
        final int INDEX = path.getNode(0).asIndex;
        switch (this.type) {
            case TAG_Byte_Array: return ((INDEX < 0) ?
                                         this.asByteArray.length >= -INDEX :
                                         (this.asByteArray.length > INDEX))
                                        && path.subPath(1).isRoot();
            case TAG_Int_Array: return ((INDEX < 0) ?
                                        this.asIntArray.length >= -INDEX :
                                        (this.asIntArray.length > INDEX))
                                       && path.subPath(1).isRoot();
            case TAG_Long_Array: return ((INDEX < 0) ?
                                         this.asLongArray.length >= -INDEX :
                                         (this.asLongArray.length > INDEX))
                                        && path.subPath(1).isRoot();
            case TAG_List: {
                if ((INDEX < 0) ? this.asList.size() < -INDEX :
                                  (this.asList.size() <= INDEX)) {
                    return false;
                }
                return this.asList.get(path.getNode(0).asIndex)
                       .exists(path.subPath(1));
            }
            default: return false;
        }
    }

    public DataOperationResult get(NBTPath path, double scale) {
        if (path.isRoot()) {
            return new DataOperationResult(this, scale);
        }
        if (path.getNode(0).isAsName()) {
            if (this.type != NBTTagType.TAG_Compound ||
                !(this.asCompound.containsName(path.getNode(0).asName))) {
                return new DataOperationResult(null);
            }
            return this.asCompound.get(path.getNode(0).asName)
                   .get(path.subPath(1), scale);
        }
        final int INDEX = path.getNode(0).asIndex;
        switch (this.type) {
            case TAG_Byte_Array: {
                if (INDEX < 0) {
                    if (this.asByteArray.length < -INDEX ||
                        !(path.subPath(1).isRoot())) {
                        return new DataOperationResult(null);
                    }
                    return new DataOperationResult(
                        new NBTTag(this.asByteArray[
                            path.getNode(0).asIndex + this.asByteArray.length
                        ]), scale
                    );
                }
                if (this.asByteArray.length <= INDEX ||
                    !(path.subPath(1).isRoot())) {
                    return new DataOperationResult(null);
                }
                return new DataOperationResult(
                    new NBTTag(this.asByteArray[path.getNode(0).asIndex]),scale
                );
            }
            case TAG_Int_Array: {
                if (INDEX < 0) {
                    if (this.asIntArray.length < -INDEX ||
                        !(path.subPath(1).isRoot())) {
                        return new DataOperationResult(null);
                    }
                    return new DataOperationResult(
                        new NBTTag(this.asIntArray[
                            path.getNode(0).asIndex + this.asIntArray.length
                        ]), scale
                    );
                }
                if (this.asIntArray.length <= INDEX ||
                    !(path.subPath(1).isRoot())) {
                    return new DataOperationResult(null);
                }
                return new DataOperationResult(
                    new NBTTag(this.asIntArray[path.getNode(0).asIndex]), scale
                );
            }
            case TAG_Long_Array: {
                if (INDEX < 0) {
                    if (this.asLongArray.length < -INDEX ||
                        !(path.subPath(1).isRoot())) {
                        return new DataOperationResult(null);
                    }
                    return new DataOperationResult(
                        new NBTTag(this.asLongArray[
                            path.getNode(0).asIndex + this.asLongArray.length
                        ]), scale
                    );
                }
                if (this.asLongArray.length <= INDEX ||
                    !(path.subPath(1).isRoot())) {
                    return new DataOperationResult(null);
                }
                return new DataOperationResult(
                    new NBTTag(this.asLongArray[path.getNode(0).asIndex]),scale
                );
            }
            case TAG_List: {
                if ((INDEX < 0) ? this.asList.size() < -INDEX :
                                  (this.asList.size() <= INDEX)) {
                    return new DataOperationResult(null);
                }
                return this.asList.get(path.getNode(0).asIndex)
                       .get(path.subPath(1), scale);
            }
            default: return new DataOperationResult(null);
        }
    }

    public DataOperationResult merge(NBTPath path, NBTTag tag) {
        //
        return null;
    }

    public DataOperationResult modifySet(NBTPath path, NBTTag tag) {
        if (path.isRoot()) {
            return new DataOperationResult(tag);
        }
        final List<NBTTag> OPERS = new ArrayList<NBTTag>();
        OPERS.add(this);
        NBTPath subpath = path;
        while (!(subpath.isRoot())) {
            OPERS.add(OPERS.get(OPERS.size() - 1)
                      .get(subpath.subPath(0, 1), 1).tag);
            subpath = subpath.subPath(1);
        }
        final int NULLS = OPERS.indexOf(null);
        if (NULLS == -1) {
            if (path.lastNode().isAsIndex() &&
                this.get(path, 1).tag.type != tag.type) {
                return new DataOperationResult(null);
            }
        } else if (NULLS == OPERS.size() - 1) {
            if (OPERS.get(OPERS.size() - 2).type != NBTTagType.TAG_Compound) {
                return new DataOperationResult(null);
            }
        } else {
            return new DataOperationResult(null);
        }
        OPERS.set(OPERS.size() - 1, tag);
        for (int i = OPERS.size() - 2; i >= 0; i--) {
            final NBTTag TAG = OPERS.get(i);
            switch (TAG.type) {
                case TAG_List: {
                    OPERS.set(i, new NBTTag(TAG.getAsList().set(
                        path.getNode(i).asIndex, OPERS.get(i + 1)
                    )));
                    break;
                }
                case TAG_Compound: {
                    final NBTCompound COMPOUND = TAG.getAsCompound();
                    COMPOUND.set(path.getNode(i).asName, OPERS.get(i + 1));
                    OPERS.set(i, new NBTTag(COMPOUND));
                    break;
                }
                case TAG_Byte_Array: {
                    final byte[] BYTEARR = TAG.getAsByteArray();
                    int arri = path.getNode(i).asIndex;
                    if (arri < 0) {
                        arri += BYTEARR.length;
                    }
                    BYTEARR[arri] = OPERS.get(i + 1).getAsByte();
                    break;
                }
                case TAG_Int_Array: {
                    final int[] INTARR = TAG.getAsIntArray();
                    int arri = path.getNode(i).asIndex;
                    if (arri < 0) {
                        arri += INTARR.length;
                    }
                    INTARR[arri] = OPERS.get(i + 1).getAsInt();
                    break;
                }
                case TAG_Long_Array: {
                    final long[] LONGARR = TAG.getAsLongArray();
                    int arri = path.getNode(i).asIndex;
                    if (arri < 0) {
                        arri += LONGARR.length;
                    }
                    LONGARR[arri] = OPERS.get(i + 1).getAsLong();
                    break;
                }
                default: assert false;
            }
        }
        return new DataOperationResult(1, OPERS.get(0));
    }

    public BigInteger byteLength() {
        switch (this.type) {
            case TAG_End: return BigInteger.ZERO;
            case TAG_Byte: return BigInteger.ONE;
            case TAG_Short: return BigInteger.valueOf(2L);
            case TAG_Int: return BigInteger.valueOf(4L);
            case TAG_Long: return BigInteger.valueOf(8L);
            case TAG_Float: return BigInteger.valueOf(4L);
            case TAG_Double: return BigInteger.valueOf(8L);
            case TAG_Byte_Array: return BigInteger
                                 .valueOf(4L +(long)(this.asByteArray.length));
            case TAG_String: return BigInteger.valueOf(2L + this.asString
                                                            .byteLength());
            case TAG_List: {
                BigInteger res = BigInteger.valueOf(5L);
                for (NBTTag i : this.asList) {
                    res = res.add(i.byteLength());
                }
                return res;
            }
            case TAG_Compound: {
                BigInteger res = BigInteger.ONE;
                for (UTF8Sequence i : this.asCompound.names()) {
                    res = res.add(BigInteger.valueOf(3L + i.byteLength()));
                    res = res.add(this.asCompound.get(i).byteLength());
                }
                return res;
            }
            case TAG_Int_Array: return BigInteger
                                .valueOf(4L * (long)(this.asIntArray.length) +
                                         4L);
            case TAG_Long_Array: return BigInteger
                                 .valueOf(8L * (long)(this.asLongArray.length)+
                                          4L);
            default: assert false;
        }
        return null;
    }
}