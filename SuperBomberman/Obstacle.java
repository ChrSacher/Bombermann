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

    }

    public Obstacle( boolean obstacle)
    {
        isDestructable =  obstacle ;

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
        OnLoadWorldImage();
    }
     @Override
    public void OnLoadWorldImage()
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
