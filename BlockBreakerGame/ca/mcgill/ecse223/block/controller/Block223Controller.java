package ca.mcgill.ecse223.block.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.TOUserMode.Mode;
import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Ball;
import ca.mcgill.ecse223.block.model.Block;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.BlockAssignment;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.HallOfFameEntry;
import ca.mcgill.ecse223.block.model.Level;
import ca.mcgill.ecse223.block.model.Paddle;
import ca.mcgill.ecse223.block.model.PlayedBlockAssignment;
import ca.mcgill.ecse223.block.model.PlayedGame;
import ca.mcgill.ecse223.block.model.Player;
import ca.mcgill.ecse223.block.model.User;
import ca.mcgill.ecse223.block.model.UserRole;
import ca.mcgill.ecse223.block.model.PlayedGame.PlayStatus;
import ca.mcgill.ecse223.block.persistence.Block223Persistence;
import ca.mcgill.ecse223.block.view.Block223PlayModeInterface;

public class Block223Controller {

	// **********
	// Modifier methods
	// **********
	public static void createGame(String name) throws InvalidInputException { 
	    if(!(Block223Application.getCurrentUserRole() instanceof Admin)){
			throw new InvalidInputException("Admin privileges are required to create a game.");
		}
		if(name==null) {
			throw new InvalidInputException("The name of a game must be specified.");
		}	
		
		if(name=="") {
			throw new InvalidInputException("The name of a game must be specified.");
		}
		
		Block223 block223 = Block223Application.getBlock223();
		Admin admin = (Admin) Block223Application.getCurrentUserRole(); 
		Game aGame=block223.findGame(name);	
		
		if(aGame!=null) {
				throw new InvalidInputException("The name of a game must be unique.");
		}
		aGame = new Game(name,1,admin,1,1,1.0,1,1,block223);  //need help in this line
		//Block223Application.setCurrentGame(aGame);
	}

	public static void setGameDetails(int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
        String error = "";

        if(nrLevels < 1 || nrLevels > 99) {
        	 error = "The number of levels must be between 1 and 99.";
        }
        if(nrBlocksPerLevel < 1) {
          error = error + "The number of blocks per level must be greater than zero.";
        }
        if(minBallSpeedX < 1 && minBallSpeedY < 1) {
            error = error + "The minimum speed of the ball must be greater than zero.";
          }
       /* if(minBallSpeedY < 1) {
            error = error + "The minimum speed of the ball must be greater than zero.";
          }*/
        if(ballSpeedIncreaseFactor <= 0.0) {
            error = error + "The speed increase factor of the ball must be greater than zero.";
          }
        if(maxPaddleLength < 1 || maxPaddleLength > 390) {
            error = error + "The maximum length of the paddle must be greater than zero and less than or equal to 390.";
          }
        if(minPaddleLength < 1 ) {
            error = error + "The minimum length of the paddle must be greater than zero.";
          }
        if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
        Game game = Block223Application.getCurrentGame();
        if(game == null) {
        	throw new InvalidInputException("A game must be selected to define game settings.");
        }
        List<Level> alevels = game.getLevels();
        for(Level alevel : alevels) {
        	int nrBlocks = alevel.getBlockAssignments().size();
        	if (nrBlocksPerLevel < nrBlocks) {
        		throw new InvalidInputException("The maximum number of blocks per level cannot be less than the number of existing blocks in a level.");
        	}
        }
        if(!(Block223Application.getCurrentUserRole() instanceof Admin)){
			throw new InvalidInputException("Admin privileges are required to define game settings.");
		}
        
        Admin admin = (Admin) Block223Application.getCurrentUserRole();
        if(!(admin.equals(game.getAdmin()))){
        	throw new InvalidInputException("Only the admin who created the game can define its game settings.");
        }
        
        Ball ball = game.getBall();
        ball.setMinBallSpeedX(minBallSpeedX);
        ball.setMinBallSpeedY(minBallSpeedY);
        ball.setBallSpeedIncreaseFactor(ballSpeedIncreaseFactor);
        
        Paddle paddle = game.getPaddle();
        paddle.setMaxPaddleLength(maxPaddleLength);
        paddle.setMinPaddleLength(minPaddleLength);
        
        List<Level> levels = game.getLevels();
        int size = levels.size();
        
        while(nrLevels > size) {
        	game.addLevel();
        	size = levels.size();
        }
        
        while(nrLevels < size) {
        	 Level level = game.getLevel(size-1);
        	 level.delete();
        	 size = levels.size(); 
        }
        
        game.setNrBlocksPerLevel(nrBlocksPerLevel);
}

	public static void deleteGame(String name) throws InvalidInputException {
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to delete a game.");
		}
	    if(name==null) {
		throw new InvalidInputException("The name of a game must be specified");	
		}
		Admin admin = (Admin) Block223Application.getCurrentUserRole();
		
		Block223 block223 = Block223Application.getBlock223();
		Game aGame = block223.findGame(name);
		
