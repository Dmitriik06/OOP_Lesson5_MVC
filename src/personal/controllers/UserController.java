package personal.controllers;

import personal.model.Repository;
import personal.model.User;

import java.util.List;

public class UserController {
    private final Repository repository;

    public UserController(Repository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        validateUserData(user);
        repository.CreateUser(user);
    }

    public User readUser(String userId) throws Exception {
        List<User> users = repository.getAllUsers();
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }

        throw new Exception("User not found");
    }
    public List<User> readUsers(){
        return repository.getAllUsers();
    }

    private void validateUserData(User user){
        if (user.getFirstName().isEmpty()
                || user.getLastName().isEmpty()
                || user.getPhone().isEmpty()){
            throw new IllegalStateException("User fields are empty");
        }
    }

    public void editUser(User user){
        validateUserData(user);
        repository.updateUser(user);
    }
}
