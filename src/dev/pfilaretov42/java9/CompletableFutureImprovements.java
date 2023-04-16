package dev.pfilaretov42.java9;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 */
public class CompletableFutureImprovements {

    public static void main(String[] args) throws Exception {
        //orTimeout();
        completeOnTimeout();
    }

    private static void orTimeout() throws Exception {
        CompletableFuture<String> future = new CompletableFuture<>();
        future
            .completeAsync(() ->
                Stream.generate(() -> UUID.randomUUID().toString())
                    .limit(1000000)
                    .collect(Collectors.joining("::")))
            .orTimeout(1, TimeUnit.SECONDS);

        String result = future.get();
        System.out.println("result=" + result);
    }

    private static void completeOnTimeout() throws Exception {
        CompletableFuture<String> future = new CompletableFuture<>();
        future
            .completeAsync(() ->
                Stream.generate(() -> UUID.randomUUID().toString())
                    .limit(1000000)
                    .collect(Collectors.joining("::")))
            .completeOnTimeout("Yuk", 1, TimeUnit.SECONDS);

        String result = future.get();
        System.out.println("result=" + result);
    }
}
