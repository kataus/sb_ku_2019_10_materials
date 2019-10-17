package ru.sbrf.ku.java.generics.bounds;

import ru.sbrf.ku.java.generics.bounds.entries.Animal;
import ru.sbrf.ku.java.generics.bounds.entries.Cat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sergey
 * created on 23.11.18.
 */
public class GenericsInheritance {

    public static void main(String[] args) {

        Animal cat = new Cat();

        List<Cat> catList = new ArrayList<>();
        //List<Animal> animalList = catList; //ошибка
    }
}
