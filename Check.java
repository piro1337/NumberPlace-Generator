

public class Check  extends Thread{
	private Callback callback;
	int fillcount = 30;
	public void run() {
//		dc.timer(this);
		Make mk = new Make();
		Solve sv = new Solve();
		int[][] board = new int[9][9];
		int[][] startBoard = new int[9][9];
		while(true) {

			board = mk.randomMake(fillcount);
			for(int i=0;i<9;i++) {
				for(int j=0;j<9;j++) {
					startBoard[i][j] = board[i][j];
				}
			}
			if(sv.anser(board,startBoard,0,0,1)) {//解があればtrueを返す
				int[][] copyboard = new int[9][9];
				for(int i=0;i<9;i++) {
					for(int j=0;j<9;j++) {
						copyboard[i][j] = board[i][j];
					}
				}
				if(sv.anotherAnser(copyboard,startBoard)) {//別解がなければtrueを返す
					for(int i=0;i<9;i++) {
						for(int j=0;j<9;j++) {
							System.out.print(startBoard[i][j]+" ");
						}
						System.out.println("");
					}
					System.out.println("");
					
					for(int i=0;i<9;i++) {
						for(int j=0;j<9;j++) {
							System.out.print(board[i][j]+" ");
						}
						System.out.println("");
					}
					System.out.println("生成に成功しました");
					break;
				}
			}
		}
		callback.finished(startBoard,board);
	}
    void setCallback(Callback callback) {
        this.callback = callback;
    }

    static interface Callback {
        void finished(int[][] finboard, int[][] anserboard);
    }
}
