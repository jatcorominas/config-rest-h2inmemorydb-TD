package com.config.springrest.h2db.springbootrestinmemoryh2db.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.config.springrest.h2db.springbootrestinmemoryh2db.model.Config;
import com.config.springrest.h2db.springbootrestinmemoryh2db.model.ConfigVO;
import com.config.springrest.h2db.springbootrestinmemoryh2db.repo.ConfigRepository;
import com.config.springrest.h2db.springbootrestinmemoryh2db.service.ConfigService;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext-test.xml"})
public class ConfigServiceUnitTest {

		@InjectMocks
		ConfigService configService;
		
		@Mock
		ConfigRepository mockConfigRepository;
		
		@Before
		public void init(){
			MockitoAnnotations.initMocks(this);
		}
		
		@Test
		public void findConfigByAppCodeAndVersion(){
			Date today = new Date();
			Config config =  new Config("APPCODE1", 1235, "2", today);
			when(mockConfigRepository.findByAppCodeAndVersion("APPCODE1", "2")).thenReturn(config);
			Config foundConfig = configService.findByAppCodeAndVersion("APPCODE1", "2");
			verify(mockConfigRepository, times(1)).findByAppCodeAndVersion("APPCODE1", "2");
			assertNotNull(foundConfig);
			assertThat(foundConfig).hasFieldOrPropertyWithValue("appCode", "APPCODE1");
			assertThat(foundConfig).hasFieldOrPropertyWithValue("port", 1235);
			assertThat(foundConfig).hasFieldOrPropertyWithValue("version", "2");
			assertThat(foundConfig).hasFieldOrPropertyWithValue("lastModifiedDate", today);
			
		}
		
		@Test
		public void createConfigTest(){
			Date today = new Date();
			ConfigVO configVO = new ConfigVO("APPCODE1", 1234, "1");
			Config expected = new Config("APPCODE1", 1234, "1", today);
			when(mockConfigRepository.save(expected)).thenReturn(expected);
			configService.saveConfig(configVO);
			verify(mockConfigRepository, times(1)).save(any(Config.class));	
		}
		
		@Test
		public void updateConfigTest(){
			Date today = new Date();
			ConfigVO configVO = new ConfigVO("APPCODE1", 2345, "1");
			Config config = new Config("APPCODE1", 1234, "1", today);
			when(mockConfigRepository.findByAppCodeAndVersion("APPCODE1", "1")).thenReturn(config);
			when(mockConfigRepository.save(config)).thenReturn(config);
			Config foundConfig = configService.updateConfig("APPCODE1", "1", configVO);
			verify(mockConfigRepository, times(1)).findByAppCodeAndVersion("APPCODE1", "1");
			verify(mockConfigRepository, times(1)).save(config);
			assertNotNull(foundConfig);
			assertThat(foundConfig).hasFieldOrPropertyWithValue("appCode", "APPCODE1");
			assertThat(foundConfig).hasFieldOrPropertyWithValue("port", 2345);
			assertThat(foundConfig).hasFieldOrPropertyWithValue("version", "1");
			
		}
		
		@Test
		public void findConfigsByAppCodeInDecendingOrderByLastModifiedDate(){
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
			
			List<Config> configList = new ArrayList<>();
			configList.add(todayConfig);
			configList.add(yesterdayConfig);
			configList.add(dayBeforeYesterdayConfig);
			
			when(mockConfigRepository.findByAppCodeOrderByLastModifiedDateDesc("APPCODE4")).thenReturn(configList);
			List<Config> actual = configService.findByAppCodeSortedByLastModifiedDateInDescendingOrder("APPCODE4");
			verify(mockConfigRepository, times(1)).findByAppCodeOrderByLastModifiedDateDesc("APPCODE4");
			assertThat(actual.size()).isEqualTo(3);
			assertThat(actual.get(0)).isEqualTo(todayConfig);
			assertThat(actual.get(1)).isEqualTo(yesterdayConfig);
			assertThat(actual.get(2)).isEqualTo(dayBeforeYesterdayConfig);
		}
				
}
