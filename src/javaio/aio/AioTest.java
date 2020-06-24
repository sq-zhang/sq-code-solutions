package javaio.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author sqzhang
 * @date 2020/6/23
 */
public class AioTest {
    static class ProfitCalculator {
        public ProfitCalculator() {
        }
        public static void calculateTax() {
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        String userPath = System.getProperty("user.dir");
        userPath += "/src/io/aio/";
        // read
        Path file = Paths.get(userPath + "a.txt");
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(file);

        ByteBuffer buffer = ByteBuffer.allocate(100_000);
        Future<Integer> result = channel.read(buffer, 0);

        while (!result.isDone()) {
            ProfitCalculator.calculateTax();
        }
        Integer bytesRead = result.get();
        System.out.println("Bytes read [" + bytesRead + "]");


        // write
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(
            Paths.get(userPath + "b.txt"), StandardOpenOption.READ,
            StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        CompletionHandler<Integer, Object> handler = new CompletionHandler<Integer, Object>() {

            @Override
            public void completed(Integer result, Object attachment) {
                System.out.println("Attachment: " + attachment + " " + result
                    + " bytes written");
                System.out.println("CompletionHandler Thread ID: "
                    + Thread.currentThread().getId());
            }

            @Override
            public void failed(Throwable e, Object attachment) {
                System.err.println("Attachment: " + attachment + " failed with:");
                e.printStackTrace();
            }
        };

        System.out.println("Main Thread ID: " + Thread.currentThread().getId());
        fileChannel.write(ByteBuffer.wrap("Sample".getBytes()), 0, "First Write",
            handler);
        fileChannel.write(ByteBuffer.wrap("Box".getBytes()), 0, "Second Write",
            handler);
    }
}
