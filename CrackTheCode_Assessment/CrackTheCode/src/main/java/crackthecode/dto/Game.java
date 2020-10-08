/**
 * Game
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         @date 08/14/2020
 */

package crackthecode.dto;

import java.util.Objects;

public class Game
{
    private int gameID;
    private String answer, gameStatus;

    @Override
    public String toString() {
        return "Game{" + "gameID=" + gameID + ", answer=" + answer + ", gameStatus=" + gameStatus + '}';
    }
    
    public int getGameID()
    {
        return gameID;
    }
    
    public String getAnswer()
    {
        return answer;
    }
    
    public String getGameStatus()
    {
        return gameStatus;
    }
    
    public void setGameID(int gameID)
    {
        this.gameID = gameID;
    }
    
    public void setAnswer(String answer)
    {
        this.answer = answer;
    }
    
    public void setGameStatus(String gameStatus)
    {
        this.gameStatus = gameStatus;
    }
    
    @Override
    public int hashCode()
    {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        
        if (obj == null)
        {
            return false;
        }
        
        if (getClass() != obj.getClass())
        {
            return false;
        }
        
        final Game other = (Game) obj;
        
        if (this.gameID != other.gameID)
        {
            return false;
        }
        
        if (!Objects.equals(this.answer, other.answer))
        {
            return false;
        }
        
        if (!Objects.equals(this.gameStatus, other.gameStatus))
        {
            return false;
        }
        
        return true;
    }
}