package moonroad.abstracts;

import java.lang.reflect.*;
import java.util.Map;
import moonroad.interfaces.IRequest;

public abstract class Request<Receiver> implements IRequest<Receiver> {
    public abstract String              getSelector  ( );
    public abstract Map<String, String> getArguments ( );

    public final Object invoke (Receiver receiver) throws Exception {
        Class<?> parent = receiver.getClass ( );

        try {
            Class<?>[ ] types  = new Class<?>[ ]{ Map.class };
            Method      method = parent.getMethod (this.getSelector ( ), types);

            return method.invoke (receiver, this.getArguments ( ));
        } catch (NoSuchMethodException exception) {
            Class<?>[ ] types = new Class<?>[ ] { String.class, Map.class };

            Method method = parent.getMethod ("missing", types);

            return method.invoke (
                receiver,
                this.getSelector ( ),
                this.getArguments ( )
            );
        }
    }
}

// end