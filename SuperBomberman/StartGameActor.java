import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Klasse die das Spiel mithilfe eines Klicks starten kann.
 * 
 * @author Christian Sacher
 * @version 10.12.2017
 */
public class StartGameActor extends Actor
{
    /**
     * Act - do whatever the StartGameActor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
      if (Greenfoot.mouseClicked(this)) 
      {
        BomberWorld world = (BomberWorld)getWorld();
        world.TestScenario();
        world.removeObject(this);
        return;
      }   
    }    
    
}
