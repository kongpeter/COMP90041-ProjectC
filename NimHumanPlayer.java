/**COMP90041 Project B
 * Programmer Name: Lingxuan Kong
 * Student ID: 957828*/

public class NimHumanPlayer extends NimPlayer
{
    /**The constructor of this class
     * Three arguments from NimPlayer class
     * @param userName
     * @param givenName
     * @param familyName */
    public NimHumanPlayer(String userName, String givenName, String familyName)
    {
        super(userName, givenName, familyName);
    }

    /**The move method in human player class
     * User should input the stone number they want to move
     * The exception will work for the invalid input*/
    public void removeStone()
    {
        int limit;//set limit of remove
        int remove;
        String removeStone;
        //Set the limit stone, upper bound remove
        if (NimGame.getLeftStone() > NimGame.getUpperBound())
        {
            limit = NimGame.getUpperBound();
        }
        else
        {
            limit = NimGame.getLeftStone();
        }

        System.out.println(this.getGivenName() + "'s turn - remove how many?");

        try
        {
            //put the input into try block, to handle with exception
            removeStone = Nimsys.keyboard.nextLine();
            //transform string to int, this step may call exception
            remove = Integer.parseInt(removeStone);

            if (remove > 0 && remove <= limit)
            {
                NimGame.setLeftStone(remove);
                return;
            }
            else //user input is int data type, but not in the 1 to limit
            {
                System.out.println();
                System.out.println("Invalid move. You must remove between 1 and " + limit + " stones.");
            }

            System.out.println();
            showLeftStone();
            removeStone();
        }
        catch (Exception e)//if the user input meeting exception
        {
            System.out.println();
            System.out.println("Invalid move. You must remove between 1 and " + limit + " stones.");
            System.out.println();
            showLeftStone();
            removeStone();
        }
    }

    /**Dsiplay the left stone when game is playing*/
    public void showLeftStone()
    {
        // set the left stone
        int leftStone = NimGame.getLeftStone();
        System.out.printf("%d stones left:", leftStone);
        for (int i = 0;i < leftStone; i++)
        {
            System.out.printf(" *");
        }
        System.out.printf("\n");
    }
}
