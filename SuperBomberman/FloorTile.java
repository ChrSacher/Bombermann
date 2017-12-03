import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Klasse für das Anzeigen eines Bodens. Da das Spielfeld eine variable Größe kann der Boden nicht aus einem einzigen Bild bestehen. Diese Klasse repräsentiert den Boden für genau 1 Raster-Feld.
 * 
 * @author Dieu Huyen Dinh
 * @version 30.11.17
 */
public class FloorTile extends GridActor
{
    /**
     * Variable die wahr ist wenn das Feld am Rand ist, falsch wenn es nicht ist.
     */
    private boolean isEdge = false;
    /**
     * FloorTile Constructor 
     * Konstruktor für das schnelle setzen der Edge-variable.
     *
     * @param isEdge Ist das Feld am Rand des Spielfeldes
     */
    FloorTile(boolean newIsEdge)
    {
        isEdge = newIsEdge;
    }

    /**
     * FloorTile Constructor
     *
     */
    FloorTile()
    {

    }

    /**
     * Method loadImage Setzt das Bild je nachdem wie isEdge gesetzt ist
     *
     */
    @Override
    protected void OnLoadWorldImage()
    {
        if(isEdge == true)
        {
            setImage(bomberWorld.getStyleSheet().getEdgeFloorTileImage());
        }
        else
        {
            setImage(bomberWorld.getStyleSheet().getFloorTileImage());
        }
    }

    /**
     * Method setIsEdge setzt Variable isEdge und aktualiesiert das Bild.
     *
     * @param newIsEdge neues isEdge
     */
    public void setIsEdge(boolean newIsEdge)
    {
        isEdge = newIsEdge;
        OnLoadWorldImage();
    }

    /**
     * Method getIsEdge
     *
     * @return Returns isEdge
     */
    public boolean getIsEdge()
    {
        return isEdge;
    }

}
