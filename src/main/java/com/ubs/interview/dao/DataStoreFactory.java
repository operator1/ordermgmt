package com.ubs.interview.dao;

import com.google.common.collect.Maps;
import com.ubs.interview.domain.Allocation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.ubs.interview.dao.HardcodedProductEnum.*;

@Component
public class DataStoreFactory {

    private static final DataStore INSTANCE = makeSingletonInstance();

    public DataStore getSingletonInstance() {
        return INSTANCE;
    }

    private static DataStore makeSingletonInstance() {

        final Map<String, Allocation> allocations = new HashMap<>();

        final Map<String, Integer> shinyNewProductUserAllocations = Maps.newHashMap();
        shinyNewProductUserAllocations.put("ubs", 100);
        final Allocation shinyNewProductAllocations = new Allocation(SHINY_NEW_PRODUCT.getProduct().getId(), shinyNewProductUserAllocations);
        allocations.put(SHINY_NEW_PRODUCT.getProduct().getId(), shinyNewProductAllocations);

        final Map<String, Integer> darkChocolateUserAllocations = Maps.newHashMap();
        darkChocolateUserAllocations.put("ubs", 45);
        final Allocation darkChocolateAllocations = new Allocation(DARK_CHOCOLATE.getProduct().getId(), darkChocolateUserAllocations);
        allocations.put(DARK_CHOCOLATE.getProduct().getId(), darkChocolateAllocations);

        final Map<String, Integer> goldUserAllocations = Maps.newHashMap();
        goldUserAllocations.put("ubs", 10);
        final Allocation goldAllocations = new Allocation(GOLD.getProduct().getId(), goldUserAllocations);
        allocations.put(GOLD.getProduct().getId(), goldAllocations);

        final Map<String, Integer> abbaRecordUserAllocations = Maps.newHashMap();
        abbaRecordUserAllocations.put("ubs", 243);
        abbaRecordUserAllocations.put("user", 2);
        final Allocation abbaRecordAllocations = new Allocation(ABBA_RECORD.getProduct().getId(), abbaRecordUserAllocations);
        allocations.put(ABBA_RECORD.getProduct().getId(), abbaRecordAllocations);

        return new DataStore(allocations);
    }
}
