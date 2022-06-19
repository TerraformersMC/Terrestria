package com.terraformersmc.terrestria.mixin;

import com.terraformersmc.terrestria.fix.BiomeIdFixData;
import net.fabricmc.fabric.impl.registry.sync.RegistryMapSerializer;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtIo;
import net.minecraft.world.SaveProperties;
import net.minecraft.world.level.storage.LevelStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

@Mixin(LevelStorage.Session.class)
public class MixinLevelStorageSession {
	@Shadow
	@Final
	Path directory;

	@Unique
	private static final Logger TERRESTRIA_LOGGER = LoggerFactory.getLogger("TerrestriaRegistrySyncFix");

	@Unique
	private boolean terrestria_readIdMapFile(File file) throws IOException {
		TERRESTRIA_LOGGER.debug("Reading registry data from " + file.toString());

		if (file.exists()) {
			FileInputStream fileInputStream = new FileInputStream(file);
			NbtCompound nbt = NbtIo.readCompressed(fileInputStream);
			fileInputStream.close();

			if (nbt != null) {
				BiomeIdFixData.applyFabricDynamicRegistryMap(RegistryMapSerializer.fromNbt(nbt));
				return true;
			}
		}

		return false;
	}

	@Inject(method = "readLevelProperties", at = @At("HEAD"))
	public void terrestria$readWorldProperties(CallbackInfoReturnable<SaveProperties> callbackInfo) {
		try {
			if (terrestria_readIdMapFile(new File(new File(directory.toFile(), "data"), "fabricDynamicRegistry.dat"))) {
				TERRESTRIA_LOGGER.info("[Terrestria - Registry Sync Fix] Loaded registry data");
			}
		} catch (FileNotFoundException e) {
			// Pass
		} catch (IOException e) {
			TERRESTRIA_LOGGER.warn("Reading registry file failed!", e);
		}
	}
}
