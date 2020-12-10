//package ro.upet.parking.system.management.rest.config;
//
//import java.util.Collection;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//
//import com.google.common.collect.ImmutableList;
//
//import ro.upet.parking.system.management.model.user.User;
//
//public class AuthenticationImpl implements Authentication {
//	
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	private User user;
//	
//
//	public AuthenticationImpl(User user) {
//		super();
//		this.user = user;
//	}
//
//	@Override
//	public String getName() {
//		return user.getUsername();
//	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return ImmutableList.of();
//	}
//
//	@Override
//	public Object getCredentials() {
//		return user.getPassword();
//	}
//
//	@Override
//	public Object getDetails() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Object getPrincipal() {
//		// TODO Auto-generated method stub
//		return this;
//	}
//
//	@Override
//	public boolean isAuthenticated() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
//		// TODO Auto-generated method stub
//
//	}
//
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//	
//	
//
//}
