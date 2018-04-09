package br.pro.hashi.ensino.desagil.rafaelogic.model;

public class XorGate extends Gate{
	private Emitter[] emitters;
	private NandGate nandGate;
	private NandGate nandGate1;
	private NandGate nandGate2;
	private NandGate nandGate3;
		
	public XorGate() {
		super("XorGate");
		this.size = 2;
		emitters = new Emitter[size];
		nandGate = new NandGate();
		nandGate1 = new NandGate();
		nandGate2 = new NandGate();
		nandGate3 = new NandGate();
	}
	
	@Override
	public void connect(int pinIndex, Emitter emitter) {
		emitters[pinIndex] = emitter;
	}
	
	@Override
	public boolean read() {
		nandGate.connect(0,  emitters[0]);
		nandGate.connect(1, emitters[1]);
		
		nandGate1.connect(0, emitters[0]);
		nandGate1.connect(1, nandGate);
		
		
		nandGate2.connect(0, nandGate);
		nandGate2.connect(1, emitters[1]);
		
		nandGate3.connect(0, nandGate1);
		nandGate3.connect(1, nandGate2);
		
		return (nandGate3.read());
	}
	@Override
	public int size() {
		return this.size;
	}

}
