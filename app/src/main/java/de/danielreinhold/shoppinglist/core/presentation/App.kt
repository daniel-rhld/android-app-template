package de.danielreinhold.shoppinglist.core.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.generated.NavGraphs

@Composable
fun App() {
    DestinationsNavHost(
        navGraph = NavGraphs.root,
        modifier = Modifier.fillMaxSize()
    )
}