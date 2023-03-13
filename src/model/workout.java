package model;

import java.time.LocalTime;
import java.util.ArrayList;

public class workout {
    private ArrayList<workout> elements;

    public workout() {
        this.elements = new ArrayList<>();
    }

    public void add(workout element) {
        elements.add(element);
    }

    public void remove(workout element) {
        elements.remove(element);
    }

    public int size() {
        return elements.size();
    }

    public workout get(int index) {
        return elements.get(index);
    }

    public boolean contains(workout element) {
        return elements.contains(element);
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public void clear() {
        elements.clear();
    }

    public LocalTime getStartTime() {
        return null;
    }

    public String getName() {
        return null;
    }

    // other methods as needed...
}