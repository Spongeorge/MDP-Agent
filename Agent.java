import java.util.ArrayList;
import java.util.List;

public class Agent {
	public int xPos;
	public int yPos;
	public boolean hasMetGoal;
	public static Node[][] env;
	
	public Agent(int x, int y, Node[][] e) {
		xPos = x;
		yPos = y;
		hasMetGoal = false;
		env = e;
	}
	
	public void Start() {
		System.out.println("Agent initialized at position " + (xPos+1) + "," + (yPos + 1));
		Consider();
	}
	
	public void Consider() {//Consider the potential rewards for each possible action
		if(!hasMetGoal) {
			List<Action> actions = new ArrayList<Action>();

			if(yPos < env[xPos].length - 1 && env[yPos+1][xPos].blocked == false) {
				actions.add(new Action(Direction.UP, CalculateReward(env[yPos+1][xPos]), false));
			}
			if(yPos > 0 && env[yPos-1][xPos].blocked == false) {
				actions.add(new Action(Direction.DOWN, CalculateReward(env[yPos-1][xPos]), false));
			}
			if(xPos > 0 && env[yPos][xPos-1].blocked == false) {
				actions.add(new Action(Direction.LEFT, CalculateReward(env[yPos][xPos-1]), false));
			}
			if(xPos < env.length - 1 && env[yPos][xPos+1].blocked == false) {
				actions.add(new Action(Direction.RIGHT, CalculateReward(env[yPos][xPos+1]), false));
			}

			//Default action is to give up if no actions are preferable to staying put.
			Action decision = new Action(Direction.UP, -999, true);


			//Choose the action with the highest reward
			for(Action a : actions) {
				if(a.reward > decision.reward) decision = a;
			}

			Act(decision);
		} else System.out.println("Agent has reached goal. Shutting down!");
		
	}
	
	public void Act(Action a) {
		
		if (!a.giveUp) {
			System.out.println("Decided to move " + str(a.dir) + " for reward " + a.reward + ".");
			Move(a.dir);
			System.out.println("New position: " + (xPos + 1) + "," + (yPos + 1));
			
			if((xPos + 1) == env.length && (yPos + 1) == env[xPos].length) {
				
				
				hasMetGoal = true;
			}
			
			Consider();
		} else {
			System.out.println("Agent decided to give up.");
			hasMetGoal = true;
		}
	}
	
	private String str(Agent.Direction dir) {
		switch(dir) {
		case UP:
			return "up";
		case DOWN:
			return "down";
		case LEFT:
			return "left";
		case RIGHT:
			return "right";
		}
		return "";
	}

	public void Move(Direction d) {
		switch(d) {
			case UP:
				yPos++;
				break;
			case DOWN:
				yPos--;
				break;
			case LEFT:
				xPos--;
				break;
			case RIGHT:
				xPos++;
				break;
		}
		
	}
	
	public int CalculateReward(Node node) {
		int r = 0;
		
		if(node.fire)r -= 500;

		if(node.diamond) r += 500;
		
		if(node.xPos > xPos) r += 1;
		
		if(node.yPos > yPos) r += 1;
		
		return r;
	}
	
	class Action{
		public boolean giveUp;
		public Direction dir;
		public int reward;
		
		public Action(Direction d, int r, boolean s) {
			dir = d;
			reward = r;
			giveUp = s;
		}
	}
	
	enum Direction{
		UP,
		DOWN,
		LEFT,
		RIGHT
	}
}
