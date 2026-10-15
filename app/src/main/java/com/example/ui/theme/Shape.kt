package com.example.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp
import com.example.model.ExpressiveShapeStyle

fun getExpressiveShapes(style: ExpressiveShapeStyle): Shapes {
    return when (style) {
        ExpressiveShapeStyle.SUBTLE -> Shapes(
            extraSmall = RoundedCornerShape(4.dp),
            small = RoundedCornerShape(8.dp),
            medium = RoundedCornerShape(12.dp),
            large = RoundedCornerShape(16.dp),
            extraLarge = RoundedCornerShape(20.dp)
        )
        ExpressiveShapeStyle.STANDARD -> Shapes(
            extraSmall = RoundedCornerShape(8.dp),
            small = RoundedCornerShape(12.dp),
            medium = RoundedCornerShape(16.dp),
            large = RoundedCornerShape(24.dp),
            extraLarge = RoundedCornerShape(28.dp)
        )
        ExpressiveShapeStyle.EXPRESSIVE -> Shapes(
            extraSmall = RoundedCornerShape(12.dp),
            small = RoundedCornerShape(16.dp),
            medium = RoundedCornerShape(24.dp),
            large = RoundedCornerShape(32.dp),
            extraLarge = RoundedCornerShape(40.dp)
        )
    }
}
