package com.luxoft.logeek.service.impl;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.luxoft.logeek.AppConfig;
import com.luxoft.logeek.repository.SimpleRepository;
import com.luxoft.logeek.service.SavingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Commit
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@TestExecutionListeners(value = {
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
@DatabaseSetup("/SavingServiceImplTest.xml")
public class SavingServiceImplTest {

    @Autowired
    private SavingService savingService;
    @Autowired
    private SimpleRepository repository;

    private final Long id = 1L;
    private final String newName = "ololo";

    @Test
    public void modifyWithoutCallingSave() {
        savingService.modifyWithoutCallingSave(id, newName);
    }

    @Test
    public void modifyCallingSave() {
        savingService.modifyCallingSave(id, newName);
    }

    @AfterTransaction
    public void afterTransaction() {
        assertThat(repository.findOne(id).getName(), is(newName));
    }
}