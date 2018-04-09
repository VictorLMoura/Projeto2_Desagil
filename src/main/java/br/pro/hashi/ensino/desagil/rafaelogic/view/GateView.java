package br.pro.hashi.ensino.desagil.rafaelogic.view;
import br.pro.hashi.ensino.desagil.rafaelogic.model.Source;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import br.pro.hashi.ensino.desagil.rafaelogic.model.*;

public class GateView extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private Gate gate;
	private JCheckBox entry0Button, entry1Button;
	private JCheckBox exitButton;
	private Source entry0, entry1;
	
	public GateView(Gate gate) {
		this.gate = gate;
		entry0 = new Source();
		entry1 = new Source();
		
		// Não podemos esquecer de chamar update na inicialização,
		// caso contrário a interface só ficará consistente depois
		// da primeira interação do usuário com os campos de texto.
		// A definição exata do método update é dada logo abaixo.
	    entry0Button = new JCheckBox("Entrada 0");
	    entry1Button = new JCheckBox("Entrada 1");
		exitButton = new JCheckBox("Sa�da");
		
		// Adiciona todas as componentes a este subpainel.
		add(entry0Button);
		add(entry1Button);
		add(exitButton);
		
		// Todo painel precisa de um layout, que estabelece como os componentes
		// são organizados dentro dele. O layout escolhido na linha abaixo é o
		// mais simples possível: simplesmente enfileira todos eles na HORIZONTAL.
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Estabelece que este subpainel reage ao usuário digitar nos dois
		// primeiros campos. Isso só pode ser feito se este subpainel for
		// do tipo ActionListener, por isso ele implementa essa interface.
	    entry0Button.setSelected(false);
	    entry0Button.addActionListener(this);
	    entry1Button.setSelected(false);
	    entry1Button.addActionListener(this);

	    //Estabelece o campo da sa�da como desativado para os checks
	    exitButton.setEnabled(false); 
	
		updatePicture(this.gate);
	}

	

	private void updatePicture(Gate gate) {
		this.gate = gate;
		try {
			
			//Verifica o tamanho da porta e a partir disso estabelece as conex�es
			//com as Sources.
			if(this.gate.size() == 1) {
				this.gate.connect(0, entry0);
				entry1Button.setEnabled(false);
				
				//Depois de estabelecer as conex�es, l� o resultado da porta l�gica
				//que est� selecionada na interface e determina se o checkbox da saida
				// vai estar selecionado ou n�o.
				if (this.gate.read() == true) {
					exitButton.setSelected(true);
				}
				else {
					exitButton.setSelected(false);
				}
			}
			
			else if(this.gate.size() == 2) {
				this.gate.connect(0, entry0);
				this.gate.connect(1, entry1);
				if (this.gate.read() == true) {
					exitButton.setSelected(true);	

				}
				else {
					exitButton.setSelected(false);

				}
			}
			
		}
		catch(NullPointerException exception) {
			return;
		}

	}
	
	// Toda vez que uma a��o for feita na interface, � preciso verificar
	// quais as entradas que est�o selecionadas para que as respectivas sources sejam
	// ativadas.
	@Override
	public void actionPerformed(ActionEvent e) {
		JCheckBox entrada = (JCheckBox) e.getSource();
	    if (entrada.isSelected() == true && entrada == entry0Button ) {
	    	entry0.turn(true);
		    } 
	    else if (entrada.isSelected() == true && entrada == entry1Button){
		    entry1.turn(true);
		    } 
	    else if (entrada.isSelected() == false && entrada == entry0Button){
	    	entry0.turn(false);
	    } 
	    else if (entrada.isSelected() == false && entrada == entry1Button){
	    	entry1.turn(false);
	    } 
		updatePicture(gate);		
	}
}
