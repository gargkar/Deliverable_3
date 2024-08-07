package ca.sheridancollege.project;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author brahm
 */
public class GameTest {

    @Test
    public void testDealCardsValid() {
        GoFishGame game = new GoFishGame("TestGame");
        Player player = new Player("TestPlayer");
        game.setPlayers(new Player[]{player});
        game.dealCards();
        assertEquals(5, player.getHand().getSize());
    }

    @Test
    public void testPlayerTurnsValid() {
        GoFishGame game = new GoFishGame("TestGame");
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        game.setPlayers(new Player[]{player1, player2});

        // Simulate initial hands for players
        player1.addCardToHand(new Card("Ace", "Spades"));
        player1.addCardToHand(new Card("2", "Hearts"));
        player1.addCardToHand(new Card("3", "Clubs"));
        player2.addCardToHand(new Card("3", "Diamonds"));
        player2.addCardToHand(new Card("4", "Clubs"));
        player2.addCardToHand(new Card("5", "Hearts"));

        // Manually perform a turn without user input
        Player otherPlayer = game.getPlayers()[1];
        Card card = otherPlayer.getHand().getCards().get(0);
        player1.addCardToHand(card);
        otherPlayer.removeCardFromHand(card);

        // Check hand sizes after the turn
        assertEquals(4, player1.getHand().getSize());
        assertEquals(2, otherPlayer.getHand().getSize());
    }

    @Test
    public void testGameOverConditionValid() {
        GoFishGame game = new GoFishGame("TestGame");
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        game.setPlayers(new Player[]{player1, player2});

        // Simulate a game over condition
        player1.addCardToHand(new Card("Ace", "Spades"));
        player1.addCardToHand(new Card("2", "Hearts"));
        player1.getHand().getCards().clear(); // Remove all cards from one player's hand

        assertTrue(game.isGameOver());
    }

    

    

    

    @Test
    public void testGameOverConditionBoundaryNoCardsLeft() {
        GoFishGame game = new GoFishGame("TestGame");
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        game.setPlayers(new Player[]{player1, player2});

        // Remove all cards from players' hands
        player1.getHand().getCards().clear();
        player2.getHand().getCards().clear();

        assertTrue(game.isGameOver()); // The game should be over when there are no cards left in any player's hand
    }

    @Test
    public void testDealCardsBoundaryMaxPlayers() {
        GoFishGame game = new GoFishGame("TestGame");
        Player[] players = new Player[4];
        for (int i = 0; i < 4; i++) {
            players[i] = new Player("Player" + (i + 1));
        }
        game.setPlayers(players);

        game.dealCards(); // Attempting to deal cards with the maximum number of players (4) should not throw an exception
    }

    
}
