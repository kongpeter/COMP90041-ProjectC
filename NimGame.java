/**COMP90041 Project B
 * Programmer Name: Lingxuan Kong
 * Student ID: 957828*/
public class NimGame
{
    /**Define two data type will be used in this class
     * The left stones
     * The remove upperBound stone*/
    private static int leftStone;
    private static int upperBound;

    /**constructor of this class, four parameters:
     * @param leftStone
     * @param upperBound
     * @param player1
     * @param player2*/
    public NimGame(int leftStone, int upperBound, NimPlayer player1, NimPlayer player2)
    {
        //Set a int data type, which can define the winner, player2 or player2
        int whoWins = 0;
        this.leftStone = leftStone;
        this.upperBound = upperBound;

        //initial the game the user information
        System.out.println();
        System.out.println("Initial stone count: " + leftStone);
        System.out.println("Maximum stone removal: " + upperBound);
        System.out.println("Player 1: " + player1.getGivenName() + " " + player1.getFamilyName());
        System.out.println("Player 2: " + player2.getGivenName() + " " + player2.getFamilyName());

        //add the number of played games to two players
        player1.addPlayGames();
        player2.addPlayGames();

        //start play game
        while (this.leftStone > 0)
        {
            displayLeftStone();
            player1.removeStone();
            if(this.leftStone == 0)
            {
                whoWins = 2;
                break;
            }

            displayLeftStone();
            player2.removeStone();
            if (this.leftStone == 0)
            {
                whoWins = 1;
                break;
            }
        }

        System.out.println();
        System.out.printf("Game Over\n");

        if (whoWins == 1)
        {
            player1.addWinGames();//add win games to player_1
            System.out.println(player1.getGivenName() + " " + player1.getFamilyName() + " wins!");
        }
        else if (whoWins == 2)
        {
            player2.addWinGames();//add win games to player_2
            System.out.println(player2.getGivenName() + " " + player2.getFamilyName() + " wins!");
        }
        return;
    }
    //print left stone of this game
    public void displayLeftStone()
    {
        System.out.println();
        System.out.printf("%d stones left:", leftStone);
        for (int i = 0;i < leftStone; i++)
        {
            System.out.printf(" *");
        }
        System.out.printf("\n");
    }
    //return left stones of this game
    public static int getLeftStone()
    {
        return leftStone;
    }
    //return upperbound of game
    public static int getUpperBound()
    {
        return upperBound;
    }
    /**The method of set left stone
     * @param removeStone*/
    public static void setLeftStone(int removeStone)
    {
        NimGame.leftStone = NimGame.leftStone - removeStone;
    }

}
