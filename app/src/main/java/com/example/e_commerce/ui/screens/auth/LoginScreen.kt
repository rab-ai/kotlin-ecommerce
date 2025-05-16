package com.example.e_commerce.ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.e_commerce.R
import com.example.e_commerce.data.database.AppDatabase
import com.example.e_commerce.ui.theme.EcommerceTheme
import com.example.e_commerce.utils.SessionManager
import com.example.e_commerce.utils.hashPassword
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTopAppBar(onBackClick: () -> Unit) {
    LargeTopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        title = {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = "Login",
                style = MaterialTheme.typography.headlineMedium
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Sharp.KeyboardArrowLeft,
                    contentDescription = "Back"
                )
            }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}

@Composable
fun EmailTextField(email: String, onEmailChange: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = onEmailChange,
        label = { Text("Email") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .width(343.dp)
            .height(64.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun PasswordTextField(password: String, onPasswordChange: (String) -> Unit) {
    TextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text("Password") },
        visualTransformation = PasswordVisualTransformation(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .width(343.dp)
            .height(64.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun ForgotPassword(onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ClickableText(text = AnnotatedString("Forgot your password?"), onClick = { onClick() })
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_right_alt_24),
            contentDescription = "Back",
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun LoginButton(onLoginClick: () -> Unit, isEnabled: Boolean) {
    Button(
        onClick = onLoginClick,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .width(343.dp)
            .height(48.dp),
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Text(text = "LOGIN", color = MaterialTheme.colorScheme.background)
    }
}

@Composable
fun LoginScreen(onNavigateToSignUp: () -> Unit, onLoginSuccess: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }
    val scope = rememberCoroutineScope()
    val userDao = AppDatabase.getDatabase(context).userDao()

    LaunchedEffect(Unit) {
        if(sessionManager.isLoggedIn()) {
            onLoginSuccess()
        }
    }

    Scaffold(
        topBar = { LoginTopAppBar(onBackClick = { onNavigateToSignUp() }) },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(69.dp))
            EmailTextField(email, onEmailChange = { email = it })
            Spacer(modifier = Modifier.height(16.dp))
            PasswordTextField(password, onPasswordChange = { password = it })
            Spacer(modifier = Modifier.height(16.dp))
            ForgotPassword(onClick = { println("Forgot password clicked") })
            Spacer(modifier = Modifier.height(32.dp))
            LoginButton(onLoginClick = {
                scope.launch {
                    val hashedPassword = hashPassword(password)
                    val user = userDao.getUserByEmailAndPassword(email, hashedPassword)
                    if (user != null) {
                        sessionManager.saveUserSession(user.email)
                        onLoginSuccess()
                    } else {
                        showError = true
                    }
                }
            },
                isEnabled = email.isNotBlank() && password.isNotBlank()
            )
            if (showError) {
                Text(
                    text = "Invalid email or password!",
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    EcommerceTheme {
        LoginScreen(onNavigateToSignUp = {}, onLoginSuccess = {})
    }
}