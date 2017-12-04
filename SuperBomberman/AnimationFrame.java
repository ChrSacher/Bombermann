import greenfoot.*;
/**
 * Einfach Klasse zum speichern von Animationsbildern mit einer bestimmten Anzeige Zeit
 * 
 * @author Christian Sacher
 * @version (a version number or a date)
 */
public class AnimationFrame  
{
    // instance variables - replace the example below with your own
    protected int frameTime = 5;
    
    protected GreenfootImage frameImage = null;
    
    AnimationFrame()
    {
        
    }
    AnimationFrame(String path,int time)
    {
        setImage(path);
        setFrameTime(time);
    }
    public void setFrameTime(int newFrameTime)
    {
        frameTime = newFrameTime;
    }
    
    public void setImage(String newImage)
    {
        setImage(new GreenfootImage(newImage));
    }
    
    public void setImage(GreenfootImage newImage)
    {
        if(newImage != null) frameImage = newImage;
    }
    
    public GreenfootImage getFrameImage()
    {
        return frameImage;
    }
    
    public int getFrameTime()
    {
        return frameTime;
    }
}
