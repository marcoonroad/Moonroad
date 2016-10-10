package moonroad.app;

import java.util.Observable;

public class Model extends Observable {
    private static Model model   = null;
    private Integer      counter = 0;

    private Model ( ) {
    }

    public static synchronized Model getInstance ( ) {
        if (Model.model == null) {
            Model.model = new Model ( );
        }

        return Model.model;
    }

    public synchronized void increment ( ) {
        this.counter += 1;

        this.setChanged ( );
        this.notifyObservers (counter);
    }

    public synchronized void reset ( ) {
        this.counter = 1;

        this.setChanged ( );
        this.notifyObservers (counter);
    }

    public synchronized void set (Integer integer) {
        this.counter = integer;

        this.setChanged ( );
        this.notifyObservers (counter);
    }
}

// end