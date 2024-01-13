package rege.rege.nbtutils;

import java.io.EOFException;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import rege.rege.utf8chr.UTF8Char;
import rege.rege.utf8chr.UTF8Sequence;

/**
 * @author REGE
 * @since 0.0.1a1
 */
public class NBTTagIO {
    public static final String EOF_REACH_MSG =
    "Stream reached EOF before the payload's end";

    private NBTTagIO() {
        throw new
        UnsupportedOperationException("Can't instanstiate a utility class");
    }

    private static class ReadResult implements Map.Entry<BigInteger, NBTTag> {
        private final BigInteger key;
        private final NBTTag value;

        ReadResult(BigInteger key, NBTTag value) {
            this.key = key;
            this.value = value;
        }

        //@Override
        public BigInteger getKey() {
            return this.key;
        }

        //@Override
        public NBTTag getValue() {
            return this.value;
        }

        //@Override
        public NBTTag setValue(NBTTag a) {
            throw new UnsupportedOperationException();
        }
    }

    public static BigInteger writeToStream(NBTTag tag, OutputStream stream)
    throws IOException {
        switch (tag.type) {
            case TAG_End: {
                return BigInteger.ZERO;
            }
            case TAG_Byte: {
                stream.write(tag.getAsByte());
                return BigInteger.ONE;
            }
            case TAG_Short: {
                final short SHORT = tag.getAsShort();
                stream.write(SHORT >>> 8);
                stream.write(SHORT);
                return BigInteger.valueOf(2L);
            }
            case TAG_Int: {
                final int INT = tag.getAsInt();
                stream.write(INT >>> 24);
                stream.write(INT >>> 16);
                stream.write(INT >>> 8);
                stream.write(INT);
                return BigInteger.valueOf(4L);
            }
            case TAG_Long: {
                final long LONG = tag.getAsLong();
                stream.write((int)(LONG >>> 56));
                stream.write((int)(LONG >>> 48));
                stream.write((int)(LONG >>> 40));
                stream.write((int)(LONG >>> 32));
                stream.write((int)(LONG >>> 24));
                stream.write((int)(LONG >>> 16));
                stream.write((int)(LONG >>> 8));
                stream.write((int)LONG);
                return BigInteger.valueOf(8L);
            }
            case TAG_Float: {
                final int FLOAT = Float.floatToRawIntBits(tag.getAsFloat());
                stream.write(FLOAT >>> 24);
                stream.write(FLOAT >>> 16);
                stream.write(FLOAT >>> 8);
                stream.write(FLOAT);
                return BigInteger.valueOf(4L);
            }
            case TAG_Double: {
                final long DOUBLE = Double.doubleToRawLongBits(0);
                stream.write((int)(DOUBLE >>> 56));
                stream.write((int)(DOUBLE >>> 48));
                stream.write((int)(DOUBLE >>> 40));
                stream.write((int)(DOUBLE >>> 32));
                stream.write((int)(DOUBLE >>> 24));
                stream.write((int)(DOUBLE >>> 16));
                stream.write((int)(DOUBLE >>> 8));
                stream.write((int)DOUBLE);
                return BigInteger.valueOf(8L);
            }
            case TAG_Byte_Array: {
                final byte[] BYTEARR = tag.getAsByteArray();
                stream.write(BYTEARR.length >>> 24);
                stream.write(BYTEARR.length >>> 16);
                stream.write(BYTEARR.length >>> 8);
                stream.write(BYTEARR.length);
                stream.write(BYTEARR);
                return BigInteger.valueOf(4L + BYTEARR.length);
            }
            case TAG_String: {
                final UTF8Sequence STRING = tag.getAsString();
                stream.write(STRING.length() >>> 8);
                stream.write(STRING.length());
                stream.write(STRING.getBytes());
                return BigInteger.valueOf(2L + STRING.byteLength());
            }
            case TAG_List: {
                BigInteger res = BigInteger.valueOf(4L);
                final NBTList LIST = tag.getAsList();
                stream.write(((LIST.size() != 0) ? LIST.get(0).type :
                              NBTTagType.TAG_End).id);
                stream.write(LIST.size() >>> 24);
                stream.write(LIST.size() >>> 16);
                stream.write(LIST.size() >>> 8);
                stream.write(LIST.size());
                for (NBTTag i : LIST) {
                    res = res.add(writeToStream(i, stream));
                }
                return res;
            }
            case TAG_Compound: {
                BigInteger res = BigInteger.ONE;
                final NBTCompound COMPOUND = tag.getAsCompound();
                for (UTF8Sequence name : COMPOUND.names()) {
                    final NBTTag TAG = COMPOUND.get(name);
                    stream.write(TAG.type.id);
                    stream.write(name.length() >>> 8);
                    stream.write(name.length());
                    stream.write(name.getBytes());
                    res = res.add(BigInteger.valueOf(3L + name.byteLength())
                                  .add(writeToStream(TAG, stream)));
                }
                stream.write(NBTTagType.TAG_End.id);
                return res;
            }
            case TAG_Int_Array: {
                final int[] INTARR = tag.getAsIntArray();
                stream.write(INTARR.length >>> 24);
                stream.write(INTARR.length >>> 16);
                stream.write(INTARR.length >>> 8);
                stream.write(INTARR.length);
                for (int i = 0; i < INTARR.length; i++) {
                    final int INT = INTARR[i];
                    stream.write(INT >>> 24);
                    stream.write(INT >>> 16);
                    stream.write(INT >>> 8);
                    stream.write(INT);
                }
                return BigInteger.valueOf(4L * INTARR.length + 4L);
            }
            case TAG_Long_Array: {
                final long[] LONGARR = tag.getAsLongArray();
                stream.write(LONGARR.length >>> 24);
                stream.write(LONGARR.length >>> 16);
                stream.write(LONGARR.length >>> 8);
                stream.write(LONGARR.length);
                for (int i = 0; i < LONGARR.length; i++) {
                    final long LONG = LONGARR[i];
                    stream.write((int)(LONG >>> 56));
                    stream.write((int)(LONG >>> 48));
                    stream.write((int)(LONG >>> 40));
                    stream.write((int)(LONG >>> 32));
                    stream.write((int)(LONG >>> 24));
                    stream.write((int)(LONG >>> 16));
                    stream.write((int)(LONG >>> 8));
                    stream.write((int)LONG);
                }
                return BigInteger.valueOf(8L * LONGARR.length + 4L);
            }
            default: assert false;
        }
        return null;
    }

