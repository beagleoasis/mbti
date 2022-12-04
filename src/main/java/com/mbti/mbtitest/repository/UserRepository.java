package com.mbti.mbtitest.repository;

import com.mbti.mbtitest.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    public Optional<User> findOneByEmail(String email){
        return em.createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList()
                .stream().findAny();
    }

    public User save(User user) {
        if(user.getId() == null) {
            //user.setProfile_yn("N");
            em.persist(user);
        } else {
            em.merge(user);
        }
        return user;
    }
}
