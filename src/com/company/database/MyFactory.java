package com.company.database;

import java.util.*;
import com.company.database.impl.*;
import com.company.database.OntologyJavaMapping;
import edu.stanford.smi.protege.model.*;
import edu.stanford.smi.protege.code.generator.wrapping.OntologyJavaMappingUtil;

/**
 * Generated by Protege (http://protege.stanford.edu).
 *
 * @version generated on Mon Apr 18 14:26:55 MSD 2016
 */
public class MyFactory {
    static { OntologyJavaMapping.initMap(); }

    private KnowledgeBase kb;

    public MyFactory(KnowledgeBase kb) {
        this.kb = kb;
    }


    // ***** Class Car *****

    public Cls getCarClass() {
        final String name = "Car";
        return kb.getCls(name);
    }

    public Car createCar(String name) {
        Cls cls = getCarClass();
        Instance inst = cls.createDirectInstance(name);
        return new DefaultCar(inst);
    }

    public Car getCar(String name) {
        return OntologyJavaMappingUtil.getSpecificObject(kb, kb.getInstance(name), Car.class);
    }

    public Collection<Car> getAllCarObjects() {
        return getAllCarObjects(false);
    }

    public Collection<Car> getAllCarObjects(boolean transitive) {
        Collection<Car> result = new ArrayList<Car>();
        final Cls cls = getCarClass();
        for (Object element : transitive ? cls.getInstances() : cls.getDirectInstances()) {
            Instance inst = (Instance) element;
            result.add(OntologyJavaMappingUtil.getSpecificObject(kb, inst, Car.class));
        }
        return result;
    }


    // ***** Class City *****

    public Cls getCityClass() {
        final String name = "City";
        return kb.getCls(name);
    }

    public City createCity(String name) {
        Cls cls = getCityClass();
        Instance inst = cls.createDirectInstance(name);
        return new DefaultCity(inst);
    }

    public City getCity(String name) {
        return OntologyJavaMappingUtil.getSpecificObject(kb, kb.getInstance(name), City.class);
    }

    public Collection<City> getAllCityObjects() {
        return getAllCityObjects(false);
    }

    public Collection<City> getAllCityObjects(boolean transitive) {
        Collection<City> result = new ArrayList<City>();
        final Cls cls = getCityClass();
        for (Object element : transitive ? cls.getInstances() : cls.getDirectInstances()) {
            Instance inst = (Instance) element;
            result.add(OntologyJavaMappingUtil.getSpecificObject(kb, inst, City.class));
        }
        return result;
    }


    // ***** Class DateDTP *****

    public Cls getDateDTPClass() {
        final String name = "DateDTP";
        return kb.getCls(name);
    }

    public DateDTP createDateDTP(String name) {
        Cls cls = getDateDTPClass();
        Instance inst = cls.createDirectInstance(name);
        return new DefaultDateDTP(inst);
    }

    public DateDTP getDateDTP(String name) {
        return OntologyJavaMappingUtil.getSpecificObject(kb, kb.getInstance(name), DateDTP.class);
    }

    public Collection<DateDTP> getAllDateDTPObjects() {
        return getAllDateDTPObjects(false);
    }

    public Collection<DateDTP> getAllDateDTPObjects(boolean transitive) {
        Collection<DateDTP> result = new ArrayList<DateDTP>();
        final Cls cls = getDateDTPClass();
        for (Object element : transitive ? cls.getInstances() : cls.getDirectInstances()) {
            Instance inst = (Instance) element;
            result.add(OntologyJavaMappingUtil.getSpecificObject(kb, inst, DateDTP.class));
        }
        return result;
    }


    // ***** Class Human *****

    public Cls getHumanClass() {
        final String name = "Human";
        return kb.getCls(name);
    }

    public Human createHuman(String name) {
        Cls cls = getHumanClass();
        Instance inst = cls.createDirectInstance(name);
        return new DefaultHuman(inst);
    }

    public Human getHuman(String name) {
        return OntologyJavaMappingUtil.getSpecificObject(kb, kb.getInstance(name), Human.class);
    }

    public Collection<Human> getAllHumanObjects() {
        return getAllHumanObjects(false);
    }

    public Collection<Human> getAllHumanObjects(boolean transitive) {
        Collection<Human> result = new ArrayList<Human>();
        final Cls cls = getHumanClass();
        for (Object element : transitive ? cls.getInstances() : cls.getDirectInstances()) {
            Instance inst = (Instance) element;
            result.add(OntologyJavaMappingUtil.getSpecificObject(kb, inst, Human.class));
        }
        return result;
    }


    // ***** Class Koeefis *****

    public Cls getKoeefisClass() {
        final String name = "Koeefis";
        return kb.getCls(name);
    }

    public Koeefis createKoeefis(String name) {
        Cls cls = getKoeefisClass();
        Instance inst = cls.createDirectInstance(name);
        return new DefaultKoeefis(inst);
    }

    public Koeefis getKoeefis(String name) {
        return OntologyJavaMappingUtil.getSpecificObject(kb, kb.getInstance(name), Koeefis.class);
    }

