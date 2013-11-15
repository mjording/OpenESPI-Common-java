package org.energyos.espi.common.domain;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.energyos.espi.common.test.EspiFactory.newIntervalBlock;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;

public class IntervalBlockValidationTests {
    @Test
    public void isValid() throws Exception {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        IntervalBlock intervalBlock = newIntervalBlock();

        Set<ConstraintViolation<IntervalBlock>> violations = validator.validate(intervalBlock);

        assertThat(violations, is(empty()));
    }


}
