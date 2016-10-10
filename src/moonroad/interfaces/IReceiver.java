package moonroad.interfaces;

import java.util.Map;

public interface IReceiver {
    Object missing (String selector, Map<String, String> arguments);
}

// end