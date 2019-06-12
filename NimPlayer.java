/**COMP90041 Project B
 * Programmer Name: Lingxuan Kong
 * Student ID: 957828*/

public abstract class NimPlayer implements java.io.Serializable
{
    private String userName;
    private String givenName;
    private String familyName;
    private int totalPlayGames;
    private int totalWinGames;
    private double winRatio;



    /**Constructor of this class
     * include three parameters
     * @param userName
     * @param givenName
     * @param familyName
     */
    public NimPlayer(String userName,String givenName, String familyName)
    {
        this.userName = userName;
        this.givenName = givenName;
        this.familyName = familyName;
    }
    /**get Method of this class*/
    //get UserName method
    public String getUserName()
    {
        return userName;
    }
    //get GivenName method
    public String getGivenName()
    {
        return givenName;
    }
    //get FamilyName method
    public String getFamilyName()
    {
        return familyName;
    }
    //get play games
    public int getTotalPlayGames()
    {
        return totalPlayGames;
    }
    //get won games
    public int getWonGames()
    {
        return totalWinGames;
    }
    //The number of played games will added 1
    public void addPlayGames()
    {
        totalPlayGames = totalPlayGames + 1;
    }
    //The number of won games will be added 1
    public  void addWinGames()
    {
        totalWinGames = totalWinGames + 1;
    }


    /**Edit Name method
     * Two different parameter input
     * @param newGivenName
     * @param newFamilyName */
    public void editName(String newFamilyName, String newGivenName)
    {
        familyName = newFamilyName;
        givenName = newGivenName;
    }

    //reset players states
    public void resetStats()
    {
        totalPlayGames = 0;
        totalWinGames = 0;
    }


    //get win ratio
    public double getWinRatio()
    {
        if(totalWinGames == 0)
        {
            winRatio = 0;
        }
        else
        {
            winRatio = (double) totalWinGames / (double) totalPlayGames;
        }
        return winRatio;
    }

    /**The method of remove stones*/
    public abstract void removeStone();
}


