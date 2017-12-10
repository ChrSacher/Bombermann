import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Basisklasse für alle Bomberman. Sie beinhaltet Animation,Bewegung,Bomben legen und Kollission.
 * 
 * @author Christian Sacher
 * @version (a version number or a date)
 */
public class Bomberman extends InteractableActor implements AnimationInterface
{
    /*
     * Spieler Farbe. Steuert welche Animation ausgewählt wird
     */
    private PlayerColor playerColor = PlayerColor.White;
    
    /*
     * Maximale Anzahl an Bomben die gleichzeitig platziert werden können
     */
    private int maxNumOfBombs = 3;
    
    /*
     * Bewegungsgeschwindigkeit in Pixel/Act
     */
    private int movementSpeed = 2;
    
    /*
     * Anzahl der Leben des Bombermans
     */
    private int lifes = 3;
    
    /*
     * Bombe welche kopiert und auf ein Feld gelegt wird. Durch templates ensteht bessere Benutzbarkeit.
     */
    private Bomb templateBomb = new Bomb();
    
    /*
     * Alle Bomben die in der Welt von diesen Bomberman existieren. 
     * Bombe muss dies updaten wenn sie explodiert und verschwindet.
     */
    private List<Bomb> thrownBombsList = new ArrayList<Bomb>();
    /*
     * Anzahl der platzierten Bomben
     */
    private int numBombThrown = 0;
    
    /*
     * Jetzige Richtung in die der Actor zeigt
     */
    private MovementDirection currentDirection = MovementDirection.Down;
    
    /*
     * Zeit die der Actor unverwundbar ist.
     */
    private int invulnerabilityTime = 150;
    
    
    private boolean isInvulnerable = false;
    /*
     * Zähler um die Unverwundbarkeit wieder auszuschalten
     */
    private int invulnerabilityTimeCounter = 0;
    
    /*
     * Variable die benutzt wird um Animationen zurückzusetzten
     */
    private boolean hasMoved = false;
    
    /*
     * Jetzige Animation die abgearbeitet wird
     */
    protected Animation currentAnimation = null;
    
    Bomberman()
    {
        forceGridLocation = false;
        templateBomb.setBesitzer(this);
    }
    
    /**
     * Bomberman Constructor
     *
     * @param newPlayerColor Spielerfarbe
     * @param newMaxNumOfBombs maximale gleichzetige Anzahl an Bomben 
     * @param newMovementSpeed Bewegungsgeschwindigkeit
     * @param newLifes Anzahl der Leben
     */
    Bomberman(PlayerColor newPlayerColor,int newMaxNumOfBombs, int newMovementSpeed,int newLifes)
    {
        forceGridLocation = false;
        playerColor = newPlayerColor;
        newMaxNumOfBombs = newMaxNumOfBombs;
        templateBomb.setBesitzer(this);
        movementSpeed = newMovementSpeed;
        lifes = newLifes;
    }
    
    public void addThrownBomb(Bomb newBomb)
    {
        thrownBombsList.add(newBomb);
    }
    
    public void removeThrownBomb(Bomb removeBomb)
    {
        thrownBombsList.remove(removeBomb);
    }
    /**
     * @return currentAnimation
     */
    public Animation getCurrentAnimation() 
    {
        return currentAnimation;

    }

    /**
     * @param newAnim neue Animation
     * 
     * Lädt die alte Animation aus und lädt dann die Neue
     */
    public void setCurrentAnimation(Animation newAnim) 
    {
        if(currentAnimation != null)
        {
            currentAnimation.setAttachedInterface(null);
        }
        currentAnimation = newAnim;
        if(currentAnimation != null)
        {
            currentAnimation.setAttachedInterface(this);
            
        }
        else
        {
            System.out.println("test");
        }
    }

    /**
     * @return playerColor
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
        loadAnimation(currentDirection);
    }

    /**
     * @return the maxNumOfBombs
     */
    public int getMaxNumOfBombs() 
    {
      
        return maxNumOfBombs;
    }

