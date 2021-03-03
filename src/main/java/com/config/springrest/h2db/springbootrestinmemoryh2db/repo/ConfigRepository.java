package com.config.springrest.h2db.springbootrestinmemoryh2db.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.config.springrest.h2db.springbootrestinmemoryh2db.model.Config;

@Repository
public interface ConfigRepository extends JpaRepository<Config, Long> {
	
	/*
	 * Config findByAppCodeAndVersion(String appCode, String version)
     *
     *   Fetch a config with the app code and version
	 */
	Config findByAppCodeAndVersion(String appCode, String version);
	
	/*
	 * List<Config> findByAppCodeOrderByLastModifiedDateDesc(String appCode)
     *
     *  Return all the configs given an app code sorted in descending order by last modified date.
	 */
	List<Config> findByAppCodeOrderByLastModifiedDateDesc(String appCode);
}
