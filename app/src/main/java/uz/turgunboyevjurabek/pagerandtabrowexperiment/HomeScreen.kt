package uz.turgunboyevjurabek.pagerandtabrowexperiment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(modifier: Modifier = Modifier.fillMaxSize()) {
    Column {
        ListUI()
    }
}
@Composable
fun ListUI() {
    val list= listOf(
        ItemList(painterResource(id = R.drawable.ic_launcher_foreground)),
        ItemList(painterResource(id = R.drawable.ic_launcher_foreground)),
        ItemList(painterResource(id = R.drawable.ic_launcher_foreground)),
        ItemList(painterResource(id = R.drawable.ic_launcher_foreground)),
        ItemList(painterResource(id = R.drawable.ic_launcher_foreground)),
        ItemList(painterResource(id = R.drawable.ic_launcher_foreground)),
        ItemList(painterResource(id = R.drawable.ic_launcher_foreground)),
        ItemList(painterResource(id = R.drawable.ic_launcher_foreground)),
        ItemList(painterResource(id = R.drawable.ic_launcher_foreground)),
        ItemList(painterResource(id = R.drawable.ic_launcher_foreground)),
        ItemList(painterResource(id = R.drawable.ic_launcher_foreground)),
        ItemList(painterResource(id = R.drawable.ic_launcher_foreground)),
        ItemList(painterResource(id = R.drawable.ic_launcher_foreground)),
        ItemList(painterResource(id = R.drawable.ic_launcher_foreground)),
        ItemList(painterResource(id = R.drawable.ic_launcher_foreground)),
        ItemList(painterResource(id = R.drawable.ic_launcher_foreground)),
        ItemList(painterResource(id = R.drawable.ic_launcher_foreground)),
        ItemList(painterResource(id = R.drawable.ic_launcher_foreground)),
    )
    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 150.dp)
        , content = {
            items(list.size){item->
                ItemUI(itemList =list[item])
            }
        })
}

@Composable
fun ItemUI(itemList: ItemList) {
    ElevatedCard(
        modifier = Modifier
            .height(180.dp)
            .padding(10.dp)
            .width(160.dp),
        elevation = CardDefaults.cardElevation(15.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Image(painter = itemList.img, contentDescription = null)
        }
    }
}
data class ItemList(
    val img: Painter,
)
@Preview(showSystemUi = true)
@Composable
fun ItemPreview() {
    ListUI()
}
