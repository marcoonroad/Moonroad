package moonroad.interfaces;

import java.util.Iterator;

public interface IDataAccess<Key, Value> {
    void          create (Key key, Value value);
    Value         read   (Key key);
    void          update (Key key, Value value);
    void          delete (Key key);
    Iterator<Key> keys   ( );
}

// end