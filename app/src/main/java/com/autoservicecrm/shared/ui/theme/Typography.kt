package com.autoservicecrm.shared.ui.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

internal val DefaultTypography = CustomTypography(
    title = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
    ),
    h1 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
    ),
    h2 = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
    ),
    h3 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
    ),
    subtitle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    label = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
    ),
    body = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    caption = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    ),
    navigation = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    ),
)

@Stable
data class CustomTypography(
    val title: TextStyle,
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val subtitle: TextStyle,
    val label: TextStyle,
    val body: TextStyle,
    val caption: TextStyle,
    val navigation: TextStyle,
)

val TextStyle.regular
    get() = copy(fontStyle = FontStyle.Normal)

val TextStyle.medium
    get() = copy(fontWeight = FontWeight.Medium)

val TextStyle.semiBold
    get() = copy(fontWeight = FontWeight.Bold)

val TextStyle.bold
    get() = copy(fontWeight = FontWeight.Bold)

val TextStyle.strikethrough
    get() = copy(textDecoration = TextDecoration.LineThrough)

fun CustomTypography.toMaterialTypography() = Typography(
    h4 = title,
    h5 = h1,
    h6 = h2,
    subtitle1 = subtitle,
    subtitle2 = subtitle.regular,
    body1 = body,
    body2 = body,
    button = label.regular,
    caption = caption,
)