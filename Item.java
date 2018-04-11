
/**
 * Write a description of class Item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String itemDescription;
    private int itemWeight;
    private int id;
    /**
     * Constructor for objects of class Item
     */
    public Item(String description, int weight)
    {
        itemDescription = description;
        itemWeight = weight;
    }
    
    /**
     * @return The description of the room.
     */
    public String getItemDescription()
    {
        return itemDescription;
    }
    
    public void setItemDescription(String newDescription)
    {
        itemDescription = newDescription;
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
    
}
