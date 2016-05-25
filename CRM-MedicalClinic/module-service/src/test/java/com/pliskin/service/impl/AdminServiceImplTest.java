package com.pliskin.service.impl;

import com.pliskin.model.Admin;
import com.pliskin.model.Credentials;
import com.pliskin.model.Office;
import com.pliskin.repository.AdminRepository;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by aleksandrpliskin on 23.04.16.
 */
public class AdminServiceImplTest {

    private static AdminServiceImpl adminService;
    private static Credentials credentials;

    @BeforeClass
    public static void SetUp() {
        adminService = new AdminServiceImpl();
        adminService.adminRepository = mock(AdminRepository.class);
        Admin admin = new Admin();
        credentials = mock(Credentials.class);
        admin.setCredentials(credentials);
        when(adminService.adminRepository.findOneByCredentials(credentials)).thenReturn(admin);
    }

    @Test
    public void findOneByCredentialsShouldReturnCorrectUser() {
        Admin admin = adminService.findOneByCredentials(credentials);
        Assert.assertEquals(credentials, admin.getCredentials());
    }

}
