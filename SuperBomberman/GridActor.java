import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Klasse welche festlegt/ausrechnet auf welchen Feld im Raster der Actor sich befindet.
 * Sie überschreibt auch wie sich Actor bewegen können.
 * 
 * 
 * @author Christian Sacher 
 * @version 26.11.2017
 */
public class GridActor extends Actor
{
    //Referenz auf unsere eigene Welt für schnellern Zugriff
    protected BomberWorld bomberWorld = null;

    //Variable die steuert ob eine Actor nur auf dem Grid bewegt werden kann.
    protected boolean forceGridLocation = true;

    //Position auf dem Grid in XRichtung
    protected int gridXPos = -1;

    //Position auf dem Grid in YRichtung
    protected int gridYPos = -1;

    /**
     * @return gridXPos
     */
    public int getGridXPos() 
    {
        if(bomberWorld != null) gridXPos = bomberWorld.convertPosToGrid(getX());
        else gridXPos = -1;
        return gridXPos;
    }
     /**
     * @return gridXPos als Pixel in der Welt
     */
    public int getGridXPosAsPixel() 
    {
        if(bomberWorld != null) return bomberWorld.convertGridToPos(getGridXPos());
        return -1;
    }

    /**
     * @param gridXPos auf welches Feld in X Richtung der Actor platziert werden soll. 
     * Bewegt den Actor auf das Grid
     */
    public void setGridXPos(int gridXPos) 
    {
        this.gridXPos = gridXPos;
        setLocationGrid( this.gridXPos, this.gridYPos);
    }

    /**
     * @return berechne gridYPos und gibt sie zurück
     */
    public int getGridYPos() 
    {
        if(bomberWorld != null) gridYPos = bomberWorld.convertPosToGrid(getY());
        else gridYPos = -1;
        return gridYPos;
    }
    /**
     * @return gridYPos als Pixel in der Welt
     */
    public int getGridYPosAsPixel() 
    {
        if(bomberWorld != null) return bomberWorld.convertGridToPos(getGridYPos());

        return -1;
    }

    /**
     * @param gridYPos the gridYPos to set
     */
    public void setGridYPos(int gridYPos)
    {
        this.gridYPos = gridYPos;
        setLocationGrid( this.gridXPos, this.gridYPos);
    }
    
     /**
     * @param world Welt in welcher der Actor platziert wurde
     * 
     */
    @Override
    public void addedToWorld(World world)
    {
        bomberWorld = (BomberWorld)world;
        setLocation(getX(),getY());
        OnWorldLoaded();
    }
    
     /**
     * @param locX Raster X-Position auf welcher der Actor platziert werden soll
     * @param locY Raster Y-Position auf welcher der Actor platziert werden soll
     * 
     */
    public void setLocationGrid(int locX ,int locY)
    {
        gridXPos = locX;
        gridYPos = locY;
        setLocation(bomberWorld.convertGridToPos(gridXPos),bomberWorld.convertGridToPos(gridYPos));
    }
    
    
      /**
     * @param locX Pixel in X Richtung
     * @param locY Pixel in Y Richtung
     * 
     * Wenn forceGridLocation == true wird der Actor afu dem Raster platziert, wenn nicht dann genau an die X Y Position
     */
    @Override
    public void setLocation(int locX ,int locY)
    {
        try
        {
            //Nur wenn die Welt existiert kann man ein Raster haben. 
            if(bomberWorld != null && forceGridLocation)
            {

                gridXPos = bomberWorld.convertPosToGrid(locX);
                gridYPos = bomberWorld.convertPosToGrid(locY);
                super.setLocation(bomberWorld.roundToGrid(locX),bomberWorld.roundToGrid(locY));

            } 
            else
            {
                //Neue Position ist trotzdem in einen Grid nur nicht direkt darauf
                if(bomberWorld != null)
                {
                    gridXPos = bomberWorld.convertPosToGrid(locX);
                    gridYPos = bomberWorld.convertPosToGrid(locY);
                }
                else
                {
                    //Welt existiert nicht
                    gridXPos = -1;
                    gridYPos = -1;
                }

                super.setLocation(locX,locY);
            } }
        catch(Exception e)
        {
            Logger.logError("Exception in setLocation");
            e.printStackTrace();
            
        }
    }
    
   /**
     * Methode die aufgerufen wird sobald der Actor in der Welt plaziert und Referenzen und Positionen gesetzt wurden.
     */
    protected void OnWorldLoaded()
    {

    }
}