    public Collection<Koeefis> getAllKoeefisObjects() {
        return getAllKoeefisObjects(false);
    }

    public Collection<Koeefis> getAllKoeefisObjects(boolean transitive) {
        Collection<Koeefis> result = new ArrayList<Koeefis>();
        final Cls cls = getKoeefisClass();
        for (Object element : transitive ? cls.getInstances() : cls.getDirectInstances()) {
            Instance inst = (Instance) element;
            result.add(OntologyJavaMappingUtil.getSpecificObject(kb, inst, Koeefis.class));
        }
        return result;
    }


    // ***** Class KoefChanceDTP *****

    public Cls getKoefChanceDTPClass() {
        final String name = "KoefChanceDTP";
        return kb.getCls(name);
    }

    public KoefChanceDTP createKoefChanceDTP(String name) {
        Cls cls = getKoefChanceDTPClass();
        Instance inst = cls.createDirectInstance(name);
        return new DefaultKoefChanceDTP(inst);
    }

    public KoefChanceDTP getKoefChanceDTP(String name) {
        return OntologyJavaMappingUtil.getSpecificObject(kb, kb.getInstance(name), KoefChanceDTP.class);
    }

    public Collection<KoefChanceDTP> getAllKoefChanceDTPObjects() {
        return getAllKoefChanceDTPObjects(false);
    }

    public Collection<KoefChanceDTP> getAllKoefChanceDTPObjects(boolean transitive) {
        Collection<KoefChanceDTP> result = new ArrayList<KoefChanceDTP>();
        final Cls cls = getKoefChanceDTPClass();
        for (Object element : transitive ? cls.getInstances() : cls.getDirectInstances()) {
            Instance inst = (Instance) element;
            result.add(OntologyJavaMappingUtil.getSpecificObject(kb, inst, KoefChanceDTP.class));
        }
        return result;
    }


    // ***** Class KoefKBM *****

    public Cls getKoefKBMClass() {
        final String name = "KoefKBM";
        return kb.getCls(name);
    }

    public KoefKBM createKoefKBM(String name) {
        Cls cls = getKoefKBMClass();
        Instance inst = cls.createDirectInstance(name);
        return new DefaultKoefKBM(inst);
    }

    public KoefKBM getKoefKBM(String name) {
        return OntologyJavaMappingUtil.getSpecificObject(kb, kb.getInstance(name), KoefKBM.class);
    }

    public Collection<KoefKBM> getAllKoefKBMObjects() {
        return getAllKoefKBMObjects(false);
    }

    public Collection<KoefKBM> getAllKoefKBMObjects(boolean transitive) {
        Collection<KoefKBM> result = new ArrayList<KoefKBM>();
        final Cls cls = getKoefKBMClass();
        for (Object element : transitive ? cls.getInstances() : cls.getDirectInstances()) {
            Instance inst = (Instance) element;
            result.add(OntologyJavaMappingUtil.getSpecificObject(kb, inst, KoefKBM.class));
        }
        return result;
    }


    // ***** Class KoefKM *****

    public Cls getKoefKMClass() {
        final String name = "KoefKM";
        return kb.getCls(name);
    }

    public KoefKM createKoefKM(String name) {
        Cls cls = getKoefKMClass();
        Instance inst = cls.createDirectInstance(name);
        return new DefaultKoefKM(inst);
    }

    public KoefKM getKoefKM(String name) {
        return OntologyJavaMappingUtil.getSpecificObject(kb, kb.getInstance(name), KoefKM.class);
    }

    public Collection<KoefKM> getAllKoefKMObjects() {
        return getAllKoefKMObjects(false);
    }

    public Collection<KoefKM> getAllKoefKMObjects(boolean transitive) {
        Collection<KoefKM> result = new ArrayList<KoefKM>();
        final Cls cls = getKoefKMClass();
        for (Object element : transitive ? cls.getInstances() : cls.getDirectInstances()) {
            Instance inst = (Instance) element;
            result.add(OntologyJavaMappingUtil.getSpecificObject(kb, inst, KoefKM.class));
        }
        return result;
    }


    // ***** Class KoefKS *****

    public Cls getKoefKSClass() {
        final String name = "KoefKS";
        return kb.getCls(name);
    }

    public KoefKS createKoefKS(String name) {
        Cls cls = getKoefKSClass();
        Instance inst = cls.createDirectInstance(name);
        return new DefaultKoefKS(inst);
    }

    public KoefKS getKoefKS(String name) {
        return OntologyJavaMappingUtil.getSpecificObject(kb, kb.getInstance(name), KoefKS.class);
    }

    public Collection<KoefKS> getAllKoefKSObjects() {
        return getAllKoefKSObjects(false);
    }

    public Collection<KoefKS> getAllKoefKSObjects(boolean transitive) {
        Collection<KoefKS> result = new ArrayList<KoefKS>();
        final Cls cls = getKoefKSClass();
        for (Object element : transitive ? cls.getInstances() : cls.getDirectInstances()) {
            Instance inst = (Instance) element;
            result.add(OntologyJavaMappingUtil.getSpecificObject(kb, inst, KoefKS.class));
        }
        return result;
    }


