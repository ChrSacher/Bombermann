import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BomberWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BomberWorld extends World
{
    private int gridSize = 25;
    private int gridXNum = 10;
    private int gridYNum = 10;
    private BombermanStyleSheet styleSheet = new BombermanStyleSheet();
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
    }

    /**
     * BomberWorld Constructor
     *
     * @param gridSize A parameter
     * @param gridNumX A parameter
     * @param gridNumY A parameter
     * @param numObstacles A parameter
     */
    public BomberWorld(int gridSize,int gridNumX,int gridNumY,int numObstacles)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(gridNumX * gridSize , gridNumY * gridSize, 1); 
        styleSheet.loadImages();
        styleSheet.resizeImages(gridSize);
        generateWorld(gridSize,gridNumX,gridNumY,numObstacles);
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
     * Method generateNewWorld
     *
     * @param gridSize A parameter
     * @param gridNumX A parameter
     * @param gridNumY A parameter
     * @param numObstacles A parameter
     */
    public void generateNewWorld(int gridSize,int gridNumX,int gridNumY,int numObstacles)
    {
        Greenfoot.setWorld(new BomberWorld(gridSize,gridNumX,gridNumY,numObstacles));
    }

    /**
     * Method generateWorld
     *
     * @param gridSize A parameter
     * @param gridNumX A parameter
     * @param gridNumY A parameter
     * @param numPlayers A parameter
     * @param numObstacles A parameter
     */
    public void generateWorld(int gridSize,int gridNumX,int gridNumY,int numObstacles)
    {
        removeObjects(getObjects(null));
        setGridSize(gridSize);
        drawGrid();

        for(int i = 0; i < gridNumX;i++)
        {
            for(int j = 0; j < gridNumY;j++)
            {
                FloorTile newFloor = new FloorTile(false);
                addObject(newFloor,i * gridSize, j * gridSize);  

            }
        }
        for(int i = 2; i < (gridNumX -2);i +=2)
        {
            for(int j = 2; j < (gridNumY - 2);j +=2)
            {

                Obstacle upperOuterWall = new Obstacle();    
                addObject(upperOuterWall,i * gridSize,j * gridSize);
                upperOuterWall.setisDestructable(false);
            }
        }

                
        
        for(int i = 0; i < gridNumX;i++)
        {
            Obstacle upperOuterWall = new Obstacle();    
            addObject(upperOuterWall,i * gridSize,0);
            upperOuterWall.setisDestructable(false);

            Obstacle lowerOuterWall = new Obstacle();
            addObject(lowerOuterWall,i * gridSize,(gridNumY -1 ) * gridSize);
            lowerOuterWall.setisDestructable(false);
        }

        for(int i = 1; i < gridNumY -1;i++)
        {
            Obstacle upperOuterWall = new Obstacle();    
            addObject(upperOuterWall,0,i * gridSize);
            upperOuterWall.setisDestructable(false);

            Obstacle lowerOuterWall = new Obstacle();
            addObject(lowerOuterWall,(gridNumX -1 ) * gridSize, i *gridSize);
            lowerOuterWall.setisDestructable(false);
        }

    }

    /**
     * Method addPlayer
     *
     * @param newBomberman A parameter
     * @param gridX A parameter
     * @param gridY A parameter
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
        generateWorld(30,15,15,50);
        addPlayer(new PlayerBomberman(),1,1);
    }
}
