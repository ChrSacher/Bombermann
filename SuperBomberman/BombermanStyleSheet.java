import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Bomberman has a certain style for the game. The Images do not change.
 * This class holds are standard image information in a normal Bomber man game.
 * An advantage of using this class is that we have all pictures in 1 place and can access them in this 1 place
 * If we wanted to change the images all we would have to do is to change it in this class and tell every other class to just grab it.
 * 
 * @author Christian Sacher 
 * @version 24.11.2017
 */
public class BombermanStyleSheet  
{
   
    
    private Animation bombermanAnimations[][] = new Animation[PlayerColor.values().length][MovementDirection.values().length];
    
    private GreenfootImage wallImage = new GreenfootImage("/SpriteSheetImages/FloorTiles/Wall.png");

    private GreenfootImage obstacleImage = new GreenfootImage("/SpriteSheetImages/FloorTiles/Obstacle.png");

    private GreenfootImage floorTile = new GreenfootImage("/SpriteSheetImages/FloorTiles/Floor3.png");

    private GreenfootImage edgeFloorTile = new GreenfootImage("/SpriteSheetImages/FloorTiles/Edge_Floor.png");

    private GreenfootImage bombImage = new GreenfootImage("/SpriteSheetImages/Bomb/Bomb.png");

    /*
     * Die PowerUp Bilder werden nach folgender Reihenfolge gespeichert. Nach Reihenfolge in der Enum Deklarierung . Erst Positiv dann negativ.
     */
    private GreenfootImage powerUpImages[] = new GreenfootImage[PowerUpType.values().length * 2  ];

    /*
     * The explosion images are 9 images. 1 center piece, 4 middle pieces and 4 end pieces. The order of  the images is center piece then middle piece and end piece for each of the MovementDirections
     */
    private GreenfootImage explosionImages[] = new GreenfootImage[1 + MovementDirection.values().length * 2];

    public GreenfootImage getPowerupImage(PowerUpType type,int value)
    {
        if(value >= 0)
        {
            return powerUpImages[type.ordinal() * 2];
        }
        else
        {
            return powerUpImages[type.ordinal() * 2 +1];
        }
        
    }
    public GreenfootImage getWallImage()
    {
        return wallImage;
    }

    public GreenfootImage getObstacleImage()
    {
        return obstacleImage;
    }

    public GreenfootImage getFloorTileImage()
    {
        return floorTile;
    }

    public GreenfootImage getEdgeFloorTileImage()
    {
        return edgeFloorTile;
    }

    public GreenfootImage getBombImage()
    {
        return bombImage;
    }


    
    public Animation getBombermanAnimation(PlayerColor color,MovementDirection direction)
    {
        return bombermanAnimations[color.ordinal()][direction.ordinal()];
    }
    
    public GreenfootImage getExplosionCenterImage()
    {
        return explosionImages[0];
    }

    public GreenfootImage getExplosionEndImage(MovementDirection direction)
    {
        return explosionImages[direction.ordinal() * 2 + 2];
    }

    public GreenfootImage getExplosionPieceImage(MovementDirection direction)
    {
        return explosionImages[direction.ordinal() * 2 + 1];
    }

    /**
     * Constructor for objects of class BombermanStyleSheet
     */
    public BombermanStyleSheet()
    {

    }

