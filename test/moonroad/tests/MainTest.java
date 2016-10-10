package moonroad.tests;

import        moonroad.classes.HTML;
import        moonroad.classes.ThreadServer;
import        moonroad.interfaces.IServer;
import        java.io.IOException;
import        java.net.MalformedURLException;
import        java.io.InputStream;
import        java.util.Scanner;
import        java.net.URL;
import static org.junit.Assert.*;
import        org.junit.After;
import        org.junit.Before;
import        org.junit.Test;

public class MainTest {
    private static String url     = "http://localhost:8080";
    private static IServer server = null;

    public MainTest ( ) throws Exception {
        MainTest.initialize ( );
    }

    private static synchronized void initialize ( ) throws Exception {
        if (MainTest.server == null) {
            MainTest.server = new ThreadServer ( );
        }
    }

    @Before
    public void bootServer ( ) throws Exception {
        // race conditions there!
        Thread.sleep (2500);

        if (! MainTest.server.isAlive ( )) {
            MainTest.server.boot ( );
        }
    }

    @After
    public void shutdownServer ( ) throws Exception {
        Thread.sleep (2500);

        if (MainTest.server.isAlive ( )) {
            MainTest.server.shutdown ( );
        }
    }

    @Test
    public void incrementPath ( ) throws MalformedURLException, IOException {
        do {
            InputStream stream = new URL (url).openStream ( );

            Scanner scanner  = new Scanner (stream);
            String  response = "";
            String  text     = "Hello, world! (Client number 1)";

            while (scanner.hasNextLine ( )) {
                response += scanner.nextLine ( ) + "\n";
            }

            assertEquals (
                response,
                new HTML (text, HTML.bold (text)).toString ( ) + "\n"
            );
        } while (false);

        do {
            new URL (url).openStream ( ); // ignore response

            InputStream stream = new URL (url).openStream ( );

            Scanner scanner  = new Scanner (stream);
            String  response = "";
            String  text     = "Hello, world! (Client number 3)";

            while (scanner.hasNextLine ( )) {
                response += scanner.nextLine ( ) + "\n";
            }

            assertEquals (
                response,
                new HTML (text, HTML.bold (text)).toString ( ) + "\n"
            );
        } while (false);
    }

    @Test
    public void resetPath ( ) throws MalformedURLException, IOException {
        // <reset> selector
        do {
            InputStream stream = new URL (url + "/reset").openStream ( );

            Scanner scanner  = new Scanner (stream);
            String  response = "";
            String  text     = "Hello, world! (Client number 1)";

            while (scanner.hasNextLine ( )) {
                response += scanner.nextLine ( ) + "\n";
            }

            assertEquals (
                response,
                new HTML (text, HTML.bold (text)).toString ( ) + "\n"
            );
        } while (false);

        // yes, Java sucks
        do {
            new URL (url).openStream ( ); // ignore response

            InputStream stream = new URL (url + "/reset").openStream ( );

            Scanner scanner  = new Scanner (stream);
            String  response = "";
            String  text     = "Hello, world! (Client number 1)";

            while (scanner.hasNextLine ( )) {
                response += scanner.nextLine ( ) + "\n";
            }

            assertEquals (
                response,
                new HTML (text, HTML.bold (text)).toString ( ) + "\n"
            );
        } while (false);
    }

    @Test
    public void setPath ( ) throws MalformedURLException, IOException {
        // <set> selector
        do {
            InputStream stream = new URL (url + "/set?value=12").openStream ( );

            Scanner scanner  = new Scanner (stream);
            String  response = "";
            String  text     = "Hello, world! (Client number 12)";

            while (scanner.hasNextLine ( )) {
                response += scanner.nextLine ( ) + "\n";
            }

            assertEquals (
                response,
                new HTML (text, HTML.bold (text)).toString ( ) + "\n"
            );
        } while (false);

        do {
            new URL (url).openStream ( ); // ignore response

            InputStream stream = new URL (url).openStream ( );

            Scanner scanner  = new Scanner (stream);
            String  response = "";
            String  text     = "Hello, world! (Client number 14)";

            while (scanner.hasNextLine ( )) {
                response += scanner.nextLine ( ) + "\n";
            }

            assertEquals (
                response,
                new HTML (text, HTML.bold (text)).toString ( ) + "\n"
            );
        } while (false);
    }
}

// end