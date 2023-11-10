package bookstore.app.book.service.impl;

import bookstore.app.book.entity.Account;
import bookstore.app.book.repository.AccountRepository;
import bookstore.app.book.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public Account createAccount(Account account) {
        if (accountRepository.getByUserName(account.getUsername()) != null){
            return null;
        }
        return accountRepository.save(account);
    }

    @Override
    public Account getById(Long id) {
        return accountRepository.getById(id);
    }

    @Override
    public Account getByUserName(String userName) {
        return accountRepository.getByUserName(userName);
    }
}
