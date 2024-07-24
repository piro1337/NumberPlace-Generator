

import java.util.Random;

public class Make {
	public int[][] randomMake(int x) {//ランダムにボードを生成する
		int[][] randomBoard = new int[9][9];
//		int[][] test = {{9,0,2,0,0,0,0,0,0},
//						{0,4,0,5,0,9,0,0,1},
//						{0,0,0,0,2,6,0,0,0},
//						{1,0,0,4,0,8,0,7,0},
//						{0,5,0,0,0,0,0,0,6},
//						{0,0,3,0,0,0,0,9,0},
//						{0,7,0,0,0,0,0,0,5},
//						{4,0,0,0,0,0,8,3,0},
//						{0,0,0,0,5,0,1,0,0}};
		Random random = new Random();
		Judgment jg = new Judgment();
		for(int i=0;i<x;i++) {
			int ri = random.nextInt(9);
			int rj = random.nextInt(9);
			int rv = random.nextInt(9)+1;
			if(randomBoard[ri][rj]==0) {
				randomBoard[ri][rj] = rv;
			}else {
				i--;
				continue;
			}
			if(!jg.judge(randomBoard, ri, rj)) {
				randomBoard[ri][rj] = 0;
				i--;
			}
		}
//		for(int i=0;i<9;i++) {
//			for(int j=0;j<9;j++) {
//				System.out.print(randomBoard[i][j]+" ");
//			}
//			System.out.println("");
//		}
		
		return randomBoard;
	}
}
