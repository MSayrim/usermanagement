package com.example.usermanagement.service;


import com.example.usermanagement.dto.constant.ShortUserDTO;
import com.example.usermanagement.dto.constant.UserDTO;
import com.example.usermanagement.dto.request.UserFilterDTO;
import com.example.usermanagement.entity.User;
import com.example.usermanagement.enums.ErrorType;
import com.example.usermanagement.enums.Role;
import com.example.usermanagement.exception.GenericServiceException;
import com.example.usermanagement.mapper.UserMapper;
import com.example.usermanagement.repository.UserRepository;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDTO createOrUpdate(UserDTO userDTO){

        validateUser(userDTO);

        User user = userMapper.toEntity(userDTO);
        user.setName(WordUtils.capitalizeFully(user.getName()));
        user.setSurname(WordUtils.capitalizeFully(user.getSurname()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(userDTO.getId() != null && !userDTO.getId().equals("")){
            User readUser = userRepository.findByIdAndActiveTrue(userDTO.getId()).orElseThrow( () -> new GenericServiceException(ErrorType.ENTITY_NOT_FOUND, "This personnel is not found!"));
            user.setId(readUser.getId());
        }
        user.setActive(Boolean.TRUE);
        return userMapper.toDTO(userRepository.save(user));
    }

    private void validateUser(UserDTO userDTO) {
        User user = userRepository.findAllByUsernameAndActiveTrue(userDTO.getUsername());
        if (user != null && !user.getId().equals(userDTO.getId())) {
            throw new GenericServiceException(ErrorType.USERNAME_ALREADY_IN_USE);
        }
    }

    public Page<UserDTO> filter(UserFilterDTO userFilterDTO){
        Page<User> users = userRepository.findAll((Specification<User>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(userFilterDTO.getNameSurname() != null ){
                Expression<String> nameSurname = criteriaBuilder.concat(criteriaBuilder.concat(root.get("name"), " "), root.get("surname"));
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(nameSurname, "%" + userFilterDTO.getNameSurname() + "%")));
            }
            if (userFilterDTO.getTelephone() != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("telephone"), "%" + userFilterDTO.getTelephone() + "%")));
            }
            if (userFilterDTO.getRole() != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("role"), userFilterDTO.getRole())));
            }
            predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("active"), Boolean.TRUE)));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, userFilterDTO.getGenericPage().getPageable());
        return users.map(userMapper::toDTO);
    }

    public UserDTO getSpecificPersonnel(String id){
        User personnel = userRepository.findByIdAndActiveTrue(id).orElseThrow(() -> new GenericServiceException(ErrorType.ENTITY_NOT_FOUND, "This Personnel is not found!"));
        return userMapper.toDTO(personnel);
    }

    public List<ShortUserDTO> getAllPersonnelsByRole(String role){
        List<User> personnels = userRepository.findAllByRoleAndActiveTrue(Role.valueOf(role));
        return userMapper.toShortDTOList(personnels);
    }

    public User getSpecificPersonnelById(String id){
        return userRepository.findByIdAndActiveTrue(id).orElseThrow(() -> new GenericServiceException(ErrorType.ENTITY_NOT_FOUND, "This Personnel is not found!"));
    }


    @PostConstruct
    private void addAdminPersonnel(){
        List<User> users = userRepository.findAll();
        if(CollectionUtils.isEmpty(users)){
            User user = new User();
            user.setName("ilk");
            user.setSurname("admin");
            user.setUsername(user.getName()+"."+user.getSurname());
            user.setTelephone("5554443322");
            user.setEmail("ilkadmin@mailinator.com");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setRole(Role.ADMIN);
            user.setActive(Boolean.TRUE);
            userRepository.save(user);

        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsernameAndActiveTrue(username).orElseThrow();
    }
}
