import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FloorTile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FloorTile extends GridActor
{
    private boolean isEdge = false;
    /**
     * Method loadImage
     *
     * @param isEdge A parameter
     */
    FloorTile(boolean newIsEdge)
    {
        isEdge = newIsEdge;
    }
    
    public void loadImage(boolean newIsEdge)
    {
        isEdge = newIsEdge;
        if(isEdge == true)
        {
            setImage(bomberWorld.getStyleSheet().getEdgeFloorTileImage());
        }
        else
        {
            setImage(bomberWorld.getStyleSheet().getFloorTileImage());
        }
    }
    
    public void addedToWorld(World world)
    {
        super.addedToWorld(world);
        loadImage(isEdge);
    }
  
}
