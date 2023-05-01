package com.banking.repository;

import com.banking.entity.Account;
import com.banking.entity.User;
import com.banking.utility.HibernateUtility;
import com.banking.utility.MyFactoryRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class UserRepository extends MyFactoryRepository<User, Long> {

    public UserRepository() {
        super(new User());
    }

    public Optional<User> findByEmail(String email) {
        openSession();
        CriteriaQuery<User> criteria = getCriteriaBuilder().createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root);
        criteria.where(getCriteriaBuilder().equal(root.get("email"), email));
        List<User> result = getEntityManager().createQuery(criteria).getResultList();
        if (result.isEmpty())
            return Optional.empty();
        closeSession();
        return Optional.of(result.get(0));
    }
}