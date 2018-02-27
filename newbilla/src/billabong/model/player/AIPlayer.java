package billabong.model.player;

import billabong.ai.MiniMax;

public class AIPlayer extends Player {

	public AIPlayer() {
		super();
		try{
			setColor(cols[getTeamId()]);
		}catch(Exception e){};
	}
	
	@Override
	public void move() {
		MiniMax mm = MiniMax.getInstance();
		try {
			mm.calculateMoves(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
