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
    { if( isDestructable== true)
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
    public void setWallImage()
    { 
        if (isDestructable== true)
        { 
            setImage ( zerstoertImage );
        
        }
        else 
        {
            setImage ( unzerstoertImage );
        }
    }
    private boolean isDestructable= true;
    private GreenfootImage zerstoertImage= new GreenfootImage("/SpriteSheetImages/FloorTiles/Obstacle.png");
    private GreenfootImage unzerstoertImage = new GreenfootImage ("/SpriteSheetImages/FloorTiles/Wall.png");
}
