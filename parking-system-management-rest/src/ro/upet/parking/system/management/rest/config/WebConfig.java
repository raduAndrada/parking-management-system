package ro.upet.parking.system.management.rest.config;

import javax.inject.Inject;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;

import ro.upet.parking.system.management.business.api.user.UserService;

@Configuration
@ComponentScan("ro.upet.parking.system.management.rest")
public class WebConfig /*extends WebSecurityConfigurerAdapter   */{

	@Inject
	UserService userService;


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(new AuthenticationProviderImpl());
//    }
    
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		 http
//		 	.authorizeRequests()
//		 	.antMatchers("/v1/login").permitAll()
//		 	.anyRequest()
//		 	.authenticated()
//		 	.and()
//		 		.httpBasic();
//	}
//	
	

	
//	class AuthenticationProviderImpl implements AuthenticationProvider {
//
//		@Override
//		public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//			final String username = authentication.getName();
//			final String password = authentication.getCredentials().toString();
//			User user = userService.loginWithUsernameAndPassword(username, password);
//			if (user == null) {
//				user = userService.loginWithEmailAndPassword(username, password);
//			}
//			if (user == null) {
//				throw new BadCredentialsException("Invalid credentials");
//			}
//			return new UsernamePasswordAuthenticationToken(username, password);
//		}
//
//		@Override
//		public boolean supports(Class<?> authentication) {
//			return authentication.equals(UsernamePasswordAuthenticationToken.class);
//		}
//		
//	}

}

