package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import models.User;


@Service
public class UserService {
    private final List<User> store = new ArrayList<>();

    public UserService() {
        store.add(new User(UUID.randomUUID().toString(), "Prathiksha Kini", "gpkini2002@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(), "Padmini Kini", "kinipadmini@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(), "Mahalasa Kini", "kinimahalasa@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(), "Gurudath Kini", "gurukini@gmail.com"));
    }

    public List<User> getUsers() {
        return new ArrayList<>(store); // Return a copy to avoid external modification
    }

    public User getUserById(String userId) {
        return store.stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void addUser(User user) {
        if (store.stream().anyMatch(existingUser -> existingUser.getEmail().equals(user.getEmail()))) {
            throw new RuntimeException("Email already exists");
        }
        user.setUserId(UUID.randomUUID().toString());
        store.add(user);
    }

    public User updateUser(String userId, User updatedUser) {
        Optional<User> userOptional = store.stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst();

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            return user;
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public void deleteUser(String userId) {
        boolean removed = store.removeIf(user -> user.getUserId().equals(userId));
        if (!removed) {
            throw new RuntimeException("User not found");
        }
    }
}
