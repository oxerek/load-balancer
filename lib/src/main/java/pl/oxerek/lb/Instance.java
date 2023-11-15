package pl.oxerek.lb;

import static com.google.common.base.Preconditions.checkNotNull;

class Instance {

    private final String address;

    private final Integer weight;

    Instance(String address) {
        this(address, 1);
    }

    Instance(String address, Integer weight) {
        this.address = checkNotNull(address);
        this.weight = checkNotNull(weight);
    }

    String address() {
        return address;
    }

    Integer weight() {
        return weight;
    }
}
