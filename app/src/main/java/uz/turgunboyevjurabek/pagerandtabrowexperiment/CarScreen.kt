package uz.turgunboyevjurabek.pagerandtabrowexperiment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CarScreen() {
    Column(modifier =Modifier.fillMaxSize(),
     ) {
        ListCar()
    }
}

@Composable
fun ListCar() {
    val list= listOf(
        ItemListHome(painterResource(id = R.drawable.car_fiel)),
        ItemListHome(painterResource(id = R.drawable.car_out)),
        ItemListHome(painterResource(id = R.drawable.car_fiel)),
        ItemListHome(painterResource(id = R.drawable.car_out)),
        ItemListHome(painterResource(id = R.drawable.car_fiel)),
        ItemListHome(painterResource(id = R.drawable.car_out)),
        ItemListHome(painterResource(id = R.drawable.car_fiel)),
        ItemListHome(painterResource(id = R.drawable.car_out)),
        ItemListHome(painterResource(id = R.drawable.car_fiel)),
        ItemListHome(painterResource(id = R.drawable.car_out)),
        ItemListHome(painterResource(id = R.drawable.car_fiel)),
        ItemListHome(painterResource(id = R.drawable.car_out)),
        ItemListHome(painterResource(id = R.drawable.car_fiel)),
        ItemListHome(painterResource(id = R.drawable.car_out)),

    )
    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 150.dp),
        content ={
            items(list.size){
                ItemUIHome(itemListhome = list[it])
            }
        } )
}
@Composable
fun ItemUIHome(itemListhome:ItemListHome) {
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
            Image(painter = itemListhome.img, contentDescription = null,Modifier.size(100.dp))
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun Preview() {
    ListCar()
}
data class ItemListHome(
    val img:Painter
)