    public static Map.Entry<BigInteger, NBTTag>
    readFromStream(NBTTagType type, InputStream stream)
    throws IOException, IllegalArgumentException, NullPointerException {
        if (type == null) {
            throw new NullPointerException("NBTTagType is null");
        }
        switch (type) {
            case TAG_End: return new ReadResult(
                BigInteger.ZERO, new NBTTag(NBTTagType.TAG_End)
            );
            case TAG_Byte: {
                final int INPUT = stream.read();
                if (INPUT == -1) {
                    throw new EOFException(EOF_REACH_MSG);
                }
                return new ReadResult(BigInteger.ONE,new NBTTag((byte)INPUT));
            }
            case TAG_Short: {
                short r;
                int input = stream.read();
                if (input == -1) {
                    throw new EOFException(EOF_REACH_MSG);
                }
                r = (short)input;
                r <<= 8;
                input = stream.read();
                if (input == -1) {
                    throw new EOFException(EOF_REACH_MSG);
                }
                r |= (short)input;
                return new ReadResult(BigInteger.valueOf(2L), new NBTTag(r));
            }
            case TAG_Int: {
                int r;
                int input = stream.read();
                if (input == -1) {
                    throw new EOFException(EOF_REACH_MSG);
                }
                r = input;
                r <<= 8;
                input = stream.read();
                if (input == -1) {
                    throw new EOFException(EOF_REACH_MSG);
                }
                r |= input;
                r <<= 8;
                input = stream.read();
                if (input == -1) {
                    throw new EOFException(EOF_REACH_MSG);
                }
                r |= input;
                r <<= 8;
                input = stream.read();
                if (input == -1) {
                    throw new EOFException(EOF_REACH_MSG);
                }
                r |= input;
                return new ReadResult(BigInteger.valueOf(4L), new NBTTag(r));
            }
            case TAG_Long: {
                long r;
                int input = stream.read();
                if (input == -1) {
                    throw new EOFException(EOF_REACH_MSG);
                }
                r = input;
                for (byte i = 1; i < (byte)8; i++) {
                    r <<= 8;
                    input = stream.read();
                    if (input == -1) {
                        throw new EOFException(EOF_REACH_MSG);
                    }
                    r |= input;
                }
                return new ReadResult(BigInteger.valueOf(8L), new NBTTag(r));
            }
            case TAG_Float: {
                int r;
                int input = stream.read();
                if (input == -1) {
                    throw new EOFException(EOF_REACH_MSG);
                }
                r = input;
                r <<= 8;
                input = stream.read();
                if (input == -1) {
                    throw new EOFException(EOF_REACH_MSG);
                }
                r |= input;
                r <<= 8;
                input = stream.read();
                if (input == -1) {
                    throw new EOFException(EOF_REACH_MSG);
                }
                r |= input;
                r <<= 8;
                input = stream.read();
                if (input == -1) {
                    throw new EOFException(EOF_REACH_MSG);
                }
                r |= input;
                return new ReadResult(BigInteger.valueOf(4L),
                                       new NBTTag(Float.intBitsToFloat(r)));
            }
            case TAG_Double: {
                long r;
                int input = stream.read();
                if (input == -1) {
                    throw new EOFException(EOF_REACH_MSG);
                }
                r = input;
                for (byte i = 1; i < (byte)8; i++) {
                    r <<= 8;
                    input = stream.read();
                    if (input == -1) {
                        throw new EOFException(EOF_REACH_MSG);
                    }
                    r |= input;
                }
                return new ReadResult(BigInteger.valueOf(8L),
                                       new NBTTag(Double.longBitsToDouble(r)));
            }
            case TAG_Byte_Array: {
                final int LENGTH = readFromStream(NBTTagType.TAG_Int, stream)
                                   .getValue().getAsInt();
                final byte[] R = new byte[LENGTH];
                for (int i = 0; i < LENGTH; i++) {
                    final int INPUT = stream.read();
                    if (INPUT == -1) {
                        throw new EOFException(EOF_REACH_MSG);
                    }
                    R[i] = (byte)INPUT;
                }
                return new ReadResult(BigInteger.valueOf(4L + LENGTH),
                                      new NBTTag(R));
            }
            case TAG_String: {
                int length = readFromStream(NBTTagType.TAG_Short, stream)
                             .getValue().getAsShort();
                if (length < 0) {
                    length += 65536;
                }
                final UTF8Char[] R = new UTF8Char[length];
                for (int i = 0; i < length; i++) {
                    final int HEAD = stream.read();
                    if (HEAD == -1) {
                        throw new EOFException(EOF_REACH_MSG);
                    }
                    final byte[] P;
                    if (HEAD < 192 || HEAD == 255) {
                        P = new byte[]{(byte)HEAD};
                    } else {
                        P = new byte[(HEAD < 224) ? 2 : (
                            (HEAD < 240) ? 3 : (
                                (HEAD < 248) ? 4 : (
                                    (HEAD < 252) ? 5 : ((HEAD < 254) ? 6 : 7)
                                )
                            )
                        )];
                        P[0] = (byte)HEAD;
                        for (int j = 1; j < P.length; j++) {
                            final int BODY = stream.read();
                            if (BODY == -1) {
                                throw new EOFException(EOF_REACH_MSG);
                            }
                            P[i] = (byte)BODY;
                        }
                    }
                    R[i] = UTF8Sequence.decodeFrom(P).charAt(0);
                }
                final UTF8Sequence STRING = new UTF8Sequence(R);
                return new ReadResult(
                    BigInteger.valueOf(2L + STRING.byteLength()),
                    new NBTTag(STRING)
                );
            }
            case TAG_List: {
                final int TYPEID = stream.read();
                if (TYPEID == -1) {
                    throw new EOFException(EOF_REACH_MSG);
                }
                final NBTTagType TYPE = NBTTagType.getByID(TYPEID);
                if (TYPE == null) {
                    throw new IllegalArgumentException(
                        "Unknown NBTTagType ID " + Integer.toString(TYPEID)
                    );
                }
                final int LENGTH = readFromStream(NBTTagType.TAG_Int, stream)
                                   .getValue().getAsInt();
                final NBTList LIST = new NBTList();
                BigInteger res = BigInteger.valueOf(5L);
                for (int i = 0; i < LENGTH; i++) {
                    final Map.Entry<BigInteger, NBTTag> READRESULT =
                    readFromStream(TYPE, stream);
                    res = res.add(READRESULT.getKey());
                    LIST.append(READRESULT.getValue());
                }
                return new ReadResult(res, new NBTTag(LIST));
            }
            case TAG_Compound: {
                final NBTCompound COMPOUND = new NBTCompound();
                BigInteger res = BigInteger.ONE;
                while (true) {
                    final int TYPEID = stream.read();
                    if (TYPEID == -1) {
                        throw new EOFException(EOF_REACH_MSG);
                    }
                    final NBTTagType TYPE = NBTTagType.getByID(TYPEID);
                    if (TYPE == null) {
                        throw new IllegalArgumentException(
                            "Unknown NBTTagType ID " + Integer.toString(TYPEID)
                        );
                    }
                    if (TYPE == NBTTagType.TAG_End) {
                        break;
                    }
                    final Map.Entry<BigInteger, NBTTag> READRESULT =
                    readFromStream(NBTTagType.TAG_String, stream);
                    final Map.Entry<BigInteger, NBTTag> READRESULT2 =
                    readFromStream(TYPE, stream);
                    res =
                    res.add(READRESULT.getKey().add(READRESULT2.getKey()));
                    COMPOUND.set(READRESULT.getValue().getAsString(),
                                 READRESULT2.getValue());
                }
                return new ReadResult(res, new NBTTag(COMPOUND));
            }
            case TAG_Int_Array: {
                final int LENGTH = readFromStream(NBTTagType.TAG_Int, stream)
                                   .getValue().getAsInt();
                final int[] R = new int[LENGTH];
                for (int i = 0; i < LENGTH; i++) {
                    R[i] = readFromStream(NBTTagType.TAG_Int, stream)
                           .getValue().getAsInt();
                }
                return new ReadResult(BigInteger.valueOf(4L * LENGTH + 4L),
                                      new NBTTag(R));
            }
            case TAG_Long_Array: {
                final int LENGTH = readFromStream(NBTTagType.TAG_Int, stream)
                                   .getValue().getAsInt();
                final long[] R = new long[LENGTH];
                for (int i = 0; i < LENGTH; i++) {
                    R[i] = readFromStream(NBTTagType.TAG_Long, stream)
                           .getValue().getAsLong();
                }
                return new ReadResult(BigInteger.valueOf(8L * LENGTH + 4L),
                                      new NBTTag(R));
            }
            default: assert false;
        }
        return null;
    }

