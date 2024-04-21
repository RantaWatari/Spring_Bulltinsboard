package com.tuyano.springboot;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

import com.tuyano.springboot.repositories.AccountDataRepository;

import jakarta.inject.Inject;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@ContextConfiguration(locations = {"/src/test/resources/app-config.xml", "/src/test/resources/test-config.xml"}) 
@SpringBootTest
class bulletinBoardApplicationTests_Account {
    
	public bulletinBoardApplicationTests_Account() {
        System.out.println("Constructor");
    }
    
    @BeforeAll
    public static void setUpClass() {
        System.out.println("@BeforeAll");
    }
    
    @AfterAll
    public static void tearDownClass() {
        System.out.println("@AfterAll");
		
    }
    
    @BeforeEach
    public void setUp() {
        System.out.println("@BeforeEach");
    }
    
    @AfterEach
    public void tearDown() {
        System.out.println("@AfterEach");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(accountRepository.count());
		//System.out.println(accountRepository.findAll());
		for (AccountData testDetas: accountRepository.findAll()) {
			System.out.println(testDetas.getName());
		}
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		accountRepository.deleteAll();
    }

	/**
	 *何か知らんが、injectしないとダメみたい。
	 */
	@Inject
	private AccountDataRepository accountRepository;

	@DisplayName("最初のテスト")
	@Test
	void test1() {
		AccountData testAccount = new AccountData();
		testAccount.setName("testuser");
		testAccount.setPassWord("testpass");
		testAccount.setId(1234);
		testAccount.setLoggedIn(true);
		accountRepository.saveAndFlush(testAccount);

		assertEquals(testAccount.getName(), "testuser");
		assertEquals(testAccount.getPassWord(), "testpass");
		assertEquals(testAccount.getId(), 1234);
		assertEquals(testAccount.getLoggedIn(), true);

		//fail("Hello");
	}

	
	@DisplayName("複数のアカウントを入れた場合のテスト")
	@Test	
	void test2() {
		//accountRepository.deleteAll();
		AccountData testDeta = new AccountData();
		testDeta.setName("testuser1");
		testDeta.setPassWord("testpass1");
		accountRepository.saveAndFlush(testDeta);
		
		AccountData testAccount2 = new AccountData();
		testAccount2.setName("testuser2");
		testAccount2.setPassWord("testpass2");
		accountRepository.saveAndFlush(testAccount2);
		
		AccountData testAccount3 = new AccountData();
		testAccount3.setName("testuser3");
		testAccount3.setPassWord("testpass3");
		accountRepository.saveAndFlush(testAccount3);
		
		Integer index = 1;
		String[] testText = {"testuser","testpass"};
		for (AccountData testDetas: accountRepository.findAll()) {
			String strIndex = index.toString();
			
			assertEquals(testDetas.getName(), testText[0]+strIndex);
			assertEquals(testDetas.getPassWord(), testText[1]+strIndex);
			index++;
		}
	}
	
	
	@DisplayName("複数のアカウントを入れた場合のテスト")
	@Test	
	void test3() {
		//accountRepository.deleteAll();
		AccountData testDeta = new AccountData();
		testDeta.setName("testuser1");
		testDeta.setPassWord("testpass1");
		accountRepository.saveAndFlush(testDeta);
		
		AccountData testAccount2 = new AccountData();
		testAccount2.setName("testuser2");
		testAccount2.setPassWord("testpass2");
		accountRepository.saveAndFlush(testAccount2);
		
		AccountData testAccount3 = new AccountData();
		testAccount3.setName("testuser3");
		testAccount3.setPassWord("testpass3");
		accountRepository.saveAndFlush(testAccount3);
		
		Integer index = 1;
		String[] testText = {"testuser","testpass"};
		for (AccountData testDetas: accountRepository.findAll()) {
			String strIndex = index.toString();
			
			assertEquals(testDetas.getName(), testText[0]+strIndex);
			assertEquals(testDetas.getPassWord(), testText[1]+strIndex);
			index++;
		}
	}
	
	@Test
	void test00() {
		String test = " w atari ";
		test = test.strip();
		System.out.println(test);
		System.out.println(test.isEmpty());
		String test2 = " ";
		test2 = test2 .strip();
		System.out.println(test2);
		System.out.println(test2.isEmpty());
	}
}
