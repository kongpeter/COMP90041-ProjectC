/**COMP90041 Project B
 * Programmer Name: Lingxuan Kong
 * Student ID: 957828*/
import java.util.*;
import java.io.*;

public class Nimsys
{
    public static Scanner keyboard = new Scanner(System.in);
    //define the array of players
    private static NimPlayer[] players = new NimPlayer[100];
    //set a player number, which can store the number of players add to this program, from 1 to 100
    private static int playerNumber = 0;
    //set a sting list will store the valid input from user
    private static List<String> commandInputArray = Arrays.asList(
            "addplayer", "addaiplayer", "editplayer", "removeplayer", "displayplayer",
            "resetstats", "rankings", "startgame", "exit");



    /**Java Comparator:
     * Compare the two username, follow from the alphabetically*/
    private static Comparator<NimPlayer> dispUser = new Comparator<NimPlayer>()
    {
        @Override
        public int compare(NimPlayer o1, NimPlayer o2)
        {
            return o1.getUserName().compareTo(o2.getUserName());
        }
    };
    /**Java Comparator
     * Compare the win ratio of Nimplayer in the array
     * By DESC, the ratio from high to low
     * If with the same win ratio, use the username alphabetically*/
    private static Comparator<NimPlayer> rankDesc = new Comparator<NimPlayer>()
    {
        @Override
        public int compare(NimPlayer o1, NimPlayer o2)
        {
            double w1 = o1.getWinRatio();
            double w2 = o2.getWinRatio();

            if (w1 == w2)
            {
                return o1.getUserName().compareTo(o2.getUserName());
            }
            else if (w1 - w2 < 0)
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
    };
    /**Java Comparator
     * Compare the win ratio of Nimplayer in the array
     * By ASC, the ratio low high to high
     * If with the same win ratio, use the username alphabetically*/
    private static Comparator<NimPlayer> rankAsc = new Comparator<NimPlayer>()
    {
        @Override
        public int compare(NimPlayer o1, NimPlayer o2)
        {
            double w1 = o1.getWinRatio();
            double w2 = o2.getWinRatio();

            if (w1 == w2)
            {
                return o1.getUserName().compareTo(o2.getUserName());
            }
            else if (w1 - w2 < 0)
            {
                return -1;
            }
            else
            {
                return 1;
            }

        }
    };




    //the function, which can output the symbol "$"
    public static void dollar()
    {
        System.out.println();
        System.out.printf("$");
    }

    /**The addPlayer method
     * Three different parameters as the input
     * @param username
     * @param familyName
     * @param givenName
     * Allows new players to be added to the game
     * If a player with the given username already exists,the system should indicate this
     * The number of the players will be stored as playerNumber*/
    public void addPlayer(String username,String familyName,String givenName)
    {
        username = username.trim();//eliminates leading and trailing spaces
        familyName = familyName.trim();
        givenName = givenName.trim();
        for(int i=0;i<playerNumber;i++)
        {
            if(players[i].getUserName().equals(username))
            {
                System.out.println("The player already exists.");
                dollar();
                return;
            }
        }
        players[playerNumber]=new NimHumanPlayer(username,givenName,familyName);
        playerNumber++;
        dollar();
        return;
    }


    /**The addAIPlayer method
     * Three different parameters as the input
     * @param username
     * @param familyName
     * @param givenName
     * Allows new AI players to be added to the game
     * If a player with the given username already exists,the system should indicate this
     * The number of the players will be stored as playerNumber*/
    public void addAIPlayer(String username,String familyName,String givenName)
    {
        username = username.trim();//eliminates leading and trailing spaces
        familyName = familyName.trim();
        givenName = givenName.trim();
        for(int i=0;i<playerNumber;i++)
        {
            if(players[i].getUserName().equals(username))
            {
                System.out.println("The player already exists.");
                dollar();
                return;
            }
        }
        players[playerNumber]=new NimAIPlayer(username,givenName,familyName);
        playerNumber++;
        dollar();
        return;
    }



    /**Allows players to be removed from the game
     * The username of the player to be removed is given as an argument to the command
     * @param removeUsername*/
    public void remove(String removeUsername)
    {
        removeUsername = removeUsername.trim();//remove the space between command and uername
        if (removeUsername.equals(""))
        {
            System.out.println("Are you sure you want to remove all players? (y/n)");
            if (keyboard.nextLine().equals("y"))
            {
                playerNumber = 0;
                dollar();
                return;
            }
            else
            {
                dollar();
                return;
            }
        }
        //remove a player with username
        for(int i = 0; i < playerNumber; i++)
        {
            if(players[i].getUserName().equals(removeUsername))
            {
                for(int j = i; j < playerNumber - 1; j++)
                {
                    players[j]=players[j+1];
                }
                players[playerNumber-1] = null;
                playerNumber = playerNumber - 1;
                dollar();
                return;
            }
        }
        //Did not find the remove player
        System.out.println("The player does not exist.");
        dollar();
        return;
    }



    /** Allows player details to be edited
    * If a username for a non-existent player is given, the system should indicate that the player does not exist
    * Three parameters should be input:
    * @param username
     * @param newFamilyName
     * @param newGivenName */
    public void edit(String username, String newFamilyName, String newGivenName)
    {
        username = username.trim();//eliminates leading and trailing spaces
        newFamilyName = newFamilyName.trim();
        newGivenName = newGivenName.trim();
        for (int i = 0; i < playerNumber; i++)
        {
            if (players[i].getUserName().equals(username))
            {
                players[i].editName(newFamilyName, newGivenName);
                dollar();
                return;
            }
        }
        System.out.println("The player does not exist.");
        dollar();
        return;
    }


    /**Allows player statistics to be reset
     * The username of the player whose statistics are to be reset is given as an argument to the command
     * If no username is given, the command should reset all player statistics
     * One input parameter in this method
     * @param resetUserName */
    public void reset(String resetUserName)
    {
        resetUserName = resetUserName.trim();//remove the space from input
        //remove all players in this system
        if (resetUserName.equals(""))
        {
            System.out.println("Are you sure you want to reset all player statistics? (y/n)");
            if (keyboard.nextLine().equals("y"))
            {
                for (int i = 0; i < playerNumber; i++)
                {
                    players[i].resetStats();

                }
                dollar();
                return;
            }
            else
            {
                dollar();
                return;
            }
        }
        //begin remove the player with username
        for (int i = 0; i < playerNumber; i++)
        {
            if (players[i].getUserName().equals(resetUserName))
            {
                players[i].resetStats();
                dollar();
                return;
            }
        }
        //did not find the username in the system
        System.out.println("The player does not exist.");
        dollar();
        return;
    }

    /**Displays player information
     * The username of the player whose information is to be displayed is given as an argument to the command
     * If no username is given, the command should display information for all players, ordered by username alphabetically
     * If a username for a non-existent player is given, the system should indicate that the player does not exist
     * One parameter should be input
     * @param displayUsername */
    public void display(String displayUsername)
    {
        displayUsername = displayUsername.trim();//remove the space of user input displayusername
        if (displayUsername.equals(""))
        {
            Arrays.sort(players,0,playerNumber, dispUser);
            for (int i = 0; i < playerNumber; i++)
            {
                System.out.println(players[i].getUserName()+","+players[i].getGivenName()
                        +","+players[i].getFamilyName()+","+players[i].getTotalPlayGames()
                +" games,"+players[i].getWonGames()+" wins");
            }
            dollar();
            return;
        }
        //check the user input username
        for (int i = 0; i < playerNumber; i++)
        {
            if (players[i].getUserName().equals(displayUsername))
            {
                System.out.println(players[i].getUserName()+","+players[i].getGivenName()
                        +","+players[i].getFamilyName()+","+players[i].getTotalPlayGames()
                        +" games,"+players[i].getWonGames()+" wins");
                dollar();
                return;
            }
        }
        //not find that user input username
        System.out.println("The player does not exist.");
        dollar();
        return;
    }

    /**Outputs a list of player rankings
     *There are three columns displayed:
     * The first column displays percentage wins or winning ratio
     * The second column displays the number of games played
     * The final column shows the player’s full name
     * The sort order is desc or descending by default
     * If the user provides asc as an argument, the players should be ranked by the percentage of games they have won in ascending order
     * One parameter should be input:
     * @param rank */
    public void rankings(String rank)
    {
        rank = rank.trim();//remove the space of the user input
        int rankNumber;//set the number will be displayed, no more than 10
        if (playerNumber > 10)
        {
            rankNumber = 10;
        }
        else
        {
            rankNumber = playerNumber;
        }

        if (rank.equals("") || rank.equals("desc"))//set the default input, which should be DESC ranking
        {
            Arrays.sort(players,0,playerNumber,rankDesc);//Call the array sort with DESC
        }
        else
        {
            Arrays.sort(players,0,playerNumber,rankAsc);//Call the array sort with ASC
        }

        for (int i = 0; i < rankNumber; i++)
        {
            int ratio = (int)Math.round(players[i].getWinRatio() * 100);//Transfer the winning ratio to int
            System.out.printf("%-4s",(String)(ratio + "%"));
            System.out.printf(" | %02d games | ",players[i].getTotalPlayGames());
            System.out.println(players[i].getGivenName() + " " + players[i].getFamilyName());
        }
        dollar();
        return;

    }


    /**Creates and commences a game of Nim
     * The system will first check the correctness of the username input
     * The input should be an array, which store the two usernames, initial stone and uppbound stone
     * @param gameInfo */
    public void playGame(String gameInfo)
    {
        gameInfo = gameInfo.trim();
        String[] gameData = gameInfo.split(",");//divide the game info into array
        //find the left stone, this method generally used to convert String to Integer in Java
        int leftStone = Integer.parseInt(gameData[0]);
        int upperBound = Integer.parseInt(gameData[1]);

        int i;//check the two users, if they are in the player system
        for (i = 0; i < playerNumber; i++)
        {
            if (players[i].getUserName().equals(gameData[2]))
            {
                break;
            }
        }

        int j;
        for (j = 0; j < playerNumber; j++)
        {
            if (players[j].getUserName().equals(gameData[3]))
            {
                break;
            }
        }
        //One of the player does not in the system
        if (i == playerNumber || j == playerNumber)
        {
            System.out.println("One of the players does not exist.");
            dollar();
            return;
        }
        else
        {
            NimGame game = new NimGame(leftStone,upperBound, players[i],players[j]);
            dollar();
            return;
        }
    }


    //main method of this class
    public static void main(String[] args)
    {
        Nimsys nimsys = new Nimsys();//create a new instance of this class
        System.out.println("Welcome to Nim");
        dollar();

        /**Set the file stream to store players info
         * The file name should be "players.dat"*/
        File playerInfoFile = new File("players.dat");
        if(playerInfoFile.exists())
        {
            try
            {//Read the info from exist file, should be Object input
                ObjectInputStream inputStream = new ObjectInputStream(
                        new BufferedInputStream(new FileInputStream("players.dat" )));
                players = (NimPlayer[]) inputStream.readObject();
                for(int i = 0; i < players.length; i++)
                {
                    if(players[i] != null)
                        playerNumber++;
                }
                inputStream.close();
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }

        while (true)
        {
            try
            {
                String userInput = keyboard.next();
                //Judge if user input contains in the command list,if not,throw a exception
                if (!commandInputArray.contains(userInput))
                {
                    keyboard.nextLine();
                    throw new InvalidCommandInputException(userInput);
                }

                //add player
                if (userInput.equals("addplayer"))
                {
                    String input = keyboard.nextLine();
                    String[] inputName = input.split(",");//add the user input to one array, using split to divide input
                    //Judge if user input argument is equals to 3
                    if (inputName.length != 3)
                    {
                        throw new InvalidArgInputException();
                    }
                    else
                    {
                        nimsys.addPlayer(inputName[0], inputName[1], inputName[2]);
                    }
                }

                //add AIplayer
                if (userInput.equals("addaiplayer"))
                {
                    String input = keyboard.nextLine();
                    String[] inputName = input.split(",");//add the user input to one array, using split to divide input
                    //Judge if user input argument is equals to 3
                    if (inputName.length != 3)
                    {
                        throw new InvalidArgInputException();
                    }
                    else
                    {
                        nimsys.addAIPlayer(inputName[0], inputName[1], inputName[2]);
                    }
                }

                //remove player
                if (userInput.equals("removeplayer"))
                {
                    String input = keyboard.nextLine();
                    String[] input_remove = input.split(",");
                    //judge the invalid input arguments
                    if (input_remove.length <1)
                    {
                        throw new InvalidArgInputException();
                    }
                    else
                    {
                        nimsys.remove(input_remove[0]);
                    }
                }

                //edit player
                if (userInput.equals("editplayer"))
                {
                    String input = keyboard.nextLine();
                    String[] inputName = input.split(",");
                    //judge the input invalid arguments,if will throw a new exception
                    if (inputName.length != 3)
                    {
                        throw new InvalidArgInputException();
                    }
                    else
                    {
                        nimsys.edit(inputName[0], inputName[1], inputName[2]);
                    }
                }

                //reset user states
                if (userInput.equals("resetstats"))
                {
                    String input = keyboard.nextLine();
                    String[] input_reset = input.split(",");
                    if (input_reset.length != 1)
                    {
                        throw new InvalidArgInputException();
                    }
                    else
                    {
                        nimsys.reset(input_reset[0]);
                    }
                }

                //display player
                if (userInput.equals("displayplayer"))
                {
                    String input = keyboard.nextLine();
                    String[] input_dis = input.split(",");
                    if (input_dis.length != 1)
                    {
                        throw new InvalidArgInputException();
                    }
                    else
                    {
                        nimsys.display(input_dis[0]);
                    }
                }

                //ranking the player
                if (userInput.equals("rankings"))
                {
                    String input = keyboard.nextLine();
                    String[] input_rank = input.split(",");
                    if (input_rank.length != 1)
                    {
                        throw new InvalidArgInputException();
                    }
                    else
                    {
                        nimsys.rankings(input_rank[0]);
                    }
                }


                //start playing the game
                if (userInput.equals("startgame"))
                {
                    String input = keyboard.nextLine();
                    String[] input_info = input.split(",");
                    if (input_info.length != 4)
                    {
                        throw new InvalidArgInputException();
                    }
                    else
                    {
                        nimsys.playGame(input);
                    }
                }

                //exit
                if (userInput.equals("exit"))
                {
                    ObjectOutputStream outputStream;

                    try
                    {
                        outputStream = new ObjectOutputStream( new BufferedOutputStream(
                                new FileOutputStream("players.dat" )));

                        outputStream.writeObject(players);
                        outputStream.flush();
                        outputStream.close();
                    }
                    catch (IOException e)
                    {
                        System.out.println(e.getMessage());
                    }

                    System.out.println();
                    System.exit(0);
                }
            }
            catch (InvalidCommandInputException e)
            {
                System.out.println(e.getMessage());
                dollar();
            }
            catch (InvalidArgInputException e)
            {
                System.out.println(e.getMessage());
                dollar();
            }

        }
    }
}


/**
 * This class can handle the Invalid Command input from user
 * Will three a exception that will tell user invalid input command
 * have one constructor to output the exception message*/
class InvalidCommandInputException extends Exception
{
    /**Constructor of this class
     * One parameter should be input: user Invalid input
     * @param inputCommand */
    public InvalidCommandInputException(String inputCommand)
    {
        super("\'" + inputCommand + "\' is not a valid command.");
    }
}

/**
 * This class will handle Invalid number of arguments
 * The user enters a valid Nimsys command, but does not provide the correct number of arguments*/
class InvalidArgInputException extends Exception
{
    /**Constructor with no argument*/
    public InvalidArgInputException()
    {
        super("Incorrect number of arguments supplied to command.");
    }
}

