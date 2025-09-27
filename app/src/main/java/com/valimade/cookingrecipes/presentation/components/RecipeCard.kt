package com.valimade.cookingrecipes.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.valimade.cookingrecipes.R
import com.valimade.cookingrecipes.presentation.model.RecipeUI
import com.valimade.cookingrecipes.utils.ui.theme.MainBlueColor
import com.valimade.cookingrecipes.utils.ui.theme.MainPinkColor

@Composable
fun RecipeCard(
    recipe: RecipeUI,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            AsyncImage(
                model = recipe.image,
                contentDescription = recipe.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.error)
            )
            
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = recipe.title,
                    color = MainPinkColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )

                Text(
                    text = recipe.annotation,
                    color = MainBlueColor,
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconWithInformation(
                        iconUrl = R.drawable.ic_dish,
                        text = "${recipe.servings} Servings"
                    )
                    
                    IconWithInformation(
                        iconUrl = R.drawable.ic_watch,
                        text = "${recipe.readyInMinutes} Minutes"
                    )
                    
                    IconWithInformation(
                        iconUrl =R.drawable.ic_like,
                        text = "${recipe.aggregateLikes} Likes"
                    )
                }
            }
        }
    }
}

