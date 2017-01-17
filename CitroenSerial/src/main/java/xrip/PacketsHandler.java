package xrip;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class PacketsHandler {
    interface PacketHandler {
        void run(Packet packet);
    }

    class RDSTitle implements PacketHandler {
        @Override
        public void run(Packet packet) {
            Log.d("citroen_log", "RDS Title: "+packet.asString());
        }
    }

    class InfoMessage implements PacketHandler {
        @Override
        public void run(Packet packet) {
            try {
                Log.d("citroen_log", "Information Message: "+String.format("0x%02X", (int) packet.byteAt(1) ));
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }
    }

    Map<Integer, PacketHandler> handlers;

    public PacketsHandler() {
        handlers.put(0x1A1, new InfoMessage());
        handlers.put(0x2A5, new RDSTitle());
    }

    public void Handle(Packet packet) throws Exception {
        PacketHandler handler = handlers.get(packet.id);
        if (handler != null) {
            handler.run(packet);
        } else {
            throw new Exception("Packet without handler");
        }
    }


}

