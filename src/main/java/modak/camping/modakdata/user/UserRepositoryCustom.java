package modak.camping.modakdata.user;

import org.springframework.data.domain.Page;

public interface UserRepositoryCustom {
    Page<User> findAll(String email);
}
