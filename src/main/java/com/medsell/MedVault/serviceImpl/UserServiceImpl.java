/**
 * 
 */
package com.medsell.MedVault.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medsell.MedVault.Exception.ResourceNotFoundException;
import com.medsell.MedVault.dto.UserDto;
import com.medsell.MedVault.entity.User;
import com.medsell.MedVault.repo.UserRepo;
import com.medsell.MedVault.service.NotificationService;
import com.medsell.MedVault.service.UserService;

/**
 * 
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
    private NotificationService notificationService;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User saveuser = this.userRepo.save(user);
		UserDto userToDto = this.userToDto(saveuser);
		int user_id = saveuser.getId();
		String message = "New user registered: " + user.getUsername();
        notificationService.createNotificationForAdmin(message,user_id);
		return userToDto;
	}

	@Override
	public boolean userExist(String username) {
		
		return findUserByUserName(username).isPresent();
	}
	public Optional<User>findUserByUserName(String username){
		return  this.userRepo.findByUsername(username);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		user.setEmail(userDto.getEmail());
		user.setFirst_name(userDto.getFirst_name());
		user.setLast_name(userDto.getLast_name());
		user.setUsername(userDto.getUsername());
		user.setUser_profile_image(userDto.getUser_profile_image());
		user.setPassword(userDto.getPassword());
		User updateuser = this.userRepo.save(user);
		UserDto userDTO2 = this.userToDto(updateuser);
		return userDTO2;
	}

	@Override
	public UserDto getUserById(int userId) {
		User user = this.userRepo.findById(userId).get();
		UserDto userToDto = this.userToDto(user);
		return userToDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> collect = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		
		return collect;
	}

	@Override
	public void deleteUsers(int userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		this.userRepo.delete(user);
	}
	
	@Override
	public int userCount() {
		int count = (int) this.userRepo.count();
		return count;
	}
	
	public double calculateActiveUserPercentage() {
        long totalUsers = userRepo.count();
        long activeUsers = userRepo.countByIsActive(1); // Assuming 1 represents active users

        if (totalUsers == 0) {
            return 0.0; // To avoid division by zero
        }

        return (double) (activeUsers * 100) / totalUsers;
    }
	
//	@Override
//	public boolean updateUserstatus(String status,Integer userId) {
//
//		boolean updatestatus = this.userRepo.updatestatus(status,userId);
//		return updatestatus;
//
//	}

	private User dtoToUser(UserDto userDto) {
		User map = this.modelMapper.map(userDto, User.class);
		return map;
	}

	private UserDto userToDto(User user) {
		UserDto map = this.modelMapper.map(user, UserDto.class);
		return map;

	}

}
