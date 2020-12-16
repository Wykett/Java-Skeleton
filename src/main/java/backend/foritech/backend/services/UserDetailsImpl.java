package backend.foritech.backend.services;

import backend.foritech.backend.entities.PersonEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String email;

	@JsonIgnore
	private String password;
	
	private boolean verified;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Long id, String email, String password, boolean verified,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.email = email;
		this.verified = verified;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(PersonEntity personUser) {
		GrantedAuthority authority = new SimpleGrantedAuthority(personUser.getRole());
		List<GrantedAuthority> authorities = Arrays.asList(authority);

		return new UserDetailsImpl(personUser.getPersonId(), personUser.getPersonEmail(), personUser.getPassword(), personUser.isVerified(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getId() {
		return this.id;
	}
	
	public String getEmail() {
		return this.email;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	public boolean isVerified() {
		return this.verified;
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
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
}