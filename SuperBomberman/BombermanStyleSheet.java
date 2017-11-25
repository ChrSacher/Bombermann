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
    /*
     * First index is the player Number and the second index is the the Type of Image
     * The type is by the same order as the MovementDirection Enum
     */
    private GreenfootImage bombermanImageFiles[][] = new GreenfootImage[4][4];

    private GreenfootImage wallImage = new GreenfootImage("/SpriteSheetImages/FloorTiles/Wall.png");

    private GreenfootImage obstacleImage = new GreenfootImage("/SpriteSheetImages/FloorTiles/Obstacle.png");

    private GreenfootImage floorTile = new GreenfootImage("/SpriteSheetImages/FloorTiles/Floor.png");

    private GreenfootImage edgeFloorTile = new GreenfootImage("/SpriteSheetImages/FloorTiles/Edge_Floor.png");

    private GreenfootImage bombImage = new GreenfootImage("/SpriteSheetImages/Bomb/Bomb.png");

    /*
     * Die PowerUp Bilder werden nach folgender Reihenfolge gespeichert. Nach Reihenfolge in der Enum Deklarierung . Erst Positiv dann negativ.
     */
    private GreenfootImage powerUpImages[] = new GreenfootImage[8];

    /*
     * The explosion images are 9 images. 1 center piece, 4 middle pieces and 4 end pieces. The order of  the images is center piece then middle piece and end piece for each of the MovementDirections
     */
    private GreenfootImage explosionImages[] = new GreenfootImage[9];

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

    public GreenfootImage getBombermanImage(PlayerColor color,MovementDirection direction)
    {
        return bombermanImageFiles[color.ordinal()][direction.ordinal()];
    }

    public GreenfootImage getExplosionCenterImage()
    {
        return explosionImages[0];
    }

    public GreenfootImage getExplosionEndImage(MovementDirection direction)
    {
        return explosionImages[direction.ordinal() * 2 +1];
    }

    public GreenfootImage getExplosionCenterImage(MovementDirection direction)
    {
        return explosionImages[direction.ordinal() * 2];
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
        bombermanImageFiles[PlayerColor.White.ordinal()][MovementDirection.Up.ordinal()] = new GreenfootImage("/SpriteSheetImages/Bomberman/BWhite_Up.png");
        bombermanImageFiles[PlayerColor.White.ordinal()][MovementDirection.Down.ordinal()] = new GreenfootImage("/SpriteSheetImages/Bomberman/BWhite_Down.png");
        bombermanImageFiles[PlayerColor.White.ordinal()][MovementDirection.Left.ordinal()] = new GreenfootImage("/SpriteSheetImages/Bomberman/BWhite_Left.png");
        bombermanImageFiles[PlayerColor.White.ordinal()][MovementDirection.Right.ordinal()] = new GreenfootImage("/SpriteSheetImages/Bomberman/BWhite_Right.png");

        bombermanImageFiles[PlayerColor.Red.ordinal()][MovementDirection.Up.ordinal()] = new GreenfootImage("/SpriteSheetImages/Bomberman/BRed_Up.png");
        bombermanImageFiles[PlayerColor.Red.ordinal()][MovementDirection.Down.ordinal()] = new GreenfootImage("/SpriteSheetImages/Bomberman/BRed_Down.png");
        bombermanImageFiles[PlayerColor.Red.ordinal()][MovementDirection.Left.ordinal()] = new GreenfootImage("/SpriteSheetImages/Bomberman/BRed_Left.png");
        bombermanImageFiles[PlayerColor.Red.ordinal()][MovementDirection.Right.ordinal()] = new GreenfootImage("/SpriteSheetImages/Bomberman/BRed_Right.png");

        bombermanImageFiles[PlayerColor.Black.ordinal()][MovementDirection.Up.ordinal()] = new GreenfootImage("/SpriteSheetImages/Bomberman/BBlack_Up.png");
        bombermanImageFiles[PlayerColor.Black.ordinal()][MovementDirection.Down.ordinal()] = new GreenfootImage("/SpriteSheetImages/Bomberman/BBlack_Down.png");
        bombermanImageFiles[PlayerColor.Black.ordinal()][MovementDirection.Left.ordinal()] = new GreenfootImage("/SpriteSheetImages/Bomberman/BBlack_Left.png");
        bombermanImageFiles[PlayerColor.Black.ordinal()][MovementDirection.Right.ordinal()] = new GreenfootImage("/SpriteSheetImages/Bomberman/BBlack_Right.png");

        bombermanImageFiles[PlayerColor.Blue.ordinal()][MovementDirection.Up.ordinal()] = new GreenfootImage("/SpriteSheetImages/Bomberman/BBlue_Up.png");
        bombermanImageFiles[PlayerColor.Blue.ordinal()][MovementDirection.Down.ordinal()] = new GreenfootImage("/SpriteSheetImages/Bomberman/BBlue_Down.png");
        bombermanImageFiles[PlayerColor.Blue.ordinal()][MovementDirection.Left.ordinal()] = new GreenfootImage("/SpriteSheetImages/Bomberman/BBlue_Left.png");
        bombermanImageFiles[PlayerColor.Blue.ordinal()][MovementDirection.Right.ordinal()] = new GreenfootImage("/SpriteSheetImages/Bomberman/BBlue_Right.png");

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
        explosionImages[MovementDirection.Right.ordinal() * 2 + 1].rotate(-90);
        explosionImages[MovementDirection.Right.ordinal() * 2 + 2] = new GreenfootImage(endPath);
        explosionImages[MovementDirection.Right.ordinal() * 2 + 2].rotate(-90);

        powerUpImages[PowerUpType.Speed.ordinal() * 2] = new GreenfootImage("/SpriteSheetImages/Powerups/SpeedUp.png");
        powerUpImages[PowerUpType.Speed.ordinal() * 2 + 1] = new GreenfootImage("/SpriteSheetImages/Powerups/SpeedDown.png");   

        powerUpImages[PowerUpType.Ammount.ordinal() * 2] = new GreenfootImage("/SpriteSheetImages/Powerups/BombsUp.png");
        powerUpImages[PowerUpType.Ammount.ordinal() * 2 + 1] = new GreenfootImage("/SpriteSheetImages/Powerups/BombsDown.png");  

        powerUpImages[PowerUpType.Strength.ordinal() * 2] = new GreenfootImage("/SpriteSheetImages/Powerups/LengthUp.png");
        powerUpImages[PowerUpType.Strength.ordinal() * 2 + 1] = new GreenfootImage("/SpriteSheetImages/Powerups/LengthDown.png");  

        powerUpImages[PowerUpType.Death.ordinal() * 2] = new GreenfootImage("/SpriteSheetImages/Powerups/Death.png");
        powerUpImages[PowerUpType.Death.ordinal() * 2 + 1] = new GreenfootImage("/SpriteSheetImages/Powerups/Death2.png");  

    }

    public void resizeImages(int size)
    {
        for(GreenfootImage[] i : bombermanImageFiles)
        {
            for(GreenfootImage image : i)
            {
                image.scale(size,size);
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
