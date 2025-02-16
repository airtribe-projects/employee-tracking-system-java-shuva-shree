package com.airtribe.EmployeeTrackingSystem.webSecurityConfig;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CustomOAuth2Service {

    public DefaultOAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest){

        OAuth2User oauth2User = new DefaultOAuth2UserService().loadUser( oAuth2UserRequest);
        Set<GrantedAuthority> authorities = new HashSet<>();

        // Extract roles from the token
        Map<String, Object> attributes = oauth2User.getAttributes();
        String role = (String) attributes.get("role"); // Assume roles are provided in the 'role' attribute

        // Map roles to Spring Security authorities
        if ("ADMIN".equals(role)) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else if ("MANAGER".equals(role)) {
            authorities.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_EMPLOYEE"));
        }

        return new DefaultOAuth2User(authorities, attributes, "name");
    }

}
