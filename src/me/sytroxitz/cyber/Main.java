package me.sytroxitz.cyber;

import me.sytroxitz.cyber.ui.LauncherFrame;
import me.sytroxitz.cyber.util.OSHelper;
import me.sytroxitz.cyber.util.UnzipUtility;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        new LauncherFrame();
        //launch();
    }

    public static void launch() {
        File minecraftDirectory = new File(OSHelper.getOS().getMc());
        File minecraftAssets = new File(minecraftDirectory.toString() + "assets");

        File natives = new File(System.getProperty("user.dir") + File.separator + "natives.zip");
        File libraries = new File(System.getProperty("user.dir") + File.separator + "libraries.zip");
        File jar = new File(System.getProperty("user.dir") + File.separator + "CYBER_Client.jar");

        try {
            FileUtils.copyURLToFile(new URL("https://github.com/Sytroxitz/cyber-launcher/raw/main/natives.zip"), natives);
            FileUtils.copyURLToFile(new URL("https://github.com/Sytroxitz/cyber-launcher/raw/main/libraries.zip"), libraries);
            FileUtils.copyURLToFile(new URL("https://github.com/Sytroxitz/cyber-launcher/raw/main/CYBER_Client.jar"), jar);
        } catch (IOException e) {
            e.printStackTrace();
        }

        UnzipUtility unzipper = new UnzipUtility();

        try {
            unzipper.unzip(natives.toString(), System.getProperty("user.dir") + File.separator + "natives");
            natives.delete();

            unzipper.unzip(libraries.toString(), System.getProperty("user.dir") + File.separator + "libraries");
            libraries.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Process process = Runtime.getRuntime().exec("java -"
                    + "Xms1024M "
                    + "-Xmx4096M "
                    + "-Djava.library.path=\"" + System.getProperty("user.dir") + File.separator + "natives" + "\" "
                    + "-cp \"" + System.getProperty("user.dir") + File.separator + "libraries" + File.separator + "*" + ";" + jar.toString() + "\" "
                    + "net.minecraft.client.main.Main "
                    + "--width 1670 "
                    + "--height 940 "
                    + "--username Sytroxitz "
                    + "--version 1.8.8 "
                    + "--gameDir " + minecraftDirectory.toString() + " "
                    + "--assetsDir " + minecraftAssets.toString() + " "
                    + "assetIndex 1.8.8 "
                    + "uuid N/A "
                    + "--accessToken aeef7bc935f9420eb6314dea7ad7e1e5 "
                    + "--userType mojang"
                    );
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String s = null;
            while((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }
            while((s = stdError.readLine()) != null) {
                System.out.println(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
