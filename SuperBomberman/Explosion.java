import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Klasse welche Explosion in der Wlt darstellt und Kollisionen mit anderen Actors macht.
 * 
 * @author Dieu Huyen Dinh 
 * @version (a version number or a date)
 */
public class Explosion extends GridActor
{
    /*
     * Zeit bis Explosion aus der Welt entfernt wird.
     */
    private int explosionFadeTime = 100;
    
    /*
     * Wie lange die Explosion in der Welt existiert
     */
    private int lebenTime =0 ;
    /**
     * Act - do whatever the Explosion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        lebenTime = lebenTime + 1 ;
        checkCollision();
        if (lebenTime > explosionFadeTime)
        {
            bomberWorld.removeObject(this);
        }

    }    

    public void checkCollision()
    {
        List  < InteractableActor > inactor = getIntersectingObjects(InteractableActor.class);
        for ( InteractableActor i : inactor  )
        { 
            i.OnReceiveExplosion ();
        }

    }
    @Override
    protected void OnWorldLoaded()
    {
        setCenterImage();
        

    }
    
    Explosion(Explosion other)
    {
        explosionFadeTime = other.explosionFadeTime;
        lebenTime = other.lebenTime;

    }
    
    Explosion()
    {
        
    }
    
    /**
     * Method setEndImage
     * Setzt Endbild
     * @param dir Richtung
     */
    public void setEndImage(MovementDirection dir)
    {
        GreenfootImage image = bomberWorld.getStyleSheet().getExplosionEndImage(dir);
        setImage( image );
    }
    /**
     * Method setCenterImage
     * Setzt Mittelpunktbild
     */
    public void setCenterImage()
    {
        GreenfootImage image = bomberWorld.getStyleSheet().getExplosionCenterImage();
        setImage( image );
    }
    /**
     * Method setPieceImage
     * Setzt Mittelbild
     * @param dir Richtung
     */
    public void setPieceImage(MovementDirection dir)
    {
        GreenfootImage image = bomberWorld.getStyleSheet().getExplosionPieceImage(dir);
        setImage( image );
    }
    
}