		if(aGame==null) {
	return;
		}
		if(aGame.isPublished()) {
			throw new InvalidInputException("A published game cannot be deleted.");
		}
		if(!(aGame.getAdmin().equals(admin))) {
			throw new InvalidInputException("Only the admin who created the game can delete the game.");
		}
		
		if(aGame != null) {
			aGame.delete();
			Block223Persistence.save(block223);
		}
	}

	public static void selectGame(String name) throws InvalidInputException {
		Block223 block223 = Block223Application.getBlock223();
		
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to select a game.");
		}
		if(name == null){
			throw new InvalidInputException("A game name needs to be specified.");
		}
		Admin admin = (Admin) Block223Application.getCurrentUserRole();
		Game game = block223.findGame(name);
		if(game==null){
			throw new InvalidInputException("A game with name " + name + " does not exist.");
		}
		if(game.isPublished()) {
			throw new InvalidInputException("A published game cannot be changed.");
		}
		if(!(admin.equals(game.getAdmin()))){
        	throw new InvalidInputException("Only the admin who created the game can select the game.");
        }    
		Block223Application.setCurrentGame(game);
	}

	public static void updateGame(String name, int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
		
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to define game settings.");
		}
		
		Game game = Block223Application.getCurrentGame();
		if(game==null) {
			throw new InvalidInputException("A game must be selected to define game settings.");
		}
		Admin admin = (Admin) Block223Application.getCurrentUserRole();
		if(!(admin.equals(game.getAdmin()))){
        	throw new InvalidInputException("Only the admin who created the game can define its game settings.");
        }    
		if(name==null || name.equals("")){
		 	throw new InvalidInputException("The name of a game must be specified.");
		}

		String currentName = game.getName();
		Block223 block223 = Block223Application.getBlock223();
		if(!currentName.equals(name)) {
			Game result = block223.findGame(name);
			if(result!=null) {
				throw new InvalidInputException("The name of a game must be unique.");
			}else {
				game.setName(name);
			}
		}
		setGameDetails(nrLevels, nrBlocksPerLevel, minBallSpeedX, minBallSpeedY, ballSpeedIncreaseFactor,maxPaddleLength,minPaddleLength);
	}

	public static void addBlock(int red, int green, int blue, int points) throws InvalidInputException {
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to add a block.");
		}
		Game game = Block223Application.getCurrentGame();
		if(game==null) {
			throw new InvalidInputException("A game must be selected to add a block.");
		}
		Admin admin = (Admin) Block223Application.getCurrentUserRole();
		if(!(admin.equals(game.getAdmin()))){
        	throw new InvalidInputException("Only the admin who created the game can add a block.");
        }  
		List<Block> blocks = game.getBlocks();
		for(Block block: blocks) {
			if((block.getRed()==red) && (block.getGreen()==green) && (block.getBlue()==blue)) {
				throw new InvalidInputException("A block with the same color already exists for the game.");
			}
		}
		if(red < 0 || red > 255) {
			throw new InvalidInputException("Red must be between 0 and 255.");
		}
		if(green < 0 || green > 255) {
			throw new InvalidInputException("Green must be between 0 and 255.");
		}
		if(blue < 0 || blue > 255) {
			throw new InvalidInputException("Blue must be between 0 and 255.");
		}
		if(points < 1 || points > 1000) {
			throw new InvalidInputException("Points must be between 1 and 1000.");
		}
		game.addBlock(red,green,blue,points);
	}

	public static void deleteBlock(int id) throws InvalidInputException {

		if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to delete a block.");
		}
		Admin admin = (Admin) Block223Application.getCurrentUserRole(); 
		Game aGame= Block223Application.getCurrentGame();
		
		if(aGame==null){
			throw new InvalidInputException("A game must be selected to delete a block.");
		}
		
		if(!aGame.getAdmin().equals(admin)) {
			throw new InvalidInputException("Only the admin who created the game can delete a block.");
		}
		Block aBlock=aGame.findBlock(id);
		if(aBlock!=null) {
			aBlock.delete();
		}
		
	}
	

	public static void updateBlock(int id, int red, int green, int blue, int points) throws InvalidInputException {
		if(Block223Application.getCurrentUserRole() instanceof Admin==false) {
			throw new InvalidInputException("Admin privileges are required to update a block.");
		}
		
		Game game = Block223Application.getCurrentGame();
		
		if (game == null) {
			throw new InvalidInputException("A game must be selected to update a block.");
		}
		
		Admin admin = (Admin) Block223Application.getCurrentUserRole();
		
		if (!(game.getAdmin().equals(admin))) {
			throw new InvalidInputException("Only the admin who created the game can update a block.");
		}
		
		if(points < 1 || points > 1000) {
			throw new InvalidInputException("Points must be between 1 and 1000.");
		}
		Block block = game.findBlock(id);
		List<Block> blocks = game.getBlocks();
		for(Block block1: blocks) {
			if((block1.getRed()==red) && (block1.getGreen()==green) && (block1.getBlue()==blue) ) {
				throw new InvalidInputException("A block with the same color already exists for the game.");
			}
		}
			
		if(block == null) {
			throw new InvalidInputException("The block does not exist.");
		}
		
		if(red < 0 || red > 255) {
			throw new InvalidInputException("Red must be between 0 and 255.");
		}
		if(green < 0 || green > 255) {
			throw new InvalidInputException("Green must be between 0 and 255.");
		}
		if(blue < 0 || blue > 255) {
			throw new InvalidInputException("Blue must be between 0 and 255.");
		}
		
		
		block.setRed(red);
		block.setGreen(green);
		block.setBlue(blue);
		block.setPoints(points);
		
	}
	public static void positionBlock(int id, int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
		
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to position a block.");
		}
		
		
		Block223 block223 = Block223Application.getBlock223();
		Admin admin = (Admin) Block223Application.getCurrentUserRole(); 
		Game aGame= Block223Application.getCurrentGame();
		
		if(aGame==null){
			throw new InvalidInputException("A game must be selected to position a block.");
		}
		if(level < 1 || level > aGame.getLevels().size()) {
			throw new InvalidInputException("Level "+ level + " does not exist for the game.");
		}
		if(!aGame.getAdmin().equals(admin)) {
			throw new InvalidInputException("Only the admin who created the game can position a block.");
		}
		Level alevel= aGame.getLevel(level-1);

		if(aGame.numberOfBlocks()==aGame.getNrBlocksPerLevel()) {
			throw new InvalidInputException("The number of blocks has reached the maximum number ("  + aGame.getNrBlocksPerLevel() + ") allowed for this game.");
		}
	    for(int index =0; index<alevel.getBlockAssignments().size();index++) {
		    if(gridHorizontalPosition==alevel.getBlockAssignment(index).getGridHorizontalPosition() && gridVerticalPosition==alevel.getBlockAssignment(index).getGridVerticalPosition()) {
		    	throw new InvalidInputException("A block already exists at location "  + gridHorizontalPosition + "/" + gridVerticalPosition + ".");
		         }
		}
  
	    Block aBlock=aGame.findBlock(id);
		if(aBlock==null) {
			throw new InvalidInputException("The block does not exist.");
		}
		
		if(gridHorizontalPosition<=0 || gridHorizontalPosition>15) {
			throw new InvalidInputException("The horizontal position must be between 1 and 15.");
		}
		if(gridVerticalPosition<=0 || gridVerticalPosition>15) {
			throw new InvalidInputException("The vertical position must be between 1 and 15.");
			}
		BlockAssignment aBlockassignment = new BlockAssignment(gridHorizontalPosition, gridVerticalPosition, alevel, aBlock, aGame);
			
	}

	public static void moveBlock(int level, int oldGridHorizontalPosition, int oldGridVerticalPosition,
			int newGridHorizontalPosition, int newGridVerticalPosition) throws InvalidInputException {
	
		Game currentGame = Block223Application.getCurrentGame(); 
		UserRole currentUserRole = Block223Application.getCurrentUserRole();
		
		if (!(currentUserRole instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to move a block.");
		}
		if (currentGame == null) {
			throw new InvalidInputException("A game must be selected to move a block.");
		}
		if (!(currentGame.getAdmin().equals(currentUserRole))) {
			throw new InvalidInputException("Only the admin who created the game can move a block.");
		}
		
		Level foundLevel;
		try {
			foundLevel = currentGame.getLevel(level-1);
		} catch (IndexOutOfBoundsException e) {
			throw new InvalidInputException("Level " + level + " does not exist for the game.");
		}
		
		BlockAssignment foundAssignment = foundLevel.findBlockAssignment(oldGridHorizontalPosition, oldGridVerticalPosition);
		if (foundAssignment == null) {
			throw new InvalidInputException("A block does not exist at location " + oldGridHorizontalPosition + "/" + oldGridVerticalPosition + ".");
		}
		
		if (foundLevel.findBlockAssignment(newGridHorizontalPosition, newGridVerticalPosition) != null) {
			throw new InvalidInputException("A block already exists at location " + newGridHorizontalPosition + "/" + newGridVerticalPosition + ".");
		}
		
		int maxNumberOfHorizontalBlocks = (Game.PLAY_AREA_SIDE - Game.WALL_PADDING * 2 + Game.COLUMNS_PADDING) / (Game.COLUMNS_PADDING + Block.SIZE);
		int maxNumberOfVerticalBlocks = (Game.PLAY_AREA_SIDE - Game.WALL_PADDING + Game.ROW_PADDING - Paddle.VERTICAL_DISTANCE - Paddle.PADDLE_WIDTH - Ball.BALL_DIAMETER) / (Game.ROW_PADDING + Block.SIZE);
		if (newGridHorizontalPosition < 1 || newGridHorizontalPosition > maxNumberOfHorizontalBlocks) {
			throw new InvalidInputException("The horizontal position must be between 1 and " + maxNumberOfHorizontalBlocks + ".");
		}	
		if (newGridVerticalPosition < 1 || newGridVerticalPosition > maxNumberOfVerticalBlocks) {
			throw new InvalidInputException("The vertical position must be between 1 and " + maxNumberOfVerticalBlocks + ".");
		}
		
		foundAssignment.setGridHorizontalPosition(newGridHorizontalPosition);
		foundAssignment.setGridVerticalPosition(newGridVerticalPosition);
	}

	public static void removeBlock(int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
		Game game = Block223Application.getCurrentGame();
			
		if( game == null){
			throw new InvalidInputException("A game must be selected to remove a block.");
		}
		if (Block223Application.getCurrentUserRole() instanceof Admin == false){
			throw new InvalidInputException("Admin privileges are required to remove a block.");	
		}	
		Admin admin = (Admin) Block223Application.getCurrentUserRole();
		if(!game.getAdmin().equals(admin)){
	        throw new InvalidInputException("Only the admin who created the game can remove a block.");
		}
		Level lvl = game.getLevel(level-1);
		
		BlockAssignment assignment = lvl.findBlockAssignment(gridHorizontalPosition, gridVerticalPosition);
		
		if (assignment != null){
			assignment.delete();
		}
	}

	public static void saveGame() throws InvalidInputException {
		 Game game = Block223Application.getCurrentGame();
		 if(game == null) {
	        	throw new InvalidInputException("A game must be selected to save it.");
	        }
	        
	        if(!(Block223Application.getCurrentUserRole() instanceof Admin)){
				throw new InvalidInputException("Admin privileges are required to save a game.");
			}
	        
	        Admin admin = (Admin) Block223Application.getCurrentUserRole();
	        if(!(admin.equals(game.getAdmin()))){
	        	throw new InvalidInputException("Only the admin who created the game can save it.");
	        }
		Block223 block223 = Block223Application.getBlock223();
		try {
		Block223Persistence.save(block223);
		} catch(RuntimeException e) {
			throw new RuntimeException(e);
		}
	}

	public static void register(String username, String playerPassword, String adminPassword) //FEATURE 12
			throws InvalidInputException {
		
		if (Block223Application.getCurrentUserRole() != null) { 
			throw new InvalidInputException("Cannot register a new user while a user is logged in.");
		}

		if (playerPassword == null || playerPassword.equals("")) {
			throw new InvalidInputException("The player password needs to be specified."); 
		}

		if (playerPassword.equals(adminPassword)) {
			throw new InvalidInputException("The passwords have to be different.");
		}
		
		if (username == null || username.equals("")) {
			throw new InvalidInputException("The username must be specified."); 
		}
		
		Block223 block223 = Block223Application.getBlock223(); 
		Player player = new Player(playerPassword, block223);
		
		User user;
		try {
			user = new User(username, block223, player);
		} catch (RuntimeException e) {
			player.delete();
			throw new InvalidInputException("The username has already been taken.");
		}
		
		if (adminPassword != null && adminPassword != "") {
			Admin admin = new Admin(adminPassword, block223);
			user.addRole(admin);
		}
		
		Block223Persistence.save(block223);
	}

	public static void login(String username, String password) throws InvalidInputException {
		Block223 block223 = Block223Application.resetBlock223();
		User user = User.getWithUsername(username);
		if (user == null) {
			throw new InvalidInputException("The username and password do not match.");	
		}
		List<UserRole> roles = user.getRoles();

		if (Block223Application.getCurrentUserRole() != null) {
			throw new InvalidInputException("Cannot login a new user while a user is already logged in.");
		}	
		if (username == null) {
			throw new InvalidInputException("The username and password do not match.");

		}
		for (UserRole role : roles) {
			String rolePassword = role.getPassword();
			if (rolePassword.equals(password)) {
				Block223Application.setCurrentUserRole(role);
				return;
			}
		}
		throw new InvalidInputException("The username and password do not match.");	
	}
	
	public static void logout() {
		Block223 block223 = Block223Application.getBlock223();
		Block223Application.setCurrentUserRole(null);
		Block223Persistence.save(block223);
	}
	// play mode

	public static void selectPlayableGame(String name, int id) throws InvalidInputException  {
			if(!(Block223Application.getCurrentUserRole() instanceof Player)) {
				throw new InvalidInputException("Player privileges are required to play a game.");
			}
			Block223 block223 = Block223Application.getBlock223();
			Game game = block223.findGame(name);
			PlayedGame pgame;
			Player player = (Player) Block223Application.getCurrentUserRole();
			if(game!=null) {
				String username = User.findUsername(player);
				//findUsername(...) needs to be implemented in the User class
				pgame = new PlayedGame(username,game,block223);
				pgame.setPlayer(player);
			}else {
				 pgame = block223.findPlayableGame(id);
				 if(pgame == null) {
						throw new InvalidInputException("The game does not exist.");
				 }
				 if(!pgame.getPlayer().equals(player)) {
					 throw new InvalidInputException("Only the player that started a game can continue the game.");
				 }
				//findPlayableGame needs to be implemented	
			}
			Block223Application.setCurrentPlayableGame(pgame);
		}

	public static void startGame(Block223PlayModeInterface ui) throws InvalidInputException {
			PlayedGame game = Block223Application.getCurrentPlayableGame();
			if(game==null) {
				throw new InvalidInputException("A game must be selected to play it.");
			}
			if((Block223Application.getCurrentUserRole() instanceof Admin) && game.getPlayer()!=null ) {
				throw new InvalidInputException("Player privileges are required to play a game.");
			}
			if((Block223Application.getCurrentUserRole() instanceof Admin) && !game.getGame().getAdmin().equals(Block223Application.getCurrentUserRole())){
				throw new InvalidInputException("Only the admin of a game can test the game.");
			}
			
			if((Block223Application.getCurrentUserRole() instanceof Player) && game.getPlayer()==null) {
				throw new InvalidInputException("Admin privileges are required to test a game.");
			}
			if((Block223Application.getCurrentUserRole() ==null)) {
				throw new InvalidInputException("Player privileges are required to play a game.");
			}
			//need to add InvalidInputException
			game.play();
			ui.takeInputs();
			while(game.getPlayStatus()==PlayStatus.Moving) {
				String userInputs = ui.takeInputs();
				updatePaddlePosition(userInputs);
				//needs to be implemented
				game.move();	
				
				if(userInputs.contains(" ")) {
					game.pause();
				}
				
				try {
					TimeUnit.MILLISECONDS.sleep((long) game.getWaitTime());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ui.refresh();	
			}
			if(game.getPlayStatus()==PlayStatus.GameOver) {
				Block223Application.setCurrentPlayableGame(null);
			}else if(game.getPlayer()!=null){
				Block223 block223 = Block223Application.getBlock223();
				Block223Persistence.save(block223);
			}
	}
	private static void updatePaddlePosition(String userInputs) {
		PlayedGame game = Block223Application.getCurrentPlayableGame();
		for(int i = 0; i<userInputs.length(); i++) {
			if(userInputs.charAt(i)==' ') {
				break;
			}
			if(userInputs.charAt(i)=='l' && (game.getCurrentPaddleX()>1)) {
			 Boolean ismoved= game.setCurrentPaddleX(game.getCurrentPaddleX()+PlayedGame.PADDLE_MOVE_LEFT); 
			}
            if(userInputs.charAt(i)=='r' && (game.getCurrentPaddleX()<(Game.PLAY_AREA_SIDE-game.getCurrentPaddleLength()))) 
			{
			 Boolean ismoved = game.setCurrentPaddleX(game.getCurrentPaddleX()+PlayedGame.PADDLE_MOVE_RIGHT); 

			}
		}
	}
	public void movePaddle(String queueInput) throws InvalidInputException {	
		PlayedGame game = Block223Application.getCurrentPlayableGame();
		for(int i = 0; i<queueInput.length(); i++) {
			if(queueInput.charAt(i)==' ') {
				break;
			}
			if(queueInput.charAt(i)=='l' && (game.getCurrentPaddleX()>0)) {
			 game.setCurrentPaddleX(game.getCurrentPaddleX()+PlayedGame.PADDLE_MOVE_LEFT); 
			}
			if(queueInput.charAt(i)=='r' && (game.getCurrentPaddleX()<(Game.PLAY_AREA_SIDE-game.getCurrentPaddleLength()))) 
			{
			 game.setCurrentPaddleX(game.getCurrentPaddleX()+PlayedGame.PADDLE_MOVE_RIGHT); 
			}else if(queueInput.charAt(i)=='r' && !(game.getCurrentPaddleX()<(Game.PLAY_AREA_SIDE-game.getCurrentPaddleLength()))) {
				throw new InvalidInputException("Cannot move paddle right.");
			}else if(queueInput.charAt(i)=='r' && !(game.getCurrentPaddleX()>0)) {
				throw new InvalidInputException("Cannot move paddle left.");
			}
		}	
	}

	public static void testGame(Block223PlayModeInterface ui) throws InvalidInputException {
	    UserRole admin = Block223Application.getCurrentUserRole();
	    if (!(admin instanceof Admin)) throw new InvalidInputException("Admin privileges are required to test a game.");

	    Game game = Block223Application.getCurrentGame();
	    if (game == null) throw new InvalidInputException("A game must be selected to test it.");
	    if (!game.getAdmin().equals(admin)) throw new InvalidInputException("Only the admin who created the game can test it.");

	    String username = User.findUsername(admin);
	    Block223 block223 = Block223Application.getBlock223();
	    PlayedGame pgame = new PlayedGame(username, game, block223);
	    pgame.setPlayer(null);
	    Block223Application.setCurrentPlayableGame(pgame);
	    startGame(ui);
	}
	public static void publishGame () throws InvalidInputException {
	    if (!(Block223Application.getCurrentUserRole() instanceof Admin)) throw new InvalidInputException("Admin privileges are required to publish a game.");
	    Game game = Block223Application.getCurrentGame();
	    if (game == null) throw new InvalidInputException("A game must be selected to publish it.");
	    if (!(game.getAdmin().equals(Block223Application.getCurrentUserRole()))) throw new InvalidInputException("Only the admin who created the game can publish it.");
	    if (game.numberOfBlocks() < 1) throw new InvalidInputException("At least one block must be defined for a game to be published.");
	    game.setPublished(true);
	}
	
	// **********
	// Query methods
	// **********
	public static List<TOGame> getDesignableGames() throws InvalidInputException {
		Block223 block223 = Block223Application.getBlock223();
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)){
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}
		Admin admin = (Admin) Block223Application.getCurrentUserRole();
		
		List<TOGame> result = new ArrayList<TOGame>();
	
		List<Game> games = block223.getGames();
		for(Game game: games) {
			Admin gameAdmin = game.getAdmin();
			if(gameAdmin.equals(admin) && !game.isPublished()) {
				TOGame to = new TOGame(game.getName(),game.getLevels().size(),game.getNrBlocksPerLevel(),game.getBall().getMinBallSpeedX(),game.getBall().getMinBallSpeedY(),game.getBall().getBallSpeedIncreaseFactor(),game.getPaddle().getMaxPaddleLength(),game.getPaddle().getMinPaddleLength());
				result.add(to);
			}
		}
		return result;
	}

	public static TOGame getCurrentDesignableGame() throws InvalidInputException {
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)){
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}
		Game game = Block223Application.getCurrentGame();
		if(game==null) {
			throw new InvalidInputException("A game must be selected to access its information.");
		}
		Admin admin = (Admin) Block223Application.getCurrentUserRole();
		if(!(game.getAdmin().equals(admin))) {
			throw new InvalidInputException("Only the admin who created the game can access its information.");
		}
		TOGame to = new TOGame(game.getName(),game.getLevels().size(),game.getNrBlocksPerLevel(),game.getBall().getMinBallSpeedX(),
				game.getBall().getMinBallSpeedY(),game.getBall().getBallSpeedIncreaseFactor(),game.getPaddle().getMaxPaddleLength(),
				game.getPaddle().getMinPaddleLength());
		return to;
	}

	public static List<TOBlock> getBlocksOfCurrentDesignableGame() throws InvalidInputException {
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)){
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}
		Game game = Block223Application.getCurrentGame();
		if(game==null) {
			throw new InvalidInputException("A game must be selected to access its information.");
		}
		Admin admin = (Admin) Block223Application.getCurrentUserRole();
		if(!(game.getAdmin().equals(admin))) {
			throw new InvalidInputException("Only the admin who created the game can access its information.");
		}
		List<TOBlock> result = new ArrayList<TOBlock>();
		List<Block> blocks = game.getBlocks();
		
		for(Block block: blocks) {
			TOBlock to = new TOBlock(block.getId(),block.getRed(),block.getGreen(),block.getBlue(),block.getPoints());
			result.add(to);
		}
		return result;
	}

	public static TOBlock getBlockOfCurrentDesignableGame(int id) throws InvalidInputException {
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)){
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}
		Game game = Block223Application.getCurrentGame();
		if(game==null) {
			throw new InvalidInputException("A game must be selected to access its information.");
		}
		Admin admin = (Admin) Block223Application.getCurrentUserRole();
		if(!(game.getAdmin().equals(admin))) {
			throw new InvalidInputException("Only the admin who created the game can access its information.");
		}
		Block block = game.findBlock(id);//find block implemented by talha
		if(block==null) {
			throw new InvalidInputException("The block does not exist.");
		}
		TOBlock to = new TOBlock(block.getId(),block.getRed(),block.getGreen(),block.getBlue(),block.getPoints());
		return to;
	}

	public static List<TOGridCell> getBlocksAtLevelOfCurrentDesignableGame(int level) throws InvalidInputException {
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)){
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}
		
		Game game = Block223Application.getCurrentGame();
		if(game==null) {
			throw new InvalidInputException("A game must be selected to access its information.");
		}
		if(level< Game.MIN_NR_LEVELS || level > game.getLevels().size()) {
			throw new InvalidInputException("Level "+ level + " does not exist for the game.");

		}
		Admin admin = (Admin) Block223Application.getCurrentUserRole();
		if(!(game.getAdmin().equals(admin))) {
			throw new InvalidInputException("Only the admin who created the game can access its information.");
		}
		
		List<TOGridCell> result = new ArrayList<TOGridCell>();
		Level lvl = game.getLevel(level-1);
		
		List<BlockAssignment> assignments = lvl.getBlockAssignments();
		for(BlockAssignment assignment: assignments) {
			TOGridCell to = new TOGridCell(assignment.getGridHorizontalPosition(),assignment.getGridVerticalPosition(),assignment.getBlock().getId(),assignment.getBlock().getRed(),assignment.getBlock().getGreen(),assignment.getBlock().getBlue(),assignment.getBlock().getPoints());
			result.add(to);
		}
		return result;	}

	public static TOUserMode getUserMode() {

		UserRole userRole = Block223Application.getCurrentUserRole();
		
		if (userRole == null) {
			return new TOUserMode(Mode.None);
		}
		
		if (userRole instanceof Player) {
			return new TOUserMode(Mode.Play);
		}
		
		else {
			return new TOUserMode(Mode.Design);
		}
	}

	// play mode

	public static List<TOPlayableGame> getPlayableGames() throws InvalidInputException {
		Block223 block223 = Block223Application.getBlock223();
		if(!(Block223Application.getCurrentUserRole() instanceof Player)){
			throw new InvalidInputException("Player privileges are required to play a game.");
		}
		Player player = (Player) Block223Application.getCurrentUserRole();
		
		List<TOPlayableGame> result = new ArrayList<TOPlayableGame>();
		List<Game> games = block223.getGames();
		
		for(Game game: games) {
			Boolean published = game.isPublished();
			if(published) {
				TOPlayableGame to = new TOPlayableGame(game.getName(), -1, 0);
				result.add(to);
			}
		}
		List<PlayedGame> playedGames = player.getPlayedGames();
		for(PlayedGame playedGame : playedGames) {
			TOPlayableGame to = new TOPlayableGame(playedGame.getGame().getName(), playedGame.getId(), playedGame.getCurrentLevel());
			result.add(to);
		}
		
		return result;
	}

	public static TOCurrentlyPlayedGame getCurrentPlayableGame() throws InvalidInputException {
		if(Block223Application.getCurrentUserRole()==null){
			throw new InvalidInputException("Player privileges are required to play a game.");
		}
		PlayedGame pgame = Block223Application.getCurrentPlayableGame();
		if(pgame==null) {
			throw new InvalidInputException("A game must be selected to play it.");
		}
		if((Block223Application.getCurrentUserRole() instanceof Admin)&&(pgame.getPlayer()!=null)){
			throw new InvalidInputException("Player privileges are required to play a game.");
		}
		if((Block223Application.getCurrentUserRole() instanceof Admin) && !pgame.getGame().getAdmin().equals(Block223Application.getCurrentUserRole())){
			throw new InvalidInputException("Only the admin of a game can test the game.");
		}
		if((Block223Application.getCurrentUserRole() instanceof Player) && (pgame.getPlayer()==null)){
			throw new InvalidInputException("Admin privileges are required to test a game.");

		}
		Boolean paused = pgame.getPlayStatus() == PlayStatus.Ready || pgame.getPlayStatus() == PlayStatus.Paused ;
		TOCurrentlyPlayedGame result = new TOCurrentlyPlayedGame(pgame.getGame().getName(), paused, pgame.getScore(), pgame.getLives(), pgame.getCurrentLevel(), pgame.getPlayername(), pgame.getCurrentBallX(), pgame.getCurrentBallY(), pgame.getCurrentPaddleLength(), pgame.getCurrentPaddleX());
	    List<PlayedBlockAssignment> blocks =  pgame.getBlocks();
	    for(PlayedBlockAssignment pblock : blocks) {
	    	  TOCurrentBlock to = new TOCurrentBlock(pblock.getBlock().getRed(), pblock.getBlock().getGreen(), pblock.getBlock().getBlue(), pblock.getBlock().getPoints(), pblock.getX(), pblock.getY(), result);
	    }
	    return result;
	}

	public static TOHallOfFame getHallOfFame(int start, int end) throws InvalidInputException {
		if(!(Block223Application.getCurrentUserRole() instanceof Player)){
			throw new InvalidInputException("Player privileges are required to access a game's hall of fame.");
		}
		PlayedGame pgame = Block223Application.getCurrentPlayableGame();
		if(pgame==null) {
			throw new InvalidInputException("A game must be selected to view its hall of fame.");
		}
		Game game = pgame.getGame();
		TOHallOfFame result = new TOHallOfFame(game.getName());
		if(start<1) {
			start=1;
		}
		if(end>game.numberOfHallOfFameEntries()) {
			end = game.numberOfHallOfFameEntries();
		}
		start=game.numberOfHallOfFameEntries()-start;
		end= game.numberOfHallOfFameEntries()-end;
		for(int i = start; i >= end ; i--){
			TOHallOfFameEntry to = new TOHallOfFameEntry(i+1, game.getHallOfFameEntry(i).getPlayername(), game.getHallOfFameEntry(i).getScore(), result);
		}
		return result;
	}

	public static TOHallOfFame getHallOfFameWithMostRecentEntry(int numberOfEntries) throws InvalidInputException {
		if(!(Block223Application.getCurrentUserRole() instanceof Player)){
			throw new InvalidInputException("Player privileges are required to access a game's hall of fame.");
		}
		PlayedGame pgame = Block223Application.getCurrentPlayableGame();
		if(pgame==null) {
			throw new InvalidInputException("A game must be selected to view its hall of fame.");
		}
		Game game = pgame.getGame();
		TOHallOfFame result = new TOHallOfFame(game.getName());
		HallOfFameEntry mostRecent = game.getMostRecentEntry();
		int index = game.indexOfHallOfFameEntry(mostRecent);
		int start = index + numberOfEntries/2;
		if(start > game.numberOfHallOfFameEntries()-1) {
			start=game.numberOfHallOfFameEntries()-1;
		}
		int end = start-numberOfEntries+1;
		if(end<0) {
			end = 0;
		}
		for(int i = start; i >= end ; i--){
			TOHallOfFameEntry to = new TOHallOfFameEntry(i+1, game.getHallOfFameEntry(i).getPlayername(), game.getHallOfFameEntry(i).getScore(), result);
		}
		return result;
	}	
	
	// Query methods for constants
	
	public static int getBlockSize() {
		return Block.SIZE;
	}
	
	public static int getPaddleWidth() {
		return Paddle.PADDLE_WIDTH;
	}
	
	public static int getBallDiameter() {
		return Ball.BALL_DIAMETER;
	}
	
	public static double getBallSpeedX() {
		return Block223Application.getCurrentPlayableGame().getBallDirectionX();
	}
	
	public static double getBallSpeedY() {
		return Block223Application.getCurrentPlayableGame().getBallDirectionY();
	}
	public static void resetBallSpeed() {
		Block223Application.getCurrentPlayableGame().resetBallDirectionX();
		Block223Application.getCurrentPlayableGame().resetBallDirectionY();
	}
	public static void outOfPlayAreaX390() {
		Block223Application.getCurrentPlayableGame().setCurrentBallX(Game.PLAY_AREA_SIDE - Ball.BALL_DIAMETER);
	}
	public static void outOfPlayAreaX0() {
		Block223Application.getCurrentPlayableGame().setCurrentBallX(Ball.BALL_DIAMETER);
	}
	public static void outOfPlayAreaY390() {
		Block223Application.getCurrentPlayableGame().setCurrentBallY(Game.PLAY_AREA_SIDE - Ball.BALL_DIAMETER);
	}
	public static void outOfPlayAreaY0() {
		Block223Application.getCurrentPlayableGame().setCurrentBallY(Ball.BALL_DIAMETER);
	}
	
	public static TOHallOfFame getHallOfFameWithName(String name, int start, int end) throws InvalidInputException {
		if(!(Block223Application.getCurrentUserRole() instanceof Player)){
			throw new InvalidInputException("Player privileges are required to access a game's hall of fame.");
		}
		Block223 block223 = Block223Application.getBlock223();
		PlayedGame pgame = block223.findPlayableGame(name);
		if(pgame==null) {
			throw new InvalidInputException("A game must be selected to view its hall of fame.");
		}
		Game game = pgame.getGame();
		TOHallOfFame result = new TOHallOfFame(game.getName());
		if(start<1) {
			start=1;
		}
		if(end>game.numberOfHallOfFameEntries()) {
			end = game.numberOfHallOfFameEntries();
		}
		start=game.numberOfHallOfFameEntries()-start;
		end= game.numberOfHallOfFameEntries()-end;
		int index=0;
		for(int i = start; i >= end ; i--){
			TOHallOfFameEntry to = new TOHallOfFameEntry(i+1, game.getHallOfFameEntry(i).getPlayername(), game.getHallOfFameEntry(i).getScore(), result);
			index++;
		}
		return result;
	}
	public static TOHallOfFame getHallOfFameWithMostRecentEntry(int numberOfEntries, String name) throws InvalidInputException {
		if(!(Block223Application.getCurrentUserRole() instanceof Player)){
			throw new InvalidInputException("Player privileges are required to access a game's hall of fame.");
		}
		PlayedGame pgame = Block223Application.getCurrentPlayableGame();
		if(pgame==null) {
			Block223 block223 = Block223Application.getBlock223();
			pgame = block223.findPlayableGame(name);
		}
		Game game = pgame.getGame();
		TOHallOfFame result = new TOHallOfFame(game.getName());
		HallOfFameEntry mostRecent = game.getMostRecentEntry();
		int index = game.indexOfHallOfFameEntry(mostRecent);
		int start = index + numberOfEntries/2;
		if(start > game.numberOfHallOfFameEntries()-1) {
			start=game.numberOfHallOfFameEntries()-1;
		}
		int end = start-numberOfEntries+1;
		if(end<0) {
			end = 0;
		}
		for(int i = start; i >= end ; i--){
			TOHallOfFameEntry to = new TOHallOfFameEntry(i+1, game.getHallOfFameEntry(i).getPlayername(), game.getHallOfFameEntry(i).getScore(), result);
		}
		return result;
		}
	
	
	public static boolean isPlayer () {
		return (Block223Application.getCurrentUserRole() instanceof Player);	
	}
	
	public static boolean isAdmin () {
		return (Block223Application.getCurrentUserRole() instanceof Admin);	
	}
	
}
