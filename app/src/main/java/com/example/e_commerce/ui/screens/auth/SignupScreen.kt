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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.e_commerce.R
import com.example.e_commerce.data.database.AppDatabase
import com.example.e_commerce.data.model.User
import com.example.e_commerce.ui.navigation.Routes
import com.example.e_commerce.ui.theme.EcommerceTheme
import com.example.e_commerce.utils.SessionManager
import com.example.e_commerce.utils.hashPassword
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpTopAppBar(onBackClick: () -> Unit) {
    LargeTopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        title = {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = "Sign Up",
                style = MaterialTheme.typography.headlineMedium
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.height(44.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Sharp.KeyboardArrowLeft,
                    contentDescription = "Back",
                    modifier = Modifier.size(32.dp)
                )
            }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}

@Composable
fun NameTextField(name: String, onNameChange: (String) -> Unit) {
    TextField(
        value = name,
        onValueChange = onNameChange,
        label = { Text("Name") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
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
fun SignUpScreen(onNavigateToLogin: () -> Unit, onSignUpSuccess: () -> Unit) { // context parametresi kaldırıldı
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }
    val scope = rememberCoroutineScope()
    val userDao = AppDatabase.getDatabase(context).userDao()

    LaunchedEffect(Unit) {
        if(sessionManager.isLoggedIn()) {
            onNavigateToLogin()
        }
    }

    Scaffold(
        topBar = { SignUpTopAppBar(onBackClick = { println("Back clicked") }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(73.dp))
            NameTextField(name, onNameChange = { name = it })
            Spacer(modifier = Modifier.height(8.dp))
            EmailTextField(email, onEmailChange = { email = it })
            Spacer(modifier = Modifier.height(8.dp))
            PasswordTextField(password, onPasswordChange = { password = it })
            Spacer(modifier = Modifier.height(16.dp))
            AlreadyHaveAccount(onClick = onNavigateToLogin)
            Spacer(modifier = Modifier.height(16.dp))
            SignUpButton(onClick = {
                scope.launch {
                    val hashedPassword = hashPassword(password)
                    val user = User(name = name, email = email, passwordHash = hashedPassword)
                    userDao.insertUser(user)
                    onSignUpSuccess()
                }
            })
        }
    }
}

@Composable
fun SignUpButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .width(343.dp)
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Text(text = "SIGN UP", color = MaterialTheme.colorScheme.background)
    }
}

@Composable
fun AlreadyHaveAccount(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 20.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ClickableText(
            text = AnnotatedString("Already have an account?"),
            onClick = { onClick() }
        )
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_right_alt_24),
            contentDescription = "Back",
            tint = MaterialTheme.colorScheme.primary
        )
    }
}
