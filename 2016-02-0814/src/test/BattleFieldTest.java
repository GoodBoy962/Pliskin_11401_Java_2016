package test;


import impl.BattleField;
import impl.People;
import interfaces.Audience;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class BattleFieldTest {

    @BeforeClass
    public static void initObjects() {
        Audience people = mock(People.class);
    }

    @Test
    public void goodStartShouldBe() {
        Assert.assertEquals("Start", new BattleField().startBattle("start"));
    }


}
