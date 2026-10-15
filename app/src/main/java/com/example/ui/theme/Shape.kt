package com.example.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.model.ExpressiveShapeStyle

object MaterialCornerRadius {
    val None = 0.dp
    val ExtraSmall = 4.dp
    val Small = 8.dp
    val Medium = 12.dp
    val Large = 16.dp
    val LargeIncreased = 20.dp
    val ExtraLarge = 28.dp
    val ExtraLargeIncreased = 32.dp
    val ExtraExtraLarge = 48.dp
}

object MaterialComponentShapes {
    val Full: Shape = CircleShape
    val ExtraLargePill: Shape = RoundedCornerShape(MaterialCornerRadius.ExtraExtraLarge)
    val FloatingBarShape: Shape = RoundedCornerShape(MaterialCornerRadius.ExtraLargeIncreased)
    val HeroCardShape: Shape = RoundedCornerShape(MaterialCornerRadius.ExtraLargeIncreased)
    val ComponentCardShape: Shape = RoundedCornerShape(MaterialCornerRadius.LargeIncreased)
    val SheetShape: Shape = RoundedCornerShape(
        topStart = MaterialCornerRadius.ExtraLargeIncreased,
        topEnd = MaterialCornerRadius.ExtraLargeIncreased
    )
}

fun getExpressiveShapes(style: ExpressiveShapeStyle): Shapes {
    return when (style) {
        ExpressiveShapeStyle.SUBTLE -> Shapes(
            extraSmall = RoundedCornerShape(MaterialCornerRadius.ExtraSmall),
            small = RoundedCornerShape(MaterialCornerRadius.Small),
            medium = RoundedCornerShape(MaterialCornerRadius.Medium),
            large = RoundedCornerShape(MaterialCornerRadius.Large),
            extraLarge = RoundedCornerShape(MaterialCornerRadius.LargeIncreased)
        )
        ExpressiveShapeStyle.STANDARD -> Shapes(
            extraSmall = RoundedCornerShape(MaterialCornerRadius.Small),
            small = RoundedCornerShape(MaterialCornerRadius.Medium),
            medium = RoundedCornerShape(MaterialCornerRadius.Large),
            large = RoundedCornerShape(MaterialCornerRadius.ExtraLarge),
            extraLarge = RoundedCornerShape(MaterialCornerRadius.ExtraLargeIncreased)
        )
        ExpressiveShapeStyle.EXPRESSIVE -> Shapes(
            extraSmall = RoundedCornerShape(MaterialCornerRadius.Medium),
            small = RoundedCornerShape(MaterialCornerRadius.Large),
            medium = RoundedCornerShape(MaterialCornerRadius.LargeIncreased),
            large = RoundedCornerShape(MaterialCornerRadius.ExtraLargeIncreased),
            extraLarge = RoundedCornerShape(MaterialCornerRadius.ExtraExtraLarge)
        )
    }
}
