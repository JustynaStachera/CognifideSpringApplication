package com.stachera.justyna.cognifide.controllers;

import com.stachera.justyna.cognifide.dao.SBLibraryDao;
import com.stachera.justyna.cognifide.domain.SBBook;
import com.stachera.justyna.cognifide.domain.SBField;
import com.stachera.justyna.cognifide.utils.SBUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <h1>LIBRARY PAGE CONTROLLER</h1>
 * Class is used to manage book data and display them by GUI. GUI was written thanks to Bootstrap.
 *
 * @author Justyna Stachera
 * @version 1.0
 */
@Controller
public class SBLibraryPageController
{
    private SBLibraryDao libraryDao;
    
    /**
     * An argument constructor autowired {@link SBLibraryDao} interface.
     *
     * @param libraryDao It contains method declarations to manage JSON file.
     */
    @Autowired
    public SBLibraryPageController(SBLibraryDao libraryDao)
    {
        this.libraryDao = libraryDao;
    }
    
    /**
     * The method is used to display books by chosen category.
     *
     * @param categoryName Category name.
     * @param model        It contains data to send on website page.
     * @return View model called 'index'.
     */
    @GetMapping("api/book/list/category")
    public String bookAllGet(@ModelAttribute("categoryName") SBField categoryName,
                             Model model)
    {
        JSONObject dataJsonObject = libraryDao.getJSONData();
        JSONArray bookArray = libraryDao.getBookAll(dataJsonObject);
        List<SBBook> bookList = SBUtils.toSBBookList(bookArray);
        List<String> categoryList = libraryDao.getBookCategories(dataJsonObject);
        
        if (categoryName.getField() != null)
        {
            bookList = bookList.stream().filter(sbBook -> {
                
                boolean isTrue = false;
                String[] categories = sbBook.getCategories();
                
                if (categories != null)
                {
                    for (String s : sbBook.getCategories())
                    {
                        if (s.equals(categoryName.getField()))
                        {
                            isTrue = true;
                        }
                    }
                }
                return isTrue;
            }).collect(Collectors.toList());
        }
        
        model.addAttribute("categories", categoryList);
        model.addAttribute("books", bookList);
        
        return "index";
    }
    
    /**
     * The method is used to find books by category.
     *
     * @param categoryName       Category name.
     * @param redirectAttributes It contains attributes which was redirected to {@link SBLibraryPageController}
     *                           method called 'bookAllGet'.
     * @return It redirects to 'api/book/list/category'.
     */
    @PostMapping("api/book/sort/category")
    public String bookSortGet(@ModelAttribute("categoryName") SBField categoryName,
                              RedirectAttributes redirectAttributes)
    {
        redirectAttributes.addFlashAttribute("categoryName", categoryName);
        
        return "redirect:/api/book/list/category";
    }
}