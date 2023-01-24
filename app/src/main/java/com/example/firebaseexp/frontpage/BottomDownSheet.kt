package com.example.firebaseexp.frontpage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun BottomDrawer1() {
    BottomDrawer()

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomDrawer() {
    
    
    BottomSheetScaffold(sheetContent = {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent),

            shape = RoundedCornerShape(20.dp)

        ) {
            Column(modifier = Modifier
                .fillMaxHeight()
                .padding(10.dp)) {
                Row( modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    GradientButton(text = "Electrician", textColor = Color.White, gradient = Brush.horizontalGradient(colors = listOf(
                        Color.Black, Color.Gray))) {

                    }
                    GradientButton(text = "Electrician", textColor = Color.White, gradient = Brush.horizontalGradient(colors = listOf(
                        Color.Black, Color.Gray))) {

                    }


                }
                Row( modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    GradientButton(text = "Electrician", textColor = Color.White, gradient = Brush.horizontalGradient(colors = listOf(
                        Color.Black, Color.Gray))) {

                    }
                    GradientButton(text = "Electrician", textColor = Color.White, gradient = Brush.horizontalGradient(colors = listOf(
                        Color.Black, Color.Gray))) {

                    }

                }
            }

        }

    },
        sheetBackgroundColor = Color.Transparent,
        sheetPeekHeight = 400.dp
    ) {

    }
    
}