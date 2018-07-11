/*
 *    Copyright (C) 2018 Eton Otieno Oboch
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.edoubletech.moviemania.data.dao

import androidx.room.Delete
import androidx.room.Insert

/**
 * This is the base data access object with a type parameter with common methods to be shared by
 * different DAOs.
 *
 * @param T This is the type of data that will be modified or added.
 */
interface BaseDao<T> {

    @Insert
    fun insertData(data: T)

    @Delete
    fun deleteData(data: T)
}