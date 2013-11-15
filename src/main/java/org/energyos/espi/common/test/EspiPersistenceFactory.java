package org.energyos.espi.common.test;

import org.energyos.espi.common.domain.*;
import org.energyos.espi.common.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class EspiPersistenceFactory {
    @Autowired
    private DataCustodianService dataCustodianService;
    @Autowired
    private ThirdPartyService thirdPartyService;
    @Autowired
    private RetailCustomerService retailCustomerService;
    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private UsagePointService usagePointService;

    public Authorization createAuthorization() {
        return createAuthorization(createRetailCustomer());
    }

    public Authorization createAuthorization(RetailCustomer retailCustomer) {
        DataCustodian dataCustodian = EspiFactory.newDataCustodian();
        dataCustodianService.persist(dataCustodian);
        Authorization authorization = EspiFactory.newAuthorization(retailCustomer, dataCustodian);
        authorizationService.persist(authorization);

        return authorization;
    }

    public Subscription createSubscription() {
        RetailCustomer retailCustomer = EspiFactory.newRetailCustomer();
        retailCustomerService.persist(retailCustomer);

        return createSubscription(retailCustomer);
    }

    public Subscription createSubscription(RetailCustomer retailCustomer) {
        ThirdParty thirdParty = EspiFactory.newThirdParty();
        thirdPartyService.persist(thirdParty);

        Subscription subscription = EspiFactory.newSubscription(retailCustomer, thirdParty);
        subscription.setLastUpdate(Calendar.getInstance());
        subscriptionService.persist(subscription);

        return subscription;
    }

    public RetailCustomer createRetailCustomer() {
        RetailCustomer retailCustomer = EspiFactory.newRetailCustomer();
        retailCustomerService.persist(retailCustomer);

        return retailCustomer;
    }

    public UsagePoint createUsagePoint() {
        UsagePoint usagePoint = EspiFactory.newUsagePoint(createRetailCustomer());
        usagePointService.persist(usagePoint);

        return usagePoint;
    }

    public ThirdParty createThirdParty() {
        ThirdParty thirdParty = EspiFactory.newThirdParty();
        thirdPartyService.persist(thirdParty);

        return thirdParty;
    }
}
