
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
    private String itemDescription;
    private int itemWeight;
    private boolean itemsDisponibles;
    /**
     * Constructor for objects of class Item
     */
    public Item(String nombre,String itemDescription, int itemWeight, boolean itemsDisponibles)
    {
        this.nombre = nombre;
        itemDescription = itemDescription;
        this.itemWeight = itemWeight;
        this.itemsDisponibles = itemsDisponibles;
    }
    
    /**
     * El nombre del item
     */
    public String getNombre()
    {
        return nombre;
    }
    
    /**
     * @return The description of the item.
     */
    public String getItemDescription()
    {
        return itemDescription;
    }
    
    /**
     * @return The description of the item.
     */
    public int getWeight()
    {
        return itemWeight;
    }
    
    /**
     * Metodo para imprimir la información de los items.
     */
    public String getInfoItem() 
    {
        return "Hay " + getItemDescription() + " su peso es de " + getWeight() + " Kg.";
    }
    
    /**
     * Metodo para poder recoger items.
     */
    public boolean getTakeItem() 
    {
        return itemsDisponibles;
    }
}
