/**COMP90041 Project B
 * Programmer Name: Lingxuan Kong
 * Student ID: 957828*/

public class NimAIPlayer extends NimPlayer
{
    /**The constructor of this class
     * Three arguments from NimPlayer class
     * @param userName
     * @param givenName
     * @param familyName */
    public NimAIPlayer(String userName, String givenName, String familyName)
    {
        super(userName, givenName, familyName);
    }

    /**The method of remove stone of AI player
     * This method will calculate the remove stone*/
    public void removeStone()
    {
        int removeNumber;//initial move stone number
        int leftStone = NimGame.getLeftStone();//get the left stone from NimGame
        int upperMove = NimGame.getUpperBound();//get the upper bound from NimGame

        System.out.println(this.getGivenName()+"'s turn - remove how many?");

        //calculate the remove stone of AI player
        if(leftStone % (upperMove + 1) != 1)
        {
            removeNumber = (leftStone - 1) % (upperMove + 1);
        }
        else
        {
            removeNumber = 1;
        }

        //input the remove stone into NimGame
        NimGame.setLeftStone(removeNumber);
    }


    public String advancedMove(boolean[] available, String lastMove)
    {
        // the implementation of the victory
        // guaranteed strategy designed by you
        String move = "";

        return move;
    }
}