    // ***** Class KoefKVS *****

    public Cls getKoefKVSClass() {
        final String name = "KoefKVS";
        return kb.getCls(name);
    }

    public KoefKVS createKoefKVS(String name) {
        Cls cls = getKoefKVSClass();
        Instance inst = cls.createDirectInstance(name);
        return new DefaultKoefKVS(inst);
    }

    public KoefKVS getKoefKVS(String name) {
        return OntologyJavaMappingUtil.getSpecificObject(kb, kb.getInstance(name), KoefKVS.class);
    }

    public Collection<KoefKVS> getAllKoefKVSObjects() {
        return getAllKoefKVSObjects(false);
    }

    public Collection<KoefKVS> getAllKoefKVSObjects(boolean transitive) {
        Collection<KoefKVS> result = new ArrayList<KoefKVS>();
        final Cls cls = getKoefKVSClass();
        for (Object element : transitive ? cls.getInstances() : cls.getDirectInstances()) {
            Instance inst = (Instance) element;
            result.add(OntologyJavaMappingUtil.getSpecificObject(kb, inst, KoefKVS.class));
        }
        return result;
    }


    // ***** Class StrahCompany *****

    public Cls getStrahCompanyClass() {
        final String name = "StrahCompany";
        return kb.getCls(name);
    }

    public StrahCompany createStrahCompany(String name) {
        Cls cls = getStrahCompanyClass();
        Instance inst = cls.createDirectInstance(name);
        return new DefaultStrahCompany(inst);
    }

    public StrahCompany getStrahCompany(String name) {
        return OntologyJavaMappingUtil.getSpecificObject(kb, kb.getInstance(name), StrahCompany.class);
    }

    public Collection<StrahCompany> getAllStrahCompanyObjects() {
        return getAllStrahCompanyObjects(false);
    }

    public Collection<StrahCompany> getAllStrahCompanyObjects(boolean transitive) {
        Collection<StrahCompany> result = new ArrayList<StrahCompany>();
        final Cls cls = getStrahCompanyClass();
        for (Object element : transitive ? cls.getInstances() : cls.getDirectInstances()) {
            Instance inst = (Instance) element;
            result.add(OntologyJavaMappingUtil.getSpecificObject(kb, inst, StrahCompany.class));
        }
        return result;
    }


    // ***** Getter methods for slots *****

    public Slot getKBM0Slot() {
        final String name = "KBM0";
        return kb.getSlot(name);
    }

    public Slot getKBM1Slot() {
        final String name = "KBM1";
        return kb.getSlot(name);
    }

    public Slot getKBM2Slot() {
        final String name = "KBM2";
        return kb.getSlot(name);
    }

    public Slot getKBM3Slot() {
        final String name = "KBM3";
        return kb.getSlot(name);
    }

    public Slot getKBM4Slot() {
        final String name = "KBM4";
        return kb.getSlot(name);
    }

    public Slot getKoeefSlot() {
        final String name = "Koeef";
        return kb.getSlot(name);
    }

    public Slot getAgeKoefSlot() {
        final String name = "ageKoef";
        return kb.getSlot(name);
    }

    public Slot getBasisStavkaSlot() {
        final String name = "basisStavka";
        return kb.getSlot(name);
    }

    public Slot getCityNameSlot() {
        final String name = "cityName";
        return kb.getSlot(name);
    }

    public Slot getCompanyNameSlot() {
        final String name = "companyName";
        return kb.getSlot(name);
    }

    public Slot getDTPSlot() {
        final String name = "dTP";
        return kb.getSlot(name);
    }

    public Slot getDateSlot() {
        final String name = "date";
        return kb.getSlot(name);
    }

    public Slot getHumanNameSlot() {
        final String name = "humanName";
        return kb.getSlot(name);
    }

    public Slot getKlassKBMSlot() {
        final String name = "klassKBM";
        return kb.getSlot(name);
    }

    public Slot getKlassKSSlot() {
        final String name = "klassKS";
        return kb.getSlot(name);
    }

    public Slot getMarkaSlot() {
        final String name = "marka";
        return kb.getSlot(name);
    }

    public Slot getModelSlot() {
        final String name = "model";
        return kb.getSlot(name);
    }

    public Slot getMyCitySlot() {
        final String name = "myCity";
        return kb.getSlot(name);
    }

    public Slot getMy_CarSlot() {
        final String name = "my_Car";
        return kb.getSlot(name);
    }

    public Slot getPowerSlot() {
        final String name = "power";
        return kb.getSlot(name);
    }

    public Slot getStajKoefSlot() {
        final String name = "stajKoef";
        return kb.getSlot(name);
    }

    public Slot getStajStartyearSlot() {
        final String name = "stajStartyear";
        return kb.getSlot(name);
    }

    public Slot getValueSlot() {
        final String name = "value";
        return kb.getSlot(name);
    }

    public Slot getYearBithdaySlot() {
        final String name = "yearBithday";
        return kb.getSlot(name);
    }
}
