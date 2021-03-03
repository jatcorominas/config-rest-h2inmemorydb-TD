package com.config.springrest.h2db.springbootrestinmemoryh2db.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.springrest.h2db.springbootrestinmemoryh2db.model.Config;
import com.config.springrest.h2db.springbootrestinmemoryh2db.model.ConfigVO;
import com.config.springrest.h2db.springbootrestinmemoryh2db.repo.ConfigRepository;


@Service
public class ConfigService {
	
	@Autowired
	ConfigRepository repository;
	
	/*
	 * public Config findByAppCodeAndVersion(String appCode, String version)
     *
     *   This message is invoked by the REST controller to retrieve a config given an app code and version number
	 */
	public Config findByAppCodeAndVersion(String appCode, String version){
		Config configData = repository.findByAppCodeAndVersion(appCode, version);
		return configData;
		
	}
	
	/*
	 * public List<Config> findByAppCodeSortedByLastModifiedDateInDescendingOrder(String appCode)
     *
     *   This message is invoked by the REST Controller to retrieve all the configurations for an app code sorted
     *   in descending order by last modified date.
	 */
	public List<Config> findByAppCodeSortedByLastModifiedDateInDescendingOrder(String appCode){
		List<Config> configsByAppCodeSortedByLastModifiedDateInDescOrder = new ArrayList<>();
		repository.findByAppCodeOrderByLastModifiedDateDesc(appCode).forEach(configsByAppCodeSortedByLastModifiedDateInDescOrder::add);
		return configsByAppCodeSortedByLastModifiedDateInDescOrder;
	}
	
	/*
	 * public Config saveConfig(ConfigVO configvo)
     *
     *   This message is invoked by the REST controller to create a new configuration provided that there is no
     *   config for the given app code and version number that exists in the database.
	 */
	public Config saveConfig(ConfigVO configvo){
		// Create a new config with today's date
		Config newConfig = repository.save(new Config(configvo.getAppCode(), configvo.getPort(), configvo.getVersion(), new Date()));
		return newConfig;
	}
	
	/*
	 * public Config updateConfig(String appCode, String version, ConfigVO configvo)
     *
     *  This message is invoked by the REST controller to update an existing config.
	 */
	public Config updateConfig(String appCode, String version, ConfigVO configvo){
		Config updatedConfig = null;
		Config configData = repository.findByAppCodeAndVersion(appCode, version);
		if (configData != null) {
			Config _config = configData;
			_config.setAppCode(configvo.getAppCode());
			_config.setPort(configvo.getPort());
			_config.setVersion(configvo.getVersion());
			_config.setLastModifiedDate(new Date());
			updatedConfig = repository.save(_config);
			
		}
		return updatedConfig;
	}
	
	

}
