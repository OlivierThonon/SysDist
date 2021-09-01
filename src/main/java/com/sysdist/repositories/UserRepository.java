package com.sysdist.repositories;

import com.sysdist.models.Article;
import com.sysdist.models.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, String> {
}
