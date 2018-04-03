package com.stachera.justyna.cognifide;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import java.util.logging.Logger;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * <h1>WEB APPLICATION TESTS</h1>
 * Class is used to do basic test which check if application works properly.
 *
 * @author Justyna Stachera
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:properties/test-data.properties")
public class SBWebApplicationTests
{
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    private final static Logger LOGGER = Logger.getLogger(SBWebApplicationTests.class.getName());
    
    private final String contentType = "application/json;charset=UTF-8";
    
    private MockMvc mockMvc;
    
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Autowired
    private Environment env;
    
    /**
     * The method is used to prepare {@link MockMvc} object.
     */
    @Before
    public void prepare()
    {
        LOGGER.info("Spring Boot Test initialization...");
        
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    /**
     * Test checks if particular ISBN number exists.
     *
     * @throws Exception {@link MockMvc#perform(RequestBuilder)} can cause {@link Exception}.
     */
    @Test
    public void checkIsbnJsonAnswerTest() throws Exception
    {
        LOGGER.info("Spring Boot Test : checkIsbnJsonAnswerTest in progress...");
        
        final String requestUrl = "/api/book/9780080568782";
        
        mockMvc.perform(get(requestUrl))
               .andExpect(status().isOk())
               .andExpect(content().contentType(contentType))
               .andExpect(jsonPath("$.isbn").value(env.getProperty("isbn-01")))
               .andExpect(jsonPath("$.title").value(env.getProperty("title-01")))
               .andExpect(jsonPath("$.subtitle").value(env.getProperty("subtitle-01")))
               .andExpect(jsonPath("$.publisher").value(env.getProperty("publisher-01")))
               .andExpect(jsonPath("$.publishedDate").value(env.getProperty("publishedDate-01")))
               .andExpect(jsonPath("$.averageRating").value(env.getProperty("averageRating-01")))
               .andExpect(jsonPath("$.pageCount").value(env.getProperty("pageCount-01")))
               .andExpect(jsonPath("$.previewLink").value(env.getProperty("previewLink-01")))
               .andExpect(jsonPath("$.description").value(env.getProperty("description-01")))
               .andExpect(jsonPath("$.thumbnailUrl").value(env.getProperty("thumbnailUrl-01")))
               .andExpect(jsonPath("$.language").value(env.getProperty("language-01")))
               .andExpect(jsonPath("$.categories[0]").value(env.getProperty("categories[0]-01")))
               .andExpect(jsonPath("$.authors[0]").value(env.getProperty("authors[0]-01")))
               .andExpect(jsonPath("$.authors[1]").value(env.getProperty("authors[1]-01")));
    }
    
    /**
     * Test checks if ISBN number doesn't exist.
     *
     * @throws Exception {@link MockMvc#perform(RequestBuilder)} can cause {@link Exception}.
     */
    @Test
    public void checkIsbnNotFoundTest() throws Exception
    {
        LOGGER.info("Spring Boot Test : checkIsbnNotFoundTest in progress...");
        
        final String requestUrl = "/api/book/nonexisting";
        
        thrown.expect(NestedServletException.class);
        thrown.expectMessage(env.getProperty("servletNestedExceptionIsbn"));
        
        mockMvc.perform(get(requestUrl));
    }
    
    /**
     * Test checks if particular category name exists.
     *
     * @throws Exception {@link MockMvc#perform(RequestBuilder)} can cause {@link Exception}.
     */
    @Test
    public void checkCategoryJsonAnswerTest() throws Exception
    {
        LOGGER.info("Spring Boot Test : checkCategoryJsonAnswerTest in progress...");
        
        final String requestUrl = "/api/category/History/books";
        
        mockMvc.perform(get(requestUrl))
               .andExpect(status().isOk())
               .andExpect(content().contentType(contentType))
               .andExpect(jsonPath("$[0].isbn").value(env.getProperty("isbn-02")))
               .andExpect(jsonPath("$[0].title").value(env.getProperty("title-02")))
               .andExpect(jsonPath("$[0].subtitle").value(env.getProperty("subtitle-02")))
               .andExpect(jsonPath("$[0].publisher").value(env.getProperty("publisher-02")))
               .andExpect(jsonPath("$[0].publishedDate").value(env.getProperty("publishedDate-02")))
               .andExpect(jsonPath("$[0].averageRating").doesNotExist())
               .andExpect(jsonPath("$[0].pageCount").value(env.getProperty("pageCount-02")))
               .andExpect(jsonPath("$[0].previewLink").value(env.getProperty("previewLink-02")))
               .andExpect(jsonPath("$[0].description").value(env.getProperty("description-02")))
               .andExpect(jsonPath("$[0].thumbnailUrl").value(env.getProperty("thumbnailUrl-02")))
               .andExpect(jsonPath("$[0].language").value(env.getProperty("language-02")))
               .andExpect(jsonPath("$[0].categories[0]").value(env.getProperty("categories[0]-02")))
               .andExpect(jsonPath("$[0].authors[0]").value(env.getProperty("authors[0]-02")));
    }
    
    /**
     * Test checks if book list is sorted in descend order by average rating.
     *
     * @throws Exception {@link MockMvc#perform(RequestBuilder)} can cause {@link Exception}.
     */
    @Test
    public void checkRatingJsonAnswerTest() throws Exception
    {
        LOGGER.info("Spring Boot Test : checkRatingJsonAnswerTest in progress...");
        
        final String requestUrl = "/api/rating";
        
        mockMvc.perform(get(requestUrl))
               .andExpect(status().isOk())
               .andExpect(content().contentType(contentType))
               .andExpect(jsonPath("$[0].averageRating").value(5.0))
               .andExpect(jsonPath("$[0].authors[0]").value("Jain Pravin"))
               .andExpect(jsonPath("$[1].averageRating").value(5.0))
               .andExpect(jsonPath("$[1].authors[0]").value("BUYYA"))
               .andExpect(jsonPath("$[2].averageRating").value(4.5))
               .andExpect(jsonPath("$[2].authors[0]").value("Sir Thomas Stamford Raffles"))
               .andExpect(jsonPath("$[3].averageRating").value(4.5))
               .andExpect(jsonPath("$[3].authors[0]").value("Kathy Sierra"))
               .andExpect(jsonPath("$[3].authors[1]").value("Bert Bates"))
               .andExpect(jsonPath("$[4].averageRating").value(4.0))
               .andExpect(jsonPath("$[4].authors[0]").value("Clifford Geertz"))
               .andExpect(jsonPath("$[5].averageRating").value(4.0))
               .andExpect(jsonPath("$[5].authors[0]").value("Bruce Eckel"))
               .andExpect(jsonPath("$[6].averageRating").value(4.0))
               .andExpect(jsonPath("$[6].authors[0]").value("David Flanagan"))
               .andExpect(jsonPath("$[7].averageRating").value(4.0))
               .andExpect(jsonPath("$[7].authors[0]").value("Douglas Lea"))
               .andExpect(jsonPath("$[8].averageRating").value(4.0))
               .andExpect(jsonPath("$[8].authors[0]").value("Eric Burke"))
               .andExpect(jsonPath("$[9].averageRating").value(4.0))
               .andExpect(jsonPath("$[9].authors[0]").value("Kenneth L. Calvert"))
               .andExpect(jsonPath("$[9].authors[1]").value("Michael J. Donahoo"))
               .andExpect(jsonPath("$[10].averageRating").value(3.5))
               .andExpect(jsonPath("$[10].authors[0]").value("George Reese"))
               .andExpect(jsonPath("$[11].averageRating").value(3.5))
               .andExpect(jsonPath("$[11].authors[0]").value("Barry Burd"))
               .andExpect(jsonPath("$[12].averageRating").value(1.0))
               .andExpect(jsonPath("$[12].authors[0]").value("James William Bayley Money"));
    }
    
    @After
    public void quit()
    {
        LOGGER.info("Spring Boot Test done!");
    }
}