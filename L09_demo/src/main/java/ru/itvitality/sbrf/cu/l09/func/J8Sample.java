package ru.itvitality.sbrf.cu.l09.func;

import org.h2.engine.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class J8Sample {
    public List<String> test() {

        List<User> users = new ArrayList<>();

        List<String> names = new ArrayList<>();
        for ( User user : users ) {
            if ( user.getName().startsWith( "A" ) ) {
                names.add( user.getName() );
            }
        }
        return names;
    }

    public List<String> test8() {

        List<User> users = new ArrayList<>();

        return users.stream().filter( u -> u.getName().startsWith( "A" ) )
                .map( User::getName )
                .collect( Collectors.toList() );
    }
}
