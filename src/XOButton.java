import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class XOButton extends JButton implements ActionListener{
	//change imageSource here. Note the last '/'
	String imageSource = "C:\\Users\\A\\Documents\\Eclipse\\Tic Tac Toe\\images\\";
	MiniMaxer MiniMaxer = new MiniMaxer();
	//String imageSource="null";
	TicTacToe t ;
	ImageIcon X, O;
	byte value = 0;
	// 0 nothing ;  1X ;   2O
	
	
	public XOButton(TicTacToe x){
		t = x;
//		imageSource = this.getClass().getResource("").toString().substring(5);
		//System.out.println(imageSource);
		O = new ImageIcon(imageSource+"O.png");
		X = new ImageIcon(imageSource+"X.png");
		//X= new ImageIcon("/X.png");

		this.addActionListener(this);
		
	}
	
	
	public void setValue(int i){
		value = (byte)i;
	}
	public void setI(int i){
		if(i==1)setIcon(X);
		else if(i == 2) setIcon(O);
	}

	//after one button been clicked, the button change its value and icon,
	//and then call MiniMaxer to calculate the next move. 
	//Clicks on used button will not work.
	
	public void actionPerformed(ActionEvent e){
		if(value == 0 )value++;
		else return;
		value%=3;
		switch(value){
		case 0:
			setIcon(O);
			break;
		case 1:
			setIcon(O);
			int [] state = new int [20];
			for (int i =0;i<20;i++){
				state[i]=t.buttons[i].value;
				System.out.print(state[i]);
			}
			System.out.println();
			int action=MiniMaxer.abSearch(state);
			System.out.println("XOButton:"+action);

			t.buttons[action].setIcon(X);
			t.buttons[action].setValue(2);
			System.out.println("XOButton:action done");
			state[action]=2;
			if(MiniMaxer.isTerminal(state)){
				//after isTerminal(state),  MiniMaxer.winner has been change to the real winner.
				t.win(MiniMaxer.winner);
				System.out.println("winner:"+MiniMaxer.winner);
			}
			
			break;
		case 2:
			setIcon(O);
			break;
		}
	}
}
