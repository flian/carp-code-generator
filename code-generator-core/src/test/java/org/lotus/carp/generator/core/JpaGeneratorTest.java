package org.lotus.carp.generator.core;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 7/26/2018
 * Time: 10:56 AM
 */
public class JpaGeneratorTest {

    @Test
    public void rendEntityAndRepositoryTest() {
        JpaGenerator generator = new JpaGenerator();
        generator.rendEntityAndRepository(true);
    }
}