

public class Judgment {//今当てはめた数値が矛盾していないかを確認する
	  int[][] kumi = {
			  {1,2,0,4,5,3,7,8,6},
			  {2,0,1,5,3,4,8,6,7}
	  };
	public boolean judge(int[][] board,int row, int col) {
		  int match = 0;
			if(board[row][col] == 0){
				return true;
			}
			
		  for(int i=0;i<9;i++) {
			  if(board[row][col] == board[row][i]) {
				  match++;
			  }
		  }
		  if(match>=2) {
			  return false;
		  }else {
			  match = 0;
			  for(int i=0;i<9;i++) {
				  if(board[row][col] == board[i][col]) {
					  match++;
				  }
			  }
			  if(match>=2) {
				  return false;
			  }else {
				  if(board[row][col] == board[kumi[0][row]][kumi[0][col]]||board[row][col] == board[kumi[1][row]][kumi[1][col]]||board[row][col] == board[kumi[0][row]][kumi[1][col]]||board[row][col] == board[kumi[1][row]][kumi[0][col]]) {
					  return false;
				  }else {
					  return true;
				  }
			  }
		  }
	}
}
