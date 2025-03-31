package com.Not_an_UP.whatgugumod.util.handlers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.Not_an_UP.whatgugumod.util.UsefulFunc;

import net.minecraft.world.World;

public class WorldFileHandler {
	
	public static void generatePasswordFile(World world) {
        if (world.isRemote) return; // 只在服务端执行

        File worldDir = world.getSaveHandler().getWorldDirectory();
        File guguDir = new File(worldDir, "gugu");
        
        if (!guguDir.exists()) {
            guguDir.mkdirs();
        }

        File passwordFile = new File(guguDir, "guguPassword.txt");
        
        if (!passwordFile.exists()) {
            try (FileWriter writer = new FileWriter(passwordFile)) {
                String password = String.format("%08d", UsefulFunc.randint(100000000));
                writer.write(password);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static String readPassword(World world) {
        try {
            File worldDir = world.getSaveHandler().getWorldDirectory();
            return new String(Files.readAllBytes(Paths.get(worldDir.getPath(), "gugu", "guguPassword.txt")));
        } catch (IOException e) {
            return null;
        }
    }
}