    public static BigInteger writeSNBTToStream(NBTTag tag, OutputStream stream)
    throws IOException {
        switch (tag.type) {
            case TAG_Byte: {
                final byte[] WRITE =
                (Byte.toString(tag.getAsByte()) + "b").getBytes("UTF-8");
                stream.write(WRITE);
                return BigInteger.valueOf(WRITE.length);
            }
            case TAG_Short: {
                final byte[] WRITE =
                (Short.toString(tag.getAsShort()) + "s").getBytes("UTF-8");
                stream.write(WRITE);
                return BigInteger.valueOf(WRITE.length);
            }
            case TAG_Int: {
                final byte[] WRITE =
                (Integer.toString(tag.getAsInt())).getBytes("UTF-8");
                stream.write(WRITE);
                return BigInteger.valueOf(WRITE.length);
            }
            case TAG_Long: {
                final byte[] WRITE =
                (Long.toString(tag.getAsLong()) + "L").getBytes("UTF-8");
                stream.write(WRITE);
                return BigInteger.valueOf(WRITE.length);
            }
            case TAG_Float: {
                final byte[] WRITE =
                (Float.toString(tag.getAsFloat()) + "f").getBytes("UTF-8");
                stream.write(WRITE);
                return BigInteger.valueOf(WRITE.length);
            }
            case TAG_Double: {
                final byte[] WRITE =
                (Double.toString(tag.getAsDouble()) + "d").getBytes("UTF-8");
                stream.write(WRITE);
                return BigInteger.valueOf(WRITE.length);
            }
            case TAG_Byte_Array: {
                final byte[] BYTEARR = tag.getAsByteArray();
                stream.write('[');
                stream.write('B');
                stream.write(';');
                if (BYTEARR.length == 0) {
                    stream.write(']');
                    return BigInteger.valueOf(4L);
                }
                BigInteger res = BigInteger.valueOf(3L);
                byte[] write =
                (Byte.toString(BYTEARR[0]) + "B").getBytes("UTF-8");
                stream.write(write);
                res = res.add(BigInteger.valueOf(write.length));
                for (int i = 1; i < BYTEARR.length; i++) {
                    write =
                    ("," + Byte.toString(BYTEARR[i]) + "B").getBytes("UTF-8");
                    stream.write(write);
                    res = res.add(BigInteger.valueOf(write.length));
                }
                stream.write(']');
                return res.add(BigInteger.ONE);
            }
            case TAG_String: {
                final UTF8Char BACKSLASH = new UTF8Char('\\');
                final UTF8Char QUOTE = new UTF8Char('"');
                final byte[] WRITE = tag.getAsString().replace(
                    BACKSLASH,
                    new UTF8Sequence(new UTF8Char[]{BACKSLASH, BACKSLASH})
                ).replace(QUOTE,
                          new UTF8Sequence(new UTF8Char[]{BACKSLASH, QUOTE}))
                .getBytes();
                stream.write('"');
                stream.write(WRITE);
                stream.write('"');
                return BigInteger.valueOf(WRITE.length)
                       .add(BigInteger.valueOf(2L));
            }
            case TAG_List: {
                final NBTList LIST = tag.getAsList();
                stream.write('[');
                if (LIST.size() == 0) {
                    stream.write(']');
                    return BigInteger.valueOf(2L);
                } 
                final Iterator<NBTTag> ITT = LIST.iterator();
                BigInteger res = BigInteger.ONE
                                 .add(writeSNBTToStream(ITT.next(), stream));
                while (ITT.hasNext()) {
                    stream.write(',');
                    res = res.add(writeSNBTToStream(ITT.next(), stream)
                                  .add(BigInteger.ONE));
                }
                stream.write(']');
                return res.add(BigInteger.ONE);
            }
            case TAG_Compound: {
                final NBTCompound COMPOUND = tag.getAsCompound();
                stream.write('{');
                if (COMPOUND.size() == 0) {
                    stream.write('}');
                    return BigInteger.valueOf(2L);
                }
                final Iterator<UTF8Sequence> ITT = COMPOUND.names().iterator();
                UTF8Sequence name = ITT.next();
                byte[] write = null;
                BigInteger res = BigInteger.ONE;
                if (name.strip(new UTF8Sequence(
                    "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_-.+"
                )).isEmpty() && !(name.isEmpty())) {
                    write = name.getBytes();
                    stream.write(write);
                    res = res.add(BigInteger.valueOf(write.length));
                } else {
                    res = res.add(writeSNBTToStream(new NBTTag(name), stream));
                }
                stream.write(':');
                res = res.add(BigInteger.ONE.add(writeSNBTToStream(
                    COMPOUND.get(name), stream
                )));
                while (ITT.hasNext()) {
                    stream.write(',');
                    res = res.add(BigInteger.ONE);
                    name = ITT.next();
                    if (name.strip(new UTF8Sequence(
                        "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_-.+"
                    )).isEmpty() && !(name.isEmpty())) {
                        write = name.getBytes();
                        stream.write(write);
                        res = res.add(BigInteger.valueOf(write.length));
                    } else {
                        res =
                        res.add(writeSNBTToStream(new NBTTag(name), stream));
                    }
                    stream.write(':');
                    res = res.add(BigInteger.ONE.add(writeSNBTToStream(
                        COMPOUND.get(name), stream
                    )));
                }
                stream.write('}');
                return res.add(BigInteger.ONE);
            }
            case TAG_Int_Array: {
                final int[] INTARR = tag.getAsIntArray();
                stream.write('[');
                stream.write('I');
                stream.write(';');
                if (INTARR.length == 0) {
                    stream.write(']');
                    return BigInteger.valueOf(4L);
                }
                BigInteger res = BigInteger.valueOf(3L);
                byte[] write = Integer.toString(INTARR[0]).getBytes("UTF-8");
                stream.write(write);
                res = res.add(BigInteger.valueOf(write.length));
                for (int i = 1; i < INTARR.length; i++) {
                    write =
                    ("," + Integer.toString(INTARR[i])).getBytes("UTF-8");
                    stream.write(write);
                    res = res.add(BigInteger.valueOf(write.length));
                }
                stream.write(']');
                return res.add(BigInteger.ONE);
            }
            case TAG_Long_Array: {
                final long[] LONGARR = tag.getAsLongArray();
                stream.write('[');
                stream.write('L');
                stream.write(';');
                if (LONGARR.length == 0) {
                    stream.write(']');
                    return BigInteger.valueOf(4L);
                }
                BigInteger res = BigInteger.valueOf(3L);
                byte[] write =
                (Long.toString(LONGARR[0]) + "L").getBytes("UTF-8");
                stream.write(write);
                res = res.add(BigInteger.valueOf(write.length));
                for (int i = 1; i < LONGARR.length; i++) {
                    write =
                    ("," + Long.toString(LONGARR[i]) + "L").getBytes("UTF-8");
                    stream.write(write);
                    res = res.add(BigInteger.valueOf(write.length));
                }
                stream.write(']');
                return res.add(BigInteger.ONE);
            }
            default: assert false;
        }
        return null;
    }

