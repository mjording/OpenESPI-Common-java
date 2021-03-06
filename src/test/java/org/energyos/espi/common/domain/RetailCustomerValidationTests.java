/*
 * Copyright 2013 EnergyOS.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.energyos.espi.common.domain;

import org.hibernate.validator.constraints.NotEmpty;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import java.util.Set;

import static org.energyos.espi.common.support.TestUtils.assertAnnotationPresent;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RetailCustomerValidationTests {

    @Test
    public void retailCustomer_should_BeValid() throws Exception {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        RetailCustomer customer = new RetailCustomer();
        customer.setFirstName("First");
        customer.setLastName("Last");

        Set<ConstraintViolation<RetailCustomer>> violations = validator.validate(customer);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void isInvalid() throws Exception {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        RetailCustomer retailCustomer = new RetailCustomer();

        Set<ConstraintViolation<RetailCustomer>> violations = validator.validate(retailCustomer);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void firstName() {
        assertAnnotationPresent(RetailCustomer.class, "firstName", NotEmpty.class);
    }

    @Test
    public void lastName() {
        assertAnnotationPresent(RetailCustomer.class, "lastName", NotEmpty.class);
    }

    @Test
    public void enabled() {
        assertAnnotationPresent(RetailCustomer.class, "enabled", NotNull.class);
    }

    @Test
    public void role() {
        assertAnnotationPresent(RetailCustomer.class, "role", NotEmpty.class);
    }
}
