
public class Environment {
	
	public static Node[][] grid;
	public static Agent agent;
	
	public static void main(String[] args) {
		
		//Static initialization of environment according to the scenario
		grid = new Node[3][3];
		
		grid[0][0] = new Node(false, false, false, 0, 0);//1,1
		grid[1][0] = new Node(false, false, false, 1, 0);//2,1
		grid[2][0] = new Node(false, false, true, 2, 0);//3,1 (Blocked)
		grid[0][1] = new Node(false, false, false, 0, 1);//1,2
		grid[1][1] = new Node(false, false, false, 1, 1);//2,2
		grid[2][1] = new Node(false, false, false, 2, 1);//3,2
		grid[0][2] = new Node(true, false, false, 0, 2);//1,3 (Fire)
		grid[1][2] = new Node(false, false, false, 1, 2);//2,3
		grid[2][2] = new Node(false, true, false, 2, 2);//3,3 (Diamond)
		
		agent = new Agent(0, 0, grid);
		
		agent.Start();
	}

}
