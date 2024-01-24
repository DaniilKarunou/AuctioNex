package com.auctionex.service;

import com.auctionex.entity.User;

import java.util.Set;

public interface UserService {

    /**
     * Saves a user.
     *
     * @param user The user to be saved.
     * @return The saved user.
     */
    User saveUser(User user);

    /**
     * Finds a user by login.
     *
     * @param login The login of the user to be found.
     * @return The found user or null if not found.
     */
    User findUserByLogin(String login);

    /**
     * Retrieves all users.
     *
     * @return An iterable collection of all users.
     */
    Iterable<User> getAllUsers();

    /**
     * Retrieves users with pagination.
     *
     * @param pageNr        The page number.
     * @param howManyOnPage The number of users on each page.
     * @return An iterable collection of users for the specified page.
     */
    Iterable<User> getAllUsersPaging(Integer pageNr, Integer howManyOnPage);

    /**
     * Deletes a user by login.
     *
     * @param login The login of the user to be deleted.
     */
    void deleteUser(String login);

    /**
     * Checks if a user with the specified email exists.
     *
     * @param email The email to be checked.
     * @return True if exists, false otherwise.
     */
    boolean existsByEmail(String email);

    /**
     * Retrieves a user by email.
     *
     * @param email The email of the user to be retrieved.
     * @return The retrieved user.
     */
    User getUserByEmail(String email);

    void deleteUserByEmail(String email);

//    /**
//     * Retrieves users by role.
//     *
//     * @param role The role to filter users.
//     * @return An iterable collection of users with the specified role.
//     */
//    Iterable<User> getUsersByRole(String role);
//
//    /**
//     * Updates user details.
//     *
//     * @param login     The login of the user to be updated.
//     * @param updatedUser The updated user details.
//     * @return The updated user.
//     */
//    User updateUser(String login, User updatedUser);
//
//    /**
//     * Changes user password.
//     *
//     * @param login    The login of the user to change the password.
//     * @param newPassword The new password.
//     */
//    void changeUserPassword(String login, String newPassword);
//
//    /**
//     * Sets the account status of a user to either locked or unlocked.
//     *
//     * @param login  The login of the user.
//     * @param blocked True to block the account, false to unblock.
//     */
//    void setUserAccountStatus(String login, boolean blocked);
//
//    User getCurrentUser();
//    boolean existsByLogin(String login);
//    Set<String> getUserRoles(String login);
//    void setUserRole(String login, String newRole);
}
