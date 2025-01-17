package personal.model;

import java.util.ArrayList;
import java.util.List;

public class RepositoryFile implements Repository {
    private UserMapper mapper = new UserMapper();
    private FileOperation fileOperation;

    public RepositoryFile(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            List<String> lines = fileOperation.readAllLines();
            for (String line : lines) {
                users.add(mapper.map(line));
            }
        } catch (ArrayIndexOutOfBoundsException e){
        }
        return users;
    }

    @Override
    public String CreateUser(User user) {

        List<User> users = getAllUsers();
        int max = 0;
        for (User item : users) {
            int id = Integer.parseInt(item.getId());
            if (max < id){
                max = id;
            }
        }
        int newId = max + 1;
        String id = String.format("%d", newId);
        user.setId(id);
        users.add(user);
        List<String> lines = new ArrayList<>();
        for (User item: users) {
            lines.add(mapper.map(item));
        }
        fileOperation.saveAllLines(lines);
        return id;
    }

    public void updateUser(User user){
        deleteUser(user.getId());
        List<User> users = getAllUsers();
        saveUser(user, users);
    }

    private void saveUser(User user, List<User> users){
        users.add(user);
        saveUsers(users);
    }

    public void deleteUser(String userID){
        List<User> users = getAllUsers();
        users.remove(findUser(userID, users));
        saveUsers(users);
    }

    private User findUser(String userID, List<User> users){
        for (User user: users) {
            if (user.getId().equals(userID)) {
                return user;
            }
        }
        throw new IllegalStateException("User not found");
    }

    private void saveUsers(List<User> users){
        List<String> lines = new ArrayList<>();
        for (User item: users) {
            lines.add(mapper.map(item));
        }
        fileOperation.saveAllLines(lines);
    }
}
