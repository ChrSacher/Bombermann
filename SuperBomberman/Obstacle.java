import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Klasse welche Hindernise beschreibt. Kann zerstörbar und nicht zerstörbar sein.
 * 
 * @author Dieu Huyen Dinh 
 * @version 06.12.2017
 */
public class Obstacle extends InteractableActor
{
    public Obstacle()
    {
        isDestructable = true;
        setExplosionHandlingType(ExplosionHandling.Block);
    }

    /**
     * Obstacle Constructor
     *
     * @param obstacle ist Zerstörbar?
     */
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
            Logger.log("Hindernis zerstört");
            //generiere Zufallszahl damit zufällig 1 positives powerup,ein negatives Powerup oder gar kein Powerup ensteht
            int zufallzahl = Greenfoot.getRandomNumber(100);
            if ( zufallzahl<= 25)
            {
                PowerUp powerUp = new PowerUp ();
                bomberWorld.addObject(powerUp, getX(), getY());
                PowerUpType type = PowerUpType.values()[Greenfoot.getRandomNumber(PowerUpType.values().length)];
                powerUp.setPowerUp(type);
                powerUp.setValue(1);
                Logger.log("Lasse positives Powerup vom typ " + type.toString() +"fallen");
                
                

            }
            else 
            {
                if(zufallzahl > 30 && zufallzahl <= 85)
                {
                    Logger.log("Lasse kein Powerup fallen");
                }
                else
                {
                     PowerUp powerUp = new PowerUp ();
                    bomberWorld.addObject(powerUp, getX(), getY());
                    PowerUpType type = PowerUpType.values()[Greenfoot.getRandomNumber(PowerUpType.values().length)];
                    powerUp.setPowerUp(type);
                    powerUp.setValue(-1);
                    Logger.log("Lasse negatives Powerup vom typ " + type.toString() + " fallen");
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
            //setzten des Verhaltens gegenüber von Explosionen. Zerstörbare Hindernisse haben eine Explosion über sich.
            setExplosionHandlingType(ExplosionHandling.Receive);
        }
        else 
        {
            setImage ( bomberWorld.getStyleSheet().getWallImage());
            setExplosionHandlingType(ExplosionHandling.Block);
        }
    }
    /*
     * Ist das Hindernis Zerstörbar
     */
    private boolean isDestructable= true;

}
