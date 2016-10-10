package moonroad.app;

import moonroad.interfaces.IReceiver;
import java.net.Socket;
import java.util.Map;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import moonroad.classes.RequestDecoder;

public class Service implements IReceiver {
    private final Controller controller;
    private final Socket     socket;

    public Service (Controller controller, Socket socket) throws Exception {
        this.controller = controller;
        this.socket     = socket;

        final BufferedReader reader = new BufferedReader (
            new InputStreamReader (socket.getInputStream ( ))
        );

        new RequestDecoder<Service> (reader).decode ( ).invoke (this);
    }

    public void main (Map<String, String> arguments) throws Exception {
        this.increment (arguments);
    }

    public void increment (Map<String, String> arguments) throws Exception {
        this.controller.increment (socket);
    }

    public void reset (Map<String, String> arguments) throws Exception {
        this.controller.reset (socket);
    }

    public void set (Map<String, String> arguments) throws Exception {
        String value = arguments.get ("value");

        this.controller.set (socket, Integer.parseInt (value));
    }

    public Object missing (String selector, Map<String, String> arguments) {
        // throw new RuntimeException (new NoSuchMethodException (selector));
        try {
            this.set (arguments);
        } catch (Exception exception) {
            throw new RuntimeException (exception);
        }

        return null;
    }
}

// end