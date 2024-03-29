package me.eliq.dontkeepvanishingitems.mixin;

import me.eliq.dontkeepvanishingitems.DontKeepVanishingItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class DeathMixin {
    @Shadow
    protected void destroyVanishingCursedItems() {/*dummy body*/}

    @Shadow @Final private Inventory inventory;

    @Inject(at = @At("TAIL"), method = "dropEquipment")
    protected void destroyVanishingCursedItems(CallbackInfo ci) {
        var player = (Player) (Object) this;
        if (player.level.getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY)) {
            destroyVanishingCursedItems();
        }
    }


}
