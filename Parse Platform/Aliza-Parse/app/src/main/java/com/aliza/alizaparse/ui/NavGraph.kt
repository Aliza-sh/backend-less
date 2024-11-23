package com.aliza.alizaparse.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aliza.alizaparse.ui.screens.profile.ProfileScreen
import com.aliza.alizaparse.ui.screens.signIn.SignInScreen
import com.aliza.alizaparse.ui.screens.signUp.SignUpScreen
import com.aliza.alizaparse.ui.screens.splash.SplashScreen
import com.aliza.alizaparse.ui.screens.verify.VerifyScreen
import com.aliza.alizaparse.utils.PROFILE_SCREEN
import com.aliza.alizaparse.utils.SIGN_IN_SCREEN
import com.aliza.alizaparse.utils.SIGN_UP_SCREEN
import com.aliza.alizaparse.utils.SPLASH_SCREEN
import com.aliza.alizaparse.utils.VERIFY_SCREEN

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = SPLASH_SCREEN
    ) {
        composable(SPLASH_SCREEN) {
            SplashScreen {
                navController.popBackStack()
                navController.navigate(it)
            }
        }
        composable(SIGN_UP_SCREEN) {
            SignUpScreen {
                navController.navigate(it)
            }
        }
        composable(SIGN_IN_SCREEN) {
            SignInScreen {
                navController.navigate(it)
            }
        }
        composable(VERIFY_SCREEN) {
            VerifyScreen {
                if (it == "Back")
                    navController.popBackStack()
                else {
                    while (navController.popBackStack()) {
                        navController.popBackStack()
                    }
                    navController.navigate(it)
                }
            }
        }
        composable(PROFILE_SCREEN) {
            ProfileScreen() {
                navController.popBackStack()
                navController.navigate(it)
            }
        }
    }
}
