package com.company.database;

import java.util.*;

/**
 * Generated by Protege (http://protege.stanford.edu).
 * Source Class: Human
 *
 * @version generated on Mon Apr 18 14:26:55 MSD 2016
 */
public interface Human {

    // Slot dTP

    Collection<DateDTP> getDTP();

    boolean hasDTP();

    void addDTP(DateDTP newDTP);

    void removeDTP(DateDTP oldDTP);

    void setDTP(Collection<? extends DateDTP> newDTP);


    // Slot humanName

    String getHumanName();

    boolean hasHumanName();

    void setHumanName(String newHumanName);


    // Slot myCity

    City getMyCity();

    boolean hasMyCity();

    void setMyCity(City newMyCity);


    // Slot my_Car

    Car getMy_Car();

    boolean hasMy_Car();

    void setMy_Car(Car newMy_Car);


    // Slot stajStartyear

    int getStajStartyear();

    boolean hasStajStartyear();

    void setStajStartyear(int newStajStartyear);


    // Slot yearBithday

    int getYearBithday();

    boolean hasYearBithday();

    void setYearBithday(int newYearBithday);

    void delete();
}
