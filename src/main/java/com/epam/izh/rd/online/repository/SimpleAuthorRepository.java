package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = new Author[] {};

    @Override
    public boolean save(Author author) {
        Author[] saveAuthors;
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            saveAuthors = new Author[authors.length+1];
            System.arraycopy(authors, 0, saveAuthors, 0, authors.length);
            saveAuthors[saveAuthors.length-1] = author;
            authors = saveAuthors;
            return true;
        } else return false;
    }

    @Override
    public Author findByFullName(String name, String lastname){

        for (Author author : authors) {
                if (author.getName().equals(name) && author.getLastName().equals(lastname)) {
                return author;
                }
        }
        return null;
    }

    @Override
    public boolean remove (Author author){
        int value = 0;
        int i =0;
        for (Author author1 : authors) {
            if (author.getName().equals(author1.getName()) && author.getLastName().equals(author1.getLastName()))
                value++;
        }
        if (value > 0) {
          Author[] newAuthors = new Author[count()-value];
          for (Author author1 : authors ) {
              if (!author.getName().equals(author1.getName()) || !author.getLastName().equals(author1.getLastName())) {
                  newAuthors[i] = author1;
                  i++;
              }
          }
          authors = newAuthors;
          return true;
        } else return false;
    }

    @Override
    public int count() {
        return authors.length;
    }

}
