/*
 * SPDX-FileCopyrightText: 2022-2024 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package lineagex.core.ext

import android.content.res.Resources.getSystem
import android.util.Range
import androidx.annotation.Dimension
import androidx.annotation.Px
import kotlin.math.roundToInt

@get:Px
val @receiver:Dimension(Dimension.DP) Int.toPx
    get() = (this * getSystem().displayMetrics.density).roundToInt()

@get:Dimension(Dimension.DP)
val @receiver:Px Int.toDp
    get() = (this / getSystem().displayMetrics.density).roundToInt()
