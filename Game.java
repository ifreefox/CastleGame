package castle;

import java.util.HashMap;
import java.util.Scanner;

public class Game {
   //私有的属性
	private Room currentRoom;
	private HashMap<String, Handler> handlers = new HashMap<>();
	
	//内部类，实现命令效果
		private abstract class Handler{
			public void doCmd(String word) {
				
			}
			public boolean isBye() {
				return false;
			}
		}
		//help
//		private class HandlerHelp extends Handler{
//			public void doCmd(String word) {
//				System.out.println("迷路了吗？你可以做的命令有：go bye help");
//		        System.out.println("如：\tgo east");
//			}
//		}
		//bye
//		private class HandlerBye extends Handler{
//			public boolean isBye() {
//				return true;
//			}
//		}
		//go
//		private class HandlerGo extends Handler{
//			public void doCmd(String word) {
//			goRoom(word);
//			}
//		}
//		
   //构造器     
    public Game() 
    {
    	handlers.put("go", new Handler() {
    		public void doCmd(String word) {
    			goRoom(word);
    		}
    	});
    	handlers.put("bye", new Handler() {
    		public boolean isBye() {
				return true;
			}
    	});
    	handlers.put("help", new Handler() {
    		public void doCmd(String word) {
				System.out.println("迷路了吗？你可以做的命令有：go bye help");
		        System.out.println("如：\tgo east");
			}
    	});
        createRooms();
    }

    private void createRooms()
    {
        Room outside, lobby, pub, study, bedroom;
      
        //	制造房间
        outside = new Room("城堡外");
        lobby = new Room("大堂");
        pub = new Room("小酒吧");
        study = new Room("书房");
        bedroom = new Room("卧室");
        
        //	初始化房间的出口
        outside.setExit("east", lobby);
        outside.setExit("south", study);
        outside.setExit("west", pub);
        lobby.setExit("west", outside);
        pub.setExit("east", outside);
        study.setExit("north", outside);
        study.setExit("east", bedroom);
        bedroom.setExit("west", study);
        lobby.setExit("up", pub);
        pub.setExit("down", lobby);
        
        currentRoom = outside;  //	从城堡门外开始
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("欢迎来到城堡！");
        System.out.println("这是一个超级无聊的游戏。");
        System.out.println("如果需要帮助，请输入 'help' 。");
        System.out.println();
        showPrompt();
    }

    
    public void goRoom(String direction) 
    {
        Room nextRoom = currentRoom.getExit(direction);
        

        if (nextRoom == null) {
            System.out.println("那里没有门！");
        }
        else {
            currentRoom = nextRoom;
            showPrompt();
        }
    }
    
    public void showPrompt() {
    	
        System.out.println("你在" + currentRoom);
        System.out.print("出口有: ");
        System.out.print(currentRoom.getExitDesc());
        System.out.println();
    }
	
    public void play() {
    	
    	Scanner in = new Scanner(System.in);
    	
    	while ( true ) {
    		String line = in.nextLine();
    		String[] words = line.split(" ");
    		Handler handler = handlers.get(words[0]);
    		String value = "";
    		
    		if(words.length > 1)
    			value = words[1];
    		if(handler != null) {
    			handler.doCmd(value);
    			if(handler.isBye()) {
    				break;
    			}
    		}
    		/*
    		if ( words[0].equals("help") ) {
    			printHelp();
    		} else if (words[0].equals("go") ) {
    			goRoom(words[1]);
    		} else if ( words[0].equals("bye") ) {
    			break;
    		}
    		*/
    	}
    	
    	in.close();
    }
    
	public static void main(String[] args) {
		Game game = new Game();
		game.printWelcome();
		game.play();
        System.out.println("感谢您的光临。再见！");
        
	}

}
