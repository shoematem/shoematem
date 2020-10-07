/**
 * Round
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         @date 08/14/2020
 */

package crackthecode.dto;

import java.util.Objects;

public class Round
{
    private int roundID, gameID;
    private String guess, result, timeOfGuess;

    @Override
    public String toString() {
        return "Round{" + "roundID=" + roundID + ", gameID=" + gameID + ", guess=" + guess + ", result=" + result + ", timeOfGuess=" + timeOfGuess + '}';
    }

    public int getRoundID()
    {
        return roundID;
    }
    
    public int getGameID()
    {
        return gameID;
    }
    
    public String getGuess()
    {
        return guess;
    }
    
    public String getResult()
    {
        return result;
    }
    
    public String getTimeOfGuess()
    {
        return timeOfGuess;
    }
        
    public void setRoundID(int roundID)
    {
        this.roundID = roundID;
    }
    
    public void setGameID(int gameID)
    {
        this.gameID = gameID;
    }

    public void setGuess(String guess)
    {
        this.guess = guess;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

    public void setTimeOfGuess(String timeOfGuess)
    {
        this.timeOfGuess = timeOfGuess;
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
        
        final Round other = (Round) obj;
        
        if (this.roundID != other.roundID)
        {
            return false;
        }
        
        if (this.gameID != other.gameID)
        {
            return false;
        }
        
        if (!Objects.equals(this.guess, other.guess))
        {
            return false;
        }
        
        if (!Objects.equals(this.result, other.result))
        {
            return false;
        }
        
        if (!Objects.equals(this.timeOfGuess, other.timeOfGuess))
        {
            return false;
        }
        
        return true;
    }
}