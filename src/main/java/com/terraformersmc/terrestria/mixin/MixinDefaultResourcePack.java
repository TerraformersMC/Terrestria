package com.terraformersmc.terrestria.mixin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.minecraft.resource.DefaultResourcePack;
import net.minecraft.resource.DirectoryResourcePack;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

@Mixin(DefaultResourcePack.class)
public class MixinDefaultResourcePack {
	@Shadow
	private static Map<ResourceType, FileSystem> typeToFileSystem;

	@Inject(method = "findInputStream", at = @At(value = "INVOKE", target = "java/net/URL.openStream()Ljava/io/InputStream;"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
	private void fabric_fixResourcePollution(ResourceType type, Identifier id, CallbackInfoReturnable<InputStream> callback, String path) {
		System.out.println("findInputStream! " +type + " " + id);

		try {
			callback.setReturnValue(getRealInputStream(type, path));
		} catch (IOException var6) {
			callback.setReturnValue(DefaultResourcePack.class.getResourceAsStream(path));
		}
	}

	@Inject(method = "getInputStream", at = @At("HEAD"), cancellable = true)
	private void fabric_fixGetInputStream(String path, CallbackInfoReturnable<InputStream> callback) {
		System.out.println("getInputStream! " + path);

		FileSystem fs = typeToFileSystem.get(ResourceType.SERVER_DATA);

		if (fs == null) {
			return;
		}

		try {
			callback.setReturnValue(Files.newInputStream(fs.getPath(path)));
		} catch (IOException swallowed) {
			// fall through to vanilla behavior
		}
	}

	@Inject(method = "contains", at = @At(value = "INVOKE", target = "net/minecraft/resource/DefaultResourcePack.isValidUrl(Ljava/lang/String;Ljava/net/URL;)Z"), cancellable = true)
	private void fabric_fixContains(ResourceType type, Identifier id, CallbackInfoReturnable<Boolean> callback) {
		System.out.println("contains! " + type + " " + id);

		FileSystem fs = typeToFileSystem.get(type);

		if (fs == null) {
			return;
		}

		Path path = fs.getPath(type.getDirectory(), id.getNamespace(), id.getPath());
		callback.setReturnValue(Files.isRegularFile(path));
	}

	@Unique
	private static InputStream getRealInputStream(ResourceType type, String path) throws IOException {
		FileSystem fs = typeToFileSystem.get(type);

		if (fs != null) {
			// Apparently Minecraft couldn't find its own resources, they'll be an error in the log for this
			return Files.newInputStream(fs.getPath(path));
		} else {
			return DefaultResourcePack.class.getResourceAsStream(path);
		}
	}

	// copies
	@Unique
	private static String getPath(ResourceType type, Identifier id) {
		return "/" + type.getDirectory() + "/" + id.getNamespace() + "/" + id.getPath();
	}

	@Unique
	private static boolean isValidUrl(String fileName, URL url) throws IOException {
		return url != null && (url.getProtocol().equals("jar") || DirectoryResourcePack.isValidPath(new File(url.getFile()), fileName));
	}
}
