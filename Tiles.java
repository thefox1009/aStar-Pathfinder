import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Tiles {
final static int GROUND = 0;
final static int PLAYER = 1;
final static int OBSTACLE = 2;
final static int OBJECTIVE = 3;
final static int CHECKED = 4;
final static int PATH = 5;
final static int CLOSED = 6;


final static int tileSize =40;
private int state;


private int tileNmb;
private int x;
private int y;
private Point point;



private int h;

private int g;
private int f;
private Point parentPointer;



	public Tiles(int tileNmb, int state,int x, int y){
		
		
		this.tileNmb= tileNmb;
		this.state = state;
		point = new Point(x, y);
		this.x= x * Tiles.tileSize;
		this.y= y * Tiles.tileSize;
		parentPointer = new Point();
		g = 0;
	
	}
	public Tiles(){
		state=0;
	}
	
	void drawTile(Graphics g){
		switch(state) {
		case GROUND: g.setColor(Color.LIGHT_GRAY);
		
				break;
		case PLAYER: g.setColor(Color.RED);
				break;
		case OBSTACLE: g.setColor(new Color(22, 22, 22));
				break;
		case OBJECTIVE: g.setColor(new Color(247, 197, 22));
				break;
		case CLOSED: g.setColor(new Color(238, 77, 46));
				break;
		case PATH: g.setColor(new Color(242, 245, 244));
				break;
		case CHECKED: g.setColor((new Color(117, 192, 199)));
		break;
		}
		g.fillRect(x,y, tileSize,tileSize);
		g.setColor(Color.GRAY);
		g.drawRect(x,y, tileSize,tileSize);
//		
		g.drawString("" + this.g, x+ 5, y + 10);
		g.drawString("" + h, x + 5, y + 25);
		g.drawString("" + f, x + 25, y + 10);
		//g.drawString("" + tileNmb, x + 5, y + 10);
//		g.drawString("" + point.x, x+ 5, y + 10);
//		g.drawString("" + point.y, x + 5, y + 25);
	}
	void deleteObjective(){
		if(state == Tiles.OBJECTIVE){
			state=Tiles.GROUND;
		}
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		if(this.state == Tiles.PLAYER){
			state=Tiles.PLAYER;
		}
		else if(this.state == Tiles.OBJECTIVE){
			state=Tiles.OBJECTIVE;
		}else this.state = state;
	}
	public int getTileNmb() {
		return tileNmb;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public int getG() {
		return g;
	}
	public void setG(int g) {
		this.g = g;
	}
	public int getF() {
		return f;
	}
	public void setF(int f) {
		this.f = f;
	}
	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
	public Point getParentPointer() {
	return parentPointer;
}
public void setParentPointer(Point parentPointer) {
	this.parentPointer = parentPointer;
}

	
}
