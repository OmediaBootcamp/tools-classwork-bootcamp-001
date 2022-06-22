package dev.omedia.creational.factory.method;

import dev.omedia.creational.factory.method.exception.FactoryMethodException;
import junit.framework.TestCase;
import org.junit.Test;

public class SVReaderFactoryTest extends TestCase {
    @Test
    public void testCreateCSV() throws FactoryMethodException {
        SVReader svReader = SVReaderFactory.create("a.csv");
        assertTrue(svReader instanceof CSVReader);
    }

    @Test
    public void testCreateTSV() throws FactoryMethodException {
        SVReader svReader = SVReaderFactory.create("a.tsv");
        assertTrue(svReader instanceof TSVReader);
    }
}