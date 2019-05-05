package com.github.btafarelo.autentia.prueba.courses;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LevelMapperTest {

    @Autowired
    private LevelMapper mapper;

    private static final Set<String> LEVELS = new HashSet<>();

    static {
        LEVELS.add("avanzado");
        LEVELS.add("básico");
        LEVELS.add("intermedio");
    }

    @Test
    public void checkNumberOfLevels() {
        assert(mapper.list().size() == 3);
    }

    @Test
    public void checkLevelsLabels() {
        for(LevelTO l : mapper.list())
            if (!LEVELS.contains(l.getValue()))
                assert (false);

        assert(true);
    }
}
