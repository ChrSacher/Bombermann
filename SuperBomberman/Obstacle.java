import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Obstacle here.
 * 
 * @author Dieu Huyen Dinh 
 * @version (a version number or a date)
 */
public class Obstacle extends InteractableActor
{
    public Obstacle()
    {
        isDestructable = true;
        setExplosionHandlingType(ExplosionHandling.Block);
    }

    public Obstacle( boolean obstacle)
    {
        isDestructable =  obstacle ;
        if(isDestructable) 
        {
            setExplosionHandlingType(ExplosionHandling.Receive);
        }
        else
        {
            setExplosionHandlingType(ExplosionHandling.Block);
        }
    }
    @Override
    protected void OnReceiveExplosion()
    {
        if( isDestructable== true)
        {

            int zufallzahl = Greenfoot.getRandomNumber(100);
            if ( zufallzahl<= 25)
            {
                PowerUp powerUp = new PowerUp ();
                bomberWorld.addObject(powerUp, getX(), getY());
                powerUp.setPowerUp(PowerUpType.values()[Greenfoot.getRandomNumber(PowerUpType.values().length)]);
                powerUp.setValue(1);

            }
            else 
            {
                if(zufallzahl > 30 && zufallzahl <= 85)
                {
                }
                else
                {
                     PowerUp powerUp = new PowerUp ();
                    bomberWorld.addObject(powerUp, getX(), getY());
                    powerUp.setPowerUp(PowerUpType.values()[Greenfoot.getRandomNumber(PowerUpType.values().length)]);
                    powerUp.setValue(-1);
                }
               

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
        
    } 

    public void setisDestructable(boolean newIsDestructable)
    {
        isDestructable = newIsDestructable;
        OnWorldLoaded();
    }
     @Override
    public void OnWorldLoaded()
    { 
        if (isDestructable== true)
        { 
            setImage ( bomberWorld.getStyleSheet().getObstacleImage());
            setExplosionHandlingType(ExplosionHandling.Receive);
        }
        else 
        {
            setImage ( bomberWorld.getStyleSheet().getWallImage());
            setExplosionHandlingType(ExplosionHandling.Block);
        }
    }
    private boolean isDestructable= true;

}
