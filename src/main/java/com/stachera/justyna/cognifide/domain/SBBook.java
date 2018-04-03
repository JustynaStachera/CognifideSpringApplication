package com.stachera.justyna.cognifide.domain;

import com.stachera.justyna.cognifide.utils.SBUtils;
import org.json.simple.JSONObject;

import java.util.Arrays;
import java.util.Objects;

/**
 * <h1>BOOK CLASS</h1>
 * Class is used to mapping JSON 'item' object.
 *
 * @author Justyna Stachera
 * @version 1.0
 */
public class SBBook
{
    private String isbn;
    
    private String title;
    
    private String subtitle;
    
    private String publisher;
    
    private Long publishedDate;
    
    private String description;
    
    private Integer pageCount;
    
    private String thumbnailUrl;
    
    private String language;
    
    private String previewLink;
    
    private Double averageRating;
    
    private String[] authors;
    
    private String[] categories;
    
    public SBBook() {}
    
    public String getIsbn()
    {
        return isbn;
    }
    
    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    public String getSubtitle()
    {
        return subtitle;
    }
    
    public void setSubtitle(String subtitle)
    {
        this.subtitle = subtitle;
    }
    
    public String getPublisher()
    {
        return publisher;
    }
    
    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }
    
    public Long getPublishedDate()
    {
        return publishedDate;
    }
    
    public String getPublishedDateConv()
    {
        return publishedDate != null ? SBUtils.toStringDateFormat(publishedDate) : null;
    }
    
    public void setPublishedDate(Long publishedDate)
    {
        this.publishedDate = publishedDate;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public Integer getPageCount()
    {
        return pageCount;
    }
    
    public void setPageCount(Integer pageCount)
    {
        this.pageCount = pageCount;
    }
    
    public String getThumbnailUrl()
    {
        return thumbnailUrl;
    }
    
    public void setThumbnailUrl(String thumbnailUrl)
    {
        this.thumbnailUrl = thumbnailUrl;
    }
    
    public String getLanguage()
    {
        return language;
    }
    
    public void setLanguage(String language)
    {
        this.language = language;
    }
    
    public String getPreviewLink()
    {
        return previewLink;
    }
    
    public void setPreviewLink(String previewLink)
    {
        this.previewLink = previewLink;
    }
    
    public Double getAverageRating()
    {
        return averageRating;
    }
    
    public void setAverageRating(Double averageRating)
    {
        this.averageRating = averageRating;
    }
    
    public String[] getAuthors()
    {
        return authors;
    }
    
    public void setAuthors(String[] authors)
    {
        this.authors = authors;
    }
    
    public String[] getCategories()
    {
        return categories;
    }
    
    public void setCategories(String[] categories)
    {
        this.categories = categories;
    }
    
    /**
     * The method is used to convert {@link SBBook} to {@link JSONObject}. If some field equals NULL it is ignored
     * during mapping.
     *
     * @return {@link SBBook} converted to {@link JSONObject}.
     */
    public JSONObject toJsonObject()
    {
        JSONObject jsonObject = new JSONObject();
        
        if (isbn != null) { jsonObject.put("isbn", isbn); }
        if (title != null) { jsonObject.put("title", title); }
        if (subtitle != null) { jsonObject.put("subtitle", subtitle); }
        if (publisher != null) { jsonObject.put("publisher", publisher); }
        if (publishedDate != null) { jsonObject.put("publishedDate", publishedDate); }
        if (description != null) { jsonObject.put("description", description); }
        if (pageCount != null) { jsonObject.put("pageCount", pageCount); }
        if (thumbnailUrl != null) { jsonObject.put("thumbnailUrl", thumbnailUrl); }
        if (language != null) { jsonObject.put("language", language); }
        if (previewLink != null) { jsonObject.put("previewLink", previewLink); }
        if (averageRating != null) { jsonObject.put("averageRating", averageRating); }
        if (authors != null) { jsonObject.put("authors", authors); }
        if (categories != null) { jsonObject.put("categories", categories); }
        
        return jsonObject;
    }
    
    @Override
    public String toString()
    {
        return "SBBook{" +
               "isbn='" + isbn + '\'' +
               ", title='" + title + '\'' +
               ", subtitle='" + subtitle + '\'' +
               ", publisher='" + publisher + '\'' +
               ", publishedDate=" + publishedDate +
               ", description='" + description + '\'' +
               ", pageCount=" + pageCount +
               ", thumbnailUrl='" + thumbnailUrl + '\'' +
               ", language='" + language + '\'' +
               ", previewLink='" + previewLink + '\'' +
               ", averageRating=" + averageRating +
               ", authors=" + Arrays.toString(authors) +
               ", categories=" + Arrays.toString(categories) +
               '}';
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        SBBook book = (SBBook) o;
        return Objects.equals(isbn, book.isbn) &&
               Objects.equals(title, book.title) &&
               Objects.equals(subtitle, book.subtitle) &&
               Objects.equals(publisher, book.publisher) &&
               Objects.equals(publishedDate, book.publishedDate) &&
               Objects.equals(description, book.description) &&
               Objects.equals(pageCount, book.pageCount) &&
               Objects.equals(thumbnailUrl, book.thumbnailUrl) &&
               Objects.equals(language, book.language) &&
               Objects.equals(previewLink, book.previewLink) &&
               Objects.equals(averageRating, book.averageRating) &&
               Arrays.equals(authors, book.authors) &&
               Arrays.equals(categories, book.categories);
    }
    
    @Override
    public int hashCode()
    {
        
        int result = Objects.hash(isbn, title, subtitle, publisher, publishedDate, description, pageCount, thumbnailUrl,
                                  language, previewLink, averageRating);
        result = 31 * result + Arrays.hashCode(authors);
        result = 31 * result + Arrays.hashCode(categories);
        return result;
    }
}
