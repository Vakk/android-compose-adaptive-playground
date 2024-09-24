package com.valeriik.adaptivedesign.ui.responsive

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.valeriik.adaptivedesign.data.model.Recipe
import com.valeriik.adaptivedesign.ui.Navigation
import com.valeriik.adaptivedesign.ui.components.AppAdaptiveComponent
import com.valeriik.adaptivedesign.ui.viewmodel.RecipesListViewModel

@Composable
fun RecipesList(
    modifier: Modifier = Modifier,
    viewModel: RecipesListViewModel = viewModel(),
    navController: NavController
) {
    val recipes by viewModel.recipesList.collectAsState()

    Scaffold(modifier = modifier) { paddingValues ->
        AppAdaptiveComponent(mobile = {
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(recipes) { item ->
                    com.valeriik.adaptivedesign.ui.adaptive.RecipeItem(item, onClick = {
                        navController.navigate(
                            Navigation.RecipeDetails(
                                id = item.id.toString(),
                                title = item.name,
                            )
                        )
                    })
                }
            }
        }, tablet =  {
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(recipes) { item ->
                    RecipeItem(item, onClick = {
                        navController.navigate(
                            Navigation.RecipeDetails(
                                id = item.id.toString(),
                                title = item.name,
                            )
                        )
                    })
                }
            }
        })
    }
}

@Composable
fun RecipeItem(
    recipe: Recipe,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val context = LocalContext.current

    Card(onClick = onClick) {
        Box(modifier = modifier) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                model = ImageRequest.Builder(context)
                    .data(recipe.image)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .build(),
                contentDescription = recipe.name
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.8f))
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = recipe.name,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}