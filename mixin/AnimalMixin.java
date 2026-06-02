package com.tfcgourmet.tfc_gourmet.mixin;

import com.tfcgourmet.tfc_gourmet.util.TFCAnimalAccessor;
import net.minecraft.world.entity.animal.Animal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Animal.class)
public class AnimalMixin implements TFCAnimalAccessor {
    
    @Unique
    @Override
    public Object tfcGourmet$getGender() {
        Animal self = (Animal) (Object) this;
        try {
            java.lang.reflect.Method getGenderMethod = self.getClass().getMethod("getGender");
            return getGenderMethod.invoke(self);
        } catch (Exception e) {
            return null;
        }
    }
    
    @Unique
    @Override
    public Float tfcGourmet$getFamiliarity() {
        Animal self = (Animal) (Object) this;
        try {
            java.lang.reflect.Method getFamiliarityMethod = self.getClass().getMethod("getFamiliarity");
            Object result = getFamiliarityMethod.invoke(self);
            if (result instanceof Number) {
                return ((Number) result).floatValue();
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
