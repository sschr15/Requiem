package ladysnake.dissolution.client;

import ladysnake.dissolution.api.DissolutionPlayer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.WaterCreatureEntity;
import net.minecraft.util.Identifier;

import javax.annotation.Nullable;

public final class EntityShaders {

    @Nullable
    public static Identifier getShader(Entity camera) {
        if (camera instanceof DissolutionPlayer) {
            Entity possessed = (Entity) ((DissolutionPlayer)camera).getPossessionManager().getPossessedEntity();
            if (possessed != null) {
                MinecraftClient.getInstance().worldRenderer.onSetCameraEntity(possessed);
            }
        } else if (camera instanceof WaterCreatureEntity) {
            return ShaderHandler.FISH_EYE_SHADER_ID;
        }
        return null;
    }

}
