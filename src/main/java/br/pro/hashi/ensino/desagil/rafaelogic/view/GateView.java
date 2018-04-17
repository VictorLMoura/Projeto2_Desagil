package br.pro.hashi.ensino.desagil.rafaelogic.view;
import br.pro.hashi.ensino.desagil.rafaelogic.model.Source;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.pro.hashi.ensino.desagil.rafaelogic.view.SimplePanel;
import br.pro.hashi.ensino.desagil.rafaelogic.model.*;

public class GateView extends SimplePanel implements ActionListener, MouseListener{

	private static final long serialVersionUID = 1L;
	
	private Gate gate;
	private JCheckBox entry0Button, entry1Button;
	private JLabel exitLabel;
	private Source entry0, entry1;
	
	// Estes nomes aqui são auto-explicativos, não?
	private Color color;
	private Image image;
	
	public GateView(Gate gate) {		
		// Como subclasse de SimplePanel, esta classe agora
		// exige que uma largura e uma altura sejam fixadas.
		super(550, 260);
		
		this.gate = gate;
		
		entry0 = new Source();
		entry1 = new Source();
		
		// Não podemos esquecer de chamar update na inicialização,
		// caso contrário a interface só ficará consistente depois
		// da primeira interação do usuário com os campos de texto.
		// A definição exata do método update é dada logo abaixo.
	    entry0Button = new JCheckBox("Entrada 0");
	    entry1Button = new JCheckBox("Entrada 1");
		exitLabel = new JLabel("Exit");
	    
		
		// Adiciona todas as componentes a este subpainel.
		add(entry0Button, 30, 70, 100, 20);
		add(entry1Button, 30, 150, 100, 20);
		add(exitLabel, 395, 35, 50, 40);
		
		
		Font labelFont = exitLabel.getFont();
		String labelText = exitLabel.getText();
		int stringWidth = exitLabel.getFontMetrics(labelFont).stringWidth(labelText);
		int componentWidth = exitLabel.getWidth();
		// Find out how much the font can grow in width.
		double widthRatio = (double)componentWidth / (double)stringWidth;
		int newFontSize = (int)(labelFont.getSize() * widthRatio);
		int componentHeight = exitLabel.getHeight();
		// Pick a new font size so it will not be larger than the height of label.
		int fontSizeToUse = Math.min(newFontSize, componentHeight);
		// Set the label's font size to the newly determined size.
		exitLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
		
		
		
		
		// Estabelece que este subpainel reage ao usuário digitar nos dois
		// primeiros campos. Isso só pode ser feito se este subpainel for
		// do tipo ActionListener, por isso ele implementa essa interface.
	    entry0Button.setSelected(false);
	    entry0Button.addActionListener(this);
	    entry1Button.setSelected(false);
	    entry1Button.addActionListener(this);
	
		updatePicture(this.gate);
		
		// Inicializamos esse atributo com o preto.
		color = Color.BLACK;
		
		// Usamos isso no Projeto 1, vocês lembram?
		String path = "/" + gate.toString() + ".png";
		URL url = getClass().getResource(path);
		image = new ImageIcon(url).getImage();
		
		// Toda componente Swing possui este método, que
		// permite adicionar objetos que reagem a eventos
		// de mouse que acontecem nela. Ou seja, ao passar
		// this como parâmetro, estamos pedindo para a
		// componente reagir aos próprios eventos de mouse.
		addMouseListener(this);
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
					color = Color.GREEN;
					repaint();
				}
				else {
					color = Color.BLACK;
					repaint();
				}
			}
			
			else if(this.gate.size() == 2) {
				this.gate.connect(0, entry0);
				this.gate.connect(1, entry1);
				if (this.gate.read() == true) {
					color = Color.GREEN;
					repaint();

				}
				else {
					color = Color.BLACK;
					repaint();

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



	@Override
	public void mouseClicked(MouseEvent event) {
		// Descobre em qual posição o clique ocorreu.
		int x = event.getX();
		int y = event.getY();

		if( Math.pow(x-420, 2)+Math.pow(y-100, 2) <= 400) {

			// ...então abrimos o seletor de cor...
			color = JColorChooser.showDialog(this, null, color);

			// ...e atualizamos a tela.
			repaint();
		}		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void paintComponent(Graphics g) {

		// Não podemos esquecer desta linha, pois não somos os
		// únicos responsáveis por desenhar o painel, como era
		// o caso no Projeto 1. Agora é preciso desenhar também
		// componentes internas, e isso é feito pela superclasse.
		super.paintComponent(g);

		// Desenha a imagem passando posição e tamanho.
		// O último parâmetro pode ser sempre null.
		g.drawImage(image, 150, 30, 175, 175, null);

		// Desenha um retângulo cheio.
		g.setColor(color);
		g.fillOval(400, 80, 40, 40);

		// Evita bugs visuais em alguns sistemas operacionais.
		getToolkit().sync();
    }
}
