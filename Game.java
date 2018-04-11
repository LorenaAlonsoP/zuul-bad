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
    private Player player;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        player = new Player(createRooms());
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    public Room createRooms()
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
        habitacionInicial.setExit("north", pasilloNorte);
        habitacionInicial.setExit("east", pasilloEste);
        habitacionInicial.setExit("south", pasilloSur);
        habitacionInicial.setExit("West", pasilloOeste);

        pasilloNorte.setExit("east", biblioteca);

        pasilloEste.setExit("north", biblioteca);
        pasilloEste.setExit("west", habitacionInicial);

        pasilloOeste.setExit("east", habitacionInicial);

        pasilloSur.setExit("north", habitacionInicial);
        pasilloSur.setExit("south", vestibulo);
        pasilloSur.setExit("southEast", comedor);

        biblioteca.setExit("south", pasilloEste);
        biblioteca.setExit("west", pasilloNorte);

        vestibulo.setExit("north", pasilloSur);
        vestibulo.setExit("east", comedor);
        vestibulo.setExit("west", salaChimenea);

        comedor.setExit("west", vestibulo);
        comedor.setExit("northWest", pasilloSur);

        salaChimenea.setExit("north", entrada);
        salaChimenea.setExit("east", vestibulo);

        entrada.setExit("south", salaChimenea);
        entrada.setExit("west", fueraCastillo);

        fueraCastillo.setExit("east", entrada);

        habitacionInicial.addItem("Espejo", 8);
        
        pasilloSur.addItem("Escultura de marmol", 200);
        
        biblioteca.addItem("Estanteria de libros", 20);
        
        vestibulo.addItem("Fuente", 250);
        
        comedor.addItem("Silla", 10);
        
        salaChimenea.addItem("Sillón", 50);
        
        entrada.addItem("Florero", 30);
        
        return habitacionInicial;  // start game outside
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
    public void printWelcome()
    {
        System.out.println();
        System.out.println("¡Bienvenido a Legend of Ekem!");
        System.out.println("En Legend of Ekem trataremos de escapar de un castillo donde hemos despertado sin saber como hemos llegado allí.");
        System.out.println("Escribe 'help' si necesitas ayuda.");
        System.out.println();
        player.look();
        System.out.println("\n");
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    public boolean processCommand(Command command) 
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
        else if (commandWord.equals("look")) {
            player.look();
        }
        else if (commandWord.equals("go")) {
            player.goRoom(command);
        }
        else if (commandWord.equals("back")) {
            player.back();
        }
        else if (commandWord.equals("eat")) {
            player.eat();
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
    public void printHelp() 
    {
        System.out.println("Estas solo, perdido y desorientado.");
        System.out.println("en un castillo desconocido.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    public boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("¿Salir donde?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

}
