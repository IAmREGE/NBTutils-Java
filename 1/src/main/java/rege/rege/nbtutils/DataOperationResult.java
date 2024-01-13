package rege.rege.nbtutils;

/**
 * @author REGE
 * @since 0.0.1a1
 */
public class DataOperationResult {
    public final boolean success;
    public final int result;
    public final NBTTag tag;

    DataOperationResult(NBTTag tag, double scale) {
        this.success = tag != null;
        if (this.success) {
            switch (tag.type) {
                case TAG_End: {
                    this.result = 0;
                    break;
                }
                case TAG_Byte:
                case TAG_Short:
                case TAG_Int:
                case TAG_Long:
                case TAG_Byte_Array:
                case TAG_String:
                case TAG_List:
                case TAG_Compound:
                case TAG_Int_Array:
                case TAG_Long_Array: {
                    this.result = (int)(tag.longValue() * scale);
                    break;
                }
                case TAG_Float: {
                    this.result = (int)(scale * tag.getAsFloat());
                    break;
                }
                case TAG_Double: {
                    this.result = (int)(scale * tag.getAsDouble());
                    break;
                }
                default: {
                    assert false;
                    this.result = 0;
                }
            }
        } else {
            this.result = 0;
        }
        this.tag = tag;
    }

    DataOperationResult(NBTTag tag) {
        this(tag, 1.);
    }

    DataOperationResult(int result, NBTTag tag) {
        this.success = tag != null;
        this.result = this.success ? result : 0;
        this.tag = tag;
    }

    @Override
    public String toString() {
        return this.success ? Integer.toString(this.result) + " " +
                              this.tag.toString() : "failure";
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DataOperationResult) {
            final DataOperationResult CVT = (DataOperationResult)o;
            if (this.success || CVT.success) {
                return this.success && CVT.success && this.result == CVT.result
                       && this.tag.equals(CVT.tag);
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (this.success) {
            return Long.hashCode((((long)(Integer.hashCode(this.result))) <<32)
                                 | this.tag.hashCode());
        }
        return 0;
    }
}