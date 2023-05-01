package com.banking.repository;

import com.banking.entity.Account;
import com.banking.entity.User;
import com.banking.utility.MyFactoryRepository;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class AccountRepository extends MyFactoryRepository<Account,Long> {
    public AccountRepository(){
        super(new Account());
    }

    public Optional<Account> findAccountByAccountNo(String accountNo){
        openSession();
        CriteriaQuery<Account> criteria = getCriteriaBuilder().createQuery(Account.class);
        Root<Account> root = criteria.from(Account.class);
        criteria.select(root);
        criteria.where(getCriteriaBuilder().equal(root.get("hesapNo"), accountNo));
        List<Account> result = getEntityManager().createQuery(criteria).getResultList();
        if (result.isEmpty()) return Optional.empty();
        closeSession();
        return Optional.of(result.get(0));
    }
}