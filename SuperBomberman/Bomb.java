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
        explosionSound.play();
        bomberWorld.addObject(sondernexplosion, getX(), getY());
         sondernexplosion.setCenterImage();
        for ( int i = 1; i< explosionGridLength; i++)
        {

            int exposition = gridXPos + i;
            int exppixel = bomberWorld.convertGridToPos( exposition );
            boolean canC = canContinueExplosion(exppixel-getX(),0);
            boolean isV = isValidExplosionLocation (exppixel-getX(),0 );
            if (isV == true)
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
                Explosion explosion = new Explosion(explosionTemplate);
                 bomberWorld.addObject(explosion, getX(),ypixel);
                if(i < explosionGridLength -1)
                {
                    explosion.setPieceImage(MovementDirection.Down);
                }
                else
                {
                    explosion.setEndImage(MovementDirection.Down);
                }
               
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
                Explosion explosion = new Explosion(explosionTemplate);
                bomberWorld.addObject(explosion, getX(),ypixel);
                if(i < explosionGridLength -1)
                {
                    explosion.setPieceImage(MovementDirection.Up);
                }
                else
                {
                    explosion.setEndImage(MovementDirection.Up);
                }
                
            }

          
            if( canC == false)
            {
                break;
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
    protected void OnLoadWorldImage()
    {
        GreenfootImage image = bomberWorld.getStyleSheet().getBombImage();
        setImage( image );

    }
}
