package com.tuyano.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tuyano.springboot.AccountData;

@Repository
public interface AccountDataRepository  extends JpaRepository<AccountData, Long> {
	public List<AccountData> findByNameLike(String name);
	public AccountData findByName(String name);
	public List<AccountData> findByPassWord(String passWord);

}
