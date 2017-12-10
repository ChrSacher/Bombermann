import greenfoot.*;
import java.util.ArrayList;
/**
 * Speichert Animationsframes und berechnet das jetzige Bild.
 * 
 * @author Christian Sacher
 * @version (a version number or a date)
 */
public class Animation  
{
    // Zugehöriges Interface
    protected AnimationInterface attachedInterface = null;

    //liste aller Frames
    protected ArrayList<AnimationFrame> frames = new ArrayList<AnimationFrame>();

    //An  welcher Stelle in der Animation sind wir
    protected int animationFrameCounter = 0;

    //Unser jeztiges Bild
    protected AnimationFrame currentFrame = null;

    public Animation(Animation other)
    {
       attachedInterface = other.attachedInterface;
       frames = other.frames;
       animationFrameCounter  = other.animationFrameCounter;
       currentFrame = other.currentFrame;
    }
    
    public Animation()
    {
    }

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
        
        //Neue Brechnung unseres jetztigen Bildes
        setAnimationFrameCounter(animationFrameCounter);
    }

    /**
     * @return the attachedInterface
     */
    public AnimationInterface getAttachedInterface() 
    {
        return attachedInterface;
    }

    /**
     * @param attachedInterface Interface welches gerufen wird
     * Ruft OnNextAnimation auf wenn ein valides Interface gesetzt wird
     */
    public void setAttachedInterface(AnimationInterface attachedInterface) 
    {
        this.attachedInterface = attachedInterface;
        if(attachedInterface != null && frames.size() > 0 )
        {
            attachedInterface.OnNextAnimation(frames.get(0).getFrameImage());
        }
    }

    /**
     * @return  Jetztiger Frame
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
     * @param Neues Frame
     * Rufe OnNextAnimation, falls es nicht das alte ist
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
     * @param frames Liste von AnimationFrame
     */
    public void setFrames(ArrayList<AnimationFrame> frames) 
    {
        
        this.frames = frames;
    }

    /**
     * @return  animationFrameCounter
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
        //Offset vom Anfang da nur die Zeit eines Frames gespeichert wurde
        int currentOffset = 0;
        //berechne welcher Frame jetzt gerade ist
        
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
    /*
     * @return Jetztiges Bild in der Animations
     */
    public GreenfootImage getCurrentImage()
    {
        int currentOffset = 0;
        for(AnimationFrame frame : frames)
        {
            if(currentOffset + frame.getFrameTime() > animationFrameCounter && currentOffset <= animationFrameCounter)
            {
                setCurrentFrame(frame);
                return currentFrame.getFrameImage();
            }
            currentOffset += frame.getFrameTime();
        }
        return null;
    }

}
