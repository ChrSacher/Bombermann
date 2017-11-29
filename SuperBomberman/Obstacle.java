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
    public Obstacle( boolean obstacle)
    {
        isDestructable =  obstacle ;
       
    }
    
    protected void OnReceiveExplosion()
    {
        if( isDestructable== true)
        {
            PowerUp powerUp = new PowerUp ();
            bomberWorld.addObject(powerUp, getX(), getY());
            powerUp.setPowerUp(PowerUpType.values()[Greenfoot.getRandomNumber(PowerUpType.values().length)]);
            
           int zufallzahl = Greenfoot.getRandomNumber(100);
           if ( zufallzahl<= 50)
           {
            powerUp.setValue(1);
            
            }
            else {
            powerUp.setValue(-1);
        }
            getWorld().removeObject(this);
            
        }
       
        
    }
   
    public boolean getIsDestructable()
    { 
     return isDestructable;
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
