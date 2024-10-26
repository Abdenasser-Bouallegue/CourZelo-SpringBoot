package io.medali.repository;

import io.medali.entity.Role;
import io.medali.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);
  @Modifying
  @Query("update User u set u.blocking = TRUE where u.id = ?1")
  void userBlocage(long id);

  @Modifying
  @Query("update User u set u.blocking = FALSE where u.id = ?1")
  void deblocageuser(long id);

  List<User> findByRole(Role role);

  User findByFirstname(String name);

}
