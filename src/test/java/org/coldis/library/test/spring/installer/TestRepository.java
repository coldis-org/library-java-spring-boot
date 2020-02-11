package org.coldis.library.test.spring.installer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Test repository.
 */
@Repository
public interface TestRepository extends CrudRepository<TestEntity, TestEntityKey> {

}
