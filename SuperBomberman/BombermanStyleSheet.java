import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Klasse zum speichern aller Bilder. 
 * Nicht alle Bilder haben die selbe größe usw.
 * Funktionen um sehr einfach An gesuchte Bilder zu kommen
 * Jede Welt kann Z.B ein eigenes SpriteSheet haben um komplett anders auszusehen
 * 
 * @author Christian Sacher 
 * @version 24.11.2017
 */
public class BombermanStyleSheet  
{

    /*
     * Array zum speichern der Animationen eines Bombermans
     * Erster Index für die Farbe der Figur und zweiter Index für die Richtung der Bewegungen
     */
    private Animation bombermanAnimations[][] = new Animation[PlayerColor.values().length][MovementDirection.values().length];

    private Animation bombermanDeathAnimations[] = new Animation[PlayerColor.values().length];

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
     * Es gibt 9 Explosions Bilder. 1 für die Mitte , 4 für Mittelstücke in 4 Richtungen, 4 für Endstücke in 4 Richtungen
     * Gespeichert als Mitte, Mittleres Stück, EndStück,Mittleres Stück, EndStück....
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

     public Animation getBombermanDeathAnimation(PlayerColor color)
    {
        return new Animation(bombermanDeathAnimations[color.ordinal()]);
    }
    
    public Animation getBombermanAnimation(PlayerColor color,MovementDirection direction)
    {
        return new Animation(bombermanAnimations[color.ordinal()][direction.ordinal()]);
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
        //Alle Bilder sind in einer bestimmten Reihenfolge gespeichert um sie einfach zu greifen.

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
            
            bombermanDeathAnimations[color.ordinal()] = new Animation();
            bombermanDeathAnimations[color.ordinal()].addAnimationFrame( paths[color.ordinal()] + "Death0.png",5);
            bombermanDeathAnimations[color.ordinal()].addAnimationFrame( paths[color.ordinal()] + "Death1.png",5);
            bombermanDeathAnimations[color.ordinal()].addAnimationFrame( paths[color.ordinal()] + "Death2.png",5);
            bombermanDeathAnimations[color.ordinal()].addAnimationFrame( paths[color.ordinal()] + "Death3.png",5);
            
        }

    }

    /**
     * Method resizeImages
     *
     * @param size Pixel Größe für die Felder
     */
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
        for(Animation da : bombermanDeathAnimations)
        {
            for(AnimationFrame frame : da.getFrames())
            {
                frame.getFrameImage().scale(size,size);
            }

        }

    }
}
