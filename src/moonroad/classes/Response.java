package moonroad.classes;

import java.io.IOException;
import java.net.Socket;
import org.apache.commons.io.IOUtils;

public class Response {
    final Socket socket;

    public Response (Socket socket) {
        this.socket = socket;
    }

    public void send (String html, String status) {
        try {
            IOUtils.write (
                "HTTP/1.1 " + status + "\r\n\r\n" + html,
                this.socket.getOutputStream ( )
            );
        } catch (IOException exception) {
            throw new RuntimeException (exception);
        }
    }

    public void send (String html) {
        this.send (html, "200 OK");
    }

    public void send (Object object) throws IOException {
        this.send (object.toString ( ));
    }

    public void send (HTML html) throws IOException {
        this.send (html.toString ( ));
    }
}

// end
