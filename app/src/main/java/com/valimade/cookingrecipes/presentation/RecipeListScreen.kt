package com.valimade.cookingrecipes.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.valimade.cookingrecipes.presentation.components.RecipeCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecipeListScreen(
    modifier: Modifier
) {
    val context = LocalContext.current
    val viewModel: RecipeListViewModel = koinViewModel()
    val recipePreviewState by viewModel.recipePreviewState.collectAsState()

    LaunchedEffect(recipePreviewState.error) {
        if (recipePreviewState.error != null) {
            Toast.makeText(context, recipePreviewState.error, Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (recipePreviewState.isLoading) {
            CircularProgressIndicator(color = Color.Blue)
        } else {
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(recipePreviewState.recipeList) { recipe ->
                    RecipeCard(
                        recipe = recipe,
                        onClick = {},
                    )
                }
            }
        }
    }
}
