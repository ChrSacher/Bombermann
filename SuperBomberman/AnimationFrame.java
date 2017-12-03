import greenfoot.*;
/**
 * Write a description of class AnimationFrame here.
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
