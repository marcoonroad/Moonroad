package moonroad.abstracts;

import moonroad.interfaces.IDataAccess;
import java.util.Iterator;

public abstract class DataAccess<Key, Value>
    implements IDataAccess<Key, Value> {
    // switch all data to a new IDataAccess object
    public final void transfer (DataAccess<Key, Value> dataAccess) {
        Iterator<Key> iterator = this.keys ( );

        while (iterator.hasNext ( )) {
            Key   key   = iterator.next ( );
            Value value = this.read (key);

            dataAccess.create (key, value);
            this.delete (key);
        }
    }
}

// end