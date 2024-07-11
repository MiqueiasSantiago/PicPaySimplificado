package com.desafiopicpay.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.desafiopicpay.domain.user.User;
import com.desafiopicpay.domain.user.UserType;
import com.desafiopicpay.dtos.UserDTO;
import com.desafiopicpay.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public void validateTransaction(User sender, BigDecimal amount) throws Exception {
		if(sender.getUserType() == UserType.COMMON) {
			throw new Exception("Usuário do tipo Lojista não está autorizado a realizar transação");
		}
		if(sender.getBalance().compareTo(amount) < 0) {
			throw new Exception("Saldo insuficiente");
		}
	}
	
	public User findUserById(Long id) throws Exception {
		return this.repository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
	}
	
	public User createUser(UserDTO data) {
		User newUser = new User(data);
		this.repository.save(newUser);
		return newUser;
	}
	
	public void saveUser(User user) {
		this.repository.save(user);
	}

	public List<User> getAllUsers() {
		return this.repository.findAll();
	}
}
