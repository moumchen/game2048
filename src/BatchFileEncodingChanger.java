import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BatchFileEncodingChanger {

    public static void main(String[] args) {
        String directoryPath = "/Users/chenyanqian/Documents/GitHub/game2048"; // 默认路径，可以修改或者通过参数传入
        Charset sourceCharset = Charset.forName("GB18030"); // 指定源编码为 GB18030
        Charset targetCharset = StandardCharsets.UTF_8; // 目标编码为 UTF-8

        if (args.length > 0) {
            directoryPath = args[0]; // 如果有命令行参数，使用第一个参数作为目录路径
        }

        File directory = new File(directoryPath);

        if (!directory.isDirectory()) {
            System.err.println("错误: 提供的路径不是一个有效的目录: " + directoryPath);
            return;
        }

        System.out.println("开始转换目录: " + directoryPath + " 中的 Java 文件编码从 " + sourceCharset.displayName() + " 到 " + targetCharset.displayName());
        changeFileEncoding(directory, sourceCharset, targetCharset);
        System.out.println("编码转换完成。");
    }

    public static void changeFileEncoding(File directory, Charset sourceCharset, Charset targetCharset) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    changeFileEncoding(file, sourceCharset, targetCharset);
                } else if (file.getName().endsWith(".java")) {
                    try {
                        convertFileEncoding(file, sourceCharset, targetCharset);
                        System.out.println("成功转换文件编码: " + file.getPath()); // 提示成功转换的文件
                    } catch (IOException e) {
                        System.err.println("Error changing encoding for file: " + file.getPath() + " - " + e.getMessage()); // 包含错误信息
                    }
                }
            }
        }
    }

    private static void convertFileEncoding(File file, Charset sourceCharset, Charset targetCharset) throws IOException {
        byte[] bytes = Files.readAllBytes(file.toPath());
        String content = new String(bytes, sourceCharset); // 使用指定的源编码读取
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), targetCharset))) {
            writer.write(content); // 使用 UTF-8 写入
        }
    }
}
