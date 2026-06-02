package com.tfcgourmet.tfc_gourmet.util;

/**
 * Interface to access TFC animal traits without reflection.
 * TFC animals implement this via mixin.
 */
public interface TFCAnimalAccessor {
    /**
     * Gets the gender of the animal (MALE/FEMALE).
     * Returns null for non-TFC animals or if gender is not available.
     */
    Object tfcGourmet$getGender();
    
    /**
     * Gets the familiarity value (0.0 to 1.0).
     * Returns null for non-TFC animals or if familiarity is not available.
     */
    Float tfcGourmet$getFamiliarity();
}
