package br.pro.hashi.ensino.desagil.rafaelogic.model;

public class NotGate extends Gate{
	private Emitter[] emitters;
	private NandGate nandGate;
	
	public NotGate(){
		emitters = new Emitter[2];
		nandGate = new NandGate();
	}

	@Override
	public void connect(int pinIndex, Emitter emitter) {
		emitters[pinIndex] = emitter;
	}
	
	@Override
	public boolean read() {
		nandGate.connect(0,  emitters[0]);
		nandGate.connect(1, emitters[0]);
		return (nandGate.read());
	}
}
