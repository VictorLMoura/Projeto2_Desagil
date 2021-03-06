package br.pro.hashi.ensino.desagil.rafaelogic.model;

public class AndGate extends Gate{
	private Emitter[] emitters;
	private NandGate nandGate;
	private NandGate nandGate2;
	private int size;

	public AndGate() {
		super("AndGate");
		this.size = 2;
		emitters = new Emitter[size];
		nandGate = new NandGate();
		nandGate2 = new NandGate();
	}
	

	@Override
	public void connect(int pinIndex, Emitter emitter) {
		emitters[pinIndex] = emitter;
	}
	
	
	@Override
	public boolean read() {
		nandGate.connect(0, emitters[0]);
		nandGate.connect(1, emitters[1]);
		
		
		
		nandGate2.connect(0, nandGate);
		nandGate2.connect(1, nandGate);
		
		return (nandGate2.read());
	}


	@Override
	public int size() {
		return size;
	}
}
