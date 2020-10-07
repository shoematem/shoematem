/**
 * GameDao - 
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         @date 08/14/2020
 */

package crackthecode.dao;

import crackthecode.dto.Game;
import java.util.List;

public interface GameDao
{
    List<Game> getAllGames();
    Game getGameByID(int gameID, boolean isCreateRound);
    Game addGame(Game game, String gameAnswer);
    void updateGame(int gameID);
    void deleteGames();
}