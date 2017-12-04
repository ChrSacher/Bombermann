import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Bomb here.
 * 
 * @author Dieu Huyen Dinh
 * @version (a version number or a date)
 */
public class Bomb extends InteractableActor
{
    private int explosionGridLength = 2;

    private Bomberman Besitzer;

    private int explosionTime = 150;

    private Explosion explosionTemplate = new Explosion();

    private int lebenTime = 0; 
    
    //nicht static weil mehrere gleichzeitig abspielen
    GreenfootSound explosionSound = new GreenfootSound("bomb.mp3");
    /**
     * @return the explosionGridLength
     */
    public int getExplosionGridLength() {
        return explosionGridLength;
    }

    /**
     * @param explosionGridLength the explosionGridLength to set
     */
    public void setExplosionGridLength(int explosionGridLength) {
        if(explosionGridLength <= 2) explosionGridLength = 2;
        this.explosionGridLength = explosionGridLength;
    }

    /**
     * @return the besitzer
     */
    public Bomberman getBesitzer() {
        return Besitzer;
    }

    /**
     * @param besitzer the besitzer to set
     */
    public void setBesitzer(Bomberman besitzer) {
        Besitzer = besitzer;
    }

    /**
     * @return the explosionTime
     */
    public int getExplosionTime() {
        return explosionTime;
    }

    /**
     * @param explosionTime the explosionTime to set
     */
    public void setExplosionTime(int explosionTime) {
        this.explosionTime = explosionTime;
    }

    /**
     * @return the explosionTemplate
     */
    public Explosion getExplosionTemplate() {
        return explosionTemplate;
    }

    /**
     * @param explosionTemplate the explosionTemplate to set
     */
    public void setExplosionTemplate(Explosion explosionTemplate) {
        this.explosionTemplate = explosionTemplate;
    }

    /**
     * @return the lebenTime
     */
    public int getLebenTime() {
        return lebenTime;
    }

    /**
     * @param lebenTime the lebenTime to set
     */
    public void setLebenTime(int lebenTime) {
        this.lebenTime = lebenTime;
    } 

    /**
     * Act - do whatever the Bomb wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        lebenTime = lebenTime + 1 ;
        if (lebenTime > explosionTime)
        {
            explodier();
        }
    }    

    /**
     * Method canContinueExplosion
     *
     * @param x A parameter
     * @param y A parameter
     * @return The return value
     */
    public ExplosionHandling getHandlingAtPoint( int x, int y)
    {
        int currentX  = getX();
        int currentY = getY();
        setLocation(x,y);
        List<InteractableActor> actors = getIntersectingObjects(InteractableActor.class);
        ExplosionHandling typeAtField = ExplosionHandling.None;
        for(InteractableActor actor :actors)
        {
            if(actor.getExplosionHandlingType() == ExplosionHandling.Block)
            {
               typeAtField = ExplosionHandling.Block;
               break;
            }
             if(actor.getExplosionHandlingType() == ExplosionHandling.Receive)
            {
               typeAtField = ExplosionHandling.Receive; 
            }
        }
        
        setLocation(currentX,currentY);
        return typeAtField;
    }

