package ui.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.calculator.bookfinder.accountbuttons.AccountButtons
import com.calculator.bookfinder.accountbuttons.Property1
import com.calculator.bookfinder.accountbuttons.lindenHill
import com.calculator.bookfinder.header.Header
import com.google.relay.compose.BoxScopeInstance.boxAlign
import com.google.relay.compose.BoxScopeInstance.columnWeight
import com.google.relay.compose.BoxScopeInstance.rowWeight
import data.Routes.Routes
import ui.ViewModel.LoginViewModel


@Composable
fun RegisterScreen(loginViewModel: LoginViewModel, navController: NavController){
    Column(modifier= Modifier
        .fillMaxSize()
        .background(color = Color(0xFFE5DBD0)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top){
        Text(text = "Register", fontFamily = lindenHill, fontSize = 50.sp,
            color = Color(
                alpha = 255,
                red = 0,
                green = 0,
                blue = 0
            ), modifier = Modifier
                .boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = 0.dp,
                        y = 110.dp
                    )
                )
        )
        EmailSection(modifier = Modifier
            .rowWeight(1.0f)
            .columnWeight(1.0f)
            .width(335.dp)
            .height(75.dp)
            .boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 0.dp,
                    y = 190.dp
                )
            ),loginViewModel,"Email")
        PasswordSection(modifier = Modifier
            .rowWeight(1.0f)
            .columnWeight(1.0f)
            .width(335.dp)
            .height(75.dp)
            .boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 0.dp,
                    y = 240.dp
                )
            )
            ,loginViewModel,"Password")
        PasswordSection(modifier = Modifier
            .rowWeight(1.0f)
            .columnWeight(1.0f)
            .width(335.dp)
            .height(75.dp)
            .boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 0.dp,
                    y = 290.dp
                )
            )
            ,loginViewModel,"Repeat password")
        AccountButtons(
            buttonPressed = {
                loginViewModel.login { navController.navigate(Routes.HomeScreen.route) }
            },
            buttonName = "Register",
            property1 = Property1.Default,
            modifier = Modifier
                .rowWeight(1.0f)
                .columnWeight(1.0f)
                .height(75.dp)
                .width(335.dp)
                .boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = 0.dp,
                        y = 340.0.dp
                    )
                )
        )



    }
}