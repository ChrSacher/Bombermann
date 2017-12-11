import greenfoot.*;
/**
 * Interface für Animationen
 * Methoden für ein neues Bild, Animation ist auf dem ersten bild und Animation ist auf dem letzten Bild
 * 
 * @author Christian Sacher 
 * @version 11.12.17
 */
public interface AnimationInterface  
{
   /**
     * Method OnNextAnimation 
     *
     * @param newImage Neues Bild aus der Animation
     */
   public void OnNextAnimation(GreenfootImage newImage);
   
   //public void OnAnimationStarted();
   
   //public void OnAnimationEnded();
}