    public Map.Entry<BigInteger, NBTTag> readSNBTFromStream(InputStream stream)
    throws IOException, IllegalArgumentException {
        // TODO
        NBTTagType detectType = NBTTagType.TAG_End;
        int readbyte;
        long ifInteger = 0L;
        double ifDecimal = 0.;
        boolean escaped = false;
        byte quoteMode = 0;
        List<Byte> cachedBytes = new ArrayList<Byte>();
        BigInteger res = BigInteger.ZERO;
        while (true) {
            readbyte = stream.read();
            if (readbyte == -1) {
                switch (detectType) {
                    case TAG_End: {
                        return new ReadResult(res, new NBTTag(detectType));
                    }
                    case TAG_Byte: {
                        return new ReadResult(res,new NBTTag((byte)ifInteger));
                    }
                    case TAG_Short: {
                        return new ReadResult(res,
                                              new NBTTag((short)ifInteger));
                    }
                    case TAG_Int: {
                        return new ReadResult(res, new NBTTag((int)ifInteger));
                    }
                    case TAG_Long: {
                        return new ReadResult(res, new NBTTag(ifInteger));
                    }
                    case TAG_Float: {
                        return new ReadResult(res,
                                              new NBTTag((float)ifDecimal));
                    }
                    case TAG_Double: {
                        return new ReadResult(res,new NBTTag(ifDecimal));
                    }
                    case TAG_Byte_Array: {
                        throw new
                        EOFException("TAG_Byte_Array bracket not closed");
                    }
                    case TAG_String: {
                        throw new EOFException("TAG_String quote not closed");
                    }
                    case TAG_List: {
                        throw new EOFException("TAG_List bracket not closed");
                    }
                    case TAG_Compound: {
                        throw new
                        EOFException("TAG_Compound brace not closed");
                    }
                    case TAG_Int_Array: {
                        throw new
                        EOFException("TAG_Int_Array bracket not closed");
                    }
                    case TAG_Long_Array: {
                        throw new
                        EOFException("TAG_Long_Array bracket not closed");
                    }
                    default: assert false;
                }
            }
            res.add(BigInteger.ONE);
            switch (readbyte) {
                case 32: {
                    if (cachedBytes.isEmpty()) {
                        break;
                    }
                    if (quoteMode == (byte)0) {
                        throw new IllegalArgumentException(
                            "Unexpected space when reading " +
                            detectType.toString()
                        );
                    }
                    cachedBytes.add(Byte.valueOf((byte)32));
                    break;
                }
                case 34: {
                    if (cachedBytes.isEmpty()) {
                        detectType = NBTTagType.TAG_String;
                        quoteMode = 2;
                        break;
                    }
                    if (quoteMode == (byte)0) {
                        throw new IllegalArgumentException(
                            "Unexpected double quote when reading " +
                            detectType.toString()
                        );
                    }
                    if (quoteMode == (byte)2) {
                        if (!escaped) {
                            return new ReadResult(res, new NBTTag(
                                UTF8Sequence
                                .decodeFrom(bytelist2arr(cachedBytes))
                            ));
                        }
                        escaped = false;
                        cachedBytes.add(Byte.valueOf((byte)34));
                    }
                    break;
                }
                case 39: {
                    if (cachedBytes.isEmpty()) {
                        detectType = NBTTagType.TAG_String;
                        quoteMode = 1;
                        break;
                    }
                    if (quoteMode == (byte)0) {
                        throw new IllegalArgumentException(
                            "Unexpected single quote when reading " +
                            detectType.toString()
                        );
                    }
                    if (quoteMode == (byte)1) {
                        if (!escaped) {
                            return new ReadResult(res, new NBTTag(
                                UTF8Sequence
                                .decodeFrom(bytelist2arr(cachedBytes))
                            ));
                        }
                        escaped = false;
                        cachedBytes.add(Byte.valueOf((byte)39));
                    }
                    break;
                }
                default:
                    break;
            }
        }
    }

    private byte[] bytelist2arr(List<Byte> list) {
        final byte[] RES = new byte[list.size()];
        int index = 0;
        for (Byte i : list) {
            RES[index] = i.byteValue();
            index++;
        }
        return RES;
    }

    private int[] intlist2arr(List<Integer> list) {
        final int[] RES = new int[list.size()];
        int index = 0;
        for (Integer i : list) {
            RES[index] = i.intValue();
            index++;
        }
        return RES;
    }

    private long[] longlist2arr(List<Long> list) {
        final long[] RES = new long[list.size()];
        int index = 0;
        for (Long i : list) {
            RES[index] = i.longValue();
            index++;
        }
        return RES;
    }
}