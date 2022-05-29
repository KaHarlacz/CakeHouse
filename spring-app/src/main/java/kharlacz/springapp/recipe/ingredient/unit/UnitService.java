package kharlacz.springapp.recipe.ingredient.unit;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UnitService {
    
    public Set<String> getUnitNames() {
        return Arrays.stream(Unit.values())
                .map(Unit::name).collect(Collectors.toSet());
    }
}
