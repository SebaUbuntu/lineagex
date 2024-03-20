/*
 * SPDX-FileCopyrightText: 2022-2024 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package lineagex.settingslib

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.Px
import androidx.annotation.XmlRes
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.preference.PreferenceFragmentCompat
import com.google.android.material.appbar.AppBarLayout
import lineagex.ui.ext.setOffset
import kotlin.reflect.safeCast

abstract class SettingsFragment(
    @XmlRes private val preferencesResId: Int,
) : PreferenceFragmentCompat() {
    private val settingsActivity
        get() = SettingsActivity::class.safeCast(activity)

    @Px
    private var appBarOffset = -1

    private val offsetChangedListener = AppBarLayout.OnOffsetChangedListener { _, i ->
        appBarOffset = -i
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingsActivity?.let { settingsActivity ->
            val appBarLayout = settingsActivity.appBarLayout

            if (appBarOffset != -1) {
                appBarLayout.setOffset(appBarOffset, settingsActivity.coordinatorLayout)
            } else {
                appBarLayout.setExpanded(true, false)
            }

            appBarLayout.setLiftOnScrollTargetView(listView)

            appBarLayout.addOnOffsetChangedListener(offsetChangedListener)
        }
    }

    override fun onDestroyView() {
        settingsActivity?.appBarLayout?.apply {
            removeOnOffsetChangedListener(offsetChangedListener)

            setLiftOnScrollTargetView(null)
        }

        super.onDestroyView()
    }

    @CallSuper
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(preferencesResId, rootKey)
    }

    @CallSuper
    override fun onCreateRecyclerView(
        inflater: LayoutInflater,
        parent: ViewGroup,
        savedInstanceState: Bundle?
    ) = super.onCreateRecyclerView(inflater, parent, savedInstanceState).apply {
        clipToPadding = false
        isVerticalScrollBarEnabled = false

        ViewCompat.setOnApplyWindowInsetsListener(this) { _, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())

            updatePadding(
                bottom = insets.bottom,
                left = insets.left,
                right = insets.right,
            )

            windowInsets
        }
    }
}
