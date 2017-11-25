import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GridActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GridActor extends Actor
{
    protected BomberWorld bomberWorld = null;
    public void addedToWorld(World world)
    {
        bomberWorld = (BomberWorld)world;
        setLocation(bomberWorld.roundToGrid(getX()),bomberWorld.roundToGrid(getY()));
    }
}
