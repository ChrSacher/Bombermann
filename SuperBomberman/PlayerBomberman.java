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
    
    public static String[] WASDKEYS = {"w","s","a","d","space"};
    public static String[] ARROWKEYS = {"up","down","left","right","enter"};
    public static String[] IJKLKEYS = {"i","k","j","l","."};
    public static String[] NUMBERKEYS = {"5","2","1","3","+"};
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
    PlayerBomberman(PlayerColor newPlayerColor,int newMaxNumOfBombs, int newMovementSpeed,int newLifes,String[] newSet)
    {
        super(newPlayerColor,newMaxNumOfBombs,newMovementSpeed,newLifes);
        setKeySet(newSet);
        
    }
    void setKeySet(String[] newSet)
    {
        if(newSet.length != 5) return;
        keySet = newSet;
    }

    public void act()
    {
        super.act();
        handleKeys();
        
    }

    private void handleKeys()
    {
        
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
        if(Greenfoot.isKeyDown(keySet[InputKeys.ThrowBomb.ordinal()]))
        {
            dropBomb();
        }

    }
}