    /**
     * @param maxNumOfBombs maximale Anzahl an Bomben >= 1
     */
    public void setMaxNumOfBombs(int maxNumOfBombs) 
    {
          if(maxNumOfBombs <= 1) maxNumOfBombs = 1; 
        this.maxNumOfBombs = maxNumOfBombs;
    }

    /**
     * @return movementSpeed
     */
    public int getMovementSpeed() 
    {
        return movementSpeed;
    }

    /**
     * @param movementSpeed Bewegungsgeschwindgkeit 2 <= x <= 5
     */
    public void setMovementSpeed(int movementSpeed) 
    {
        if(movementSpeed <= 2) movementSpeed = 2;
        if(movementSpeed >= 5) movementSpeed = 5;
        this.movementSpeed = movementSpeed;
    }

    /**
     * @return lifes
     */
    public int getLifes() 
    {
        return lifes;
    }

    /**
     * @param lifes the lifes to set
     * Wenn lifes <=0 dann wird OnDeath aufgerufen
     */
    public void setLifes(int newLifes) 
    {
        lifes = newLifes;
        if(lifes <= 0)
        {
            OnDeath();
        }
    }

    /**
     * @return templateBomb
     */
    public Bomb getTemplateBomb()
    {
        return templateBomb;
    }

    /**
     * @param templateBomb Template Bombe
     */
    public void setTemplateBomb(Bomb templateBomb) 
    {
        this.templateBomb = templateBomb;
    }

    /**
     * @return numBombThrown
     */
    public int getNumBombThrown() 
    {
        return numBombThrown;
    }

    /**
     * @param numBombThrown Anzahl werfbarer Bomben
     */
    public void setNumBombThrown(int numBombThrown)
    {
        this.numBombThrown = numBombThrown;
    }

    /**
     * @return currentDirection
     */
    public MovementDirection getCurrentDirection() 
    {
        return currentDirection;
    }

    /**
     * @param currentDirection Neue Rchtung
     */
    public void setCurrentDirection(MovementDirection currentDirection)
    {
        if(currentDirection ==  this.currentDirection) return;
        this.currentDirection = currentDirection;
        loadAnimation(currentDirection);
    }

    /**
     * Act - do whatever the Bomberman wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    final public void act() 
    {
        hasMoved = false;
        //Powerup kollision und aufnehemen
        findPowerUp();
        
        //Unverwundbarkeit überprüfen
        if(isInvulnerable)
        {
            invulnerabilityTimeCounter--;
            if(invulnerabilityTimeCounter <= 0)
            {
                OnEndInvulnerability();
            }
        }
        
        OnAct();
        
        //wenn sich der Spieler nicht bewegt hat wird die Animation zurückgesetzt
        if(hasMoved == false)
        {
            if(currentAnimation != null)
            {
                currentAnimation.setAnimationFrameCounter(0);
            }
        }
        else
        {
             if(currentAnimation != null)
            {
                currentAnimation.setAnimationFrameCounter(currentAnimation.getAnimationFrameCounter() + 1);
            }
        }
    }
    /**
     * Method OnNextAnimation aus AnimationInterface
     *
     * @param newImage Neues Bild aus der Animation
     */
    @Override
    public void OnNextAnimation(GreenfootImage newImage)
    {
       loadImage(newImage);
    }
    
    /**
     * Method OnAct
     *
     * Spezielle Methode die von act() aufgerufen wird und anstatt act() überschrieben werden soll.
     * Dies ist nötig, da wohin Bewegt wird von den Unterklassen gemacht wird (Tasten oder KI) aber Animationen müssen vor und nach dieser Bewegung überprüft werden.
     * Unterklassen sollen sich nicht unbedingt mit dem Animationsystem befassen.
     */
    public void OnAct() 
    {
       
    } 
    
