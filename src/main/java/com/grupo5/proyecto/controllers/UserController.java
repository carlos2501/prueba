package com.grupo5.proyecto.controllers;

import com.grupo5.proyecto.services.implementations.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;

import java.util.List;

public class UserController {
@Autowired
private SessionRegistry sessionRegistry;

public void listLoggedInUsers() {
final List<Object> allPrincipals = sessionRegistry.getAllPrincipals();

        for (final Object principal : allPrincipals) {
        if (principal instanceof MyUserDetails) {
        final MyUserDetails user = (MyUserDetails) principal;

        List<SessionInformation> activeUserSessions =
        sessionRegistry.getAllSessions(principal,
        /* includeExpiredSessions */ false); // Should not return null;

        if (!activeUserSessions.isEmpty()) {
        // Do something with user
        System.out.println(user);
        }

}
}
        }
        }