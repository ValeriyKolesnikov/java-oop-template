package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[]{};

    @Override
    public boolean save (SchoolBook book) {
        SchoolBook [] saveBooks = new SchoolBook[schoolBooks.length+1];
        System.arraycopy(schoolBooks, 0, saveBooks, 0, schoolBooks.length);
            saveBooks [saveBooks.length-1] = book;
            schoolBooks = saveBooks;
            return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int lengthArray = 0;
        int j = 0;
        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook.getName().equals(name)) lengthArray++;
        }
        SchoolBook[] findBooks = new SchoolBook[lengthArray];
        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook.getName().equals(name)){
                findBooks[j]=schoolBook;
                j++;
            }
        }
        return findBooks;
    }

    @Override
    public boolean removeByName(String name) {
        int value = findByName(name).length;
        boolean flag = false;
        if (value != 0) {
            SchoolBook[] newSchoolBooks = new SchoolBook[count()-value];
            int i = 0;
            flag = true;
            for (SchoolBook schoolBook : schoolBooks) {
                if (!name.equals(schoolBook.getName())) {
                    newSchoolBooks[i] = schoolBook;
                    i++;
                }
            }
            schoolBooks = newSchoolBooks;
        }
        return flag;
    }

    @Override
    public int count() {
      return schoolBooks.length;
    }

}
