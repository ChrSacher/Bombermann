import greenfoot.*;
/**
 * Interface für Animationen
 * 
 * @author Christian Sacher 
 * @version (a version number or a date)
 */
public interface AnimationInterface  
{
   /*
    * Wird gerufen , wenn sich ein AnimationFrame einer Animation ändert.
    */
   public void OnNextAnimation(GreenfootImage newImage);
   
}
