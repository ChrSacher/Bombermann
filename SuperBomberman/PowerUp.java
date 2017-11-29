import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PowerUp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PowerUp extends InteractableActor
{  
    private int value = 1;
    private PowerUpType powerUpType = PowerUpType.Speed;
   

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
    
    
    
    public void decideImage()
    {
        switch( powerUpType)
        {
            case Speed:
            {

                if (value >= 0)
                { 
                    setImage("/SpriteSheetImages/Powerups/SpeedUp.png");
                }
                else
                { 
                    setImage("/SpriteSheetImages/Powerups/SpeedDown.png");
                }

            }  
            break;
            case Strength:
            { 
                if (value >= 0)
                { 
                    setImage("/SpriteSheetImages/Powerups/LengthUp.png");
                }
                else
                { 
                    setImage("/SpriteSheetImages/Powerups/LengthDown.png");
                }
            }
            break;
            case Death:
            { 
                if (value >= 0)
                { 
                    setImage("/SpriteSheetImages/Powerups/Death.png");
                }
                else
                { 
                    setImage("/SpriteSheetImages/Powerups/Death2.png");
                }

            } 
            break;
            case Ammount:
            {
                if (value >= 0)
                { 
                    setImage("/SpriteSheetImages/Powerups/BombsUp.png");
                }
                else
                { 
                    setImage("/SpriteSheetImages/Powerups/BombsDown.png");
                }

            }
            break;
        }
    }

}