/**
 * RoundDao - 
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         @date 08/14/2020
 */

package crackthecode.dao;

import crackthecode.dto.Round;

public interface RoundDao
{
    Round getRoundByID(int gameID);
    Round addRound(Round round);
    void deleteRounds();
}