/*
 * Copyright (c) 2008-2018, Hazelcast, Inc. All Rights Reserved.
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

package com.hazelcast.jet.stream.impl.processor;

import com.hazelcast.jet.core.AbstractProcessor;

import javax.annotation.Nonnull;
import java.util.function.BiConsumer;

public class CollectorCombineP<T> extends AbstractProcessor {

    private final BiConsumer<T, T> combiner;
    private T result;

    public CollectorCombineP(BiConsumer<T, T> combiner) {
        this.combiner = combiner;
    }

    @Override
    protected boolean tryProcess(int ordinal, @Nonnull Object item) throws Exception {
        if (result != null) {
            combiner.accept(result, (T) item);
        } else {
            result = (T) item;
        }
        return true;
    }

    @Override
    public boolean complete() {
        return result == null || tryEmit(result);
    }
}