    protected void OnStartInvulnerability()
    {
        invulnerabilityTimeCounter = invulnerabilityTime;
        isInvulnerable = true;
        if(currentAnimation == null) return ;
            loadImage(currentAnimation.getCurrentImage());
    }
    
    protected void OnEndInvulnerability()
    {
        isInvulnerable = false;
        if(currentAnimation == null) return ;
            loadImage(currentAnimation.getCurrentImage());
    }
    
    /**
     * Method onDeath
     *
     */
    protected void OnDeath()
    {
        bomberWorld.removeObject(this);
    }

    protected void loadAnimation(MovementDirection dir)
    {
        //jetztige Animation laden
            setCurrentAnimation(bomberWorld.getStyleSheet().getBombermanAnimation(playerColor,dir));
    }

    /**
     * Method canMoveAtPos
     *
     * @param direction Bewegungsrchtung
     * @param x X Pixel in der Welt
     * @param y Y Pixel in der Welt
     * @return Kann an der Stelle X,Y sich in direction bewegt werden
     */
    protected boolean canMoveAtPos(MovementDirection direction,int x,int y)
    {
        //wir müssen den gesamten Actor bewegen um Kollision zu überprüfen (Greenfoot bietet keine Methode dafür
        int currentX = getX();
        int currentY = getY();

        setLocation(x,y);
        if(canMove(direction))
        {
            setLocation(currentX,currentY);
            return true;
        }
        else
        {
            setLocation(currentX,currentY);
            return false;
        }
    }
    
    /**
     * Method maxMoveDistance
     *
     * @param newDirection Richtung in die überprüft werden soll
     * @return Wie viele Pixel kann in die Richtung gegangen werden 0<=x<= movementSpeed
     */
    protected int maxMoveDistance(MovementDirection newDirection)
    {
        //wir gehen davon aus ,dass wir nicht kollidieren und gehen nur so weit wie wir in einem Act laufen können
        for(int i = 1; i <= movementSpeed;i++)
        {

            List<Obstacle> blocking = new ArrayList<Obstacle>();
            switch(newDirection)
            {
                case Up:
                {
                    //Müssen ganzen Actor bewegen um Kollision an einer anderen Stelle zu überprüfen
                    setLocation(getX(),getY() - i);
                    blocking.addAll(getIntersectingObjects(Obstacle.class));
                    setLocation(getX(),getY() + i );

                }break;
                case Down:
                {
                    setLocation(getX(),getY() + i);
                    blocking.addAll(getIntersectingObjects(Obstacle.class));
                    setLocation(getX(),getY() - i);
                }break;
                case Left:
                {
                    setLocation(getX() - i,getY() );
                    blocking.addAll(getIntersectingObjects(Obstacle.class));
                    setLocation(getX() + i,getY() );
                }break;
                case Right:
                {
                    setLocation(getX() + i,getY() );
                    blocking.addAll(getIntersectingObjects(Obstacle.class));
                    setLocation(getX() - i,getY() );
                }break;

            }
            if(blocking.size() != 0) 
            {        
                //-1 da wir nicht auf die Stelle können , welche überprüft wurde
                return i -1;
            }
        }
        return movementSpeed;
    }

    /**
     * Method canMove
     *
     * @param newDirection Bewegungsrichtung
     * @return Können wir mindestens 1 Pixel in die Richtung gehen
     */
    protected boolean canMove(MovementDirection newDirection)
    {
        return maxMoveDistance(newDirection) > 0;
    }

    

