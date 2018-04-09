package br.pro.hashi.ensino.desagil.rafaelogic.model;

public abstract class Gate implements Emitter, Receiver {
	protected int size;
	private String name;
	
	protected Gate(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}

	public abstract int size();
}

