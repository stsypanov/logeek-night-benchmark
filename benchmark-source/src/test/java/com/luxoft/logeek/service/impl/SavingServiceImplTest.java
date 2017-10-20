package com.luxoft.logeek.service.impl;

import com.luxoft.logeek.AppConfig;
import com.luxoft.logeek.service.SavingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class SavingServiceImplTest {
    @Autowired
    private SavingService savingService;

    @Test
    public void modifyWithoutCallingSave() {

    }

    @Test
    public void modifyCallingSave() {

    }
}