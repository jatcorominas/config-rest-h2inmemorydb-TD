package com.config.springrest.h2db.springbootrestinmemoryh2db.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.config.springrest.h2db.springbootrestinmemoryh2db.model.Config;
import com.config.springrest.h2db.springbootrestinmemoryh2db.model.ConfigVO;
import com.config.springrest.h2db.springbootrestinmemoryh2db.service.ConfigService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ConfigController {
	
	@Autowired
	ConfigService configService;
	
	/*
	 * GET /api/{appCode}/config
     *  
     * Returns the configurations for a given app code sorted in descending order by last modified date
	 */
	
	@GetMapping("/{appCode}/config/{version}")
	public ResponseEntity<Config> getConfigsByAppCodeAndVersion(@PathVariable("appCode") String appCode, @PathVariable("version") String version){
		System.out.println("Get config by app code and version");
		Config configData = configService.findByAppCodeAndVersion(appCode, version);
		if( configData != null){
			Config _config = configData;
			return new ResponseEntity<Config>(_config, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/*
	 * GET /api/{appCode}/config/{version}
     *
     * Returns the config json object for an app code and version number. It is assumed that a config has one and only one
     * configuration for an app code and version number
	 * 
	 */
	
	@GetMapping("/{appCode}/config")
	public ResponseEntity<List<Config>> getConfigsByAppCode(@PathVariable("appCode") String appCode){
		System.out.println("Get config by app code sorted by last modified date in descending order ");
		List<Config> configData = configService.findByAppCodeSortedByLastModifiedDateInDescendingOrder(appCode);
		if( !configData.isEmpty()){
			return new ResponseEntity<List<Config>>(configData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/*
	 * POST /api/{appCode}/config/{version}
     *
     * Creates a new configuration provided that there is no existing config with the same properties
	 */
	@PostMapping("/{appCode}/config/{version}")
	public Config postCustomer(@PathVariable("appCode") String appCode, @PathVariable("version") String version, @RequestBody ConfigVO configvo) {
		System.out.println("Create a new config if it does not already exist...");
		Config existingConfig = configService.findByAppCodeAndVersion(appCode, version);
		if(existingConfig != null){
			return existingConfig;
		}
		return configService.saveConfig(configvo);
	}

	/*
	 * PUT /api/{appCode}/config/{version}
     *
     * Updates a configuration for a given app code and version number
	 */

	@PutMapping("/{appCode}/config/{version}")
	public ResponseEntity<Config> updateCustomer(@PathVariable("appCode") String appCode, @PathVariable("version") String version, @RequestBody ConfigVO config) {
		System.out.println("Update Config with appCode = " + appCode + " version = " + version + "...");
		Config updatedConfig = configService.updateConfig(appCode, version, config);
		if(updatedConfig != null){
			return new ResponseEntity<>(updatedConfig, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
