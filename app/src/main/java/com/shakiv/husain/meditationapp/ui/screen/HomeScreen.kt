package com.shakiv.husain.meditationapp.ui.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shakiv.husain.meditationapp.R
import com.shakiv.husain.meditationapp.data.model.BottomMenuContent
import com.shakiv.husain.meditationapp.data.model.Feature
import com.shakiv.husain.meditationapp.ui.theme.AquaBlue
import com.shakiv.husain.meditationapp.ui.theme.ButtonBlue
import com.shakiv.husain.meditationapp.ui.theme.DarkerButtonBlue
import com.shakiv.husain.meditationapp.ui.theme.DeepBlue
import com.shakiv.husain.meditationapp.ui.theme.LightRed
import com.shakiv.husain.meditationapp.ui.theme.TextWhite
import com.shakiv.husain.meditationapp.utils.Data.getBottomMenuList
import com.shakiv.husain.meditationapp.utils.Data.getChipList
import com.shakiv.husain.meditationapp.utils.Data.getFeaturedList
import com.shakiv.husain.meditationapp.utils.standardQuadFromTo

@Preview(showBackground = true)
@Composable fun HomeScreen() {

    var chipList by remember {
        mutableStateOf(emptyList<String>())
    }

    LaunchedEffect(key1 = Unit) {
        chipList = getChipList()
    }

    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column {

            GreetingSection()
            ChipsSection(chipsList = chipList)
            CurrentMeditation()
            FeaturedSection(featureList = getFeaturedList())

            BottomMenu(items = getBottomMenuList())

        }
    }
}


@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedItemIndex: Int = 0
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(15.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
            ) {
                selectedItemIndex = index
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemClick()
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeHighlightColor else Color.Transparent)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = item.title,
            color = if(isSelected) activeTextColor else inactiveTextColor
        )
    }
}

@Composable fun GreetingSection(name: String = "Shakiv Husain") {


    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Column(
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Good morning, $name", style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = "We wish you have a good day.", style = MaterialTheme.typography.bodySmall
            )

        }
        Image(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search Image", modifier = Modifier.size(20.dp)

        )
    }
}


@Composable fun ChipsSection(
    chipsList: List<String>
) {

    var selectionChipIndex by remember {
        mutableStateOf(0)
    }

    LazyRow() {
        items(chipsList.size) {
            Box(modifier = Modifier
                .padding(top = 15.dp, bottom = 15.dp, start = 15.dp)
                .clickable {
                    selectionChipIndex = it
                }
                .clip(RoundedCornerShape(10.dp))
                .background(
                    if (selectionChipIndex == it) ButtonBlue else DarkerButtonBlue
                )
                .padding(15.dp)) {

                Text(text = chipsList.get(index = it), color = TextWhite)
            }
        }

    }
}


@Composable fun CurrentMeditation(color: Color = LightRed) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(vertical = 15.dp, horizontal = 20.dp)
            .fillMaxWidth()
    ) {

        Column {
            Text(text = "Daily Thought", style = MaterialTheme.typography.headlineMedium)
            Text(
                text = "Meditation 3-10 min", style = MaterialTheme.typography.bodySmall,
                color = TextWhite
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "Play Button", tint = TextWhite,
                modifier = Modifier.size(16.dp)
            )
        }

    }
}


@Composable
fun FeaturedSection(featureList: List<Feature>) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.84f)
    ) {
        Text(
            text = "Features",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(15.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 10.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(featureList.size) { index ->
                FeatureItem(featureList[index])
            }
        }


    }

}


@Composable

fun FeatureItem(feature: Feature) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1F)
            .clip(RoundedCornerShape(10.dp))
            .background(feature.darkColor)
    ) {

        val width = constraints.maxWidth
        val height = constraints.maxHeight

        // Medium Colored Path
        val mediumColoredPath1 = Offset(0F, height * 0.3f)
        val mediumColoredPath2 = Offset(width * .1F, height * 0.35f)
        val mediumColoredPath3 = Offset(width * .4F, height * 0.5f)
        val mediumColoredPath4 = Offset(.75F, height * 0.7f)
        val mediumColoredPath5 = Offset(width * 1.4F, -height.toFloat())


        val mediumColoredPath = Path().apply {
            standardQuadFromTo(mediumColoredPath1, mediumColoredPath2)
            standardQuadFromTo(mediumColoredPath2, mediumColoredPath3)
            standardQuadFromTo(mediumColoredPath3, mediumColoredPath4)
            standardQuadFromTo(mediumColoredPath4, mediumColoredPath5)

            lineTo(width.toFloat(), height.toFloat() + 100F)
            lineTo(-100f, height.toFloat() + 100F)
            close()
        }


        // Light colored path
        val lightPoint1 = Offset(0f, height * 0.35f)
        val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

        val lightColoredPath = Path().apply {
            moveTo(lightPoint1.x, lightPoint1.y)
            standardQuadFromTo(lightPoint1, lightPoint2)
            standardQuadFromTo(lightPoint2, lightPoint3)
            standardQuadFromTo(lightPoint3, lightPoint4)
            standardQuadFromTo(lightPoint4, lightPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }


        Canvas(
            modifier = Modifier
                .fillMaxSize()

        ) {
            drawPath(
                path = mediumColoredPath,
                color = feature.mediumColor
            )

            drawPath(
                path = lightColoredPath,
                color = feature.lightColor
            )
        }


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {

            Text(
                text = feature.title,
                style = MaterialTheme.typography.headlineMedium,
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopStart)
            )

            Image(
                painter = painterResource(id = feature.iconId),
                contentDescription = feature.title,
                modifier = Modifier.align(Alignment.BottomStart)

            )

            Text(
                text = "Start",
                color = TextWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {

                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ButtonBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)


            )


        }


    }

}

