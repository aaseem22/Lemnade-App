package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                Lemonade()
            }
        }
    }


@Composable
fun Lemonade() {

    var currentStep by remember {
        mutableStateOf(1)
    }

    var squeezeCount by remember {
        mutableStateOf(0)
    }

    androidx.compose.material.Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        when(currentStep){
            1 ->{
                LemonAndText(
                    text = R.string.p1,
                    image = R.drawable.lemon_tree,
                    contentDesc = R.string.app_name
                ) {
                    currentStep = 2
                    squeezeCount = (2..4).random()
                }
            }
            2 ->{
                LemonAndText(
                    text = R.string.p2,
                    image = R.drawable.lemon_squeeze,
                    contentDesc = R.string.t2
                ) {
                    squeezeCount--

                    if (squeezeCount == 0) {
                        currentStep = 3
                    }
                }
            }
            3 ->{
                LemonAndText(
                    text = R.string.p3,
                    image = R.drawable.lemon_drink,
                    contentDesc = R.string.t3
                ) {
                    currentStep = 4

                }
            }
            4 ->{
                LemonAndText(
                    text = R.string.p4,
                    image = R.drawable.lemon_restart,
                    contentDesc = R.string.t4
                ) {
                    currentStep = 1

                }
            }

        }

    }

    
}



@Composable
fun LemonAndText(
    modifier: Modifier= Modifier,
    text: Int,
    image: Int,
    contentDesc: Int,
    onImageClick: () -> Unit
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text= stringResource(text),
            fontSize = 18.sp
            )
        Spacer(modifier = modifier.height(16.dp))
        Image(
            painter =  painterResource(image) ,
            contentDescription = stringResource(contentDesc),
            modifier = Modifier
                .wrapContentSize()
                .clickable(
                    onClick = onImageClick
                )
                .border(BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(12.dp)
            )



    }

}

@Preview(showBackground = true)
@Composable
fun previewLemon(){
    Lemonade()
}