package rege.rege.nbtutils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rege.rege.utf8chr.UTF8Char;
import rege.rege.utf8chr.UTF8Sequence;

public class NBTTag {
    public final NBTTagType type;
    private final byte asByte;
    private final short asShort;
    private final int asInt;
    private final long asLong;
    private final float asFloat;
    private final double asDouble;
    private final byte[] asByteArray;
    private final UTF8Sequence asString;
    private final List<NBTTag> asList;
    private final NBTCompound asCompound;
    private final int[] asIntArray;
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
        (type == NBTTagType.TAG_List) ? new ArrayList<NBTTag>() : null;
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
        for (UTF8Char i : value) {
            final long ORD = i.ord();
            if (ORD == -1L || ORD > 0x7fffffffL) {
                throw new IllegalArgumentException("string is not valid");
            }
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

    public NBTTag(List<NBTTag> value)
    throws NullPointerException, IllegalArgumentException {
        if (value == null) {
            throw new NullPointerException("List is null");
        }
        int index = 0;
        final NBTTagType CHECKTYPE = (value.isEmpty()) ? NBTTagType.TAG_End :
                                     (value.get(0).type);
        for (NBTTag i : value) {
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
        this.type = NBTTagType.TAG_List;
        this.asByte = 0;
        this.asShort = 0;
        this.asInt = 0;
        this.asLong = 0L;
        this.asFloat = 0f;
        this.asDouble = 0.;
        this.asByteArray = null;
        this.asString = null;
        this.asList = new ArrayList<NBTTag>(value);
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
                if (this.asList.isEmpty()) {
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
            if (this.type != o.type) {
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
}