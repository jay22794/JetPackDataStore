/*
* Copyright 2021 Test
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

package com.jetpackdatastore.prefrence_datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 *  Manages the preferences data storage
 *
 *  @param context  The instance of [Context]
 */
class PreferencesDataStoreManager(private val context: Context) {

    //Create a instance of Preferences DataStore
    private val Context.userPreferredLanguageDataStore: DataStore<Preferences> by preferencesDataStore(
        name = DATASTORE_NAME
    )

    /**
     *  Get the app language from Preferences DataStore
     */
    val appLanguage: Flow<String> = context.userPreferredLanguageDataStore.data
        .map { preferences ->
            preferences[LANGUAGE] ?: ""
        }

    /**
     *  Save the app language into Preferences DataStore
     */
    suspend fun saveAppLanguage(language: String) {
        context.userPreferredLanguageDataStore.edit { preferences ->
            preferences[LANGUAGE] = language
        }
    }

    companion object {
        //Preferences DataStore name
        const val DATASTORE_NAME = "app_language"
        //Keys to use for saving/retrieving the data from DataStore
        val LANGUAGE = stringPreferencesKey("language")
    }
}
