package com.config.springrest.h2db.springbootrestinmemoryh2db.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.config.springrest.h2db.springbootrestinmemoryh2db.model.Config;
import com.config.springrest.h2db.springbootrestinmemoryh2db.repo.ConfigRepository;


@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext-test.xml"})
@DataJpaTest
public class ConfigRepositoryUnitTest {

		@Autowired
		private TestEntityManager entityManager;
		
		@Autowired
		ConfigRepository configRepository;
		
		@Test
		public void should_find_config_by_appcode_and_version(){
			Date today = new Date();
			Config config1 = new Config("APPCODE1", 1235, "2", new Date());
			entityManager.persist(config1);
			
			Config foundConfig = configRepository.findByAppCodeAndVersion("APPCODE1", "2");
			
			assertNotNull(foundConfig);
			assertThat(foundConfig).hasFieldOrPropertyWithValue("appCode", "APPCODE1");
			assertThat(foundConfig).hasFieldOrPropertyWithValue("port", 1235);
			assertThat(foundConfig).hasFieldOrPropertyWithValue("version", "2");
			assertThat(foundConfig).hasFieldOrPropertyWithValue("lastModifiedDate", today);
		}
		
		@Test
		public void should_create_a_config(){
			Date today = new Date();
			Config config = configRepository.save( new Config("APPCODE2", 3456, "3", today));
			
			assertThat(config).hasFieldOrPropertyWithValue("appCode", "APPCODE2");
			assertThat(config).hasFieldOrPropertyWithValue("port", 3456);
			assertThat(config).hasFieldOrPropertyWithValue("version", "3");
			assertThat(config).hasFieldOrPropertyWithValue("lastModifiedDate", today);
		}
		
		@Test
		public void should_update_a_config_by_appcode_and_version(){
			Date today = new Date();
			Config config1 = new Config("APPCODE3", 9084, "3", today);
			entityManager.persist(config1);
			
			Config config2 = new Config("APPCODE3", 2894, "3", today);
			entityManager.persist(config2);
			
			Config updatedConfig = new Config("APPCODE3", 2894, "3", today);
			
			Config config = configRepository.findById(config2.getId()).get();
			config.setAppCode(updatedConfig.getAppCode());
			config.setPort(updatedConfig.getPort());
			config.setVersion(updatedConfig.getVersion());
			config.setLastModifiedDate(updatedConfig.getLastModifiedDate());
			configRepository.save(config);
			
			Config checkConfig = configRepository.findById(config2.getId()).get();
			assertThat(checkConfig.getId()).isEqualTo(config2.getId());
			assertThat(checkConfig.getAppCode()).isEqualTo(updatedConfig.getAppCode());
			assertThat(checkConfig.getPort()).isEqualTo(updatedConfig.getPort());
			assertThat(checkConfig.getLastModifiedDate()).isEqualTo(updatedConfig.getLastModifiedDate());
			
		}
		
		@Test
		public void should_return_configs_in_decending_order_by_lastModifiedDate(){
			Date today = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(today);
			c.add(Calendar.DATE, -1);
			Date yesterday = c.getTime();
			c.setTime(yesterday);
			c.add(Calendar.DATE, -1);
			Date dayBeforeYesterday = c.getTime();
			
			Config dayBeforeYesterdayConfig = new Config("APPCODE4", 9876, "1", dayBeforeYesterday);
			Config yesterdayConfig = new Config("APPCODE4", 9782, "2", yesterday);
			Config todayConfig = new Config("APPCODE4", 3986, "3", today);
			entityManager.persist(dayBeforeYesterdayConfig);
			entityManager.persist(yesterdayConfig);
			entityManager.persist(todayConfig);
			
			List<Config> configs = configRepository.findByAppCodeOrderByLastModifiedDateDesc("APPCODE4");
			assertThat(configs.size()).isEqualTo(3);
			assertThat(configs.get(0)).isEqualTo(todayConfig);
			assertThat(configs.get(1)).isEqualTo(yesterdayConfig);
			assertThat(configs.get(2)).isEqualTo(dayBeforeYesterdayConfig);
			
			
		}
		
			
}
