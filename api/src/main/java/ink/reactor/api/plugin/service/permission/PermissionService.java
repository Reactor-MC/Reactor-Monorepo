package ink.reactor.api.plugin.service.permission;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface PermissionService {
    /**
     * Get online user ranks
     * @param user id to search
     *  @return a collection of all user ranks, false if user isn't in cache
     */
    Collection<UserRank> getRanks(final String user);

    /**
     * Get online user permissions
     * @param user id to search
     * @return all user permissions, false if user isn't in cache
     */
    Set<String> getPermissions(final String user);

    /**
     * Check if a user is operator
     * @param user id to search
     * @return if user is operator
     */
    boolean isOp(final String user);

    /**
     * Set ranks to a player. If player don't exist in database, create it
     * @param user id to search
     * @param ranks collections of ranks
     */
    void setRanks(final String user, final Collection<Rank> rank);

    /**
     * Add rank to a player. If player don't exist in database, create it
     * @param user id to search
     * @param rank rank to add
     */
    void addRank(final String user, final Rank rank);

    /**
     * Remove rank to a player.
     * @param user id to search
     * @param rank rank to add
     */
    boolean removeRank(final String user, final Rank rank);

    /**
     * Get user permissions instance in cache.
     * @param user id to search
     * @return user permissions, false if user isn't in cache
     */
    UserPermissions getUserInfo(final String user);

    /**
     * Check if user contains all permissions
     * @param user id to search
     * @param permissions permission
     * @return true if user has all permissions, false if user isn't in cache
     */
    boolean hasPermission(final String user, final String permission);

    /**
     * Check if user contains all permissions
     * @param user id to search
     * @param permissions an array of all permissions
     * @return true if user has all permissions, false if user isn't in cache
     */
    boolean hasPermissions(final String user, final String... permissions);

    /**
     * Remove permissions from user
     * @param user id to search
     * @param permissions an array of all permissions
     * @return true if one or more permissions are removed
     */
    boolean removePermissions(final String user, final String... permissions);
    
    /**
     * Add permissions to user. If player don't exist in database, create it
     * @param user id to search
     * @param permissions an array of all permissions
     */
    void addPermissions(final String user, final String... permissions); 

    /**
     * Set permissions to user. If player don't exist in database, create it
     * @param user id to search
     * @param permissions an array of all permissions
     */
    void setPermission(final String user, final String permission);

    /**
     * Get query to database
     * @param user id to search
     * @return a future of user permissions
     */
    CompletableFuture<UserPermissions> getUser(final String user);

    /**
     * Get query to database and if absent create the user
     * @param user id to search
     * @return a future of user permissions
     */
    CompletableFuture<UserPermissions> createIfAbsent(final String user);

    /**
     * Insert query to database
     * @param user id to search
     * @return a future of boolean - if player can be saved or no
     */
    CompletableFuture<Boolean> savePlayer(final String user);
}