package pl.tgargula;

import com.sun.javafx.application.PlatformImpl;

public class MainWrapper {
    public static void main(String[] args) {
        PlatformImpl.startup(() -> {});

        Main.main(args);
    }
}
