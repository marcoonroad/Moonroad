package moonroad.app;

import moonroad.classes.Response;
import moonroad.classes.HTML;
import java.util.Observer;
import java.util.Observable;
import java.io.IOException;

public class View implements Observer {
    private Integer counter = 0;

    public void print (Response response) throws IOException {
        String text = "Hello, world! (Client number " + this.counter.toString ( ) + ")";

        response.send (new HTML (text, HTML.bold (text)));
    }

    public void update (Observable observable, Object object) {
        this.counter = (Integer) object;
    }
}

// end