package com.example.test.model;

import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.util.Random;

public class NameGenerator {


    public String getRandomName() {
        return new Random().ints(5, 97, 122).collect(StringBuilder::new,
                StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
    }

}
