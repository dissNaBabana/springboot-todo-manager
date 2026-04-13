package md.ceiti.spring.service;

import md.ceiti.spring.entity.User;
import md.ceiti.spring.entity.UserRole;
import md.ceiti.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> findById(int id){
        return userRepository.findById(id);
    }

    public List<User> findAllByRoleIn(Iterable<UserRole> roles){
        return userRepository.findAllByRoleInOrderById(roles);
    }

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user){
        userRepository.save(user);
    }

    public void updateRole(int id, UserRole newRole){userRepository.updateRole(id, newRole);}

    public void deleteById(int id){
        userRepository.deleteById(id);
    }

    public User getCurrentUser(){
        String email=  SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository
                .findByEmailIgnoreCase(email)
                .orElseThrow(()-> new IllegalArgumentException("User with email"+ email + "not found"));
    }


}
