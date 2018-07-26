package org.lotus.carp.generator.core;

import org.junit.Test;

import java.io.OutputStreamWriter;
import java.io.Writer;

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
        Writer out = new OutputStreamWriter(System.out);
        generator.rendEntity(out);
    }

    @Test
    public void rendRepository2Console() {
        JpaGenerator generator = new JpaGenerator();
        Writer out = new OutputStreamWriter(System.out);
        generator.rendRepository(out);
    }
}