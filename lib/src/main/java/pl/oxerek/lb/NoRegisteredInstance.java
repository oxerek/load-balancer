package pl.oxerek.lb;

class NoRegisteredInstance extends RuntimeException {

    NoRegisteredInstance(String message) {
        super(message);
    }
}
