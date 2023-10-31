package com.grapplesoft.meil_backend.enums;

import lombok.Getter;

/**
 * Actions
 * T101 - Allotment of the Project
 */
@Getter
public enum ActionTypeEnum {
    T101("T101"),
    T102("T102"),
    T103("T103"),
    T104("T104"),
    T105("T105"),
    T106("T106"),
    T107("T107"),
    T108("T108"),
    T109("T109"),
    T110("T110"),
    T111("T111"),
    T112("T112"),
    T113("T113"),
    T114("T114"),
    T115("T115"),
    T116("T116"),
    T117("T117"),
    T118("T118"),
    T119("T119");

    private final String value;

    ActionTypeEnum(String value) {
        this.value = value;
    }
}
