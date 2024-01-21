@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class
)

package uz.turgunboyevjurabek.pagerandtabrowexperiment

import android.graphics.ColorFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab

import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Tab
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.turgunboyevjurabek.pagerandtabrowexperiment.ui.theme.PagerAndTabRowExperimentTheme
import uz.turgunboyevjurabek.pagerandtabrowexperiment.ui.theme.Purple40

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PagerAndTabRowExperimentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        TabView()
                        ListUI()
                    }
                }
            }
        }
    }
}

@Composable
fun TabView() {
    val tabItems= listOf(
        TabItem(
            title = "HomePage",
            selectedIcon = painterResource(id = R.drawable.home_fiel),
            unselectedIcon = painterResource(id = R.drawable.home_uot)
        ),
        TabItem(
            title = "CarPage",
            selectedIcon = painterResource(id = R.drawable.car_fiel),
            unselectedIcon = painterResource(id = R.drawable.car_out)
        ),
        TabItem(
            title = "NaturalPage",
            selectedIcon = painterResource(id = R.drawable.nattural_1),
            unselectedIcon = painterResource(id = R.drawable.nattural_1)
        ),
        TabItem(
            title = "MixPage",
            selectedIcon = painterResource(id = R.drawable.mix2),
            unselectedIcon = painterResource(id = R.drawable.mix_1)
        )
    )
    val pages= listOf(
        HomeScreen,
        CarScreen,
        NaturalScreen,
        MixScreen
    )
    var selectedScreen by remember { mutableStateOf<Screen>(HomeScreen) }

    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    val pagerState= rememberPagerState {
        tabItems.size
    }
    val currentScreen = rememberUpdatedState(selectedScreen)
    LaunchedEffect(pagerState){
        snapshotFlow { pagerState.currentPage }.collect{page->
            selectedScreen=pages[page]
        }
    }

    LaunchedEffect(selectedTabIndex){
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(pagerState.currentPage,pagerState.isScrollInProgress){
        if (!pagerState.isScrollInProgress){
            selectedTabIndex=pagerState.currentPage
        }

    }



    Column() {

        TabRow(selectedTabIndex = pages.indexOf(currentScreen.value),
            indicator = { tabPositions ->
                // Empty indicator to make it invisible
            },
            modifier = Modifier
            ) {
            tabItems.forEachIndexed { index, item ->
                CustomTab(selected = index==selectedTabIndex,
                    onClick = { selectedTabIndex=index },
                    text = { Text(text = item.title, fontFamily = FontFamily.Serif, fontSize = 10.sp)},
                    icon ={
                        Image(
                            painter = if (index == selectedTabIndex) item.selectedIcon else item.unselectedIcon,
                            contentDescription = null, modifier = Modifier.size(25.dp)
                        )
                    }
                )
            }
        }

        HorizontalPager(state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)) {
            when (val screen = pages[pagerState.currentPage]) {
                is HomeScreen -> HomeScreen()
                is CarScreen -> CarScreen()
                is NaturalScreen -> NaturalScreen()
                is MixScreen -> MixScreen()
            }

        }
    }



}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    PagerAndTabRowExperimentTheme {
        Column {
            TabView()
        }

    }
}
data class TabItem(
    val title:String,
    val selectedIcon:Painter,
    val unselectedIcon:Painter,
)

@Composable
fun CustomTab(
    selected: Boolean,
    onClick: () -> Unit,
    text: @Composable () -> Unit,
    icon: @Composable () -> Unit
) {
    // Custom tabni shakllantirish
    ElevatedCard(
        modifier = Modifier
            .clickable(onClick = onClick)
            .clip(RoundedCornerShape(15.dp))
            .size(60.dp)
            .padding(10.dp)
            .border(
                BorderStroke(
                    if (selected) 1.5.dp else 1.dp,
                    if (selected) Color.Blue else Color.White
                ), shape = RoundedCornerShape(15.dp)
            ),
        colors = CardDefaults.cardColors(if (selected) Color.Cyan else Color.White),
        elevation = CardDefaults.cardElevation(10.dp)

    ) {
        // Tab ichidagi elementlarni joylashtirish
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            // Icon
            icon()

            // Text
            Spacer(modifier = Modifier.width(4.dp))
            text()
        }
    }
}
