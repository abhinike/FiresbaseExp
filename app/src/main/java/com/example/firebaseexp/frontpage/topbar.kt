package com.example.firebaseexp.frontpage

import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun GoogleSignInButton(
    signInClicked: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp)
                .height(55.dp)
                .fillMaxWidth()
                .clickable {
                    signInClicked()
                },
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(width = 1.5.dp, color = Color.Black),
            elevation = 5.dp
        ) {
            Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .size(32.dp)
                        .padding(0.dp)
                        .align(Alignment.CenterVertically),
                    painter = painterResource(id = com.example.firebaseexp.R.drawable.ic_google_logo),
                    contentDescription = "google_logo"
                )
                Text(
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .align(Alignment.CenterVertically),
                    text = "Sign In With Google",
                    fontSize = MaterialTheme.typography.h6.fontSize,
                )
            }
        }
    }
}//signinbutton


@Composable
fun ProfileScreen(
    profileImage: Uri,
    name: String,
    email: String,
    signOutClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .size(150.dp)
                .fillMaxHeight(0.4f),
            shape = RoundedCornerShape(125.dp),
            elevation = 10.dp
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = profileImage,
                contentDescription = "profile_photo",
                contentScale = ContentScale.FillBounds
            )
        }

        Column(
            modifier = Modifier
                .fillMaxHeight(0.6f)
                .padding(top = 60.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = {},
                readOnly = true,
                label = {
                    Text(text = "Name")
                },
            )

            OutlinedTextField(
                modifier = Modifier.padding(top = 20.dp),
                value = email,
                onValueChange = {},
                readOnly = true,
                label = {
                    Text(text = "Email")
                },
            )

            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 100.dp),
                onClick = { signOutClicked() }
            ) {
                Text(text = "LogOut")
            }
        }

    }
}


@Composable
fun topBar(
    profileImage: Uri,
    name: String,
    email: String,
    signOutClicked: () -> Unit

){
    Row(
        modifier = Modifier
            .padding(16.dp)
            .height(40.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row() {
            Text("Hello ", style = TextStyle(fontSize = 18.sp, fontFamily = FontFamily.SansSerif))
            Text(name, style = TextStyle(fontSize =18.sp, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold))

        }

        Row() {
            Card(
                modifier = Modifier
                    .size(40.dp)
                    .fillMaxHeight(0.4f)
                    .clickable { signOutClicked() },
                shape = RoundedCornerShape(20.dp),
                elevation = 10.dp
            ) {
                Icon(painter = painterResource(id = com.example.firebaseexp.R.drawable.bell_icon), contentDescription = "bell icon" )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Card(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .size(40.dp)
                    .fillMaxHeight(0.4f)
                    .clickable { signOutClicked() },

                border = BorderStroke(1.dp, Color.Black),
                elevation = 10.dp
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = profileImage,
                    contentDescription = "profile_photo",
                    contentScale = ContentScale.FillBounds
                )
            }

        }





    }
}

@Composable
fun ImageCircle(
    painter : Painter,
    des : String
){
    Card (
        modifier = Modifier
            .height(50.dp)
            .width(50.dp),
        shape = RoundedCornerShape(25.dp),
        border = BorderStroke(0.5.dp, Color.White),
        elevation = 10.dp
    ){
        Image(painter = painter, contentDescription = des)
    }
}
