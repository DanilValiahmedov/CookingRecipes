package com.valimade.cookingrecipes.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun IconWithInformation(
    iconUrl: Int,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = iconUrl),
            contentDescription = "Icon",
            modifier = Modifier.size(26.dp),
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = text,
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
        )
    }
}
