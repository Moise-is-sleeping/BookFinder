package ui.Screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import com.calculator.bookfinder.accountbuttons.AccountButtons
import com.calculator.bookfinder.header.lancelot
import com.calculator.bookfinder.rememberme.Ellipse1Property1Default
import com.calculator.bookfinder.rememberme.Ellipse1Property1Variant2
import com.calculator.bookfinder.rememberme.Ellipse2Property1Default
import com.calculator.bookfinder.rememberme.Property1
import com.calculator.bookfinder.rememberme.RememberMe
import com.calculator.bookfinder.rememberme.RememeberMeProperty1Default
import com.calculator.bookfinder.rememberme.RememeberMeProperty1Variant2
import com.calculator.bookfinder.rememberme.TopLevelProperty1Default
import com.calculator.bookfinder.rememberme.TopLevelProperty1Variant2
import com.calculator.bookfinder.signinemail.Email
import com.calculator.bookfinder.signinemail.EmailIcon
import com.calculator.bookfinder.signinemail.SignInEmail
import com.calculator.bookfinder.signinemail.TopLevel
import com.calculator.bookfinder.signinpassword.PasswordIcon
import com.calculator.bookfinder.signinpassword.SignInPassword
import com.google.relay.compose.BoxScopeInstance.boxAlign
import com.google.relay.compose.BoxScopeInstance.columnWeight
import com.google.relay.compose.BoxScopeInstance.rowWeight
import ui.ViewModel.LoginViewModel


@Composable
fun LoginScreen(loginViewModel: LoginViewModel){
    var state by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .background(color = Color(0xFFE5DBD0))
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Sign In", fontFamily = lancelot, fontSize = 50.sp,
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
            ),loginViewModel)
        PasswordSection(modifier = Modifier
            .rowWeight(1.0f)
            .columnWeight(1.0f)
            .width(335.dp)
            .height(75.dp)
            .boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 0.dp,
                    y = 270.dp
                )
            )
            ,loginViewModel)

        Row(
            modifier = Modifier.boxAlign(
            alignment = Alignment.TopStart,
            offset = DpOffset(
                x = -100.dp,
                y = 310.0.dp
                )
            )
        )
        {
            RadioButton(
                selected = state,
                onClick = { state = !state },
                modifier = Modifier.semantics { contentDescription = "Localized Description" },
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


        AccountButtons(
            buttonPressed = {
                            loginViewModel.login { Log.d("authentication","succes") }
            },
            buttonName = "   Login",
            property1 = com.calculator.bookfinder.accountbuttons.Property1.Default,
            modifier = Modifier.rowWeight(1.0f).columnWeight(1.0f).height(75.dp).width(335.dp)
                .boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = 0.dp,
                        y = 370.0.dp
                    )
                )
        )


    }

}

















@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailSection(modifier: Modifier = Modifier,loginViewModel: LoginViewModel) {
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
                loginViewModel.changeEmail(it)   },
                placeholder = {
                    Text(text = "someone@gmail.com")
                },
                colors = textFieldColors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color.Black
                ),
                singleLine = true

            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordSection(modifier: Modifier = Modifier,loginViewModel: LoginViewModel) {
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
                onValueChange = { loginViewModel.changePassword(it) },
                singleLine = true,
                placeholder = { Text(text = "password") },
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
                        // Please provide localized description for accessibility services
                        val description = if (passwordHidden) "Show password" else "Hide password"
                        Icon(imageVector = visibilityIcon, contentDescription = description)
                    }

                }

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
