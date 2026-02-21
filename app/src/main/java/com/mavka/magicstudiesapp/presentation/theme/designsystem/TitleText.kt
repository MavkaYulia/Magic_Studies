package com.mavka.magicstudiesapp.presentation.theme.designsystem

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.mavka.magicstudiesapp.presentation.theme.ui.Typography

@Composable
fun TitleText(title: String) {
    Text(text = title, style = Typography.titleLarge  )
}