import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Klasse die eine Bombe in der Welt darstellt. Sie kann explodieren und Explosionen erzeugen.
 * 
 * @author Dieu Huyen Dinh
 * @version (a version number or a date)
 */
public class Bomb extends InteractableActor
{
    /*
     * Länge der Explosionen
     */
    private int explosionGridLength = 2;
    /*
     * Wem gehört diese Bombe
     */
    private Bomberman Besitzer;

    /*
     * Zeit bis zur Explosion
     */
    private int explosionTime = 150;

    /*
     * Template einer Explosion
     */
    private Explosion explosionTemplate = new Explosion();

    /*
     * Wie lange der Actor am Leben ist.
     */
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
     * @param explosionGridLength neue Explosionslänge 2<=x<=5
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

    public void setBesitzer(Bomberman besitzer) {
        Besitzer = besitzer;
    }
    public int getExplosionTime() {
        return explosionTime;
    }

    public void setExplosionTime(int explosionTime) {
        this.explosionTime = explosionTime;
    }

   
    public Explosion getExplosionTemplate() {
        return explosionTemplate;
    }

  
    public void setExplosionTemplate(Explosion explosionTemplate) {
        this.explosionTemplate = explosionTemplate;
    }

  
    public int getLebenTime() {
        return lebenTime;
    }

   
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
     * @param x X Pixel in der Welt
     * @param y Y Pixel in der Welt
     * @return Höchstes Handling an der Stell (x,y)
     */
    public ExplosionHandling getHandlingAtPoint( int x, int y)
    {
        int currentX  = getX();
        int currentY = getY();
        //Für Kollision muss der Actor bewegt werden,
        setLocation(x,y);
        
        List<InteractableActor> actors = getIntersectingObjects(InteractableActor.class);
        ExplosionHandling typeAtField = ExplosionHandling.None;
        
        
        for(InteractableActor actor :actors)
        {
            //Block ist wichtiger als Receive und None
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
        Logger.log("Bombe explodiert");
        Explosion sondernexplosion = new Explosion ( );
        explosionSound.play();
        bomberWorld.addObject(sondernexplosion, getX(), getY());
         sondernexplosion.setCenterImage();
        for ( int i = 1; i< explosionGridLength; i++)
        {
            //i-te Feld nach rechts
            int exposition = gridXPos + i;
            int exppixel = bomberWorld.convertGridToPos( exposition );
            
            ExplosionHandling handlingType = getHandlingAtPoint(exppixel,getY());
            //wenn handling auf dem Feld == Receive muss Explosion erstellt werden und aufgehört werden
            if(handlingType == ExplosionHandling.Receive)
            {
                Explosion explosion = new Explosion(explosionTemplate);
                bomberWorld.addObject(explosion, exppixel, getY( ));
                //Letzte Stück muss Endstück sein
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
            if(handlingType == ExplosionHandling.Block)
            {
                //Aufhören
                break;
            }
            //Explosion erstellen und weitermachen
            if(handlingType == ExplosionHandling.None)
            {
                Explosion explosion = new Explosion(explosionTemplate);
                bomberWorld.addObject(explosion, exppixel, getY( ));
                 //Letzte Stück muss Endstück sein
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
            //i-te Feld nach links
            int exposition = gridXPos - i;
            int exppixel = bomberWorld.convertGridToPos( exposition );

            ExplosionHandling handlingType = getHandlingAtPoint(exppixel,getY());
            if(handlingType == ExplosionHandling.Receive)
            {
                Explosion explosion = new Explosion(explosionTemplate);
                bomberWorld.addObject(explosion, exppixel, getY( ));
                 //Letzte Stück muss Endstück sein
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
            if(handlingType == ExplosionHandling.Block)
            {
                break; //aufhörem
            }
            //Explosion erstellen und weitermachen
            if(handlingType == ExplosionHandling.None)
            {
                Explosion explosion = new Explosion(explosionTemplate);
                bomberWorld.addObject(explosion, exppixel, getY( ));
                 //Letzte Stück muss Endstück sein
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
            //i-te Feld nach unten
            int exposition = gridYPos + i;
            int ypixel = bomberWorld.convertGridToPos( exposition );

            ExplosionHandling handlingType = getHandlingAtPoint(getX(),ypixel);
            if(handlingType == ExplosionHandling.Receive)
            {
                Explosion explosion = new Explosion(explosionTemplate);
                bomberWorld.addObject(explosion, getX(),ypixel);
                 //Letzte Stück muss Endstück sein
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
            if(handlingType == ExplosionHandling.Block)
            {
                break; //Aufhören
            }
            //Explosion erstellen und weitermachen
            if(handlingType == ExplosionHandling.None)
            {
                Explosion explosion = new Explosion(explosionTemplate);
                bomberWorld.addObject(explosion, getX(),ypixel);
                 //Letzte Stück muss Endstück sein
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
            //i-te Feld nach oben
            int exposition = gridYPos - i;
            int ypixel = bomberWorld.convertGridToPos( exposition );

            ExplosionHandling handlingType = getHandlingAtPoint(getX(),ypixel);
            if(handlingType == ExplosionHandling.Receive)
            {
                Explosion explosion = new Explosion(explosionTemplate);
                bomberWorld.addObject(explosion, getX(),ypixel);
                 //Letzte Stück muss Endstück sein
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
            if(handlingType == ExplosionHandling.Block)
            {
                break; //aufhören
            }
             //Letzte Stück muss Endstück sein
            if(handlingType == ExplosionHandling.None)
            {
                Explosion explosion = new Explosion(explosionTemplate);
                bomberWorld.addObject(explosion, getX(),ypixel);
                 //Letzte Stück muss Endstück sein
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
        else Logger.logError("Bombe hat keinen Besitzer");
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
