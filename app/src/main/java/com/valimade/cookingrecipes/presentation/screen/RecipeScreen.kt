package com.valimade.cookingrecipes.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.valimade.cookingrecipes.presentation.viewmodel.RecipeViewModel
import com.valimade.cookingrecipes.utils.ui.theme.MainBlueColor
import com.valimade.cookingrecipes.utils.ui.theme.MainPinkColor
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecipeScreen(
    recipeId: Int,
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    val viewModel: RecipeViewModel = koinViewModel()
    val recipeState by viewModel.recipeState.collectAsState()


    LaunchedEffect(recipeState.error) {
        if (recipeState.error != null) {
            Toast.makeText(context, recipeState.error, Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(recipeId) {
        viewModel.getRecipeById(recipeId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (recipeState.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(color = Color.Blue)
            }
        } else if (recipeState.error != null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(MainPinkColor),
                    onClick = onBack,
                    content = { Text(text = "Back", color = MainBlueColor) },
                )
            }
        } else {
            if (recipeState.recipe.image.isNotEmpty()) {
                AsyncImage(
                    model = recipeState.recipe.image,
                    contentDescription = recipeState.recipe.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            Text(
                text = recipeState.recipe.title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Ingredients",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                userScrollEnabled = false
            ) {
                items(recipeState.recipe.ingredients) { ingredient ->
                    Text(
                        text = "• $ingredient",
                        fontSize = 14.sp,
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Steps",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                userScrollEnabled = false
            ) {
                itemsIndexed(recipeState.recipe.steps) { index, step ->
                    Text(
                        text = "${index + 1}. $step",
                        fontSize = 14.sp,
                    )
                }
            }
        }
    }
}
