package com.mbti.mbtitest.config.auth;

import com.mbti.mbtitest.domain.user.User;
import com.mbti.mbtitest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Collections;

@Service
@Transactional
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final OAuth2UserRepository oAuth2UserRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        org.springframework.security.oauth2.client.userinfo.OAuth2UserService<OAuth2UserRequest, OAuth2User> service
                = new DefaultOAuth2UserService();

        // Spring Security가 access token을 이용해 OAuth2 server에서 유저 정보를 가져온 후,
        // loadUser 메서드를 통해 유저의 정보를 가져온다.
        OAuth2User oAuth2User = service.loadUser(userRequest); // OAuth service(kakao, naver)에서 가져온 유저 정보

        String registrationId = userRequest.getClientRegistration().getRegistrationId(); // OAuth service 이름(예, 카카오, 네이버)

        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName(); // OAuth2 로그인 시 키(PK)가 되는 값

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        // 소셜에서 가져온 유저 정보를 통한 save or update
        User user = saveOrUpdate(attributes);

        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRole().role)),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes){
        // 이메일을 기준으로 유저를 찾아온다. Optional으로 찾아오기 때문에 값이 만약 null이라면,
        // Optional의 orElse 메서드를 통해 저장된 값을 유저로 만들어 반환한다.
        User user =  oAuth2UserRepository.findOneByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName()))
                .orElse(attributes.toEntity());


        return oAuth2UserRepository.save(user);
    }
}
