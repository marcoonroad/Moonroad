package moonroad.classes;

import moonroad.abstracts.Request;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

public class RequestDecoder<Receiver> {
    private final BufferedReader reader;

    public RequestDecoder (BufferedReader reader) {
        this.reader = reader;
    }

    public Request<Receiver> decode ( ) throws Exception {
        String[ ] strings = reader.readLine ( ).split (" ");

        String method     = strings[ 0 ];
        String route      = strings[ 1 ];
        String version    = strings[ 2 ];
        String agent      = reader.readLine ( ).split (": ")[ 1 ];
        String host       = reader.readLine ( ).split (": ")[ 1 ];
        String accepts    = reader.readLine ( ).split (": ")[ 1 ];
        String connection = reader.readLine ( ).split (": ")[ 1 ];

        if (route.equals ("/")) {
            route = "main";
        }

        if (route.contains ("?")) {
            String[ ] message = route.split ("\\?");

            // <main> is the default method
            if (message[ 1 ] == null) {
                message[ 1 ] = message[ 0 ];
                message[ 0 ] = "main";
            }

            String    selector = message[ 0 ].replaceFirst ("/", "").replaceAll ("/", "_");
            String[ ] pairs    = null;

            if (message[ 1 ].matches ("&")) {
                pairs = message[ 1 ].split ("&");
            } else {
                pairs = new String[ ] { message[ 1 ] };
            }


            Map<String, String> arguments = new HashMap<String, String> ( );

            for (String pair : pairs) {
                String[ ] entry = pair.split ("=");

                if (entry[ 1 ] == null) {
                    entry[ 1 ] = "";
                }

                arguments.put (entry[ 0 ], entry[ 1 ]);
            }

            if (method.equals ("GET")) {
                return new GetRequest<Receiver> (selector, arguments);
            }
            else {
                throw new UnsupportedOperationException (method + " HTTP method");
            }
        } else {
            String selector = null;

            selector = route.replaceFirst ("/", "").replaceAll ("/", "_");

            if (method.equals ("GET")) {
                return new GetRequest<Receiver> (selector);
            }
            else {
                throw new UnsupportedOperationException (method + " HTTP method");
            }
        }
    }
}

// end