package com.example.firebaseexp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.imageLoader
import com.example.firebaseexp.frontpage.*
import com.example.firebaseexp.ui.theme.FirebaseExpTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.maps.android.compose.GoogleMap


class MainActivity : ComponentActivity() {

    companion object {
        const val RC_SIGN_IN = 100
    }


    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // firebase auth instance
        mAuth = FirebaseAuth.getInstance()

        // configure Google SignIn
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        setContent {
            FirebaseExpTheme {
//                nativation()


                if (mAuth.currentUser == null) {
                    GoogleButton {
                        signIn()
                    }
                } else {
                    val user: FirebaseUser = mAuth.currentUser!!
                    topBar(
                        profileImage = user.photoUrl!!,
                        name = user.displayName!!,
                        email = user.email!!,
                        signOutClicked = {
                            signOut()
                        }
                    )
                }//else
            }
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }//signIN

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // result returned from launching the intent from GoogleSignInApi.getSignInIntent()
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception
            if (task.isSuccessful) {
                try {
                    // Google SignIn was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: Exception) {
                    // Google SignIn Failed
                    Log.d("SignIn", "Google SignIn Failed")
                }
            } else {
                Log.d("SignIn", exception.toString())
            }
        }

    }//onActivityresult

    @OptIn(ExperimentalMaterialApi::class)
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // SignIn Successful
                    Toast.makeText(this, "SignIn Successful", Toast.LENGTH_SHORT).show()
                    setContent {
                        FirebaseExpTheme {
                            val user: FirebaseUser = mAuth.currentUser!!


                            //BottomDrawer
                            BottomSheetScaffold(
                                sheetContent = {
                                    Card(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(Color.Transparent),

                                        shape = RoundedCornerShape(20.dp)

                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxHeight()
                                                .padding(10.dp)
                                        ) {
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(5.dp),
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                GradientButton(
                                                    text = "Electrician",
                                                    textColor = Color.White,
                                                    gradient = Brush.horizontalGradient(
                                                        colors = listOf(
                                                            Color.Black, Color.Gray
                                                        )
                                                    )
                                                ) {

                                                }
                                                GradientButton(
                                                    text = "Plumber",
                                                    textColor = Color.White,
                                                    gradient = Brush.horizontalGradient(
                                                        colors = listOf(
                                                            Color.Black, Color.Gray
                                                        )
                                                    )
                                                ) {

                                                }


                                            }
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(5.dp),
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                GradientButton(
                                                    text = "Painter",
                                                    textColor = Color.White,
                                                    gradient = Brush.horizontalGradient(
                                                        colors = listOf(
                                                            Color.Black, Color.Gray
                                                        )
                                                    )
                                                ) {

                                                }
                                                GradientButton(
                                                    text = "Carpenter",
                                                    textColor = Color.White,
                                                    gradient = Brush.horizontalGradient(
                                                        colors = listOf(
                                                            Color.Black, Color.Gray
                                                        )
                                                    )
                                                ) {

                                                }

                                            }
                                        }

                                    }

                                },
                                sheetBackgroundColor = Color.Transparent,
                                sheetPeekHeight = 400.dp
                            ) {
                               Column() {
                                   topBar(
                                       profileImage = user.photoUrl!!,
                                       name = user.displayName!!,
                                       email = user.email!!,
                                       signOutClicked = {
                                           signOut()
                                       })

                                   GoogleMap( modifier = Modifier.fillMaxSize())
                               }

                            }
                        }
                    }
                } else {
                    // SignIn Failed
                    Toast.makeText(this, "SignIn Failed", Toast.LENGTH_SHORT).show()
                }
            }
    }//firebasewithgoogle

    private fun signOut() {
        // get the google account
        val googleSignInClient: GoogleSignInClient

        // configure Google SignIn
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Sign Out of all accounts
        mAuth.signOut()
        googleSignInClient.signOut()
            .addOnSuccessListener {
                Toast.makeText(this, "Sign Out Successful", Toast.LENGTH_SHORT).show()
                setContent {
                    FirebaseExpTheme {
                        GoogleSignInButton {
                            signIn()
                        }
                    }
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Sign Out Failed", Toast.LENGTH_SHORT).show()
            }
    }//signout


}//mainactivity


// result returned from launching the intent from GoogleSignInApi.getSignInIntent()














