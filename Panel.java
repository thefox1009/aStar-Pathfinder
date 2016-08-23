import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;







public class Panel extends Canvas{
	
	
	 private double averageFPS;
	 private BufferStrategy strat;
	 private Graphics g;
	 private Tiles[][] tile; 
	

	private boolean findPath = false;
	

	private int state;
	private int mouseX;
	private int mouseY;
	 
	private Point playerPoint;
	 
	


	private Point objectivePoint;
	
	private final int FRAME_WIDTH = 800;
	private final int FRAME_HEIGHT = 600;
	
	
	Pathfinder pathfinder;
	

	
	
	//CONSTRUCOR
	public Panel(){
		super();
		setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		tile = new Tiles[FRAME_HEIGHT/Tiles.tileSize][FRAME_WIDTH/Tiles.tileSize];
		addMouseListener(new MouseHandler());
			
			
	
	}

	
	
	
	
	public void start() {
		
		
		createBufferStrategy(2);
		strat = getBufferStrategy();
		reset();
		
					
		pathfinder = new Pathfinder(this);
		
				
		
		
		long startTime;
		long URDTimeMillis;
		long waitTime;
		long totalTime = 0;
		
		int frameCount = 0;
		final int frameMax = 30;
		
		long targetTime = 1000/frameMax;
			
			while(true){
				startTime = System.nanoTime();
			
			
			if(findPath){
				pathfinder.pathfinder();
			}
			render();
			
		
			
			URDTimeMillis =(System.nanoTime() - startTime)/1000000;
			waitTime = targetTime - URDTimeMillis;
	if(waitTime<0)waitTime = 0;
			
			try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			totalTime+= System.nanoTime() - startTime;
			frameCount++;
			if(frameCount == frameMax){
				averageFPS= 1000.0 / ((totalTime / frameCount) / 1000000.0);
				frameCount=0;
				totalTime=0;
			}
			}
	}
	
	
	
	
	
 private void render() {
	 g =strat.getDrawGraphics();
		draw();
		g.dispose();
		strat.show();
		
		
		}


private void draw() {
	g.fillRect(0,0, FRAME_WIDTH,FRAME_HEIGHT);
	g.setColor(Color.WHITE);
	for(int i=0;i<tile.length;i++){
		for(int j=0;j<tile[i].length;j++){
			
			tile[i][j].drawTile(g);
		
		}
	
	g.drawString("" + averageFPS, 650, 20);
	
}



}


private void reset() {
	
		
		
	
	int tileNmb=0;
	for(int i=0;i<tile.length;i++){
		for(int j=0;j<tile[i].length;j++){
			tileNmb++;
			//init player
			if(tileNmb==141)
			{state=Tiles.PLAYER;
			
			}
			
			else{state = Tiles.GROUND;}
			
			tile[i][j]= new Tiles(tileNmb, state,j,i);
			
			if(tileNmb==141)
			{
			playerPoint = tile[i][j].getPoint();
			}
		}
	}
	}






class MouseHandler implements MouseListener{


//set Tiles with MouseClickes
@Override
public void mousePressed(MouseEvent e) {
	mouseX = e.getX();
	mouseY = e.getY();
	// mousebutton1 = delete/ create obstacle/reset Tiles - mousebutton2 = set objective
	for(int i=0;i<tile.length;i++){
		for(int j=0;j<tile[i].length;j++){
		//checks for collision
			if((mouseX >=tile[i][j].getX()) && (mouseX < (tile[i][j].getX() + Tiles.tileSize))
					&&(mouseY >=tile[i][j].getY()) && (mouseY < (tile[i][j].getY() + Tiles.tileSize))){
				
				if(e.getButton() == MouseEvent.BUTTON1 && !findPath){
					
					if(tile[i][j].getState() == Tiles.OBSTACLE){
						
						tile[i][j].setState(Tiles.GROUND);
						
					}
					else if (tile[i][j].getState() == Tiles.PLAYER){
						findPath = true;
						
					}
					else {tile[i][j].setState(Tiles.OBSTACLE);
					}
				}
			
				else if(((e.getButton() == MouseEvent.BUTTON3) || e.getButton() == MouseEvent.BUTTON2 )&& !findPath){
					tile[i][j].setState(Tiles.OBJECTIVE);
					objectivePoint = tile[i][j].getPoint();
					
					for(int k=0;k<tile.length;k++){
						for(int l=0;l<tile[i].length;l++){
							
							if((tile[k][l].getState() == Tiles.OBJECTIVE)&& (tile[k][l]!=tile[i][j])){
								tile[k][l].deleteObjective();
								
							}
						}
					}
			}
				
				
			}
			
		}
	}
}

@Override
public void mouseEntered(MouseEvent e) {}
@Override
public void mouseExited(MouseEvent e){}
@Override
public void mouseReleased(MouseEvent e) {}
@Override
public void mouseClicked(MouseEvent e) {}

}
public void setFindPath(boolean findPath) {
	this.findPath = findPath;
}





public Tiles[][] getTile() {
	return tile;
}


public void setTile(Tiles[][] tile) {
	this.tile = tile;
}
public Point getPlayerPoint() {
	return playerPoint;
}

public Point getObjectivePoint() {
	return objectivePoint;
}
public boolean isFindPath() {
	return findPath;
}






}





	
	
	
