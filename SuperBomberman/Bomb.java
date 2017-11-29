import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Bomb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bomb extends InteractableActor
{
    private int explosionGridLength = 4;
    private Bomberman Besitzer;
    private int explosionTime = 150;
    private Class < Explosion > explosionclass = Explosion.class;
    private int lebenTime = 0; 
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

    public boolean canContinueExplosion( int x, int y)
    {
        Obstacle obtacles = (Obstacle)getOneObjectAtOffset(x,y, Obstacle.class);
        if ( obtacles == null )
       {
           return true;
        }
        return false;
    }
    
    public boolean isValidExplosionLocation(int x, int y)
    {
    
        List <Obstacle> obtacles = getObjectsAtOffset(x,y, Obstacle.class);
        for ( Obstacle o : obtacles)
        {
            
            if (o.getIsDestructable()== false)
            { 
                return false;
            }
        }
       return true;
       
    }
    
    public void explodier()
    {
         Explosion sondernexplosion = new Explosion ( );
        bomberWorld.addObject(sondernexplosion, getX(), getY());
        
       
        for ( int i = 1; i< explosionGridLength; i++)
        {
            
            int exposition = gridXPos + i;
            int exppixel = bomberWorld.convertGridToPos( exposition );
            boolean canC = canContinueExplosion(exppixel-getX(),0);
            boolean isV = isValidExplosionLocation (exppixel-getX(),0 );
            if (isV == true)
            {
                Explosion explosion = new Explosion();
                bomberWorld.addObject(explosion, exppixel, getY( ));
            }
            
      
          
            
            if( canC == false)
            {
                break;
            }
           
        }

        for ( int i = 1; i< explosionGridLength; i++)
        {
            int exposition = gridXPos - i;
            int exppixel = bomberWorld.convertGridToPos( exposition );
       
            boolean canC = canContinueExplosion(exppixel-getX(),0);
            boolean isV = isValidExplosionLocation (exppixel-getX(),0 );
            if (isV == true)
            {
                Explosion explosion = new Explosion();
                bomberWorld.addObject(explosion, exppixel, getY( ));
            }
            
      
          
            
            if( canC == false)
            {
                break;
            }
        }
        
        for ( int i = 1; i< explosionGridLength; i++)
        {
            int exposition = gridYPos + i;
            int ypixel = bomberWorld.convertGridToPos( exposition );

            boolean canC = canContinueExplosion(0,ypixel-getY());
            boolean isV = isValidExplosionLocation (0,ypixel-getY() );
            if (isV == true)
            {
                Explosion explosion = new Explosion();
                bomberWorld.addObject(explosion, getX(),ypixel);
            }
            
      
          
            
            if( canC == false)
            {
                break;
            }
        }
        
        for ( int i = 1; i< explosionGridLength; i++)
        {
            int exposition = gridYPos - i;
            int ypixel = bomberWorld.convertGridToPos( exposition );

      
           boolean canC = canContinueExplosion(0,ypixel-getY());
            boolean isV = isValidExplosionLocation (0,ypixel-getY() );
            if (isV == true)
            {
                Explosion explosion = new Explosion();
                bomberWorld.addObject(explosion, getX(),ypixel);
            }
            
      
          
            
            if( canC == false)
            {
                break;
            }
        }

       
        bomberWorld.removeObject(this);

     
    }

    public Bomb()
    {

    }

    public Bomb(Bomb otherBomb)
    {

    }

    public int getExplosionGridLength()
    {
        return explosionGridLength;
    }

    public void setExplosionGridLength(int newLength)
    {
        explosionGridLength = newLength;
    }

    protected void loadImage()
    {
        GreenfootImage image = bomberWorld.getStyleSheet().getBombImage();
        setImage( image );

    }
}
