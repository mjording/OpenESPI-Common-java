/*
 * Copyright 2013, 2014 EnergyOS.org
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

package org.energyos.espi.common.repositories;

import org.energyos.espi.common.domain.Subscription;

import java.util.List;
import java.util.UUID;

public interface SubscriptionRepository {
    void persist(Subscription subscription);
    
    void merge(Subscription subscription);

    List<Subscription> findAll();

    Subscription findByHashedId(String hashedId);

	Subscription findById(Long subscriptionId);

	void deleteById(Long id);

	Subscription findByUUID(UUID uuid);
}
