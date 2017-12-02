import greenfoot.*;
import java.util.ArrayList;
/**
 * Write a description of class Animation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Animation  
{
    // instance variables - replace the example below with your own
    protected AnimationInterface attachedInterface = null;

    protected ArrayList<AnimationFrame> frames = new ArrayList<AnimationFrame>();

    protected int animationFrameCounter = 0;

    protected AnimationFrame currentFrame = null;

    /**
     * Method addAnimationFrame
     *
     * @param path A parameter
     * @param time A parameter
     */
    public void addAnimationFrame(String path,int time )
    {
        AnimationFrame fra =  new AnimationFrame(path,time);
        addAnimationFrame(fra);
    }

    public void addAnimationFrame(AnimationFrame newFrame )
    {
        if(newFrame !=null ) frames.add(newFrame);
    }

    public void removeAnimationFrame(AnimationFrame newFrame )
    {
        if(newFrame !=null ) frames.remove(newFrame);
    }

    /**
     * @return the attachedInterface
     */
    public AnimationInterface getAttachedInterface() 
    {
        return attachedInterface;
    }

    /**
     * @param attachedInterface the attachedInterface to set
     */
    public void setAttachedInterface(AnimationInterface attachedInterface) 
    {
        this.attachedInterface = attachedInterface;
        if(getAttachedInterface() != null && frames.size() > 0 )
        {
            getAttachedInterface().OnNextAnimation(frames.get(0).getFrameImage());
        }
    }

    /**
     * @return the attachedInterface
     */
    public AnimationFrame getCurrentFrame() 
    {
        int currentOffset = 0;
        for(AnimationFrame frame : frames)
        {
            if(currentOffset + frame.getFrameTime() <= animationFrameCounter)
            {
                setCurrentFrame(frame);
         
            }
            currentOffset += frame.getFrameTime();
        }
        return null;
    }

    /**
     * @param attachedInterface the attachedInterface to set
     */
    public void setCurrentFrame(AnimationFrame currentFrame) 
    {
        if(currentFrame != this.currentFrame && currentFrame != null)
        {
            this.currentFrame = currentFrame;
            if(getAttachedInterface() != null)
            {
                getAttachedInterface().OnNextAnimation(this.currentFrame.getFrameImage());
            }
        }

    }

    /**
     * @return the frames
     */
    public ArrayList<AnimationFrame> getFrames() 
    {
        return frames;
    }

    /**
     * @param frames the frames to set
     */
    public void setFrames(ArrayList<AnimationFrame> frames) 
    {
        
        this.frames = frames;
    }

    /**
     * @return the animationFrameCounter
     */
    public int getAnimationFrameCounter() 
    {
        return animationFrameCounter;
    }

    /**
     * @return the animationFrameCounter
     */
    public int getAnimationFrameCount() 
    {
        int frameCount = 0;
        for(AnimationFrame frame : frames)
        {
            frameCount += frame.getFrameTime();
        }
        return frameCount;
    }

    /**
     * @param animationFrameCounter the animationFrameCounter to set
     */
    public void setAnimationFrameCounter(int newAnimationFrameCounter) 
    {

        int frameCount = getAnimationFrameCount();
        animationFrameCounter = newAnimationFrameCounter % frameCount;
        int currentOffset = 0;
        for(AnimationFrame frame : frames)
        {
            if(currentOffset + frame.getFrameTime() >= animationFrameCounter)
            {
                setCurrentFrame(frame);
                return;
            }
            currentOffset += frame.getFrameTime();
        }
    }

    public GreenfootImage getCurrentImage()
    {
        int currentOffset = 0;
        for(AnimationFrame frame : frames)
        {
            if(currentOffset + frame.getFrameTime() <= animationFrameCounter)
            {
                return frame.getFrameImage();
         
            }
            currentOffset += frame.getFrameTime();
        }
        return null;
    }

}
