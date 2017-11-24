import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PowerUp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PowerUp extends InteractableActor
{  
    private int value =1;
    private PowerUpType powerUp = PowerUpType.speed;
    private GreenfootImage image = new GreenfootImage("/SpriteSheetImages/Powerups/SpeedUp.png");  

    /**
     * Act - do whatever the PowerUp wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        Wahlen();
    }

    public void setValue( int neueValue)
    {
        value = neueValue;

        Wahlen();

    }
    public void setPowerUp(PowerUpType newPowerUp)
    {
        powerUp = newPowerUp;
        Wahlen();
    }

    public void Wahlen()
    {
        switch( powerUp)
        {
            case speed:
            {

                if (value >=1)
                { 
                    setImage("/SpriteSheetImages/Powerups/SpeedUp.png");
                }
                else
                { 
                    setImage("/SpriteSheetImages/Powerups/SpeedDown.png");
                }

            }  
            break;
            case streng:
            { 
                if (value >=1)
                { 
                    setImage("/SpriteSheetImages/Powerups/LengthUp.png");
                }
                else
                { 
                    setImage("/SpriteSheetImages/Powerups/LengthDown.png");
                }
            }
            break;
            case death:
            { 
                if (value >=1)
                { 
                    setImage("/SpriteSheetImages/Powerups/Death.png");
                }
                else
                { 
                    setImage("/SpriteSheetImages/Powerups/Death2.png");
                }

            } 
            break;
            case ammount:
            {
                if (value >=1)
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