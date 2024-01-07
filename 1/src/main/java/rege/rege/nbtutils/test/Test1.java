package rege.rege.nbtutils.test;

import java.util.logging.Logger;

import rege.rege.nbtutils.NBTTag;

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
    }
}
