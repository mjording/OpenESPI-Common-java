package org.energyos.espi.common.domain;

import org.energyos.espi.common.atom.XMLTest;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.energyos.espi.common.test.EspiFactory.newServiceCategory;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ServiceCategoryValidationTests extends XMLTest {
    @Test
    public void isValid() throws Exception {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        ServiceCategory serviceCategory = newServiceCategory();

        Set<ConstraintViolation<ServiceCategory>> violations = validator.validate(serviceCategory);

        assertThat(violations, is(empty()));
    }

    @Test
    public void isInvalid() throws Exception {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        ServiceCategory serviceCategory = new ServiceCategory();

        Set<ConstraintViolation<ServiceCategory>> violations = validator.validate(serviceCategory);

        assertThat(violations, is(not(empty())));
    }
}
