package moonroad.app;

import moonroad.classes.Logger;
import moonroad.abstracts.Server;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.io.IOException;

public class LocalServer extends Server<Socket> {
    private final Logger       logger;
    private final ServerSocket server;
    private final Model        model;
    private final Controller   controller;
    private final View         view;
    private       boolean      loaded = false;

    public LocalServer ( ) {
        this (8080);
    }

    public LocalServer (Integer port) {
        this (port, new Logger (System.out));
    }

    public LocalServer (Integer port, Logger logger) {
        this.logger     = logger;
        this.model      = Model.getInstance ( );
        this.view       = new View ( );
        this.controller = new Controller (model, view);

        try {
            this.server = new ServerSocket (port);
        } catch (IOException exception) {
            throw new RuntimeException (exception);
        }
    }

    @Override
    public void boot ( ) {
        super.boot ( );

        if (loaded) return;

        try {
            loaded = true;

            while (this.hasNext ( )) {
                final Socket socket = this.next ( );

                new Service (controller, socket);

                socket.close ( );
            }
        } catch (IOException exception) {
            throw new RuntimeException (exception);
        } catch (Exception exception) {
            throw new RuntimeException (exception);
        }
    }

    public Integer getTimeout ( ) {
        try {
            return (Integer) this.server.getSoTimeout ( );
        } catch (IOException exception) {
            throw new RuntimeException (exception);
        }
    }

    public void setTimeout (Integer timeout) {
        try {
            this.server.setSoTimeout (timeout);
        } catch (SocketException exception) {
            throw new RuntimeException (exception);
        }
    }

    public Socket accept ( ) {
        try {
            return this.server.accept ( );
        } catch (IOException exception) {
            throw new RuntimeException (exception);
        }
    }
}

// end