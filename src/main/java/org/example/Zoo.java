package org.example;

import java.util.ArrayList;
import java.util.List;

public record Zoo(List<Animal> animals) {
    public Zoo() {
        this(new ArrayList<>());
    }

    public int totalFoodRequirements() {
        int sum = 0;
        for (Animal animal : animals) {
            sum += animal.species().foodRequirements();
        }
        return sum;
    }
}
