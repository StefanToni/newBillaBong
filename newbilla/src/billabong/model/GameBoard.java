package billabong.model;

import java.util.ArrayList;
import java.util.List;

import billabong.ai.Diffuser;
import billabong.model.player.Player;

public class GameBoard {
	int width;
	int height;
	private BoardSquare[][] bs;
	private List<Kangaroo> kangaroos = new ArrayList<>(); // just an eas of access to all the kangaroos for all the players on the gameboard
	private List<Player> players = new ArrayList<>();
	
	public GameBoard(int width, int height) {
		super();
		this.width = width;
		this.height = height;

		Diffuser diff = Diffuser.getInstance(); 

		bs = new BoardSquare[width][height];
		
		for (int x=0; x<width; x++){
			for (int y=0; y<height; y++){
				bs[x][y] = new BoardSquare(x, y, diff.getWeight(x, y));
			}
		}
		
		bs[6][6].setWater(true);
		bs[7][6].setWater(true);
		bs[8][6].setWater(true);
		bs[9][6].setWater(true);
		bs[6][7].setWater(true);
		bs[7][7].setWater(true);
		bs[8][7].setWater(true);
		bs[9][7].setWater(true);

	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public BoardSquare getBoardSquare(int x, int y){
		return bs[x][y];
	}

	public BoardSquare[][] getBs() {
		return bs;
	}

	/**
	 * Adds the kangaroo to the the boardsquare as specified by the kangaroos x and y.<br>
	 * Add the kangaroo to the list of kangaroos
	 * @param k
	 */
	private void addKangaroo(Kangaroo kanga) {
		getBoardSquare(kanga.getX(), kanga.getY()).setOccupant(kanga);
		this.kangaroos.add(kanga);
	}

	public List<Kangaroo> getKangaroos() {
		return this.kangaroos;
	}
	
	public GameBoard clone(){
		GameBoard clone = new GameBoard(getWidth(), getHeight());

		for (Player p: this.players){
			Player cp = (Player) p.clone();
			clone.addPlayer(cp);
		}

		return clone;
	}

	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * Adds the player to the gameboard, adds the kangaroos to the boardsquares 
	 * @param p
	 */
	public void addPlayer(Player p) {
		this.players.add(p);
		for (Kangaroo k: p.getKangaroos()){
			addKangaroo(k);
		}
	}

	public void move(Kangaroo k, int tx, int ty) {
		
		// TODO Check for lapping - if lap increment counter if unlap decrement counter
		
		// TODO Check for number of laps completed if finished remove kangaroo
		
		// Otherwise just move him/ her
		this.bs[k.getX()][k.getY()].setOccupant(null);
		this.bs[tx][ty].setOccupant(k);
		k.setX(tx);
		k.setY(ty);
	}
}
