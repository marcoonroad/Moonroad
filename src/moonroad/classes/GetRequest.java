package moonroad.classes;

import moonroad.abstracts.Request;
import java.util.Map;
import java.util.HashMap;

public class GetRequest<Receiver> extends Request<Receiver> {
    private final String              selector;
    private final Map<String, String> arguments;

    public GetRequest (String selector) {
        this (selector, new HashMap<String, String> ( ));
    }

    public GetRequest (String selector, Map<String, String> arguments) {
        this.selector  = selector;
        this.arguments = arguments;
    }

    public String getSelector ( ) {
        return selector;
    }

    public Map<String, String> getArguments ( ) {
        return arguments;
    }
}

// end