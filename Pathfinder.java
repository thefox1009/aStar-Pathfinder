import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Pathfinder {
	
	private Tiles tile[][];
	private Panel panel;
	private List<Tiles> openList;
	private List<Tiles> closedList;
	private Tiles player;
	private Tiles objective;
	private boolean init = true;
	private long time = 0;
	private long start;
	private long end;
	private Tiles current;
	private boolean finished = false;
	
	
	
	
	
	



	public Pathfinder(Panel panel){
		this.panel=panel;
		closedList = new ArrayList<Tiles>();
		openList = new ArrayList<Tiles>();
		
	}
	
	
	
	public void pathfinder(){
		
		if(init){
			initPathfinding();
		
			
		}
		
		else{
			findPath();
		}
		
	}
	
	private void finishPath(Tiles tile) {
		Tiles looptile1 = tile;
		Tiles looptile2;
		while(true){
			if(looptile1.getState() != Tiles.PLAYER){
				looptile1.setState(Tiles.PATH);
				looptile2 = this.tile[looptile1.getParentPointer().y][looptile1.getParentPointer().x];
				looptile1 = looptile2;
				
				
			}
				
			else{
				end = System.currentTimeMillis();
		time = (end - start);
		System.out.println(time);
		
		panel.setFindPath(false);
		finished=true;
		
		break;
		}}
	}

	private void findPath() {
		if(closedList.isEmpty()){
			
			closedList.add(tile[panel.getPlayerPoint().y][panel.getPlayerPoint().x]);
			
			
			
		}else{
			current = getLowestF();
			closedList.add(current);
			openList.remove(current);
		}
		addNeighbours(closedList.get(closedList.size() - 1));
		calculate();
		
		
		for(int i=0;i<openList.size();i++){
			openList.get(i).setState(Tiles.CHECKED);
		}
		for(int i=0;i<closedList.size();i++){
			if(panel.isFindPath()){
			closedList.get(i).setState(Tiles.CLOSED);
			}
			
			
	}
		}
	
	


	private void initPathfinding(){
		
		//checks if objective is here
		start = System.currentTimeMillis();
		boolean obj = false;
		if(tile==null){
			tile = panel.getTile();
		}
		for(int i=0;i<tile.length;i++){
			for(int j=0;j<tile[i].length;j++){
				
				if(tile[i][j].getState() == Tiles.OBJECTIVE)obj = true;
			
			}
		}
			if(!obj) {
				panel.setFindPath(false);
				return;
			}
				
			
		
		if(player == null){
			player = tile[panel.getPlayerPoint().y][panel.getPlayerPoint().x];
		}
		if(objective == null){
			objective = tile[panel.getObjectivePoint().y][panel.getObjectivePoint().x];
		}
		for(int i=0;i<tile.length;i++){
			for(int j=0;j<tile[i].length;j++){
				if(tile[i][j].getState()!=Tiles.OBSTACLE){
				tile[i][j].setH((distance(tile[i][j].getPoint(), panel.getObjectivePoint())) / 10);
				}
			}
			init = false;
			
	}

				}
	private int distance(Point a, Point b) {
		int ht = (int)(Math.pow((a.x - b.x), 2)+ Math.pow((a.y - b.y), 2));
	
		return ht;
	}
	private void addNeighbours(Tiles parent) {
		//1
		if(checkArrayOob(parent.getPoint().y -1, parent.getPoint().x -1)){
		
		if(closedList.contains(tile[parent.getPoint().y - 1][parent.getPoint().x - 1]) || tile[parent.getPoint().y - 1][parent.getPoint().x - 1].getState() == Tiles.OBSTACLE){
			
		}
		else if(tile[parent.getPoint().y - 1][parent.getPoint().x - 1].getState() == Tiles.OBJECTIVE){
			finishPath(parent);
			return;
		}
			
		else if(openList.contains(tile[parent.getPoint().y - 1][parent.getPoint().x - 1])){
			if(checkParentTile(parent, tile[parent.getPoint().y - 1][parent.getPoint().x - 1])){
				tile[parent.getPoint().y - 1][parent.getPoint().x - 1].setParentPointer(parent.getPoint());
			}
		}
			
		else{
			tile[parent.getPoint().y - 1][parent.getPoint().x - 1].setParentPointer(parent.getPoint());
		openList.add(tile[parent.getPoint().y - 1][parent.getPoint().x - 1]);
		
		}
		}
		//
		
		//2
				if(checkArrayOob(parent.getPoint().y, parent.getPoint().x - 1)){
				
				if(closedList.contains(tile[parent.getPoint().y][parent.getPoint().x - 1]) || tile[parent.getPoint().y][parent.getPoint().x - 1].getState() == Tiles.OBSTACLE){
					
				}
				else if(tile[parent.getPoint().y][parent.getPoint().x - 1].getState() == Tiles.OBJECTIVE){
					finishPath(parent);
					return;
				}
					
				else if(openList.contains(tile[parent.getPoint().y][parent.getPoint().x - 1])){
					if(checkParentTile(parent, tile[parent.getPoint().y][parent.getPoint().x - 1])){
						tile[parent.getPoint().y][parent.getPoint().x - 1].setParentPointer(parent.getPoint());
					}
				}
					
				else{
					tile[parent.getPoint().y][parent.getPoint().x - 1].setParentPointer(parent.getPoint());
				openList.add(tile[parent.getPoint().y][parent.getPoint().x - 1]);
				
				}
				}
				//
				//3
				if(checkArrayOob(parent.getPoint().y -1, parent.getPoint().x + 1)){
				
				if(closedList.contains(tile[parent.getPoint().y - 1][parent.getPoint().x + 1]) || tile[parent.getPoint().y - 1][parent.getPoint().x + 1].getState() == Tiles.OBSTACLE){
					
				}
				else if(tile[parent.getPoint().y - 1][parent.getPoint().x + 1].getState() == Tiles.OBJECTIVE){
					finishPath(parent);
					return;
				}
					
				else if(openList.contains(tile[parent.getPoint().y - 1][parent.getPoint().x + 1])){
					if(checkParentTile(parent, tile[parent.getPoint().y - 1][parent.getPoint().x + 1])){
						tile[parent.getPoint().y - 1][parent.getPoint().x + 1].setParentPointer(parent.getPoint());
					}
				}
					
				else{
					tile[parent.getPoint().y - 1][parent.getPoint().x + 1].setParentPointer(parent.getPoint());
				openList.add(tile[parent.getPoint().y - 1][parent.getPoint().x + 1]);
				
				}
				}
				//
				//4
				if(checkArrayOob(parent.getPoint().y, parent.getPoint().x + 1)){
				
				if(closedList.contains(tile[parent.getPoint().y][parent.getPoint().x + 1]) || tile[parent.getPoint().y][parent.getPoint().x + 1].getState() == Tiles.OBSTACLE){
					
				}
				else if(tile[parent.getPoint().y][parent.getPoint().x + 1].getState() == Tiles.OBJECTIVE){
					finishPath(parent);
					return;
				}
					
				else if(openList.contains(tile[parent.getPoint().y][parent.getPoint().x + 1])){
					if(checkParentTile(parent, tile[parent.getPoint().y][parent.getPoint().x + 1])){
						tile[parent.getPoint().y][parent.getPoint().x + 1].setParentPointer(parent.getPoint());
					}				}
					
				else{
					tile[parent.getPoint().y][parent.getPoint().x + 1].setParentPointer(parent.getPoint());
				openList.add(tile[parent.getPoint().y][parent.getPoint().x + 1]);
				
				}
				}
				//
				//5
				if(checkArrayOob(parent.getPoint().y + 1, parent.getPoint().x + 1)){
				
					if(closedList.contains(tile[parent.getPoint().y + 1][parent.getPoint().x + 1]) || tile[parent.getPoint().y + 1][parent.getPoint().x + 1].getState() == Tiles.OBSTACLE){
						
					}
				else if(tile[parent.getPoint().y + 1][parent.getPoint().x + 1].getState() == Tiles.OBJECTIVE){
					finishPath(parent);
					return;
				}
					
				else if(openList.contains(tile[parent.getPoint().y + 1][parent.getPoint().x + 1])){
					if(checkParentTile(parent, tile[parent.getPoint().y + 1][parent.getPoint().x + 1])){
						tile[parent.getPoint().y + 1][parent.getPoint().x + 1].setParentPointer(parent.getPoint());
					}				}
					
				else{
					tile[parent.getPoint().y + 1][parent.getPoint().x + 1].setParentPointer(parent.getPoint());
				openList.add(tile[parent.getPoint().y + 1][parent.getPoint().x + 1]);
				
				}
				}
				//
				//6
				if(checkArrayOob(parent.getPoint().y + 1, parent.getPoint().x)){
				System.out.println("6 1");
				if(closedList.contains(tile[parent.getPoint().y + 1][parent.getPoint().x]) || tile[parent.getPoint().y + 1][parent.getPoint().x].getState() == Tiles.OBSTACLE){
				}
				else if(tile[parent.getPoint().y + 1][parent.getPoint().x].getState() == Tiles.OBJECTIVE){
					finishPath(parent);
					return;
				}
					
				else if(openList.contains(tile[parent.getPoint().y + 1][parent.getPoint().x])){
					if(checkParentTile(parent, tile[parent.getPoint().y + 1][parent.getPoint().x])){
						tile[parent.getPoint().y + 1][parent.getPoint().x].setParentPointer(parent.getPoint());
					}				}
					
				else{
					tile[parent.getPoint().y + 1][parent.getPoint().x].setParentPointer(parent.getPoint());
				openList.add(tile[parent.getPoint().y + 1][parent.getPoint().x]);
				
				}
				}
				//
				//7
				if(checkArrayOob(parent.getPoint().y + 1, parent.getPoint().x -1)){
					
				if(closedList.contains(tile[parent.getPoint().y + 1][parent.getPoint().x - 1]) || tile[parent.getPoint().y + 1][parent.getPoint().x - 1].getState() == Tiles.OBSTACLE){
					
				}
				else if(tile[parent.getPoint().y + 1][parent.getPoint().x - 1].getState() == Tiles.OBJECTIVE){
					
					finishPath(parent);
					return;
				}
					
				else if(openList.contains(tile[parent.getPoint().y + 1][parent.getPoint().x - 1])){
					if(checkParentTile(parent, tile[parent.getPoint().y + 1][parent.getPoint().x - 1])){
						tile[parent.getPoint().y + 1][parent.getPoint().x - 1].setParentPointer(parent.getPoint());
					}				}
					
				else{
					
					tile[parent.getPoint().y + 1][parent.getPoint().x - 1].setParentPointer(parent.getPoint());
				openList.add(tile[parent.getPoint().y + 1][parent.getPoint().x - 1]);
				
				}
				}
				//
				//8
				if(checkArrayOob(parent.getPoint().y - 1, parent.getPoint().x)){
				
				if(closedList.contains(tile[parent.getPoint().y - 1][parent.getPoint().x]) || tile[parent.getPoint().y - 1][parent.getPoint().x].getState() == Tiles.OBSTACLE){
					
				}
				else if(tile[parent.getPoint().y - 1][parent.getPoint().x].getState() == Tiles.OBJECTIVE){
					finishPath(parent);
					return;
				}
					
				else if(openList.contains(tile[parent.getPoint().y - 1][parent.getPoint().x])){
					if(checkParentTile(parent, tile[parent.getPoint().y - 1][parent.getPoint().x])){
						tile[parent.getPoint().y - 1][parent.getPoint().x].setParentPointer(parent.getPoint());
					}				}
					
				else{
					tile[parent.getPoint().y - 1][parent.getPoint().x].setParentPointer(parent.getPoint());
				openList.add(tile[parent.getPoint().y - 1][parent.getPoint().x]);
				
				}
				}
				//
				
				
				
				
				
				
	
		
		
		}
	
	
		
	private boolean checkParentTile(Tiles parent, Tiles tile2) {
		if(parent.getG() + (distance(parent.getPoint(), tile2.getPoint())) < tile2.getG()){
			
			return true;
			
		}
		else return false;
		
	}

	private boolean checkArrayOob(int y, int x) {
		
		if(y>=0 && y<tile.length && x>=0 && x<tile[1].length){
			return true;
		}else return false;
		
	}
	private void calculate(){
		//calculate g and f
		
		for(int i = 0; i<openList.size(); i++){
			openList.get(i).setG(tile[openList.get(i).getParentPointer().y][openList.get(i).getParentPointer().x].getG() + distance(openList.get(i).getPoint(),tile[openList.get(i).getParentPointer().y][openList.get(i).getParentPointer().x].getPoint()));
			openList.get(i).setF(openList.get(i).getH() + openList.get(i).getG());
		}
		
		
	}
	private Tiles getLowestF() {

		Tiles f = openList.get(0);
		for(int i=0;i<openList.size();i++){
			if((openList.get(i).getF() < f.getF()) && (openList.get(i).getH() <= f.getH())){
				f=openList.get(i);
			}
		}
		return f;
		
	}
	public boolean isFinished() {
		return finished;
	}
	public void setFinished(boolean finished){
		this.finished=finished;
	}



	
}
