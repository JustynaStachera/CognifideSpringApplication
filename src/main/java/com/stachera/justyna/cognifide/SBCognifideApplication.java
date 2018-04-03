package com.stachera.justyna.cognifide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <h1>COGNIFIDE SPRING BOOT APPLICATION</h1>
 * <h2>Application is used to manage JSON files which contains book data. Results of requests appear in Web Browser
 * .</h2>
 * <p>
 * Features:
 * <p>
 * - display all book list in JSON format;
 * <p>
 * - display book by ISBN number in JSON format;
 * <p>
 * - display book list by category name in JSON format;
 * <p>
 * - display book list by average rating in descend order.
 * <p>
 * An exemplary valid JSON file is available from: static/json/books.json
 * <p>
 * WARNING: Installation of JSON Formatter Plugin in Web Browser is recommended.
 *
 * @author Justyna Stachera
 * @version 1.0
 */
@SpringBootApplication
public class SBCognifideApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(SBCognifideApplication.class, args);
    }
}
