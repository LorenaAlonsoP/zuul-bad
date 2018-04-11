
/**
 * Write a description of class Item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String nombre;
    private int itemWeight;
    private boolean itemsDisponibles;
    /**
     * Constructor for objects of class Item
     */
    public Item(String nombre, int itemWeight, boolean itemsDisponibles)
    {
        this.nombre = nombre;
        this.itemWeight = itemWeight;
        this.itemsDisponibles = itemsDisponibles;
    }
    
    /**
     * @return The description of the room.
     */
    public String getNombre()
    {
        return nombre;
    }
    
    public void setNombre(String nombre)
    {
        nombre = nombre;
    }
    
    /**
     * @return The description of the room.
     */
    public int getWeight()
    {
        return itemWeight;
    }
    
    public void setWeight(int newWeight)
    {
        itemWeight = newWeight;
    }
    
    public String getInfoItem()
    {
        return "Hay " + getNombre() + " y su peso es de: " + getWeight() + " Kg";
    }
    
    /**
     * Si el item es true se puede coger el objeto.
     */
    public boolean setSePuedeCoger() 
    {
        return itemsDisponibles;
    }
}
