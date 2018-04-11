import java.util.Stack;
import java.util.ArrayList;
import java.util.Random;
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
    private static final int pesoMaximo = 50;
    /**
     * Constructor for objects of class Player
     */
    public Player(Room habitacionInicial)
    {
        ultimaSala = new Stack<>();
        currentRoom = habitacionInicial;
        bolsa = new ArrayList<>();
        pesoTotal = 0;
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
    public void take(String nombre)
    {
        ArrayList<Item> itemsRoom = currentRoom.getListItems();
        Item item = null;
        for(int i=0;i < itemsRoom.size(); i++)
        {
            if(itemsRoom.get(i).getNombre().equals(nombre))
            {                item = itemsRoom.get(i);
            }
        }

        if(item.setSePuedeCoger() && item != null){
            int pesoTotal = pesoMaximo + item.getWeight();
            boolean disponibilidad = true;
            int contador = 0;            
            if(pesoTotal <= pesoMaximo){
                bolsa.add(item);
                pesoTotal += item.getWeight();
                itemsRoom.remove(item);
                System.out.println("Has metido en la bolsa el siguiente item: " + nombre);
            }
            else{
                System.out.println("La bolsa está a tope, no puedes coger más cosas.");
            }            
        }
        else{
            System.out.println("No puedes coger ese item.");
        }
    }

    /**
     * Método que muestra los objetos que se encuentran dentro de la bolsa.
     */
    public void dentroBolsa() 
    {
        if(bolsa.size() > 0){
            for(Item item : bolsa)
            {
                System.out.println(item.getNombre() + " - " + item.getWeight() + " Kg.");      
            }
        }
        else{
            System.out.println("La bolsa está vacia.");
        }
    }

    /**
     * Método para poder tirar un objeto de la bolsa.
     */
    public void drop(String nombre)
    {
        if(bolsa.size() > 0){
            boolean disponibilidad = true;
            int contador = 0;
            Item item = null;
            while(disponibilidad)
            {
                if(bolsa.get(contador).getNombre().equals(nombre)){
                    item = bolsa.get(contador);
                    currentRoom.addItem(item);
                    disponibilidad = false;
                    pesoTotal -= item.getWeight();
                    bolsa.remove(contador);
                }
                contador++;
            }
            System.out.println("Has soltado este objeto: " + nombre);
        }
        else
        {
            System.out.println("La bolsa está vacia, no puedes soltar ningun objeto.");
        }
    }   
}
