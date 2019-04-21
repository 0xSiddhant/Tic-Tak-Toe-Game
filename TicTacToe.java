import java.util.Scanner;

public class TicTacToe {
	
	int board[][] = new int[3][3];
	int currPlayer;
	boolean draw,win;
	Scanner sc ;
	TicTacToe(){
		int k =2;
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board.length;j++) {
				board[i][j] = k++;
			}
		}
		currPlayer = toss();
		sc = new Scanner(System.in);
		gameLoop();
	}
	

	
	private int toss() {
		int ans =(int)(Math.random()*10)%2;
		System.out.println("Player "+(ans+1)+" win the toss");
		return ans;
	}



	private void gameLoop() {
		while(!win & !draw) {
			System.out.println("Player "+(this.currPlayer+1)+" Move");
			showBoard();
			String move = sc.nextLine();
			String Amove[] = move.split(",");
			int x = Integer.parseInt(Amove[0]);
			int y = Integer.parseInt(Amove[1]);
			if(notOccupy(x,y)) {
				board[x][y] = this.currPlayer;
			}
			else {
				System.out.println("Place is occupied.\n Try Again");
				continue;
			}
			if(checkWin()) {
				break;
			}
			if(this.currPlayer == 0) {
				this.currPlayer = 1;
				
			}else
				this.currPlayer = 0;
			
		}
		showBoard();
		System.out.println("game over");
		if(win) {
			System.out.println(" Player "+(this.currPlayer+1)+" won the game.");
		}
		if(draw) {
			System.out.println("Game Drow");
		}
	}



	private boolean checkWin() {
		boolean won = gameWon();
		if(won) {
			this.win = true;
			System.out.println();
			return true;
		}
		boolean drow = true;
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board.length;j++) {
				if(board[i][j] >1) {
					drow = false;
					break;
				}
					
			}
		}
		if(drow) {
			this.draw = true;
			return true;
		}
		return false;
	}


	private boolean gameWon() {
		// Row Check
		for(int i=0;i<board.length;i++) {
			if(board[i][0] == board[i][1] && board[i][1] == board[i][2])
				return true;
		}
		
		//Column Check
		for(int i=0;i<board.length;i++) {
			if(board[0][i] == board[1][i] && board[1][i] == board[2][i])
				return true;
		}
		//Diagonal Check
		if(board[0][0] == board[1][1] && board[1][1] == board[2][2])
			return true;
		if(board[2][0] == board[1][1] && board[1][1] == board[0][2])
			return true;
		
		return false;
	}



	private boolean notOccupy(int x, int y) {
		if(board[x][y] == 0 || board[x][y] == 1)
		return false;
		else
			return true;
	}



	private void showBoard() {
		for(int i=0;i<this.board.length;i++) {
			System.out.print("| ");
			for(int j=0;j<this.board.length;j++) {
				if(board[i][j] == 0) {
					System.out.print(" O ");
				}
				else if(board[i][j] == 1) {
						System.out.print(" X ");
					}
					else
						System.out.print("___");
				System.out.print(" | ");
			}
			System.out.println();
			System.out.println("------------------");
		}
		
	}

	
	
	public static void main(String[] args) {

		new TicTacToe();
	}

}
