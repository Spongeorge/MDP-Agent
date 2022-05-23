
public class Node {
	public boolean fire;
	public boolean diamond;
	public boolean blocked;
	public int xPos;
	public int yPos;
	
	public Node(boolean f, boolean d, boolean b, int x, int y) {
		fire = f;
		diamond = d;
		blocked = b;
		xPos = x;
		yPos = y;
	}
}
