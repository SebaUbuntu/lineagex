/*
 * SPDX-FileCopyrightText: 2023 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package lineagex.core.ext

import android.content.ClipData

fun ClipData.asArray() = mutableListOf<ClipData.Item>().apply {
    for (i in 0 until itemCount) {
        this.add(getItemAt(i))
    }
}
