import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Obstacle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Obstacle extends InteractableActor
{
    public Obstacle()
    {
       
    }
    
    protected void OnReceiveExplosion()
    {
        if( isDestructable== true)
        {
            getWorld().removeObject(this);
            
        }
    }
    
    /**
     * Act - do whatever the Obstacle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        setWallImage();
    } 
    
    public void addedToWorld(World world)
    {
        super.addedToWorld(world);
        setWallImage();
    }
    
    public void setisDestructable(boolean newIsDestructable)
    {
        isDestructable = newIsDestructable;
        setWallImage();
    }
    public void setWallImage()
    { 
        if (isDestructable== true)
        { 
            setImage ( bomberWorld.getStyleSheet().getObstacleImage());
        
        }
        else 
        {
            setImage ( bomberWorld.getStyleSheet().getWallImage());
        }
    }
    private boolean isDestructable= true;
   
}
