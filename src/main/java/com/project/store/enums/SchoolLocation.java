package com.project.store.enums;

import lombok.Getter;

@Getter
public enum SchoolLocation {
    ONE_GATE(0, "Gate 1"),
    THREE_GATE(1, "Gate 3"),
    FIVE_GATE(2, "Gate 5"),
    SIX_GATE(3, "Gate 6"),
    SEVEN_GATE(4, "Gate 7"),
    TEACHING_BUILDING_ONE(5, "Teaching Building 1"),
    LYNN_LIBRARY(6, "LYNN Library"),
    YIDAN_LIBRARY(7, "YIDAN Library"),
    NEXUS_LIBRARY(8, "NEXUX_Library");

    private int code;
    private String address;

    SchoolLocation(int code, String address) {
        this.code = code;
        this.address = address;
    }
}
