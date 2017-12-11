import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Klasse welche Powerups in der Welt darstellt.
 * 
 * @author Dieu Huyen Dinh
 * @version 07.12.17
 */
public class PowerUp extends InteractableActor
{  
    /*
     * Wert der darstellt, ob es ein positives Powerup oder ein negatives Powerup ist.
     */
    private int value = 1;
    /*
     * Variante des PowerUps
     */
    private PowerUpType powerUpType = PowerUpType.Speed;
   
    public PowerUp()
    {
        
    }
    
    public PowerUp(int value,PowerUpType powerUpType)
    {
        this.value = value;
        this.powerUpType = powerUpType;
    }
    /**
     * Act - do whatever the PowerUp wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        decideImage();
    }
    
    public PowerUpType getPowerUpType()
    {
        return powerUpType;
    }
    
    public void setValue(int newValue)
    {
        value = newValue;

        decideImage();

    }
    public int getValue()
    {
        return value;
    }
    public void setPowerUp(PowerUpType newPowerUp)
    {
        powerUpType = newPowerUp;
        decideImage();
    }
    
     @Override
    protected void OnWorldLoaded()
    {
        decideImage();
    }
    /**
     * Method decideImage
     * Lade Bild des Powerups mithilfe von powerUpType und value
     */
    public void decideImage()
    {
        GreenfootImage image = bomberWorld.getStyleSheet().getPowerupImage(powerUpType, value);
        setImage(image);
        
    }
    

}