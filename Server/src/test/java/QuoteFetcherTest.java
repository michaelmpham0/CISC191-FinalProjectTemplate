import edu.sdccd.cisc191.template.QuoteFetcher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

public class QuoteFetcherTest {

    /**
     * Tests to see if method returns a string
     * containing the quote when the API call is successful.
     */
    @Test
    public void testAPICall() {

        QuoteFetcher quoteFetcher = new QuoteFetcher();

        String quote = quoteFetcher.fetchGameQuote();

        // Assert
        assertNotNull(quote);
        assertTrue(quote.startsWith("\""));
        assertTrue(quote.endsWith("\""));
    }
}