    /**
     * loadImages - Diese Methode lädt alle Bilder die für Bomberman gebraucht werden.
     */
    public void loadImages()
    {
        //Wir haben nur Bilder für max 4 Spieler

        //ordinal damit Reihenfolge egal ist
        
       
        
        String piecePath = "/SpriteSheetImages/Explosion/Piece.png";
        String endPath =  "/SpriteSheetImages/Explosion/End.png";

        //load explosion images
        explosionImages[0] = new GreenfootImage("/SpriteSheetImages/Explosion/Center.png");
        explosionImages[MovementDirection.Up.ordinal() * 2 + 1] = new GreenfootImage(piecePath);
        explosionImages[MovementDirection.Up.ordinal() * 2 + 1].rotate(-90);
        explosionImages[MovementDirection.Up.ordinal() * 2 + 2] = new GreenfootImage(endPath);
        explosionImages[MovementDirection.Up.ordinal() * 2 + 2].rotate(-90);

        explosionImages[MovementDirection.Down.ordinal() * 2 + 1] = new GreenfootImage(piecePath);
        explosionImages[MovementDirection.Down.ordinal() * 2 + 1].rotate(90);
        explosionImages[MovementDirection.Down.ordinal() * 2 + 2] = new GreenfootImage(endPath);
        explosionImages[MovementDirection.Down.ordinal() * 2 + 2].rotate(90);

        explosionImages[MovementDirection.Left.ordinal() * 2 + 1] = new GreenfootImage(piecePath);
        explosionImages[MovementDirection.Left.ordinal() * 2 + 1].rotate(180);
        explosionImages[MovementDirection.Left.ordinal() * 2 + 2] = new GreenfootImage(endPath);
        explosionImages[MovementDirection.Left.ordinal() * 2 + 2].rotate(180);

        explosionImages[MovementDirection.Right.ordinal() * 2 + 1] = new GreenfootImage(piecePath);
        //explosionImages[MovementDirection.Right.ordinal() * 2 + 1].rotate(-90);
        explosionImages[MovementDirection.Right.ordinal() * 2 + 2] = new GreenfootImage(endPath);
        //explosionImages[MovementDirection.Right.ordinal() * 2 + 2].rotate(-90);

        powerUpImages[PowerUpType.Speed.ordinal() * 2] = new GreenfootImage("/SpriteSheetImages/Powerups/SpeedUp.png");
        powerUpImages[PowerUpType.Speed.ordinal() * 2 + 1] = new GreenfootImage("/SpriteSheetImages/Powerups/SpeedDown.png");   

        powerUpImages[PowerUpType.Ammount.ordinal() * 2] = new GreenfootImage("/SpriteSheetImages/Powerups/BombsUp.png");
        powerUpImages[PowerUpType.Ammount.ordinal() * 2 + 1] = new GreenfootImage("/SpriteSheetImages/Powerups/BombsDown.png");  

        powerUpImages[PowerUpType.Strength.ordinal() * 2] = new GreenfootImage("/SpriteSheetImages/Powerups/LengthUp.png");
        powerUpImages[PowerUpType.Strength.ordinal() * 2 + 1] = new GreenfootImage("/SpriteSheetImages/Powerups/LengthDown.png");  

        powerUpImages[PowerUpType.Death.ordinal() * 2] = new GreenfootImage("/SpriteSheetImages/Powerups/Death.png");
        powerUpImages[PowerUpType.Death.ordinal() * 2 + 1] = new GreenfootImage("/SpriteSheetImages/Powerups/Death2.png");  
        
        
        
        String paths[] = new String[PlayerColor.values().length];
        paths[PlayerColor.White.ordinal()] = "/SpriteSheetImages/Bomberman/WhiteBomberman/";
        paths[PlayerColor.Black.ordinal()] = "/SpriteSheetImages/Bomberman/BlackBomberman/";
        paths[PlayerColor.Red.ordinal()] = "/SpriteSheetImages/Bomberman/RedBomberman/";
        paths[PlayerColor.Blue.ordinal()] = "/SpriteSheetImages/Bomberman/BlueBomberman/";
        
        for(PlayerColor color : PlayerColor.values())
        {
            bombermanAnimations[color.ordinal()][MovementDirection.Up.ordinal()] = new Animation();
            bombermanAnimations[color.ordinal()][MovementDirection.Up.ordinal()].addAnimationFrame( paths[color.ordinal()] + "Up_0.png",6);
            bombermanAnimations[color.ordinal()][MovementDirection.Up.ordinal()].addAnimationFrame( paths[color.ordinal()] + "Up_1.png",6);
            bombermanAnimations[color.ordinal()][MovementDirection.Up.ordinal()].addAnimationFrame( paths[color.ordinal()] + "Up_0.png",6);
            bombermanAnimations[color.ordinal()][MovementDirection.Up.ordinal()].addAnimationFrame( paths[color.ordinal()] + "Up_2.png",6);
            
            bombermanAnimations[color.ordinal()][MovementDirection.Down.ordinal()] = new Animation();
            bombermanAnimations[color.ordinal()][MovementDirection.Down.ordinal()].addAnimationFrame( paths[color.ordinal()] + "Down_0.png",6);
            bombermanAnimations[color.ordinal()][MovementDirection.Down.ordinal()].addAnimationFrame( paths[color.ordinal()] + "Down_1.png",6);
            bombermanAnimations[color.ordinal()][MovementDirection.Down.ordinal()].addAnimationFrame( paths[color.ordinal()] + "Down_0.png",6);
            bombermanAnimations[color.ordinal()][MovementDirection.Down.ordinal()].addAnimationFrame( paths[color.ordinal()] + "Down_2.png",6);
            
            bombermanAnimations[color.ordinal()][MovementDirection.Left.ordinal()] = new Animation();
            bombermanAnimations[color.ordinal()][MovementDirection.Left.ordinal()].addAnimationFrame( paths[color.ordinal()] + "Left_0.png",5);
            bombermanAnimations[color.ordinal()][MovementDirection.Left.ordinal()].addAnimationFrame( paths[color.ordinal()] + "Left_1.png",5);
            bombermanAnimations[color.ordinal()][MovementDirection.Left.ordinal()].addAnimationFrame( paths[color.ordinal()] + "Left_0.png",5);
            bombermanAnimations[color.ordinal()][MovementDirection.Left.ordinal()].addAnimationFrame( paths[color.ordinal()] + "Left_2.png",5);
            
            bombermanAnimations[color.ordinal()][MovementDirection.Right.ordinal()] = new Animation();
            bombermanAnimations[color.ordinal()][MovementDirection.Right.ordinal()].addAnimationFrame( paths[color.ordinal()] + "Right_0.png",5);
            bombermanAnimations[color.ordinal()][MovementDirection.Right.ordinal()].addAnimationFrame( paths[color.ordinal()] + "Right_1.png",5);
            bombermanAnimations[color.ordinal()][MovementDirection.Right.ordinal()].addAnimationFrame( paths[color.ordinal()] + "Right_0.png",5);
            bombermanAnimations[color.ordinal()][MovementDirection.Right.ordinal()].addAnimationFrame( paths[color.ordinal()] + "Right_2.png",5);
        }

    }

    public void resizeImages(int size)
    {
       
        for(Animation[] i : bombermanAnimations)
        {
            for(Animation image : i)
            {
                for(AnimationFrame frame : image.getFrames())
                {
                    frame.getFrameImage().scale(size,size);
                }
                
                
            }
        }
        
  
        
        wallImage.scale(size,size);

        obstacleImage.scale(size,size);

        floorTile.scale(size,size);

        edgeFloorTile.scale(size,size);

        bombImage.scale(size,size);
        
        for(GreenfootImage powerImage : powerUpImages)
        {
            powerImage.scale(size,size);
        }

        for(GreenfootImage explsImage : explosionImages)
        {
            explsImage.scale(size,size);
        }

    }
}
