package xrip;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.felhr.serialportexample.MainActivity;
import com.felhr.serialportexample.UsbService;

import java.util.HashMap;
import java.util.Map;

import static android.R.id.content;

public class PacketsHandler {
    interface PacketHandler {
        void run(Packet packet);
    }

    class Dummy implements PacketHandler {
        @Override
        public void run(Packet packet) {
            // Write your own handler
        }
    }

    class RDSTitle implements PacketHandler {
        @Override
        public void run(Packet packet) {
            Log.e("citroen_log", "RDS Title: "+packet.asString());
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

    Map<Integer, PacketHandler> handlers = new HashMap<Integer, PacketHandler>();;

    public PacketsHandler() {
        Log.d("citroen_log", "test");
        handlers.put(0xFFF, new Dummy());
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

