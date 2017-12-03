import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PowerUp here.
 * 
 * @author Dieu Huyen Dinh
 * @version (a version number or a date)
 */
public class PowerUp extends InteractableActor
{  
    private int value = 1;
    
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
    protected void OnLoadWorldImage()
    {
        decideImage();
    }
    public void decideImage()
    {
        GreenfootImage image = bomberWorld.getStyleSheet().getPowerupImage(powerUpType, value);
        setImage(image);
        
    }
    

}