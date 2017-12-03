import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class BomberWorld here.
 * 
 * @author Christian Sacher
 * @version (a version number or a date)
 */
public class BomberWorld extends World
{
    private int gridSize = 25;
    private int gridXNum = 10;
    private int gridYNum = 10;
    private int safeZoneSize = 4;
    private BombermanStyleSheet styleSheet = new BombermanStyleSheet();
    
    private String soundFiles[] = {"Track_04.mp3","Track_06.mp3"};
    
    GreenfootSound currentSound = null;
    /**
     * Constructor for objects of class BomberWorld.
     * 
     */
    public BomberWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 1000, 1); 
        styleSheet.loadImages();
        styleSheet.resizeImages(gridSize);
        setPaintOrder();

    }
    public void started()
    {
        super.started();
        
        currentSound = new GreenfootSound(soundFiles[Greenfoot.getRandomNumber(soundFiles.length)]);
        if(currentSound != null) currentSound.playLoop();
    }
    public void stopped()
    {
        super.stopped();
        if(currentSound != null) currentSound.stop();
    }
    /**
     * BomberWorld Constructor
     *
     * @param gridSize A parameter
     * @param gridNumX A parameter
     * @param gridNumY A parameter
     * @param numObstacles A parameter
     */
    public BomberWorld(int gridSize,int gridNumX,int gridNumY,int numObstacles,int numPlayers)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(gridNumX * gridSize , gridNumY * gridSize, 1); 
        styleSheet.loadImages();
        styleSheet.resizeImages(gridSize);
        generateWorld(gridSize,gridNumX,gridNumY,numObstacles,numPlayers);
        setPaintOrder();
    }

    public void setPaintOrder()
    {
        setPaintOrder(Bomberman.class,Explosion.class,Bomb.class,PowerUp.class,Obstacle.class,FloorTile.class);
    }

    /**
     * Method setGridSize
     *
     * @param newGridSize A parameter
     */
    public void setGridSize(int newGridSize)
    {
        gridSize = newGridSize;
        getStyleSheet().resizeImages(gridSize);
        drawGrid();
        repaint();
    }

    public int getGridSize()
    {
        return gridSize;
    }

    /**
     * Method getStyleSheet
     *
     * @return The return value
     */
    public BombermanStyleSheet getStyleSheet()
    {
        return styleSheet;
    }

    /**
     * Method convertPosToGrid
     *
     * @param pos A parameter
     * @return The return value
     */
    public int convertPosToGrid(double pos)
    {
        return (int)Math.floor(pos / gridSize);
    }

    /**
     * Method convertGridToPos
     *
     * @param grid A parameter
     * @return The return value
     */
    public int convertGridToPos(int grid)
    {
        return (grid * gridSize) + (int)Math.floor(gridSize/ 2);
    }

    /**
     * Method roundToGrid
     *
     * @param pos A parameter
     * @return The return value
     */
    public int roundToGrid(double pos)
    {
        return convertGridToPos(convertPosToGrid(pos));
    }

    /**
     * Method drawGrid
     *
     */
    public void drawGrid()
    {
        getBackground().clear();
        getBackground().setColor(Color.BLACK);
        for(int i = 0; i <= gridXNum;i++)
        {
            getBackground().drawLine(0,i * gridSize,gridYNum * gridSize,i * gridSize);
        }
        for(int i = 0; i <= gridYNum;i++)
        {
            getBackground().drawLine(i * gridSize,0,i * gridSize,gridXNum * gridSize);
        }

    }

    /**
     * Method generateWorld
     *
     * @param gridSize Wie viele Pixel ein Feld groß ist
     * @param gridNumX Wie viele Felder in X Richtung
     * @param gridNumY Wie viele Felder in Y Richtung
     * @param numObstacles Wie viele zerstörbare Hindernisse soll es geben
     * @param numPlayers Wie viele Spieler sollen generiert werden. Max 4
     */
    public void generateWorld(int gridSize,int newGridNumX,int newGridNumY,int numObstacles,int numPlayers)
    {
        removeObjects(getObjects(null));
        setGridSize(gridSize);
        drawGrid();
        gridXNum = newGridNumX;
        gridYNum = newGridNumY;
        for(int i = 0; i < gridYNum;i++)
        {
            for(int j = 0; j < gridYNum;j++)
            {
                FloorTile newFloor = new FloorTile(false);
                addObject(newFloor,i * gridSize, j * gridSize);  

            }
        }
        for(int i = 2; i < (gridXNum -2);i +=2)
        {
            for(int j = 2; j < (gridYNum - 2);j +=2)
            {

                Obstacle upperOuterWall = new Obstacle();    
                addObject(upperOuterWall,i * gridSize,j * gridSize);
                upperOuterWall.setisDestructable(false);
            }
        }

        
        for(int i = 0; i < gridXNum;i++)
        {
            Obstacle upperOuterWall = new Obstacle();    
            addObject(upperOuterWall,i * gridSize,0);
            upperOuterWall.setisDestructable(false);

            Obstacle lowerOuterWall = new Obstacle();
            addObject(lowerOuterWall,i * gridSize,(gridYNum -1 ) * gridSize);
            lowerOuterWall.setisDestructable(false);
        }

        for(int i = 1; i < gridYNum -1;i++)
        {
            Obstacle upperOuterWall = new Obstacle();    
            addObject(upperOuterWall,0,i * gridSize);
            upperOuterWall.setisDestructable(false);

            Obstacle lowerOuterWall = new Obstacle();
            addObject(lowerOuterWall,(gridXNum -1 ) * gridSize, i *gridSize);
            lowerOuterWall.setisDestructable(false);
        }

        boolean occupancyGrid[][] = new boolean[gridSize][gridSize];
        List<InteractableActor> actorList = getObjects(InteractableActor.class);
        for(InteractableActor actor:actorList)
        {
            
                occupancyGrid[convertPosToGrid(actor.getX())][convertPosToGrid(actor.getY())] = true;     
        }
        int countObstacles = 0;
        boolean wasAbleToPlace = true;
        while(wasAbleToPlace == true && countObstacles < numObstacles)
        {
            wasAbleToPlace = false;
            for(int i = safeZoneSize; i < gridXNum - safeZoneSize;i++)
            {
                for(int j = 0; j < gridYNum ;j++)
                {
                    int randomNumber = Greenfoot.getRandomNumber(100);
                    if(occupancyGrid[i][j] == false && countObstacles < numObstacles)
                    {

                        wasAbleToPlace = true;
                        if(randomNumber < 50)
                        {

                            Obstacle destroyableObstacle = new Obstacle(true);
                            addObject(destroyableObstacle,convertGridToPos(i),convertGridToPos(j));
                            occupancyGrid[convertPosToGrid(destroyableObstacle.getX())][convertPosToGrid(destroyableObstacle.getY())] = true;   
                            countObstacles++;
                        }
                    }                     
                }
            }
            for(int i = 0; i < gridXNum ;i++)
            {
                for(int j = safeZoneSize; j < gridYNum - safeZoneSize;j++)
                {
                    int randomNumber = Greenfoot.getRandomNumber(100);
                    if(occupancyGrid[i][j] == false  && countObstacles < numObstacles)
                    {

                        wasAbleToPlace = true;
                        if(randomNumber < 50)
                        {

                            Obstacle destroyableObstacle = new Obstacle(true);
                            addObject(destroyableObstacle,convertGridToPos(i),convertGridToPos(j));
                            occupancyGrid[convertPosToGrid(destroyableObstacle.getX())][convertPosToGrid(destroyableObstacle.getY())] = true;   
                            countObstacles++;
                        }
                    }                     
                }
            }
        }

        if(numPlayers <= 0) numPlayers = 0;
        if(numPlayers >= 4) numPlayers = 4;
        
        if(numPlayers >= 1)
        {
            PlayerBomberman newBomber = new PlayerBomberman();       
            addPlayer(newBomber,1,1);
            newBomber.setPlayerColor(PlayerColor.White);
            
            
        }
        if(numPlayers >= 2)
        {
            PlayerBomberman newBomber = new PlayerBomberman();
            addPlayer(newBomber,1,gridYNum - 2);
            newBomber.setPlayerColor(PlayerColor.Red);
            newBomber.setKeySet(PlayerBomberman.ARROWKEYS);
        }
        if(numPlayers >= 3)
        {
            PlayerBomberman newBomber = new PlayerBomberman();
            addPlayer(newBomber,gridXNum - 2 ,1);
            newBomber.setPlayerColor(PlayerColor.Black);
            newBomber.setKeySet(PlayerBomberman.IJKLKEYS);
        }
        if(numPlayers >= 4)
        {
            PlayerBomberman newBomber = new PlayerBomberman();
            addPlayer(newBomber,gridXNum - 2,gridYNum - 2);
            newBomber.setPlayerColor(PlayerColor.Blue);
            newBomber.setKeySet(PlayerBomberman.NUMBERKEYS);
        }
        
    }

    /**
     * Method addPlayer
     *
     * @param newBomberman A Bomberman Object to add
     * @param gridX On Which Grid in X Direction to place the Bomberman
     * @param gridY On Which Grid in Y Direction to place the Bomberman
     */
    public void addPlayer(PlayerBomberman newBomberman,int gridX,int gridY)
    {
        addObject(newBomberman,gridSize * gridX + gridSize / 2,gridSize * gridY  + gridSize / 2); 
    }

    /**
     * Method playerTestSceneario
     *
     */
    public  void playerTestSceneario()
    {
        generateWorld(50,15,15,36,4);
        
    }
}
