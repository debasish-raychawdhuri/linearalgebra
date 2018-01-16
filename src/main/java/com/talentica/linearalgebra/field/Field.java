/*
 * This file is a part of LinearAlgebra project.
 *
 * Copyright  2018  Talentica Software Pvt. Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.talentica.linearalgebra.field;

import com.talentica.linearalgebra.ring.Ring;

/**
 * Created by debasishc on 1/8/17.
 */
public interface Field<E> extends Ring<E>{
    E invert(E value);
    default E divide(E dividand, E divisor){
        return multiply(dividand, invert(divisor));
    }
    default E nthRoot(E value, int n){
        throw new UnsupportedOperationException(n+"th root is not supported");
    }
}
