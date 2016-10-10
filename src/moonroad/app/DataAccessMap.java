package moonroad.app;

import moonroad.abstracts.DataAccess;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class DataAccessMap<Key, Value> extends DataAccess<Key, Value> {
    private HashMap<Key, Value> map = new HashMap<Key, Value> ( );

    @Override
    public void create (Key key, Value value) {
        if (this.map.containsKey (key)) {
            throw new IllegalArgumentException (
                "Key [" + key.toString ( ) + "] already exists!"
            );
        }

        this.map.put (key, value);
    }

    public Value read (Key key) {
        return this.map.get (key);
    }

    public void update (Key key, Value value) {
        if (! this.map.containsKey (key)) {
            throw new IllegalArgumentException (
                "Key [" + key.toString ( ) + "] was not found!"
            );
        }

        this.map.put (key, value);
    }

    public void delete (Key key) {
        if (! this.map.containsKey (key)) {
            throw new IllegalArgumentException (
                "Key [" + key.toString ( ) + "] was not found!"
            );
        }

        this.map.remove (key);
    }

    public Iterator<Key> keys ( ) {
        return this.map.keySet ( ).iterator ( );
    }
}

// end