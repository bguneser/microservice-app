package accountservice.service;


import accountservice.dto.AccountDto;
import accountservice.entity.Account;
import accountservice.repo.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class AccountService {

    private AccountRepository accountRepository;
    private ModelMapper modelMapper;

    public AccountDto get(String id) {

        Account account = accountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        AccountDto accountDto = modelMapper.map(account, AccountDto.class);
        return accountDto;
    }

    public AccountDto save(AccountDto accountDto) {
        Account account = modelMapper.map(accountDto, Account.class);
        account = accountRepository.save(account);
        accountDto.setId(account.getId());
        return accountDto;
    }

    @Transactional
    public AccountDto update(String id, AccountDto accountDto) {
        Assert.isNull(id, "id cannot bu null");
        Optional<Account> account = accountRepository.findById(id);
        Account accountToUpdate = account.map(it -> {
            it.setBirthDate(accountDto.getBirthDate());
            it.setName(accountDto.getName());
            it.setSurname(accountDto.getSurname());
            return it;
        }).orElseThrow(IllegalArgumentException::new);

        return modelMapper.map(accountRepository.save(accountToUpdate), AccountDto.class);

    }

    @Transactional
    public void delete(String id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        accountRepository.delete(account);

    }

    public Slice<AccountDto> findAll(Pageable pageable) {
        Slice<Account> accounts = accountRepository.findAll(pageable);
        return null;

    }


}
