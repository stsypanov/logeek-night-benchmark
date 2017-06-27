package com.luxoft.logeek.repository;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * Created by Сергей on 24.12.2016.
 */
public class BaseDao {

	@Autowired
	protected EntityManager em;
}
