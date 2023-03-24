package model;

import java.time.LocalTime;
import java.util.ArrayList;

public class workout {
    private ArrayList<workout> elements;
    private LocalTime startTime;

    public workout() {
        this.elements = new ArrayList<>();
    }

    public void add(workout element) {
        elements.add(element);
    }

    public void remove(workout element) {
        elements.remove(element);
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

    public void setName() {
        this.elements = elements;
    }

    public ArrayList<workout> getName() {
        return elements;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

}

