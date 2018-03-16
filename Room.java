import java.util.HashMap;
import java.util.ArrayList;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private Item items;
    private HashMap<String, Room> exits;
    private ArrayList<Item> listaItems;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        listaItems = new ArrayList<>();
        exits = new HashMap<>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor The room in the given direction.
     */
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString() {
        String exits1 = "Exits: ";
        if(exits.get("north") != null) {
            exits1 += "north ";
        }
        if(exits.get("south") != null) {
            exits1 += "south ";
        }
        if(exits.get("east") != null) {
            exits1 += "east ";
        }
        if(exits.get("west") != null) {
            exits1 += "west ";
        }
        if(exits.get("southEast") != null) {
            exits1 += "southEast ";
        }
        if(exits.get("northWest") != null) {
            exits1 += "northWest ";
        }
        return exits1;
    }

    /**
     * Return a long description of this room, of the form:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return A description of the room, including exits.
     */
    public String getLongDescription()
    {
        String dato = "";
        dato = "Tu estas en " + description + ".\n" + "Objetos encontrados: " + devolverItem() + ".\n" +  getExitString();
        return dato;
    }
    
    /**
     * Metodo para a�adir m�s de un objeto.
     */
    public void addItem(String itemDescription, int itemWeight) 
    {
        Item newItem = new Item(itemDescription, itemWeight);
        listaItems.add(newItem);
    }
    
    /**
     * Devuelve la lista de items.
     */
    public String devolverItem()
    {
        String itemDato = "";
        if(listaItems.size() == 0) {
            itemDato = "No hay objetos";
        }
        else {
            for(Item object : listaItems) {
                itemDato += object.getItemDescription() + " ";
            }
        }
        return itemDato;
    }
}