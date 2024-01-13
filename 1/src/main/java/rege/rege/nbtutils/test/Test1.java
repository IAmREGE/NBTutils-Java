package rege.rege.nbtutils.test;

import java.util.logging.Logger;

import rege.rege.nbtutils.NBTCompound;
import rege.rege.nbtutils.NBTList;
import rege.rege.nbtutils.NBTPath;
import rege.rege.nbtutils.NBTPathNode;
import rege.rege.nbtutils.NBTTag;
import rege.rege.utf8chr.UTF8Sequence;

/**
 * @author REGE
 * @since 0.0.1a1
 */
public class Test1 {
    public static final Logger LOGGER =
    Logger.getLogger("rege.rege.nbtutils.test.Test1");

    /**
     * <p>Run the test code.</p>
     * <h4>Expected output:</h4>
     * <ol>
     * <li>0b</li>
     * <li>1b</li>
     * <li>1b</li>
     * <li>613s</li>
     * <li>-89234</li>
     * <li>3083627922L</li>
     * <li>3.1415927f</li>
     * <li>3.14159265358979d</li>
     * <li>Items[-1].tags.Enchantments[0].lvl</li>
     * <li>112358</li>
     * <li>28b</li>
     * <li>[B;14B,28B,57B]</li>
     * </ol>
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        LOGGER.info(new NBTTag(false).toString());
        LOGGER.info(new NBTTag(true).toString());
        LOGGER.info(new NBTTag((byte)1).toString());
        LOGGER.info(new NBTTag((short)613).toString());
        LOGGER.info(new NBTTag(-89234).toString());
        LOGGER.info(new NBTTag(3083627922L).toString());
        LOGGER.info(new NBTTag(3.14159265358979f).toString());
        LOGGER.info(new NBTTag(3.14159265358979).toString());
        LOGGER.info(new NBTPath(
            new NBTPathNode("Items"),
            new NBTPathNode(-1),
            new NBTPathNode("tags"),
            new NBTPathNode("Enchantments"),
            new NBTPathNode(0),
            new NBTPathNode("lvl")
        ).toString());
        NBTList sampleList = new NBTList();
        sampleList.append(new NBTTag(112358));
        NBTCompound sampleCompound = new NBTCompound();
        sampleCompound.set(new UTF8Sequence("foo"), new NBTTag(sampleList));
        sampleCompound.set(new UTF8Sequence("bar"),
                           new NBTTag(new byte[]{14, 28, 57}));
        NBTTag sampleTag = new NBTTag(sampleCompound);
        LOGGER.info(sampleTag.get(new NBTPath(
            new NBTPathNode("foo"), new NBTPathNode(-1)
        ), 1).tag.toString());
        LOGGER.info(sampleTag.get(new NBTPath(
            new NBTPathNode("bar"), new NBTPathNode(-2)
        ), 1).tag.toString());
        LOGGER.info(sampleTag.get(new NBTPath(
            new NBTPathNode("bar")
        ), 1).tag.toString());
        LOGGER.info(sampleTag.toString());
        LOGGER.info(sampleTag.get(new NBTPath(), 2.5).toString());
    }
}
