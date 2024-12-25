package ui.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ui.api.enums.TabulatedFunctionFactoryType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettingsDto {
    private TabulatedFunctionFactoryType factoryType;
}