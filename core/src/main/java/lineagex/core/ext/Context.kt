/*
 * SPDX-FileCopyrightText: 2024 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package lineagex.core.ext

import android.content.Context
import android.content.res.Resources.Theme
import android.os.Build
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt

/**
 * @see Theme.resolveAttribute
 */
fun Context.resolveAttribute(@AttrRes attribute: Int) = TypedValue().also {
    theme.resolveAttribute(attribute, it, true)
}

/**
 * @see TypedValue.resourceId
 */
fun Context.getAttributeResourceId(@AttrRes attribute: Int) = resolveAttribute(attribute).resourceId

/**
 * @see TypedValue.isColorType
 * @see TypedValue.data
 */
@ColorInt
fun Context.getAttributeColor(@AttrRes attribute: Int) = resolveAttribute(attribute).let {
    require(
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            it.isColorType
        } else {
            it.type in TypedValue.TYPE_FIRST_COLOR_INT..TypedValue.TYPE_LAST_COLOR_INT
        }
    )

    it.data
}
