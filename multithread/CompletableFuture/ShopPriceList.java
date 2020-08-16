package multithread.CompletableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @author sqzhang
 * @year 2020
 */
public class ShopPriceList {
    static public class Shop {
        private Random random = new Random();
        private String name;
        public Shop(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public double getPrice(String product) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return random.nextDouble() * product.charAt(0) + product.charAt(1);
        }
    }

    public static List<Shop> shopList = Arrays.asList(
        new Shop("BestPrice"),
        new Shop("LetsSaveBig"),
        new Shop("MyFavoriteShop"),
        new Shop("BuyItAll")
    );

    private static List<String> findPriceSync(String product) {
        return shopList.stream()
            .map(shop -> String.format("%s price is %.2f",
                shop.getName(), shop.getPrice(product)))  //格式转换
            .collect(Collectors.toList());
    }

    private static List<String> findPriceAsync(String product) {
        List<CompletableFuture<String>> completableFutureList = shopList.stream()
            //转异步执行
            .map(shop -> CompletableFuture.supplyAsync(
                () -> String.format("%s price is %.2f",
                    shop.getName(), shop.getPrice(product))))  //格式转换
            .collect(Collectors.toList());

        return completableFutureList.stream()
            .map(CompletableFuture::join)  //获取结果不会抛出异常
            .collect(Collectors.toList());
    }

    private static List<String> findPriceFutureAsync(String product) {
        ExecutorService es = Executors.newCachedThreadPool();
        List<Future<String>> futureList = shopList.stream().map(shop -> es.submit(() ->
            String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))))
            .collect(Collectors.toList());
        return futureList.stream().map(f -> {
            String result = null;
            try {
                result = f.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return result;
        }).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        long t0 = System.currentTimeMillis();
        findPriceSync("product");
        long t1 = System.currentTimeMillis();
        System.out.println("Find Price Sync Done in " + (t1 - t0));
        findPriceAsync("product");
        long t2 = System.currentTimeMillis();
        System.out.println("Find Price ASync Done in " + (t2 - t1));
        findPriceFutureAsync("product");
        long t3 = System.currentTimeMillis();
        System.out.println("Find Price ASyncFuture Done in " + (t3 - t2));
    }
}
