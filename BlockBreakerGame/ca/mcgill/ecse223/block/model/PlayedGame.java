/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/
package ca.mcgill.ecse223.block.model;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.*;
import math.geom2d.conic.CircleArc2D;
import ca.mcgill.ecse223.block.model.BouncePoint.BounceDirection;
import java.util.*;

// line 11 "../../../../../Block223PlayMode.ump"
// line 104 "../../../../../Block223Persistence.ump"
// line 1 "../../../../../Block223States.ump"
public class PlayedGame implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------


  /**
   * at design time, the initial wait time may be adjusted as seen fit
   */
  public static final int INITIAL_WAIT_TIME = 50;
  private static int nextId = 1;
  public static final int NR_LIVES = 3;

  /**
   * the PlayedBall and PlayedPaddle are not in a separate class to avoid the bug in Umple that occurred for the second constructor of Game
   * no direct link to Ball, because the ball can be found by navigating to Game and then Ball
   */
  public static final int BALL_INITIAL_X = Game.PLAY_AREA_SIDE / 2;
  public static final int BALL_INITIAL_Y = Game.PLAY_AREA_SIDE / 2;

  /**
   * no direct link to Paddle, because the paddle can be found by navigating to Game and then Paddle
   * pixels moved when right arrow key is pressed
   */
  public static final int PADDLE_MOVE_RIGHT = 5;

  /**
   * pixels moved when left arrow key is pressed
   */
  public static final int PADDLE_MOVE_LEFT = -5;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PlayedGame Attributes
  private int score;
  private int lives;
  private int currentLevel;
  private double waitTime;
  private String playername;
  private double ballDirectionX;
  private double ballDirectionY;
  private double currentBallX;
  private double currentBallY;
  private double currentPaddleLength;
  private double currentPaddleX;
  private double currentPaddleY;

  //Autounique Attributes
  private int id;

  //PlayedGame State Machines
  public enum PlayStatus { Ready, Moving, Paused, GameOver }
  private PlayStatus playStatus;

  //PlayedGame Associations
  private Player player;
  private Game game;
  private List<PlayedBlockAssignment> blocks;
  private BouncePoint bounce;
  private Block223 block223;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PlayedGame(String aPlayername, Game aGame, Block223 aBlock223)
  {
    // line 47 "../../../../../Block223PlayMode.ump"
    boolean didAddGameResult = setGame(aGame);
          if (!didAddGameResult)
          {
             throw new RuntimeException("Unable to create playedGame due to game");
          }
    // END OF UMPLE BEFORE INJECTION
    score = 0;
    lives = NR_LIVES;
    currentLevel = 1;
    waitTime = INITIAL_WAIT_TIME;
    playername = aPlayername;
    resetBallDirectionX();
    resetBallDirectionY();
    resetCurrentBallX();
    resetCurrentBallY();
    currentPaddleLength = getGame().getPaddle().getMaxPaddleLength();
    resetCurrentPaddleX();
    currentPaddleY = Game.PLAY_AREA_SIDE - Paddle.VERTICAL_DISTANCE - Paddle.PADDLE_WIDTH;
    id = nextId++;
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create playedGame due to game");
    }
    blocks = new ArrayList<PlayedBlockAssignment>();
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create playedGame due to block223");
    }
    setPlayStatus(PlayStatus.Ready);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setScore(int aScore)
  {
    boolean wasSet = false;
    score = aScore;
    wasSet = true;
    return wasSet;
  }

  public boolean setLives(int aLives)
  {
    boolean wasSet = false;
    lives = aLives;
    wasSet = true;
    return wasSet;
  }

  public boolean setCurrentLevel(int aCurrentLevel)
  {
    boolean wasSet = false;
    currentLevel = aCurrentLevel;
    wasSet = true;
    return wasSet;
  }

  public boolean setWaitTime(double aWaitTime)
  {
    boolean wasSet = false;
    waitTime = aWaitTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setPlayername(String aPlayername)
  {
    boolean wasSet = false;
    playername = aPlayername;
    wasSet = true;
    return wasSet;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setBallDirectionX(double aBallDirectionX)
  {
    boolean wasSet = false;
    ballDirectionX = aBallDirectionX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetBallDirectionX()
  {
    boolean wasReset = false;
    ballDirectionX = getDefaultBallDirectionX();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setBallDirectionY(double aBallDirectionY)
  {
    boolean wasSet = false;
    ballDirectionY = aBallDirectionY;
    wasSet = true;
    return wasSet;
  }

  public boolean resetBallDirectionY()
  {
    boolean wasReset = false;
    ballDirectionY = getDefaultBallDirectionY();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setCurrentBallX(double aCurrentBallX)
  {
    boolean wasSet = false;
    currentBallX = aCurrentBallX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetCurrentBallX()
  {
    boolean wasReset = false;
    currentBallX = getDefaultCurrentBallX();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setCurrentBallY(double aCurrentBallY)
  {
    boolean wasSet = false;
    currentBallY = aCurrentBallY;
    wasSet = true;
    return wasSet;
  }

  public boolean resetCurrentBallY()
  {
    boolean wasReset = false;
    currentBallY = getDefaultCurrentBallY();
    wasReset = true;
    return wasReset;
  }

  public boolean setCurrentPaddleLength(double aCurrentPaddleLength)
  {
    boolean wasSet = false;
    currentPaddleLength = aCurrentPaddleLength;
    wasSet = true;
    return wasSet;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setCurrentPaddleX(double aCurrentPaddleX)
  {
    boolean wasSet = false;
    currentPaddleX = aCurrentPaddleX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetCurrentPaddleX()
  {
    boolean wasReset = false;
    currentPaddleX = getDefaultCurrentPaddleX();
    wasReset = true;
    return wasReset;
  }

  public int getScore()
  {
    return score;
  }

  public int getLives()
  {
    return lives;
  }

  public int getCurrentLevel()
  {
    return currentLevel;
  }

  public double getWaitTime()
  {
    return waitTime;
  }

  /**
   * added here so that it only needs to be determined once
   */
  public String getPlayername()
  {
    return playername;
  }

  /**
   * 0/0 is the top left corner of the play area, i.e., a directionX/Y of 0/1 moves the ball down in a straight line
   */
  public double getBallDirectionX()
  {
    return ballDirectionX;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultBallDirectionX()
  {
    return getGame().getBall().getMinBallSpeedX();
  }

  public double getBallDirectionY()
  {
    return ballDirectionY;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultBallDirectionY()
  {
    return getGame().getBall().getMinBallSpeedY();
  }

  /**
   * the position of the ball is at the center of the ball
   */
  public double getCurrentBallX()
  {
    return currentBallX;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultCurrentBallX()
  {
    return BALL_INITIAL_X;
  }

  public double getCurrentBallY()
  {
    return currentBallY;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultCurrentBallY()
  {
    return BALL_INITIAL_Y;
  }

  public double getCurrentPaddleLength()
  {
    return currentPaddleLength;
  }

  /**
   * the position of the paddle is at its top right corner
   */
  public double getCurrentPaddleX()
  {
    return currentPaddleX;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultCurrentPaddleX()
  {
    return (Game.PLAY_AREA_SIDE - currentPaddleLength) / 2;
  }

  public double getCurrentPaddleY()
  {
    return currentPaddleY;
  }

  public int getId()
  {
    return id;
  }

  public String getPlayStatusFullName()
  {
    String answer = playStatus.toString();
    return answer;
  }

  public PlayStatus getPlayStatus()
  {
    return playStatus;
  }

  public boolean play()
  {
    boolean wasEventProcessed = false;
    
    PlayStatus aPlayStatus = playStatus;
    switch (aPlayStatus)
    {
      case Ready:
        setPlayStatus(PlayStatus.Moving);
        wasEventProcessed = true;
        break;
      case Paused:
        setPlayStatus(PlayStatus.Moving);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean pause()
  {
    boolean wasEventProcessed = false;
    
    PlayStatus aPlayStatus = playStatus;
    switch (aPlayStatus)
    {
      case Moving:
        setPlayStatus(PlayStatus.Paused);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean move()
  {
    boolean wasEventProcessed = false;
    
    PlayStatus aPlayStatus = playStatus;
    switch (aPlayStatus)
    {
      case Moving:
        if (hitPaddle())
        {
        // line 12 "../../../../../Block223States.ump"
          doHitPaddleOrWall();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBoundsAndLastLife())
        {
        // line 13 "../../../../../Block223States.ump"
          doOutOfBounds();
          setPlayStatus(PlayStatus.GameOver);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBounds())
        {
        // line 14 "../../../../../Block223States.ump"
          doOutOfBounds();
          setPlayStatus(PlayStatus.Paused);
          wasEventProcessed = true;
          break;
        }
        if (hitLastBlockAndLastLevel())
        {
        // line 15 "../../../../../Block223States.ump"
          doHitBlock();
          setPlayStatus(PlayStatus.GameOver);
          wasEventProcessed = true;
          break;
        }
        if (hitLastBlock())
        {
        // line 16 "../../../../../Block223States.ump"
          doHitBlockNextLevel();
          setPlayStatus(PlayStatus.Ready);
          wasEventProcessed = true;
          break;
        }
        if (hitBlock())
        {
        // line 17 "../../../../../Block223States.ump"
          doHitBlock();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        if (hitWall())
        {
        // line 18 "../../../../../Block223States.ump"
          doHitPaddleOrWall();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        // line 19 "../../../../../Block223States.ump"
        doHitNothingAndNotOutOfBounds();
        setPlayStatus(PlayStatus.Moving);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setPlayStatus(PlayStatus aPlayStatus)
  {
    playStatus = aPlayStatus;

    // entry actions and do activities
    switch(playStatus)
    {
      case Ready:
        // line 7 "../../../../../Block223States.ump"
        doSetup();
        break;
      case GameOver:
        // line 25 "../../../../../Block223States.ump"
        doGameOver();
        break;
    }
  }
  /* Code from template association_GetOne */
  public Player getPlayer()
  {
    return player;
  }

  public boolean hasPlayer()
  {
    boolean has = player != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_GetMany */
  public PlayedBlockAssignment getBlock(int index)
  {
    PlayedBlockAssignment aBlock = blocks.get(index);
    return aBlock;
  }

  public List<PlayedBlockAssignment> getBlocks()
  {
    List<PlayedBlockAssignment> newBlocks = Collections.unmodifiableList(blocks);
    return newBlocks;
  }

  public int numberOfBlocks()
  {
    int number = blocks.size();
    return number;
  }

  public boolean hasBlocks()
  {
    boolean has = blocks.size() > 0;
    return has;
  }

  public int indexOfBlock(PlayedBlockAssignment aBlock)
  {
    int index = blocks.indexOf(aBlock);
    return index;
  }
  /* Code from template association_GetOne */
  public BouncePoint getBounce()
  {
    return bounce;
  }

  public boolean hasBounce()
  {
    boolean has = bounce != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Block223 getBlock223()
  {
    return block223;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setPlayer(Player aPlayer)
  {
    boolean wasSet = false;
    Player existingPlayer = player;
    player = aPlayer;
    if (existingPlayer != null && !existingPlayer.equals(aPlayer))
    {
      existingPlayer.removePlayedGame(this);
    }
    if (aPlayer != null)
    {
      aPlayer.addPlayedGame(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGame(Game aGame)
  {
    boolean wasSet = false;
    if (aGame == null)
    {
      return wasSet;
    }

    Game existingGame = game;
    game = aGame;
    if (existingGame != null && !existingGame.equals(aGame))
    {
      existingGame.removePlayedGame(this);
    }
    game.addPlayedGame(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlocks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public PlayedBlockAssignment addBlock(int aX, int aY, Block aBlock)
  {
    return new PlayedBlockAssignment(aX, aY, aBlock, this);
  }

  public boolean addBlock(PlayedBlockAssignment aBlock)
  {
    boolean wasAdded = false;
    if (blocks.contains(aBlock)) { return false; }
    PlayedGame existingPlayedGame = aBlock.getPlayedGame();
    boolean isNewPlayedGame = existingPlayedGame != null && !this.equals(existingPlayedGame);
    if (isNewPlayedGame)
    {
      aBlock.setPlayedGame(this);
    }
    else
    {
      blocks.add(aBlock);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlock(PlayedBlockAssignment aBlock)
  {
    boolean wasRemoved = false;
    //Unable to remove aBlock, as it must always have a playedGame
    if (!this.equals(aBlock.getPlayedGame()))
    {
      blocks.remove(aBlock);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlockAt(PlayedBlockAssignment aBlock, int index)
  {  
    boolean wasAdded = false;
    if(addBlock(aBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlocks()) { index = numberOfBlocks() - 1; }
      blocks.remove(aBlock);
      blocks.add(index, aBlock);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBlockAt(PlayedBlockAssignment aBlock, int index)
  {
    boolean wasAdded = false;
    if(blocks.contains(aBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlocks()) { index = numberOfBlocks() - 1; }
      blocks.remove(aBlock);
      blocks.add(index, aBlock);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBlockAt(aBlock, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setBounce(BouncePoint aNewBounce)
  {
    boolean wasSet = false;
    bounce = aNewBounce;
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBlock223(Block223 aBlock223)
  {
    boolean wasSet = false;
    if (aBlock223 == null)
    {
      return wasSet;
    }

    Block223 existingBlock223 = block223;
    block223 = aBlock223;
    if (existingBlock223 != null && !existingBlock223.equals(aBlock223))
    {
      existingBlock223.removePlayedGame(this);
    }
    block223.addPlayedGame(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    if (player != null)
    {
      Player placeholderPlayer = player;
      this.player = null;
      placeholderPlayer.removePlayedGame(this);
    }
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removePlayedGame(this);
    }
    while (blocks.size() > 0)
    {
      PlayedBlockAssignment aBlock = blocks.get(blocks.size() - 1);
      aBlock.delete();
      blocks.remove(aBlock);
    }
    
    bounce = null;
    Block223 placeholderBlock223 = block223;
    this.block223 = null;
    if(placeholderBlock223 != null)
    {
      placeholderBlock223.removePlayedGame(this);
    }
  }

  // line 54 "../../../../../Block223PlayMode.ump"
   private Boolean isPositionFree(int x, int y){
    for(PlayedBlockAssignment pblock : blocks) {
				 if((x==pblock.getX()) && (y==pblock.getY())) {
					 return false;
				 }
			 }
		   return true;
  }


  /**
   * Guards
   */
  // line 32 "../../../../../Block223States.ump"
   private boolean hitPaddle(){
    BouncePoint bounceP=calculateBouncePointPaddle();
  setBounce(bounceP);
    return (bounceP!=null);
  }


  @SuppressWarnings("deprecation")
  // line 39 "../../../../../Block223States.ump"
   private BouncePoint calculateBouncePointPaddle(){
    double BallX = getCurrentBallX();
        double BallY = getCurrentBallY();
        double nextBallX = (BallX + getBallDirectionX());
        double nextBallY = (BallY + getBallDirectionY());
        Line2D ballTrajec = new Line2D.Double(BallX, BallY, nextBallX, nextBallY);     
        double ballRadius = Ball.BALL_DIAMETER / 2;
        
        Rectangle2D boxA = new Rectangle2D.Double(getCurrentPaddleX(), (getCurrentPaddleY() - ballRadius), getCurrentPaddleLength(), ballRadius);
        Rectangle2D boxB = new Rectangle2D.Double((getCurrentPaddleX() - ballRadius), getCurrentPaddleY(), ballRadius, Paddle.PADDLE_WIDTH);
        Rectangle2D boxC = new Rectangle2D.Double((getCurrentPaddleX() + getCurrentPaddleLength()), getCurrentPaddleY(), ballRadius, Paddle.PADDLE_WIDTH);
        Rectangle2D boxE = new Rectangle2D.Double((getCurrentPaddleX() - ballRadius), (getCurrentPaddleY() - ballRadius), ballRadius, ballRadius);
        Rectangle2D boxF = new Rectangle2D.Double((getCurrentPaddleX() + getCurrentPaddleLength()), (getCurrentPaddleY() - ballRadius), ballRadius, ballRadius);
         
        BouncePoint bounceP = null;
        
        if (boxA.intersectsLine(ballTrajec)) {
          if (BallY >= 350) {
            Line2D lineA = new Line2D.Double((getCurrentPaddleX() - getCurrentPaddleLength()), (getCurrentPaddleY() - ballRadius), getCurrentPaddleX(), (getCurrentPaddleY() - ballRadius));
            Point2D intersection = calculateIntersectPoint(ballTrajec, lineA);
            bounceP = new BouncePoint(intersection.getX(), intersection.getY(), BounceDirection.FLIP_Y);
          }
        }
        else if (boxB.intersectsLine(ballTrajec)) {
          if (BallX >= getCurrentPaddleX() - ballRadius) {
            Line2D lineB = new Line2D.Double((getCurrentPaddleX() - ballRadius), getCurrentPaddleY(), (getCurrentPaddleX() - ballRadius), (getCurrentPaddleY() + Paddle.PADDLE_WIDTH));
            Point2D intersection = calculateIntersectPoint(ballTrajec, lineB);
            bounceP = new BouncePoint(intersection.getX(), intersection.getY(), BounceDirection.FLIP_X);
          }
        }
        else if (boxC.intersectsLine(ballTrajec)) {
          if (BallX <= getCurrentPaddleX() + getCurrentPaddleLength() + ballRadius) {
            Line2D lineC = new Line2D.Double((getCurrentPaddleX() + getCurrentPaddleLength() + ballRadius), getCurrentPaddleY(), (getCurrentPaddleX() + getCurrentPaddleLength() + ballRadius), (getCurrentPaddleY() + Paddle.PADDLE_WIDTH));
            Point2D intersection = calculateIntersectPoint(ballTrajec, lineC);
            bounceP = new BouncePoint(intersection.getX(), intersection.getY(), BounceDirection.FLIP_X);
          }
        }
        else if (boxE.intersectsLine(ballTrajec)) {
          CircleArc2D arcE = new CircleArc2D(getCurrentPaddleX(), getCurrentPaddleY(), ballRadius, 89, 92);
          math.geom2d.line.Line2D traj1 = new math.geom2d.line.Line2D(BallX, BallY, nextBallX, nextBallY);
          double intX = 0;
          double intY = 0;
          Collection<math.geom2d.Point2D> intersects = arcE.intersections(traj1);
          if (intersects.size() != 0) {
            for (math.geom2d.Point2D point : intersects) {
              intX = point.getX();
              intY = point.getY();
            }
          }
          else{ 
            return null;}
          
          if (getBallDirectionX() < 0) {
            bounceP = new BouncePoint(intX, intY, BounceDirection.FLIP_Y);
          }
          else {
            bounceP = new BouncePoint(intX, intY, BounceDirection.FLIP_X);
          }
        }
        else if (boxF.intersectsLine(ballTrajec)) {
          CircleArc2D arcF = new CircleArc2D((getCurrentPaddleX() + getCurrentPaddleLength()), getCurrentPaddleY(), ballRadius, -1, 92);
          math.geom2d.line.Line2D traj2 = new math.geom2d.line.Line2D(BallX, BallY, nextBallX, nextBallY);
          double intX = 0;
          double intY = 0;
          Collection<math.geom2d.Point2D> intersects = arcF.intersections(traj2);
          if (intersects.size() != 0) {
            for (math.geom2d.Point2D point : intersects) {
              intX = point.getX();
              intY = point.getY();
            }
          }
          else { return null; }
          
          if (getBallDirectionX() < 0) {
            bounceP = new BouncePoint(intX, intY, BounceDirection.FLIP_X);
          }
          else {
            bounceP = new BouncePoint(intX, intY, BounceDirection.FLIP_Y);
          }
        }
      return bounceP;
  }

  // line 122 "../../../../../Block223States.ump"
   private boolean isOutOfBoundsAndLastLife(){
    boolean outOfBounds = false;
	   if(getLives()==1) {
		   outOfBounds = isBallOutOfBounds();
		   
	   }
    return outOfBounds;
  }

  // line 131 "../../../../../Block223States.ump"
   private boolean isBallOutOfBounds(){
    if(getCurrentBallY()>=((Game.PLAY_AREA_SIDE)-((Ball.BALL_DIAMETER)/2))) {
	    	return true;
	    }
	return false;
  }

  // line 138 "../../../../../Block223States.ump"
   private boolean isOutOfBounds(){
    boolean outOfBounds = isBallOutOfBounds();
	   return outOfBounds;
  }

  // line 145 "../../../../../Block223States.ump"
   private boolean hitLastBlockAndLastLevel(){
    Game game = getGame();
		int nrLevels = game.numberOfLevels();
		setBounce(null);
		   
		   if (nrLevels == currentLevel)
		   {
			   int nrBlocks = numberOfBlocks();
			   if(nrBlocks == 1)
			   {
			     PlayedBlockAssignment block = getBlock(0);
			      BouncePoint bp = calculateBouncePointBlock(block);
				
	// Have to correct the NullPointerException - Done
			
				if(bp!=null)
				{
					bp.setHitBlock(block); 
				}
				setBounce(bp);  
			   return (bp!=null);
			   }
		   }
		 return false;
  }

  // line 172 "../../../../../Block223States.ump"
   private boolean hitLastBlock(){
    int nrBlocks = numberOfBlocks();
	   setBounce(null);

	   if (nrBlocks == 1)
	   {
		   PlayedBlockAssignment block = getBlock(0);
		   BouncePoint bp = calculateBouncePointBlock(block);

		   if(bp!=null)
		   {
			   bp.setHitBlock(block);
		   }
		   setBounce(bp);
		   return (bp!=null);
	   }
	   return false;
  }

  // line 194 "../../../../../Block223States.ump"
   private boolean hitBlock(){
    int nrBlocks = numberOfBlocks();
    setBounce(null);
  
     for (int index = 0; index< nrBlocks-1; index++)
     {
       PlayedBlockAssignment block = getBlock(index);
       BouncePoint bp = calculateBouncePointBlock(block);
       bounce = getBounce();
       
       if(bp!=null)
       {
       bp.setHitBlock(block); // NullPointer correction!
       
      boolean closer = isCloser(bp, bounce);
     if (closer)
      {
         setBounce(bp); 
     }
     return (getBounce()!= null);
  }}
  return false;
  }

  // line 219 "../../../../../Block223States.ump"
   private boolean isCloser(BouncePoint first, BouncePoint second){
    if (second==null)
		{
		return true;
		}
		if(first == null)
		{
			return false;
		}
		double Dist_a =  Math.sqrt( ((first.getX() - getCurrentBallX())*(first.getX() - getCurrentBallX())) 
			+ ((first.getY() - getCurrentBallY())*(first.getY() - getCurrentBallY())));

	    double Dist_b =  Math.sqrt( ((second.getX() - getCurrentBallX())*(second.getX() - getCurrentBallX())) 
			+ ((second.getY() - getCurrentBallY())*(second.getY() - getCurrentBallY())));

	if (Dist_a<Dist_b)
	{
	 return true;
	}
	return false;
  }


  @SuppressWarnings("deprecation")
  // line 242 "../../../../../Block223States.ump"
   private BouncePoint calculateBouncePointBlock(PlayedBlockAssignment block){
    double BallX = getCurrentBallX();
   			   double BallY = getCurrentBallY();
   			   double nextBallX = (BallX + getBallDirectionX());
   			   double nextBallY = (BallY + getBallDirectionY());
   			   
   			   Line2D ballTrajectory = new Line2D.Double(BallX, BallY, nextBallX, nextBallY);
   			   
   				int ballRadius = Ball.BALL_DIAMETER / 2;
   				
   				Rectangle2D boxA = new Rectangle2D.Double(block.getX(), (block.getY() - ballRadius + 0.01), Block.SIZE, ballRadius);
   				Rectangle2D boxB = new Rectangle2D.Double((block.getX() - ballRadius + 0.01), block.getY(), ballRadius, Block.SIZE);
   				Rectangle2D boxC = new Rectangle2D.Double((block.getX() + Block.SIZE - 0.01), block.getY(), ballRadius, Block.SIZE);
   				Rectangle2D boxD = new Rectangle2D.Double(block.getX(), (block.getY() + Block.SIZE - 0.01), Block.SIZE, ballRadius);
   				Rectangle2D boxE = new Rectangle2D.Double((block.getX() - ballRadius), (block.getY() - ballRadius), ballRadius, ballRadius);
   				Rectangle2D boxF = new Rectangle2D.Double((block.getX() + Block.SIZE), (block.getY() - ballRadius), ballRadius, ballRadius);
   				Rectangle2D boxG = new Rectangle2D.Double((block.getX() - ballRadius), (block.getY() + Block.SIZE), ballRadius, ballRadius);
   				Rectangle2D boxH = new Rectangle2D.Double((block.getX() + Block.SIZE), (block.getY() + Block.SIZE), ballRadius, ballRadius);

   				BouncePoint bp = null;
   //BOX A:
   				
   				if (boxA.intersectsLine(ballTrajectory)) {
   					Line2D lineA = new Line2D.Double(block.getX(), (block.getY() - ballRadius), (block.getX() + Block.SIZE), (block.getY() - ballRadius));
   					Point2D intersection = calculateIntersectPoint(ballTrajectory, lineA);
                     bp = new BouncePoint(intersection.getX(), intersection.getY(), BounceDirection.FLIP_Y);
   				}
   //BOX B:
   				
   				else if (boxB.intersectsLine(ballTrajectory)) {
   					Line2D lineB = new Line2D.Double((block.getX() - ballRadius), block.getY(), (block.getX() - ballRadius), (block.getY() + Block.SIZE));
   					Point2D intersection = calculateIntersectPoint(ballTrajectory, lineB);
   					bp = new BouncePoint(intersection.getX(), intersection.getY(), BounceDirection.FLIP_X);
   				}
   //BOX C:
   				
   				else if (boxC.intersectsLine(ballTrajectory)) {
   					Line2D lineC = new Line2D.Double((block.getX() + Block.SIZE + ballRadius), block.getY(), (block.getX() + Block.SIZE + ballRadius), (block.getY() + Block.SIZE));
   					Point2D intersection = calculateIntersectPoint(ballTrajectory, lineC);
   					bp = new BouncePoint(intersection.getX(), intersection.getY(), BounceDirection.FLIP_X);
   				}
   //BOX D:
   								
   				else if (boxD.intersectsLine(ballTrajectory)) {
   					Line2D lineD = new Line2D.Double(block.getX(), (block.getY() + Block.SIZE + ballRadius), (block.getX() + Block.SIZE), (block.getY() + Block.SIZE + ballRadius));
   					Point2D intersection = calculateIntersectPoint(ballTrajectory, lineD);
   					bp = new BouncePoint(intersection.getX(), intersection.getY(), BounceDirection.FLIP_Y);
   				}
   //BOX E:
   				
   				else if (boxE.intersectsLine(ballTrajectory)) {
   					CircleArc2D arc = new CircleArc2D(block.getX(), block.getY(), ballRadius, 89, 92);

   					math.geom2d.line.Line2D traj = new math.geom2d.line.Line2D(BallX, BallY, nextBallX, nextBallY);
   					double intX = 0;
   					double intY = 0;
   					Collection<math.geom2d.Point2D> intersects = arc.intersections(traj);
   					if (intersects.size() != 0) {
   						for (math.geom2d.Point2D point : intersects) {
   							intX = point.getX(); // -- Reminder: Have to look into this
   							intY = point.getY();
   						}
   					}
   					
   					else { return null;
   					 }
   					
   					if (getBallDirectionX() < 0) {
   						bp = new BouncePoint(intX, intY, BounceDirection.FLIP_Y);
   					}
   					else {
   						bp = new BouncePoint(intX, intY, BounceDirection.FLIP_X);
   					}
   				}
   //BOX F
   				else if (boxF.intersectsLine(ballTrajectory)) {
   					CircleArc2D arc = new CircleArc2D((block.getX() + Block.SIZE), block.getY(), ballRadius, -1, 92);
   					
   					math.geom2d.line.Line2D traj = new math.geom2d.line.Line2D(BallX, BallY, nextBallX, nextBallY);
   					double intX = 0;
   					double intY = 0;
   					Collection<math.geom2d.Point2D> intersects = arc.intersections(traj);
   					if (intersects.size() != 0) {
   						for (math.geom2d.Point2D point : intersects) {
   							intX = point.getX(); // Look into this
   							intY = point.getY();
   						}
   					}
   					else { return null;
   					}
   					
   					if (getBallDirectionX() < 0) {
   						bp = new BouncePoint(intX, intY, BounceDirection.FLIP_X);
   					}
   					else {
   						bp = new BouncePoint(intX, intY, BounceDirection.FLIP_Y);
   					}
   				}
   //BOX G
   				else if (boxG.intersectsLine(ballTrajectory)) {
   					CircleArc2D arc = new CircleArc2D(block.getX(), (block.getY() + Block.SIZE), ballRadius, -89, -92);
   					math.geom2d.line.Line2D traj = new math.geom2d.line.Line2D(BallX, BallY, nextBallX, nextBallY);
   					double intX = 0;
   					double intY = 0;
   					Collection<math.geom2d.Point2D> intersects = arc.intersections(traj);
   					if (intersects.size() != 0) {
   						for (math.geom2d.Point2D point : intersects) {
   							intX = point.getX();
   							intY = point.getY();
   						}
   					}
   					else { return null;
   					}
   					
   					if (getBallDirectionX() < 0) {
   						bp = new BouncePoint(intX, intY, BounceDirection.FLIP_Y);
   					}
   					else {
   						bp = new BouncePoint(intX, intY, BounceDirection.FLIP_X);
   					}
   				}
   				
   //BOX H
   				else if (boxH.intersectsLine(ballTrajectory)) {
   					CircleArc2D arc = new CircleArc2D((block.getX() + Block.SIZE), (block.getY() + Block.SIZE), ballRadius, 269, 92);

   					math.geom2d.line.Line2D traj = new math.geom2d.line.Line2D(BallX, BallY, nextBallX, nextBallY);
   					double intX = 0;
   					double intY = 0;
   					Collection<math.geom2d.Point2D> intersects = arc.intersections(traj);
   					if (intersects.size() != 0) {
   						for (math.geom2d.Point2D point : intersects) {
   							intX = point.getX();
   							intY = point.getY();
   						}
   					}
   					else { return null;
   					}
   					
   					if (getBallDirectionX() < 0) {
   						bp = new BouncePoint(intX, intY, BounceDirection.FLIP_X);
   					}
   					else {
   						bp = new BouncePoint(intX, intY, BounceDirection.FLIP_Y);
   					}
   				}
   			return bp;
  }

  // line 391 "../../../../../Block223States.ump"
   private boolean hitWall(){
    BouncePoint bounceP = calculateBouncePointWall();
	
	
	setBounce(bounceP); 
	   
	return (bounceP!=null);
  }

  // line 400 "../../../../../Block223States.ump"
   private BouncePoint calculateBouncePointWall(){
    BouncePoint bounceP = null;
       double ballRadius = Ball.BALL_DIAMETER / 2;
       double BallX =  getCurrentBallX();
       double BallY =  getCurrentBallY();
       double nextBallX = (BallX + getBallDirectionX());
       double nextBallY = (BallY + getBallDirectionY());
       Line2D ballTrajec = new Line2D.Double(BallX, BallY, nextBallX, nextBallY);
    
       Rectangle2D boxA = new Rectangle2D.Double(0, 0, ballRadius, (Game.PLAY_AREA_SIDE - ballRadius));
       Rectangle2D boxB = new Rectangle2D.Double(ballRadius, 0, (Game.PLAY_AREA_SIDE - Ball.BALL_DIAMETER), ballRadius);
       Rectangle2D boxC = new Rectangle2D.Double((Game.PLAY_AREA_SIDE - ballRadius), 0, ballRadius, (Game.PLAY_AREA_SIDE - ballRadius));

       if (boxA.intersectsLine(ballTrajec) && boxB.intersectsLine(ballTrajec)) {
         bounceP = new BouncePoint(5, 5, BounceDirection.FLIP_BOTH);
       }
       else if (boxB.intersectsLine(ballTrajec) && boxC.intersectsLine(ballTrajec)) {
         bounceP = new BouncePoint(385, 5, BounceDirection.FLIP_BOTH);
       }
       else if (boxA.intersectsLine(ballTrajec)) {
             if (BallX <= ballRadius ) {
              Line2D lineA = new Line2D.Double(ballRadius, ballRadius, ballRadius, (Game.PLAY_AREA_SIDE - ballRadius));
              Point2D intersection = calculateIntersectPoint(ballTrajec, lineA);
              bounceP = new BouncePoint(intersection.getX(), intersection.getY(), BounceDirection.FLIP_X);
             }
          }
           
       else if (boxB.intersectsLine(ballTrajec)) {
           if (BallY <= 10) {
          Line2D lineB = new Line2D.Double(ballRadius, ballRadius, (Game.PLAY_AREA_SIDE - ballRadius), ballRadius);
          Point2D intersection = calculateIntersectPoint(ballTrajec, lineB);
          bounceP = new BouncePoint(intersection.getX(), intersection.getY(), BounceDirection.FLIP_Y);
           }
        }
       else if (boxC.intersectsLine(ballTrajec)) {
           if (BallX >= Game.PLAY_AREA_SIDE - ballRadius) {
           Line2D lineC = new Line2D.Double((Game.PLAY_AREA_SIDE - ballRadius), ballRadius, (Game.PLAY_AREA_SIDE - ballRadius), (Game.PLAY_AREA_SIDE - ballRadius));
           Point2D intersection = calculateIntersectPoint(ballTrajec, lineC);
           bounceP = new BouncePoint(intersection.getX(), intersection.getY(), BounceDirection.FLIP_X);
           }
        }
         
       return bounceP;
  }


  /**
   * Actions
   */
  // line 449 "../../../../../Block223States.ump"
   private void doSetup(){
    resetCurrentBallX();
	   resetCurrentBallY();
	   resetBallDirectionX();
	   resetBallDirectionY();
	   resetCurrentPaddleX();
	   Game game = getGame();
	   Level level = game.getLevel(currentLevel-1);
	   List<BlockAssignment> assignments = level.getBlockAssignments();
	   for(BlockAssignment assignment: assignments) {
		   PlayedBlockAssignment pblock = new PlayedBlockAssignment(Game.WALL_PADDING+(Block.SIZE+Game.COLUMNS_PADDING)*(assignment.getGridHorizontalPosition()-1),Game.WALL_PADDING+(Block.SIZE+Game.ROW_PADDING)*(assignment.getGridVerticalPosition()-1), assignment.getBlock(), this);
	   }
	   while(numberOfBlocks() < game.getNrBlocksPerLevel()) {
		   
		   	 int n = game.getNrBlocksPerLevel();
		   
		   	 int maxColumn = (Game.PLAY_AREA_SIDE - 2*(Game.WALL_PADDING) + Game.COLUMNS_PADDING)/Game.COLUMNS_PADDING+Block.SIZE;
			 int minColumn = 1;
			 Random rC = new Random();
			 int index_X = rC.nextInt((maxColumn - minColumn) + 1) + minColumn;
			 
			 int maxRow = n/maxColumn;
			 int minRow = 1;
			 Random rR = new Random();
			  int index_Y = rR.nextInt(Math.abs(maxRow - minRow) + 1) + minRow;
			 
			 
			 int positionX = Game.WALL_PADDING+(Block.SIZE+Game.COLUMNS_PADDING)*(index_X-1);
			 
			 int positionY = Game.WALL_PADDING+(Block.SIZE+Game.COLUMNS_PADDING)*(index_Y-1);
	
			 Boolean positionFound = isPositionFree(positionX,positionY);
			 while(!positionFound) {
				 if(index_X<15) {
					 index_X++;
					 positionX = Game.WALL_PADDING+(Block.SIZE+Game.COLUMNS_PADDING)*(index_X-1);
					 }
					 else if(index_Y<maxRow){
						 index_Y++;
						 index_X=1;
						 positionY= Game.WALL_PADDING+(Block.SIZE+Game.COLUMNS_PADDING)*(index_Y-1);
					 }
					 else {
						 index_X=1;
						 index_Y=1;
						 positionX = Game.WALL_PADDING+(Block.SIZE+Game.COLUMNS_PADDING)*(index_X-1);
						 positionY= Game.WALL_PADDING+(Block.SIZE+Game.COLUMNS_PADDING)*(index_Y-1);
					 }
				 positionFound = isPositionFree(positionX,positionY);
			 }
		     
			 PlayedBlockAssignment pblock = new PlayedBlockAssignment(positionX,positionY,game.getRandomBlock(),this);
		   
		   
	   }
  }

  // line 506 "../../../../../Block223States.ump"
   private void doHitPaddleOrWall(){
    bounceBall();
  }

  // line 512 "../../../../../Block223States.ump"
   private void doOutOfBounds(){
    setLives(getLives()-1);
	   resetCurrentBallX();
	   resetCurrentBallY();
	   resetBallDirectionX();
	   resetBallDirectionY();
	   resetCurrentPaddleX();
  }

  // line 522 "../../../../../Block223States.ump"
   private void doHitBlock(){
    // Reminder: IMPLEMENT GUARDS!
       
           double Y1 = bounce.getY() - currentBallY;
         double Y2 = ballDirectionY - Y1;
         double X1 = bounce.getX() - currentBallX;
         double X2 = ballDirectionX - X1;
         if (bounce.getDirection() == BounceDirection.FLIP_Y) {
           if (Y2 == 0) {
             setCurrentBallX(bounce.getX());
             setCurrentBallY(bounce.getY());
             return;
           }
         }
         else if (bounce.getDirection() == BounceDirection.FLIP_X) {
           if (X2 == 0) {
             setCurrentBallX(bounce.getX());
             setCurrentBallY(bounce.getY());
             return;
           }
         }
         else if (bounce.getDirection() == BounceDirection.FLIP_BOTH) {
           if ((Y2 == 0) || (X2 == 0)) {
             return;
           }
         }
    
        int score = getScore();
        PlayedBlockAssignment pblock = bounce.getHitBlock();
        Block block = pblock.getBlock();
        int points = block.getPoints();
        setScore(score+points);
        pblock.delete();
        bounceBall();
  }

  // line 558 "../../../../../Block223States.ump"
   private void doHitBlockNextLevel(){
    doHitBlock();
       int level = getCurrentLevel();
       setCurrentLevel(level+1);
       
       setCurrentPaddleLength(getGame().getPaddle().getMaxPaddleLength()-(getGame().getPaddle().getMaxPaddleLength()-getGame().getPaddle().getMinPaddleLength())/(getGame().numberOfLevels()-1)*(getCurrentLevel()-1));
       
       setWaitTime( INITIAL_WAIT_TIME * Math.pow(getGame().getBall().getBallSpeedIncreaseFactor(), (getCurrentLevel()-1)));
  }

  // line 567 "../../../../../Block223States.ump"
   private void doHitNothingAndNotOutOfBounds(){
    double x = getCurrentBallX();
    double y = getCurrentBallY();
    double dx = getBallDirectionX();
    double dy = getBallDirectionY();
    setCurrentBallX(x + dx);
    setCurrentBallY(y + dy);
  }

  // line 576 "../../../../../Block223States.ump"
   private void doGameOver(){
    Block223 block223 = this.getBlock223();
	   Player p= getPlayer();
	   if(p!=null) {
		   Game game=getGame();
		   HallOfFameEntry hof = new HallOfFameEntry(score,playername, p, game, block223);
		   game.setMostRecentEntry(hof);
	   }
	   this.delete();
  }

  // line 588 "../../../../../Block223States.ump"
   private void bounceBall(){
    double Y1 = bounce.getY()-currentBallY;
	     double Y2 = ballDirectionY-Y1;
	     double X1 = bounce.getX()-currentBallX;
	     double X2 = ballDirectionX-X1;
	     double newBallDirectionY;
	     double newBallDirectionX;
	     double newPositionY=0;
	     double newPositionX=0;
	     double xSign=Math.signum(ballDirectionX);
	     double ySign = Math.signum(ballDirectionY);
	    
	   if(xSign == 0) {
	       xSign=1;
	     }
	     
	    
	     if(ySign == 0) {
	       ySign=1;
	     }
	
	     else if (bounce.getDirection()== BounceDirection.FLIP_Y) {
	       if (Y2 == 0) {
	         setCurrentBallX(bounce.getX());
	         setCurrentBallY(bounce.getY());
	         return;
	       }
	       newBallDirectionY= ballDirectionY* -1;
	       newBallDirectionX= ballDirectionX + (xSign *0.1 * Math.abs(ballDirectionY));
	       newPositionY= bounce.getY()+Y2/ballDirectionY * newBallDirectionY;
	       newPositionX= bounce.getX()+Y2/ballDirectionY * newBallDirectionX;
	       setBallDirectionY(newBallDirectionY);
	       setBallDirectionX(newBallDirectionX);
	     }
	     
	     else  if (bounce.getDirection()== BounceDirection.FLIP_X) {
	       if (Y2 == 0) {
	         setCurrentBallX(bounce.getX());
	         setCurrentBallY(bounce.getY());
	         return;
	       }
	       newBallDirectionX= ballDirectionX* -1;
	       newBallDirectionY= ballDirectionY + (ySign *0.1 * Math.abs(ballDirectionX));
	       newPositionY= bounce.getY()+X2/ballDirectionX * newBallDirectionY;
	       newPositionX= bounce.getX()+X2/ballDirectionX * newBallDirectionX;
	       setBallDirectionY(newBallDirectionY);
	       setBallDirectionX(newBallDirectionX);
	     }
	     else  if (bounce.getDirection()== BounceDirection.FLIP_BOTH) {
	       if ((Y2 == 0)|| (X2 == 0)) {
	         setCurrentBallX(bounce.getX());
	         setCurrentBallY(bounce.getY());
	         return;
	       }
	       newBallDirectionY= ballDirectionY* -1;
	       newBallDirectionX= ballDirectionX*-1;
	       newPositionY= bounce.getY()+Y2/ballDirectionY * newBallDirectionY;
	       newPositionX= bounce.getX()+X2/ballDirectionX * newBallDirectionX;
	       setBallDirectionY(newBallDirectionY);
	       setBallDirectionX(newBallDirectionX);
	     }
	     
	     
	     setCurrentBallX(newPositionX);
	     setCurrentBallY(newPositionY);
  }

  // line 656 "../../../../../Block223States.ump"
   private Point2D calculateIntersectPoint(Line2D line1, Line2D line2){
    if (line1.getX2() == line1.getX1()) {	
	    		return (new Point2D.Double(line1.getX1(), line2.getY1()));
	    	}
	    
		  
		   if (line2.getX2() == line2.getX1()) {  
			   return (new Point2D.Double(line2.getX1(), line1.getY1()));
		   }
		   
		   
		      double a1 = ((line1.getY2() - line1.getY1()) / (line1.getX2() - line1.getX1()));
			  double a2 = ((line2.getY2() - line2.getY1()) / (line2.getX2() - line2.getX1()));
			  double b1 = (line1.getY1() - a1 * line1.getX1());
			  double b2 = (line2.getY1() - a2 * line2.getX1());
			  double c = (b2 - b1) / (a1 - a2);
			  double d = a1 * c + b1;
			  
			  return (new Point2D.Double(c, d));
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "score" + ":" + getScore()+ "," +
            "lives" + ":" + getLives()+ "," +
            "currentLevel" + ":" + getCurrentLevel()+ "," +
            "waitTime" + ":" + getWaitTime()+ "," +
            "playername" + ":" + getPlayername()+ "," +
            "ballDirectionX" + ":" + getBallDirectionX()+ "," +
            "ballDirectionY" + ":" + getBallDirectionY()+ "," +
            "currentBallX" + ":" + getCurrentBallX()+ "," +
            "currentBallY" + ":" + getCurrentBallY()+ "," +
            "currentPaddleLength" + ":" + getCurrentPaddleLength()+ "," +
            "currentPaddleX" + ":" + getCurrentPaddleX()+ "," +
            "currentPaddleY" + ":" + getCurrentPaddleY()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "bounce = "+(getBounce()!=null?Integer.toHexString(System.identityHashCode(getBounce())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "block223 = "+(getBlock223()!=null?Integer.toHexString(System.identityHashCode(getBlock223())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 107 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = 8597675110221231714L ;

  
}