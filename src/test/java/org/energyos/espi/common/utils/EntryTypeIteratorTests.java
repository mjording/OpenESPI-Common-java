package org.energyos.espi.common.utils;

import com.google.common.collect.Lists;
import org.energyos.espi.common.BaseTest;
import org.energyos.espi.common.domain.*;
import org.energyos.espi.common.models.atom.EntryType;
import org.energyos.espi.common.service.ResourceService;
import org.energyos.espi.common.test.EspiPersistenceFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("/spring/test-context.xml")
@Transactional
public class EntryTypeIteratorTests extends BaseTest {
    @Autowired
    private ResourceService resourceService;

    @Autowired
    private EspiPersistenceFactory espiPersistenceFactory;

    @Test
    public void next_returnsTheUsagePoint() {
        UsagePoint usagePoint = espiPersistenceFactory.createUsagePoint();

        EntryTypeIterator iterator = new EntryTypeIterator(resourceService, Lists.newArrayList(usagePoint.getId()), UsagePoint.class);

        assertThat(find(iterator, usagePoint), is(notNullValue()));
    }

    @Test
    public void next_returnsCorrectTimeConfiguration() {
        espiPersistenceFactory.createLocalTimeParameters();
        UsagePoint usagePoint = espiPersistenceFactory.createUsagePoint();
        TimeConfiguration localTimeParameters = usagePoint.getLocalTimeParameters();

        EntryTypeIterator iterator = new EntryTypeIterator(resourceService, Lists.newArrayList(usagePoint.getId()), UsagePoint.class);

        assertIteratorContains(iterator, localTimeParameters);
    }

    @Test
    public void next_doesntReturnWrongTimeConfiguration() {
        TimeConfiguration wrongTimeParameters = espiPersistenceFactory.createLocalTimeParameters();
        UsagePoint usagePoint = espiPersistenceFactory.createUsagePoint();
        TimeConfiguration localTimeParameters = usagePoint.getLocalTimeParameters();

        EntryTypeIterator iterator = new EntryTypeIterator(resourceService, Lists.newArrayList(usagePoint.getId()), UsagePoint.class);

        assertIteratorNotContains(iterator, wrongTimeParameters);
    }

    @Test
    public void next_givenUsagePointWithMeterReadings() {
        UsagePoint usagePoint = espiPersistenceFactory.createUsagePoint();
        MeterReading meterReading = usagePoint.getMeterReadings().get(0);

        EntryTypeIterator iterator = new EntryTypeIterator(resourceService, Lists.newArrayList(usagePoint.getId()), UsagePoint.class);

        assertThat(find(iterator, meterReading), is(notNullValue()));
    }

    @Test
    public void next_doesntReturnWrongMeterReading() {
        MeterReading wrongMeterReading = espiPersistenceFactory.createUsagePoint().getMeterReadings().get(0);
        UsagePoint usagePoint = espiPersistenceFactory.createUsagePoint();

        EntryTypeIterator iterator = new EntryTypeIterator(resourceService, Lists.newArrayList(usagePoint.getId()), UsagePoint.class);

        assertIteratorNotContains(iterator, wrongMeterReading);
    }

    @Test
    public void next_givenIntervalBlock() {
        UsagePoint usagePoint = espiPersistenceFactory.createUsagePoint();
        IntervalBlock intervalBlock = usagePoint.getMeterReadings().get(0).getIntervalBlocks().get(0);

        EntryTypeIterator iterator = new EntryTypeIterator(resourceService, Lists.newArrayList(usagePoint.getId()), UsagePoint.class);

        assertThat(find(iterator, intervalBlock), is(notNullValue()));
    }

    @Test
    public void next_givenElectricPowerUsageSummary() {
        UsagePoint usagePoint = espiPersistenceFactory.createUsagePoint();
        ElectricPowerUsageSummary electricPowerUsageSummary = usagePoint.getElectricPowerUsageSummaries().get(0);

        EntryTypeIterator iterator = new EntryTypeIterator(resourceService, Lists.newArrayList(usagePoint.getId()), UsagePoint.class);

        assertThat(find(iterator, electricPowerUsageSummary), is(notNullValue()));
    }

    @Test
    public void next_givenElectricPowerQualitySummary() {
        UsagePoint usagePoint = espiPersistenceFactory.createUsagePoint();
        ElectricPowerQualitySummary electricPowerQualitySummary = usagePoint.getElectricPowerQualitySummaries().get(0);

        EntryTypeIterator iterator = new EntryTypeIterator(resourceService, Lists.newArrayList(usagePoint.getId()), UsagePoint.class);

        assertThat(find(iterator, electricPowerQualitySummary), is(notNullValue()));
    }

    @Test
    public void next_givenReadingType() {
        UsagePoint usagePoint = espiPersistenceFactory.createUsagePoint();
        ReadingType readingType = usagePoint.getMeterReadings().get(0).getReadingType();

        EntryTypeIterator iterator = new EntryTypeIterator(resourceService, Lists.newArrayList(usagePoint.getId()), UsagePoint.class);

        assertThat(find(iterator, readingType), is(notNullValue()));
    }

    @Test
    public void entry_hasLinks() throws Exception {
        UsagePoint usagePoint = espiPersistenceFactory.createUsagePoint();

        EntryTypeIterator iterator = new EntryTypeIterator(resourceService, Lists.newArrayList(usagePoint.getId()), UsagePoint.class);

        UsagePoint usagePointFromEntry = find(iterator, usagePoint).getContent().getUsagePoint();
        assertThat(usagePointFromEntry.getSelfLink().getHref(), is(not(nullValue())));
    }

    private EntryType find(EntryTypeIterator iterator, IdentifiedObject matchedObject) {
        while(iterator.hasNext()) {
            EntryType entry = iterator.next();
            if (entry.getContent().getResources().get(0).equals(matchedObject))
                return entry;
        }
        return null;
    }

    private void assertIteratorContains(EntryTypeIterator iterator, IdentifiedObject matchedObject) {
        List<IdentifiedObject> entries = new ArrayList<>();

        while(iterator.hasNext()) {
            entries.add(iterator.next().getContent().getResource());
        }

        assertThat(entries, hasItem(matchedObject));
    }

    private void assertIteratorNotContains(EntryTypeIterator iterator, IdentifiedObject matchedObject) {
        List<IdentifiedObject> entries = new ArrayList<>();

        while(iterator.hasNext()) {
            entries.add(iterator.next().getContent().getResource());
        }

        assertThat(entries, not(hasItem(matchedObject)));
    }
}
