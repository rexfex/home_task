package org.itstep.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.itstep.dao.AccountDao;
import org.itstep.model.Account;
import org.junit.jupiter.api.Test;

class AccountDaoTest {

	@Test
	void testSave() {
		AccountDao accDao = new AccountDao();

		Account account = new Account("q", "Daniels","jj","qqqqq");

		accDao.save(account);
		assertNotNull(accDao.get("q").getPassword());
		accDao.delete("q");
	}

}
