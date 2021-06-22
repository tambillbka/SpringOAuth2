package com.education.tutoringappserver.common.security;

import com.education.tutoringappserver.common.exception.ServiceStatus;
import com.education.tutoringappserver.common.utils.Constants;
import com.education.tutoringappserver.entities.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    private final CustomUserDetail userDetail;

    @Autowired
    public JwtTokenProvider(CustomUserDetail userDetail) {
        this.userDetail = userDetail;
    }

    @PostConstruct
    private void init() {
        Constants.SECRET_KEY = Base64.getEncoder().encodeToString(Constants.SECRET_KEY.getBytes());
    }

    public String createToken(String username, List<Role> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put(Constants.AUTH, roles.stream()
                .map(s -> new SimpleGrantedAuthority(s.getAuthority()))
                .collect(Collectors.toList()));

        Date now = new Date();
        Date validity = new Date(now.getTime() + Constants.EXPIRE_LENGTH);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS512, Constants.SECRET_KEY)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetail.loadUserByUsername(getUserName(token));
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public String getUserName(String token) {
        return Jwts.parser().setSigningKey(Constants.SECRET_KEY).parseClaimsJwt(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(Constants.AUTHORIZATION);
        return (Objects.nonNull(bearerToken) && bearerToken.startsWith(Constants.BEARER))
                ? bearerToken.substring(7) : null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(Constants.SECRET_KEY).parseClaimsJwt(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new ServiceStatus(Constants.INVALID_TOKEN, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
