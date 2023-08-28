package com.tuyano.springboot;

public class Certification {
	
	static String  result = null;
	static boolean checkResult = false;
	
	public static String admit(String name, String password, Iterable<AccountData> accountList) {
		
		result = null;

		accountList.forEach(account -> {
			if(account.getName().equals(name) && account.getPassWord().equals(password)) {
				result = account.getName();
				account.setLoggedIn(true);
			}
		});
		
		System.out.println(result == null ? "false" : result);
		return result;
	}
	
	public static boolean check(String name, Iterable<AccountData> accountList) {
		
		checkResult = false;
		
		accountList.forEach(account -> {
			if(account.getName().equals(name) && account.getLoggedIn() == true) {
				checkResult = true;
			}
		});
		return checkResult;
	}


	public static void logout(String name, Iterable<AccountData> accountlist){
		accountlist.forEach(account -> {
			if(account.getName().equals(name)){
				account.setLoggedIn(false);
				System.out.println(account.getName() + " is " + account.getLoggedIn());
			}
		});
	}
}
