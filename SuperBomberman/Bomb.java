import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bomb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bomb extends InteractableActor
{
    private int explosionGridLength = 2;
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
    
    public void explodier()
    {
     bomberWorld.removeObject(this);
     Explosion explosion = new Explosion ( );
    bomberWorld.addObject(explosion, 25,25);
 
     
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
}
