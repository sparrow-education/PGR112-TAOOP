package com.rinseo.composition;

public class PC {
    // Since Java can only inherit from one class, we can use composition to model our PC
    // A PC has a case, has a motherboard, has a monitor
    private final Case pcCase;
    private final Monitor monitor;
    private final Motherboard motherboard;

    public PC(Case pcCase, Monitor monitor, Motherboard motherboard) {
        this.pcCase = pcCase;
        this.monitor = monitor;
        this.motherboard = motherboard;
    }

    public Case getPcCase() {
        return pcCase;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public Motherboard getMotherboard() {
        return motherboard;
    }
}
