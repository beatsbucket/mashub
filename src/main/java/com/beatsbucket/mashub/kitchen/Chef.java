/*
 * Copyright 2015 The Mashub Project
 *
 * The Mashub Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.beatsbucket.mashub.kitchen;

import java.util.concurrent.Future;

/**
 * Chef is a worker who observes an {@link Ingred} and cooks another {@link Ingred} by Recipe.
 */
public interface Chef<Q extends Queue, C extends Command> {
    Q setQueue(Q queue);
    C getCommand();
    C getAndDeleteCommand();
    Q insertCommand(C command);
    Future perform(C command);
    void addListener(Listener listener);
}