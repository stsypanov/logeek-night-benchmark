package com.luxoft.logeek.service;

import com.luxoft.logeek.AppConfig;
import com.luxoft.logeek.dto.AuditDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Сергей on 12.02.2017.
 */
@Transactional
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class AuditLocalServiceImplTest {

	@Autowired AuditLocalService service;

	private Set<AuditDto> inserts;

	@Before
	public void setUp() {
		inserts = new HashSet<>();

		for (long i = 0; i < 100; i++) {
			inserts.add(new AuditDto(i));
		}
	}

	@Test
	public void name() {
		service.auditChanges(inserts);
	}
}