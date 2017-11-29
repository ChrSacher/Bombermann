import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GridActor here.
 * 
 * @author Christian Sacher 
 * @version 26.11.2017
 */
public class GridActor extends Actor
{
    //Referenz auf unsere eigene Welt für schnellern Zugriff
    protected BomberWorld bomberWorld = null;
    
    //Variable die steuert ob eine Actor nur auf dem Grid bewegt werden kann.
    protected boolean forceGridLocation = true;

    //Position auf dem Grid in XRichtung
    protected int gridXPos = -1;
    
    //Position auf dem Grid in YRichtung
    protected int gridYPos = -1;

    /**
     * @return the gridXPos
     */
    public int getGridXPos() 
    {
        if(bomberWorld != null) gridXPos = bomberWorld.convertPosToGrid(getX());
        else gridYPos = -1;
        return gridXPos;
    }
    
    public int getGridXPosAsPixel() 
    {
        if(bomberWorld != null) return bomberWorld.convertGridToPos(getGridXPos());
     
        return -1;
    }
    
    /**
     * @param gridXPos the gridXPos to set
     */
    public void setGridXPos(int gridXPos) 
    {
        this.gridXPos = gridXPos;
    }

    /**
     * @return the gridYPos
     */
    public int getGridYPos() 
    {
        if(bomberWorld != null) gridYPos = bomberWorld.convertPosToGrid(getY());
        else gridYPos = -1;
        return gridYPos;
    }

    
    public int getGridYPosAsPixel() 
    {
        if(bomberWorld != null) return bomberWorld.convertGridToPos(getGridYPos());
     
        return -1;
    }
    
    /**
     * @param gridYPos the gridYPos to set
     */
    public void setGridYPos(int gridYPos)
    {
        this.gridYPos = gridYPos;
    }

    public void addedToWorld(World world)
    {
        bomberWorld = (BomberWorld)world;
        setLocation(getX(),getY());
    }

    public void setLocation(int locX ,int locY)
    {
        if(bomberWorld != null && forceGridLocation)
        {
            gridXPos = bomberWorld.convertPosToGrid(locX);
            gridYPos = bomberWorld.convertPosToGrid(locY);
            super.setLocation(bomberWorld.roundToGrid(locX),bomberWorld.roundToGrid(locY));
        } 
        else
        {
            gridXPos = -1;
            gridYPos = -1;
            super.setLocation(locX,locY);
        }
    }

}
