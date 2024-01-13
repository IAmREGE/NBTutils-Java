package rege.rege.nbtutils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author REGE
 * @since 0.0.1a1
 */
public enum NBTTagType {
    /**
     * The type to mark an empty list or declare the end of an NBT compound.
     */
    TAG_End(0),
    /**
     * The type to store an 8-bit integer in interval [-128,127].
     */
    TAG_Byte(1),
    /**
     * The type to store a 16-bit integer in interval [-32768,32767].
     */
    TAG_Short(2),
    /**
     * The type to store a 32-bit integer in interval [-2147483648,2147483647].
     */
    TAG_Int(3),
    /**
     * The type to store a 64-bit integer in interval
     * [-9223372036854775808,9223372036854775807].
     */
    TAG_Long(4),
    /**
     * The type to store a 32-bit float in IEEE 754-2008 form.
     */
    TAG_Float(5),
    /**
     * The type to store a 64-bit float in IEEE 754-2008 form.
     */
    TAG_Double(6),
    TAG_Byte_Array(7),
    TAG_String(8),
    TAG_List(9),
    TAG_Compound(10),
    TAG_Int_Array(11),
    TAG_Long_Array(12);

    private static final Map<Integer, NBTTagType> byID;

    /**
     * The ID byte of the type in NBT files.
     */
    public final int id;

    private NBTTagType(int id) {
        this.id = id;
    }

    /**
     * @param id The ID byte.
     * @return The enum of the tag type if found, otherwise null.
     */
    public static NBTTagType getByID(int id) {
        return byID.get(Integer.valueOf(id));
    }

    static {
        byID = new HashMap<Integer, NBTTagType>();
        byID.put(Integer.valueOf(0), TAG_End);
        byID.put(Integer.valueOf(1), TAG_Byte);
        byID.put(Integer.valueOf(2), TAG_Short);
        byID.put(Integer.valueOf(3), TAG_Int);
        byID.put(Integer.valueOf(4), TAG_Long);
        byID.put(Integer.valueOf(5), TAG_Float);
        byID.put(Integer.valueOf(6), TAG_Double);
        byID.put(Integer.valueOf(7), TAG_Byte_Array);
        byID.put(Integer.valueOf(8), TAG_String);
        byID.put(Integer.valueOf(9), TAG_List);
        byID.put(Integer.valueOf(10), TAG_Compound);
        byID.put(Integer.valueOf(11), TAG_Int_Array);
        byID.put(Integer.valueOf(12), TAG_Long_Array);
    }
}