    protected void decideMoveY(MovementDirection dir)
    {
        //erlaube nur Up und Down Bewegung
        if(MovementDirection.Left == dir || dir == MovementDirection.Right) return;
        //Überprüfe ob wir in die Richtung können
        if(canMove(dir) == true)
        {
            
            move(dir);
            return;
        }
        
        //Überprüfe ob wir perfekt in unseren grid hoch können

        if(canMoveAtPos(dir,getGridXPosAsPixel(),getGridYPosAsPixel()))
        {
            //Berechne Weg bis zu dem Feld 
            int deltaX = getGridXPosAsPixel() - getX();
            int remainingMovement =  movementSpeed - Math.abs(deltaX);
            //überprüfe ob wir mehr laufen können  als Distanz zum Feld
            if(remainingMovement <= 0)
            {
                //da wir abs() benutzen wissen wir Richtung nicht
                if(deltaX > 0)
                {
                    move(MovementDirection.Right,movementSpeed);
                }
                else
                {
                    move(MovementDirection.Left,movementSpeed);
                }

                return;
            }
            else
            {
               
                move(MovementDirection.Right,deltaX);
                move(dir,remainingMovement);
                return;
            }
        }
        //Überprüfe ob wir links oder rechts in die Richtung können
        if(getX() % bomberWorld.getGridSize() < bomberWorld.getGridSize() / 2)
        {
            //Können wir überhaupt links uns bewegen
            if(canMoveAtPos(dir,bomberWorld.convertGridToPos(gridXPos - 1),getGridYPosAsPixel()))
            {
                //Berechne Weg bis zu dem Feld 
                int deltaX = bomberWorld.convertGridToPos(gridXPos - 1) - getX();
                int remainingMovement =  movementSpeed - Math.abs(deltaX);
                 //überprüfe ob wir mehr laufen können  als Distanz zum Feld
                if(remainingMovement <= 0)
                {
                    
                    move(MovementDirection.Left,movementSpeed);
                    return;
                }
                else
                {
                    
                    move(MovementDirection.Left,-deltaX);
                    move(dir,remainingMovement);
                    return;
                }
            }
        }
        else
        {
            //können wir uns überhaupt rechts bewegen
            if(canMoveAtPos(dir,bomberWorld.convertGridToPos(gridXPos + 1),getGridYPosAsPixel()))
            {
                //Berechne Weg bis zu dem Feld 
                int deltaX = bomberWorld.convertGridToPos(gridXPos + 1) - getX();
                int remainingMovement =  movementSpeed - deltaX;
                 //überprüfe ob wir mehr laufen können  als Distanz zum Feld
                if(remainingMovement <= 0)
                {
                    
                    move(MovementDirection.Right,movementSpeed);
                    return;
                }
                else
                {
                   
                    move(MovementDirection.Right,deltaX);
                    move(dir,remainingMovement);
                    return;
                }
            }
        }
    }
        
    protected void decideMoveX(MovementDirection dir)
    {
        if(MovementDirection.Up == dir || dir == MovementDirection.Down) return;
        //analog zu decideMoveY
        if(canMove(dir) == true)
        {
            
            move(dir);
            return;
        }
        //überprüfe ob wir perfekt in unseren grid hoch können

        if(canMoveAtPos(dir,getGridXPosAsPixel(),getGridYPosAsPixel()))
        {
            int deltaY = getGridYPosAsPixel() - getY();
            int remainingMovement =  movementSpeed - Math.abs(deltaY);
            if(remainingMovement <= 0)
            {

                if(deltaY >= 0)
                {
                    
                    move(MovementDirection.Down,movementSpeed);
                }
                else
                {
                    
                    move(MovementDirection.Up,movementSpeed);
                }

                return;
            }
            else
            {
                
                move(MovementDirection.Down,deltaY);
                
                move(dir,remainingMovement);
                return;
            }
        }
        //Überprüfe ob wir links oder rechts hoch können
        if(getY() % bomberWorld.getGridSize() < bomberWorld.getGridSize() / 2)
        {
            //nach links
            if(canMoveAtPos(dir,getGridXPosAsPixel(),bomberWorld.convertGridToPos(gridYPos - 1) ))
            {
                int deltaY = bomberWorld.convertGridToPos(gridYPos - 1) - getY();
                int remainingMovement =  movementSpeed - Math.abs(deltaY);
                if(remainingMovement <= 0)
                {
                    
                    move(MovementDirection.Up,movementSpeed);
                    return;
                }
                else
                {
                    
                    move(dir,-deltaY);
                    move(MovementDirection.Up,remainingMovement);
                    return;
                }
            }
        }
        else
        {
            if(canMoveAtPos(dir,getGridXPosAsPixel(),bomberWorld.convertGridToPos(gridYPos + 1)))
            {
                int deltaX = bomberWorld.convertGridToPos(gridXPos + 1) - getX();
                int remainingMovement =  movementSpeed - deltaX;
                if(remainingMovement <= 0)
                {
                    
                    move(MovementDirection.Down,movementSpeed);
                    return;
                }
                else
                {
                   
                    move(MovementDirection.Down,deltaX);
                    move(dir,remainingMovement);
                    return;
                }
            }
        }
    }
    
    

