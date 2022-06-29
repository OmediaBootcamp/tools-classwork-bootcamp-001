package dev.omedia;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        String url = "https://s4107-16.imovies.cc/video/imovie_hash_code/21/2022062807353881_high_RUS.mp4?md5=O0udifJzI_LzU5Ms9omFPw&expires=1656575180&data=YTozOntzOjc6InVzZXJfaXAiO3M6MTE6IjQ2LjQ5LjYxLjEyIjtzOjEwOiJ1c2VyX2FnZW50IjtzOjEwMToiTW96aWxsYS81LjAgKFgxMTsgTGludXggeDg2XzY0KSBBcHBsZVdlYktpdC81MzcuMzYgKEtIVE1MLCBsaWtlIEdlY2tvKSBDaHJvbWUvMTAzLjAuMC4wIFNhZmFyaS81MzcuMzYiO3M6NzoicmVmZXJlciI7czoyMzoiaHR0cHM6Ly93d3cuaW1vdmllcy5jYy8iO30=?dw=1";
//        videoDownloader2(url, "filmi123.txt");
        String url1 = "https://s4114-07.imovies.cc/video/imovie_hash_code/7/2015012622470774_medium_rus.mp4?md5=ic_G8IOfD29OqGED__QV9Q&expires=1656582631&data=YTozOntzOjc6InVzZXJfaXAiO3M6MTE6IjQ2LjQ5LjYxLjEyIjtzOjEwOiJ1c2VyX2FnZW50IjtzOjEwMToiTW96aWxsYS81LjAgKFgxMTsgTGludXggeDg2XzY0KSBBcHBsZVdlYktpdC81MzcuMzYgKEtIVE1MLCBsaWtlIEdlY2tvKSBDaHJvbWUvMTAzLjAuMC4wIFNhZmFyaS81MzcuMzYiO3M6NzoicmVmZXJlciI7czoyMzoiaHR0cHM6Ly93d3cuaW1vdmllcy5jYy8iO30=?dw=1";
        long from = System.currentTimeMillis();
        videoDownloader(url, "file1.mp4");
        videoDownloader2(url, "file2.mp4");
        long to = System.currentTimeMillis();
        System.out.println(to-from);
    }


    public static void videoDownloader(String url, String fileName) {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            // handle exception
        }
    }

    public static void videoDownloader2(String url, String fileName) {
        try (InputStream in = new URL(url).openStream()) {
            Files.copy(in, Path.of(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void videoDownloader3(String url, String fileName) {
        try {
            long contentLen = getContentLength(url);
//            ExecutorService service = Executors.newFixedThreadPool(2);
            long from = System.currentTimeMillis();
            Runnable a1 = () -> {
                try {
                    partVideoDownloader(url, "a", 0, contentLen / 2);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            };
            Runnable b1 = () -> {
                try {
                    partVideoDownloader(url, "b", contentLen / 2 + 1, contentLen);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            };
            Thread th1 = new Thread(a1);
            Thread th2 = new Thread(b1);
            th1.run();
            th2.run();
//            th1.join();
//            th2.join();
//            service.shutdown();
            long to = System.currentTimeMillis();
            System.out.printf("download time:%d\n", to-from);
            mergeFiles("a","b",fileName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void partVideoDownloader(String url, String fileName, long start, long end) throws Exception{
        URL url1 = new URL(url);
        URLConnection connection = url1.openConnection();
        String format = String.format("bytes=%d-%d", start, end);
        connection.setRequestProperty("range", format);
        connection.connect();
        Files.copy(connection.getInputStream(), Path.of(fileName), StandardCopyOption.REPLACE_EXISTING);
        System.out.println(format);
    }

    private static void mergeFiles(String path1, String path2, String destination) throws Exception {
        Path of = Path.of(destination);
        Files.write(of, Files.readAllBytes(Path.of(path1)), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        Files.write(of, Files.readAllBytes(Path.of(path2)), StandardOpenOption.APPEND);
    }


    private static long getContentLength(String url) throws IOException {
        return new URL(url).openConnection().getContentLengthLong();
    }

}
