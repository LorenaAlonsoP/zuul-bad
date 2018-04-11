import java.util.Stack;

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
    /**
     * Constructor for objects of class Player
     */
    public Player(Room habitacionInicial)
    {
        ultimaSala = new Stack<>();
        currentRoom = habitacionInicial;
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
    
    public void eat() 
    {
        System.out.println("Acabas de comer y ya no tienes hambre.");
    }
}
