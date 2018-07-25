package org.lotus.carp.generator.base.config;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 7/25/2018
 * Time: 4:36 PM
 */
public class ConfigTest {

    @Test
    public void get() {
        String key = "test.should.be.overide";
        String shouldBe = "overide";
        assertEquals("password should equal", shouldBe, Config.get(key));
    }
}