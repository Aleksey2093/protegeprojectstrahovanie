package com.company.database.impl;

import edu.stanford.smi.protege.code.generator.wrapping.AbstractWrappedInstance;
import edu.stanford.smi.protege.model.*;
import com.company.database.KoefKBM;
import com.company.database.*;

/**
 * Generated by Protege (http://protege.stanford.edu).
 * Source Class: KoefKBM
 *
 * @version generated on Mon Apr 18 14:26:55 MSD 2016
 */
public class DefaultKoefKBM extends DefaultKoeefis
         implements KoefKBM {

    public DefaultKoefKBM(Instance instance) {
        super(instance);
    }


    public DefaultKoefKBM() {
    }

    // Slot KBM0

    public int getKBM0() {
        java.lang.Integer value = (java.lang.Integer) getWrappedProtegeInstance().getOwnSlotValue(getKBM0Slot());
        return value == null ? null :
            (java.lang.Integer) value.intValue();
    }


    public Slot getKBM0Slot() {
        final String name = "KBM0";
        return getKnowledgeBase().getSlot(name);
    }


    public boolean hasKBM0() {
        return hasSlotValues(getKBM0Slot());
    }


    public void setKBM0(int newKBM0) {
        setSlotValue(getKBM0Slot(), new java.lang.Integer(newKBM0));
    }

    // Slot KBM1

    public int getKBM1() {
        java.lang.Integer value = (java.lang.Integer) getWrappedProtegeInstance().getOwnSlotValue(getKBM1Slot());
        return value == null ? null :
            (java.lang.Integer) value.intValue();
    }


    public Slot getKBM1Slot() {
        final String name = "KBM1";
        return getKnowledgeBase().getSlot(name);
    }


    public boolean hasKBM1() {
        return hasSlotValues(getKBM1Slot());
    }


    public void setKBM1(int newKBM1) {
        setSlotValue(getKBM1Slot(), new java.lang.Integer(newKBM1));
    }

    // Slot KBM2

    public int getKBM2() {
        java.lang.Integer value = (java.lang.Integer) getWrappedProtegeInstance().getOwnSlotValue(getKBM2Slot());
        return value == null ? null :
            (java.lang.Integer) value.intValue();
    }


    public Slot getKBM2Slot() {
        final String name = "KBM2";
        return getKnowledgeBase().getSlot(name);
    }


    public boolean hasKBM2() {
        return hasSlotValues(getKBM2Slot());
    }


    public void setKBM2(int newKBM2) {
        setSlotValue(getKBM2Slot(), new java.lang.Integer(newKBM2));
    }

    // Slot KBM3

    public int getKBM3() {
        java.lang.Integer value = (java.lang.Integer) getWrappedProtegeInstance().getOwnSlotValue(getKBM3Slot());
        return value == null ? null :
            (java.lang.Integer) value.intValue();
    }


    public Slot getKBM3Slot() {
        final String name = "KBM3";
        return getKnowledgeBase().getSlot(name);
    }


    public boolean hasKBM3() {
        return hasSlotValues(getKBM3Slot());
    }


    public void setKBM3(int newKBM3) {
        setSlotValue(getKBM3Slot(), new java.lang.Integer(newKBM3));
    }

    // Slot KBM4

    public int getKBM4() {
        java.lang.Integer value = (java.lang.Integer) getWrappedProtegeInstance().getOwnSlotValue(getKBM4Slot());
        return value == null ? null :
            (java.lang.Integer) value.intValue();
    }


    public Slot getKBM4Slot() {
        final String name = "KBM4";
        return getKnowledgeBase().getSlot(name);
    }


    public boolean hasKBM4() {
        return hasSlotValues(getKBM4Slot());
    }


    public void setKBM4(int newKBM4) {
        setSlotValue(getKBM4Slot(), new java.lang.Integer(newKBM4));
    }

    // Slot klassKBM

    public int getKlassKBM() {
        java.lang.Integer value = (java.lang.Integer) getWrappedProtegeInstance().getOwnSlotValue(getKlassKBMSlot());
        return value == null ? null :
            (java.lang.Integer) value.intValue();
    }


    public Slot getKlassKBMSlot() {
        final String name = "klassKBM";
        return getKnowledgeBase().getSlot(name);
    }


    public boolean hasKlassKBM() {
        return hasSlotValues(getKlassKBMSlot());
    }


    public void setKlassKBM(int newKlassKBM) {
        setSlotValue(getKlassKBMSlot(), new java.lang.Integer(newKlassKBM));
    }

    // Slot value

    public float getValue() {
        java.lang.Float value = (java.lang.Float) getWrappedProtegeInstance().getOwnSlotValue(getValueSlot());
        return value == null ? null :
            (java.lang.Float) value.floatValue();
    }


    public Slot getValueSlot() {
        final String name = "value";
        return getKnowledgeBase().getSlot(name);
    }


    public boolean hasValue() {
        return hasSlotValues(getValueSlot());
    }


    public void setValue(float newValue) {
        setSlotValue(getValueSlot(), new java.lang.Float(newValue));
    }
}
