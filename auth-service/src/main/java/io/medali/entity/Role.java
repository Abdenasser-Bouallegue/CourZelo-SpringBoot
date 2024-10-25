package io.medali.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static io.medali.entity.Permission.*;

@RequiredArgsConstructor
public enum Role {


  USER(Collections.emptySet()),
  ADMIN(
          Set.of(
                  ADMIN_READ,
                  ADMIN_UPDATE,
                  ADMIN_DELETE,
                  ADMIN_CREATE
          )
  ),
  SUPERADMIN(
          Set.of(
                  SUPERADMIN_READ,
                  SUPERADMIN_UPDATE,
                  SUPERADMIN_DELETE,
                  SUPERADMIN_CREATE,
                  ADMIN_READ,
                  ADMIN_UPDATE,
                  ADMIN_DELETE,
                  ADMIN_CREATE
          )
  ),
  TEACHER(
          Set.of(
                  TEACHER_READ,
                  TEACHER_UPDATE,
                  TEACHER_DELETE,
                  TEACHER_CREATE
          )
  ),
  STUDENT(
          Set.of(
                  STUDENT_READ,
                  STUDENT_UPDATE,
                  STUDENT_DELETE,
                  STUDENT_CREATE
          )
  ),HEADOFDEPARTEMENT(
          Set.of(
                  HEADOFDEPARTEMENT_READ,
                  HEADOFDEPARTEMENT_UPDATE,
                  HEADOFDEPARTEMENT_DELETE,
                  HEADOFDEPARTEMENT_CREATE
          )
  ),RECRUITER(
          Set.of(
                  RECRUITER_READ,
                  RECRUITER_UPDATE,
                  RECRUITER_DELETE,
                  RECRUITER_CREATE
          )
  )

  ;

  @Getter
  private final Set<Permission> permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}
