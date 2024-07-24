

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawCanvas extends JPanel{//描画するクラス
	boolean point1 = false;
	boolean spoint1 = false;
	int btx = 275;
	int bty = 600;
	int btsizex = 200;
	int btsizey = 100;
	int stx = 230;
	int sty = 200;
	int stsizex = 280;
	int stsizey = 100;
	int fillcount = 30;
	boolean generate = false;
	boolean rode = false;
	int gamen = 0;
	int[][] finboard = new int[9][9];
	int[][] addboard = new int[9][9];
	boolean[][] choice = new boolean[9][9];
	boolean click = false;
	boolean[][] cell = new boolean[9][9];
	boolean correct = false;
	int[][][] memo = new int[9][9][6];
	boolean esc = false;
	boolean save = false;
	boolean fillpoint1 = false;
	boolean fillpoint2 = false;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		if(!generate&&rode) {
			Font font = new Font("Serif",Font.BOLD,50);
			g.setFont(font);
			g2.drawString("生成中", 300, 380);
			
			if(gamen==1) {
				g2.fillRect(315,400,20,20);
			}else if(gamen==2) {
				g2.fillRect(315,400,20,20);
				g2.fillRect(365,400,20,20);
			}else if(gamen==3) {
				g2.fillRect(315,400,20,20);
				g2.fillRect(365,400,20,20);
				g2.fillRect(415,400,20,20);
			}

		}
		if(point1&&!rode) {
			g2.setColor(Color.RED);	
		}else {
			g2.setColor(Color.BLACK);	
		}
		if(!generate) {
			Font seisei = new Font("Serif",Font.BOLD,btsizey-10);
			g.setFont(seisei);
			g2.drawString("生成", btx, bty+btsizey-10);
			g2.drawRect(btx,bty,btsizex,btsizey);

			g2.setColor(Color.BLACK);
			Font kazu = new Font("Serif",Font.BOLD,30);
			g.setFont(kazu);
			g2.drawString("埋める数   "+fillcount,250,500);
			g2.setColor(Color.GRAY);
			g2.fillRect(440,455,30,30);
			g2.fillRect(440,495,30,30);

			g2.setColor(Color.BLUE);
			g2.fillPolygon(new int[] {455,465,445}, new int[] {460,480,480}, 3);
			g2.fillPolygon(new int[] {455,465,445}, new int[] {520,500,500}, 3);
			if(fillpoint1){
				g2.setColor(Color.BLACK);
				g2.drawRect(440,455,30,30);
				g2.drawPolygon(new int[] {455,465,445}, new int[] {460,480,480}, 3);
			}
			if(fillpoint2){
				g2.setColor(Color.BLACK);
				g2.drawRect(440,495,30,30);
				g2.drawPolygon(new int[] {455,465,445}, new int[] {520,500,500}, 3);
			}
			
			Font font5 = new Font("Serif",Font.BOLD,stsizey-10);
			g.setFont(font5);
    		g2.setColor(Color.BLACK);
    		if(spoint1&&!rode) {
    			g2.setColor(Color.BLUE);
    		}
			g2.drawString("ロード", stx, sty+stsizey-10);
			g2.drawRect(stx,sty,stsizex,stsizey);
		}else {
			g2.setColor(Color.BLACK);
			Font font = new Font("Arial",Font.BOLD,30);
			g.setFont(font);
			g2.drawString("esc", 0, 770);
			Font font2 = new Font("Arial",Font.BOLD,45);
			g2.setFont(font2);
		    for(int i=0; i<9; i++) {
		    	for(int j=0; j<9; j++) {
		    		if(finboard[i][j]!=0) {
			    		g2.setColor(Color.BLACK);
			    		g2.drawString(String.valueOf(finboard[i][j]),40+j*80,85+i*80);
		    			continue;
		    		}
		    		if(choice[i][j]) {
		    			Color chColor = new Color(204, 255, 255);
						g2.setColor(chColor);
			    		g2.fillRect(10+j*80, 30+i*80, 80, 80);
		    		}
		    		if(click&&cell[i][j]) {
		    			g2.setColor(Color.CYAN);
			    		g2.fillRect(10+j*80, 30+i*80, 80, 80);
		    		}
		    	}
		    }
    		g2.setColor(Color.BLACK);
    		for(int i=0; i<10; i++) {
		    	 g2.drawLine(0,30+i*80,750,30+i*80);
		    	 g2.drawLine(10+i*80,0,10+i*80,750);
		    }
			g2.setColor(Color.BLUE);
			g2.fillRect(0, 265, 750, 10);
			g2.fillRect(0, 505, 750, 10);
			g2.fillRect(245, 0, 10, 750);
			g2.fillRect(485, 0, 10, 750);

    		g2.setColor(Color.BLUE);
		    for(int i=0; i<9; i++) {
		    	for(int j=0; j<9; j++) {
		    		if(addboard[i][j] != 0) {
		    			g2.drawString(String.valueOf(addboard[i][j]),40+j*80,85+i*80);
		    		}
		    	}
		    }
		    
    		g2.setColor(Color.BLACK);
			Font font4 = new Font("Arial",Font.BOLD,20);
			g2.setFont(font4);
		    for(int i=0; i<9; i++) {
		    	for(int j=0; j<9; j++) {
		    		if(memo[i][j][0]==0)continue;
		    		for(int k=0; k<6; k++) {
		    			if(memo[i][j][k] != 0) {
		    				if(k<=2) {
		    					g2.drawString(String.valueOf(memo[i][j][k]),(20+j*80)+k*25,(50+i*80));
		    				}else {
		    					g2.drawString(String.valueOf(memo[i][j][k]),(20+j*80)+k%3*25,(50+i*80)+55);
		    				}
		    			}else {
		    				break;
		    			}
		    		}
		    	}
		    }

		    if(esc) {
	    		g2.setColor(Color.WHITE);
		    	g2.fillRect(120, 100, 500, 500);
				Font font5 = new Font("Serif",Font.BOLD,stsizey-10);
				g.setFont(font5);
	    		g2.setColor(Color.BLACK);
	    		if(spoint1) {
	    			g2.setColor(Color.BLUE);
	    		}
				g2.drawString("セーブ", stx, sty+stsizey-10);
				g2.drawRect(stx,sty,stsizex,stsizey);
				if(save) {
		    		g2.setColor(Color.BLACK);
					Font font6 = new Font("Serif",Font.BOLD,50);
					g.setFont(font6);
					g2.drawString("セーブ完了", 250, 400);
				}
		    }
		    
		    if(correct) {
				Font font3 = new Font("Serif",Font.BOLD,80);
				g2.setFont(font3);
				g2.setColor(Color.BLUE);
				g2.drawString("PERFECT!",180,300);
		    }
		}
	}
	public TimerTask timer(JFrame jf) {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
//				System.out.println("aiu");
				jf.repaint();
				if(gamen == 0) {
					gamen=1;
				}else if(gamen==1) {
					gamen=2;
				}else if(gamen==2) {
					gamen=3;
				}else if(gamen==3) {
					gamen=0;
				}
			}
		};
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, 0, 1000);
		return task;
	}
}
