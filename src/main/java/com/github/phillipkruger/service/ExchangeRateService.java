package com.github.phillipkruger.service;

import com.github.phillipkruger.model.CurencyCode;
import com.github.phillipkruger.model.ExchangeRate;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

/**
 * Service that return the exchange rate against a certain base
 *
 * @author Phillip Kruger (phillip.kruger@redhat.com)
 */
@ApplicationScoped
public class ExchangeRateService {
    private static final Logger LOG = Logger.getLogger(ExchangeRateService.class);
    
    @Inject
    Vertx vertx;

    @Inject @ConfigProperty(name = "exchangerate.api.key")
    String apiKey;
    
    public ExchangeRate getExchangeRate(CurencyCode base, CurencyCode forCurencyCode) {
        try {
            CompletableFuture<ExchangeRate> futureExchangeRates = getFutureExchangeRate(base, forCurencyCode);
            ExchangeRate exchangeRate = futureExchangeRates.get();
            return exchangeRate;
        } catch (InterruptedException | ExecutionException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public CompletableFuture<ExchangeRate> getFutureExchangeRate(CurencyCode base, CurencyCode forCurencyCode) {
        LOG.info(">>> Getting exchange rate for " + base);
        Future<ExchangeRate> future = Future.future((f) -> {
            WebClient client = WebClient.create(vertx);
            client.get(80, "www.alphavantage.co", "/query")
                .addQueryParam("function", "CURRENCY_EXCHANGE_RATE")
                .addQueryParam("from_currency", base.toString())
                .addQueryParam("to_currency", "ZAR")
                .addQueryParam("apikey", apiKey)
                .send((var e) -> {
                    if(e.succeeded()){
                        HttpResponse<Buffer> response = e.result();
                        JsonObject jsonResponse = response.bodyAsJsonObject();
                        
                        String rate = jsonResponse.getJsonObject("Realtime Currency Exchange Rate").getString("5. Exchange Rate");
                        
                        ExchangeRate exchangeRate = new ExchangeRate(); 
                        exchangeRate.base = base;
                        exchangeRate.against = forCurencyCode;
                        exchangeRate.rate = Double.valueOf(rate);
                        LOG.info("<<< Got exchange rate for " + base);
                        f.complete(exchangeRate);
                    }else if(e.failed()){
                        f.fail(e.cause());
                    }
                });
        });
        return future.toCompletionStage().toCompletableFuture();    
    }


}
