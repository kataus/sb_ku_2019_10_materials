package ru.itvitality.sbrf.cu.spring.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service
public class MyService {
    @Secured( {"ROLE_USER"} )
    public void onlyUser() {
        System.out.println( "Hello, i'm here" );
    }

    public void onlyAdmin() {}
}
