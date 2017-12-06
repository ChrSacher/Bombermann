import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
/**
 * Standard Welt 
 * 
 * @author Christian Sacher
 * @version (a version number or a date)
 */
public class BomberWorld extends World
{
    /*
     * Größe eines Feldes in Pixel
     */
    private int gridSize = 25;
    
     /*
     * Anzahl der Felder in X Richtung
     */
    private int gridXNum = 10;
    
     /*
     * Anzahl der Felder in Y Richtung
     */
    private int gridYNum = 10;
    
    /*
     * Distanz zu den Ecken ab welcher Hindernisse generiert werden könenn
     */
    private int safeZoneSize = 4;
    
    /*
     * Stylesheet für diese Welt
     */
    private BombermanStyleSheet styleSheet = new BombermanStyleSheet();
    
     /*
     * Hintergrund Musik welche gespielt werden kann
     */
    private String soundFiles[] = {"Track_04.mp3","Track_06.mp3"};
    
     /*
     * Hintergrundmusik die gespielt wird
     */
    GreenfootSound currentSound = null;
    
    /**
     * Constructor for objects of class BomberWorld.
     * 
     */
    public BomberWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 1000, 1); 
        styleSheet.loadImages();
        styleSheet.resizeImages(gridSize);
        setPaintOrder();
        

    }
    @Override
    public void started()
    {
        super.started();
        //zufälligen Song auswählen
        currentSound = new GreenfootSound(soundFiles[Greenfoot.getRandomNumber(soundFiles.length)]);
        if(currentSound != null) currentSound.playLoop();
    }
    public void stopped()
    {
        super.stopped();
        if(currentSound != null) currentSound.stop();
    }
    /**
     * BomberWorld Constructor
     *
     * @param gridSize A parameter
     * @param gridNumX A parameter
     * @param gridNumY A parameter
     * @param numObstacles A parameter
     */
    public BomberWorld(int gridSize,int gridNumX,int gridNumY,int numObstacles,int numPlayers)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(gridNumX * gridSize , gridNumY * gridSize, 1); 
        styleSheet.loadImages();
        styleSheet.resizeImages(gridSize);
        generateWorld(gridSize,gridNumX,gridNumY,numObstacles,numPlayers);
        setPaintOrder();
    }
    
    public void setPaintOrder()
    {
        setPaintOrder(Bomberman.class,Explosion.class,Bomb.class,PowerUp.class,Obstacle.class,FloorTile.class);
    }

    /**
     * Method setGridSize
     *
     * @param newGridSize A parameter
     */
    public void setGridSize(int newGridSize)
    {
        gridSize = newGridSize;
        getStyleSheet().resizeImages(gridSize);
        drawGrid();
        
        
        //Diese Methode gehört zu Greenfoot und funktionert nicht richtig
        repaint();
    }

    public int getGridSize()
    {
        return gridSize;
    }

   
    public BombermanStyleSheet getStyleSheet()
    {
        return styleSheet;
    }

    /**
     * Method convertPosToGrid
     *
     * @param pos Pixel Position
     * @return Feld auf dem Raster
     */
    public int convertPosToGrid(double pos)
    {
        return (int)Math.floor(pos / gridSize);
    }

    /**
     * Method convertGridToPos
     *
     * @param grid Feld auf dem Raster
     * @return Mittelpunkt-Position des Feldes auf dem Raster in Pixel
     */
    public int convertGridToPos(int grid)
    {
        return (grid * gridSize) + (int)Math.floor(gridSize/ 2);
    }

    /**
     * Method roundToGrid
     *
     * @param pos Position
     * @return Position der Mitte des Feldes 
     */
    public int roundToGrid(double pos)
    {
        return convertGridToPos(convertPosToGrid(pos));
    }

    /**
     * Method drawGrid
     * Zeichnet das geladene Raster in die Welt
     */
    public void drawGrid()
    {
        getBackground().clear();
        getBackground().setColor(Color.BLACK);
        for(int i = 0; i <= gridXNum;i++)
        {
            getBackground().drawLine(0,i * gridSize,gridYNum * gridSize,i * gridSize);
        }
        for(int i = 0; i <= gridYNum;i++)
        {
            getBackground().drawLine(i * gridSize,0,i * gridSize,gridXNum * gridSize);
        }

    }

    /**
     * Method generateWorld
     *
     * @param gridSize Wie viele Pixel ein Feld groß ist
     * @param gridNumX Wie viele Felder in X Richtung
     * @param gridNumY Wie viele Felder in Y Richtung
     * @param numObstacles Wie viele zerstörbare Hindernisse soll es geben
     * @param numPlayers Wie viele Spieler sollen generiert werden. Max 4
     */
    public void generateWorld(int gridSize,int newGridNumX,int newGridNumY,int numObstacles,int numPlayers)
    {
        removeObjects(getObjects(null));
        setGridSize(gridSize);
        drawGrid();
        gridXNum = newGridNumX;
        gridYNum = newGridNumY;
        //Untergrund erstellen
        for(int i = 0; i < gridYNum;i++)
        {
            for(int j = 0; j < gridYNum;j++)
            {
                FloorTile newFloor = new FloorTile(false);
                addObject(newFloor,i * gridSize, j * gridSize);  

            }
        }
        
         //Innere Wand erstellen und unzerstörbar machen
        for(int i = 2; i < (gridXNum -2);i +=2)
        {
            for(int j = 2; j < (gridYNum - 2);j +=2)
            {

                Obstacle innerWall = new Obstacle();    
                addObject(innerWall,i * gridSize,j * gridSize);
                innerWall.setisDestructable(false);
            }
        }

        //Äußere Wand erstellen und unzerstörbar machen
        for(int i = 0; i < gridXNum;i++)
        {
            Obstacle upperOuterWall = new Obstacle();    
            addObject(upperOuterWall,i * gridSize,0);
            upperOuterWall.setisDestructable(false);

            Obstacle lowerOuterWall = new Obstacle();
            addObject(lowerOuterWall,i * gridSize,(gridYNum -1 ) * gridSize);
            lowerOuterWall.setisDestructable(false);
        }
        //rechte und linke äußere Wand erstellen und unzerstörbar machen
        for(int i = 1; i < gridYNum -1;i++)
        {
            Obstacle leftWall = new Obstacle();    
            addObject(leftWall,0,i * gridSize);
            leftWall.setisDestructable(false);

            Obstacle rightWall = new Obstacle();
            addObject(rightWall,(gridXNum -1 ) * gridSize, i *gridSize);
            rightWall.setisDestructable(false);
        }

        
        
        //occupany grid ist eine Array was anzeigt ob ein Feld belegt ist durch einen Interactable Actor
        boolean occupancyGrid[][] = new boolean[gridXNum][gridYNum];
        List<InteractableActor> actorList = getObjects(InteractableActor.class);
        for(InteractableActor actor:actorList)
        {
                occupancyGrid[convertPosToGrid(actor.getX())][convertPosToGrid(actor.getY())] = true;     
        }
        //Wie viele Obstacles wir schon erstellt haben
        int countObstacles = 0;
        //gibt es noch freie Felder
        boolean wasAbleToPlace = true;
        
        //Wir platzieren solange Hindernisse bis wir genügend erstrellt haben oder es keinen Platz mehr gibt
        while(wasAbleToPlace == true && countObstacles < numObstacles)
        {
            wasAbleToPlace = false;
            for(int i = 0; i < gridXNum;i++)
            {
                for(int j = 0; j < gridYNum ;j++)
                {
                    int randomNumber = Greenfoot.getRandomNumber(100);
                    //ignorieren wenn es die linke obere Ecke ist
                    if( i < safeZoneSize && j < safeZoneSize) continue;
                    
                     //ignorieren wenn es die rechte obere Ecke ist
                    if( i < safeZoneSize && j > gridXNum - safeZoneSize) continue;
                    
                    //ignorieren wenn es die rechte untere Ecke ist
                    if( i > gridXNum - safeZoneSize && j > gridXNum - safeZoneSize) continue;
                    //ignorieren wenn es die linke untere Ecke ist
                    if( i > gridXNum - safeZoneSize && j < safeZoneSize) continue;
                    
                    //können wir es an dieser Position platzieren? und haben wir nicht genug?
                    if(occupancyGrid[i][j] == false && countObstacles < numObstacles)
                    {

                        wasAbleToPlace = true;
                        //zufallszahl damit nicht alle Hindernisse an einer Seite enstehen
                        if(randomNumber < 20)
                        {

                            Obstacle destroyableObstacle = new Obstacle(true);
                            addObject(destroyableObstacle,convertGridToPos(i),convertGridToPos(j));
                            //variablen updated
                            occupancyGrid[convertPosToGrid(destroyableObstacle.getX())][convertPosToGrid(destroyableObstacle.getY())] = true;   
                            countObstacles++;
                        }
                    }                     
                }
            }
        }
        //wir besitzen nur 4 Spielerfarben
        if(numPlayers <= 0) numPlayers = 0;
        if(numPlayers >= 4) numPlayers = 4;
        
        if(numPlayers >= 1)
        {
            PlayerBomberman newBomber = new PlayerBomberman();       
            addPlayer(newBomber,1,1);
            newBomber.setPlayerColor(PlayerColor.White);
            
            
        }
        if(numPlayers >= 2)
        {
            PlayerBomberman newBomber = new PlayerBomberman();
            addPlayer(newBomber,1,gridYNum - 2);
            newBomber.setPlayerColor(PlayerColor.Red);
            newBomber.setKeySet(PlayerBomberman.ARROWKEYS);
        }
        if(numPlayers >= 3)
        {
            PlayerBomberman newBomber = new PlayerBomberman();
            addPlayer(newBomber,gridXNum - 2 ,1);
            newBomber.setPlayerColor(PlayerColor.Black);
            newBomber.setKeySet(PlayerBomberman.IJKLKEYS);
        }
        if(numPlayers >= 4)
        {
            PlayerBomberman newBomber = new PlayerBomberman();
            addPlayer(newBomber,gridXNum - 2,gridYNum - 2);
            newBomber.setPlayerColor(PlayerColor.Blue);
            newBomber.setKeySet(PlayerBomberman.NUMBERKEYS);
        }
        
    }

    /**
     * Method addPlayer Bomberman der Welt hinzufügen und auf ein Feld stellen
     *
     * @param newBomberman A Bomberman Object to add
     * @param gridX On Which Grid in X Direction to place the Bomberman
     * @param gridY On Which Grid in Y Direction to place the Bomberman
     */
    public void addPlayer(PlayerBomberman newBomberman,int gridX,int gridY)
    {
        addObject(newBomberman,gridSize * gridX + gridSize / 2,gridSize * gridY  + gridSize / 2); 
    }

    /**
     * Method playerTestSceneario
     *
     */
    public  void playerTestSceneario()
    {
        generateWorld(35,15,15,90,4);
        
    }
}