    public void explodier()
    {
        Explosion sondernexplosion = new Explosion ( );
        explosionSound.play();
        bomberWorld.addObject(sondernexplosion, getX(), getY());
         sondernexplosion.setCenterImage();
        for ( int i = 1; i< explosionGridLength; i++)
        {

            int exposition = gridXPos + i;
            int exppixel = bomberWorld.convertGridToPos( exposition );
            ExplosionHandling canC = getHandlingAtPoint(exppixel,getY());
            if(canC == ExplosionHandling.Receive)
            {
                Explosion explosion = new Explosion(explosionTemplate);
                bomberWorld.addObject(explosion, exppixel, getY( ));
                if(i < explosionGridLength - 1)
                {
                    explosion.setPieceImage(MovementDirection.Right);
                }
                else
                {
                    explosion.setEndImage(MovementDirection.Right);
                }
                break;
            }
            if(canC == ExplosionHandling.Block)
            {
                break;
            }
            if(canC == ExplosionHandling.None)
            {
                Explosion explosion = new Explosion(explosionTemplate);
                bomberWorld.addObject(explosion, exppixel, getY( ));
                if(i < explosionGridLength - 1)
                {
                    explosion.setPieceImage(MovementDirection.Right);
                }
                else
                {
                    explosion.setEndImage(MovementDirection.Right);
                }
            }

        }

        for ( int i = 1; i< explosionGridLength; i++)
        {
            int exposition = gridXPos - i;
            int exppixel = bomberWorld.convertGridToPos( exposition );

           ExplosionHandling canC = getHandlingAtPoint(exppixel,getY());
            if(canC == ExplosionHandling.Receive)
            {
                Explosion explosion = new Explosion(explosionTemplate);
                bomberWorld.addObject(explosion, exppixel, getY( ));
                if(i < explosionGridLength - 1)
                {
                    explosion.setPieceImage(MovementDirection.Left);
                }
                else
                {
                    explosion.setEndImage(MovementDirection.Left);
                }
                break;
            }
            if(canC == ExplosionHandling.Block)
            {
                break;
            }
            if(canC == ExplosionHandling.None)
            {
                Explosion explosion = new Explosion(explosionTemplate);
                bomberWorld.addObject(explosion, exppixel, getY( ));
                if(i < explosionGridLength - 1)
                {
                    explosion.setPieceImage(MovementDirection.Left);
                }
                else
                {
                    explosion.setEndImage(MovementDirection.Left);
                }
            }
        }

        for ( int i = 1; i< explosionGridLength; i++)
        {
            int exposition = gridYPos + i;
            int ypixel = bomberWorld.convertGridToPos( exposition );

            ExplosionHandling canC = getHandlingAtPoint(getX(),ypixel);
            if(canC == ExplosionHandling.Receive)
            {
                Explosion explosion = new Explosion(explosionTemplate);
                bomberWorld.addObject(explosion, getX(),ypixel);
                if(i < explosionGridLength - 1)
                {
                    explosion.setPieceImage(MovementDirection.Down);
                }
                else
                {
                    explosion.setEndImage(MovementDirection.Down);
                }
                break;
            }
            if(canC == ExplosionHandling.Block)
            {
                break;
            }
            if(canC == ExplosionHandling.None)
            {
                Explosion explosion = new Explosion(explosionTemplate);
                bomberWorld.addObject(explosion, getX(),ypixel);
                if(i < explosionGridLength - 1)
                {
                    explosion.setPieceImage(MovementDirection.Down);
                }
                else
                {
                    explosion.setEndImage(MovementDirection.Down);
                }
            }
        }

        for ( int i = 1; i< explosionGridLength; i++)
        {
            int exposition = gridYPos - i;
            int ypixel = bomberWorld.convertGridToPos( exposition );

            ExplosionHandling canC = getHandlingAtPoint(getX(),ypixel);
            if(canC == ExplosionHandling.Receive)
            {
                Explosion explosion = new Explosion(explosionTemplate);
                bomberWorld.addObject(explosion, getX(),ypixel);
                if(i < explosionGridLength - 1)
                {
                    explosion.setPieceImage(MovementDirection.Up);
                }
                else
                {
                    explosion.setEndImage(MovementDirection.Up);
                }
                break;
            }
            if(canC == ExplosionHandling.Block)
            {
                break;
            }
            if(canC == ExplosionHandling.None)
            {
                Explosion explosion = new Explosion(explosionTemplate);
                bomberWorld.addObject(explosion, getX(),ypixel);
                if(i < explosionGridLength - 1)
                {
                    explosion.setPieceImage(MovementDirection.Up);
                }
                else
                {
                    explosion.setEndImage(MovementDirection.Up);
                }
            }
        }

        if(Besitzer != null) Besitzer.removeThrownBomb(this);
        else System.out.println("nope");
        bomberWorld.removeObject(this);

    }
    public Bomb()
    {

    }

    public Bomb(Bomb otherBomb)
    {
        explosionGridLength = otherBomb.explosionGridLength;
        Besitzer = otherBomb.Besitzer;   
        explosionTime =  otherBomb.explosionTime;   
        explosionTemplate = new Explosion(otherBomb.explosionTemplate);  
        lebenTime  = otherBomb.lebenTime; 
    }

    @Override
    protected void OnWorldLoaded()
    {
        GreenfootImage image = bomberWorld.getStyleSheet().getBombImage();
        setImage( image );

    }
}
