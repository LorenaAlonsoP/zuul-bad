import java.util.Stack;
import java.util.ArrayList;
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    // instance variables - replace the example below with your own
    private Room currentRoom;
    private Stack<Room> ultimaSala;
    private ArrayList<Item> bolsa;
    /**
     * Constructor for objects of class Player
     */
    public Player(Room habitacionInicial)
    {
        ultimaSala = new Stack<>();
        currentRoom = habitacionInicial;
        bolsa = new ArrayList<>();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    public void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println(":¿A dónde?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            ultimaSala.push(currentRoom);
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            System.out.println("\n");
        }
    }
    
    /**
     * Método para imprimir información.
     */
    public void look() 
    {
        System.out.println(currentRoom.getLongDescription());
    }
    
    /**
     * Método para volver a la sala anterior
     */
    public void back() 
    {
        if(!ultimaSala.empty()) {
            currentRoom = ultimaSala.pop();
        }
    }
    
    /**
     * Método que permite al personaje comer.
     */
    public void eat() 
    {
        System.out.println("Acabas de comer y ya no tienes hambre.");
    }
    
    /**
     * Método que permite al personaje coger objetos.
     */
    public void take(Command command)
    {
        if(!command.hasSecondWord()) {
            System.out.println("¿Qué item quieres coger?");
            return;
        }
        String item = (command.getSecondWord());
        ArrayList<Item> coger = currentRoom.firstItemList();
        for (Item itemA : coger)
        {
            if(itemA.getItemDescription().equals (item))
            {
                System.out.println("Item añadido a la bolsa.");
                bolsa.add(itemA);
                currentRoom.removeItem(itemA);
                break;
            }
        }
    }
}
