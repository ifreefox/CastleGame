package castle;

public class HandlerGo extends Handler{

	public HandlerGo(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	public void doCmd(String word) {
		game.goRoom(word);
	}
	
}
