import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Klasse um auf Explosionen zu reagieren und diese ein wenig zu steuern
 * 
 * @author Dieu Huyen Dinh
 * @version (a version number or a date)
 */
public class InteractableActor extends GridActor
{
    
    protected ExplosionHandling ExplosionHandlingType =  ExplosionHandling.None;
    
    public void setExplosionHandlingType(ExplosionHandling newHandlign)
    {
        ExplosionHandlingType = newHandlign;
    }
    
    public ExplosionHandling getExplosionHandlingType()
    {
        return ExplosionHandlingType;
    }
    /*
     * method OnReceiveExplosion
     * Methode wird aufgerufen wenn der Actor von einer Explosion getroffen wurde. Kann mehrmals bei einer Explosion aufgerufen werden.
     */
    protected void OnReceiveExplosion()
    { 
    }
    
    /**
     * Act - do whatever the InteractableActor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
