package ui.api.dto;

import lombok.*;
import ui.api.enums.TabulatedFunctionFactoryType;

@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettingsDto {
    private TabulatedFunctionFactoryType factoryType;


}
