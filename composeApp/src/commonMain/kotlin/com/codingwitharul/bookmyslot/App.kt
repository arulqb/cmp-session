package com.codingwitharul.bookmyslot

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.window.core.layout.WindowSizeClass
import com.codingwitharul.bookmyslot.di.appModule
import com.codingwitharul.bookmyslot.presentation.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.dsl.KoinAppDeclaration

@Composable
@Preview
fun App(
    windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass,
    koinAppDeclaration: KoinAppDeclaration? = null
) {
    KoinApplication(application = {
        koinAppDeclaration?.invoke(this)
        modules(appModule())
    }) {
        AppTheme {
            Router(windowSizeClass)
        }
    }
}
