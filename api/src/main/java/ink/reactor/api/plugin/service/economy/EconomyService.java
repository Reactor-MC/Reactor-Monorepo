package ink.reactor.api.plugin.service.economy;

import java.util.concurrent.CompletableFuture;

public interface EconomyService {

    /**
     * Checks if a player has an associated economic account.
     * 
     * @param user The name of the player.
     * @return true if the player has an account, false otherwise.
     */
    public boolean hasAccount(final String user);

    /**
     * Gets the current balance of a player.
     * 
     * @param user The name of the player whose balance is to be retrieved.
     * @return The current balance of the player.
     */
    public double getBalance(final String user);

    /**
     * Checks if a player has enough money for a transaction.
     * 
     * @param user The name of the player.
     * @param amount The amount of money to check.
     * @return true if the player has enough money, false otherwise.
     */
    public boolean hasBalance(final String user, final double amount);

    /**
     * Performs a withdrawal operation for a player.
     * 
     * @param user The name of the player.
     * @param amount The amount of money to withdraw.
     * @return A response containing the withdrawn amount, updated balance, and the result of the operation.
     */
    public EconomyResponse withdrawPlayer(final String user, final double amount);

    /**
     * Performs a deposit operation for a player.
     * 
     * @param user The name of the player.
     * @param amount The amount of money to deposit.
     * @return A response containing the deposited amount, updated balance, and the result of the operation.
     */
    public EconomyResponse depositPlayer(final String user, final double amount);

    /**
     * Creates an account for a player if one does not already exist.
     * 
     * @param user The name of the player for whom the account will be created.
     * @return true if the account was created successfully, false if the account already exists.
     */
    public CompletableFuture<Boolean> createPlayerAccount(final String user);
}
