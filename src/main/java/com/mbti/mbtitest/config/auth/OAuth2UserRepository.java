package com.mbti.mbtitest.config.auth;

import com.mbti.mbtitest.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OAuth2UserRepository {

    private final EntityManager em;

    // 이메일에 해당하는 유저 정보가 있는지 확인하는 함수
    public Optional<User> findOneByEmail(String email){

        System.out.println("repository email : " + email);

        System.out.println("repository email find result : " + em.createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList()
                .stream().findAny().toString());

        return em.createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList()
                .stream().findAny();
    }

    // 소셜 로그인 유저 저장처리 부분
    public User save(User user) {

        System.out.println("repository user save : " + user.toString());
        System.out.println("repository user : " + user.getEmail());

        if(user.getId() == null) {
            //user.setProfile_yn("N");
            em.persist(user);
        } else {
            em.merge(user);
        }
        return user;
    }

}
