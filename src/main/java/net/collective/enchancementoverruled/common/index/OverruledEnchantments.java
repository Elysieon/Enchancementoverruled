package net.collective.enchancementoverruled.common.index;

import net.collective.enchancementoverruled.Enchancementoverruled;
import net.collectively.geode.registration.GeodeEnchantment;

public interface OverruledEnchantments {
    GeodeEnchantment WEAVING = Enchancementoverruled.geode.registerEnchantment("weaving");
    GeodeEnchantment STENOSIS = Enchancementoverruled.geode.registerEnchantment("stenosis");
    GeodeEnchantment SCURRY = Enchancementoverruled.geode.registerEnchantment("scurry");
    static void init() {
    }
}
