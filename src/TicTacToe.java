


import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.GridLayout;

@SuppressWarnings("serial")
public class TicTacToe extends JFrame{
	MiniMaxer MiniMaxer = new MiniMaxer();
	JPanel p = new JPanel();
	XOButton buttons[] =new XOButton[20];

	public static void main(String args[]){

		TicTacToe t = new TicTacToe(2);
		t.hardness();
		t.whoFirst();
	}

	//main frame
	public TicTacToe(int h){
		super("TicTacToe");
		setSize(500,400);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		p.setLayout(new GridLayout(4,5));
		for(int i=0;i<20;i++){
			buttons[i] = new XOButton(this);
			p.add(buttons[i] );
		}
		add(p);
		setVisible(true);
		MiniMaxer.DEPTH=h;
	}

	//clear all button icon and value
	public void flush(){
		for(int i=0;i<20;i++){
			buttons[i].setValue(0);
			buttons[i].setIcon(null);
		}
	}


	//pop a message showing the winner
	public void win(int i){
		String string = null;
		if(i==0) string = "draw";
		else if(i==1)string = "You win";
		else string = "Computer win";
		JOptionPane.showMessageDialog(this, string
				,string, JOptionPane.PLAIN_MESSAGE);
		flush();
		this.hardness();
		this.whoFirst();
	}

	//dialog to decide who play first
	public void whoFirst(){
		Object[] options = {"I go first "," AI go first "};
		int response=JOptionPane.showOptionDialog(this, "please choose who go first", "First Go",JOptionPane.YES_OPTION,     

				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);   

		if(response==0) {  		  
		} 
		else if(response==1)  { 
			int rand = ((int) ((Math.random())*100))%20;
			this.buttons[rand].setI(2);
			this.buttons[rand].setValue(2);
		}    
	}

	//dialog to decide the hardness
	public void hardness(){
		Object[] options = {" easy "," normal "," hard "};
		int response=JOptionPane.showOptionDialog(this, "please choose difficulty", "hardness",JOptionPane.YES_OPTION,     

				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);   

		if(response==0) {  
			this.setTitle("easy");
			MiniMaxer.DEPTH=2; 

		} else if(response==1)  {   
			this.setTitle("normal");
			MiniMaxer.DEPTH=3;    

		} else if(response==2) { 
			this.setTitle("hard");
			MiniMaxer.DEPTH=4; 
		}    
	}

}
