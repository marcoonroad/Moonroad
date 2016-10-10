package moonroad.classes;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.PrintStream;

public class Logger {
    private final PrintStream stream;

    public Logger (PrintStream stream) {
        this.stream = stream;
    }

    public void log (BufferedReader reader) throws IOException {
        while (true) {
            final String line = reader.readLine ( );

            if (line.isEmpty ( )) {
                break;
            }

            stream.println (line);
        }

        stream.println ("");

        /*
        String[ ] strings = reader.readLine ( ).split (" ");
        stream.println ("HTTP Method: "  + strings[ 0 ]);
        stream.println ("HTTP Route: "   + strings[ 1 ]);
        stream.println ("HTTP Version: " + strings[ 2 ]);

        String agent      = reader.readLine ( ).split (": ")[ 1 ];
        String host       = reader.readLine ( ).split (": ")[ 1 ];
        String accepts    = reader.readLine ( ).split (": ")[ 1 ];
        String connection = reader.readLine ( ).split (": ")[ 1 ];

        stream.println ("HTTP User Agent: " + agent);
        stream.println ("HTTP Host: " + host);
        stream.println ("HTTP - Accepts:" + accepts);
        stream.println ("HTTP - Connection: " + connection);

        stream.println ("");
         */
    }
}

// end