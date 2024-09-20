package com.polyglot.appsecurity.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.polyglot.appsecurity.model.AccessModel;


@Service
public class AuthService {

	 private static final Random RANDOM = new Random();
	 
	 public static List<AccessModel> generateMockAccessModelList(int count) {
	        List<AccessModel> accessModels = new ArrayList();
	        for (int i = 0; i < count; i++) {
	            AccessModel accessModel = new AccessModel();
	            accessModel.setUserId(generateRandomUserId());
	            accessModel.setFullName(generateRandomFullName());
	            accessModel.setUserName(generateRandomUserName());
	            accessModel.setPassword(generateRandomPassword());
	            accessModels.add(accessModel);
	        }
	        return accessModels;
	    }
	 
	 private static Integer generateRandomUserId() {
	        return RANDOM.nextInt(10000);
	    }

	    private static String generateRandomFullName() {
	        String[] firstNames = {"John", "Jane", "Alex", "Emily", "Chris", "Katie"};
	        String[] lastNames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis"};
	        return firstNames[RANDOM.nextInt(firstNames.length)] + " " + lastNames[RANDOM.nextInt(lastNames.length)];
	    }

	    private static String generateRandomUserName() {
	        String[] userNames = {"fvc", "janedoe", "alexjones", "emilybrown", "chrisknight", "katiedavis"};
	        return userNames[RANDOM.nextInt(userNames.length)];
	    }

	    private static String generateRandomPassword() {
	        String[] passwords = {"1a.2b.3c.", "qwerty123", "abc123XYZ", "passw0rd", "securePass", "admin1234"};
	        return passwords[RANDOM.nextInt(passwords.length)];
	    }
	
	public List<AccessModel>  getAcces() {
		
		return  generateMockAccessModelList(3);
	}
	
	public Boolean validatedCredentials(String UserName, String Password) {
        List<AccessModel> result = getAcces();
        List<AccessModel> resultFilter = result.stream()
                .filter(t -> t.getUserName().equals(UserName) && t.getPassword().equals(Password))
                .collect(Collectors.toList());
        if (null == resultFilter || resultFilter.isEmpty()) {
            return false;
        }
        return true;
    }
	
}