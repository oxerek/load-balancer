package pl.oxerek.lb;
class NotUniqueInstanceException extends RuntimeException {

    private String address;

    NotUniqueInstanceException(String message, String address) {
        super(message);
        this.address = address;
    }

    String address() {
        return address;
    }
}
