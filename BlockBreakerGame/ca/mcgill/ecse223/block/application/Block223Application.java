package ca.mcgill.ecse223.block.application;

import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.PlayedGame;
import ca.mcgill.ecse223.block.model.UserRole;
import ca.mcgill.ecse223.block.persistence.Block223Persistence;
import ca.mcgill.ecse223.block.view.Block223Page;

public class Block223Application {
	private static Block223 block223;
	private static UserRole currentUserRole;
	private static Game currentGame;
	private static PlayedGame currentPlayableGame;
	private static int index;
	
	public static void main(String[] args) {
		// start UI
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Block223Page().setVisible(true);
            }
        });
	}
	public static Block223 getBlock223(){
		if (block223 == null){
			block223=Block223Persistence.load();
		}
		return block223;
	}
	public static Block223 resetBlock223(){
		block223 = Block223Persistence.load();
		return block223;
	}

	public static UserRole setCurrentUserRole(UserRole aUserRole){
		return currentUserRole=aUserRole;
	}

	public static UserRole getCurrentUserRole(){
		return currentUserRole;
}
	public static void setCurrentGame(Game aGame){
		currentGame = aGame;
	}
	public static Game getCurrentGame(){
		return currentGame;
	}
	
	public static Block223 getBlock223A() {
		return block223;
	}
	public static void setCurrentPlayableGame(PlayedGame pgame) {
		currentPlayableGame = pgame;
	}
	public static PlayedGame getCurrentPlayableGame() {
		// TODO Auto-generated method stub
		return currentPlayableGame;
	}
	
	

}
