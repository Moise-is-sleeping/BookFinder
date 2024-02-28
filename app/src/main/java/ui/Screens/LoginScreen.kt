package ui.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.calculator.bookfinder.accountbuttons.AccountButtons
import com.calculator.bookfinder.accountbuttons.lindenHill
import com.calculator.bookfinder.registerlink.RegisterLink
import com.calculator.bookfinder.rememberme.Ellipse1Property1Default
import com.calculator.bookfinder.rememberme.Ellipse1Property1Variant2
import com.calculator.bookfinder.rememberme.Ellipse2Property1Default
import com.calculator.bookfinder.rememberme.Property1
import com.calculator.bookfinder.rememberme.RememeberMeProperty1Default
import com.calculator.bookfinder.rememberme.RememeberMeProperty1Variant2
import com.calculator.bookfinder.rememberme.TopLevelProperty1Default
import com.calculator.bookfinder.rememberme.TopLevelProperty1Variant2
import com.calculator.bookfinder.signinemail.Email
import com.calculator.bookfinder.signinemail.EmailIcon
import com.calculator.bookfinder.signinemail.TopLevel
import com.calculator.bookfinder.signinpassword.PasswordIcon
import com.google.relay.compose.BoxScopeInstance.boxAlign
import com.google.relay.compose.BoxScopeInstance.columnWeight
import com.google.relay.compose.BoxScopeInstance.rowWeight
import data.Routes.Routes
import ui.ViewModel.LoginViewModel


@Composable
fun LoginScreen(loginViewModel: LoginViewModel,navController: NavController){
    var state by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .background(color = Color(0xFFE5DBD0))
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.15f))
        Text(text = "Sign In", fontFamily = lindenHill, fontSize = 50.sp,
            color = Color(
            alpha = 255,
            red = 0,
            green = 0,
            blue = 0
            )
        )
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        EmailSection(modifier = Modifier
            .rowWeight(1.0f)
            .columnWeight(1.0f)
            .width(335.dp)
            .height(75.dp),
            loginViewModel,"Email")

        Spacer(modifier = Modifier.fillMaxHeight(0.15f))
        SignInPasswordSection(modifier = Modifier
            .rowWeight(1.0f)
            .columnWeight(1.0f)
            .width(335.dp)
            .height(75.dp)
            ,loginViewModel,"Password")
        Spacer(modifier = Modifier.fillMaxHeight(0.07f))
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        )
        {
            Spacer(modifier = Modifier.fillMaxWidth(0.07f))
            RadioButton(
                selected = state,
                onClick = { state = !state },
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color(0xFFFBF2C0),
                    unselectedColor= Color(0xFFFFFFFF)
                )
            )
            RememeberMeProperty1Default(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = 0.dp,
                        y = 13.0.dp
                    )
                )
            )
        }
        Spacer(modifier = Modifier.fillMaxHeight(0.25f))
        AccountButtons(
            buttonPressed = {
                            loginViewModel.login { navController.navigate(Routes.HomeScreen.route) }
            },
            buttonName = "   Login",
            property1 = com.calculator.bookfinder.accountbuttons.Property1.Default,
            modifier = Modifier
                .rowWeight(1.0f)
                .columnWeight(1.0f)
                .height(75.dp)
                .width(335.dp)
            )
        Spacer(modifier = Modifier.fillMaxHeight(0.35f))
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Spacer(modifier = Modifier.fillMaxWidth(0.1f))
            RegisterLink(
                registerButton = {
                    navController.navigate(Routes.RegisterScreen.route)
                },
                modifier = Modifier.rowWeight(1.0f)
                    .columnWeight(1.0f)
                    .height(39.dp).width(232.dp)
            )
        }




    }

}

















@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailSection(modifier: Modifier = Modifier,loginViewModel: LoginViewModel,placeHolder:String) {
    val wrongInfoBool by loginViewModel.wrongInfo.collectAsState()
    var email by rememberSaveable { mutableStateOf("") }
    TopLevel(modifier = modifier) {
        EmailIcon(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 15.0.dp,
                    y = 13.0.dp
                )
            )
        )

        Email(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 60.0.dp,
                    y = 5.dp
                )
            )
        ) {
            TextField(
                modifier= Modifier
                    .fillMaxWidth()
                    .background(color = Color.Transparent),
                value = loginViewModel.email,
                onValueChange = {
                        loginViewModel.changeEmail(it)
                        loginViewModel.changeError()
                                },
                placeholder = {
                    Text(text = placeHolder,fontFamily = lindenHill,modifier = Modifier)
                },
                colors = textFieldColors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color.Black
                ),
                singleLine = true,
                isError = wrongInfoBool
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInPasswordSection(modifier: Modifier = Modifier, loginViewModel: LoginViewModel, placeholder:String) {
    val wrongInfoBool by loginViewModel.wrongInfo.collectAsState()
    var password by rememberSaveable { mutableStateOf("") }
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    TopLevel(modifier = modifier) {
        PasswordIcon(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 15.0.dp,
                    y = 13.0.dp
                )
            )
        )
        com.calculator.bookfinder.signinpassword.Email(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 60.0.dp,
                    y = 5.dp
                )
            )
        ) {
            TextField(
                value = loginViewModel.password,
                onValueChange = {
                    loginViewModel.changePassword(it)
                    loginViewModel.changeError()
                                },
                singleLine = true,
                placeholder = { Text(text = placeholder,fontFamily = lindenHill) },
                colors = textFieldColors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color.Black
                ),
                visualTransformation =
                if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(onClick = { passwordHidden = !passwordHidden }) {
                        val visibilityIcon =
                            if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        val description = if (passwordHidden) "Show password" else "Hide password"
                        Icon(imageVector = visibilityIcon, contentDescription = description)
                    }

                },
                isError = wrongInfoBool

            )
        }
    }
}



@Preview
@Composable
fun RememberMeEdited(
    modifier: Modifier = Modifier,
    property1: Property1 = Property1.Default,
    remeberMeButton: () -> Unit = {}
) {
    when (property1) {
        Property1.Default -> TopLevelProperty1Default(modifier = modifier) {
            Ellipse1Property1Default(
                remeberMeButton = remeberMeButton,
                modifier = Modifier.boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = 26.dp,
                        y = 0.0.dp
                    )
                )
            )
            Ellipse2Property1Default(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = 30.0.dp,
                        y = 15.0.dp
                    )
                )
            )
            RememeberMeProperty1Default(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = 60.0.dp,
                        y = 13.0.dp
                    )
                )
            )
        }
        Property1.Variant2 -> TopLevelProperty1Variant2(modifier = modifier) {
            Ellipse1Property1Variant2(
                remeberMeButton = remeberMeButton,
                modifier = Modifier.boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = 14.0.dp,
                        y = 0.0.dp
                    )
                )
            )
            RememeberMeProperty1Variant2(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = 60.0.dp,
                        y = 13.0.dp
                    )
                )
            )
        }
    }
}