    protected void decideMove(MovementDirection newDirection)
    {
        switch(newDirection)
        {
            case Up:
            {

                decideMoveY(newDirection);
            }break;
            case Down:
            {
                 decideMoveY(newDirection);
            }break;
            case Left:
            {
                decideMoveX(newDirection);

            }break;
            case Right:
            {
               decideMoveX(newDirection);
            }break;

        }
    }

    protected void move(MovementDirection newDirection)
    {
        move (newDirection,maxMoveDistance(newDirection));
    }

    /**
     * Method move  Da unsere Kollision pixel genau ist muss man perfekt den Spalt treffen um Richtungen zu wechseln. Da dies sehr umständlich ist , gibt es eine kluge Bewegungshilfe.
     *
     * @param newDirection In welche Richtung soll gelaufen werden.
     */
    protected void move(MovementDirection newDirection,int distance)
    {
        if(canMove(newDirection) == false) return;
        hasMoved = true;
        
        if(currentDirection != newDirection)
        {
            setCurrentDirection(newDirection);

        }
        switch(newDirection)
        {
            case Up:
            {

                setLocation(getX(),getY() - distance);
            }break;
            case Down:
            {
                setLocation(getX(),getY() + distance);
            }break;
            case Left:
            {
                setLocation(getX()- distance ,getY());
            }break;
            case Right:
            {
                setLocation(getX() + distance,getY() );
            }break;

        }
    }
    @Override
    protected void OnReceiveExplosion()
    {
        if(isInvulnerable == false)
        {
             setLifes(getLifes() - 1 );
             OnStartInvulnerability();
        }
        
    }

    /**
     * Method dropBomb
     *
     */
    void dropBomb()
    {
        List<Bomb> bombs = bomberWorld.getObjects(Bomb.class);
        for(Bomb bomb : bombs)
        {
            if(bomb.getGridXPos() == getGridXPos() && bomb.getGridYPos() == getGridYPos())
                return;
        }
        if(getMaxNumOfBombs() > thrownBombsList.size())
        {
            Bomb newBomb = new Bomb(templateBomb);
            bomberWorld.addObject(newBomb,bomberWorld.convertGridToPos(gridXPos),bomberWorld.convertGridToPos(gridYPos));         
            addThrownBomb(newBomb);
            numBombThrown++;
        }
        
    }

    /**
     * Method findPowerUp
     *
     */
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
                    setLifes(getLifes() + foundPowerUp.getValue());
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
            bomberWorld.removeObject(foundPowerUp);
        }
    }
    protected void loadImage(GreenfootImage image)
    {
        if(image != null) 
        {
            if(isInvulnerable == true)
            {
                GreenfootImage alphaImage = new GreenfootImage(image);
                alphaImage.setTransparency(100);
                setImage(alphaImage);
            }
            else
            {
                setImage(image);
            }
            
        }
        
    }
     @Override
    protected void OnWorldLoaded()
    {
        loadAnimation(currentDirection);
        if(currentAnimation == null) return ;
        loadImage(currentAnimation.getCurrentImage());
        
    }
    
  
}
