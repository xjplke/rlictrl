package cn.adfi.rlictrl.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="tbl_user")
public class User implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7219329081810399223L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Long id;
	
	@Column(length=32,unique=true, nullable=false)
	private String username;
	
	@Column(length=64,nullable=false)
	private String password;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<ROLE> roles = new HashSet<ROLE>();
	
	private boolean enabled;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(enabled && roles !=null &&  roles.size()>0){
			Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
			for(ROLE role : roles){
				authorities.add(new SimpleGrantedAuthority(role.getName()));
			}
			return authorities;
		}
		return Collections.emptyList();
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
