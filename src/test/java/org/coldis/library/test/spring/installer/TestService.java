package org.coldis.library.test.spring.installer;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Test service.
 */
@RestController
@RequestMapping(path = { "${org.coldis.library.spring.test.data-installer}" })
public class TestService {

	/**
	 * Test repository.
	 */
	@Autowired
	private TestRepository testRepository;

	/**
	 * Finds the test entity.
	 *
	 * @param  property1 Test property 1.
	 * @param  property2 Test property 2.
	 * @return           The test entity.
	 */
	@RequestMapping(method = { RequestMethod.GET }, path = "search")
	public TestEntity searchByTest1AndTest2(@RequestParam final Integer property1,
			@RequestParam final Integer property2) {
		return this.testRepository.findById(new TestEntityKey(property1, property2)).orElse(null);
	}

	/**
	 * Finds the test entity by its properties.
	 *
	 * @param  property1 Test property 1.
	 * @param  property2 Test property 2.
	 * @return           The test entity.
	 */
	@RequestMapping(method = { RequestMethod.GET }, path = "{property1}/{property2}")
	public TestEntity findByTest1AndTest2(@PathVariable final Integer property1,
			@PathVariable final Integer property2) {
		return this.testRepository.findById(new TestEntityKey(property1, property2)).orElse(null);
	}

	/**
	 * Updates the test entity.
	 *
	 * @param property1  Test property 1.
	 * @param property2  Test property 2.
	 * @param testEntity Entity data.
	 */
	@RequestMapping(method = { RequestMethod.PUT }, path = { "{property1}/{property2}" })
	public void update(@PathVariable final Integer property1, @PathVariable final Integer property2,
			@RequestBody final TestEntity testEntity) {
		// Finds the entity.
		final TestEntity retrievedEntity = this.testRepository.findById(new TestEntityKey(property1, property2))
				.orElse(null);
		// Updates the entity properties.
		BeanUtils.copyProperties(testEntity, retrievedEntity);
		this.testRepository.save(retrievedEntity);
	}

	/**
	 * Creates the test entity.
	 *
	 * @param testEntity Test entity.
	 */
	@RequestMapping(method = { RequestMethod.POST })
	public void create(@RequestBody final TestEntity testEntity) {
		this.testRepository.save(testEntity);
	}

}
