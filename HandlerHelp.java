package castle;

public class HandlerHelp extends Handler {

	public HandlerHelp(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCmd(String word) {
		System.out.println("��·������������������У�go bye help");
        System.out.println("�磺\tgo east");
	}

}
