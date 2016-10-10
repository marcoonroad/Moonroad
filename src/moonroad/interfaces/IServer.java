package moonroad.interfaces;

import java.net.Socket;

public interface IServer<Client> extends IDaemon {
    void    rebootIn   (Integer milliseconds);
    Boolean isAlive    ( );
    Client  accept     ( );
    Client  acceptIn   (Integer milliseconds);
    Integer getTimeout ( );
    void    setTimeout (Integer milliseconds);
}

// end