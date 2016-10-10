package moonroad.app;

import moonroad.classes.Response;
import java.net.Socket;
import java.io.IOException;

public class Controller {
    private final Model model;
    private final View  view;

    public Controller (Model model, View view) {
        this.model = model;
        this.view  = view;

        this.model.addObserver (this.view);
    }

    public void increment (Socket socket) throws IOException {
        this.model.increment ( );
        this.view.print (new Response (socket));
    }

    public void reset (Socket socket) throws IOException {
        this.model.reset ( );
        this.view.print (new Response (socket));
    }

    public void set (Socket socket, Integer integer) throws IOException {
        this.model.set (integer);
        this.view.print (new Response (socket));
    }
}

// end