package org.lotus.carp.generator.core;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 7/26/2018
 * Time: 10:56 AM
 */
public class JpaGeneratorTest {

    @Test
    public void rendEntity2Console() {
        JpaGenerator generator = new JpaGenerator();
        generator.rendEntity2Console();
    }

    @Test
    public void rendRepository2Console() {
        JpaGenerator generator = new JpaGenerator();
        generator.rendRepository2Console();
    }
}