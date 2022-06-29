package dev.omedia;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.Channels;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.nio.file.StandardOpenOption.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        String url = "https://s4107-16.imovies.cc/video/imovie_hash_code/21/2022062807353881_high_RUS.mp4?md5=O0udifJzI_LzU5Ms9omFPw&expires=1656575180&data=YTozOntzOjc6InVzZXJfaXAiO3M6MTE6IjQ2LjQ5LjYxLjEyIjtzOjEwOiJ1c2VyX2FnZW50IjtzOjEwMToiTW96aWxsYS81LjAgKFgxMTsgTGludXggeDg2XzY0KSBBcHBsZVdlYktpdC81MzcuMzYgKEtIVE1MLCBsaWtlIEdlY2tvKSBDaHJvbWUvMTAzLjAuMC4wIFNhZmFyaS81MzcuMzYiO3M6NzoicmVmZXJlciI7czoyMzoiaHR0cHM6Ly93d3cuaW1vdmllcy5jYy8iO30=?dw=1";
//        videoDownloader2(url, "filmi123.txt");
        String url1 = "https://s4114-07.imovies.cc/video/imovie_hash_code/7/2015012622470774_medium_rus.mp4?md5=ic_G8IOfD29OqGED__QV9Q&expires=1656582631&data=YTozOntzOjc6InVzZXJfaXAiO3M6MTE6IjQ2LjQ5LjYxLjEyIjtzOjEwOiJ1c2VyX2FnZW50IjtzOjEwMToiTW96aWxsYS81LjAgKFgxMTsgTGludXggeDg2XzY0KSBBcHBsZVdlYktpdC81MzcuMzYgKEtIVE1MLCBsaWtlIEdlY2tvKSBDaHJvbWUvMTAzLjAuMC4wIFNhZmFyaS81MzcuMzYiO3M6NzoicmVmZXJlciI7czoyMzoiaHR0cHM6Ly93d3cuaW1vdmllcy5jYy8iO30=?dw=1";
        long from = System.currentTimeMillis();
//        videoDownloader(url, "file1.mp4");
//        videoDownloader2(url, "file2.mp4");
        videoDownloader3(url1, "file.mp4");
        long to = System.currentTimeMillis();
        System.out.println(to - from);
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
            ExecutorService service = Executors.newFixedThreadPool(5);
            Future<Path> future1 = service.submit(() -> partVideoDownloader(url, 0, contentLen / 2));
            Future<Path> future2 = service.submit(() -> partVideoDownloader(url, contentLen / 2 + 1, contentLen));
            service.shutdown();


            mergeFiles(future1.get(), future2.get(), fileName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Path partVideoDownloader(String url, long start, long end) throws Exception {

        long from = System.currentTimeMillis();
        URL url1 = new URL(url);
        URLConnection connection = url1.openConnection();
        String format = String.format("bytes=%d-%d", start, end);
        connection.setRequestProperty("range", format);
        connection.connect();

        Path tempFile = Files.createTempFile("Bootcamp", ".tmp");
        try (InputStream inputStream = connection.getInputStream();
             AsynchronousFileChannel open = AsynchronousFileChannel.open(tempFile, CREATE, WRITE, TRUNCATE_EXISTING)
        ) {
            int bytesRead;
            long position = 0L;
            byte[] dataBuffer = new byte[1 << 16];
            while ((bytesRead = inputStream.read(dataBuffer)) != -1) {
                open.write(ByteBuffer.wrap(dataBuffer, 0, bytesRead), position).get();
                position += bytesRead;
            }
        }
        long to = System.currentTimeMillis();
        System.out.printf("%s - time = %d\n", format, to - from);

        return tempFile;
    }

    private static Path mergeFiles(String path1, String path2, String destination) throws Exception {
        return mergeFiles(Path.of(path1), Path.of(path2), destination);
    }

    private static Path mergeFiles(Path path1, Path path2, String destination) throws Exception {

        Path of = Path.of(destination);
        try (AsynchronousFileChannel open = AsynchronousFileChannel
                .open(of, CREATE, WRITE, TRUNCATE_EXISTING)) {
            byte[] array = Files.readAllBytes(path1);
            Future<Integer> write = open.write(ByteBuffer.wrap(array), 0);
            Future<Integer> write1 = open.write(ByteBuffer.wrap(Files.readAllBytes(path2)), array.length);
            write.get();
            write1.get();

        }

//        Files.write(of, Files.readAllBytes(path1), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
//        Files.write(of, Files.readAllBytes(path2), StandardOpenOption.APPEND);
        return of;
    }


    private static long getContentLength(String url) throws IOException {
        return new URL(url).openConnection().getContentLengthLong();
    }

}
