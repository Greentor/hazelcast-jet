/*
 * Copyright (c) 2008-2017, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.jet.stream;

import org.junit.Test;

import java.util.Map.Entry;

import static org.junit.Assert.assertEquals;

public class MinTest extends AbstractStreamTest {

    @Test
    public void sourceMap() throws Exception {
        IStreamMap<String, Integer> map = getMap();
        fillMap(map);

        int result = map.stream()
                        .map(Entry::getValue)
                        .min(Integer::compareTo)
                        .get();

        assertEquals(0, result);
    }

    @Test
    public void sourceList() throws Exception {
        IStreamList<Integer> list = getList();
        fillList(list);

        long result = list
                .stream()
                .min(Integer::compareTo)
                .get();

        assertEquals(0, result);
    }


}
