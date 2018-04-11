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
    private int pesoTotal;
    /**
     * Constructor for objects of class Player
     */
    public Player(Room habitacionInicial)
    {
        ultimaSala = new Stack<>();
        currentRoom = habitacionInicial;
        bolsa = new ArrayList<>();
        pesoTotal = 50;
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    public void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println(":�A d�nde?");
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
     * M�todo para imprimir informaci�n.
     */
    public void look() 
    {
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * M�todo para volver a la sala anterior
     */
    public void back() 
    {
        if(!ultimaSala.empty()) {
            currentRoom = ultimaSala.pop();
        }
    }

    /**
     * M�todo que permite al personaje comer.
     */
    public void eat() 
    {
        System.out.println("Acabas de comer y ya no tienes hambre.");
    }

    /**
     * M�todo que permite al personaje coger objetos.
     */
    public void take(Command command)
    {
        if(!command.hasSecondWord()) {
            System.out.println("�Qu� item quieres coger?");
            return;
        }
        String item = (command.getSecondWord());
        ArrayList<Item> coger = currentRoom.firstItemList();
        for (Item itemA : coger)
        {
            if(itemA.getItemDescription().equals (item))
            {
                if(itemA.getWeight() <= pesoTotal) {
                    System.out.println("Item a�adido a la bolsa.");
                    bolsa.add(itemA);
                    currentRoom.removeItem(itemA);
                    break;
                }
                else {
                    System.out.println("El objeto pesa demasiado, no se puede coger.");
                }
            }
        }
    }
    
    /**
     * M�todo que muestra los objetos que se encuentran dentro de la bolsa.
     */
    public void dentroBolsa() 
    {
        if(bolsa.size() == 0) {
            System.out.println("La bolsa est� vac�a.");
        }
        else {
            for (Item itemA : bolsa) {
                System.out.println(itemA.getItemDescription() + " - " + itemA.getWeight() + " Kg.");
            }
        }
    }
    
    /**
     * M�todo para poder tirar un objeto de la bolsa.
     */
    public void drop(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("�que item necesitas?");
            return;
        }
        String item = (command.getSecondWord());

        for (Item itemA : bolsa)
        {              
            if (itemA.getItemDescription().equals (item))
            {
                System.out.println ("Objeto depositado");
                bolsa.remove(itemA);
                currentRoom.addItem(itemA);
                break;
            } 
        }
    }
}
