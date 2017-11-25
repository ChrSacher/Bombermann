import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Bomberman here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bomberman extends InteractableActor
{
    private PlayerColor playerColor = PlayerColor.White;
    private int maxNumOfBombs = 2;
    private double movementSpeed = 1;
    private int lifes = 3;
    private Bomb templateBomb = new Bomb();
    private boolean isDead = false;

    private int gridXPos = 0;
    private int gridYPos = 0;
    //for statistics
    private int numBombThrown = 0;
    private int numKills = 0;

    private BomberWorld bomberWorld = null;
    
    private MovementDirection currentDirection = MovementDirection.Down;
    
    Bomberman()
    {
         
    }
   
    /**
     * @return the maxNumOfBombs
     */
    public PlayerColor getPlayerColor() 
    {
        return playerColor;
       
    }

    /**
     * @param maxNumOfBombs the maxNumOfBombs to set
     */
    public void setPlayerColor(PlayerColor color) 
    {
        playerColor = color;
        setDirectionImage(currentDirection);
    }
    
    /**
     * @return the maxNumOfBombs
     */
    public int getMaxNumOfBombs() 
    {
        return maxNumOfBombs;
    }

    /**
     * @param maxNumOfBombs the maxNumOfBombs to set
     */
    public void setMaxNumOfBombs(int maxNumOfBombs) 
    {
        this.maxNumOfBombs = maxNumOfBombs;
    }

    /**
     * @return the movementSpeed
     */
    public double getMovementSpeed() 
    {
        return movementSpeed;
    }

    /**
     * @param movementSpeed the movementSpeed to set
     */
    public void setMovementSpeed(double movementSpeed) 
    {
        if(movementSpeed < 0.5) movementSpeed = 0.5;
        if(movementSpeed > 4) movementSpeed = 4;
        this.movementSpeed = movementSpeed;
    }

    /**
     * @return the lifes
     */
    public int getLifes() 
    {
        return lifes;
    }

    /**
     * @param lifes the lifes to set
     */
    public void setLifes(int lifes) 
    {
        this.lifes = lifes;
    }

    /**
     * @return the templateBomb
     */
    public Bomb getTemplateBomb()
    {
        return templateBomb;
    }

    /**
     * @param templateBomb the templateBomb to set
     */
    public void setTemplateBomb(Bomb templateBomb) 
    {
        this.templateBomb = templateBomb;
    }

    /**
     * @return the isDead
     */
    public boolean isDead() 
    {
        return isDead;
    }

    /**
     * @param isDead the isDead to set
     */
    public void setDead(boolean isDead)
    {
        this.isDead = isDead;
    }

    /**
     * @return the gridXPos
     */
    public int getGridXPos() 
    {
        return gridXPos;
    }

    /**
     * @param gridXPos the gridXPos to set
     */
    public void setGridXPos(int gridXPos) 
    {
        this.gridXPos = gridXPos;
    }

    /**
     * @return the gridYPos
     */
    public int getGridYPos() 
    {
        return gridYPos;
    }

    /**
     * @param gridYPos the gridYPos to set
     */
    public void setGridYPos(int gridYPos)
    {
        this.gridYPos = gridYPos;
    }

    /**
     * @return the numBombThrown
     */
    public int getNumBombThrown() 
    {
        return numBombThrown;
    }

    /**
     * @param numBombThrown the numBombThrown to set
     */
    public void setNumBombThrown(int numBombThrown)
    {
        this.numBombThrown = numBombThrown;
    }

    /**
     * @return the numKills
     */
    public int getNumKills() 
    {
        return numKills;
    }

    /**
     * @param numKills the numKills to set
     */
    public void setNumKills(int numKills)
    {
        this.numKills = numKills;
    }

    /**
     * @return the currentDirection
     */
    public MovementDirection getCurrentDirection() 
    {
        return currentDirection;
    }

    /**
     * @param currentDirection the currentDirection to set
     */
    public void setCurrentDirection(MovementDirection currentDirection)
    {
        this.currentDirection = currentDirection;
        setDirectionImage(currentDirection);
    }

    /**
     * Act - do whatever the Bomberman wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }  

    public void addedToWorld(World world)
    {
        super.addedToWorld(world);
        setDirectionImage(currentDirection);
    }

    void onDeath()
    {

    }

    protected void setDirectionImage(MovementDirection dir)
    {

        BomberWorld w = (BomberWorld)getWorld();
        setImage(w.getStyleSheet().getBombermanImage(playerColor,dir));
    }

    protected boolean canMove(MovementDirection newDirection)
    {
        return true;
    }

    protected void move(MovementDirection newDirection)
    {
        if(canMove(newDirection) == false) return;
        if(currentDirection != newDirection)
        {
            currentDirection = newDirection;
            setDirectionImage(newDirection);
        }
        switch(newDirection)
        {
            case Up:
            {
                setLocation(getX(),getY() - (int)Math.round(movementSpeed));
            }break;
            case Down:
            {
                 setLocation(getX(),getY() + (int)Math.round(movementSpeed));
            }break;
            case Left:
            {
                 setLocation(getX()- (int)Math.round(movementSpeed),getY() );
            }break;
            case Right:
            {
                 setLocation(getX() + (int)Math.round(movementSpeed),getY() );
            }break;
            
        }
    }

    protected void OnReceiveExplosion()
    {

    }

    void dropBomb()
    {
        Bomb newBomb = new Bomb(templateBomb);
        newBomb.setLocation(bomberWorld.convertGridToPos(gridXPos),bomberWorld.convertGridToPos(gridYPos));
    }

    void findPowerUp()
    {
        List<PowerUp> foundPowerUpsList = getIntersectingObjects(PowerUp.class);
        for(PowerUp foundPowerUp : foundPowerUpsList)
        {
            switch(foundPowerUp.getPowerUpType())
            {
                case Speed:
                {
                    setMovementSpeed(getMovementSpeed() + foundPowerUp.getValue());
                }break;
                case Death:
                {
                    setLifes(getLifes()+ foundPowerUp.getValue());
                }break;

                case Ammount:
                {
                    setMaxNumOfBombs(getMaxNumOfBombs()  + foundPowerUp.getValue());
                }break;
                case Strength:
                {
                    templateBomb.setExplosionGridLength(templateBomb.getExplosionGridLength() + foundPowerUp.getValue());
                }break;
            }
        }
    }
}
