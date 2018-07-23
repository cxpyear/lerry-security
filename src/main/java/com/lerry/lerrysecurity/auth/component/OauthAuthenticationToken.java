package com.lerry.lerrysecurity.auth.component;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created with CodeGenerator
 * Description:
 * @author  LErry.li
 * Date: 2018年7月23日
 * Time: 下午3:50:28
 */
public class OauthAuthenticationToken extends AbstractAuthenticationToken {

    private Object details;

    public Object principal;

    public Object credentials;

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public OauthAuthenticationToken(Collection<? extends GrantedAuthority> authorities , UserDetails userDetails , String userName , String passWord) {
        super(authorities);
        this.principal = userName;
        this.credentials = passWord;
        if(userDetails!= null){
            this.details = userDetails;
            this.setAuthenticated(true);
        }else {
            throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
        }
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    public static void setAuthentication(UserDetails userDetails, String userName, String passWord){
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        Authentication authentication = new OauthAuthenticationToken(authorities, userDetails, userName, passWord);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AbstractAuthenticationToken token = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        token.setDetails(userDetails);
    }
}
