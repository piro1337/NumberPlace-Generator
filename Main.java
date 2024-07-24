
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.TimerTask;

import javax.swing.JFrame;

public class Main extends JFrame implements MouseMotionListener,MouseListener,KeyListener{//フレームを作成しマウスイベントを管理する
	DrawCanvas dc = new DrawCanvas();
	int x = 0;
	int y = 0;
	int[][] difboard = new int[9][9];
	boolean shift = false;
	int[][] memocnt = new int[9][9];
	public static void main(String[] args) {
//		DrawCanvas dc = new DrawCanvas();
//		dc.window();
		Main m = new Main();
		m.setVisible(true);
//		Make mk = new Make();
//		mk.randomMake();
	}
	Main(){

//		dc.window();
		setTitle("suudoku");
	    setBounds(100, 100, 750, 830);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocation(650, 10);
	    add(dc);
	    dc.addMouseMotionListener(this);
	    dc.addMouseListener(this);
	    addKeyListener(this);
	}
	public void mouseDragged(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		Point p = e.getPoint();
		
//		System.out.println("x:"+p.x);
//		System.out.println("y:"+p.y);
		if(p.x>=dc.btx&&p.x<=dc.btx+dc.btsizex&&p.y>=dc.bty&&p.y<=dc.bty+dc.btsizey) {
			dc.point1 = true;
			repaint();
		}else {
			dc.point1 = false;
			repaint();
		}
		if(p.x>=dc.stx&&p.x<=dc.stx+dc.stsizex&&p.y>=dc.sty&&p.y<=dc.sty+dc.stsizey) {
			dc.spoint1 = true;
			repaint();
		}else {
			dc.spoint1 = false;
			repaint();
		}
		if(p.x>=440&&p.x<=440+30&&p.y>=455&&p.y<=455+30) {
			dc.fillpoint1 = true;
			repaint();
		}else {
			dc.fillpoint1 = false;
			repaint();
		}

		if(p.x>=440&&p.x<=440+30&&p.y>=495&&p.y<=495+30) {
			dc.fillpoint2 = true;
			repaint();
		}else {
			dc.fillpoint2 = false;
			repaint();
		}
		
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				if(p.x>=10+j*80&&p.x<=10+j*80+80&&p.y>=30+i*80&&p.y<=30+i*80+80) {
					dc.choice[i][j] = true;
					repaint();
				}else {
					dc.choice[i][j] = false;
					repaint();
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		if(dc.fillpoint1&&!dc.rode){
			dc.fillcount++;
		}else if(dc.fillpoint2&&!dc.rode){
			dc.fillcount--;
		}
		if(dc.spoint1&&dc.esc&&dc.generate) {
		       try {
		            // FileWriterクラスのオブジェクトを生成する
		            FileWriter file = new FileWriter("./save.txt");
		            // PrintWriterクラスのオブジェクトを生成する
		            PrintWriter pw = new PrintWriter(new BufferedWriter(file));
		            
		            //ファイルに書き込む
		            for(int i=0; i<9; i++) {
		            	for(int j=0; j<9; j++) {
		            		pw.print(dc.finboard[i][j]);
		            	}
		            }
		            
		            for(int i=0; i<9; i++) {
		            	for(int j=0; j<9; j++) {
		            		pw.print(dc.addboard[i][j]);
		            	}
		            }
		            
		            for(int i=0; i<9; i++) {
		            	for(int j=0; j<9; j++) {
		            		pw.print(difboard[i][j]);
		            	}
		            }
		            for(int i=0; i<9; i++) {
		            	for(int j=0; j<9; j++) {
		            		for(int k=0; k<6; k++) {
		            			pw.print(dc.memo[i][j][k]);
		            		}
		            	}
		            }
		            
		            dc.save = true;
		            //ファイルを閉じる
		            pw.close();
		        } catch (IOException a) {
		            a.printStackTrace();
		        }
			}
		if(dc.spoint1&&!dc.generate&&!dc.rode){
				try{
					  File file = new File("./save.txt");
					  FileReader filereader = new FileReader(file);

					  char date = 0;
					  for(int i=0; i<9; i++) {
						  for(int j=0; j<9; j++) {
							  date = (char)filereader.read();
							  dc.finboard[i][j] = Character.getNumericValue(date);
						  }
					  }
					  for(int i=0; i<9; i++) {
						  for(int j=0; j<9; j++) {
							  date = (char)filereader.read();
							  dc.addboard[i][j] = Character.getNumericValue(date);
						  }
					  }
					  for(int i=0; i<9; i++) {
						  for(int j=0; j<9; j++) {
							  date = (char)filereader.read();
							  difboard[i][j] = Character.getNumericValue(date);
						  }
					  }
			            for(int i=0; i<9; i++) {
			            	for(int j=0; j<9; j++) {
			            		for(int k=0; k<6; k++) {
									  date = (char)filereader.read();
									  dc.memo[i][j][k] = Character.getNumericValue(date);
			            		}
			            	}
			            }
					  dc.esc = false;
					  dc.generate = true;
					  repaint();
					}catch(FileNotFoundException a){
					  System.out.println(a);
					}catch(IOException a){
					  System.out.println(a);
					}
			}

		if(dc.point1&&!dc.generate&&!dc.rode) {//問題になるまでボードの生成を繰り返す
			dc.rode = true;
			dc.generate = false;
			
			TimerTask task = dc.timer(this);
	        Check.Callback callback = new Check.Callback() {
	            public void finished(int[][] board, int[][] anserboard) {

	            	for(int i=0; i<9; i++) {
	            		for(int j=0; j<9; j++) {
	            			if(board[i][j]==0) {
	            				difboard[i][j]=anserboard[i][j];
	            			}
	            		}
	            	}
            		task.cancel();
            		dc.generate = true;
					dc.rode = false;
            		dc.finboard = board;
            		dc.repaint();
	            }
	        };
			Check c = new Check();
			c.fillcount = dc.fillcount;
			c.setCallback(callback);
			c.start();
		}else {
			Point p = e.getPoint();
			for(int i=0;i<9;i++) {
				for(int j=0;j<9;j++) {
					if(dc.finboard[i][j]!=0) {
						continue;
					}
					if(p.x>=10+j*80&&p.x<=10+j*80+80&&p.y>=30+i*80&&p.y<=30+i*80+80) {
						dc.cell[i][j] = true;
						dc.click = true;
						repaint();
						x=i;
						y=j;
					}else {
						dc.cell[i][j] = false;
						repaint();
					}
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if(dc.esc) {
				dc.esc = false;
				dc.save = false;
				repaint();
			}else {
				dc.esc = true;
				repaint();
			}
		}
		if(dc.generate&&dc.click&&!dc.esc) {
			switch ( e.getKeyCode() ) {
			case KeyEvent.VK_1:
				if(shift) {
					if(memocnt[x][y]<=5) {
						dc.memo[x][y][memocnt[x][y]] = 1;
						memocnt[x][y]++;
						repaint();
						break;
					}else {
						break;
					}
				}
				dc.addboard[x][y] = 1;
				repaint();
				break;
			case KeyEvent.VK_2:
				if(shift) {
					if(memocnt[x][y]<=5) {
						dc.memo[x][y][memocnt[x][y]] = 2;
						memocnt[x][y]++;
						repaint();
						break;
					}else {
						break;
					}
				}
				dc.addboard[x][y] = 2;
				repaint();
				break;
			case KeyEvent.VK_3:
				if(shift) {
					if(memocnt[x][y]<=5) {
						dc.memo[x][y][memocnt[x][y]] = 3;
						memocnt[x][y]++;
						repaint();
						break;
					}else {
						break;
					}
				}
				dc.addboard[x][y] = 3;
				repaint();
				break;
			case KeyEvent.VK_4:
				if(shift) {
					if(memocnt[x][y]<=5) {
						dc.memo[x][y][memocnt[x][y]] = 4;
						memocnt[x][y]++;
						repaint();
						break;
					}else {
						break;
					}
				}
				dc.addboard[x][y] = 4;
				repaint();
				break;
			case KeyEvent.VK_5:
				if(shift) {
					if(memocnt[x][y]<=5) {
						dc.memo[x][y][memocnt[x][y]] = 5;
						memocnt[x][y]++;
						repaint();
						break;
					}else {
						break;
					}
				}
				dc.addboard[x][y] = 5;
				repaint();
				break;
			case KeyEvent.VK_6:
				if(shift) {
					if(memocnt[x][y]<=5) {
						dc.memo[x][y][memocnt[x][y]] = 6;
						memocnt[x][y]++;
						repaint();
						break;
					}else {
						break;
					}
				}
				dc.addboard[x][y] = 6;
				repaint();
				break;
			case KeyEvent.VK_7:
				if(shift) {
					if(memocnt[x][y]<=5) {
						dc.memo[x][y][memocnt[x][y]] = 7;
						memocnt[x][y]++;
						repaint();
						break;
					}else {
						break;
					}
				}
				dc.addboard[x][y] = 7;
				repaint();
				break;
			case KeyEvent.VK_8:
				if(shift) {
					if(memocnt[x][y]<=5) {
						dc.memo[x][y][memocnt[x][y]] = 8;
						memocnt[x][y]++;
						repaint();
						break;
					}else {
						break;
					}
				}
				dc.addboard[x][y] = 8;
				repaint();
				break;
			case KeyEvent.VK_9:
				if(shift) {
					if(memocnt[x][y]<=5) {
						dc.memo[x][y][memocnt[x][y]] = 9;
						memocnt[x][y]++;
						repaint();
						break;
					}else {
						break;
					}
				}
				dc.addboard[x][y] = 9;
				repaint();
				break;
			case KeyEvent.VK_BACK_SPACE:
				dc.addboard[x][y] = 0;
				for(int i=0; i<6; i++) {
					dc.memo[x][y][i] = 0;
				}
				memocnt[x][y] = 0;
				repaint();
            	break;
			case KeyEvent.VK_SHIFT:
				shift = true;
				break;
			}
		}
		
		if(Arrays.deepEquals(difboard, dc.addboard)&&dc.generate) {
			dc.correct = true;
			System.out.println("clear");
			repaint();
		}else {
			dc.correct = false;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		switch ( e.getKeyCode() ) {
		case KeyEvent.VK_SHIFT:
			shift = false;
			break;
		}
	}

}
