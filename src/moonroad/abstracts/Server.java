package moonroad.abstracts;

import java.util.Iterator;
import moonroad.interfaces.IServer;

public abstract class Server<Client> implements IServer<Client>, Iterator<Client> {
    private Boolean alive = false;

    @Override
    public synchronized Boolean isAlive ( ) {
        return this.alive;
    }

    @Override
    public synchronized void boot ( ) {
        if (this.isAlive ( )) {
            throw new IllegalStateException ("Already alive!");
        }

        this.alive = true;
    }

    @Override
    public synchronized void shutdown ( ) {
        if (! this.isAlive ( )) {
            throw new IllegalStateException ("Already dead!");
        }

        this.alive = false;
    }

    @Override
    public final void rebootIn (Integer milliseconds) {
        this.shutdown ( );

        if (milliseconds > 0) {
            try {
                Thread.sleep (milliseconds);
            } catch (InterruptedException exception) {
                throw new RuntimeException (exception);
            }
        }

        this.boot ( );
    }

    @Override
    public final void reboot ( ) {
        this.rebootIn (0);
    }

    public boolean hasNext ( ) {
        return this.isAlive ( );
    }

    public Client next ( ) {
        return this.accept ( );
    }

    public void remove ( ) {
        throw new UnsupportedOperationException ("Iterator.remove");
    }


    public Client acceptIn (Integer milliseconds) {
        final Integer previous = this.getTimeout ( );

        this.setTimeout (milliseconds);
        final Client client = this.accept ( );
        this.setTimeout (previous);

        return client;
    }
}

// end