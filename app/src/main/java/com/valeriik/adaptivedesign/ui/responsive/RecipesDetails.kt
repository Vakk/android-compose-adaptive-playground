package com.valeriik.adaptivedesign.ui.responsive

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.valeriik.adaptivedesign.data.model.Recipe
import com.valeriik.adaptivedesign.ui.Navigation
import com.valeriik.adaptivedesign.ui.components.AppAdaptiveComponent
import com.valeriik.adaptivedesign.ui.components.AppAsyncImage
import com.valeriik.adaptivedesign.ui.viewmodel.RecipesListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetails(
    modifier: Modifier = Modifier,
    receiptId: String,
    navController: NavController,
    viewModel: RecipesListViewModel = viewModel()
) {
    val receiptState = viewModel.fetchDetails(receiptId).collectAsState()
    val receipt = receiptState.value

    if (receipt != null) {
        Success(receipt = receipt, navController = navController)
    } else {
        Scaffold(modifier = modifier, topBar = {
            TopAppBar(title = {
                Text("Loading...")
            }, navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "Navigate")
                }
            })
        }) { paddingValues ->
            Text("Loading...", modifier = Modifier.padding(paddingValues))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Success(
    modifier: Modifier = Modifier, navController: NavController, receipt: Recipe
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(modifier = modifier, topBar = {
        TopAppBar(title = {
            Text(receipt.name)
        }, navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, "Navigate")
            }
        }, colors = TopAppBarDefaults.topAppBarColors(
            scrolledContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
            navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
        ), scrollBehavior = scrollBehavior
        )
    }) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(
                    start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                    end = paddingValues.calculateStartPadding(LayoutDirection.Rtl),
                )
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
        ) {
            item {
                Spacer(modifier = Modifier.padding(top = paddingValues.calculateTopPadding()))
                AnimatedVisibility(visible = scrollBehavior.state.collapsedFraction > 0.5f) {
                    TopAppBar(title = {
                        Text(receipt.name, color = MaterialTheme.colorScheme.onSurface)
                    }, navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, "Navigate")
                        }
                    })
                }
            }
            item {
                AppAsyncImage(
                    data = receipt.image,
                    contentDescription = receipt.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                )
            }
            item {
                IngredientsView(
                    modifier = modifier
                        .fillMaxWidth(),
                    ingredients = receipt.ingredients
                )
            }
            item {
                Text(
                    "Instruction:",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            item {
                CookingInstructionsView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    receipt.instructions
                )

            }
            item {
                Spacer(modifier = Modifier.height(paddingValues.calculateBottomPadding() + 16.dp))
            }
        }
    }
}

@Composable
private fun IngredientsView(modifier: Modifier, ingredients: List<String>) {
    Column(
        modifier = modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        ingredients.forEach {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Canvas(
                    modifier = Modifier.size(6.dp)
                ) {
                    drawCircle(Color.Black)
                }
                Text(" $it", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
private fun CookingInstructionsView(
    modifier: Modifier, instructions: List<String>
) {
    Column(modifier = modifier) {
        instructions.forEachIndexed { index, value ->
            Text(
                text = "${index + 1}) $value",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Composable
private fun RelatedContentView(
    navController: NavController,
    modifier: Modifier = Modifier,
    relatedRecipes: List<Recipe>
) {
    if (relatedRecipes.isEmpty()) {
        return
    }
    Column(
        modifier = modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Related receipts:", style = MaterialTheme.typography.bodyLarge)
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(relatedRecipes) { item ->
                Card(
                    onClick = {
                        navController.navigate(
                            Navigation.RecipeDetails(
                                title = item.name,
                                id = item.id.toString(),
                            )
                        )
                    },
                    colors = CardDefaults.cardColors(
                        contentColor = MaterialTheme.colorScheme.primary
                    ),
                ) {
                    Box(modifier = Modifier
                        .width(156.dp)
                        .height(156.dp)) {
                        AppAsyncImage(
                            modifier = Modifier
                                .fillMaxHeight()
                                .aspectRatio(1f),
                            data = item.image, contentDescription = item.name
                        )
                        Text(
                            text = item.name,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.8f))
                                .padding(8.dp)
                                .align(Alignment.BottomCenter)
                        )
                    }
                }
            }
        }
    }
}