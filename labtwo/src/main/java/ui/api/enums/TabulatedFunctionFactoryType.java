package ui.api.enums;

import lombok.Getter;

@Getter
public enum TabulatedFunctionFactoryType {
    ARRAY_FACTORY("Массив"),
    LINKED_LIST_FACTORY("Связный список");

    private final String localizedName;

    TabulatedFunctionFactoryType(String localizedName) {
        this.localizedName = localizedName;
    }

}
