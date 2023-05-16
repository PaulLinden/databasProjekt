package org.example.controller;

import org.example.model.User;
import org.example.repository.UserRepository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class UserController {

    private final UserRepository userRepository;
    public UserController() {
        this.userRepository = new UserRepository();
    }

    public void getAllUsers() {
        try {
            List<HashMap<String, Object>> userList = userRepository.getAllUserData();

            for (HashMap<String, Object> user : userList) {
                for (String key : user.keySet()) {
                    System.out.println(key + ": " + user.get(key));
                }
                System.out.println("------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getUser(int id){
        User user = new User();
        user.setId(id);
        userRepository.getSpecificUser(user);
    }

    public void getUsersNameAndIdList() throws SQLException {
        List<HashMap<String, Object>> list = userRepository.getUsersNameAndId();

        for (HashMap<String, Object> user : list) {
            
            for (String key : user.keySet()) {
                System.out.println(key + ": " + user.get(key));
            }
            System.out.println("------------------------");
        }
    }

    public void checkUserId(int id) {
        User user = new User();
        user.setId(id);
        try {
            boolean exists = userRepository.userExists(user);
            if (exists) {
                System.out.println("User with ID " + id + " exists.");
            } else {
                System.out.println("User with ID " + id + " does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUser(String name, String email){
        try {
            User newUser = new User();
            newUser.setName(name);
            newUser.setEmail(email);

           userRepository.createUserRow(newUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void userOnlineController(){
        userRepository.getUsersOnline();
    }

    public void userOfflineController(){
        userRepository.getUsersOffline();
    }

    public void removeUser(int id) {
        User user = new User();
        user.setId(id);

        try {
            boolean exists = userRepository.userExists(user);
            if (exists) {
                userRepository.removeUserById(user);
                System.out.println("User with ID " + id + " has been removed.");
            } else {
                System.out.println("User with ID " + id + " does not exist.");
            }
        } catch (SQLException e) {
            // Handle the exception appropriately
            e.printStackTrace();
        }
    }

   /* public void updateUser(int id, String newName, String newEmail) {
        try {
            boolean exists = userRepository.isUserIdExists(id);
            if (exists) {
                User userToUpdate = new User();
                userToUpdate.setId(id);
                userToUpdate.setName(newName);
                userToUpdate.setEmail(newEmail);

                userRepository.updateUser(userToUpdate);
                System.out.println("User with ID " + id + " has been updated.");
            } else {
                System.out.println("User with ID " + id + " does not exist.");
            }
        } catch (SQLException e) {
            // Handle the exception appropriately
            e.printStackTrace();
        }
    }*/
}
