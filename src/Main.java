import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {

        String folderPath = "C:\\Users\\Isaev\\Desktop\\TEST_PAGE";
        File file = new File(folderPath);

        long start = System.currentTimeMillis();

        FolderSizeCalculator calculator = new FolderSizeCalculator(file);
        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(calculator);
        System.out.println(getHumanReadableSize(size));

        long duration = (System.currentTimeMillis() - start);
        System.out.println(duration + " ms");



    }

    public static long getFolderSize(File folder) {
        if (folder.isFile()) {
            return folder.length();
        }
        long sum = 0;
        File[] files = folder.listFiles();
        for (File file : files) {
            sum += getFolderSize(file);
        }
        return sum;
    }

    //TODO: 24B, 234Kb, 36Mb, 34Gb, 42Tb
    public static String getHumanReadableSize(long size) {
        double sum;
        double number = size * 0.1;
        StringBuilder value = new StringBuilder();
        if (number > 0 && number <= 102.4) {
            value.append(size).append("B");
        } else if (number > 102.4 && number <= 104857.6) {
            sum = (size / Math.pow(2, 10));
            String result = String.format("%.3f", sum);
            value.append(result).append("Kb");
        } else if (number > 104857.6 && number <= 107374182.4) {
            sum = (size / Math.pow(2, 20));
            String result = String.format("%.3f", sum);
            value.append(result).append("Mb");
        } else if (number > 107374182.4 && number <= 109951162777.6) {
            sum = (size / Math.pow(2, 30));
            String result = String.format("%.3f", sum);
            value.append(result).append("Gb");
        } else if (number > 109951162777.6) {
            sum = (size / Math.pow(2, 40));
            String result = String.format("%.3f", sum);
            value.append(result).append("Tb");
        } else {
            value.append(size);
        }
        return value.toString();
    }

    //TODO: 24B, 234Kb, 36Mb, 34Gb, 42Tb
    //   24B, 234K, 36M, 34G, 42T
    //   235K => 235 * 1024 =240640
    public long getSizeFromHumanReadable(String size) {
        String[] strings = size.split("");
        for (String string : strings) {

        }
        return 0;
    }
}
