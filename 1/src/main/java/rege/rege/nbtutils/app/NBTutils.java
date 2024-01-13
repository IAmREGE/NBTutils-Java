package rege.rege.nbtutils.app;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

import rege.rege.nbtutils.NBTTagIO;
import rege.rege.nbtutils.NBTTagType;

/**
 * @author REGE
 * @since 0.0.1a1
 */
public class NBTutils {
    public static class AutoZeroInputStream extends InputStream {
        public final InputStream stream;

        public AutoZeroInputStream(InputStream stream) {
            this.stream = stream;
        }

        @Override
        public int read() throws IOException {
            final int GET = this.stream.read();
            return (GET == -1) ? 0 : GET;
        }
    }

    public static void printHelp() {
        System.out.println("Usage: [JVMargs] read [<filename>]");
    }

    public static void main(String[] args) throws IOException {
        switch (args.length) {
            case 0: {
                printHelp();
                return;
            }
            case 1: {
                if (args[0].equals("read")) {
                    NBTTagIO.writeSNBTToStream(NBTTagIO.readFromStream(
                        NBTTagType.TAG_Compound,
                        new AutoZeroInputStream(System.in)
                    ).getValue(), System.out);
                    System.out.println();
                    System.out.flush();
                } else {
                    printHelp();
                }
                return;
            }
            case 2: {
                if (args[0].equals("read")) {
                    NBTTagIO.writeSNBTToStream(NBTTagIO.readFromStream(
                        NBTTagType.TAG_Compound,
                        new AutoZeroInputStream(new FileInputStream(args[1]))
                    ).getValue(), System.out);
                    System.out.println();
                    System.out.flush();
                } else {
                    printHelp();
                }
                return;
            }
            default: return;
        }
    }
}