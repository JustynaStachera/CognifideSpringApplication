package com.stachera.justyna.cognifide.controllers;

import com.stachera.justyna.cognifide.dao.SBLibraryDao;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>LIBRARY REST CONTROLLER</h1>
 * Class is used to manage HTML requests which are related with JSON files. They contain book data.
 *
 * @author Justyna Stachera
 * @version 1.0
 */
@RestController
public class SBLibraryRestController
{
    private SBLibraryDao libraryDao;
    
    /**
     * An argument constructor autowired {@link SBLibraryDao} interface.
     *
     * @param libraryDao It contains method declarations to manage JSON file.
     */
    @Autowired
    public SBLibraryRestController(SBLibraryDao libraryDao)
    {
        this.libraryDao = libraryDao;
    }
    
    /**
     * The method displays all books on website in JSON format.
     *
     * @return This returns {@link JSONArray} which contains all books.
     */
    @GetMapping(value = "api/book/all", produces = "application/json;charset=UTF-8")
    public JSONArray booksGetAll()
    {
        JSONObject dataJsonObject = libraryDao.getJSONData();
        
        return libraryDao.getBookAll(dataJsonObject);
    }
    
    /**
     * The method is used to display the book by ISBN number on website page in JSON format.
     *
     * @param isbn ISBN number parameter.
     * @return This returns {@link JSONObject} which contains book data by ISBN number (if ISBN number exists).
     */
    @GetMapping(value = "api/book/{isbn}", produces = "application/json;charset=UTF-8")
    public JSONObject bookIsbnGet(@PathVariable("isbn") String isbn)
    {
        JSONObject dataJsonObject = libraryDao.getJSONData();
        
        return libraryDao.getBookByIsbn(dataJsonObject, isbn);
    }
    
    /**
     * The method is used to display the book list by category name on website page in JSON format.
     *
     * @param categoryName Category name parameter.
     * @return This returns {@link JSONArray} which contains book list by category name (if category name exists).
     */
    @GetMapping(value = "api/category/{categoryName}/books", produces = "application/json;charset=UTF-8")
    public JSONArray booksCategoryGet(@PathVariable("categoryName") String categoryName)
    {
        JSONObject dataJsonObject = libraryDao.getJSONData();
        
        return libraryDao.getBooksByCategory(dataJsonObject, categoryName);
    }
    
    /**
     * The method is used to display book list by average rating on website page in JSON format.
     *
     * @return This returns {@link JSONArray} which contains book list by average rating (if average rating exists).
     */
    @GetMapping(value = "api/rating", produces = "application/json;charset=UTF-8")
    public JSONArray booksRatingDesc()
    {
        JSONObject dataJsonObject = libraryDao.getJSONData();
        
        return libraryDao.getBookByRatingDesc(dataJsonObject);
    }
}