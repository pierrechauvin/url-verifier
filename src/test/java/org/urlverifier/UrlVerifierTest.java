package org.urlverifier;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlVerifierTest {

    private final static Logger LOGGER = Logger.getLogger( UrlVerifierTest.class.getName() );

    @ParameterizedTest(name = "{index} => verify({0}) should return {1}")
    @CsvFileSource(resources = {"/urls.csv"}, delimiter = ';')
    public void urlTests( final String url, int expectedResult) throws IOException {
        LOGGER.log(Level.INFO, "Testing {0} with expected HTTP status code: {1}", new Object[]{ url, expectedResult });
        assertEquals(expectedResult, ( (HttpURLConnection) new URL( url ).openConnection()).getResponseCode() );
    }
}
