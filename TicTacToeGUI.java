
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TicTacToeGUI extends JFrame implements ActionListener {

	public static int BOARD_SIZE = 3;

	public static enum GameStatus {
		Incomplete, XWins, ZWins, Tie
	}

	private JButton[][] buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
	boolean crossTurn = true;

	TicTacToeGUI() {
		super.setTitle("TicTacToe Game");
		super.setSize(600, 600);
		super.setVisible(true);

		GridLayout grid = new GridLayout(BOARD_SIZE, BOARD_SIZE);
		super.setLayout(grid);

		Font font = new Font("Comic Sans", 1, 150);

		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				JButton button = new JButton();
				buttons[row][col] = button;
				button.setFont(font);
				button.addActionListener(this);
				super.add(button);
			}
		}
		super.setResizable(false);
		
		// Adding Frame closing logic.
		super.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clickedButton = (JButton) e.getSource();
		makemove(clickedButton);
		GameStatus gs = this.getGameStatus();
		if(gs == GameStatus.Incomplete) {
			return;
		}
		declareWinner(gs);
		int choice = JOptionPane.showConfirmDialog(this, "Do you want to restart the Game");
		if(choice == JOptionPane.YES_OPTION)
		{
			for (int row = 0; row < BOARD_SIZE; row++) {
				for (int col = 0; col < BOARD_SIZE; col++) {
					buttons[row][col].setText("");
				}
			}
			crossTurn = true;
		}else {
			super.dispose();
		}
			
	}

	private void declareWinner(GameStatus gs) {

		if(gs == GameStatus.XWins)
			JOptionPane.showMessageDialog(this, "X Wins");
		else if(gs == GameStatus.ZWins)
			JOptionPane.showMessageDialog(this, "O Wins");
		else
			JOptionPane.showMessageDialog(this, "Tie");
	}

	private GameStatus getGameStatus() {
		String text1 = "", text2 = "";
		int row = 0, col = 0;
		// text inside Row
		row = 0;
		while (row < BOARD_SIZE) {
			col = 0;
			while (col < BOARD_SIZE - 1) {
				text1 = buttons[row][col].getText();
				text2 = buttons[row][col + 1].getText();
				if (!text1.equals(text2) || text1.length() == 0) {
					break;
				}
				col++;
			}
			if (col == BOARD_SIZE - 1) {
				if (text1.equals("X")) {
					return GameStatus.XWins;
				} else {
					return GameStatus.ZWins;
				}
			}
			row++;
		}

		// text inside col
		col = 0;
		while (col < BOARD_SIZE) {
			row = 0;
			while (row < BOARD_SIZE - 1) {
				text1 = buttons[row][col].getText();
				text2 = buttons[row + 1][col].getText();
				if (!text1.equals(text2) || text1.length() == 0) {
					break;
				}
				row++;
			}
			if (row == BOARD_SIZE - 1) {
				if (text1.equals("X")) {
					return GameStatus.XWins;
				} else {
					return GameStatus.ZWins;
				}
			}
			col++;
		}
		
		// test inside diagonal
		row = 0;
		col = 0;
		while(row<BOARD_SIZE-1) {
			text1 = buttons[row][col].getText();
			text2 = buttons[row+1][col+1].getText();
			if(!text1.equals(text2) || text1.length() ==0){
				break;
			}
			row++;
			col++;
		}
		if(row == BOARD_SIZE-1) {
			if (text1.equals("X")) {
				return GameStatus.XWins;
			} else {
				return GameStatus.ZWins;
			}
		}
		
		// test in 2nd diagonal
		row = BOARD_SIZE-1;
		col = 0;
		while(row>0) {
			text1 = buttons[row][col].getText();
			text2 = buttons[row-1][col+1].getText();
			if(!text1.equals(text2) || text1.length() == 0)
				break;
			row--;
			col++;
		}
		if(row == 0) {
			if (text1.equals("X")) {
				return GameStatus.XWins;
			} else {
				return GameStatus.ZWins;
			}
		}
		
		
		String txt = "";
		for (row = 0; row < BOARD_SIZE; row++) {
			for(col =0;col < BOARD_SIZE;col++) {
				txt = buttons[row][col].getText();
				if(txt.length() == 0)
					return GameStatus.Incomplete;
			}
		}
		return GameStatus.Tie;
	}

	private void makemove(JButton clickedButton) {
		String btnTxt = clickedButton.getText();
		if (btnTxt.length() > 0) {
			JOptionPane.showMessageDialog(this, "Invalid Move");

		} else {
			if (crossTurn) {
				clickedButton.setText("X");
			} else {
				clickedButton.setText("O");
			}
			crossTurn = !crossTurn;
		}
	}
	
	
	public static void main(String[] args) {
		new TicTacToeGUI();
	}
}
