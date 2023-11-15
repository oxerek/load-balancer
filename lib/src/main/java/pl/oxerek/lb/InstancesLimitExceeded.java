package pl.oxerek.lb;

class InstancesLimitExceeded extends RuntimeException {

    InstancesLimitExceeded(String message) {
        super(message);
    }
}
