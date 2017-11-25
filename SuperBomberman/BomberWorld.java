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
        super(600, 400, 1); 
        styleSheet.loadImages();
        styleSheet.resizeImages(gridSize);
    }
    
    public BombermanStyleSheet getStyleSheet()
    {
        return styleSheet;
    }
    
    public int convertPosToGrid(double pos)
    {
        return (int)Math.floor(pos / gridSize);
    }
    
    public int convertGridToPos(int grid)
    {
        return (grid * gridSize) + gridSize/ 2;
    }
    
    public int roundToGrid(double pos)
    {
        return convertGridToPos(convertPosToGrid(pos));
    }
    
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
}
