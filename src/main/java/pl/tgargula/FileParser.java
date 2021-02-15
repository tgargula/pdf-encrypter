package pl.tgargula;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class FileParser {

    public static String getEncryptedFileName(File selectedFile) {
        List<String> stringList = new ArrayList<>(Arrays.asList(selectedFile.getName().split("\\.")));
        int index = stringList.size() - 2;
        stringList.set(index, stringList.get(index) + "-encrypted");
        return String.join(".", stringList);
    }

    public static String getOutputPath(File selectedFile, String outputFileName) {
        return selectedFile.getParent() + "/" + outputFileName;
    }

}
