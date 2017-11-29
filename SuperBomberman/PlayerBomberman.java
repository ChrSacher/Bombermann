import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayerBomberman here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerBomberman extends Bomberman
{
    private String keySet[] = new String[5];

    /**
     * Constructor for objects of class PlayerBomberman.
     * 
     */
    public PlayerBomberman()
    {
        keySet[InputKeys.Up.ordinal()] = "w";
        keySet[InputKeys.Down.ordinal()] = "s";
        keySet[InputKeys.Left.ordinal()] = "a";
        keySet[InputKeys.Right.ordinal()] = "d";
        keySet[InputKeys.ThrowBomb.ordinal()] = "space";

    }

    void setKeySet(String[] newSet)
    {
        if(newSet.length != 5) return;
        keySet = newSet;
    }

    public void act()
    {
        handleKeys();
    }

    private void handleKeys()
    {
        System.out.println(Greenfoot.getKey());
        if(Greenfoot.isKeyDown(keySet[InputKeys.Up.ordinal()]))
        {
            decideMove(MovementDirection.Up);
        }
        else if(Greenfoot.isKeyDown(keySet[InputKeys.Down.ordinal()]))
        {
            decideMove(MovementDirection.Down);
        }
        else if(Greenfoot.isKeyDown(keySet[InputKeys.Left.ordinal()]))
        {
            decideMove(MovementDirection.Left);
        }
        else if(Greenfoot.isKeyDown(keySet[InputKeys.Right.ordinal()]))
        {
            decideMove(MovementDirection.Right);
        }
        else if(Greenfoot.isKeyDown(keySet[InputKeys.ThrowBomb.ordinal()]))
        {
            dropBomb();
        }

    }
}
