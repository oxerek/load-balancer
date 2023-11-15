package pl.oxerek.lb;

import java.util.Map;

interface Strategy {

    int instanceIndex(Map<String, Instance> instances);
}
