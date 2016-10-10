package moonroad.interfaces;

public interface IRequest<Receiver> {
    Object invoke (Receiver receiver) throws Exception;
}

// end