/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room fueraCastillo, entrada, salaChimenea, vestibulo, comedor, pasilloSur, habitacionInicial, pasilloOeste, pasilloEste, biblioteca, pasilloNorte;

        // create the rooms
        habitacionInicial = new Room("Te despiertas aturdido en una habitación desconocida, tienes que lograr escapar de allí.");
        pasilloOeste = new Room("Llegas al Pasillo Oeste, esta destruido, no puedes avanzar por ahí.");
        pasilloNorte = new Room("Llegas al Pasillo Norte, ¿Dónde deberías avanzar ahora?");
        pasilloEste = new Room("Llegas al Pasillo Este, ¿Dónde deberías avanzar ahora?");
        pasilloSur = new Room("Llegas al Pasillo Sur, el más grande de todos, ¿Dónde deberías avanzar ahora?");
        biblioteca = new Room("Entras en una Biblioteca enorme, repleta de libros de todo tipo, solo hay dos puertas, por la que acabas de entrar y otra, ¿Cuál eliges?");
        vestibulo = new Room("Entras en un Vestibulo enorme que conecta con varias salas, ¿A cuál quieres ir?");
        comedor = new Room("Llegas a un enorme comedor, pero no hay ninguna salida, deberás volver por donde has venido.");
        salaChimenea = new Room("Entras a una sala con sillones y una chimenea en el centro de estos, hay dos puertas, por la que has entrado y otra, ¿Cuál decides tomar?");
        entrada = new Room("Llegas a la entrada del castillo, la salida esta justo en frente de ti, puedes salir o seguir investigando el castillo, ¿Qué eliges?");
        fueraCastillo = new Room("Has llegado a la Salida del Castillo, al fin has podido escapar.");

        // initialise room exits
        habitacionInicial.setExits(pasilloNorte, pasilloEste, pasilloSur, pasilloOeste, null);
        pasilloNorte.setExits(null, biblioteca, null, null, null);
        pasilloEste.setExits(biblioteca, null, null, habitacionInicial, null);
        pasilloOeste.setExits(null, habitacionInicial, null, null, null);
        pasilloSur.setExits(habitacionInicial, null, vestibulo, null, comedor);
        biblioteca.setExits(null, null, pasilloEste, pasilloNorte, null);
        vestibulo.setExits(pasilloSur, comedor, null, salaChimenea, null);
        comedor.setExits(null, null, null, vestibulo, null);
        salaChimenea.setExits(entrada, vestibulo, null, null, null);
        entrada.setExits(null, null, salaChimenea, fueraCastillo, null);
        fueraCastillo.setExits(null, entrada, null, null, null);

        currentRoom = habitacionInicial;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Gracias por jugar, adios.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("¡Bienvenido a Legend of Ekem!");
        System.out.println("En Legend of Ekem trataremos de escapar de un castillo donde hemos despertado sin saber como hemos llegado allí.");
        System.out.println("Escribe 'help' si necesitas ayuda.");
        System.out.println();
        printLocation();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("No entiendo que has querido decir.");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Estas solo, perdido y desorientado.");
        System.out.println("en un castillo desconocido.");
        System.out.println();
        System.out.println("Tus palabras comando son:");
        System.out.println("   go quit help");
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println(":¿A dónde?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;
        if(direction.equals("north")) {
            nextRoom = currentRoom.northExit;
        }
        if(direction.equals("east")) {
            nextRoom = currentRoom.eastExit;
        }
        if(direction.equals("south")) {
            nextRoom = currentRoom.southExit;
        }
        if(direction.equals("west")) {
            nextRoom = currentRoom.westExit;
        }
        if(direction.equals("southEast")) {
            nextRoom = currentRoom.southEastExit;
        }

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            printLocation();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("¿Salir donde?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /**
     * Método para que no haya repetición en los otros. Ejercicio 0108
     */
    private void printLocation()
    {
        System.out.println("Estas en: " + currentRoom.getDescription());
        System.out.print("Salidas: ");
        if(currentRoom.northExit != null) {
            System.out.print("north ");
        }
        if(currentRoom.eastExit != null) {
            System.out.print("east ");
        }
        if(currentRoom.southExit != null) {
            System.out.print("south ");
        }
        if(currentRoom.westExit != null) {
            System.out.print("west ");
        }
        if(currentRoom.southEastExit != null) {
            System.out.print("southEast ");
        }
        System.out.println();
    }
}
