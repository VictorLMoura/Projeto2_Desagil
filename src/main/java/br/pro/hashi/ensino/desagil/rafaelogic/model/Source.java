package br.pro.hashi.ensino.desagil.rafaelogic.model;

public class Source implements Emitter {
    private boolean on;

    public Source() {
        on = false;
    }

    public void turn(boolean status) {
        this.on = status;
    }

    @Override
    public boolean read() {
        return on;
    }
}