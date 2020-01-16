package com.example.newsapp;

public class DatabaseHelper  {

   private String Title;
   private String Description;
   private String Author;
   private String URL;
   private String URL_Image;
   private String Date;

   public DatabaseHelper() {}

   public DatabaseHelper(String title, String description, String author, String URL, String URL_Image, String date) {
      this.Title = title;
      this.Description = description;
      this.Author = author;
      this.URL = URL;
      this.URL_Image = URL_Image;
      this.Date = date;
   }

   public String getTitle() {
      return Title;
   }

   public void setTitle(String title) {
      Title = title;
   }

   public String getDescription() {
      return Description;
   }

   public void setDescription(String description) {
      Description = description;
   }

   public String getAuthor() {
      return Author;
   }

   public void setAuthor(String author) {
      Author = author;
   }

   public String getURL() {
      return URL;
   }

   public void setURL(String URL) {
      this.URL = URL;
   }

   public String getURL_Image() {
      return URL_Image;
   }

   public void setURL_Image(String URL_Image) {
      this.URL_Image = URL_Image;
   }

   public String getDate() {
      return Date;
   }

   public void setDate(String date) {
      Date = date;
   }
}
