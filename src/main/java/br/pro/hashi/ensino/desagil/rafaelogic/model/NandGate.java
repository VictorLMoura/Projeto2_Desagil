package br.pro.hashi.ensino.desagil.rafaelogic.model;

public class NandGate extends Gate {
	private Emitter[] emitters;

	public NandGate() {
		super("NandGate");
		this.size = 2;
		emitters = new Emitter[size];
	}

	@Override
	public void connect(int pinIndex, Emitter emitter) {
		emitters[pinIndex] = emitter;
	}

	@Override
	public boolean read() {
		return !(emitters[0].read() && emitters[1].read());
	}
	@Override
	public int size() {
		return this.size;
	}
}
