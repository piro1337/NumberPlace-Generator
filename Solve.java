

public class Solve {
	public boolean anser(int[][] board,int[][] copyboard,int row,int col,int count) {//与えられた問題を解いて答えがあるか確認する
		Judgment jg = new Judgment();
		  while(true) {
//			  System.out.println("a");
//			  System.out.println("a");
			if(copyboard[row][col] == 0) {
				board[row][col] = count;
				if(!jg.judge(board,row,col)) {
					count++;
				}else {
					col++;
					count = 1;
				}
			}else {
				col++;
			}
			if(count==10) {
				board[row][col] = 0;
				while(true) {
				if(row == -1){
					System.out.println("解がありません");
					System.out.println("生成失敗");
					return false;
				}
					if(col==0) {
						row--;
						col=9;
					}else{
						col--;
						if(copyboard[row][col]==0) {
							if(board[row][col]==9) {
								board[row][col] = 0;
								continue;
							}
							count = board[row][col]+1;
							break;
						}
					}
				}
			}
			if(col==9) {
				row++;
				col=0;
				count=1;
			}
			if(row==9) {
				return true;
			}
		}
	}
	public boolean anotherAnser(int[][] board, int[][] copyboard) {//与えられたボードに別解があるか確認する
		int col = 8;
		int row = 8;
		while(true){
			if(copyboard[row][col]==0){
				break;
			}
			col--;
			if(col==-1){
				col=8;
				row--;
			}
		}
		int count = board[row][col] + 1;
		if(count==10) {
			board[row][col] = 0;
			while(true) {
				if(col==0) {
					row--;
					col=9;
				}else{
					col--;
					if(copyboard[row][col]==0) {
						if(board[row][col]==9) {
							board[row][col] = 0;
							continue;
						}
						count = board[row][col]+1;
						break;
					}
				}
			}
		}

		if(!anser(board,copyboard,row,col,count)) {
			return true;
		}else {
			System.out.println("別解があります");
			System.out.println("生成失敗");
			return false;
		}
	}
}
