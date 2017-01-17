package xrip;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by i.maslennikov on 17.01.2017.
 */

public class Packet {

    public int id;
    private int lenght;
    private String data;
    private byte[] dataBytes;

    public Packet(String packet) throws Exception {
        final Pattern pattern = Pattern.compile("/(.)([0-9A-F]{3})(\\d)([0-9A-F]+)$/");
        final Matcher matcher = pattern.matcher(packet);

        if (matcher.find()) {
            id = Integer.parseInt(matcher.group(2));
            lenght = Integer.parseInt(matcher.group(3));
            data = matcher.group(4);
            dataBytes = Hex.hexStringToByteArray(data);

        } else
            throw new Exception("Incorrect packet");

    }

    public byte byteAt(int i) throws Exception {
        if (i <= lenght) {
            return dataBytes[i];
        } else {
            throw new Exception("Out of packet bounds");
        }
    }

    public boolean bitAtByte(int Bit, int Byte) {
        return (Bit & 1 << Byte) != 0;
    }

    public String asString(){
        return new String(dataBytes);
    }


}
