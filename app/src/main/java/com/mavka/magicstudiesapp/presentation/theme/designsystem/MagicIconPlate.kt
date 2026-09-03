package com.mavka.magicstudiesapp.presentation.theme.designsystem

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.mavka.magicstudiesapp.R
import com.mavka.magicstudiesapp.presentation.theme.ui.MagicStudiesAppTheme

@Composable
fun MagicIconPlate(
    icon: Int,
    modifier: Modifier = Modifier,
    size: Int = R.dimen.icon_size_medium
) {
    Box(
        modifier = modifier
            .wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(dimensionResource(size)),
            tint = Color.Unspecified
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MagicIconPlatePreview() {
    MagicStudiesAppTheme {
        MagicIconPlate(
            R.drawable.img_magic_9
        )
    }
}



