package com.example;

import com.couchbase.client.core.cnc.Event;
import com.couchbase.transactions.TransactionDurabilityLevel;
import com.couchbase.transactions.TransactionGetResult;
import com.couchbase.transactions.Transactions;
import com.couchbase.transactions.config.TransactionConfig;
import com.couchbase.transactions.config.TransactionConfigBuilder;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.core.mapping.CouchbaseDocument;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class Test {

    private final CouchbaseTemplate couchbaseTemplate;

    public Test(CouchbaseTemplate couchbaseTemplate) {
        this.couchbaseTemplate = couchbaseTemplate;
    }

    @EventListener
    public void run(ContextRefreshedEvent event) {
        TransactionConfig txConfig =  TransactionConfigBuilder.create().logDirectly(Event.Severity.INFO).logOnFailure(true, Event.Severity.ERROR)
                .expirationTime(Duration.ofSeconds(10)).durabilityLevel(TransactionDurabilityLevel.NONE).build();;
        Transactions transactions = Transactions.create(couchbaseTemplate.getCouchbaseClientFactory()
                .getCluster(), txConfig);

        transactions.run(ctx -> {
            TransactionGetResult getResult = ctx.get(couchbaseTemplate.getCouchbaseClientFactory().getDefaultCollection(), "doc-id_copy");

            CouchbaseDocument source = new CouchbaseDocument(getResult.id());
            source.setContent(getResult.contentAsObject());
            Menu m = couchbaseTemplate.getConverter().read(Menu.class, source);
            System.err.println(m);

            MenuCategory category = m.getCategories().get(0); // ClassCastException
            System.err.println(category);
        });

    }
}
