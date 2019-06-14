package org.coldis.library.test.spring.installer;

import org.apache.commons.lang3.ArrayUtils;
import org.coldis.library.spring.installer.DataInstaller;
import org.coldis.library.test.TestHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

/**
 * Data installer test.
 */
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class DataInstallerTest extends TestHelper {

	/**
	 * Test data.
	 */
	public static final TestEntity[] NON_UPDATABLE_DATA = { new TestEntity(13, 23, "33"),
					new TestEntity(14, 24, "teste5\r\n	\r\n\"abc") };

	/**
	 * Test data.
	 */
	public static final TestEntity[] UPDATABLE_DATA = { new TestEntity(10, 20, "30"), new TestEntity(11, 21, "31"),
					new TestEntity(12, 22, "32") };

	/**
	 * Test data.
	 */
	public static final TestEntity[] ALL_DATA = ArrayUtils.addAll(DataInstallerTest.NON_UPDATABLE_DATA,
			DataInstallerTest.UPDATABLE_DATA);

	/**
	 * Test repository.
	 */
	@Autowired
	private TestRepository testRepository;

	/**
	 * Data installer.
	 */
	@Autowired
	private DataInstaller dataInstaller;

	/**
	 * Cleans test data.
	 *
	 * @throws Exception If the test fails.
	 */
	@AfterEach
	@Transactional
	public void clean() throws Exception {
		this.testRepository.deleteAll();
	}

	/**
	 * Tests the auto installation.
	 *
	 * @throws Exception If the test fails.
	 */
	@Test
	public void testAutoInstallation() throws Exception {
		// For each test object.
		for (final TestEntity testEntity : DataInstallerTest.ALL_DATA) {
			// Asserts that the object have been created.
			Assertions.assertEquals(testEntity, this.testRepository
					.findById(new TestEntityKey(testEntity.getProperty1(), testEntity.getProperty2())).orElse(null));
		}
	}

	/**
	 * Tests the multiple installations.
	 *
	 * @throws Exception If the test fails.
	 */
	@Test
	public void testMultipleInstallation() throws Exception {
		// Installs data.
		this.dataInstaller.install();
		// For each test object.
		for (final TestEntity testEntity : DataInstallerTest.ALL_DATA) {
			// Asserts that the object have been created.
			Assertions.assertEquals(testEntity, this.testRepository
					.findById(new TestEntityKey(testEntity.getProperty1(), testEntity.getProperty2())).orElse(null));
		}
		// Installs data.
		this.dataInstaller.install();
		// For each updatable object.
		for (final TestEntity testEntity : DataInstallerTest.UPDATABLE_DATA) {
			// Gets the persisted entity.
			final TestEntity persistedEntity = this.testRepository
					.findById(new TestEntityKey(testEntity.getProperty1(), testEntity.getProperty2())).orElse(null);
			// Asserts that the object have been created.
			Assertions.assertEquals(testEntity, persistedEntity);
			// Makes sure the created and updated date are different.
			Assertions.assertTrue(persistedEntity.getCreatedAt().isBefore(persistedEntity.getUpdatedAt()));
		}
		// For each non-updatable object.
		for (final TestEntity testEntity : DataInstallerTest.NON_UPDATABLE_DATA) {
			// Gets the persisted entity.
			final TestEntity persistedEntity = this.testRepository
					.findById(new TestEntityKey(testEntity.getProperty1(), testEntity.getProperty2())).orElse(null);
			// Asserts that the object have been created.
			Assertions.assertEquals(testEntity, persistedEntity);
			// Makes sure the created and updated date are different.
			Assertions.assertTrue(persistedEntity.getCreatedAt().isEqual(persistedEntity.getUpdatedAt()));
		}
	}
}
