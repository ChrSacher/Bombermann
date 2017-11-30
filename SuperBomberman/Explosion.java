import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Explosion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Explosion extends GridActor
{
    private int explosionFadeTime = 100;
    
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

    protected void loadImage()
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
    
    public void setEndImage(MovementDirection dir)
    {
        GreenfootImage image = bomberWorld.getStyleSheet().getExplosionEndImage(dir);
        setImage( image );
    }
    public void setCenterImage()
    {
        GreenfootImage image = bomberWorld.getStyleSheet().getExplosionCenterImage();
        setImage( image );
    }
    public void setPieceImage(MovementDirection dir)
    {
        GreenfootImage image = bomberWorld.getStyleSheet().getExplosionPieceImage(dir);
        setImage( image );
    }
    
}
