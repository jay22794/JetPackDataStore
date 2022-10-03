/*
* Copyright 2022
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

import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.jetpackdatastore.databinding.ActivityDataStoreBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * Activity represents preferences datastore
 */
class PreferencesDataStoreActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityDataStoreBinding

    // We can use dependency injection to inject the PreferencesDataStoreManager,
    // but we will keep it simple.
    lateinit var mPreferencesDataStoreManager : PreferencesDataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityDataStoreBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initViews()
        setListeners()
    }

    /**
     * Initialise the views
     */
    private fun initViews() {
        // Initialise PreferencesDataStoreManager
        mPreferencesDataStoreManager = PreferencesDataStoreManager(this)
        // Retrieve the saved language from data store
        lifecycleScope.launch {
            val userPreferredLanguage = mPreferencesDataStoreManager.appLanguage.first()
            if (userPreferredLanguage.isNotEmpty()) {
                // Check the radio button based on saved app language
                mBinding.rbEnglish.apply {
                    isChecked = text.toString() == userPreferredLanguage
                }
                mBinding.rbHindi.apply {
                    isChecked = text.toString() == userPreferredLanguage
                }
                mBinding.rbSpanish.apply {
                    isChecked = text.toString() == userPreferredLanguage
                }
            }
        }
    }

    /**
     * Set Listeners
     */
    private fun setListeners() {
        mBinding.rgLanguage.setOnCheckedChangeListener { radioGroup, checkedId ->
            val checkedRadioButton = findViewById<RadioButton>(checkedId)
            lifecycleScope.launch {
                mPreferencesDataStoreManager.saveAppLanguage(checkedRadioButton.text.toString())
            }
        }
    }
}
