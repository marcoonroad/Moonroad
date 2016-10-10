package moonroad.classes;

import moonroad.interfaces.IServer;
import moonroad.abstracts.Server;
import java.net.Socket;
import moonroad.app.LocalServer;

// using: Delegation
public class ThreadServer extends Server<Socket> implements Runnable {
    private final IServer<Socket> server;
    public  final Thread          thread;

    public ThreadServer ( ) {
        this ((IServer<Socket>) new LocalServer ( ));
    }

    public ThreadServer (IServer<Socket> server) {
        this.server = server;
        this.thread = new Thread (this);

        this.thread.start ( );
    }

    public void run ( ) {
        this.server.boot ( );
    }

    @Override
    public Boolean isAlive ( ) {
        return this.server.isAlive ( );
    }

    @Override
    public void boot ( ) {
        this.server.boot ( );
    }

    @Override
    public void shutdown ( ) {
        this.server.shutdown ( );
    }

    public Integer getTimeout ( ) {
        return this.server.getTimeout ( );
    }

    public void setTimeout (Integer milliseconds) {
        this.server.setTimeout (milliseconds);
    }

    public Socket accept ( ) {
        return this.server.accept ( );
    }
}

// end