package com.rajabd.compose1hellojetpackcompose

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExpandLess
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import com.rajabd.compose1hellojetpackcompose.ui.theme.Compose1HelloJetpackComposeTheme

private val sampleName = listOf(
    "Yu Zhong",
    "Grager",
    "Hayabusa",
    "Hanabi",
    "Zilong",
    "Wanwan binti Yu Zhong",
    "Yin",
    "Ling",
    "Baxia",
    "Agus"
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose1HelloJetpackComposeTheme {
                // A surface container using the 'background' color from the theme

                // Move to Func containerList, -> List
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    Greeting("From Android !")
//                }
                ContainerList()

            }
        }
    }
}

//List case
@Composable
fun ContainerList() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        // single data case
//        Greeting("From Android !")

        // list case
        GreetingList(sampleName)

        // blank params case
//        GreetingList(listOf())
    }
}

@Composable
fun GreetingList(names: List<String>) {
    if (names.isNotEmpty()) {
//        Column {
//            for (name in names) {
//                Greeting(name)
//            }
//        }
        // migrated to LazyColumn

        LazyColumn{
            items(names) { name ->
                Greeting(name)
            }
        }
    } else {
        Box(contentAlignment = Alignment.Center) {
            Text(text = "No People To Great :(")
        }
    }
}

@Preview(name = "List Data Case", showBackground = true, device = Devices.PIXEL_4)
@Preview(name = "List Data Case", showBackground = true, device = Devices.PIXEL_4, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ContainerListPreview() {
    Compose1HelloJetpackComposeTheme {
        ContainerList()
    }
}


// for single item
@Composable
fun Greeting(name: String) {

    // State case
    var isExpanded by remember {mutableStateOf(false)}
    // by = delegated properties -> boilerplate
    // import androidx.compose.runtime.setValue
    // import androidx.compose.runtime.getValue

    // Animation case
    val animatedSizeDp by animateDpAsState(
        targetValue = if (isExpanded) 120.dp else 80.dp,
        animationSpec = spring(     //setting animation style
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

//    Pretty UI Case
    Card(
        backgroundColor = MaterialTheme.colors.primary,     // with Color.kt
        shape = MaterialTheme.shapes.medium,                // with Shape.kt
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
    ) {

        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.avatar_kagura),
                contentDescription = "Avatar Kagura",
                modifier = Modifier.size(animatedSizeDp),

            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Hello $name !",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = "How are you, $name ?",
//                    fontStyle = FontStyle.Italic      //without Type.kt
                    // with Type.kt
                    style = MaterialTheme.typography.body1.copy(
                        fontStyle = FontStyle.Italic
                    )
                )
            }
            // State case
            IconButton(onClick = { isExpanded = !isExpanded }) {
                Icon(
                    imageVector = if (isExpanded) Icons.Outlined.ExpandLess else Icons.Outlined.ExpandMore,
                    contentDescription = if (isExpanded) "Show Less" else "Show More"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(name="Single Data Case" ,showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    Compose1HelloJetpackComposeTheme {
        Greeting("Digdaya")
    }
}